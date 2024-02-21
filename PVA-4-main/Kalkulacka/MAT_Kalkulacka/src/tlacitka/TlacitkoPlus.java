package tlacitka;

import kalkulacka1.Model;

public class TlacitkoPlus implements I_Algoritmus {
    private final Model mujModel;
    public TlacitkoPlus() {
        mujModel = Model.getInstance();
    }
    @Override
    public void Algoritmus() {
        String radek1 = mujModel.getRadek1();
        String radek2 = mujModel.getRadek2();
        if (radek1.equals("")) return;
        mujModel.setRadek2(radek2 + radek1 + "+");
        mujModel.setRadek1("");
    }

}
