package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;

public class GameModel {
    int[][] herniPole;
    int velikostPlatna;
    public GameModel(int velikostPlatna) {
        this.velikostPlatna = velikostPlatna;
        herniPole = new int[4][4];
        generujCislo();
        generujCislo();
    }
    
    public void shiftArray(Smer smer) {
        int x;
        for (int i = 0; i < herniPole.length; i++) {
            for (int j = 0; j < herniPole[0].length; j++) {
                if(herniPole[i][j] == 0) {
                    switch(smer) {
                        case DOPRAVA:
                            x = j;
                            while(x >= 0) {
                                if(x-1 >= 0) {
                                  herniPole[i][x] = herniPole[i][x-1];
                                  herniPole[i][x-1] = 0;
                                }
                                x--;
                            }
                            break;
                        case DOLEVA:
                            x = j;
                            while(x < herniPole[0].length) {
                                while(x < herniPole[0].length) {
                                    if(x+1 < herniPole[0].length) {
                                        herniPole[i][x] = herniPole[i][x+1];
                                        herniPole[i][x+1] = 0;
                                    }
                                    x++;
                                }
                            }
                            break;
                        case NAHORU:
                            x = i;
                            while(x < herniPole.length) {
                                if(x+1 < herniPole.length) {
                                    herniPole[x][j] = herniPole[x+1][j];
                                    herniPole[x+1][j] = 0;
                                }
                                x++;
                            }
                            break;
                        case DOLU:
                            x = i;
                            while(x >= 0) {
                                if(x-1 >= 0) {
                                  herniPole[x][j] = herniPole[x-1][j];
                                  herniPole[x-1][j] = 0;
                                }
                                x--;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
    
    public void provedKrok(Smer smer) {
        int[][] oldArray = new int[4][];
        for (int i = 0; i < herniPole.length; i++) {
            oldArray[i] = Arrays.copyOf(herniPole[i], herniPole[0].length);
        }
        shiftArray(smer);
        shiftArray(smer);
        
        int x;
        for (int i = 0; i < herniPole.length; i++) {
            switch(smer) {
                case DOPRAVA:
                    x = herniPole[0].length-1;
                    while(x >= 0) {
                        if(x+1 < herniPole[0].length && herniPole[i][x] == herniPole[i][x+1]) {
                            herniPole[i][x+1] = 2*herniPole[i][x+1];
                            herniPole[i][x] = 0;
                            if(x-2 >= 0){
                                x-=2;
                            }else {
                                x-=1;
                            }
                        }else {
                            x-=1;
                        }
                    }
                    break;
                case DOLEVA:
                    x = 0;
                    while(x < herniPole[0].length) {
                        if(x-1 >= 0 && herniPole[i][x] == herniPole[i][x-1]) {
                            herniPole[i][x-1] = 2*herniPole[i][x-1];
                            herniPole[i][x] = 0;
                            if(x+2 < herniPole[0].length){
                                x+=2;
                            }else {
                                x+=1;
                            }
                        }else {
                            x+=1;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        for (int j = 0; j < herniPole[0].length; j++) {
            switch(smer) {
                case NAHORU:
                    x = 0;
                    while(x < herniPole.length) {
                        if(x-1 >= 0 && herniPole[x][j] == herniPole[x-1][j]) {
                            herniPole[x-1][j] = 2*herniPole[x-1][j];
                            herniPole[x][j] = 0;
                            if(x+2 < herniPole.length){
                                x+=2;
                            }else {
                                x+=1;
                            }
                        }else {
                            x+=1;
                        }
                    }
                    break;
                case DOLU:
                    x = herniPole.length-1;
                    while(x >= 0) {
                        if(x+1 < herniPole.length && herniPole[x][j] == herniPole[x+1][j]) {
                            herniPole[x+1][j] = 2*herniPole[x+1][j];
                            herniPole[x][j] = 0;
                            if(x-2 >= 0){
                                x-=2;
                            }else {
                                x-=1;
                            }
                        }else {
                            x-=1;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        shiftArray(smer);
        if(!areArraysSame(herniPole, oldArray)) {
            generujCislo();
        }
            
    }
    public static boolean areArraysSame(int[][] arr1, int[][] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if(!Arrays.equals(arr1[i], arr2[i])) {
                return false;
            }
        }
        return true;
    }
    public void generujCislo() {
        Random r = new Random();
        int random = r.nextInt(10);
        int randomX = r.nextInt(4);
        int randomY = r.nextInt(4);
        while(herniPole[randomX][randomY] != 0) {
            randomX = r.nextInt(4);
            randomY = r.nextInt(4);
        }
        if (random < 9) {
            herniPole[randomX][randomY] = 2;
        }else {
            herniPole[randomX][randomY] = 4;    
        }
    }
    public void vykresliSe(Graphics g) {
        String[] barvyPoli = {"null","#eee4da","#ede0c8","#f2b179","#f59563","#f67c5f","#f65e3b","#edcf72","#edcc61","#edc850","#edc53f","#edc22e"};
        
        for (int i = 0; i < herniPole.length; i++) {
            for (int j = 0; j < herniPole[0].length; j++) {
                if(herniPole[j][i] != 0) {
                    int index = (int)(Math.log(herniPole[j][i]) / Math.log(2));
                    g.setColor(Color.decode(barvyPoli[index]));
                    g.fillRect(i*velikostPlatna/4, j*velikostPlatna/4, velikostPlatna/4, velikostPlatna/4);
                    g.setColor(Color.decode("#FAF8EF"));
                    g.setFont(new Font("Arial",Font.BOLD,24));
                    FontMetrics metrics = g.getFontMetrics(g.getFont());
                    int x = i*velikostPlatna/4 + (velikostPlatna/4 - metrics.stringWidth(String.valueOf(herniPole[j][i]))) / 2;
                    int y = j*velikostPlatna/4 + ((velikostPlatna/4 - metrics.getHeight()) / 2) + metrics.getAscent();
                    g.drawString(String.valueOf(herniPole[j][i]), x, y+5);
                }
            }
        }
    }
    
}
