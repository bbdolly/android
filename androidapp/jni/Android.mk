LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_CFLAGS  = -I/media/xue/1550a300-37c8-4615-bdf0-42d824798b3f/thunder_v1_0/endpoint_common/media_processor/src/video/openmax/platform/4.1.2/inc/system/core/include 
LOCAL_CFLAGS += -I/media/xue/1550a300-37c8-4615-bdf0-42d824798b3f/thunder_v1_0/endpoint_common/media_processor/src/video/openmax/platform/4.1.2/inc/frameworks/native/include 
LOCAL_CFLAGS += -I/media/xue/1550a300-37c8-4615-bdf0-42d824798b3f/thunder_v1_0/endpoint_common/media_processor/src/video/openmax/platform/4.1.2/inc/hardware/libhardware/include/

LOCAL_LDLIBS += -L/media/xue/1550a300-37c8-4615-bdf0-42d824798b3f/thunder_v1_0/endpoint_common/media_processor/src/video/openmax/platform/4.1.2/libs
LOCAL_LDLIBS += -lbinder -lmedia -lutils -landroid_runtime -lstagefright -lstagefright_foundation -lcutils -lui -lgui
LOCAL_LDLIBS += -landroid -lEGL 

LOCAL_MODULE    := viewtest
LOCAL_SRC_FILES := viewtest.cpp

include $(BUILD_SHARED_LIBRARY)
