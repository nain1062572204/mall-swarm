package com.wang.mall.search.domain;


import java.util.List;

/**
 * 搜索相关商品品牌名称，分类名称及属性
 * Created by macro on 2019/12/27.
 */

public class EsProductRelatedInfo {
    private List<String> productCategoryNames;
    private List<ProductAttr> productAttrs;


    public List<String> getProductCategoryNames() {
        return productCategoryNames;
    }

    public void setProductCategoryNames(List<String> productCategoryNames) {
        this.productCategoryNames = productCategoryNames;
    }

    public List<ProductAttr> getProductAttrs() {
        return productAttrs;
    }

    public void setProductAttrs(List<ProductAttr> productAttrs) {
        this.productAttrs = productAttrs;
    }

    public static class ProductAttr {
        private Long attrId;
        private String attrName;
        private List<String> attrValues;
        private boolean showLess;

        public Long getAttrId() {
            return attrId;
        }

        public void setAttrId(Long attrId) {
            this.attrId = attrId;
        }

        public List<String> getAttrValues() {
            return attrValues;
        }

        public void setAttrValues(List<String> attrValues) {
            this.attrValues = attrValues;
        }

        public String getAttrName() {
            return attrName;
        }

        public void setAttrName(String attrName) {
            this.attrName = attrName;
        }

        public boolean isShowLess() {
            return showLess;
        }

        public void setShowLess(boolean showLess) {
            this.showLess = showLess;
        }
    }
}
