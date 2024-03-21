package com.example.mobileproject.Login.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class DocumentWrite implements DocumentWriteInterface {
    private FirebaseFirestore db;

    public DocumentWrite(FirebaseFirestore db) {
        this.db = db;
    }

    @Override
    public <T> Task<Void> setDocument(String collectionPath, String documentPath, T data) {
        return db.collection(collectionPath)
                .document(documentPath)
                .set(data);
    }

    @Override
    public <T> Task<Void> updateDocumentField(String collectionPath, String documentPath, String field, T newValue) {
        return db.collection(collectionPath)
                .document(documentPath)
                .update(field, newValue);
    }
}
