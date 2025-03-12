import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ChessBoardTest {
    @Test
    public void nQueenTest(){
        NQueens nQ = new NQueens();
        int size = 4;
        assertEquals(true, nQ.nQueen(new int[size], 0, size));
        assertEquals(true, nQ.nQueen(new int[0], 0, 0));
        assertEquals(false, nQ.nQueen(new int[2], 0, 2));
    }

    @Test
    public void knightsTourTest(){
        KnightsTour kt = new KnightsTour();
        assertEquals(true, kt.knightsTourUtil(new int[8][8], 8, 0, 0, 64));
        assertEquals(true, kt.knightsTourUtil(new int[5][5], 5, 0, 0, 25));
        assertEquals(false, kt.knightsTourUtil(new int[2][2], 2, 0, 0,  4));
    }
}
