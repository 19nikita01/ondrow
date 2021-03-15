package com.example.ondrow;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle implements Figure{
    public float fx, fy, r;

    public Circle() {
    }

    public Circle(float x0, float y0, float r) {
        this.fx = x0;
        this.fy = y0;
        this.r = r;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(fx, fy, r, paint);
    }

    public void setCenter(float x, float y) {
        this.fx = x;
        this.fy = y;
    }

    public boolean isUnderXY(float x, float y) {
        if ((x - fx)*(x - fx) + (y - fy)*(y - fy) < r*r) {
            return true;
        }
        return false;
    }

    @Override
    public void moveVector(float vx, float vy) {
        this.fx += vx;
        this.fy += vy;
    }
}