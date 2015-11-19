#include <jni.h>
#include <gui/Surface.h>
#include <gui/ISurface.h>
#include <gui/ISurfaceComposer.h>
#include <gui/ISurfaceComposerClient.h>
#include <gui/SurfaceComposerClient.h>
#include <android/log.h>

using namespace android;

extern "C" JNIEXPORT void JNICALL Java_com_example_viewtest_MainActivity_func1(JNIEnv* , jobject)
{
	__android_log_print(ANDROID_LOG_VERBOSE, "XUE", "The value of 1 + 1 is %d", 1+1);
	sp<SurfaceComposerClient> mSurfaceComposerClient = new SurfaceComposerClient();
	sp<SurfaceControl> mSurfaceControl = mSurfaceComposerClient->createSurface(0,1280, 720, HAL_PIXEL_FORMAT_RGB_565);


    mSurfaceComposerClient->openGlobalTransaction();
//    mSurfaceControl->setLayer(0x7fffffff);
//    mSurfaceControl->setPosition(0, 0);
//    mSurfaceControl->setSize(1280, 720);
//    mSurfaceControl->show();
    mSurfaceComposerClient->closeGlobalTransaction();

//sp<ANativeWindow> mNativeWindow = mSurfaceControl->getSurface();
//    ANativeWindow_Buffer buffer;
//    if (ANativeWindow_lock(mNativeWindow.get(), &buffer, NULL) == 0)
//    {
//      memset(buffer.bits, 0x56,  1280 * 720 * 2);
//      ANativeWindow_unlockAndPost(mNativeWindow.get());
//    }
}
