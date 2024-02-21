package model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import okno.View;

public class TlacitkoRovno implements I_SpustAlg{
    @Override
    public void algoritmus() {
        
        View.jTextField1.setText(View.jTextField1.getText()+View.jTextField2.getText());
    
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
        try {
            Object result = engine.eval(View.jTextField1.getText());
            View.jTextField2.setText(String.valueOf(result));
            View.jTextField1.setText("");
            TlacitkoANS.ans =String.valueOf(result);

        } catch (ScriptException ex) {
            Logger.getLogger(TlacitkoRovno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
