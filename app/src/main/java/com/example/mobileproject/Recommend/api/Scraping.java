package com.example.mobileproject.Recommend.api;

import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mobileproject.Recommend.activitiy.RecommendActivity3;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scraping {
    private boolean isPageLoaded = false;
    String url;

    public Scraping(WebView webView, String placeUrl){
        //WebView 자바스크립트 활성화
        webView.getSettings().setJavaScriptEnabled(true);
        // 자바스크립트인터페이스 연결
        // 이걸 통해 자바스크립트 내에서 자바함수에 접근할 수 있음.
        webView.addJavascriptInterface(new MyJavascriptInterface(), "Android");
        // 페이지가 모두 로드되었을 때, 작업 정의
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 페이지 로딩이 완료되면 isPageLoaded를 true로 설정
//                isPageLoaded = true;
                // 1초 뒤에 자바스크립트 코드 실행
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.loadUrl("javascript:window.Android.getHtml(document.getElementsByTagName('body')[0].innerHTML);");
                    }
                }, 1000);
            }
        });

        //지정한 URL을 웹 뷰로 접근하기
        webView.loadUrl(placeUrl);

//        // 일정 시간 간격으로 페이지 로딩 상태를 확인하여 HTML을 가져옴
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (isPageLoaded) {
//                    webView.loadUrl("javascript:window.Android    .getHtml(document.getElementsByTagName('html')[0].innerHTML);");
//                    Log.d("TAG", "onResponse: 실행 ");
//                } else {
//                    // 페이지가 아직 로딩 중이면 일정 시간 후에 다시 시도
//                    handler.postDelayed(this, 1000);
//                }
//            }
//        }, 1000);

    }



    public class MyJavascriptInterface {
        @JavascriptInterface
        public void getHtml(String html) {
            // 동적으로 생성된 콘텐츠 추출을 위한 추가 작업 수행
            // 예를 들어, 클래스가 "bg_present"인 요소의 내용을 추출한다고 가정
            //위 자바스크립트가 호출되면 여기로 html이 반환됨
            String imageUrl = null;
            org.jsoup.nodes.Document document = Jsoup.parse(html);
            Elements elements = document.select("a.details_present");
            if (!elements.isEmpty()) {
                Element element = elements.first();
                String backgroundImage = element.attr("style");
                imageUrl = backgroundImage.replaceFirst(".*url\\(&quot;", "").replaceFirst("&quot;\\);.*", "");
                Log.d("스크래핑", "Image URL: " + imageUrl);
            }
            String style = imageUrl;
            Log.d("Style", "getHtml: " + style);
            // 정규 표현식 패턴
            String pattern = "url\\(\"(.*?)\"\\)";
            int i = 0;
            String s;
            // 패턴과 일치하는 부분 추출
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(style);
            if (matcher.find()) {
                // 첫 번째 그룹의 값 추출
                s = matcher.group(1);
                Log.d("TAG", "ssss : " + s);
                RecommendActivity3.url[RecommendActivity3.img_index++] = s;

                Log.d("TAG", "ssss : " + RecommendActivity3.url[RecommendActivity3.img_index] + "인덱스 " + RecommendActivity3.img_index);
                i++;
                Log.d("Image URL", "getHtml: " + RecommendActivity3.url);

            }
            String source = html;
            //Log.d("스크래핑", "onCreate: " + source);
        }
    }


}
