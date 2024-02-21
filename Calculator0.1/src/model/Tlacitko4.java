package model;

import okno.View;

public class Tlacitko4 implements I_SpustAlg{

    @Override
    public void algoritmus() {
        if (View.jTextField2.getText().equals("0")) {
            View.jTextField2.setText("4");
        }else{
            View.jTextField2.setText(View.jTextField2.getText() + "4");
        }
    }
    
}
