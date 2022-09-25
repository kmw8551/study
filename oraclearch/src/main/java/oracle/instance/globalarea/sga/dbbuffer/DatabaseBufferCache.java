package oracle.instance.globalarea.sga.dbbuffer;

import java.util.HashMap;
import java.util.List;
import oracle.datastruct.physical.block.DataBlockAddress;

public class DatabaseBufferCache {
    //ChainsLatch에 의해 보호됨
    HashMap<DataBlockAddress, List<BufferHeader>> bufferMap;

    //lruChainsLatch에 의해 보호됨
    List<BufferHeader> lruList;

    List<BufferHeader> dirtyList;
}
