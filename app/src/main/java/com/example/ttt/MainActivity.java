package com.example.ttt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //    Button button1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9;
    Button buttonNames[] = new Button[9];
    TextView result;

    //create a variable to set the current player
    String player;

    //create an array of cells for each player
    int[] cells = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] cellsA = cells.clone();
    int[] cellsB = cells.clone();

    //create two array of winning moves for each player
    ArrayList<int[]> winningMoves = new ArrayList<>(Arrays.asList(
            new int[]{1, 2, 3},
            new int[]{4, 5, 6},
            new int[]{7, 8, 9},
            new int[]{3, 5, 7},
            new int[]{1, 4, 7},
            new int[]{2, 5, 8},
            new int[]{3, 6, 9},
            new int[]{1, 5, 9}
    ));

    ArrayList<int[]> winningMovesA = (ArrayList<int[]>) winningMoves.clone();
    ArrayList<int[]> winningMovesB = (ArrayList<int[]>) winningMoves.clone();


    ArrayList<int[]> buttonMovesMap = new ArrayList(Arrays.asList(
            new int[]{0, 4, 7},
            new int[]{0, 5},
            new int[]{0, 3, 6},
            new int[]{1, 4},
            new int[]{1, 3, 5, 7},
            new int[]{1, 6},
            new int[]{2, 3, 4},
            new int[]{2, 5},
            new int[]{2, 6, 7}
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing buttons and assigning click listener to them.
        for (int i = 0; i < 9; i++) {
            int id = i + 1;
            String button = "button_" + id;
            int buttonId = getResources().getIdentifier(button, "id", getPackageName());
            buttonNames[i] = findViewById(buttonId);
            final int cellId = i;
            System.out.println("button create " + " " + i + " " + button + " " + id + " " + buttonNames[i]);
            buttonNames[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean gameCont = play(cellId, cellsA, cellsB, winningMovesA, "X");
                    boolean zero = containsZero(cellsB);
                    if (gameCont && zero) {
                        computerPlays();
                    } else if (!zero) {
                        Toast.makeText(MainActivity.this, "It is a tie", Toast.LENGTH_LONG).show();
                        resetGame();
                    }
                }
            });
        }
    }

    protected boolean play(int cellId, int[] player, int[] counter, ArrayList<int[]> winMovArr, String mark) {
        if (player[cellId] == 0) {
            buttonNames[cellId].setText(mark);
            int color = (player == cellsA) ? getResources().getColor(R.color.colorPrimaryDark) : getResources().getColor(R.color.colorAccent);
            buttonNames[cellId].setTextColor(color);
            Log.d("first comment", "");
            player[cellId] = 1;
            counter[cellId] = -1;
            if (winningMove(cellId, player, winMovArr)) {
                Log.d("You win", "You win");
                String winner;
                winner = (player == cellsA) ? "You" : "Computer";
                Toast.makeText(this, winner + " won!", Toast.LENGTH_SHORT).show();
                resetGame();
                return false;
            }
        } else {
            Toast.makeText(this, "Tap another button", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void resetGame() {
        winningMovesA = (ArrayList<int[]>) winningMoves.clone();
        winningMovesB = (ArrayList<int[]>) winningMoves.clone();
        cellsA = cells.clone();
        cellsB = cells.clone();
        for (int i = 0; i < buttonNames.length; i++) {
            buttonNames[i].setText(String.valueOf(i + 1));
            buttonNames[i].setTextColor(getResources().getColor(R.color.black));
        }
    }

    protected boolean winningMove(int cellId, int[] player, ArrayList<int[]> winMovArr) {
        Log.d("started ", "");
//        boolean result = false;
        for (int i = 0; i < buttonMovesMap.get(cellId).length; i++) {
            try {
                int[] movesToCheck = winMovArr.get(buttonMovesMap.get(cellId)[i]);
                if (player[movesToCheck[0] - 1] == 1 &&
                        player[movesToCheck[1] - 1] == 1 &&
                        player[movesToCheck[2] - 1] == 1) {
                    return true;
                }
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
        }
        return false;
    }

    protected void computerPlays() {
        for (int i = 0; i < winningMovesB.size(); i++) {
            int count = 0;
            int nextCell = -1;
            for (int j = 0; j < 3; j++) {
                if (cellsB[winningMovesB.get(i)[j] - 1] == -1) {
                    count++;
                } else if (cellsB[winningMovesB.get(i)[j] - 1] == 0) {
                    nextCell = winningMovesB.get(i)[j] - 1;
                } else if (cellsB[winningMovesB.get(i)[j] - 1] == 1) {
                    break;
                }
            }
            if (count == 2 && nextCell != -1) {
                play(nextCell, cellsB, cellsA, winningMovesB, "O");
                winningMovesB.remove(i);
                return;
            }
        }
        int randomButton = (new Random()).nextInt(9);
        while (cellsB[randomButton] == -1 || cellsB[randomButton] == 1) {
            randomButton = (new Random()).nextInt(9);
        }
        play(randomButton, cellsB, cellsA, winningMovesB, "O");

    }

    public static boolean containsZero(int[] arr) {
        for (int s : arr) {
            if (s == 0)
                return true;
        }
        return false;
    }
}