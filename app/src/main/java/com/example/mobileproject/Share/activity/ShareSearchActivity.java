package com.example.mobileproject.Share.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import com.example.mobileproject.R;
import com.example.mobileproject.Share.fragment.ShareFragment;
import com.example.mobileproject.baseactivity.BaseActivity;


public class ShareSearchActivity extends BaseActivity {

    EditText editText;
    Button search;
    LinearLayout historyLayout;
    ArrayList<String> searchHistory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_share);

        editText = findViewById(R.id.search);
        historyLayout = findViewById(R.id.historyLayout);

        searchHistory = new ArrayList<>();

        search = findViewById(R.id.search_button);

        //TODO cancle 삭제

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addSearchHistory();
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    addSearchHistory();
                    return true;
                }
                return false;
            }
        });
    }

    public void addSearchHistory() {
        String searchQuery = editText.getText().toString().trim();

        if (!TextUtils.isEmpty(searchQuery)) {
            searchHistory.add(searchQuery);
            updateSearchHistoryUI();
            editText.setText("");

            Intent intent = new Intent(ShareSearchActivity.this, ShareFragment.class);
            intent.putExtra("searchQuery", searchQuery);
            startActivity(intent);
        }
    }

    public void updateSearchHistoryUI() {
        historyLayout.removeAllViews();

        for (final String searchQuery : searchHistory) {
            View searchItemView = LayoutInflater.from(this).inflate(R.layout.activity_searchsub1, null);

            TextView textView = searchItemView.findViewById(R.id.search_text);
            textView.setText(searchQuery);

            ImageView deleteImageView = searchItemView.findViewById(R.id.delete_btn);
            deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteSearchHistory(searchQuery);
                }
            });

            historyLayout.addView(searchItemView);
        }
    }

    public void deleteSearchHistory(String searchQuery) {
        searchHistory.remove(searchQuery);
        updateSearchHistoryUI();
    }
}