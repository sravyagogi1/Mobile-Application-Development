package com.sravyagogineni.lab08;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.media.MediaPlayer;

public class DrawView extends View {

    private Bitmap greenOrn, redOrn, presentImg;

    private Paint floorPaint, rugPaint, windowBlue, windowFrame,
            snowPaint, treePaint, trunkPaint, starPaint;

    private static final float ORN_SCALE = 0.125f;
    private static final float PRES_SCALE = 0.25f;

    private List<Float[]> ornamentPositions = new ArrayList<>();
    private List<Float[]> presentPositions = new ArrayList<>();

    private int ornamentIndex = 0;
    private int presentIndex = 0;
    private final int MAX_ORNAMENTS = 6;
    private final int MAX_PRESENTS = 7;

    private float ornX, ornY;
    private float ornDX = 8f, ornDY = 6f;
    private float presX, presY;
    private float presDX = 10f;

    private int ornW, ornH, presW, presH;
    private Runnable animator;
    private Sprite sprite1, sprite2, hero;
    private Bitmap[] ornamentCycle;
    private int ornamentCycleIndex = 0;
    private RectF starRect;
    private RectF leftWindowRect;
    private RectF rightWindowRect;

    private MediaPlayer allIWantPlayer;
    private MediaPlayer jinglePlayer;
    private MediaPlayer underneathPlayer;
    private boolean collisionPlaying = false;
    private Bitmap heroSheet;
    private int heroSpeed = 12;



    private Random rand = new Random();

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        floorPaint = new Paint(); floorPaint.setColor(0xFF9A6131);
        rugPaint = new Paint(); rugPaint.setColor(0xFF8c1825);
        windowBlue = new Paint(); windowBlue.setColor(0xFF3f5278);
        windowFrame = new Paint(); windowFrame.setColor(0xFFbe5a2c);
        snowPaint = new Paint(); snowPaint.setColor(0xFFFFFFFF);
        treePaint = new Paint(); treePaint.setColor(0xFF508923);
        trunkPaint = new Paint(); trunkPaint.setColor(0xFF492d17);
        starPaint = new Paint(); starPaint.setColor(0xFFffbe4a);

        greenOrn = BitmapFactory.decodeResource(getResources(), R.drawable.greenornament);
        redOrn = BitmapFactory.decodeResource(getResources(), R.drawable.redornament);

        Bitmap blueOrn = BitmapFactory.decodeResource(getResources(), R.drawable.blueornament);
        Bitmap yellowOrn = BitmapFactory.decodeResource(getResources(), R.drawable.yellowornament);
        Bitmap silverOrn = BitmapFactory.decodeResource(getResources(), R.drawable.silverornament);
        presentImg = BitmapFactory.decodeResource(getResources(), R.drawable.redpresent);

        ornamentCycle = new Bitmap[]{greenOrn,blueOrn,yellowOrn,silverOrn,redOrn};

        sprite1 = new Sprite(100, 100, 280, 300, 6, 4, Color.BLACK);
        sprite2 = new Sprite(400, 400, 560, 600, -5, 7, Color.WHITE);
        sprite1.setBitmap(ornamentCycle[0]);
        sprite2.setBitmap(redOrn);

        heroSheet = BitmapFactory.decodeResource(getResources(), R.drawable.spritesheet);
        hero = new Sprite(300, 900, 420, 1020, 0, 0, Color.TRANSPARENT);
        hero.setSpriteSheet(heroSheet, 4, 4);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();


        allIWantPlayer = MediaPlayer.create(context, R.raw.alliwant);
        jinglePlayer = MediaPlayer.create(context, R.raw.jinglebellrock);
        underneathPlayer = MediaPlayer.create(context, R.raw.underneaththetree);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        ornW = (int)(redOrn.getWidth() * ORN_SCALE);
        ornH = (int)(redOrn.getHeight() * ORN_SCALE);

        presW = (int)(presentImg.getWidth() * PRES_SCALE);
        presH = (int)(presentImg.getHeight() * PRES_SCALE);

        ornX = w / 2f;
        ornY = h / 3f;

        presX = -presW;
        presY = h * 0.78f;

        animator = () -> {
            updateAnimation();
            invalidate();
            postDelayed(animator, 16);
        };
        post(animator);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int w = getWidth();
        int h = getHeight();

        float winSize = w * 0.30f;
        leftWindowRect  = new RectF(w * 0.08f, h * 0.18f, w * 0.08f + winSize, h * 0.18f + winSize);
        rightWindowRect = new RectF(w - w * 0.08f - winSize, h * 0.18f, w - w * 0.08f, h * 0.18f + winSize);

        float starCX = w / 2f;
        float starCY = h * 0.15f - w * 0.025f;
        float starSize = w * 0.10f;
        starRect = new RectF(starCX - starSize/2f, starCY - starSize/2f, starCX + starSize/2f, starCY + starSize/2f);

        float floorTop = h * 0.75f;
        canvas.drawRect(0, floorTop, w, h, floorPaint);

        // Rug
        Path rug = new Path();
        rug.moveTo(w * 0.19f, floorTop + h * 0.12f);
        rug.lineTo(w * 0.81f, floorTop + h * 0.12f);
        rug.lineTo(w * 0.95f, h);
        rug.lineTo(w * 0.05f, h);
        rug.close();
        canvas.drawPath(rug, rugPaint);

        // Windows
        drawWindow(canvas, w * 0.08f, h * 0.18f, winSize);
        drawWindow(canvas, w - w * 0.08f - winSize, h * 0.18f, winSize);

        // Tree
        float cx = w / 2f;
        float topY = h * 0.15f;

        drawTriangle(canvas, cx, topY, w * 0.45f);
        drawTriangle(canvas, cx, topY + w * 0.20f, w * 0.55f);
        drawTriangle(canvas, cx, topY + w * 0.38f, w * 0.65f);

        float trunkWidth = w * 0.15f;
        float trunkHeight = h * 0.10f;
        canvas.drawRect(
                cx - trunkWidth / 2f,
                floorTop - trunkHeight,
                cx + trunkWidth / 2f,
                floorTop,
                trunkPaint
        );

        drawStar(canvas, cx, topY - w * 0.025f, w * 0.10f);

        // Button-added ornaments
        for (int i = 0; i < ornamentIndex; i++) {
            Float[] p = ornamentPositions.get(i);
            Bitmap bmp = (i % 2 == 0) ? greenOrn : redOrn;
            canvas.drawBitmap(
                    Bitmap.createScaledBitmap(bmp, ornW, ornH, true),
                    p[0], p[1], null
            );
        }

        // Button-added presents
        for (int i = 0; i < presentIndex; i++) {
            Float[] p = presentPositions.get(i);
            canvas.drawBitmap(
                    Bitmap.createScaledBitmap(presentImg, presW, presH, true),
                    p[0], p[1], null
            );
        }

        canvas.drawBitmap(
                Bitmap.createScaledBitmap(presentImg, presW, presH, true),
                presX, presY, null
        );

        sprite1.update(w,h);
        sprite2.update(w,h);
        hero.update(w,h);

        if (RectF.intersects(sprite1,sprite2)) {
            if (!collisionPlaying) {
                playMusic(jinglePlayer);
                collisionPlaying = true;
            }
            sprite2.offsetTo(50, 50);
        } else {
            collisionPlaying = false;
        }

        sprite1.draw(canvas);
        sprite2.draw(canvas);
        hero.draw(canvas);

        invalidate();
    }

    private void updateAnimation() {
        int w = getWidth();
        int h = (int)(getHeight() * 0.80f);

//        ornX += ornDX;
//        ornY += ornDY;
//
//        if (ornX <= 0 || ornX + ornW >= w) ornDX *= -1;
//        if (ornY <= 0 || ornY + ornH >= h) ornDY *= -1;

        presX += presDX;
        if (presX > w) presX = -presW;
    }

    public void addOrnament() {
        if (ornamentIndex >= MAX_ORNAMENTS) return;

        int w = getWidth();
        int h = getHeight();

        float x = w * 0.30f + rand.nextFloat() * (w * 0.40f);
        float y = h * 0.22f + rand.nextFloat() * (h * 0.40f);

        ornamentPositions.add(new Float[]{x, y});
        ornamentIndex++;
        invalidate();
    }

    public void addPresent() {
        if (presentIndex >= MAX_PRESENTS) return;

        float floorTop = getHeight() * 0.75f;
        float x = getWidth() * 0.35f + presentIndex * (presW + 15);
        float y = floorTop - presH;

        presentPositions.add(new Float[]{x, y});
        presentIndex++;
        invalidate();
    }

    public void removeAll() {
        ornamentPositions.clear();
        presentPositions.clear();
        ornamentIndex = 0;
        presentIndex = 0;
        invalidate();
    }

    private void drawTriangle(Canvas c, float cx, float y, float width) {
        Path p = new Path();
        p.moveTo(cx, y);
        p.lineTo(cx - width / 2f, y + width);
        p.lineTo(cx + width / 2f, y + width);
        p.close();
        c.drawPath(p, treePaint);
    }

    private void drawStar(Canvas c, float cx, float cy, float s) {
        Path star = new Path();
        star.moveTo(cx, cy - s / 2f);
        star.lineTo(cx + s / 6f, cy - s / 6f);
        star.lineTo(cx + s / 2f, cy - s / 6f);
        star.lineTo(cx + s / 4f, cy + s / 8f);
        star.lineTo(cx + s / 3f, cy + s / 2f);
        star.lineTo(cx, cy + s / 3f);
        star.lineTo(cx - s / 3f, cy + s / 2f);
        star.lineTo(cx - s / 4f, cy + s / 8f);
        star.lineTo(cx - s / 2f, cy - s / 6f);
        star.lineTo(cx - s / 6f, cy - s / 6f);
        star.close();
        c.drawPath(star, starPaint);
    }

    private void drawWindow(Canvas c, float x, float y, float size) {
        float t = size * 0.08f;
        c.drawRect(x, y, x + size, y + size, windowFrame);
        c.drawRect(x + t, y + t, x + size - t, y + size - t, windowBlue);
    }

    @Override
    protected void onDetachedFromWindow() {
        removeCallbacks(animator);
        super.onDetachedFromWindow();

        if (allIWantPlayer != null) {
            allIWantPlayer.release();
            allIWantPlayer = null;
        }

        if (jinglePlayer != null) {
            jinglePlayer.release();
            jinglePlayer = null;
        }

        if (underneathPlayer != null) {
            underneathPlayer.release();
            underneathPlayer = null;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();

            if (sprite1.contains(x, y)) {
                ornamentCycleIndex++;
                ornamentCycleIndex %= ornamentCycle.length;
                sprite1.setBitmap(ornamentCycle[ornamentCycleIndex]);
            }

            else if (sprite2.contains(x, y)) {
                sprite2.offsetTo(300, 300);
            }

            else if (starRect != null && starRect.contains(x, y)) {
                playMusic(allIWantPlayer);
            }

            else if (leftWindowRect != null && leftWindowRect.contains(x, y)){
                playMusic(jinglePlayer);
            }

            else if (rightWindowRect != null && rightWindowRect.contains(x, y)){
                playMusic(underneathPlayer);
            }
        }
        return true;
    }

    private void playMusic(MediaPlayer player) {
        if (allIWantPlayer.isPlaying()) allIWantPlayer.pause();
        if (jinglePlayer.isPlaying()) jinglePlayer.pause();
        if (underneathPlayer.isPlaying()) underneathPlayer.pause();

        player.seekTo(0);
        player.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        switch (keyCode) {
            case android.view.KeyEvent.KEYCODE_DPAD_LEFT:
                hero.setVelocity(-heroSpeed, 0);
                return true;
            case android.view.KeyEvent.KEYCODE_DPAD_RIGHT:
                hero.setVelocity(heroSpeed, 0);
                return true;
            case android.view.KeyEvent.KEYCODE_DPAD_UP:
                hero.setVelocity(0, -heroSpeed);
                return true;
            case android.view.KeyEvent.KEYCODE_DPAD_DOWN:
                hero.setVelocity(0, heroSpeed);
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        switch (keyCode) {
            case android.view.KeyEvent.KEYCODE_DPAD_LEFT:
            case android.view.KeyEvent.KEYCODE_DPAD_RIGHT:
            case android.view.KeyEvent.KEYCODE_DPAD_UP:
            case android.view.KeyEvent.KEYCODE_DPAD_DOWN:
                hero.setVelocity(0, 0);
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }


}