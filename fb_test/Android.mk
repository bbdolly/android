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
    
LOCAL_SRC_FILES := screencap.cpp

LOCAL_MODULE_TAGS := optional

LOCAL_MODULE           := screencap

include $(BUILD_EXECUTABLE)

################################################################################

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := 
LOCAL_CFLAGS := -g -O0 
LOCAL_C_INCLUDES := \
	external/zlib \
	external 

LOCAL_SHARED_LIBRARIES := libcutils libz liblog
LOCAL_STATIC_LIBRARIES := libpng
    
LOCAL_SRC_FILES := screenshot.c

LOCAL_MODULE_TAGS := optional

LOCAL_MODULE           := screenshot

include $(BUILD_EXECUTABLE)


################################################################################

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := 
LOCAL_CFLAGS := -g -O0 
LOCAL_C_INCLUDES := \


LOCAL_SHARED_LIBRARIES := 
LOCAL_STATIC_LIBRARIES := 
    
LOCAL_SRC_FILES := fbtool.c

LOCAL_MODULE_TAGS := optional

LOCAL_MODULE           := fbtool

include $(BUILD_EXECUTABLE)
