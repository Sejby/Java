package mat_dama;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import javax.swing.JPanel;
public class Pole extends JPanel implements PropertyChangeListener {
    //vlastnosti
    Logika logika;
    final public int velikostPlochy = 8;
    final public int velikostPolicka = 50;
    final public Color barvaBila=Color.white; 
    final public Color barvaTmava = new Color(255,153,51);
//    public Zeton typZetonux;
    public Pole(){
        super();
        logika = new Logika();
        logika.addPropertyChangeListener(this);
        for (Zeton zeton : logika.zetony) {
            this.add(zeton);
            zeton.setLocation((zeton.pozice.x) * 50 + 10, zeton.pozice.y * 50 + 10);
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        vykresliHerniPole(g);
        for (Zeton zeton : logika.zetony) zeton.repaint();
        vykresliMozneTahy(g);
    }
    private void vykresliHerniPole(Graphics g){
        for(int i = 0;i<velikostPlochy;i++){
            for(int j = 0;j<velikostPlochy;j++){
                if((i+j)%2==0){
                    g.setColor(barvaBila);
                }
                else{
                    g.setColor(barvaTmava);
                }
                 g.fillRect(velikostPolicka*i, velikostPolicka*j, velikostPolicka, velikostPolicka);
                g.setColor(Color.black);
               g.drawRect(velikostPolicka*i, velikostPolicka*j, velikostPolicka, velikostPolicka);
               
            }
        }
       
    }
    private void vykresliMozneTahy(Graphics g) {
        ArrayList<Point> tahy = logika.getMozneTahy();
        System.out.println(tahy.size());
        for (Point point : tahy) {
            g.setColor(Color.BLUE);
            g.fillOval(point.x * 50 + 10, point.y * 50 + 10, 30, 30);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.repaint();
    }
    
    

}
