package leetcode.editor.en;

public class Pair<L, R> {
    public L l;
    public R r;
    public Pair(final L l, final R r) {
        this.l = l;
        this.r = r;
    }
    public L getLeft() {
        return l;
    }
    public R getRight() {
        return r;
    }
}
