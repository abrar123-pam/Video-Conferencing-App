package com.example.zoom.Welcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.app.zoom.LoginActivity;
import com.example.zoom.JoinmeetingActivity;
import com.example.zoom.R;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private TextView textViewsignup,textViewsignin;
    private SpringDotsIndicator springDotsIndicator;
    private View btnJoinMeeting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initviews();
        clicklistners();
        setPagerAdapter();
    }



    private void initviews() {
        textViewsignin = findViewById(R.id.textViewSignIn);
        textViewsignup = findViewById(R.id.textViewSignup);
        viewPager = findViewById(R.id.viewPager);
        springDotsIndicator = findViewById(R.id.dots_indicator);
        btnJoinMeeting = findViewById(R.id.btnJoinMeeting);

    }
    private void clicklistners() {
        textViewsignin.setOnClickListener(this);
        textViewsignup.setOnClickListener(this);
        btnJoinMeeting.setOnClickListener(this);
    }
    private void setPagerAdapter() {
        viewPager.setAdapter(new WelcomePagerAdapter(this,getSupportFragmentManager()));
        springDotsIndicator.setViewPager(viewPager);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.textViewSignIn:
                navigateToSignIn(true);
                break;
            case R.id.textViewSignup:
                navigateToSignIn(false);
                break;

            case R.id.btnJoinMeeting:
                navigateTojoinMeetings();
                break;

        }
    }

    private void navigateTojoinMeetings() {
        Intent  intent = new Intent(this, JoinmeetingActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    private void navigateToSignIn(boolean isSignIn) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("isSignIn",isSignIn);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
