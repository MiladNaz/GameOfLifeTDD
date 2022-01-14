package gameOfLife;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class VisualBoard extends JFrame {
    static JButton[][] visualCells = new JButton[100][100];

    public VisualBoard() {

        Board board = new Board();

        setSize(1000, 1000);
        setLayout(new GridLayout(100, 100));
        for (int i = 0; i < visualCells.length; i++) {
            for (int j = 0; j < visualCells[i].length; j++) {
                visualCells[i][j] = new JButton();
                if (board.cells[i][j])
                    visualCells[i][j].setBackground(Color.BLACK);
                else
                    visualCells[i][j].setBackground(Color.WHITE);

                add(visualCells[i][j]);
            }
        }
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        while(!board.isDone){
            setTimeOut(100);
            board.nextTick();
        }

    }

    private void setTimeOut(int milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException ignored) {
        }
    }

    public static void main(String[] args) {
        new VisualBoard();
    }
}
