//
//  DeviceUtils.h
//  TestApp
//
//  Created by Yarden Rosenberg on 02/05/2022.
//  Copyright © 2022 Kidoz. All rights reserved.
//

#ifndef DeviceUtils_h
#define DeviceUtils_h


#import <Foundation/Foundation.h>
@interface DeviceUtils : NSObject

+ (NSString *)getBundleIdentifier;
+ (NSString *)getDeviceLanguage;
+ (NSString *)getUniqeDeviceID;
+ (NSString *)getDeviceType;
+ (NSString *)getOSVersion;
+ (NSString *)getWebViewVersion;
+ (NSString *)getScreenWidth;
+ (NSString *)getScreenHeight;
+ (NSString *)getDeviceScreenSizeInInches;
+ (NSString *)getDeviceModel;
+ (NSString *)getWifiMode;
+ (NSString *)getCarrierName;
+ (NSString *)getNetworkType;
+ (NSString *)getTimestamp;
+ (NSString *)getSDKVersion;
+ (NSString *)getActualSDKVersion;
+ (NSString *)getUTCTimestamp;
+ (BOOL)allowsArbitraryLoads;
+ (NSString *)getWebViewUserAgent;

+ (NSString *)getActualScreenWidth;
+ (NSString *)getActualScreenHeight;
+ (NSString *)getExtensionType;
+ (NSString *)getScaleFactor;
+ (NSString *)getDPIFactor;
+ (NSString *)getAppVersion;
+ (NSString *)getOMPartnerName;
+ (BOOL)changeUserAgent;

@end


#endif /* DeviceUtils_h */
