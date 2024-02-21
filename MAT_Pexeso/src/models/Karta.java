package models;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.imageio.ImageIO;

public class Karta {

    String faceUp;
    String faceDown;
    Boolean hidden;
    Boolean odstraneno = false;

    public Karta(String faceUp) {
        hidden = true;
        faceDown = new Random().nextBoolean() ? "../resources/back1.png" : "../resources/back2.png";
        this.faceUp = faceUp;
    }

    public void vykresliSe(Graphics g, int posx, int posy) {
        if (!odstraneno) {
            BufferedImage img;
            try {
                InputStream in;
                if (!hidden) {
                    in = getClass().getResourceAsStream(faceUp);
                } else {
                    in = getClass().getResourceAsStream(faceDown);
                }
                img = ImageIO.read(in);
                g.drawImage(img, posx * 77, posy * 77, null);
            } catch (IOException e) {

            }
        }
    }

    public String getFaceUp() {
        return faceUp;
    }

    public void odstranSe() {
        odstraneno = true;
    }

    public void setFaceUp(String faceUp) {
        this.faceUp = faceUp;
    }

    public String getFaceDown() {
        return faceDown;
    }

    public void setFaceDown(String faceDown) {
        this.faceDown = faceDown;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean isHidden() {
        return hidden;
    }
}
