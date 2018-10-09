package com.school.edsense_lite.notes;

public class Note {
    private String _name;
    private String _traits;
    private String _reason;
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
