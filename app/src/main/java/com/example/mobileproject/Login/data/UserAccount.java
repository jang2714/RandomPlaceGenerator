package com.example.mobileproject.Login.data;

//사용자 계정 정보
public class UserAccount {
    private String idToken; //Firebase Uid (고유 토큰 정보)
    private String id;
    private String password;
    private String phoneNumber;
    private String nickname;
    private String passwordHint;
    private String passwordHintAnswer;

    public UserAccount() { } //firebase에서 모델 클래스를 이용해서 가지고 와야할 때 빈 생성자를 만들어야 함

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPasswordHint() {
        return passwordHint;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    public String getPasswordHintAnswer() {
        return passwordHintAnswer;
    }

    public void setPasswordHintAnswer(String passwordHintAnswer) {
        this.passwordHintAnswer = passwordHintAnswer;
    }
}