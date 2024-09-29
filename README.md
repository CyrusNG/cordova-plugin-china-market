cordova-plugin-china-market
=====================

Cordova (PhoneGap) 3.0+ plugin to open an application on native Marketplace app aka Mi Store, Vivo Store, Oppo Store, Honor Store, Huawei Store, Meizu Store, Samsung Store, Play Store for Android or App Store for iOS.
Currently this plugin is compatible only with Android and iOS. Feel free to add support for the platform you need!

# Installation

This plugin follows the Cordova 3.0 plugin spec, so it can be installed through the Cordova CLI in your existing Cordova project:

    cordova plugin add https://github.com/CyrusNG/cordova-plugin-china-market

# How to use it

When you want to open the device's store do this:

* For Android use the app's package, default is the app package name:

    `Market.open('your.app.package')`

* For iOS, first you need to create an easy-to-read link to your app using App Store Short Links, then use the app name:

    `Market.open('yourappname')`

This will open the link `itms-apps://itunes.apple.com/app/yourappname`. Alternatively you can use your app's id that should be similar to `id284815942`

You can also add a success and failure callback like this:

    try {
      await Market.open(appId);
    } catch(e) {
      //can NOT find any matched market
    }


# For Android 11 + 

Package visibility was introduced in Android 11 (API level 30) and changes the way apps can query or interact with other apps the user has installed on a device. Using the <queries> element, apps can define the set of other packages that they can access.

So Cordova will auto add following <queries> into AndroidManifest.xml:

```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android" xmlns:tools="http://schemas.android.com/tools">

    <!-- Package Visibility @ Android 11+ (API level 30+)  -->
    <queries>
      <package android:name="com.xiaomi.market" />
      <package android:name="com.bbk.appstore" />
      <package android:name="com.oppo.market" />
      <package android:name="com.hihonor.appmarket" />
      <package android:name="com.huawei.appmarket" />
      <package android:name="com.meizu.mstore" />
      <package android:name="com.sec.android.app.samsungapps" />
      <package android:name="com.android.vending" />
    </queries>

    ...
    ...
    ...
    
</manifest>
```

# Release Notes


Version 1.3.1

* Removed API to search apps in Play Store

* Android support Mi Store, Vivo Store, Oppo Store, Honor Store, Huawei Store, Meizu Store, Samsung Store, Play Store

* iOS support App Store

Version 1.2

* Added API to search apps in Play Store (supported just in Android)

Version 1.1

* Fixed issue due to use old iOS url.

Version 1.0

* Initial release: support for open Play Store or iTunes with an Application ID


