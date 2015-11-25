#sudo fastboot flash xloader MLO
#sudo fastboot flash bootloader u-boot.bin
sudo fastboot flash boot boot.img
#sudo fastboot flash cache cache.img
sudo fastboot flash system system.img
sudo fastboot flash recovery recovery.img
#sudo fastboot flash userdata userdata.img
sudo fastboot -w
sudo fastboot reboot



