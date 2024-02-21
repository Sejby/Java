package pexeso;

import models.HerniPole;
import views.Game;
import views.Settings;
import views.View;

public class Pexeso {
    public static HerniPole pole = HerniPole.getInstance();
    public static View view = new View();
    public static Game game = new Game();
    public static Settings settings = new Settings();
    public static void main(String[] args) {
        view.setVisible(true);
        game.setVisible(false);
        settings.setVisible(false);
    }
    
}
