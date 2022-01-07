package gameOfLife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOfLifeTest {
    Board board = new Board();

    @Test
    void aBoardHasHundredColumns(){
        int actual = board.cells.length;
        int expected = 100;
        assertEquals(expected, actual);
    }

    @Test
    void aBoardHasHundredRows(){
        int actual = board.cells[99].length;
        int expected = 100;
        assertEquals(expected, actual);
    }

}
