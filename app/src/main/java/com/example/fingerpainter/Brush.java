package com.example.fingerpainter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText ;
import android.widget.TextView;

public class Brush extends AppCompatActivity {

    TextView brush_shape_txt ;
    TextView brush_width_txt ;

    Button sqr_btn  ;
    Button round_btn ;

    Button btn_confirm ;

    EditText brush_width_input  ;

    int shape  ;

    String brush_width ;

    int bsh_wth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brush);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButtonHandler();

        brush_shape_txt = ( TextView)findViewById(R.id.current_brush_shape) ;
        brush_width_txt = ( TextView)findViewById(R.id.current_brush_width) ;

        brush_width_input = (EditText)findViewById(R.id.width_input) ;

        HandleIntent();


    }


    public void HandleIntent(){

        Intent intent = getIntent()  ;

        if ( intent.getStringExtra("brush_shape") != null  ){
            String shp = intent.getStringExtra("brush_shape") ;
            brush_shape_txt.setText(shp);

        }

        if ( intent.getStringExtra("brush_width") != null ){

            bsh_wth  =  Integer.parseInt( intent.getStringExtra("brush_width")) ;

            String wth ="Width = " +  intent.getStringExtra("brush_width") ;

            brush_width_txt.setText(wth);
        }

    }

    public void ButtonHandler(){

        sqr_btn = (Button)findViewById(R.id.btn_sqr) ;

        sqr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                squareBrush() ;
            }
        });

        round_btn = (Button)findViewById(R.id.btn_rnd)  ;

        round_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                roundBrush() ;

            }


        });

        btn_confirm = (Button)findViewById(R.id.btn_confirm) ;

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm() ;
            }
        });


    }

    public void confirm(){
        Intent intent = new Intent() ;

        String shp = Integer.toString(shape) ;

        brush_width = brush_width_input.getText().toString() ;

        intent.putExtra("brush_shape" , shp ) ;




        intent.putExtra("brush_width", brush_width);

        if (  brush_width.equals("")  ){

            intent.putExtra("brush_width" , bsh_wth ) ;
        }


        setResult(RESULT_OK , intent);


        finish()  ;

    }

    public void squareBrush(){
        Intent intent = new Intent() ;

        shape = 1 ;
        String shp = Integer.toString(shape) ;

        intent.putExtra("brush_shape" ,  shp) ;

        brush_shape_txt.setText("Square");
        setResult(RESULT_OK , intent);

        //finish();

    }

    public void roundBrush(){
        Intent intent = new Intent() ;

        shape = 0 ;
        String shp = Integer.toString(shape) ;

        intent.putExtra("brush_shape" ,  shp) ;

        brush_shape_txt.setText("Round");

        setResult(RESULT_OK , intent);
        //finish()  ;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        confirm();
    }
}
