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

//    @SuppressLint("WrongViewCast")
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
    public static class AllAppsActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_all_apps);
            // Add your code for showing all apps here
        }
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
                @SuppressLint("SetTextI18n")
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    float xDiff = e2.getX() - e1.getX();
                    float yDiff = e1.getY() - e2.getY();
                    try {
                        if (Math.abs(xDiff) > Math.abs(yDiff)) {
                            // horizontal swipe, handle as before
                        } else {
                            // vertical swipe
                            if (Math.abs(yDiff) > threshold && Math.abs(velocityY) > velocity_threshold) {
                                if (yDiff > 0) {
                                    // swipe down
                                    textView.setText("Swipe down");git

                                } else {
                                    // swipe up, launch AllAppsActivity
                                    Intent intent = new Intent(MainActivity.this, AllAppsActivity.class);
                                    startActivity(intent);
                                }
                                return true;
                            }
                        }
                    } catch (Exception e) {
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
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent){
            return gestureDetector.onTouchEvent(motionEvent);
        }
    }
}