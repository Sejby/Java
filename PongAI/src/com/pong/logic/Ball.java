package com.pong.logic;

import com.pong.view.View;

import java.awt.*;
import java.util.Random;

public class Ball {
    public Point position;
    public Dimension size;
    public Point speed;
    public boolean dirX;
    public boolean dirY;
    public boolean drawable;

    private final Random rand;

    public Ball() {
        rand = new Random();
        size = new Dimension(10,10);
        speed = new Point(1, 1);
        drawable = true;
        Reset();
    }

    public void Reset() {
        Dimension size = View.GetInstance().canvas.getSize();
        position = new Point(size.width/2, size.height/2);
        dirX = rand.nextBoolean();
        dirY = rand.nextBoolean();
    }
    public void Reset(boolean dirX) {
        Dimension size = View.GetInstance().canvas.getSize();
        position = new Point(size.width/2, size.height/2);
        this.dirX = dirX;
        dirY = rand.nextBoolean();
    }
    public void Move() {
        position.x += dirX ? 1 : -1;
        position.y += dirY ? 1 : -1;
        if (position.y <= 0) dirY = true;
        else
        if (position.y >= View.GetInstance().canvas.getHeight()) dirY = false;
        if (position.x <= 0) Reset(true);
        if (position.x >= View.GetInstance().canvas.getWidth()) Reset(false);
    }
    public void ChangeSpeed(Point speed) {
        this.speed = speed;
    }
}
