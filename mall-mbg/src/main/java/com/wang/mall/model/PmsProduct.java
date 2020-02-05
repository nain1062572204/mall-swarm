package com.wang.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

public class PmsProduct implements Serializable {
    @ApiModelProperty(value = "商品id")
    private Long id;

    @ApiModelProperty(value = "属性id")
    private Long productAttributeCategoryId;

    @ApiModelProperty(value = "分类id")
    private Long productCategoryId;

    @ApiModelProperty(value = "标题")
    private String name;

    @ApiModelProperty(value = "商品轮播图片")
    private String pic;

    @ApiModelProperty(value = "货号")
    private String productSn;

    @ApiModelProperty(value = "删除状态：0->未删除；1->已删除")
    private Integer deleteStatus;

    @ApiModelProperty(value = "上架状态：0->下架；1->上架")
    private Integer publishStatus;

    @ApiModelProperty(value = "新品状态：0->不是新品；1->新品")
    private Integer newStatus;

    @ApiModelProperty(value = "推荐状态：0->不推荐；1->推荐")
    private Integer recommandStatus;

    @ApiModelProperty(value = "审核状态：0->未审核；1->审核通过")
    private Integer verifyStatus;

    @ApiModelProperty(value = "排序，越小越靠前")
    private Integer sort;

    @ApiModelProperty(value = "销量")
    private Integer sale;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "促销价")
    private BigDecimal promotionPrice;

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "市场价")
    private BigDecimal orginalPrice;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "单位")
    private String unit;

    private BigDecimal weight;

    @ApiModelProperty(value = "以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮")
    private String servicesIds;

    private String keywords;

    @ApiModelProperty(value = "画册图片，连产品图片限制为5张，以逗号分割")
    private String albumPics;

    @ApiModelProperty(value = "商品分类名称")
    private String productCategoryName;

    @ApiModelProperty(value = "产品详情")
    private String detailHtml;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }

    public void setProductAttributeCategoryId(Long productAttributeCategoryId) {
        this.productAttributeCategoryId = productAttributeCategoryId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    public Integer getRecommandStatus() {
        return recommandStatus;
    }

    public void setRecommandStatus(Integer recommandStatus) {
        this.recommandStatus = recommandStatus;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getOrginalPrice() {
        return orginalPrice;
    }

    public void setOrginalPrice(BigDecimal orginalPrice) {
        this.orginalPrice = orginalPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getServicesIds() {
        return servicesIds;
    }

    public void setServicesIds(String servicesIds) {
        this.servicesIds = servicesIds;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAlbumPics() {
        return albumPics;
    }

    public void setAlbumPics(String albumPics) {
        this.albumPics = albumPics;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getDetailHtml() {
        return detailHtml;
    }

    public void setDetailHtml(String detailHtml) {
        this.detailHtml = detailHtml;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productAttributeCategoryId=").append(productAttributeCategoryId);
        sb.append(", productCategoryId=").append(productCategoryId);
        sb.append(", name=").append(name);
        sb.append(", pic=").append(pic);
        sb.append(", productSn=").append(productSn);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", publishStatus=").append(publishStatus);
        sb.append(", newStatus=").append(newStatus);
        sb.append(", recommandStatus=").append(recommandStatus);
        sb.append(", verifyStatus=").append(verifyStatus);
        sb.append(", sort=").append(sort);
        sb.append(", sale=").append(sale);
        sb.append(", price=").append(price);
        sb.append(", promotionPrice=").append(promotionPrice);
        sb.append(", description=").append(description);
        sb.append(", orginalPrice=").append(orginalPrice);
        sb.append(", stock=").append(stock);
        sb.append(", unit=").append(unit);
        sb.append(", weight=").append(weight);
        sb.append(", servicesIds=").append(servicesIds);
        sb.append(", keywords=").append(keywords);
        sb.append(", albumPics=").append(albumPics);
        sb.append(", productCategoryName=").append(productCategoryName);
        sb.append(", detailHtml=").append(detailHtml);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}