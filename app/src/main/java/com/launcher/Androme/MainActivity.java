package com.launcher.Androme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //define variables
    RelativeLayout relativeLayout;
    TextView textView;
    SwipeListner swipeListener;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assign variable
        relativeLayout = findViewById(R.id.Relative_Layout);
        textView = findViewById(R.id.text_view);

        //initialize swipe listener
        swipeListener = new SwipeListner(relativeLayout);
    }
    private class SwipeListner implements View.OnTouchListener{
        //create variable for gesture
        GestureDetector gestureDetector;

        //create custructor

        SwipeListner(View view){
            //intialize threshold variable
            int threshold =100;
            int velocity_threshold=100;


            //intialize simple gesture listner
            GestureDetector.SimpleOnGestureListener listener = new
            GestureDetector.SimpleOnGestureListener(){
                @Override
                        public boolean onDown(MotionEvent e){
                    //pass ture
                    return true;
                }
                @Override
                        public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX, float velocityY){
                    float xDiff = e2.getX() -e1.getX();
                    float yDiff =e1.getY() -e2.getY();
                    try {
                        //catch condition
                        if(Math.abs(xDiff)>Math.abs(yDiff)) {
                            //when x is greater then y
                            //check condition
                            if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold) {
                                //when x diff greater then threshold
                                //when x velocity is greater then velocity threshold

                                if (xDiff > 0) {
                                    textView.setText("Swipe Right");

                                } else {
                                    textView.setText("Swipe left");
                                }
                                return true;
                            }
                        }else{
                                if(Math.abs(yDiff)> threshold && Math.abs(velocityY)>velocity_threshold){

                                    if(yDiff>0){
                                        textView.setText("Swipe Down");
                                    }
                                    else{
                                        textView.setText("Swipe up");
                                    }
                                    return true;

                                }
                            }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return false;
                }
            };
            //Initialize gesture direction
            gestureDetector = new GestureDetector(listener);
            //set listener on view
            view.setOnTouchListener(this);
        }
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent){
            return gestureDetector.onTouchEvent(motionEvent);
        }
    }
}