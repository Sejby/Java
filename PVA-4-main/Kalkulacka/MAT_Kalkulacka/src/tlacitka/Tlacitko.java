package tlacitka;

import kalkulacka1.Model;

public class Tlacitko implements I_Algoritmus {
    private final Model mujModel;
    public Tlacitko() {
        mujModel = Model.getInstance();
    }
    @Override
    public void Algoritmus() {
        mujModel.setRadek1(mujModel.getRadek1() + "");
    }

}
