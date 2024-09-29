//
//  CDVMarket.h
//
// Created by Cyrus NG on 2024-09-29.
// License Apache 2.0

#import <Foundation/Foundation.h>
#import <Cordova/CDVPlugin.h>

@interface CDVMarket : CDVPlugin

- (void)open:(CDVInvokedUrlCommand *)command;

@end