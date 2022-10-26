# Native iOS Adapter

**Integration Steps:**

* Drag and drop the Kidoz' AppLovinAdapter library file taken from [HERE](https://github.com/Kidoz-SDK/applovin-adapter-sample-apps/tree/main/iOS/KidozAppLovinSampleApp/KidozAppLovinSampleApp/Kidoz) into you project.
* Select your target in the project navigator, select the “Build Settings” tab, search for “Other Linker Flags”, click on the “+” and type -ObjC  `Other Linker Flags -ObjC` .

### SKAdNetwork Support

In order to support CPI attribution on iOS, please make sure to include the Kidoz ad network ID in your app property list file (Info.plist):

```java
v79kvwwj4g.skadnetwork	
```
