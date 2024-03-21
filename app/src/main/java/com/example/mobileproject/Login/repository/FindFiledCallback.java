package com.example.mobileproject.Login.repository;

public interface FindFiledCallback {
    void onUserFound(String foundField);
    void onUserNotFound(String errorMessage);
}
