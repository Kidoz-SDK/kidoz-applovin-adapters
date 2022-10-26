//
//  ViewController.swift
//  KidozAppLovinTestApp
//
//  Created by Yarden Rosenberg on 31/08/2022.
//

import UIKit
import MessageUI
import Foundation

import AppLovinSDK



class ViewController: UIViewController,MAAdViewAdDelegate,MARewardedAdDelegate,MFMailComposeViewControllerDelegate {
    
    
    public static var WATERFALL_URL_KEY = "WATERALL_KEY"
    public static var OFFER_IDS_PLACEHOLDER = "{offer_id_placeholder}"
    public static var PROD_TEST_WATERFALL_URL = "https://prebid-adapter.kidoz.net/api/test-waterfall?offer_id=" + ViewController.OFFER_IDS_PLACEHOLDER + "&"
    

#if MULTI_ENV
    var testWaterfallUrl = ""
#else
    var testWaterfallUrl = PROD_TEST_WATERFALL_URL
#endif
    
    @IBOutlet weak var logText: UITextView!
    @IBOutlet weak var appLabel: UILabel!
    
    private static var INTERSTITIAL_AD_ID = "94c3a42faf40cd5b"
    private static var REWARDED_AD_ID = "a4de0cc81e54a4c8"
    
    private let interstitialAd = MAInterstitialAd(adUnitIdentifier:INTERSTITIAL_AD_ID )
    private let rewardedAd = MARewardedAd.shared(withAdUnitIdentifier:REWARDED_AD_ID )
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setBorder()
        appLabel.text = "AppLovin Adapter Sample App"

        logOut(message: "SDK Initialized")
        interstitialAd.delegate = self
        rewardedAd.delegate = self
    }
    

    func setBorder(){
        self.logText.layer.borderColor = UIColor.orange.cgColor
        self.logText.layer.borderWidth = 2.3
        self.logText.layer.cornerRadius = 15
    }
    
    func logOut(message:String ){
        logText.text = logText.text + message + "\n"
        let location = logText.text.count - 1
        let bottom = NSMakeRange(location, 1)
        logText.scrollRangeToVisible(bottom)
    }
    
//    func getSDKVersion() -> String{
//        let sdkVersion: String = KidozAppLovinSDKInfo.getSdkVersion(),build = Bundle.main.object(forInfoDictionaryKey: kCFBundleVersionKey as String) as! String
//        return "\(sdkVersion)(\(build))"
//    }


    
    
    // MARK: - Interstitial
    
    @IBAction func loadInterstitial(_ sender: Any) {
        logOut(message: "Load Interstitial")
        interstitialAd.load()
        
    }
    
    @IBAction func showInterstitial(_ sender: Any) {
        logOut(message: "Show Interstitial")
        if(interstitialAd.isReady){
            interstitialAd.show()
        }
    }
    
    
    
    // MARK: - Rewarded

    @IBAction func loadRewarded(_ sender: Any) {
        logOut(message: "Load Rewarded")
        rewardedAd.load()
        
    }
    
    @IBAction func showRewarded(_ sender: Any) {
        logOut(message: "Show Rewarded")
        if(rewardedAd.isReady){
            rewardedAd.show()
        }
    }
    
    
    
    
    
    func didExpand(_ ad: MAAd) {
        
    }
    
    func didCollapse(_ ad: MAAd) {
        
    }
    
    func didLoad(_ ad: MAAd) {
        logOut(message: getAdType(ad: ad) + " loaded")
    }
    
    func didFailToLoadAd(forAdUnitIdentifier adUnitIdentifier: String, withError error: MAError) {
        logOut(message: getAdTypeById(adId:adUnitIdentifier) + " failed to load::" + error.message)
    }
    
    func didDisplay(_ ad: MAAd) {
        logOut(message: getAdType(ad: ad) + " opened")
    }
    
    func didHide(_ ad: MAAd) {
        logOut(message: getAdType(ad: ad) + " closed")
    }
    
    func didClick(_ ad: MAAd) {
        
    }
    
    func didFail(toDisplay ad: MAAd, withError error: MAError) {
        logOut(message: getAdType(ad: ad) + " failed to display::" + error.message)
    }
    
    func didStartRewardedVideo(for ad: MAAd){
        
    }
    
    func didCompleteRewardedVideo(for ad: MAAd){
        
    }
    
    func didRewardUser(for ad: MAAd, with reward: MAReward) {
        logOut(message: "Rewarded received")
    }
    
    private func getAdType(ad: MAAd) -> String{
        switch ad.format.label{
        case "INTER":
            return "Interstitial"
        case "REWARDED":
            return "Rewarded"
        default:
            return "Banner"
        }
    }
    
    private func getAdTypeById(adId: String) -> String{
        
        switch adId{
        case ViewController.INTERSTITIAL_AD_ID:
            return "Interstitial"
        case ViewController.REWARDED_AD_ID:
            return "Rewarded"
        default:
            return "Banner"
        }
    }
}

    


