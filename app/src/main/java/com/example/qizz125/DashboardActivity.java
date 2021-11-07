package com.example.qizz125;

import static com.example.qizz125.MainActivity.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue=20;
    List<Modelclass> allQuestionList;
    Modelclass modelclass;
    int index=0;
    TextView  question, optiona, optionb, optionc, optiond;
    CardView cardOA, cardOB, cardOC, cardOD;
    int correctCount=0;
    int wrongCount=0;
    Button nextBtn;
    private View progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Hooks();

        allQuestionList=list;
        Collections.shuffle(allQuestionList);
        modelclass=list.get(index);

        setAlldata();


        countDownTimer=new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue=timerValue-1;

            }

            @Override
            public void onFinish() {
                Dialog dialog=new Dialog(DashboardActivity.this);
            }
        }.start();
    }

    private void setAlldata() {

        question.setText(modelclass.getQuestion());
        optiona.setText(modelclass.getoA());
        optionb.setText(modelclass.getoB());
        optionc.setText(modelclass.getoC());
        optiond.setText(modelclass.getoD());
    }

    @SuppressLint("WrongViewCast")
    private void Hooks() {

        progressBar=findViewById(R.id.quiz_timer);
        question = findViewById(R.id.card_question);
        optiona = findViewById(R.id.card_optionA);
        optionb = findViewById(R.id.card_optionB);
        optionc = findViewById(R.id.card_optionC);
        optiond = findViewById(R.id.card_optionD);

        cardOA = findViewById(R.id.cardA);
        cardOB = findViewById(R.id.cardB);
        cardOC = findViewById(R.id.cardC);
        cardOD = findViewById(R.id.cardD);

        nextBtn = findViewById(R.id.nextBtn);
    }

    public void Correct(CardView cardView){

        cardView.setBackgroundColor(getResources().getColor(R.color.color_green));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctCount++;
                index++;
                modelclass = list.get(index);
                setAlldata();

            }
        });



    }
    public void Wrong(CardView cardOA) {


        cardOA.setBackgroundColor(getResources().getColor(R.color.color_red));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongCount++;
                if (index < list.size() - 1) {
                    index++;
                    modelclass = list.get(index);
                    setAlldata();
                    resetColor();
                } else {
                    GameMon();
                }
            }
        });
    }
    private void GameMon() {
        Intent intent = new Intent(DashboardActivity.this, wonActivity.class);
        intent.putExtra("correct",correctCount);
        intent.putExtra("wrong",wrongCount);
        startActivity(intent);

    }

    public void enableButton(){
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);

    }
    public void disableButton() {
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);
    }

    public void resetColor(){
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void onClickA(View view) {
        nextBtn.setClickable(true);
        if(modelclass.getoA().equals(modelclass.getAns()))
        {
            cardOA.setBackgroundColor(getResources().getColor(R.color.color_green));

            if (index < list.size()-1) {

                Correct(cardOA);

            }else {
                GameMon();

            }


        }
        else {
            Wrong(cardOA);
        }

    }
    public void onClickB(View view) {
        nextBtn.setClickable(true);
        if(modelclass.getoB().equals(modelclass.getAns()))
        {
            cardOB.setBackgroundColor(getResources().getColor(R.color.color_green));

            if (index < list.size()-1) {

                Correct(cardOB);

            }else {
                GameMon();

            }


        }
        else {
            Wrong(cardOB);
        }

    }
    public void onClickC(View view) {
        nextBtn.setClickable(true);
        if(modelclass.getoC().equals(modelclass.getAns()))
        {
            cardOC.setBackgroundColor(getResources().getColor(R.color.color_green));

            if (index < list.size()-1) {

                Correct(cardOC);

            }else {
                GameMon();

            }


        }
        else {
            Wrong(cardOC);
        }

    }
    public void onClickD(View view) {
        nextBtn.setClickable(true);
        if(modelclass.getoD().equals(modelclass.getAns()))
        {
            cardOD.setBackgroundColor(getResources().getColor(R.color.color_green));

            if (index < list.size()-1) {

                Correct(cardOD);

            }else {
                GameMon();

            }


        }
        else {
            Wrong(cardOD);
        }

    }
}
