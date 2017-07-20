#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>
#include <string.h>
#include <linux/fb.h>
#include <sys/mman.h>

inline static unsigned short int make16color(unsigned char r, unsigned char g,
		unsigned char b) {
	return ((((r >> 3) & 31) << 11) | (((g >> 2) & 63) << 5) | ((b >> 3) & 31));
}

struct fb_var_screeninfo vinfo;
struct fb_fix_screeninfo finfo;

int openfb()
{

	int fbfd = open("/dev/graphics/fb0", O_RDWR);
	if (!fbfd) {
		printf("Error: cannot open framebuffer device.\n");
		exit(1);
	}
	printf("The framebuffer device was opened successfully.\n");

	// Get fixed screen information
	if (ioctl(fbfd, FBIOGET_FSCREENINFO, &finfo)) {
		printf("Error reading fixed information.\n");
		exit(2);
	}

	// Get variable screen information
	if (ioctl(fbfd, FBIOGET_VSCREENINFO, &vinfo)) {
		printf("Error reading variable information.\n");
		exit(3);
	}

	printf("sizeof(unsigned short) = %d\n", sizeof(unsigned short));
	printf("%dx%d, %dbpp\n", vinfo.xres, vinfo.yres, vinfo.bits_per_pixel );
	printf("xoffset:%d, yoffset:%d, line_length: %d\n", vinfo.xoffset, vinfo.yoffset, finfo.line_length );

	return fbfd;
}

int main() {

	char data1[]={0xff,0xff,0xff,0xff,0x00,0x00,0x00,0x00};
	char data2[]={0x00,0x00,0x00,0x00,0xff,0xff,0xff,0xff};
	char *fbp = 0;
	int x = 0, y = 0;
	int fbfd = openfb();
	long int screensize = vinfo.xres * vinfo.yres * vinfo.bits_per_pixel / 8;

	fbp = (char *) mmap(0, screensize, PROT_READ | PROT_WRITE, MAP_SHARED, fbfd,0);
	if (fbp == -1)
	{
		printf("Error: failed to map framebuffer device to memory.\n");
		exit(4);
	}
	printf("The framebuffer device was mapped to memory successfully.\n");


//	for(x=0;x<vinfo.yres*vinfo.xres;x++)
//	{
//		memcpy(fbp+x*4,data1,4);
//	}

	for(y=0;y<vinfo.yres;y++)
	{
		for(x=0;x<vinfo.xres/2;x++)
		{
			if(y%2)
			{
				memcpy(fbp+y*vinfo.xres*4+x*8,data1,8);
			}
			else
			{
				memcpy(fbp+y*vinfo.xres*4+x*8,data2,8);
			}
		}
	}

	//clean framebuffer
	munmap(fbp, screensize);
	close(fbfd);

	return 0;
}
