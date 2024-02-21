package tlacitka;

import kalkulacka1.Model;

public class TlacitkoAns implements I_Algoritmus {
    private final Model mujModel;
    public TlacitkoAns() {
        mujModel = Model.getInstance();
    }
    @Override
    public void Algoritmus() {
        mujModel.setRadek1(mujModel.getRadek1() + mujModel.getRadek2());
    }

}
