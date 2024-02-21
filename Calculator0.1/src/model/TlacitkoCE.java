package model;

import okno.View;

public class TlacitkoCE implements I_SpustAlg{
    @Override
    public void algoritmus() {
        View.jTextField2.setText("0");
        View.jTextField1.setText("");
    }
}
