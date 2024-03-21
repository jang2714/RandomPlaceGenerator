package com.example.mobileproject.Bookmark.course;

public class BookmarkCourseData {
    private String courseName;
    private String courseLocation;
    private int courseImageView;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseLocation() {
        return courseLocation;
    }

    public void setCourseLocation(String courseLocation) {
        this.courseLocation = courseLocation;
    }

    public int getCourseImageView() {
        return courseImageView;
    }

    public void setCourseImageView(int courseImageView) {
        this.courseImageView = courseImageView;
    }

    public BookmarkCourseData(String courseName, String courseLocation, int courseImageView) {
        this.courseName = courseName;
        this.courseLocation = courseLocation;
        this.courseImageView = courseImageView;
    }
}
