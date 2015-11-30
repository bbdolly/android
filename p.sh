sudo adb root
sudo adb remount
sudo adb push ../../out/target/product/flounder/system/app/MyService/MyService.apk /system/app/MyService/
sudo adb reboot
