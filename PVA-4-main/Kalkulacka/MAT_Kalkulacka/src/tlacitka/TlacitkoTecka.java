package tlacitka;

import kalkulacka1.Model;

public class TlacitkoTecka implements I_Algoritmus {
    private final Model mujModel;
    public TlacitkoTecka() {
        mujModel = Model.getInstance();
    }
    @Override
    public void Algoritmus() {
        String radek1 = mujModel.getRadek1();
        if (radek1.contains(".")) return;
        if (radek1.equals("")) mujModel.setRadek1("0");
        
        mujModel.setRadek1(mujModel.getRadek1() + ".");
    }

}
