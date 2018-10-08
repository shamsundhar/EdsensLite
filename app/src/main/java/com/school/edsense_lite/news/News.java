package com.school.edsense_lite.news;

public class News {
    private String _title;
    private String _description;
    private String _date;
    public News(String _title, String _description, String _date) {
        this._title = _title;
        this._description = _description;
        this._date = _date;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }


}
