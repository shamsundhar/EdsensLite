package com.school.edsense_lite.messages;

public class MessageDetailsRequest {

    private Value value;

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public static class Value {
        private String Id;
        private String mapId;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getMapId() {
            return mapId;
        }

        public void setMapId(String mapId) {
            this.mapId = mapId;
        }
    }
}
