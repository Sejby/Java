package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
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
        this.setBackground(new Color(200, 255, 200));
        this.setFont(new Font("Tahoma", 1, 16));
        this.setForeground(Color.white);
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
        this.registerKeyboardAction(mujListener,
                KeyStroke.getKeyStroke(klavesa, 0),
                WHEN_IN_FOCUSED_WINDOW);
        this.setText(nazev);
        this.setBackground(new Color(66,66,66));
        this.setFont(new Font("Tahoma", 1, 15));
        this.setForeground(Color.white);
        this.setMargin(new Insets(0, 0, 0, 0));
    }
    
    //Metody Strategie
    public void SpustAlg() {
        strategie.Algoritmus();
    }
    public void NastavAlg(I_Algoritmus alg) {
        this.strategie = alg;
    }
}
