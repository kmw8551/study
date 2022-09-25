package oracle.instance.process;

import oracle.latch.buffer.BufferHandleLatch;
import oracle.latch.buffer.ChainsLatch;
import oracle.latch.buffer.LRUChainsLatch;

public class Process {
    BufferHandleLatch bufferHandleLatch;
    ChainsLatch chainsLatch;
    LRUChainsLatch lruChainsLatch;
}
