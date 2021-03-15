package com.example.ondrow;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface Figure {
    float fx=0, fy=0;

    void moveVector(float vx, float vy);
    void draw(Canvas canvas, Paint paint);
    boolean isUnderXY(float x, float y);
}