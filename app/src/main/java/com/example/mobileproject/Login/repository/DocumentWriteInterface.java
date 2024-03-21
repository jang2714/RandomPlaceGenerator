package com.example.mobileproject.Login.repository;

import com.google.android.gms.tasks.Task;

//Firestore의 문서를 작성하거나 업데이트하는 메소드 정의
public interface DocumentWriteInterface {
    <T> Task<Void> setDocument(String collectionPath, String documentPath, T data); //새 문서의 새 필드 생성
    <T> Task<Void> updateDocumentField(String collectionPath, String documentPath, String field, T newValue); //기존 문서의 필드 업데이트
}