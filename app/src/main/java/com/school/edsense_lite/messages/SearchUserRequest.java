package com.school.edsense_lite.messages;

public class SearchUserRequest {
    private Value value;

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public class Value {

        private String keyword;
        private Integer categoryId;
        private String contextTagIds;
        private Integer selectedOrgRoleId;
        private Integer orgID;
        private String tagContextName;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public String getContextTagIds() {
            return contextTagIds;
        }

        public void setContextTagIds(String contextTagIds) {
            this.contextTagIds = contextTagIds;
        }

        public Integer getSelectedOrgRoleId() {
            return selectedOrgRoleId;
        }

        public void setSelectedOrgRoleId(Integer selectedOrgRoleId) {
            this.selectedOrgRoleId = selectedOrgRoleId;
        }

        public Integer getOrgID() {
            return orgID;
        }

        public void setOrgID(Integer orgID) {
            this.orgID = orgID;
        }

        public String getTagContextName() {
            return tagContextName;
        }

        public void setTagContextName(String tagContextName) {
            this.tagContextName = tagContextName;
        }
    }
}
