package com.example.mobileproject.Home.data;
//Firebase Firestore로부터 가져온 데이터를 애플리케이션 내부에서 사용할 수 있는 형태로 바꿈

public class BestItemData {
    private BestPlaceData place;
    private BestCourseData course;

    // 장소를 나타내는 BestItemData 생성
    public BestItemData(BestPlaceData place) {
        this.place = place;
        this.course = null;
    }

    // 코스를 나타내는 BestItemData 생성
    public BestItemData(BestCourseData course) {
        this.place = null;
        this.course = course;
    }

    // getter 메서드
    public BestPlaceData getPlace() {
        return this.place;
    }

    public BestCourseData getCourse() {
        return this.course;
    }
}
