package gameOfLife;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOfLifeTest {
    Board board = new Board();


    @Test
    void aBoardHasHundredColumns() {
        int actual = board.cells.length;
        int expected = 100;
        assertEquals(expected, actual);
    }

    @Test
    void aBoardHasHundredRows() {
        int actual = board.cells[99].length;
        int expected = 100;
        assertEquals(expected, actual);
    }

    @Test
    void aCellIsEitherAliveOrDead() {
        boolean actual = board.cells[Math.round((int) (Math.random() * 100))][Math.round((int) (Math.random() * 100))];
        assertThat(actual).isNotNull();
    }

    @Test
    void aBoardContainsBothDeadAndAliveCells(){
        boolean containsBoth = Arrays.asList(board.cells[Math.round((int) (Math.random() * 100))]).contains(true && false);
        assertThat(containsBoth).isTrue();
    }

    @Test
    void anyCellWithFewerThanTwoLiveNeighborsDies(){
        board.cells[49][49] = true;
        board.cells[49][50] = false;
        board.cells[49][51] = false;
        board.cells[50][49] = false;
        board.cells[50][51] = false;
        board.cells[51][49] = false;
        board.cells[51][50] = false;
        board.cells[51][51] = false;
        board.nextTick();
        boolean aliveOrDead = board.cells[50][50];

        assertThat(aliveOrDead).isFalse();
    }

}
