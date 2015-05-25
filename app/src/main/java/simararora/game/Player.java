package simararora.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Simar Arora on 23-05-2015.
 */
public class Player extends GameObject {

    private Bitmap spriteSheet;
    private int score;
    private double dya;
    private boolean up;
    private boolean playing;
    private Animation animations = new Animation();
    private long startTime;

    public Player(Bitmap src, int width, int height, int numFrames) {
        x = 100;
        y = GamePanel.HEIGHT / 2;
        dy = 0;
        score = 0;
        this.height = height;
        this.width = width;
        spriteSheet = src;

        Bitmap[] image = new Bitmap[numFrames];

        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(spriteSheet, i * width, 0, width, height);
        }

        animations.setFrames(image);
        animations.setDelay(10);
        startTime = System.nanoTime();
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void update() {
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if (elapsed > 100) {
            score++;
            startTime = System.nanoTime();
        }
        animations.update();

        if (up) {
            dy = (int) (dya -= 1.1);
        } else {
            dy = (int) (dya += 1.1);
        }

        if (dy > 14) dy = 14;
        if (dy < -14) dy = -14;

        y += dy * 2;
        dya = 0;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(animations.getImage(), x, y, null);
    }

    public int getScore() {
        return score;
    }

    public boolean getPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void resetDYA() {
        dya = 0;
    }
}

