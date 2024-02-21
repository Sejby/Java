package tlacitka;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import model.I_SpustAlg;

public class SuperTlacitko extends JButton{
    //vlastnosti
    private I_SpustAlg strategy;
    //konstruktor
    public SuperTlacitko(){
        
    }
    public SuperTlacitko(String text,int key,I_SpustAlg algoritmus){
        super(text);
        strategy = algoritmus;
        ActionListener mujListener = new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent e) {              
                spustAlg();
            }
        };
        this.addActionListener(mujListener);
        this.registerKeyboardAction(mujListener,KeyStroke.getKeyStroke(key,0),JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.setFont(new Font("Tahoma",1,12));
        this.setBackground(new Color(0, 153, 0));
    }
    //navrhovy vzor stategie
    public void zmenaAlg(I_SpustAlg novyAlg){
        strategy = novyAlg;
    }
    public void spustAlg(){
        strategy.algoritmus();
    }
}
