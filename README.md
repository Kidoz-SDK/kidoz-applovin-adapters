# AppLovin Adapters + Sample Apps

The Kidoz AppLovin Adapter SDK is built and tested

The Adapter currently offers support for the following ad types:

+ AppLovin Interstitial Mediation 
+ AppLovin Rewarded Video Mediation 

Prerequisites
=================================

Before publishing your first app please finish the onboarding process for Kidoz's publishers [HERE](http://accounts.kidoz.net/publishers/register?utm_source=&utm_content=&utm_campaign=&utm_medium=)  
and follow the instructions in the AppLovin Custom SDK Network Integration Guide [HERE](https://dash.applovin.com/documentation/mediation/android/mediation-setup/custom-sdk)

In the custom network you should enter the following data:

Custom Network Name: Kidoz <BR>
iOS Adapter Class Name: KidozMediationAdapter<BR>
Android / Fire OS Adapter Class Name: com.applovin.mediation.adapters.KidozMediationAdapter<BR>
<BR>
<img width="800" alt="AppLovinCustomNetwork" src="https://user-images.githubusercontent.com/86282008/197710543-171fe5fc-9c5e-414b-96ab-07f3b28de708.png">
  
  
After the Kidoz Network was properly configured you will now be able to enable and configure the Kidoz Ad Data in your Ad Unit waterfall.
You should replace KIDOZ_PUBLISHER_ID and KIDOZ_SECURITY_TOKEN with the publisher id and token you recived from Kidoz when you've created your publisher account and set the json string inside the Custom Parameters field of the Kidoz ad configuration
```java
  {"publisher_id":"KIDOZ_PUBLISHER_ID","token":"KIDOZ_SECURITY_TOKEN"}
```
  
  
