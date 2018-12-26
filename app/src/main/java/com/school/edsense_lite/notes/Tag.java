package com.school.edsense_lite.notes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
@Entity(tableName = "table_notesTags")
public class Tag implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "notesTags_TagId")
    private String TagId;
    @ColumnInfo(name = "notesTags_Value")
    private String Value;
    @ColumnInfo(name = "notesTags_Category")
    private String Category;
    @ColumnInfo(name = "notesTags_CategoryId")
    private String CategoryId;
    @ColumnInfo(name = "notesTags_Type")
    private String Type;
    @ColumnInfo(name = "notesTags_Name")
    private String Name;
    @ColumnInfo(name = "notesTags_TagName")
    private String TagName;

    @ColumnInfo(name = "notesTags_PkValue")
    @ForeignKey(entity = Note.class, parentColumns = "notes_StudentNotesId", childColumns = "notesTags_PkValue")
    private String PkValue;

    @NonNull
    public String getTagId() {
        return TagId;
    }

    public void setTagId(@NonNull String tagId) {
        TagId = tagId;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    public String getPkValue() {
        return PkValue;
    }

    public void setPkValue(String pkValue) {
        PkValue = pkValue;
    }
}
