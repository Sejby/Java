package kalkulacka1;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Model {
    //Vlastnosti
    private String radek1;
    private String radek2;
    
    //Getter Setter
    public String getRadek1() {
        return radek1; 
    }
    public void setRadek1(String radek1) {
        this.radek1 = radek1;
        propertyChangeSupport.firePropertyChange("radek1", "Ahoj", radek1);
    }
    public String getRadek2() {
        return radek2;
    }
    public void setRadek2(String radek2) {    
        this.radek2 = radek2;
        propertyChangeSupport.firePropertyChange("radek2", "Ahoj", radek2);
    }

    //Konstruktor
    private Model() {
        radek1 = "";
        radek2 = "";
    }
    
    //Singleton
    public static Model getInstance() {
        return SINGLETON.MODEL;
    }
    private static class SINGLETON {
        public static final Model MODEL = new Model();
    }

    //Property Change Support
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}
