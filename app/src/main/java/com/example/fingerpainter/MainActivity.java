package com.example.fingerpainter;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;



public class MainActivity extends AppCompatActivity {

    FingerPainterView fingerPainterView ;
    Button color_btn ;
    Button brush_btn ;


    int [] colors = { Color.GREEN , Color.BLUE , Color.BLACK , Color.YELLOW , Color.RED } ;

    Paint.Cap [] brushes = { Paint.Cap.ROUND , Paint.Cap.SQUARE } ;

    public int RQST = 1 ;
    public int BRUSH = 5 ;


    Uri uri ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonHandler();

        Intent data = getIntent()  ;
        uri = data.getData()  ;
        fingerPainterView.load(uri);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {


        super.onSaveInstanceState(outState);

        int color  , width , shape ;
         color = fingerPainterView.getColour() ;
         width  =fingerPainterView.getBrushWidth() ;

        Paint.Cap brush_shape = fingerPainterView.getBrush() ;

        shape = 2 ;

        if(brush_shape == Paint.Cap.ROUND){
            shape = 0 ;
        }
        if(brush_shape == Paint.Cap.SQUARE){
            shape = 1 ;
        }

        outState.putInt("brush_color" , color);
        outState.putInt("brush_shape" , shape);

        outState.putInt("brush_width" , width);


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);


        int color = savedInstanceState.getInt("brush_color") ;
        int width = savedInstanceState.getInt("brush_width") ;
        int shape = savedInstanceState.getInt("brush_shape") ;

        fingerPainterView.setColour(color);
        fingerPainterView.setBrushWidth(width);

        fingerPainterView.setBrush( brushes[shape] );


    }

    public void ButtonHandler(){
        fingerPainterView = (FingerPainterView)findViewById(R.id.myFingerPainterViewID) ;

        color_btn = (Button)findViewById( R.id.color_btn ) ;

        color_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseColour();
            }
        });

        brush_btn = (Button)findViewById(R.id.btn_brush) ;

        brush_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseBrush() ;
            }
        });




    }



    public void chooseColour(){
        Intent intent = new Intent(MainActivity.this , Colour.class)  ;

        String clr = getBrushColor() ;

        intent.putExtra("current_color" ,  clr ) ;

        startActivityForResult(intent , RQST);


    }

    public void chooseBrush(){

        String shape = getBrushShape()  ;

        String width =  Integer.toString( fingerPainterView.getBrushWidth() ) ;

        Intent intent = new Intent(MainActivity.this , Brush.class) ;
        intent.putExtra("brush_shape" ,  shape)  ;
        intent.putExtra("brush_width" ,  width)  ;


        startActivityForResult(intent , BRUSH);

    }

    public String  getBrushColor(){

        int clr = fingerPainterView.getColour()  ;

        if(clr  == Color.BLACK){
            return  "Black" ;
        }

        if(clr ==Color.YELLOW){
            return "Yellow"  ;
        }

        if(clr==Color.BLUE){
            return "Blue" ;
        }

        if(clr == Color.RED){
            return "Red" ;
        }

        if(clr==Color.GREEN){
            return "Green" ;
        }

        return ""  ;

    }


    public String getBrushShape(){
        Paint.Cap current_shape = fingerPainterView.getBrush() ;

        if(current_shape ==  Paint.Cap.ROUND){
            return "Round"  ;
        }

        if(current_shape == Paint.Cap.SQUARE){
            return "Square"  ;
        }


        return ""  ;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == RQST && resultCode == RESULT_OK ){

            int clr =   Integer.parseInt(data.getStringExtra("color") );

            fingerPainterView.setColour( colors[clr] );

        }


        if( requestCode == BRUSH  && resultCode == RESULT_OK ){

            int brush_width = 20 ;

          int brush_shape = Integer.parseInt(data.getStringExtra("brush_shape") ) ;

          if(data.getStringExtra("brush_width")  == null  ){
              fingerPainterView.setBrushWidth( fingerPainterView.getBrushWidth() )  ;
          }
          else {
               brush_width = Integer.parseInt(data.getStringExtra("brush_width")) ;
          }



          fingerPainterView.setBrush( brushes[brush_shape]  );

          fingerPainterView.setBrushWidth(brush_width);

        }

    }


}
