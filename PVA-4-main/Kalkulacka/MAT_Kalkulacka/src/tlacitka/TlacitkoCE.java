package tlacitka;

import kalkulacka1.Model;

public class TlacitkoCE implements I_Algoritmus {
    private final Model mujModel;
    public TlacitkoCE() {
        mujModel = Model.getInstance();
    }
    @Override
    public void Algoritmus() {
        mujModel.setRadek1("");
        mujModel.setRadek2("");
    }

}
