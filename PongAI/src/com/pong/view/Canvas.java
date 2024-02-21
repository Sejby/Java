package com.pong.view;

import com.pong.logic.Logic;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    public Canvas() {
        super();
        setSize(800, 500);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(0, 0, getWidth()-1, getHeight()-1);
        Logic logic = Logic.GetInstance();
        g.setColor(Color.black);
        g.fillRect(logic.ball.position.x,    logic.ball.position.y,    logic.ball.size.width,    logic.ball.size.height);
        g.fillRect(logic.player1.position.x, logic.player1.position.y, logic.player1.size.width, logic.player1.size.height);
        g.fillRect(logic.player2.position.x, logic.player2.position.y, logic.player2.size.width, logic.player2.size.height);
        if (logic.prediction.drawable)
            g.fillRect(750, logic.prediction.position.y, 5, 5);
    }
}
