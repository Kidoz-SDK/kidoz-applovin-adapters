using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.UIElements;

public class MainCode : MonoBehaviour,IScreenLogger
{

	private Text logScreen;
	public GameObject LogScreenContainer;

	private List<string> Eventlog = new List<string>();


	private const string MaxSdkKey = "2wIV16lt2F7UTtRrnQRN4gnBEc_n7GNI-bTedN3SsQ9TbcrzUGohh9arBm8p6KDLrhATP-GCnqqccwe-9Mu6ea";

#if UNITY_ANDROID
	private const string InterstitialAdUnitId = "4bc7e2ec5cb830a6";
	private const string RewardedAdUnitId = "8f22d6151d47cc2f";
#elif UNITY_IOS
	private const string InterstitialAdUnitId = "ed39810afdabdca3"; 
	private const string RewardedAdUnitId = "a36ea0cb9c9fb265";
#else
	private const string InterstitialAdUnitId = "";
	private const string RewardedAdUnitId = "";
	private const string BannerAdUnitId = "";
#endif


	// Start is called before the first frame update
	void Start()
    {
		logScreen = LogScreenContainer.GetComponent<Text>();

		MaxSdkCallbacks.OnSdkInitializedEvent += sdkConfiguration =>
		{
			// AppLovin SDK is initialized, configure and start loading ads.
			AddEvent("MAX SDK Initialized");

			InitializeInterstitialAds();
            InitializeRewardedAds();
		};

		MaxSdk.SetVerboseLogging(true);
		MaxSdk.SetSdkKey(MaxSdkKey);
		MaxSdk.InitializeSdk();
	}


	#region Interstitial Ad Methods

	private void InitializeInterstitialAds()
	{
		// Attach callbacks
		MaxSdkCallbacks.Interstitial.OnAdLoadedEvent += OnInterstitialLoadedEvent;
		MaxSdkCallbacks.Interstitial.OnAdLoadFailedEvent += OnInterstitialFailedEvent;
		MaxSdkCallbacks.Interstitial.OnAdDisplayFailedEvent += InterstitialFailedToDisplayEvent;
		MaxSdkCallbacks.Interstitial.OnAdHiddenEvent += OnInterstitialDismissedEvent;
		MaxSdkCallbacks.Interstitial.OnAdRevenuePaidEvent += OnInterstitialRevenuePaidEvent;		
	}

	public void loadInterstitial()
	{
		AddEvent("Load Interstitial");
		MaxSdk.LoadInterstitial(InterstitialAdUnitId);
	}

	public void showInterstitial()
	{
		AddEvent("Show Interstitial");
		if (MaxSdk.IsInterstitialReady(InterstitialAdUnitId))
		{
			MaxSdk.ShowInterstitial(InterstitialAdUnitId);
		}
		else
		{
			AddEvent("Interstitial ad not ready");
		}
	}

	private void OnInterstitialLoadedEvent(string adUnitId, MaxSdkBase.AdInfo adInfo)
	{
		// Interstitial ad is ready to be shown. MaxSdk.IsInterstitialReady(interstitialAdUnitId) will now return 'true'
		AddEvent("Interstitial loaded");
	}

	private void OnInterstitialFailedEvent(string adUnitId, MaxSdkBase.ErrorInfo errorInfo)
	{
		AddEvent("Interstitial failed to load with error code: " + errorInfo.Code);
	}

	private void InterstitialFailedToDisplayEvent(string adUnitId, MaxSdkBase.ErrorInfo errorInfo, MaxSdkBase.AdInfo adInfo)
	{
		// Interstitial ad failed to display. We recommend loading the next ad
		AddEvent("Interstitial failed to display with error code: " + errorInfo.Code);
	}

	private void OnInterstitialDismissedEvent(string adUnitId, MaxSdkBase.AdInfo adInfo)
	{
		// Interstitial ad is hidden. Pre-load the next ad
		AddEvent("Interstitial closed");
	}

	private void OnInterstitialRevenuePaidEvent(string adUnitId, MaxSdkBase.AdInfo adInfo)
	{
		// Interstitial ad revenue paid. Use this callback to track user revenue.
		AddEvent("Interstitial revenue paid by " + adInfo.NetworkName);	
	}

	#endregion

	#region Rewarded Ad Methods

	private void InitializeRewardedAds()
	{
		// Attach callbacks
		MaxSdkCallbacks.Rewarded.OnAdLoadedEvent += OnRewardedAdLoadedEvent;
		MaxSdkCallbacks.Rewarded.OnAdLoadFailedEvent += OnRewardedAdFailedEvent;
		MaxSdkCallbacks.Rewarded.OnAdDisplayFailedEvent += OnRewardedAdFailedToDisplayEvent;
		MaxSdkCallbacks.Rewarded.OnAdDisplayedEvent += OnRewardedAdDisplayedEvent;
		MaxSdkCallbacks.Rewarded.OnAdClickedEvent += OnRewardedAdClickedEvent;
		MaxSdkCallbacks.Rewarded.OnAdHiddenEvent += OnRewardedAdDismissedEvent;
		MaxSdkCallbacks.Rewarded.OnAdReceivedRewardEvent += OnRewardedAdReceivedRewardEvent;
		MaxSdkCallbacks.Rewarded.OnAdRevenuePaidEvent += OnRewardedAdRevenuePaidEvent;

	}


	public void loadRewarded()
	{
		AddEvent("Load Rewarded");
		MaxSdk.LoadRewardedAd(RewardedAdUnitId);
	}

	public void showRewarded()
	{
		AddEvent("Show Rewarded");
		if (MaxSdk.IsRewardedAdReady(RewardedAdUnitId))
		{
			MaxSdk.ShowRewardedAd(RewardedAdUnitId);
		}
		else
		{
			AddEvent("Rewarded ad not ready");
		}
	}

	private void OnRewardedAdLoadedEvent(string adUnitId, MaxSdkBase.AdInfo adInfo)
	{
		// Rewarded ad is ready to be shown. MaxSdk.IsRewardedAdReady(rewardedAdUnitId) will now return 'true'
		AddEvent("Rewarded ad loaded");
	}

	private void OnRewardedAdFailedEvent(string adUnitId, MaxSdkBase.ErrorInfo errorInfo)
	{
		AddEvent("Rewarded ad failed to load with error code: " + errorInfo.Code);
	}

	private void OnRewardedAdFailedToDisplayEvent(string adUnitId, MaxSdkBase.ErrorInfo errorInfo, MaxSdkBase.AdInfo adInfo)
	{
		// Rewarded ad failed to display. We recommend loading the next ad
		AddEvent("Rewarded ad failed to display with error code: " + errorInfo.Code);
	}

	private void OnRewardedAdDisplayedEvent(string adUnitId, MaxSdkBase.AdInfo adInfo)
	{
		AddEvent("Rewarded ad displayed");
	}

	private void OnRewardedAdClickedEvent(string adUnitId, MaxSdkBase.AdInfo adInfo)
	{
		AddEvent("Rewarded ad clicked");
	}

	private void OnRewardedAdDismissedEvent(string adUnitId, MaxSdkBase.AdInfo adInfo)
	{
		// Rewarded ad is hidden. Pre-load the next ad
		AddEvent("Rewarded ad closed");
	}

	private void OnRewardedAdReceivedRewardEvent(string adUnitId, MaxSdk.Reward reward, MaxSdkBase.AdInfo adInfo)
	{
		// Rewarded ad was displayed and user should receive the reward
		AddEvent("Rewarded ad received reward");
	}

	private void OnRewardedAdRevenuePaidEvent(string adUnitId, MaxSdkBase.AdInfo adInfo)
	{
		// Rewarded ad revenue paid. Use this callback to track user revenue.
		AddEvent("Rewarded ad revenue paid by " + adInfo.NetworkName);
	}

	#endregion





	public void AddEvent(string eventString)
	{
		Eventlog.Add(eventString);

		string text = "";

		foreach (string logEvent in Eventlog)
		{
			text += logEvent;
			text += "\n";
		}
        logScreen.text = text;
	}
}
