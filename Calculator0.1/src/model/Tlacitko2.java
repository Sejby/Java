package model;

import okno.View;

public class Tlacitko2 implements I_SpustAlg{

    @Override
    public void algoritmus() {
        if (View.jTextField2.getText().equals("0")) {
            View.jTextField2.setText("2");
        }else{
            View.jTextField2.setText(View.jTextField2.getText() + "2");
        }
    }
    
}
