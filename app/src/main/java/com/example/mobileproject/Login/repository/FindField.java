package com.example.mobileproject.Login.repository;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

//Firestore에 대한 실제 데이터 액세스 코드를 구현
//db필드와 유저 입력값을 비교 후 일치하는 필드값을 추출

public class FindField implements FindFieldInterface {
    private FirebaseFirestore firestore;

    public FindField() {
        firestore = FirebaseFirestore.getInstance();
    }
    @Override
    public void findUserByField(String field, String value, String getField, FindFiledCallback callback) {
        firestore.collection("user")
                .whereEqualTo(field, value) //비교할 필드키, 입력값 비교
                .get()
                .addOnCompleteListener(task -> { //일치하는 필드 찾으면
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
                            DocumentSnapshot document = querySnapshot.getDocuments().get(0);
                            String foundField = document.getString(getField); //foundField = 해당 getField 키의 필드값 추출
                            if (foundField != null) {
                                callback.onUserFound(foundField); //foundField 반환
                            } else {
                                callback.onUserNotFound(capitalizeFirstLetter(field) + " not found");
                            }
                        } else {
                            callback.onUserNotFound("User not found");
                        }
                    } else {
                        callback.onUserNotFound("Failed to find user");
                    }
                });
    }

    //문자열의 첫 글자를 대문자로 변환
    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str; //str이 null이거나 비어있는 경우는 그대로 반환
        }
        char firstChar = str.charAt(0);
        if (Character.isUpperCase(firstChar)) {
            return str; //첫 글자가 이미 대문자인 경우도 그대로 반환
        } else {
            return Character.toUpperCase(firstChar) + str.substring(1); //첫 글자를 대문자로 변환한 후 나머지 문자열과 연결하여 반환
        }
    }
}
