package com.example.ttt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    static ArrayList<Integer> compPosition = new ArrayList<Integer>();

    Button button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9;
    TextView result;
    boolean isPlayer = true;
    List<View> buttonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);

        buttonList = new ArrayList<View>();
//        setContentView(R.layout.activity_main);
//        board [],
//        };
       // printBordGame(bordGame);
      //  playerMove();
      //  computerMove();

    }

    public void onClick(View v) {
        playerMove(v, "X");
        isPlayer = false;
        }

        public void onAction() {
            playerMove(button_2, "O");
            isPlayer = true;
        }

    private static void printBordGame(char [][] bordGame) {

        for(char[] row: bordGame){
            for (char c: row){
                System.out.print(c);
            }
            System.out.println();
        }

    }
    public void playerMove(View v, String value) {
            switch(v.getId()){
                case R.id.button_1:
                    button_1.setText(value);
                    break;
                case R.id.button_2:
                    button_2.setText(value);
                    break;
                case R.id.button_3:
                    button_3.setText(value);
                    break;
                case R.id.button_4:
                    button_4.setText(value);
                    break;
                case R.id.button_5:
                    button_5.setText(value);
                    break;
                case R.id.button_6:
                    button_6.setText(value);
                    break;
                case R.id.button_7:
                    button_7.setText(value);
                    break;
                case R.id.button_8:
                    button_8.setText(value);
                    break;
                case R.id.button_9:
                    button_9.setText(value);
                    break;
                default:
                    System.out.print("Something wrong with buttons");
                    break;
        }

        buttonList.add(v);

        onAction();


           // result = (TextView) findViewById(R.id.winner);
//            while (playerPosition.contains(playerPos) || compPosition.contains(playerPos)) {
//                System.out.println("the position is taken enter another position ");
//                playerPos = scanner.nextInt();
//                if (result.length() > 0) {
//                    result.setText(CheckWinner());
//                    System.out.println(result);
//                }
//            }
 //       }
    }

    private void computerMove() {
        Random random = new Random();
        int compPos = random.nextInt(9) + 1;
        result = (TextView) findViewById(R.id.winner);
        while (playerPosition.contains(compPos) || compPosition.contains(compPos))
            compPos = random.nextInt(9) + 1;
        if(result.length()> 0){
            System.out.println(result);
            return;
        }
    }

    public static String CheckWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();

        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);
        for (List l : winning){
            if (playerPosition.containsAll(l)){
                return "u won";
            }else if (compPosition.containsAll(l)){
                return "u lost";
            }else if (playerPosition.size() + compPosition.size() == 9) {
                return "cat";

            }
        }
        return "";
    }

 }

