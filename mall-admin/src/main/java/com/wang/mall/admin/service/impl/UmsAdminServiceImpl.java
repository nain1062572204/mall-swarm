package com.wang.mall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.wang.mall.admin.bo.UmsAdminUserDetails;
import com.wang.mall.admin.dao.UmsAdminPermissionRelationDao;
import com.wang.mall.admin.dao.UmsAdminRoleRelationDao;
import com.wang.mall.admin.dto.UmsAdminParam;
import com.wang.mall.admin.dto.UpdateAdminPasswordParam;
import com.wang.mall.admin.service.UmsAdminCacheService;
import com.wang.mall.mapper.UmsAdminLoginLogMapper;
import com.wang.mall.mapper.UmsAdminMapper;
import com.wang.mall.mapper.UmsAdminPermissionRelationMapper;
import com.wang.mall.mapper.UmsAdminRoleRelationMapper;
import com.wang.mall.model.*;
import com.wang.mall.security.util.JwtTokenUtil;
import com.wang.mall.admin.service.UmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王念
 * @create 2020-02-07 16:58
 * 管理员Service实现类
 */
@Service
@Slf4j
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    private UmsAdminLoginLogMapper adminLoginLogMapper;
    @Autowired
    private UmsAdminPermissionRelationMapper adminPermissionRelationMapper;
    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;
    @Autowired
    private UmsAdminPermissionRelationDao adminPermissionRelationDao;
    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        //先读缓存
        UmsAdmin admin = adminCacheService.getAdmin(username);
        if (admin != null)
            return admin;
        //没有缓存从数据库中读
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria()
                .andUsernameEqualTo(username);
        List<UmsAdmin> admins = adminMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(admins)) {
            admin = admins.get(0);
            //将从数据库中拿出的数据放入缓存
            adminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否具有同名用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> admins = adminMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(admins))
            return null;
        //密码加密
        umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam updatePasswordParam) {
        if (StringUtils.isEmpty(updatePasswordParam.getUsername())
                || StringUtils.isEmpty(updatePasswordParam.getNewPassword())
                || StringUtils.isEmpty(updatePasswordParam.getOldPassword())
        ) return -1;
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(updatePasswordParam.getUsername());
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(adminList)) {
            return -2;
        }
        UmsAdmin umsAdmin = adminList.get(0);
        if (!passwordEncoder.matches(updatePasswordParam.getOldPassword(), umsAdmin.getPassword())) {
            return -3;
        }
        umsAdmin.setPassword(passwordEncoder.encode(updatePasswordParam.getNewPassword()));
        adminMapper.updateByPrimaryKey(umsAdmin);
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            //List<UmsPermission> permissionList = getPermissionList(admin.getId());
            return new UmsAdminUserDetails(admin, getResourceList(admin.getId()));
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//          updateLoginTimeByUsername(username);
//          insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample example = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
            example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
        }
        return adminMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, UmsAdmin admin) {
        admin.setId(id);
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public int delete(Long id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds))
            return 0;
        //删除原有关系
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andAdminIdEqualTo(adminId);
        adminRoleRelationMapper.deleteByExample(example);
        //建立新关系
        List<UmsAdminRoleRelation> roleRelations = new ArrayList<>(roleIds.size());
        for (Long roleId : roleIds) {
            UmsAdminRoleRelation roleRelation = UmsAdminRoleRelation.builder()
                    .id(roleId)
                    .adminId(adminId)
                    .build();
            roleRelations.add(roleRelation);
        }
        adminRoleRelationDao.insertList(roleRelations);
        return roleIds.size();
    }

    @Override
    public List<UmsRole> getRoles(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        //先从缓存中拿数据
        List<UmsResource> resourceList = adminCacheService.getResourceList(adminId);
        if (!CollectionUtils.isEmpty(resourceList))
            return resourceList;
        //没有缓存从数据库中拿数据

        resourceList = adminRoleRelationDao.getResourceList(adminId);
        if (!CollectionUtils.isEmpty(resourceList)) {
            //将数据放入缓存
            adminCacheService.setResourceList(adminId, resourceList);
        }
        return resourceList;
    }

    @Override
    public int updatePermission(Long adminId, List<Long> permissionIds) {
        //删除原所有权限关系
        UmsAdminPermissionRelationExample relationExample = new UmsAdminPermissionRelationExample();
        relationExample.createCriteria().andAdminIdEqualTo(adminId);
        adminPermissionRelationMapper.deleteByExample(relationExample);
        //获取用户所有角色权限
        List<UmsPermission> permissionList = adminRoleRelationDao.getRolePermissionList(adminId);
        List<Long> rolePermissionList = permissionList.stream().map(UmsPermission::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(permissionIds)) {
            List<UmsAdminPermissionRelation> relationList = new ArrayList<>();
            //筛选出+权限
            List<Long> addPermissionIdList = permissionIds.stream().filter(permissionId -> !rolePermissionList.contains(permissionId)).collect(Collectors.toList());
            //筛选出-权限
            List<Long> subPermissionIdList = rolePermissionList.stream().filter(permissionId -> !permissionIds.contains(permissionId)).collect(Collectors.toList());
            //插入+-权限关系
            relationList.addAll(convert(adminId, 1, addPermissionIdList));
            relationList.addAll(convert(adminId, -1, subPermissionIdList));
            return adminPermissionRelationDao.insertList(relationList);
        }
        return 0;
    }

    /**
     * 将+-权限关系转化为对象
     */
    private List<UmsAdminPermissionRelation> convert(Long adminId, Integer type, List<Long> permissionIdList) {
        List<UmsAdminPermissionRelation> relationList = permissionIdList.stream().map(permissionId -> {
            UmsAdminPermissionRelation relation = new UmsAdminPermissionRelation();
            relation.setAdminId(adminId);
            relation.setType(type);
            relation.setPermissionId(permissionId);
            return relation;
        }).collect(Collectors.toList());
        return relationList;
    }


    /**
     * 添加登录记录
     */
    private void insertLoginLog(String username) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        UmsAdmin admin = getAdminByUsername(username);
        UmsAdminLoginLog adminLoginLog = UmsAdminLoginLog.builder()
                .adminId(admin.getId())
                .createTime(new Date())
                .ip(request.getRemoteAddr())
                .userAgent("")
                .address("")
                .build();
        adminLoginLogMapper.insert(adminLoginLog);
    }
}
