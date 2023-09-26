/*
 * Copyright (c) 2017-2020, The Linux Foundation. All rights reserved.
 * Not a Contribution
 */
/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#define LOG_TAG "android.hardware.gnss@2.1-service-qti"

#include <android/hardware/gnss/2.1/IGnss.h>
#include <hidl/LegacySupport.h>
#include "loc_cfg.h"
#include "loc_misc_utils.h"

extern "C" {
#include "vndfwk-detect.h"
}

#ifdef ARCH_ARM_32
#define DEFAULT_HW_BINDER_MEM_SIZE 65536
#endif

using android::hardware::gnss::V2_1::IGnss;

using android::hardware::configureRpcThreadpool;
using android::hardware::registerPassthroughServiceImplementation;
using android::hardware::joinRpcThreadpool;

using android::status_t;
using android::OK;

typedef int vendorEnhancedServiceMain(int /* argc */, char* /* argv */ []);

int main() {

    ALOGI("%s", __FUNCTION__);

    int vendorInfo = getVendorEnhancedInfo();
    bool vendorEnhanced = ( 1 == vendorInfo || 3 == vendorInfo );
    setVendorEnhanced(vendorEnhanced);

#ifdef ARCH_ARM_32
    android::hardware::ProcessState::initWithMmapSize((size_t)(DEFAULT_HW_BINDER_MEM_SIZE));
#endif
    configureRpcThreadpool(1, true);
    status_t status;

    status = registerPassthroughServiceImplementation<IGnss>();
    if (status == OK) {
    #ifdef LOC_HIDL_VERSION
        #define VENDOR_ENHANCED_LIB "vendor.qti.gnss@" LOC_HIDL_VERSION "-service.so"

        void* libHandle = NULL;
        vendorEnhancedServiceMain* vendorEnhancedMainMethod = (vendorEnhancedServiceMain*)
                dlGetSymFromLib(libHandle, VENDOR_ENHANCED_LIB, "main");
        if (NULL != vendorEnhancedMainMethod) {
            (*vendorEnhancedMainMethod)(0, NULL);
        }
    #else
        ALOGI("LOC_HIDL_VERSION not defined.");
    #endif
        joinRpcThreadpool();
    } else {
        ALOGE("Error while registering IGnss 2.1 service: %d", status);
    }

    return 0;
}
