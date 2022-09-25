package oracle.instance.globalarea.sga;

import oracle.instance.globalarea.sga.dbbuffer.DatabaseBufferCache;

/**
 * Shared Global Area라 하기도 함
 * Disk에서 가져온 데이터를 캐시하는 메모리 영역
 *
 * <p>{@link oracle.instance.process.BackgroundProcess} <br/> {@link oracle.instance.process.user.ServerProcess}
 * 둘다 사용
 * </p>
 *
 * @see "p.20"
 */
public class SystemGlobalArea {
    DatabaseBufferCache databaseBufferCache;
    RedoBufferLog redoBufferLog;
    SharedPool sharedPool;
}
