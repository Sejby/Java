package tlacitka;

import kalkulacka1.Model;

public class Tlacitko5 implements I_Algoritmus {
    private final Model mujModel;
    public Tlacitko5() {
        mujModel = Model.getInstance();
    }
    @Override
    public void Algoritmus() {
        if ("0".equals(mujModel.getRadek1())) {
            mujModel.setRadek1("");
        }
        mujModel.setRadek1(mujModel.getRadek1() + "5");
    }

}
