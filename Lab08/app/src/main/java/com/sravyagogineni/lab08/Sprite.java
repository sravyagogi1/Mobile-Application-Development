package com.sravyagogineni.lab08;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Rect;
import android.graphics.Canvas;


class Sprite extends RectF {

    private static final int DOWN = 0, LEFT = 1, RIGHT = 2, UP = 3;
    private int dX, dY;
    private int direction = DOWN;
    private Bitmap bitmap;
    private boolean isSpriteSheet = false;
    private int bmpCols = 1, bmpRows = 1;
    private int currentFrame = 0;
    private int tick = 0;
    private int color = Color.RED;

    public Sprite(float left, float top, float right, float bottom, int dX, int dY, int color) {
        super(left, top, right, bottom);
        this.dX = dX;
        this.dY = dY;
        this.color = color;
    }

    public void setVelocity(int dx, int dy) {
        this.dX = dx;
        this.dY = dy;
        if (dx > 0) direction = RIGHT;
        else if (dx < 0) direction = LEFT;
        else if (dy > 0) direction = DOWN;
        else if (dy < 0) direction = UP;
    }


    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.isSpriteSheet = false;
        this.bmpCols = 1;
        this.bmpRows = 1;
        this.currentFrame = 0;
    }

    public void setSpriteSheet(Bitmap sheet, int cols, int rows) {
        this.bitmap = sheet;
        this.isSpriteSheet = true;
        this.bmpCols = cols;
        this.bmpRows = rows;
        this.currentFrame = 0;
    }

    public void update(int screenW, int screenH) {
        offset(dX, dY);

        // bounce off walls
        if (left < 0 || right > screenW) dX *= -1;
        if (top < 0 || bottom > screenH) dY *= -1;
        if (left < 0) offsetTo(0, top);
        if (top < 0) offsetTo(left, 0);
        if (right > screenW) offsetTo(screenW - width(), top);
        if (bottom > screenH) offsetTo(left, screenH - height());
    }

    public void draw(Canvas canvas) {
        if (bitmap == null) {
            Paint p = new Paint();
            p.setColor(color);
            canvas.drawCircle(centerX(), centerY(), width() / 2f, p);
            return;
        }

        if (!isSpriteSheet) {
            canvas.drawBitmap(bitmap, null, this, null);
            return;
        }

        int frameW = bitmap.getWidth() / bmpCols;
        int frameH = bitmap.getHeight() / bmpRows;

        int srcX = currentFrame * frameW;
        int srcY = direction * frameH; // row based on direction

        Rect src = new Rect(srcX, srcY, srcX + frameW, srcY + frameH);
        canvas.drawBitmap(bitmap, src, this, null);

        // animate only while moving
        if (dX != 0 || dY != 0) {
            tick++;
            if (tick % 6 == 0) {
                currentFrame = (currentFrame + 1) % bmpCols;
            }
        } else {
            currentFrame = 0;
        }
    }
}