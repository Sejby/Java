package tlacitka;

import kalkulacka1.Model;

public class TlacitkoC implements I_Algoritmus {
    private final Model mujModel;
    public TlacitkoC() {
        mujModel = Model.getInstance();
    }
    @Override
    public void Algoritmus() {
        mujModel.setRadek1("");
    }

}
