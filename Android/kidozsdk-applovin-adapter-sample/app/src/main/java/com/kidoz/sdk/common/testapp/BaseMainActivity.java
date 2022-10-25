package com.kidoz.sdk.common.testapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.kidoz.android.applovin.sampleapp.R;

import java.io.File;

public abstract class BaseMainActivity extends Activity
{

    // The load interstitial button.
    protected Button loadInterstitialButton;
    // The show interstitial button.
    protected Button showInterstitialButton;
    // The load rewarded ad button.
    protected Button loadRewardedButton;
    // The load rewarded ad button.
    protected Button showRewardedButton;

    private ScrollView feedbackContainer;
    private TextView feedbackTV;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle(getAppLabel() + " v" + getSDKVersion());
        setContentView(R.layout.activity_main);

        feedbackContainer = findViewById(R.id.feedbackContainer);
        feedbackTV = findViewById(R.id.feedbackTextView);

        log("App version SDK "+ getAppVersion());

        // Interstitial Ad
        loadInterstitialButton = (Button) findViewById(R.id.interstitial_load);
        loadInterstitialButton.setOnClickListener(view -> {
            log("Load Interstitial");
            loadInterstitialButton.setEnabled(false);
            loadInterstitial();
        });
        showInterstitialButton = (Button) findViewById(R.id.interstitial_show);
        showInterstitialButton.setOnClickListener(view -> {
            log("Show Interstitial");
            loadInterstitialButton.setEnabled(true);
            showInterstitialButton.setEnabled(false);
            showInterstitial();
        });

        // Rewarded Ad
        loadRewardedButton = (Button) findViewById(R.id.rewarded_load);
        loadRewardedButton.setOnClickListener(view -> {
            log("Load Rewarded");
            loadRewardedButton.setEnabled(false);
            loadRewarded();
        });
        showRewardedButton = (Button) findViewById(R.id.rewarded_show);
        showRewardedButton.setOnClickListener(view -> {
            log("Show Rewarded");
            loadRewardedButton.setEnabled(true);
            showRewardedButton.setEnabled(false);
            showRewarded();
        });

        initSDK();
    }

    protected void onSDKInitSuccess(){
        log("SDK Init Success");
        loadInterstitialButton.setEnabled(true);
        loadRewardedButton.setEnabled(true);
    }

    protected void onSDKInitFailure(String error){
        log("SDK Init Error: " + error);
    }

    private String getAppVersion(){
        String versionCode = "";
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionCode = "" + pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return "v" +getSDKVersion()+ "#" + versionCode;
    }

    protected void log(String message){
        Log.d(BaseMainActivity.class.getSimpleName(),message);
        String text = feedbackTV.getText().toString() + message + "\n";
        feedbackTV.setText(text);
        feedbackContainer.post(() -> {
            feedbackContainer.fullScroll(View.FOCUS_DOWN);
        });
    }


    // Interstitial SDK callbacks handler

    protected void onInterstitialLoaded(){
        log("Interstitial Ready");
        showInterstitialButton.setEnabled(true);
    }

    protected void onInterstitialLoadFailed(String error){
        log("Failed to load interstitial: " + error);
        loadInterstitialButton.setEnabled(true);
        showInterstitialButton.setEnabled(false);
    }

    protected void onInterstitialShowFailed(String error){
        log("Failed to show interstitial: " + error);
        loadInterstitialButton.setEnabled(true);
        showInterstitialButton.setEnabled(false);
    }

    protected void onInterstitialOpened(){
        log("Interstitial Opened");
    }

    protected void onInterstitialClosed(){
        log("Interstitial Closed");
        loadInterstitialButton.setEnabled(true);
        showInterstitialButton.setEnabled(false);
    }

    // Rewarded SDK callbacks handler

    protected void onRewardedLoaded(){
        log("Rewarded Ready");
        showRewardedButton.setEnabled(true);
    }

    protected void onRewardedLoadFailed(String error){
        log("Failed to load rewarded: " + error);
        loadRewardedButton.setEnabled(true);
        showRewardedButton.setEnabled(false);
    }

    protected void onRewardedShowFailed(String error){
        log("Failed to show rewarded: " + error);
        loadRewardedButton.setEnabled(true);
        showRewardedButton.setEnabled(false);
    }

    protected void onRewardedOpened(){
        log("Rewarded Opened");
    }

    protected void onRewardedClosed(){
        log("Rewarded Closed");
        loadRewardedButton.setEnabled(true);
        showRewardedButton.setEnabled(false);
    }

    protected void onRewardAchieved(){
        Toast.makeText(this, "Reward Received", Toast.LENGTH_LONG).show();
        log("Reward Received");
    }
    protected abstract String getAppLabel();
    protected abstract String getSDKVersion();
    protected abstract void initSDK();

    protected abstract void loadInterstitial();
    protected abstract void showInterstitial();
    protected abstract void loadRewarded();
    protected abstract void showRewarded();
}
