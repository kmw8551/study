package oracle.instance.globalarea.sga.dbbuffer;

/**
 * 캐시된 데이터 블록의 상태
 * @see "p.29"
 */
public enum BlockStatus {
    FREE, DIRTY, PINNED
}
