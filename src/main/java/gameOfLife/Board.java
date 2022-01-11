package gameOfLife;

import java.util.Random;

public class Board {
    public Boolean[][] cells = new Boolean[100][100];

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
                if(cells[i][j]){
                    if(countNeighbors(i, j) < 2)
                        tempCells[i][j] = false;
                    else if(countNeighbors(i, j) == 2 || countNeighbors(i, j) == 3)
                        tempCells[i][j] = true;
                    if(countNeighbors(i,j) > 3)
                        tempCells[i][j] = false;
                }
            }
        }
        cells = tempCells;
        System.out.println(cells[30][30]);
    }

    private int countNeighbors(int x, int y) {
        int countNeighbors = 0;

        for(int i = x-1; i < x+2; i++){
            for(int j = y-1; j < y+2; j++){
                try {
                    if(cells[i][j])
                        countNeighbors++;
                }catch (ArrayIndexOutOfBoundsException e){
                }
            }
        }
        if(cells[x][y])
            countNeighbors--;
        System.out.println(countNeighbors);
        return countNeighbors;
    }

    public void clearBoard(Boolean [][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = false;
            }
        }
    }
}
