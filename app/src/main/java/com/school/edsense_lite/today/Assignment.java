package com.school.edsense_lite.today;

public class Assignment {
    private String _dueDate;
    private String _createdDate;

    public Assignment(String _dueDate, String _createdDate, String _status, String _title) {
        this._dueDate = _dueDate;
        this._createdDate = _createdDate;
        this._status = _status;
        this._title = _title;
    }

    private String _status;
    private String _title;

    public String get_dueDate() {
        return _dueDate;
    }

    public void set_dueDate(String _dueDate) {
        this._dueDate = _dueDate;
    }

    public String get_createdDate() {
        return _createdDate;
    }

    public void set_createdDate(String _createdDate) {
        this._createdDate = _createdDate;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }


}
