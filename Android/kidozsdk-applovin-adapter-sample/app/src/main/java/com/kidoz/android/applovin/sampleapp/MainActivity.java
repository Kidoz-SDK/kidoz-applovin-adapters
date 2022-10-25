package com.kidoz.android.applovin.sampleapp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.applovin.impl.mediation.c;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.kidoz.mediation.applovin.KidozSDKInfo;
import com.kidoz.sdk.common.testapp.BaseMainActivity;

public class MainActivity extends BaseMainActivity {

    private MaxInterstitialAd interstitialAd;
    private MaxRewardedAd rewardedAd;
    private MaxAdView bannerAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getAppLabel() {
        return "AppLovin Adapter Test App";
    }

    @Override
    protected String getSDKVersion() {
        return KidozSDKInfo.getKidozSDKVersion() + ":" + KidozSDKInfo.getMediationAdapterVersion();
    }

    @Override
    protected void initSDK() {
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.getInstance(this).initializeSdk(config -> {
            initInterstitialAd();
            initRewardedAd();
//            initBannerAd();
//            AppLovinSdk.getInstance(this).showMediationDebugger();
            onSDKInitSuccess();
        });


    }

    private void initInterstitialAd() {

        interstitialAd = new MaxInterstitialAd("21e57811dd22d0df", this);
        interstitialAd.setListener(new MaxAdListener() {
            @Override
            public void onAdLoaded(MaxAd ad) {
                onInterstitialLoaded();
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {
                onInterstitialOpened();
            }

            @Override
            public void onAdHidden(MaxAd ad) {
                onInterstitialClosed();
            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                onInterstitialLoadFailed(getErrorMessage(error));
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                onInterstitialShowFailed(getErrorMessage(error));
            }
        });
    }

    private void initRewardedAd() {
        rewardedAd = MaxRewardedAd.getInstance("4a7010c8c28dc832", this);
        rewardedAd.setListener(new MaxRewardedAdListener() {
            @Override
            public void onRewardedVideoStarted(MaxAd ad) {

            }

            @Override
            public void onRewardedVideoCompleted(MaxAd ad) {

            }

            @Override
            public void onUserRewarded(MaxAd ad, MaxReward reward) {
                onRewardAchieved();
            }

            @Override
            public void onAdLoaded(MaxAd ad) {
                onRewardedLoaded();
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {
                onRewardedOpened();
            }

            @Override
            public void onAdHidden(MaxAd ad) {
                onRewardedClosed();
            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                onRewardedLoadFailed(getErrorMessage(error));
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                onRewardedShowFailed(getErrorMessage(error));
            }

        });
    }

    private String getErrorMessage(MaxError error) {
        return error.getMessage() + "::" + error.getCode();
    }

    @Override
    protected void loadInterstitial() {
        interstitialAd.loadAd();
    }

    @Override
    protected void showInterstitial() {
        interstitialAd.showAd();
    }

    @Override
    protected void loadRewarded() {
        rewardedAd.loadAd();
    }

    @Override
    protected void showRewarded() {
        rewardedAd.showAd();
    }

}