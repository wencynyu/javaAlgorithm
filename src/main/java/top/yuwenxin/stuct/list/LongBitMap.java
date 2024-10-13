package top.yuwenxin.stuct.list;

public class LongBitMap {

    private final long[] bits;
    private final long size;

    public LongBitMap(long otcSize) {
        if (otcSize > 0x1FFFFFFFFL) {
            throw new RuntimeException("over 2 ^ 38, not support.");
        }
        this.size = otcSize;
        this.bits = new long[(int) ((otcSize >> 6) + 1)];
    }

    public long getSize() {
        return size;
    }

    public void put(long n) {
        int index = (int) (n >> 6);
        long offset = n & 0x3F;
        bits[index] |= 1L << offset;
    }

    public boolean contain(int n) {
        int index = n >> 6;
        int position = n & 0x3F;
        return (bits[index] & (1L << position)) != 0;
    }
}
