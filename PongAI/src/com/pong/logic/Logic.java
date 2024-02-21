package com.pong.logic;

import com.pong.ai.Controller;
import com.pong.view.Canvas;
import com.pong.view.View;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Logic {
    private static Logic instance;
    public static Logic GetInstance() {
        if (instance == null) instance = new Logic();
        return instance;
    }

    public Ball ball;
    public Ball prediction;
    public Player player1;
    public Player player2;

    public boolean isDownHeld;
    public boolean isUpHeld;
    public boolean canRun = false;

    private Random rand;

    private Logic() {
        var size = View.GetInstance().canvas.getSize();
        rand = new Random();
        ball = new Ball();
        prediction = new Ball();
        prediction.drawable = false;
        player1 = new Player();
        player2 = new Player();
        player1.position = new Point(10, ball.position.y-25);
        player2.position = new Point(size.width-25, ball.position.y-25);
        AWTEventListener listener = event -> {
            try {
                KeyEvent e = (KeyEvent)event;
                if(e.getID() == KeyEvent.KEY_PRESSED) {
                    if (e.getKeyCode() == 87 || e.getKeyCode() == 38) isUpHeld = true;
                    else
                    if (e.getKeyCode() == 83 || e.getKeyCode() == 40) isDownHeld = true;
                }
                if(e.getID() == KeyEvent.KEY_RELEASED) {
                    if (e.getKeyCode() == 87 || e.getKeyCode() == 38) isUpHeld = false;
                    else
                    if (e.getKeyCode() == 83 || e.getKeyCode() == 40) isDownHeld = false;
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        };
        Toolkit.getDefaultToolkit().addAWTEventListener(listener, AWTEvent.KEY_EVENT_MASK);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MoveLoop();
            }
        },0, 5);
    }

    private void MoveLoop() {
        if (!canRun) return;
        if (isUpHeld && player1.position.y > 0) player1.position.y--;
        if (isDownHeld && player1.position.y < View.GetInstance().canvas.getHeight() - player1.size.width) player1.position.y++;
        if (CheckCollision(player1)) {
            ball.dirX = true;
            ball.ChangeSpeed(new Point(1, rand.nextInt(2) + 1));
            //Controller.GetInstance().LearnFromPlayer();
        }
        if (CheckCollision(player2)) {
            ball.ChangeSpeed(new Point(1, rand.nextInt(2) + 1));
            ball.dirX = false;
        }

        ball.Move();
        CalcPrediction();
        RunNetwork();

        View.GetInstance().canvas.repaint();
    }

    private void RunNetwork() {
        Controller network = Controller.GetInstance();
        if (network.GetNet().equals("")) return;
        View view = View.GetInstance();
        Canvas canvas = view.canvas;
        double[][] in = { { (double) player2.position.y / (double) canvas.getHeight(), (double) CalcPrediction() / (double) canvas.getHeight() } };
        network.SetInput(in);
        double[][] out = network.GetOut();
        if (out[0][0] >= 0.5 && player2.position.y < canvas.getHeight() - player2.size.height) {
            player2.position.y += 1;
        }
        if (out[0][0] <= 0.5 && player2.position.y > 0) {
            player2.position.y -= 1;
        }
    }

    private boolean CheckCollision(Player collider) {
        if (!(collider.position.y <= ball.position.y + ball.size.height && collider.position.y + collider.size.height > ball.position.y)) return false;
        if (!(collider.position.x - 1 <= ball.position.x + ball.size.width && collider.position.x + collider.size.width + 1 >= ball.position.x)) return false;
        return true;
    }

    public int CalcPrediction() {
        int distanceX = (View.GetInstance().canvas.getWidth() - ball.position.x) / ball.speed.x;
        int distanceY;
        //System.out.println("Distance X - " + distanceX);
        if (ball.dirY) { //Going down
            distanceY = ball.position.y + distanceX / ball.speed.y;
            //System.out.println("Distance Y 0 - " + distanceY);
            prediction.position.y = distanceY;
            if (distanceY <= View.GetInstance().canvas.getHeight()) {
                //System.out.println("Distance Y 1 - " + distanceY);
                prediction.position.y = distanceY;
            } else {
                distanceY = View.GetInstance().canvas.getHeight() + (View.GetInstance().canvas.getHeight() - distanceY);
                //System.out.println("Distance Y 2 - " + distanceY);
                prediction.position.y = Math.abs(distanceY);
            }
        } else {
            distanceY = ball.position.y - distanceX / ball.speed.y;
            //System.out.println("Distance Y 3 - " + distanceY);
            prediction.position.y = Math.abs(distanceY);
        }
        View.GetInstance().canvas.repaint();
        return distanceY;
    }
}
