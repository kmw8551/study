package oracle.instance.globalarea.sga.dbbuffer;

import java.util.List;
import oracle.instance.process.Process;

public class BufferHeader {
    BlockStatus status;
    BufferBlock bufferBlockPointer;
    List<Process> bufferBlockWaitList;

    //lockmode가 exclusive일땐 1명만 있을수 있음.
    //length > 0 이면 .. Buffer Pinned
    //읽기/변경이 끝났다고 바로 사라지는건 아님 ... BufferPinning
    List<BufferHandle> lockHolderList;
}
