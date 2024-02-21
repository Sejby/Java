package pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {
    int up;
    int down;
    
    public Listener(int up, int down) {
        this.up = up;
        this.down = down;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == down) System.out.println(Thread.currentThread());
        if(e.getKeyCode() == up) System.out.println("Up");
        System.out.println(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
