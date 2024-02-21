package model;

import okno.View;

public class TlacitkoANS implements I_SpustAlg{

    static String ans;
    @Override
    public void algoritmus() {
        View.jTextField1.setText(ans);
        View.jTextField2.setText("");
    }
}
