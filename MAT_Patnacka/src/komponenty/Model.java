
package komponenty;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;
import static javax.swing.JOptionPane.showMessageDialog;

public class Model {
    
    // Vlastnosti
    public int[] ctverec;
    private Random rn = new Random();
    private static Model singleton = null;
    
    // Getter Setter
    public int[] getCtverec() {
        return ctverec;
    }
    
    // Konstruktor
    private Model() {  
        ctverec = new int[16];
        for (int i = 1; i < ctverec.length; i++) ctverec[i-1] = i;
    }
    
    //Funkce
    private void swap(int one, int two) {
       System.out.println("swap");
       int temp = ctverec[one];
       ctverec[one] = ctverec[two];
       ctverec[two] = temp;
        firePropertyChange("ctverec", true, false);
    }
    
     private boolean tryAbove(int pos){
        if(pos < 4){return false;}
        if(ctverec[pos-4] != 0){return false;}  
        swap(pos,pos-4);
        return true;
    }
     
     private boolean tryBelllow(int pos){
         if(pos > 11){return false;}
         if(ctverec[pos+4] != 0){return false;}
         swap(pos,pos+4);
         return true;
     }
     
     private boolean tryLeft(int pos){
         if(pos%4 == 0){return false;}
         if(ctverec[pos-1] != 0){return false;}
         swap(pos,pos-1);
         return true;
     }
     
     private boolean tryRight(int pos){
         if (pos%4 == 3)
          return false;
        if (ctverec[pos+1] != 0)
          return false;
        swap( pos, pos+1 );
        return true;
     }
     
     public void move(int pos){
         System.out.println("pohyb");
         
         if(tryAbove(pos))return;
         if(tryBelllow(pos))return; 
         if(tryLeft(pos))return;
         if(tryRight(pos))return;
         if(isSolved() == true)showMessageDialog(null, "Vyhra");
     }
     
     public boolean isSolved() {
        if (ctverec[ctverec.length - 1] != 0) {
        return false;
        }else{
            return true;  
     }
  }
     public static Model getInstance(){
         if(singleton == null){singleton = new Model();}
         return singleton;
     }
     
     public void resetKomponenty(){
         for (int i = 1; i < ctverec.length; i++){
         ctverec[i-1] = i;    
         ctverec[15]= 0;
         } 
                 
         firePropertyChange("reset", true, false);
     }
     
     // Vydavatel
     
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    public void firePropertyChange(String string, boolean o, boolean o1) {
        propertyChangeSupport.firePropertyChange(string, o, o1);
    }
    
    // Zamíchání
     public void scramble() {
         int[] neighbors = new int[4];
         int pocetSousedu,temp,moveTo,blank = 15;
         for(int i=0; i < ctverec.length; i++){
             if(ctverec[i]==0){
                 blank = i;
                 break;
             }
         }
         
         for(int i=0; i < 200; i++){
            pocetSousedu = najdiSousedy( blank,neighbors);
             moveTo = neighbors[rn.nextInt(pocetSousedu)];
             temp = ctverec[moveTo];
             ctverec[moveTo] = ctverec[blank];
             ctverec[blank] = temp;
             blank = moveTo;
         }
         firePropertyChange("zamichat", true, false);
     }
     
     public int najdiSousedy(int blank,int[] array){
     int numNeighbors = 0;
     if(blank > 3) array[numNeighbors++] = blank - 4;
     if(blank < 12) array[numNeighbors++] = blank + 4;
     if(blank % 4 != 0) array[numNeighbors++] = blank - 1;
     if(blank % 4 != 3) array[numNeighbors++] = blank + 1;
     
     return numNeighbors;
     }
}
