package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    //0:jerry 1: tom
    int activePlayer = 0;
    int [] gameState={2,2,2,2,2,2,2,2,2,2,2,2};
    boolean gameActive=true;
    int [][] winningPosition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{2,5,8},{0,4,8},{2,4,6},{1,4,7}};


    public void buttonPlayAgain(View view){

        Button againButton = (Button) findViewById(R.id.again);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerText);
        againButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);
        for (int i = 0;i<gridLayout.getChildCount();i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }
        for (int i = 0;i<gameState.length;i++){
            gameState[i]=2;
        }
            activePlayer = 0;

            gameActive=true;

    }



    public void drop_in(View view){


        ImageView counter = (ImageView) view;
        Log.i("TAG",counter.getTag().toString());

        int tappedCounter =Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter]==2 && gameActive ){
        gameState[tappedCounter] = activePlayer;



        counter.setTranslationY(-1500);
    if (activePlayer ==0){
        counter.setImageResource(R.drawable.jerry);
        activePlayer = 1;
    }else {

        counter.setImageResource(R.drawable.tom);
        activePlayer=0;
    }
        counter.animate().translationYBy(1500).rotation(1800).setDuration(500);
    for (int i = 0; i < winningPosition.length;i++){
        if (gameState[winningPosition[i][0]] == gameState[winningPosition[i][1]] &&
                gameState[winningPosition[i][1]]==gameState[winningPosition[i][2]] &&
                gameState[winningPosition[i][0]]!=2){
            gameActive=false;

            String winner = null;
            if (activePlayer==0){
                winner = "tom";

            }
            else if (activePlayer==1){
                winner="jerry";

            }

            Button againButton = (Button) findViewById(R.id.again);
            TextView winnerTextView = (TextView) findViewById(R.id.winnerText);
            winnerTextView.setText(winner+" has won!");

            winnerTextView.setVisibility(View.VISIBLE);
            againButton.setVisibility(View.VISIBLE);

        }


        }
            int count=0;

            for (int i = 0; i < gameState.length; i++) {
                if (gameState[i] !=2) {
                    count++;
                }
            }

//    Set<Integer> distinct = Arrays.stream(gameState).boxed().collect(Collectors.toSet());
//            boolean allEqual = distinct.size() ==1;
            if (count==9 ){
                TextView winnerTextView = (TextView) findViewById(R.id.winnerText);
                Button againButton = (Button) findViewById(R.id.again);
                winnerTextView.setText("It's a draw");
                winnerTextView.setVisibility(View.VISIBLE);
                againButton.setVisibility(View.VISIBLE);
            }
    }}




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}