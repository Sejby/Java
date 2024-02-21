package model;

import okno.View;

public class TlacitkoPlusMinus implements I_SpustAlg{
    @Override
    public void algoritmus() {
        View.jTextField2.setText(String.valueOf(Integer.valueOf(View.jTextField2.getText()) * -1));
        
    }
}
