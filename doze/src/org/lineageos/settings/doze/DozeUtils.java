/*
 * Copyright (C) 2015 The CyanogenMod Project
 *               2017-2019 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.doze;

import static android.provider.Settings.Secure.DOZE_ALWAYS_ON;
import static android.provider.Settings.Secure.DOZE_ENABLED;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.display.AmbientDisplayConfiguration;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.preference.PreferenceManager;

public final class DozeUtils {
    private static final String TAG = "DozeUtils";
    private static final boolean DEBUG = false;

    private static final String DOZE_INTENT = "com.android.systemui.doze.pulse";

    protected static final String DOZE_ENABLE = "doze_enable";
    protected static final String ALWAYS_ON_DISPLAY = "always_on_display";
    protected static final String WAKE_ON_GESTURE_KEY = "wake_on_gesture";
    protected static final String CATEG_PICKUP_SENSOR = "pickup_sensor";
    protected static final String CATEG_PROX_SENSOR = "proximity_sensor";

    protected static final String GESTURE_PICK_UP_KEY = "gesture_pick_up";
    protected static final String GESTURE_HAND_WAVE_KEY = "gesture_hand_wave";
    protected static final String GESTURE_POCKET_KEY = "gesture_pocket";

    public static void startService(Context context) {
        if (DEBUG)
            Log.d(TAG, "Starting service");
        context.startServiceAsUser(new Intent(context, DozeService.class), UserHandle.CURRENT);
    }

    protected static void stopService(Context context) {
        if (DEBUG)
            Log.d(TAG, "Stopping service");
        context.stopServiceAsUser(new Intent(context, DozeService.class), UserHandle.CURRENT);
    }

    public static void checkDozeService(Context context) {
        if (isDozeEnabled(context) && !isAlwaysOnEnabled(context) && sensorsEnabled(context)) {
            startService(context);
        } else {
            stopService(context);
        }
    }

    protected static boolean getProxCheckBeforePulse(Context context) {
        try {
            Context con = context.createPackageContext("com.android.systemui", 0);
            int id = con.getResources().getIdentifier(
                    "doze_proximity_check_before_pulse", "bool", "com.android.systemui");
            return con.getResources().getBoolean(id);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    protected static boolean enableDoze(Context context, boolean enable) {
        return Settings.Secure.putInt(context.getContentResolver(), DOZE_ENABLED, enable ? 1 : 0);
    }

    public static boolean isDozeEnabled(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), DOZE_ENABLED, 1) != 0;
    }

    protected static void wakeOrLaunchDozePulse(Context context) {
        if (isWakeOnGestureEnabled(context)) {
            if (DEBUG) {
                Log.d(TAG, "Wake up display");
            }
            PowerManager powerManager = context.getSystemService(PowerManager.class);
            powerManager.wakeUp(SystemClock.uptimeMillis(), PowerManager.WAKE_REASON_GESTURE, TAG);
        } else {
            if (DEBUG) {
                Log.d(TAG, "Launch doze pulse");
            }
            context.sendBroadcastAsUser(
                    new Intent(DOZE_INTENT), new UserHandle(UserHandle.USER_CURRENT));
        }
    }

    protected static boolean enableAlwaysOn(Context context, boolean enable) {
        return Settings.Secure.putIntForUser(context.getContentResolver(), DOZE_ALWAYS_ON,
                enable ? 1 : 0, UserHandle.USER_CURRENT);
    }

    protected static boolean isAlwaysOnEnabled(Context context) {
        final boolean enabledByDefault = context.getResources().getBoolean(
                com.android.internal.R.bool.config_dozeAlwaysOnEnabled);

        return Settings.Secure.getIntForUser(context.getContentResolver(), DOZE_ALWAYS_ON,
                       alwaysOnDisplayAvailable(context) && enabledByDefault ? 1 : 0,
                       UserHandle.USER_CURRENT)
                != 0;
    }

    protected static boolean alwaysOnDisplayAvailable(Context context) {
        return new AmbientDisplayConfiguration(context).alwaysOnAvailable();
    }

    protected static boolean isGestureEnabled(Context context, String gesture) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(gesture, false);
    }

    protected static boolean isWakeOnGestureEnabled(Context context) {
        return isGestureEnabled(context, WAKE_ON_GESTURE_KEY);
    }

    protected static boolean isPickUpEnabled(Context context) {
        return isGestureEnabled(context, GESTURE_PICK_UP_KEY);
    }

    protected static boolean isHandwaveGestureEnabled(Context context) {
        return isGestureEnabled(context, GESTURE_HAND_WAVE_KEY);
    }

    protected static boolean isPocketGestureEnabled(Context context) {
        return isGestureEnabled(context, GESTURE_POCKET_KEY);
    }

    public static boolean sensorsEnabled(Context context) {
        return isPickUpEnabled(context) || isHandwaveGestureEnabled(context)
                || isPocketGestureEnabled(context);
    }

    protected static Sensor getSensor(SensorManager sm, String type) {
        for (Sensor sensor : sm.getSensorList(Sensor.TYPE_ALL)) {
            if (type.equals(sensor.getStringType())) {
                return sensor;
            }
        }
        return null;
    }
}
