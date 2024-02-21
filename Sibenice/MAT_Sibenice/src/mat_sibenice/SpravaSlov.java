package mat_sibenice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SpravaSlov {
    //Vlastnosti
    private String cesta;
    private ArrayList<String> slova;
    private File soubor;
    
    //konstruktor
    public SpravaSlov(String cesta) {
        this.cesta = cesta;
        this.slova = new ArrayList<>();
        this.soubor = new File(cesta);
        try {
            nacteniTextuDoPole();
        } catch (IOException ex) {
            System.out.println("Soubor nenalezen!");
        }
        tiskPole();
    }
    //Metody rozhraní
    public String vyberNahodneSlovo() {
        Random rand = new Random();
        return slova.get(rand.nextInt(slova.size()));
    }
    public boolean vlozSlovo(String slovo) {
        if (obsahujeSlovo(slovo)) return false;
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.soubor, true));
            bw.append("\n" + slovo);
            bw.close();
            
            this.slova.add(slovo);
        } catch (IOException ex) {
            System.out.println("Chyba ve vloz slovo!");
            return false;
        }
        return true;
    }
    public boolean smazSlovo(String slovo) {
        if (!obsahujeSlovo(slovo)) return false;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.soubor));
            this.slova.remove(slovo);
            for (int i = 0; i < this.slova.size(); i++) {
                if (i != 0) bw.append("\n");
                bw.append(this.slova.get(i));
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("Chyba v smaz slovo!");
            return false;
        }
        return true;
    }
    public boolean obsahujeSlovo(String slovo) {
        return slova.contains(slovo);
    }
    
    //Metody
    private void nacteniTextuDoPole() throws FileNotFoundException, IOException {
        this.slova.clear();
        BufferedReader br = new BufferedReader(new FileReader(this.soubor));
        String line;
        while ((line = br.readLine()) != null) {
            this.slova.add(line);
        }
        br.close();
    }
    private void tiskPole() {
        for (String slovo : this.slova) {
            System.out.println(slovo);
        }
    }
}
