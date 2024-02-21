package mat_game_of_life;

import java.util.ArrayList;

public class Model {
    //Vlastnosti
    private ArrayList<Boolean> oldPole;
    private ArrayList<Boolean> newPole;
    private final int velikostX = 20;
    private final int velikostY = 20;
    
    //Metody rozhraní
    public void staraGenerace(ArrayList<Boolean> pole) {
        this.oldPole = pole;
    }
    public ArrayList<Boolean> novaGenerace() {
        vytvorNovePole();
        spustPravidla();
        return newPole;
    }
    
    private void vytvorNovePole() {
        newPole = new ArrayList<>();
        for (int i = 0; i < velikostX * velikostY; i++) {
            newPole.add(false);
        }
    }
    
    private void spustPravidla() {
        for (int i = 0; i < velikostX * velikostY; i++) {
            int sousedi = zjistiSousedy(i % velikostX, i / velikostY);
            Boolean alive = pravidlo1(sousedi, oldPole.get(i)) || pravidlo2(sousedi, oldPole.get(i));
            newPole.set(i, alive);
        }
    }
    private Boolean pravidlo1(int sousedi, Boolean isAlive) {
        return isAlive && sousedi > 1 && sousedi < 4;
    }
    private Boolean pravidlo2(int sousedi, Boolean isAlive) {
        return !isAlive && sousedi == 3;
    }
    
    private int zjistiSousedy(int x, int y) {
        int pocet = 0;
        if (x > 0 && y > 0 && oldPole.get((y-1) * velikostY + (x-1))) pocet++;
        if (y > 0 && oldPole.get((y-1) * velikostY +  x))    pocet++;
        if (x < velikostX - 1 && y > 0 && oldPole.get((y-1) * velikostY + (x+1))) pocet++;
        if (x > 0 && oldPole.get(y * velikostY + (x-1))) pocet++;
        if (x < velikostX - 1 && oldPole.get(y * velikostY + (x+1))) pocet++;
        if (x > 0 && y < velikostY - 1 && oldPole.get((y+1) * velikostY + (x-1))) pocet++;
        if (y < velikostY - 1 && oldPole.get((y+1) * velikostY + x)) pocet++;
        if (x < velikostX - 1 && y < velikostY - 1 && oldPole.get((y+1) * velikostY + (x+1))) pocet++;

        return pocet;
    }
}
