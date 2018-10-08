package com.school.edsense_lite.today;

public class Schedule {
    private String _time;
    private String _subject;

    public Schedule(String _time, String _subject, String _section, String _class) {
        this._time = _time;
        this._subject = _subject;
        this._section = _section;
        this._class = _class;
    }

    private String _section;
    private String _class;
    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }

    public String get_subject() {
        return _subject;
    }

    public void set_subject(String _subject) {
        this._subject = _subject;
    }

    public String get_section() {
        return _section;
    }

    public void set_section(String _section) {
        this._section = _section;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }



}
