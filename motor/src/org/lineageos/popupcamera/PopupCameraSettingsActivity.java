/*
 * Copyright (C) 2020 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.popupcamera;

import android.os.Bundle;

import com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity;
import com.android.settingslib.widget.R;

public class PopupCameraSettingsActivity extends CollapsingToolbarBaseActivity {
    private static final String TAG_POPUPCAMERA = "popupcamera";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new PopupCameraSettingsFragment(), TAG_POPUPCAMERA)
                .commit();
    }
}
