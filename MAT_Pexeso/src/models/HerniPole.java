package models;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HerniPole {

    private static HerniPole single_instance = null;
    String[] faceUpArr = {"../resources/chaos.png", "../resources/fractal.png", "../resources/lesotho.png", "../resources/palestina.png", "../resources/somaliland.png", "../resources/timor.png", "../resources/ring.png", "../resources/court.png"};
    Color[] playerColor = {Color.GREEN, Color.ORANGE, Color.RED, Color.WHITE};
    ArrayList<Karta> karty = new ArrayList<>();
    int pocetParu = 4;
    int pocetHracu = 2;
    int[] skore;
    int kdoJeNaTahu = 0;
    boolean prvniPick = false;
    boolean tretiPick = false;

    public HerniPole() {
        for (int i = 0; i < pocetParu; i++) {
            for (int j = 0; j < 2; j++) {
                karty.add(new Karta(faceUpArr[i]));
            }
        }
        skore = new int[pocetHracu];
        Arrays.fill(skore, 0);
        Collections.shuffle(karty);
    }

    public void vyhodnotSe() {
        boolean majiBytOdstraneny = false;
        if (prvniPick && tretiPick) {
            String prvniCesta = "trash";
            for (Karta karta : karty) {
                if (!karta.isHidden()) {
                    if (prvniCesta == karta.getFaceUp()) {
                        skore[kdoJeNaTahu]++;
                        majiBytOdstraneny = true;
                    } else {
                        prvniCesta = karta.getFaceUp();
                    }
                }
            }
            for (Karta karta : karty) {
                if (majiBytOdstraneny && !karta.isHidden()) {
                    karta.odstranSe();
                }
                karta.setHidden(true);
            }
            if (!majiBytOdstraneny) {
                kdoJeNaTahu++;
                kdoJeNaTahu = kdoJeNaTahu % pocetHracu;
            }
            majiBytOdstraneny = false;
            prvniPick = false;
            tretiPick = false;
        } else {
            if (prvniPick) {
                tretiPick = true;
            }
            prvniPick = true;
        }
    }

    public void obratSe(int pozice) {
        if (pozice >= karty.size() || tretiPick) {
            return;
        }
        karty.get(pozice).setHidden(false);
    }

    public void vykresliVse(Graphics g) {
        for (Karta karta : karty) {
            int posx = (int) (karty.indexOf(karta) / 4);
            int posy = karty.indexOf(karta) % 4;
            karta.vykresliSe(g, posx, posy);
        }
        for (int i = 0; i < skore.length; i++) {
            g.setColor(playerColor[i]);
            g.drawString("Player" + Integer.toString(i + 1), 350, i * 50 + 50);
            g.drawString(Integer.toString(skore[i]), 350, i * 50 + 75);
        }
    }

    public static HerniPole getInstance() {
        if (single_instance == null) {
            single_instance = new HerniPole();
        }
        return single_instance;
    }

    public ArrayList<Karta> getKarty() {
        return karty;
    }

    public void setKarty(ArrayList<Karta> karty) {
        this.karty = karty;
    }

    public int getPocetParu() {
        return pocetParu;
    }

    public void setPocetParu(int pocetParu) {
        karty = new ArrayList<Karta>();
        kdoJeNaTahu = 0;
        prvniPick = false;
        tretiPick = false;
        this.pocetParu = pocetParu;
        for (int i = 0; i < pocetParu; i++) {
            for (int j = 0; j < 2; j++) {
                karty.add(new Karta(faceUpArr[i]));
            }
        }
        skore = new int[pocetHracu];
        Arrays.fill(skore, 0);
        Collections.shuffle(karty);
    }

    public void setPocetHracu(int pocetHracu) {
        karty = new ArrayList<Karta>();
        this.pocetHracu = pocetHracu;
        kdoJeNaTahu = 0;
        prvniPick = false;
        tretiPick = false;
        for (int i = 0; i < pocetParu; i++) {
            for (int j = 0; j < 2; j++) {
                karty.add(new Karta(faceUpArr[i]));
            }
        }
        skore = new int[pocetHracu];
        Arrays.fill(skore, 0);
        Collections.shuffle(karty);
    }

}
