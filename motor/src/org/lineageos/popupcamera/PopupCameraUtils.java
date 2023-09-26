/*
 * Copyright (C) 2020-2021 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.popupcamera;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;

public class PopupCameraUtils {
    private static final String TAG = "PopupCameraUtils";
    private static final boolean DEBUG = false;

    public static void startService(Context context) {
        context.startServiceAsUser(
                new Intent(context, PopupCameraService.class), UserHandle.CURRENT);
    }
}
