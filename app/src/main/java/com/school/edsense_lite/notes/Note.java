package com.school.edsense_lite.notes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "table_notes")
public class Note {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "notes_name")
    private String _name;
    @ColumnInfo(name = "notes_traits")
    private String _traits;
    @ColumnInfo(name = "notes_reason")
    private String _reason;
    @ColumnInfo(name = "notes_imageUrl")
    private String _imageUrl;

    public Note(String _name, String _traits, String _reason, String _imageUrl) {
        this._name = _name;
        this._traits = _traits;
        this._reason = _reason;
        this._imageUrl = _imageUrl;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_traits() {
        return _traits;
    }

    public void set_traits(String _traits) {
        this._traits = _traits;
    }

    public String get_reason() {
        return _reason;
    }

    public void set_reason(String _reason) {
        this._reason = _reason;
    }

    public String get_imageUrl() {
        return _imageUrl;
    }

    public void set_imageUrl(String _imageUrl) {
        this._imageUrl = _imageUrl;
    }


}