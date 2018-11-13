package com.school.edsense_lite.messages;

public class MessagesRequest {


    //{"value":{"levelId":"2|1","pageStartIndex":1,"pageSize":10,"filters":""}}
    private Value value;
    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
    public static class Value {

        private String levelId;
        private String pageStartIndex;
        private String pageSize;
        private String filters;

        public String getLevelId() {
            return levelId;
        }

        public void setLevelId(String levelId) {
            this.levelId = levelId;
        }

        public String getPageStartIndex() {
            return pageStartIndex;
        }

        public void setPageStartIndex(String pageStartIndex) {
            this.pageStartIndex = pageStartIndex;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getFilters() {
            return filters;
        }

        public void setFilters(String filters) {
            this.filters = filters;
        }


    }

}