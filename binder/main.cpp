#include <binder/MemoryBase.h>
#include <binder/MemoryHeapBase.h>
#include <binder/MemoryDealer.h>
#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>

using namespace android;

int main()
{
	static bool _first = true;
	int g_osd_size = (1920 * 1080 * 3 / 2 + 16 * 4);

	sp<ProcessState> proc(ProcessState::self());
	sp<MemoryHeapBase> g_MemHeap = new MemoryHeapBase(g_osd_size, 0,"Oculus.OSDMemory");
	memset(g_MemHeap->getBase(),0,g_MemHeap->getSize());
	sp<IServiceManager> sm = defaultServiceManager();
	int r = defaultServiceManager()->addService(String16("Oculus.OSDMemory"), g_MemHeap);

	proc->startThreadPool();
	printf("ok\n");

	while(1) sleep(1);
	return 0;
}
