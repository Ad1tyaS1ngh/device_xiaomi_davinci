/*
 * Copyright (c) 2017-2020, The Linux Foundation. All rights reserved.
 * Not a Contribution
 */
/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#ifndef ANDROID_HARDWARE_GNSS_V2_0_GNSSBATCHING_H
#define ANDROID_HARDWARE_GNSS_V2_0_GNSSBATCHING_H

#include <android/hardware/gnss/2.0/IGnssBatching.h>
#include <hidl/Status.h>


namespace android {
namespace hardware {
namespace gnss {
namespace V2_1 {
namespace implementation {

using ::android::hardware::gnss::V2_0::IGnssBatching;
using ::android::hardware::gnss::V2_0::IGnssBatchingCallback;
using ::android::hidl::base::V1_0::IBase;
using ::android::hardware::hidl_array;
using ::android::hardware::hidl_memory;
using ::android::hardware::hidl_string;
using ::android::hardware::hidl_vec;
using ::android::hardware::Return;
using ::android::hardware::Void;
using ::android::sp;

class BatchingAPIClient;
struct GnssBatching : public IGnssBatching {
    GnssBatching();
    ~GnssBatching();

    // Methods from ::android::hardware::gnss::V1_0::IGnssBatching follow.
    Return<bool> init(const sp<V1_0::IGnssBatchingCallback>& callback) override;
    Return<uint16_t> getBatchSize() override;
    Return<bool> start(const IGnssBatching::Options& options ) override;
    Return<void> flush() override;
    Return<bool> stop() override;
    Return<void> cleanup() override;

    // Methods from ::android::hardware::gnss::V2_0::IGnssBatching follow.
    Return<bool> init_2_0(const sp<V2_0::IGnssBatchingCallback>& callback) override;

 private:
    struct GnssBatchingDeathRecipient : hidl_death_recipient {
        GnssBatchingDeathRecipient(sp<GnssBatching> gnssBatching) :
            mGnssBatching(gnssBatching) {
        }
        ~GnssBatchingDeathRecipient() = default;
        virtual void serviceDied(uint64_t cookie, const wp<IBase>& who) override;
        sp<GnssBatching> mGnssBatching;
    };

 private:
    sp<GnssBatchingDeathRecipient> mGnssBatchingDeathRecipient = nullptr;
    sp<V1_0::IGnssBatchingCallback> mGnssBatchingCbIface = nullptr;
    BatchingAPIClient* mApi = nullptr;
    sp<V2_0::IGnssBatchingCallback> mGnssBatchingCbIface_2_0 = nullptr;
};

}  // namespace implementation
}  // namespace V2_1
}  // namespace gnss
}  // namespace hardware
}  // namespace android

#endif  // ANDROID_HARDWARE_GNSS_V2_0_GNSSBATCHING_H
