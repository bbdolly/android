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


int main(int argc, char * argv[])
{
	sp<IServiceManager> sm = defaultServiceManager();
	sp<IBinder> binder = sm->getService(String16("Oculus.OSDMemory"));
	printf("Binder:%x\n",binder.get());
	sp<IMemoryHeap> memHeap = IMemoryHeap::asInterface(binder);
	char * p = (char *)memHeap->getBase();

	printf("length:%d piont:%x\n",memHeap->getSize(),p);

//	while(1)
//	{
//	printf("%d\n",*(int *)p);
//	(*(int *)p)++;
//	printf("%d\n",*(int *)p);
//	sleep(1);
//	}

//capture
	if (argc >= 2)
	{
		//start capture
		*((int *) p + 1) = 1;
		while (*((int *) p + 1) == 1)
		{
			printf("%d\n",*((int *) p + 1));
			sleep(1);
		}

		int filedes = open(argv[1], O_CREAT | O_WRONLY | O_SYNC | O_TRUNC, 0777);
		if (filedes > 0)
		{
			int ret = write(filedes, (void*) p + 4*16, 1920*1080*3/2);
			close(filedes);
		}
	}
	printf("wait for exit....\n");
	while(1) sleep(1);
	return 0;
}
