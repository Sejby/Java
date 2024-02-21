package model;

import okno.View;

public class Tlacitko7 implements I_SpustAlg{

    @Override
    public void algoritmus() {
        if (View.jTextField2.getText().equals("0")) {
            View.jTextField2.setText("7");
        }else{
            View.jTextField2.setText(View.jTextField2.getText() + "7");
        }
    }
    
}
