package com.example.mobileproject.Login.repository;

public interface FindFieldInterface {
    void findUserByField(String field, String value, String getField, FindFiledCallback callback);
}
