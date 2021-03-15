package com.example.ondrow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyView extends View {
    private ArrayList<MyFigure> figures = new ArrayList<MyFigure>();
    private MyFigure selectedFigure = null;
    private Paint textPaint = new Paint();

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(50);

        this.createFig(550, 800);
//        figures.add(new MyFigure(150, 500));
    }

    public void createFig(float x, float y) {
        figures.add(new MyFigure(x, y));
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.setBackgroundColor(Color.BLUE);
        for (MyFigure fig : figures) {
            fig.drawFigure(canvas);
        }
        String[] text;
        if (this.haveSelectedFigure()) {
            text = new String[]{"You can choose new position", "for selected bear"};
        } else {
            text = new String[]{"Touch on free space", "to create new bear", "or you can touch on existing bear", "to select him"};
        }
        int x = 200, y = 240;
        for (String str: text) {
            canvas.drawText(str, x, y, textPaint);
            y += 45;
        }
        this.invalidate();
    }

    public MyFigure getFigureUnderXY(float tx, float ty) {
        for (MyFigure fig: figures) {
            if (fig.isFigureUnderXY(tx, ty)) {
                return fig;
            }
        }
        return null;
    }

    public void setSelectedFigure(MyFigure fig) {
        this.selectedFigure = fig;
        fig.setSelected();
    }

    public boolean haveSelectedFigure() {
        if (this.selectedFigure != null) {
            return true;
        }
        return false;
    }

    public void moveSelectedFigureToXY(float tx, float ty) {
        this.selectedFigure.moveToXY(tx, ty);
        selectedFigure.setUnelected();
        this.selectedFigure = null;
    }
}