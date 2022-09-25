package oracle.instance.process;

import oracle.OracleSystem;
import oracle.latch.buffer.BufferHandleLatch;
import oracle.latch.buffer.ChainsLatch;
import oracle.latch.buffer.LRUChainsLatch;

public class Process {
    BufferHandleLatch bufferHandleLatch;
    ChainsLatch chainsLatch;
    LRUChainsLatch lruChainsLatch;

    void requestBufferHandleLatch(){
        this.bufferHandleLatch = OracleSystem.getLatch("bufferHandleLatch");
    }

    void requestChainsLatch(){

    }

    void requestLRUChainsLatch(){

    }
}
