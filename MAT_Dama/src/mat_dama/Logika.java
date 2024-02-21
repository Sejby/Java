package mat_dama;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Logika implements PropertyChangeListener {
    
    public enum smery {
        dolu,
        nahoru
    }

    public ArrayList<Zeton> zetony;
    public Zeton aktivniZeton;

    public Logika() {
        zetony =  new ArrayList<>();
        for (int i = 0; i < 8; i++) {
                if (i % 2 == 0) {
                    zetony.add(new Zeton(i+1, 0, Zeton.typZetonu.CERNY));
                    zetony.add(new Zeton(i, 1, Zeton.typZetonu.CERNY));
                    zetony.add(new Zeton(i+1, 2, Zeton.typZetonu.CERNY));
                
                    zetony.add(new Zeton(i, 5, Zeton.typZetonu.BILY));
                    zetony.add(new Zeton(i+1, 6, Zeton.typZetonu.BILY));
                    zetony.add(new Zeton(i, 7, Zeton.typZetonu.BILY));
                }
        }
        for (Zeton zeton : zetony) {
            zeton.addPropertyChangeListener(this);
        }

        // 0,1   0,3   0,5   0,7   
        // 1,0   1,2   1,4   1,6
        // 2,1   2,3   2,5   2,7
        // 5,0   5,2   5,4   5,6
        // 6,1   6,3   6,5   6,7
        // 7,0   7,2   7,4   7,6
    }
    
    public ArrayList<Point> getMozneTahy() {
        ArrayList<Point> vystup = new ArrayList<>();
        if (aktivniZeton == null) return vystup;
        
        smery smer;
        switch(aktivniZeton.typ) {
            case BDAMA:
            case BILY:
                smer = smery.dolu;
                break;
            case CDAMA:
            case CERNY:
                smer = smery.nahoru;
                break;
            default:
                smer = smery.nahoru;
                break;
        }
        
        if (smer == smery.dolu) {
            vystup.add(new Point(aktivniZeton.pozice.x + 1, aktivniZeton.pozice.y - 1));
            vystup.add(new Point(aktivniZeton.pozice.x - 1, aktivniZeton.pozice.y - 1));

        }
        
        return vystup;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getPropertyName());
        aktivniZeton = (Zeton) evt.getNewValue();
        propertyChangeSupport.firePropertyChange("repaint", "", "1");
        
    }
    
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
