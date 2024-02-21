package model;

import okno.View;

public class TlacitkoDeleno implements I_SpustAlg{
    @Override
    public void algoritmus() {
        if (View.jTextField2.getText().equals("0")) {
            
        }else{
        View.jTextField1.setText(View.jTextField1.getText() + View.jTextField2.getText() + " / ");
        View.jTextField2.setText("0");
    }
}
}