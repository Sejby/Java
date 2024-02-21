package model;

import okno.View;

public class TlacitkoKrat implements I_SpustAlg{

    @Override
    public void algoritmus() {
        View.jTextField1.setText(View.jTextField1.getText() + View.jTextField2.getText() + " * ");
        View.jTextField2.setText("0");
    }
    
}
