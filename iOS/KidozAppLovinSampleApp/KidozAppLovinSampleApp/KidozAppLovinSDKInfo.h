//
//  KidozAppLovinSDKInfo.h
//  KidozSDK_AppLovinAdapter
//
//  Created by Yarden Rosenberg on 31/08/2022.
//  Copyright © 2022 Kidoz. All rights reserved.
//

#ifndef KidozAppLovinSDKInfo_h
#define KidozAppLovinSDKInfo_h

#import <Foundation/Foundation.h>
@interface  KidozAppLovinSDKInfo: NSObject

+ (NSString*)getSdkVersion;
+ (NSString*)networkSDKVersion;
+ (NSString*)adapterSDKVersion;

@end

#endif /* KidozAppLovinSDKInfo_h */
