LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := 
LOCAL_CFLAGS := -g -O0 
LOCAL_C_INCLUDES := 
LOCAL_SHARED_LIBRARIES := 
LOCAL_SRC_FILES := test.c

LOCAL_MODULE           := fb-test

include $(BUILD_EXECUTABLE)
