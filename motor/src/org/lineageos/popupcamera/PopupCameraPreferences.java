/*
 * Copyright (C) 2020 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.popupcamera;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PopupCameraPreferences {
    private static final String TAG = "PopupCameraUtils";
    private static final boolean DEBUG = false;
    private static final String LED_EFFECT_KEY = "popup_led_effect";
    private static final boolean LED_EFFECT_DEFAULT_VALUE = true;
    private static final String SOUND_EFFECT_KEY = "popup_sound_effect";
    private static final String SOUND_EFFECT_DEFAULT_VALUE = "-1";
    private SharedPreferences mSharedPrefs;

    public PopupCameraPreferences(Context context) {
        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getSoundEffect() {
        return mSharedPrefs.getString(SOUND_EFFECT_KEY, SOUND_EFFECT_DEFAULT_VALUE);
    }

    public boolean isLedAllowed() {
        return mSharedPrefs.getBoolean(LED_EFFECT_KEY, LED_EFFECT_DEFAULT_VALUE);
    }
}
