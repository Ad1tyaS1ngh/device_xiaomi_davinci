/*
 * Copyright (C) 2015 The CyanogenMod Project
 *               2017-2021 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.popupcamera;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.lineageos.popupcamera.PopupCameraUtils;

public class BootCompletedReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = false;
    private static final String TAG = "XiaomiPopupCamera";

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (DEBUG) {
            Log.d(TAG, "Received boot completed intent");
        }
        PopupCameraUtils.startService(context);
    }
}
