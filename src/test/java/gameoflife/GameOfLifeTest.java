package gameoflife;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameOfLifeTest {
    Board board = new Board();


    @Test
    void aBoardHasHundredColumns() {
        int actual = board.getCells().length;
        int expected = 100;
        assertEquals(expected, actual);
    }

    @Test
    void aBoardHasHundredRows() {
        int actual = board.getCells().length;
        int expected = 100;
        assertEquals(expected, actual);
    }

    @Test
    void aBoardContainsBothDeadAndAliveCells(){
        boolean containsAliveCells = Arrays.stream(board.getCells()).flatMap(Arrays::stream).anyMatch(x -> x.equals(true));
        assertThat(containsAliveCells).isTrue();

        boolean containsDeadCells = Arrays.stream(board.getCells()).flatMap(Arrays::stream).anyMatch(x -> x.equals(false));
        assertThat(containsDeadCells).isTrue();

    }

    @Test
    void anyLiveCellWithFewerThanTwoLiveNeighborsDies(){
        board.clearBoard(board.getCells());
        board.setCell(50,50, true);

        board.setCell(49,49, true);
        board.nextGeneration();
        boolean aliveOrDead = board.cell(50,50);

        assertThat(aliveOrDead).isFalse();
    }

    @Test
    void anyLiveCellWithMoreThanThreeLiveNeighborsDies(){
        board.clearBoard(board.getCells());
        board.setCell(50,50, true);

        board.setCell(49,49, true);
        board.setCell(49,50,true);
        board.setCell(49,51,true);
        board.setCell(50,49,true);

        board.nextGeneration();
        boolean aliveOrDead = board.cell(50,50);

        assertThat(aliveOrDead).isFalse();
    }

    @Test
    void anyLiveCellWithTwoOrThreeLiveNeighborsLives(){
        board.clearBoard(board.getCells());
        board.setCell(50,50, true);

        board.setCell(49,49, true);
        board.setCell(49,50, true);
        board.setCell(49,51, true);

        board.nextGeneration();
        boolean aliveOrDead = board.cell(50,50);

        assertThat(aliveOrDead).isTrue();
    }

    @Test
    void anyDeadCellWithExactlyThreeLiveNeighborsBecomesALiveCell(){
        board.clearBoard(board.getCells());

        board.setCell(49,49, true);
        board.setCell(49,50, true);
        board.setCell(50,51, true);

        board.nextGeneration();
        boolean aliveOrDead = board.cell(50,50);

        assertThat(aliveOrDead).isTrue();
    }
}
