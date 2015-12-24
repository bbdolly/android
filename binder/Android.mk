
ifdef OMAP_ENHANCEMENT_VTC
LOCAL_PATH:= $(call my-dir)

##################################################################################
include $(CLEAR_VARS)
LOCAL_SRC_FILES := main.cpp
LOCAL_C_INCLUDES += \
    $(DOMX_PATH)/omx_core/inc

ifdef ANDROID_API_JB_OR_LATER
LOCAL_C_INCLUDES += \
    $(TOP)/frameworks/native/include
else
LOCAL_C_INCLUDES += \
    $(TOP)/frameworks/base/include
endif

LOCAL_SHARED_LIBRARIES := \
    libcamera_client \
    libstagefright \
    libmedia \
    libbinder \
    libcutils \
    libutils \
    liblog \
    libgui

ifdef ANDROID_API_JB_OR_LATER
LOCAL_SHARED_LIBRARIES += \
    libstagefright_foundation
endif

LOCAL_CFLAGS +=-Wall -fno-short-enums -O0 -g -D___ANDROID___ $(ANDROID_API_CFLAGS)

LOCAL_PRELINK_MODULE := false
LOCAL_MODULE_TAGS:= optional
LOCAL_MODULE := xxAPP
include $(BUILD_EXECUTABLE)
##################################################################################
include $(CLEAR_VARS)
LOCAL_SRC_FILES := mainclient.cpp
LOCAL_C_INCLUDES += \
    $(DOMX_PATH)/omx_core/inc

ifdef ANDROID_API_JB_OR_LATER
LOCAL_C_INCLUDES += \
    $(TOP)/frameworks/native/include
else
LOCAL_C_INCLUDES += \
    $(TOP)/frameworks/base/include
endif

LOCAL_SHARED_LIBRARIES := \
    libcamera_client \
    libstagefright \
    libmedia \
    libbinder \
    libcutils \
    libutils \
    liblog \
    libgui

ifdef ANDROID_API_JB_OR_LATER
LOCAL_SHARED_LIBRARIES += \
    libstagefright_foundation
endif

LOCAL_CFLAGS +=-Wall -fno-short-enums -O0 -g -D___ANDROID___ $(ANDROID_API_CFLAGS)

LOCAL_PRELINK_MODULE := false
LOCAL_MODULE_TAGS:= optional
LOCAL_MODULE := xxAPPclient
include $(BUILD_EXECUTABLE)
################################################################################################
endif
