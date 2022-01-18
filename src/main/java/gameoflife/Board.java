package gameoflife;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Board {

    private Boolean[][] cells = new Boolean[100][100];
    boolean isDone;

    public Boolean[][] getCells() {
        return cells;
    }

    public boolean cell(int i, int j) {
        return cells[i][j];
    }

    public void setCell(int column, int row, boolean cell) {
        this.cells[column][row] = cell;
    }

    public Board() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Random random = new Random();
                cells[i][j] = random.nextBoolean();
            }
        }
    }

    public void nextGeneration() {
        Boolean[][] nextGenerationCells = new Boolean[100][100];
        clearBoard(nextGenerationCells);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                checkRules(nextGenerationCells, i, j);
            }
        }
        isDone = nextGenerationCells == cells;
        cells = nextGenerationCells;
    }

    private void checkRules(Boolean[][] nextGenerationCells, int i, int j) {
        if (cells[i][j]) {
            if (countNeighbors(i, j) == 2) {
                nextGenerationCells[i][j] = true;
                changeColorOnVisualCells(i, j, Color.BLACK);
            }
            if (countNeighbors(i, j) < 2){
                nextGenerationCells[i][j] = false;
                changeColorOnVisualCells(i, j, Color.WHITE);
            }
            if(countNeighbors(i, j) > 3) {
                nextGenerationCells[i][j] = false;
                changeColorOnVisualCells(i, j, Color.WHITE);
            }
        }

        if (countNeighbors(i, j) == 3) {
            nextGenerationCells[i][j] = true;
            changeColorOnVisualCells(i, j, Color.BLACK);
        }
    }

    private void changeColorOnVisualCells(int i, int j, Color color) {
        try {
            VisualBoard.visualCells[i][j].setBackground(color);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    private int countNeighbors(int x, int y) {
        int countNeighbors = 0;

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                try {
                    if (cells[i][j])
                        countNeighbors++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
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
