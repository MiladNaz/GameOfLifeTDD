package gameOfLife;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Board {

    public Boolean[][] cells = new Boolean[100][100];
    boolean isDone;

    public Board() {

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Random random = new Random();
                cells[i][j] = random.nextBoolean();
            }
        }
    }

    public void nextTick() {
        Boolean[][] tempCells = new Boolean[100][100];
        clearBoard(tempCells);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                //check alive cells
                if (cells[i][j]) {
                    if (countNeighbors(i, j) < 2) {
                        tempCells[i][j] = false;
                        changeColorOnVisualCells(i, j, Color.WHITE);
                    }
                    else if (countNeighbors(i, j) == 2) {
                        tempCells[i][j] = true;
                        changeColorOnVisualCells(i, j, Color.BLACK);
                    }
                    if (countNeighbors(i, j) > 3) {
                        tempCells[i][j] = false;
                        changeColorOnVisualCells(i, j, Color.WHITE);
                    }
                }
                if (countNeighbors(i, j) == 3) {
                    tempCells[i][j] = true;
                    changeColorOnVisualCells(i, j, Color.BLACK);
                }
            }
        }
        isDone = tempCells == cells;
        cells = tempCells;
    }

    private void changeColorOnVisualCells(int i, int j, Color white) {
        try {
            VisualBoard.visualCells[i][j].setBackground(white);
        }catch (NullPointerException ignored){

        }
    }

    private int countNeighbors(int x, int y) {
        int countNeighbors = 0;

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                try {
                    if (cells[i][j])
                        countNeighbors++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        if (cells[x][y])
            countNeighbors--;
        return countNeighbors;
    }

    public void clearBoard(Boolean[][] cells) {
        for (Boolean[] cell : cells) {
            Arrays.fill(cell, false);
        }
    }
}
