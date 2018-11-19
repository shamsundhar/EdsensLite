package com.school.edsense_lite.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "table_section_response")
public class SectionResponseModel {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "sectionresponse_compositeTagId")
    private String compositeTagId;
    @ColumnInfo(name = "sectionresponse_compositeTagName")
    private String compositeTagName;


    public String getCompositeTagName() {
        return compositeTagName;
    }

    public void setCompositeTagName(String compositeTagName) {
        this.compositeTagName = compositeTagName;
    }

    public String getCompositeTagId() {
        return compositeTagId;
    }

    public void setCompositeTagId(String compositeTagId) {
        this.compositeTagId = compositeTagId;
    }
}
