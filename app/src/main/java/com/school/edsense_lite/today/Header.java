package com.school.edsense_lite.today;

public class Header {
    public static int ASSIGNMENT_HEADER = 1;
    public static int SCHEDULE_HEADER = 2;
    private int headerType;
    private String title;

    public Header(String title, int headerType) {
        this.title = title;
        this.headerType = headerType;
    }

    public int getHeaderType() {
        return headerType;
    }

    public void setHeaderType(int headerType) {
        this.headerType = headerType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
