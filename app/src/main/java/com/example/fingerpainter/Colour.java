package com.example.fingerpainter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colour extends AppCompatActivity {

    Button blue_btn ;
    Button green_btn ;
    Button black_btn ;
    Button yellow_btn ;
    Button red_btn ;

    TextView txt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour);

        txt = findViewById(R.id.current_color) ;

        ButtonHandler();

        Intent intent = getIntent()  ;

        if( intent.getStringExtra("current_color")  != null ){
           String color =  intent.getStringExtra("current_color") ;

            txt.setText(color);
        }


    }

    public void ButtonHandler(){
        blue_btn = (Button)findViewById(R.id.btn_blue) ;

        blue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blueColor()  ;

            }
        });

        green_btn = (Button)findViewById(R.id.btn_green) ;

        green_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greenColor();
            }
        });

        black_btn = (Button)findViewById(R.id.btn_black)  ;

        black_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackColor() ;
            }
        });

        red_btn = (Button)findViewById(R.id.btn_red) ;

        red_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              redColor() ;
            }
        });

        yellow_btn = (Button)findViewById(R.id.btn_yellow) ;

        yellow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               yellowColor() ;
            }
        });



    }

    public void yellowColor(){
        Intent intent = new Intent() ;
        intent.putExtra("color" , "3") ;
        setResult(RESULT_OK , intent);
        finish();
    }

    public void redColor(){
        Intent intent = new Intent() ;
        intent.putExtra("color" , "4") ;
        setResult(RESULT_OK , intent);
        finish();
    }


    public void blackColor(){
        Intent intent = new Intent() ;
        intent.putExtra("color" , "2") ;
        setResult(RESULT_OK , intent);
        finish();
    }

    public void blueColor(){
            Intent intent = new Intent() ;
            intent.putExtra("color" , "1") ;
            setResult(RESULT_OK , intent);
            finish();
    }

    public void greenColor(){

        Intent intent = new Intent() ;
        intent.putExtra("color" , "0") ;
        setResult(RESULT_OK , intent);
        finish();
    }


}
