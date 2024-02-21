package Karty;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class ControlerKaret {
    public Map<String, BufferedImage> collection;
    
    
    private ControlerKaret() {
        this.collection = new HashMap<>();
        try {
            collection.put("error", ImageIO.read(getClass().getClassLoader().getResourceAsStream("Karty/error.png")));
        } catch (IOException ex) {
            System.out.println("Error loading error image exiting.");
            System.exit(1);
        }
    }
    
    public BufferedImage getDefault() {
        return collection.get("error");
    }
    
    public BufferedImage getCard(String name) {
        if (collection.get(name) == null) {
            try {
                collection.put(name, ImageIO.read(getClass().getClassLoader().getResourceAsStream("Karty/" + name + ".png")));
            } catch (IOException|IllegalArgumentException ex) {
                System.out.println("Error loading -> " + "Karty/" + name + ".png");
                return collection.get("error");
            }
        }
        return collection.get(name);
    }
    
    
    
    
    private static ControlerKaret instance;
    public static ControlerKaret getInstance() {
        if (instance == null) instance = new ControlerKaret();
        return instance;
    }
}
