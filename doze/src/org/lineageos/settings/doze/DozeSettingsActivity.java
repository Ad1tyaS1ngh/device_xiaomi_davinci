/*
 * Copyright (C) 2015-2016 The CyanogenMod Project
 *               2017 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.doze;

import android.os.Bundle;

import com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity;
import com.android.settingslib.widget.R;

public class DozeSettingsActivity extends CollapsingToolbarBaseActivity {
    private static final String TAG_DOZE = "doze";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new DozeSettingsFragment(), TAG_DOZE)
                .commit();
    }
}
