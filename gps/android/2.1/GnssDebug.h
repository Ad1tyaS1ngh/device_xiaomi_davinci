/*
 * Copyright (c) 2017-2020, The Linux Foundation. All rights reserved.
 * Not a Contribution
 */
/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#ifndef ANDROID_HARDWARE_GNSS_V2_0_GNSSDEBUG_H
#define ANDROID_HARDWARE_GNSS_V2_0_GNSSDEBUG_H


#include <android/hardware/gnss/2.0/IGnssDebug.h>
#include <hidl/Status.h>

namespace android {
namespace hardware {
namespace gnss {
namespace V2_1 {
namespace implementation {

using ::android::hardware::gnss::V2_0::IGnssDebug;
using ::android::hardware::Return;
using ::android::hardware::Void;
using ::android::hardware::hidl_vec;
using ::android::hardware::hidl_string;
using ::android::sp;

/* Interface for GNSS Debug support. */
struct Gnss;
struct GnssDebug : public IGnssDebug {
    GnssDebug(Gnss* gnss);
    ~GnssDebug() {};

    //  Methods from ::android::hardware::gnss::V1_0::IGnssDebug follow
    Return<void> getDebugData(getDebugData_cb _hidl_cb) override;
    //  Methods from ::android::hardware::gnss::V2_0::IGnssDebug follow.
    Return<void> getDebugData_2_0(getDebugData_2_0_cb _hidl_cb) override;

private:
    Gnss* mGnss = nullptr;
};

}  // namespace implementation
}  // namespace V2_1
}  // namespace gnss
}  // namespace hardware
}  // namespace android

#endif  // ANDROID_HARDWARE_GNSS_V2_0_GNSSDEBUG_H
