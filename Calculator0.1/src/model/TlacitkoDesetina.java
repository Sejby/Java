package model;

import okno.View;

public class TlacitkoDesetina implements I_SpustAlg{
    @Override
    public void algoritmus() {
        View.jTextField2.setText(View.jTextField2.getText() + ".");

    }
}
