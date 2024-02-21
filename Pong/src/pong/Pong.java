package pong;

import javax.swing.SwingUtilities;

public class Pong {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View gamer = new View();
                gamer.setVisible(true);
            }
        });
    }
}
