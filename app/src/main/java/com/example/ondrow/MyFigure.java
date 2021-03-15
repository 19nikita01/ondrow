package com.example.ondrow;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MyFigure {

    private float fx, fy;

    private Figure[] bodyParts;
    private Figure[] eyesParts;

    private boolean moveFlag = false;
    private float moveStartX, moveStartY;
    private float moveFinishX, moveFinishY;
    private float moveStepVectorX, moveStepVectorY;
    private final int MOVE_TIME = 100;
    private int moveStepsPassed;

    private Paint bodyPaint = new Paint();
    private Paint eyesPaint = new Paint();
    private Paint donePathPaint = new Paint();
    private Paint pathPaint = new Paint();

    public MyFigure(float x, float y) {
        this.fx = x;
        this.fy = y;

        Circle cb = new Circle(x, y, 80);
        Circle cr = new Circle((float) (cb.fx + (Math.sqrt(2) * cb.r / 2)), (float) (cb.fy - (Math.sqrt(2) * cb.r / 2)), cb.r / 2);
        Circle cl = new Circle((float) (cb.fx - (Math.sqrt(2) * cb.r / 2)), (float) (cb.fy - (Math.sqrt(2) * cb.r / 2)), cb.r / 2);

        Circle er = new Circle((float) (cb.fx + (Math.sqrt(2) * cb.r / 2)/2), (float) (cb.fy - (Math.sqrt(2) * cb.r / 2)/2), cb.r / 5);
        Circle el = new Circle((float) (cb.fx - (Math.sqrt(2) * cb.r / 2)/2), (float) (cb.fy - (Math.sqrt(2) * cb.r / 2)/2), cb.r / 5);
        bodyParts = new Figure[]{cb, cr, cl};
        eyesParts = new Figure[]{er, el};
        bodyPaint.setColor(Color.rgb(110, 87, 63));
        bodyPaint.setStyle(Paint.Style.FILL);
        eyesPaint.setColor(Color.YELLOW);
        eyesPaint.setStyle(Paint.Style.FILL);
        donePathPaint.setColor(Color.BLACK);

        pathPaint.setColor(Color.BLUE);

    }

    public void drawFigure(Canvas canvas) {
        if (moveFlag) {
            this.moveFigureVector(moveStepVectorX, moveStepVectorY);
            canvas.drawLine(moveStartX, moveStartY, fx, fy, donePathPaint);
            canvas.drawLine(fx, fy, moveFinishX, moveFinishY, pathPaint);
            if (moveStepsPassed >= MOVE_TIME) {
                moveFlag = false;
            }
            moveStepsPassed++;
        }
        for (Figure fig: bodyParts) {
            fig.draw(canvas, bodyPaint);
        }
        for (Figure fig: eyesParts) {
            fig.draw(canvas, eyesPaint);
        }
    }

    public boolean isFigureUnderXY(float tx, float ty) {
        for (Figure fig: bodyParts) {
            if (fig.isUnderXY(tx, ty)) {
                return true;
            }
        }
        return false;
    }

    public void moveToXY(float tx, float ty) {
        moveStepsPassed = 0;
        moveStartX = fx;
        moveStartY = fy;
        moveFinishX = tx;
        moveFinishY = ty;
        moveStepVectorX = (tx - fx)/MOVE_TIME;
        moveStepVectorY = (ty - fy)/MOVE_TIME;
        moveFlag = true;

    }

    public void setSelected() {
        bodyPaint.setColor(Color.RED);
    }

    public void setUnelected() {
        bodyPaint.setColor(Color.rgb(110, 87, 63));
    }

    private void moveFigureVector(float vx, float vy) {
        for (Figure fig: bodyParts) {
            fig.moveVector(vx, vy);
        }
        for (Figure fig: eyesParts) {
            fig.moveVector(vx, vy);
        }
        fx += moveStepVectorX;
        fy += moveStepVectorY;
    }
}