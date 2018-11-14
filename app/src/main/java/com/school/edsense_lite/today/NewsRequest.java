package com.school.edsense_lite.today;

public class NewsRequest {
    private Value value;

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
    public class Value {

        private String newsTypeId;

        public String getNewsTypeId() {
            return newsTypeId;
        }

        public void setNewsTypeId(String newsTypeId) {
            this.newsTypeId = newsTypeId;
        }

    }
}
