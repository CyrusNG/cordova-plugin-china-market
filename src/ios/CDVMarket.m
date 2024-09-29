//
//  CDVMarket.h
//
// Created by Cyrus NG on 2024-09-29.
// License Apache 2.0

#include "CDVMarket.h"

@implementation CDVMarket

- (void)pluginInitialize
{
}

- (void)open:(CDVInvokedUrlCommand *)command
{
    [self.commandDelegate runInBackground:^{
        // get arguments
        NSArray *args = command.arguments;
        // get app id
        NSString *appId = [args objectAtIndex:0];
        // check app id
        if ([appId isEqual:[NSNull null]] || [appId isEqual:@""]) {
            // return error to cordova
            [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Invalid appId"] callbackId:command.callbackId];
        } else {
            // generate url
            NSString *url = [NSString stringWithFormat:@"itms-apps://itunes.apple.com/app/%@", appId];
            // open app store
            float systemVersionNum = [[[UIDevice currentDevice] systemVersion] floatValue];
            dispatch_async(dispatch_get_main_queue(), ^{
                if(systemVersionNum >= 10.0){
                    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:url] options:@{} completionHandler:nil];
                } else {
                    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:url]];
                }
            });
            // return ok to cordova
            [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_OK] callbackId:command.callbackId];
        }
    }];

}

@end
