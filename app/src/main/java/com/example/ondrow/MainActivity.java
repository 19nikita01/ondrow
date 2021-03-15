package com.example.ondrow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView view = new MyView(this, null);
        view.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MyView ev = (MyView) v;
                float tx = event.getX();
                float ty = event.getY();
//                Log.d("lkj_onTouch", "onTouch: event: " + event);
                if (ev.haveSelectedFigure()) {
                    ev.moveSelectedFigureToXY(tx, ty);
                } else {
                    MyFigure figUnderXY = ev.getFigureUnderXY(tx, ty);
                    if (figUnderXY != null) {
                        ev.setSelectedFigure(figUnderXY);
                    } else {
                        ev.createFig(tx, ty);
                    }
                }
                return false;
            }
        });

        setContentView(view);
    }
}