package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import tlacitka.I_Algoritmus;

public class SuperTlacitko extends JButton {
    //Vlastnosti
    private I_Algoritmus strategie;
    
    //Konstruktor
    public SuperTlacitko() {
        super();
    }
    public SuperTlacitko(I_Algoritmus alg, final int klavesa, String nazev) {
        super();
        NastavAlg(alg);
        ActionListener mujListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpustAlg();
            }
        };
        this.addActionListener(mujListener);
        this.registerKeyboardAction(mujListener, KeyStroke.getKeyStroke(klavesa, 0), WHEN_IN_FOCUSED_WINDOW);
        this.setText(nazev);
    }
    
    //Metody Strategie
    public void SpustAlg() {
        strategie.Algoritmus();
    }
    public void NastavAlg(I_Algoritmus alg) {
        this.strategie = alg;
    }
}
