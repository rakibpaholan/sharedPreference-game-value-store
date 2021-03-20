package com.example.sharepreferencestorescoreofgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView score_textView;
    private Button increase_button,decrease_button;
    private int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        score_textView = (TextView)findViewById(R.id.score_id);
        increase_button = (Button)findViewById(R.id.increase_button_id);
        decrease_button = (Button)findViewById(R.id.decrease_button_id);


        increase_button.setOnClickListener(this);
        decrease_button.setOnClickListener(this);



        if (loadData() != 0){
            score_textView.setText("Score : "+loadData());
        }




    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.increase_button_id){
            score = score +10;
            score_textView.setText("Score : "+score);
            saveScore(score);


        }
        if (id==R.id.decrease_button_id){
            score = score-10;
            score_textView.setText("Score : "+score);
            saveScore(score);



        }
    }

    public void saveScore(int score){

        SharedPreferences sharedPreferences = getSharedPreferences("gameValue",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("keyValue",score);

        editor.commit();

    }

    private int loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("gameValue",Context.MODE_PRIVATE);
        int score =  sharedPreferences.getInt("keyValue",0);
        return score;
    }
}