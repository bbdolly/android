adb push /home/xxue/workspace/aosp/out/target/product/flounder/system/bin/fb-test /data/fb-test
adb push /home/xxue/workspace/aosp/out/target/product/flounder/system/bin/screenshot /data/screenshot
adb push /home/xxue/workspace/aosp/out/target/product/flounder/system/bin/screencap /data/screencap

adb shell /data/fb-test
adb shell /data/screenshot /sdcard/1.png
adb pull /data/1.png
