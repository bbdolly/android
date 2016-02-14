#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#include <binder/MemoryBase.h>
#include <binder/MemoryHeapBase.h>
#include <binder/MemoryDealer.h>
#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>
#include <binder/IMemory.h>

using namespace android;

void captureIMG(char *buffer, char * filename)
{
	int * enableOSD = (int *) buffer;
	int * enableCapture = (int *) buffer + 1;
	int * osdleft = (int *) buffer + 12;
	int * osdtop = (int *) buffer + 13;
	int * osdwidth = (int *) buffer + 14;
	int * osdheight = (int *) buffer + 15;
	char * imageBase = buffer + 16 * 4;

	//start capture
	*enableCapture = 1;

	//sleep
	while (*enableCapture == 1)
	{
		printf(".\n");
		sleep(1);
	}

	//write file
	int filedes = open(filename, O_CREAT | O_WRONLY | O_SYNC | O_TRUNC, 0777);
	if (filedes > 0)
	{
		int ret = write(filedes, imageBase, 1920 * 1080 * 3 / 2);
		close(filedes);
	}
}

void OSD(char * buffer, char * filename)
{
	int * enableOSD = (int *) buffer;
	int * enableCapture = (int *) buffer + 1;
	int * osdleft = (int *) buffer + 12;
	int * osdtop = (int *) buffer + 13;
	int * osdwidth = (int *) buffer + 14;
	int * osdheight = (int *) buffer + 15;
	char * imageBase = buffer + 16 * 4;

	*osdleft = 640;
	*osdtop  = 360;
	*osdwidth = 300;
	*osdheight = 300;

	//write file
	int filedes = open(filename, O_RDONLY, 0777);
	if (filedes > 0)
	{
		read(filedes, imageBase, (*osdwidth) * (*osdheight) * 3 / 2);
		close(filedes);
	}
	for (int i = 0; i < (*osdwidth) * (*osdheight) / 4; i++)
	{
		char * p = imageBase + (*osdwidth) * (*osdheight) + 2 * i;
		char temp = *p;
		*p = *(p + 1);
		*(p + 1) = temp;
	}

	*enableOSD = 1;

	while(1) sleep(1);
}

int main(int argc, char * argv[])
{
	//open OSDMemory
	sp<IServiceManager> sm = defaultServiceManager();
	sp<IBinder> binder = sm->getService(String16("Oculus.OSDMemory"));
	printf("Binder:%x\n",binder.get());
	sp<IMemoryHeap> memHeap = IMemoryHeap::asInterface(binder);
	char * p = (char *)memHeap->getBase();
	printf("length:%d piont:%x\n",memHeap->getSize(),p);

	if(argc >= 2)
	{
		//captureIMG(p,argv[1]);
		OSD(p,argv[1]);
	}


	return 0;
}
