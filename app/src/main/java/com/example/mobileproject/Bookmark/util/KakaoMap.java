package com.example.mobileproject.Bookmark.util;

import android.app.Activity;
import android.widget.RelativeLayout;

import com.example.mobileproject.Bookmark.vo.MapMarkVO;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.checkerframework.checker.units.qual.K;

public class KakaoMap {

    private static KakaoMap instance;
    private MapView mapView;

    RelativeLayout mLoaderLayout;
    //value
    MapPoint currentMapPoint;
    private double mCurrentLng; //Long = X, Lat = Yㅌ
    private double mCurrentLat;
    boolean isTrackingMode = false;

    private static final int REQUEST_LOCATION_PERMISSION = 1001;

    public KakaoMap() {
    }

    public static KakaoMap getInstance() {
        if(instance == null){
            instance = new KakaoMap();
        }
        return instance;
    }

    public void initialize(Activity activity){
        mapView = new MapView(activity);
    }

    public MapView getMapView() {
        return mapView;
    }

    public void MapPOIItem (MapMarkVO[] mark){

        MapPOIItem[] marker = new MapPOIItem[mark.length];
        //표시할 장소에 정보 입력
        for(int i = 0; i < mark.length; i++){
            marker[i] = new MapPOIItem();
            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(mark[i].getLat(), mark[i].getLon());
            marker[i].setItemName(mark[i].getName());
            marker[i].setTag(0);
            marker[i].setMapPoint(mapPoint);
            marker[i].setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
            marker[i].setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양

        }
        mapView.addPOIItems(marker);
        mapView.fitMapViewAreaToShowAllPOIItems(); // 지도 화면에 추가된 모든 POI Item들이 화면에 나타나도록 지도 화면 중심과 확대/축소 레벨을 자동으로 조정한다.



    }


    public void MyLocation(){
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving);
    }








//    @Override
//    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float accuracyInMeters) {
//        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
//        Log.i(TAG, String.format("MapView onCurrentLocationUpdate (%f,%f) accuracy (%f)", mapPointGeo.latitude, mapPointGeo.longitude, accuracyInMeters));
//        currentMapPoint = MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude);
//        //이 좌표로 지도 중심 이동
//        mapView.setMapCenterPoint(currentMapPoint, true);
//        //전역변수로 현재 좌표 저장
//        mCurrentLat = mapPointGeo.latitude;
//        mCurrentLng = mapPointGeo.longitude;
//        Log.d(TAG, "현재위치 => " + mCurrentLat + "  " + mCurrentLng);
//        mLoaderLayout.setVisibility(View.GONE);
//        //트래킹 모드가 아닌 단순 현재위치 업데이트일 경우, 한번만 위치 업데이트하고 트래킹을 중단시키기 위한 로직
//        if (!isTrackingMode) {
//            mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
//        }
//    }
//
//    @Override
//    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {
//
//    }
//
//    @Override
//    public void onCurrentLocationUpdateFailed(MapView mapView) {
//        Log.i(TAG, "onCurrentLocationUpdateFailed");
//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
//
//    }
//
//    @Override
//    public void onCurrentLocationUpdateCancelled(MapView mapView) {
//        Log.i(TAG, "onCurrentLocationUpdateCancelled");
//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
//
//    }
//
//
//
//    private LocationListener locationListener = new LocationListener() {
//        @Override
//        public void onLocationChanged(@NonNull Location location) {
//            // 새로운 위치 정보가 도착했을 때 처리 로직을 여기에 작성합니다.
//            double latitude = location.getLatitude(); // 위도
//            double longitude = location.getLongitude(); // 경도
//            //콘솔에 위치 정보 출력
//            Log.d("Location", "Latitude: " + latitude + ", Longitude: " + longitude);
//
//            // 여기서 필요한 작업을 수행합니다. 지도에 위치를 표시하거나 다른 처리를 수행할 수 있습니다.
//            // 예시: 지도에 위치 표시하기
//            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
//            mapView.setMapCenterPoint(mapPoint, true);
//        }
//    };
}
