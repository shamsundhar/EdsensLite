package com.school.edsense_lite.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "table_messages")
public class MessagesResponseModel {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "message_id")
    private int id;
    @ColumnInfo(name = "message_mapId")
    private int mapId;
    @ColumnInfo(name = "message_orginatorId")
    private String orginatorId;
    @ColumnInfo(name = "message_orginator")
    private String orginator;
    @ColumnInfo(name = "message_subject")
    private String subject;
    @ColumnInfo(name = "message_transactionDate")
    private String transactionDate;
    @ColumnInfo(name = "message_priority")
    private String priority;
    @ColumnInfo(name = "message_contextTypeId")
    private int contextTypeId;
    @ColumnInfo(name = "message_contextDisplayName")
    private String contextDisplayName;
    @ColumnInfo(name = "message_contextId")
    private int contextId;
    @ColumnInfo(name = "message_groupTypeId")
    private int groupTypeId;
    @ColumnInfo(name = "message_isRead")
    private int isRead;
    @ColumnInfo(name = "message_totalCount")
    private int totalCount;
    @ColumnInfo(name = "message_timeStampMsg")
    private String timeStampMsg;
    @ColumnInfo(name = "message_originatorImageUrl")
    private String originatorImageUrl;
    @ColumnInfo(name = "message_messageBody")
    private String messageBody;
    @ColumnInfo(name = "message_imageFileId")
    private String imageFileId;
    //private Map<String, String> additionalProperties = new HashMap<String, String>();


    public MessagesResponseModel(int id, int mapId, String orginatorId, String orginator, String subject, String transactionDate, String priority, int contextTypeId, String contextDisplayName, int contextId, int groupTypeId, int isRead, int totalCount, String timeStampMsg, String originatorImageUrl, String messageBody, String imageFileId) {
        this.id = id;
        this.mapId = mapId;
        this.orginatorId = orginatorId;
        this.orginator = orginator;
        this.subject = subject;
        this.transactionDate = transactionDate;
        this.priority = priority;
        this.contextTypeId = contextTypeId;
        this.contextDisplayName = contextDisplayName;
        this.contextId = contextId;
        this.groupTypeId = groupTypeId;
        this.isRead = isRead;
        this.totalCount = totalCount;
        this.timeStampMsg = timeStampMsg;
        this.originatorImageUrl = originatorImageUrl;
        this.messageBody = messageBody;
        this.imageFileId = imageFileId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public String getOrginatorId() {
        return orginatorId;
    }

    public void setOrginatorId(String orginatorId) {
        this.orginatorId = orginatorId;
    }

    public String getOrginator() {
        return orginator;
    }

    public void setOrginator(String orginator) {
        this.orginator = orginator;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getContextTypeId() {
        return contextTypeId;
    }

    public void setContextTypeId(int contextTypeId) {
        this.contextTypeId = contextTypeId;
    }

    public String getContextDisplayName() {
        return contextDisplayName;
    }

    public void setContextDisplayName(String contextDisplayName) {
        this.contextDisplayName = contextDisplayName;
    }

    public int getContextId() {
        return contextId;
    }

    public void setContextId(int contextId) {
        this.contextId = contextId;
    }

    public int getGroupTypeId() {
        return groupTypeId;
    }

    public void setGroupTypeId(int groupTypeId) {
        this.groupTypeId = groupTypeId;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getTimeStampMsg() {
        return timeStampMsg;
    }

    public void setTimeStampMsg(String timeStampMsg) {
        this.timeStampMsg = timeStampMsg;
    }

    public String getOriginatorImageUrl() {
        return originatorImageUrl;
    }

    public void setOriginatorImageUrl(String originatorImageUrl) {
        this.originatorImageUrl = originatorImageUrl;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getImageFileId() {
        return imageFileId;
    }

    public void setImageFileId(String imageFileId) {
        this.imageFileId = imageFileId;
    }

//    public Map<String, String> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//    public void setAdditionalProperty(String name, String value) {
//        this.additionalProperties.put(name, value);
//    }

}