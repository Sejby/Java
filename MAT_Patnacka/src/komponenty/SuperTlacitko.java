package komponenty;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;


public class SuperTlacitko extends JButton implements PropertyChangeListener{
    
    // Vlastnosti
    public int indexpole;
    private Model model;
    
    // Konstruktor
    public SuperTlacitko(int indexpole) {
        this.indexpole = indexpole;
        model = Model.getInstance();
        model.addPropertyChangeListener(this);
        
        this.setPreferredSize(new Dimension(80,80));
        this.setBackground(new java.awt.Color(58, 148, 148));
        this.setForeground(Color.white);
        this.setFont(new Font("Tahoma",Font.BOLD,36));
        this.setText(String.valueOf(model.ctverec[indexpole]));
        
        
         if(indexpole == 15){
            this.setBackground(Color.white);
            this.setText("");
         }
    }

    public SuperTlacitko() {
        this(0);
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        // System.out.println("stisk");
        
        int[] pole = model.getCtverec();
        int hodnotaPole = pole[indexpole];
        this.setText(String.valueOf(hodnotaPole));
        if(hodnotaPole == 0){
            this.setBackground(Color.white);
            this.setText("");
        }else
            this.setBackground(new java.awt.Color(58, 148, 148));
            this.setForeground(Color.white);                       
        }
    }
    

