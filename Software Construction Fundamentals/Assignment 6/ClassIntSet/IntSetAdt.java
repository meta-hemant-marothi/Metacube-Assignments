public interface IntSetAdt{
    boolean isMember(int x);
    int size();
    boolean isSubSet(IntSetAdt s);
    IntSetAdt getComplement();
    IntSetAdt union(IntSetAdt s);
}