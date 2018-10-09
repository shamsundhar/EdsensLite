package com.school.edsense_lite.attendance;

public class Attendance {
    private String _name;
    private String _status;
    private String _reason;
    private String _imageUrl;

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
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



    public Attendance(String _name, String _status, String _reason, String _imageUrl) {
        this._name = _name;
        this._status = _status;
        this._reason = _reason;
        this._imageUrl = _imageUrl;
    }


}
