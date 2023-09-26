/*
 * Copyright (C) 2020 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.popupcamera;

public class Constants {
    public static final int FREE_FALL_SENSOR_ID = 33171042;

    public static final int CAMERA_EVENT_DELAY_TIME = 100; // ms
    public static final int MSG_CAMERA_CLOSED = 1001;
    public static final int MSG_CAMERA_OPEN = 1002;

    public static final int MOTOR_STATUS_POPUP_OK = 11;
    public static final int MOTOR_STATUS_POPUP_JAMMED = 12;
    public static final int MOTOR_STATUS_TAKEBACK_OK = 13;
    public static final int MOTOR_STATUS_TAKEBACK_JAMMED = 14;
    public static final int MOTOR_STATUS_PRESSED = 15;
    public static final int MOTOR_STATUS_CALIB_OK = 17;
    public static final int MOTOR_STATUS_CALIB_ERROR = 18;
    public static final int MOTOR_STATUS_REQUEST_CALIB = 19;

    public static final String CLOSE_CAMERA_STATE = "0";
    public static final String OPEN_CAMERA_STATE = "1";

    public static final String FRONT_CAMERA_ID = "1";
    public static final String LEFT_LED_BREATH_PATH = "/sys/class/leds/left/breath";
    public static final String LEFT_LED_BRIGHTNESS_PATH = "/sys/class/leds/left/brightness";
    public static final String LEFT_LED_PATH = "/sys/class/leds/left/";
    public static final String RIGHT_LED_PATH = "/sys/class/leds/right/";
    public static final String POPUP_SOUND_PATH = "/system_ext/media/audio/ui/";
}
