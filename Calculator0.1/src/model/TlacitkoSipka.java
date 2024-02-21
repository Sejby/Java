package model;

import okno.View;

public class TlacitkoSipka implements I_SpustAlg{
    @Override
    public void algoritmus() {
         View.jTextField2.setText(removeLastChar(View.jTextField2.getText()));
    }
       static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
}
