package com.school.edsense_lite.news;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "table_news")
public class News {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "news_newsId")
    private Integer newsId;
    @ColumnInfo(name = "news_newsName")
    private String newsName;
    @ColumnInfo(name = "news_typeName")
    private String typeName;
    @ColumnInfo(name = "news_description")
    private String description;
    @ColumnInfo(name = "news_newsTypeID")
    private Integer newsTypeID;
    @ColumnInfo(name = "news_newsDate")
    private String newsDate;
    @ColumnInfo(name = "news_expirationDate")
    private String expirationDate;
    @ColumnInfo(name = "news_link")
    private String link;
    @ColumnInfo(name = "news_prority")
    private Integer prority;
    @ColumnInfo(name = "news_organizationId")
    private Integer organizationId;
    @ColumnInfo(name = "news_viewPermissionMode")
    private String viewPermissionMode;
    @ColumnInfo(name = "news_viewPermissionInfo")
    private String viewPermissionInfo;
    @ColumnInfo(name = "news_viewPermissionInfoJson")
    private String viewPermissionInfoJson;
    @ColumnInfo(name = "news_isAdmin")
    private Boolean isAdmin;
    @ColumnInfo(name = "news_isPublish")
    private Boolean isPublish;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNewsTypeID() {
        return newsTypeID;
    }

    public void setNewsTypeID(Integer newsTypeID) {
        this.newsTypeID = newsTypeID;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getPrority() {
        return prority;
    }

    public void setPrority(Integer prority) {
        this.prority = prority;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getViewPermissionMode() {
        return viewPermissionMode;
    }

    public void setViewPermissionMode(String viewPermissionMode) {
        this.viewPermissionMode = viewPermissionMode;
    }

    public String getViewPermissionInfo() {
        return viewPermissionInfo;
    }

    public void setViewPermissionInfo(String viewPermissionInfo) {
        this.viewPermissionInfo = viewPermissionInfo;
    }

    public String getViewPermissionInfoJson() {
        return viewPermissionInfoJson;
    }

    public void setViewPermissionInfoJson(String viewPermissionInfoJson) {
        this.viewPermissionInfoJson = viewPermissionInfoJson;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Boolean isPublish) {
        this.isPublish = isPublish;
    }


}
