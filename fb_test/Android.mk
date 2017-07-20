LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := 
LOCAL_CFLAGS := -g -O0 
LOCAL_C_INCLUDES := 
LOCAL_SHARED_LIBRARIES := 
LOCAL_SRC_FILES := test.c

LOCAL_MODULE_TAGS := optional

LOCAL_MODULE           := fb-test

include $(BUILD_EXECUTABLE)


################################################################################

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := 
LOCAL_CFLAGS := -g -O0 
LOCAL_C_INCLUDES := external/skia/include/core
LOCAL_SHARED_LIBRARIES := \
    libcutils \
    libutils \
    libbinder \
    libskia \
    libui \
    libgui
    
LOCAL_SRC_FILES := screenshot.cpp

LOCAL_MODULE_TAGS := optional

LOCAL_MODULE           := screenshot

include $(BUILD_EXECUTABLE)
