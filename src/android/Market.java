package com.cordova.market.china;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.ActivityNotFoundException;

import android.net.Uri;
import android.util.Log;

import java.util.List;
import java.util.Objects;

/**
 * Interact with Markets.
 *
 * @author Cyrus NG
 * @license Apache 2.0
 */
public class Market extends CordovaPlugin
{

    /**
     * will use the first market found in following market list
     */
    private static final List<String> MARKET_LSIT = List.of(
        "com.xiaomi.market",                 //Mi
        "com.bbk.appstore",                  //Vivo
        "com.oppo.market",                   //Oppo
        "com.hihonor.appmarket",             //Honor
        "com.huawei.appmarket",              //Huawei
        "com.meizu.mstore",                  //Meizu
        "com.sec.android.app.samsungapps",   //Samsung
        "com.android.vending"                //Google
    );

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action
     *          Action to perform.
     * @param args
     *          Arguments to the action.
     * @return A PluginResult object with a status and message.
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        try {
            // open api
            if (action.equals("open")) {
                if (args.length() == 1) {
                    Context context = this.cordova.getActivity().getApplicationContext();
                    String appId = args.get(0).equals(null) || args.getString(0).isEmpty()? context.getPackageName() : args.getString(0);
                    String targetMarket = this.findMarket();
                    this.openMarket(targetMarket, appId);
                    callbackContext.success();
                    return true;
                }
            }
        } catch (JSONException e) {
            Log.d("CordovaLog","Plugin Market: cannot parse args.");
            e.printStackTrace();
        } catch (ActivityNotFoundException e) {
            Log.d("CordovaLog","Plugin Market: cannot open Google Play activity.");
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("CordovaLog", "Plugin Market: cannot find market app.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Open the appId details on market
     *
     * @param marketPackage
     *            market's package name
     *            E.g.: com.xiaomi.market
     * @param appId
     *            Application Id on market
     *            E.g.: com.google.earth
     */
    private void openMarket(String marketPackage, String appId) throws android.content.ActivityNotFoundException {
        Context context = this.cordova.getActivity().getApplicationContext();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appId));
        intent.setPackage(marketPackage);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * find market app
     *
     * @return A mark package name or null
     */
    private String findMarket() throws Exception {
        // get context
        Context context = this.cordova.getActivity().getApplicationContext();
        // loop to find one matched market app
        for (String packageName : MARKET_LSIT) {
            try {
                //try to get application info
                context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
                return packageName;
            } catch (NameNotFoundException e) {
                // do nothing then continue the loop
            }
        }
        throw new Exception("Not Found Market");
    }
}
