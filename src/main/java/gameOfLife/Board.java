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
}
