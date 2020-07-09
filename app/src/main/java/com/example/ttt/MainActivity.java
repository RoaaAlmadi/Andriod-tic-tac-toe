package com.example.ttt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        char [][] bordGame  = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
        };
        printBordGame(bordGame);
        playerMove();
        computerMove();
        printBordGame(bordGame);


    }
    private static void printBordGame(char [][] bordGame) {

        for(char[] row: bordGame){
            for (char c: row){
                System.out.print(c);
            }
            System.out.println();
        }

    }
    public void playerMove() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter number between (1-9): ");
            int playerPos = scanner.nextInt();
            System.out.println(playerPos);
            result = (TextView) findViewById(R.id.winner);
            while (playerPosition.contains(playerPos) || compPosition.contains(playerPos)) {
                System.out.println("the position is taken enter another position ");
                playerPos = scanner.nextInt();
                if (result.length() > 0) {
                    result.setText(CheckWinner());
                    System.out.println(result);
                }
            }
        }
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

