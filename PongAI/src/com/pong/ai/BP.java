package com.pong.ai;

import java.util.ArrayList;

public class BP {
    private String sit = "";
    private final ArrayList<double[][]> maticeSite = new ArrayList<>();
    private final ArrayList<double[][]> maticeUceni = new ArrayList<>();
    ArrayList<double[]> trenovaciVzoryVstup = new ArrayList<>();
    ArrayList<double[]> trenovaciVzoryVystup = new ArrayList<>();

    private double[] vstupniVektor;
    private double[] vystupniVektor;

    private double[][] vstupniVektorEpocha;
    private double[][] vystupniVektorEpocha;

    private double[][] vstupnitrenovaciEpocha;
    private double[][] vystupnitrenovaciEpocha;

    private final double koeficientUceni = 0.2;

    // getter setter
    public double[][] getVstupnitrenovaciEpocha() {
        return vstupnitrenovaciEpocha;
    }

    public void setVstupnitrenovaciEpocha(double[][] vstupnitrenovaciEpocha) {
        this.vstupnitrenovaciEpocha = vstupnitrenovaciEpocha;
        trenovaciVzoryVstup.clear();
        for (double[] doubles : vstupnitrenovaciEpocha) {
            double[] temp = new double[vstupnitrenovaciEpocha[0].length];
            System.arraycopy(doubles, 0, temp, 0, vstupnitrenovaciEpocha[0].length);
            trenovaciVzoryVstup.add(temp);
        }
    }

    public double[][] getVystupnitrenovaciEpocha() {
        return vystupnitrenovaciEpocha;
    }

    public void setVystupnitrenovaciEpocha(double[][] vystupnitrenovaciEpocha) {
        this.vystupnitrenovaciEpocha = vystupnitrenovaciEpocha;
        trenovaciVzoryVystup.clear();
        for (double[] doubles : vystupnitrenovaciEpocha) {
            double[] temp = new double[vystupnitrenovaciEpocha[0].length];
            System.arraycopy(doubles, 0, temp, 0, vystupnitrenovaciEpocha[0].length);
            trenovaciVzoryVystup.add(temp);
        }
    }

    public String getSit() {
        return sit;
    }

    public void setSit(String sit) {
        this.sit = sit;
        maticeSite.clear();
        maticeUceni.clear();
    }

    public double[] getVstupniVektor() {
        return vstupniVektor;
    }

    public void setVstupniVektor(double[] vstupniVektor) {
        double[][] temp = maticeSite.get(0);
        if (temp[0].length - 1 >= 0) System.arraycopy(vstupniVektor, 0, temp[0], 0, temp[0].length - 1);
        this.vstupniVektor = vstupniVektor;
    }

    public double[] getVystupniVektor() {
        double[] tempVektor = new double[splitSit(sit)[splitSit(sit).length - 1]];
        double[][] temp = maticeSite.get(maticeSite.size() - 1);
        System.arraycopy(temp[0], 0, tempVektor, 0, temp[0].length);
        vystupniVektor = tempVektor;
        return vystupniVektor;
    }

    public double[][] getVstupniVektorEpocha() {
        return vstupniVektorEpocha;
    }

    public void setVstupniVektorEpocha(double[][] vstupniVektorEpocha) {
        this.vstupniVektorEpocha = vstupniVektorEpocha;
    }

    public double[][] getVystupniVektorEpocha() {
        return vystupniVektorEpocha;
    }

    // ************************************************************************
    // *                   vybaveni                                           *
    // ************************************************************************
    //split--------------------------------------------------------------------
    private int[] splitSit(String sit) {
        return konvertStringInt(sit.split("-"));
    }

    private int[] konvertStringInt(String[] pole) {
        int[] temp = new int[pole.length];
        for (int i = 0; i < pole.length; i++) {
            temp[i] = Integer.parseInt(pole[i]);
        }
        return temp;
    }

    //vytvor sit---------------------------------------------------------------
    public void vytvorSit() {
        maticeSite.clear();
        maticeUceni.clear();
        for (int i = 0; i < splitSit(sit).length - 1; i++) {
            double[][] temp = new double[1][splitSit(sit)[i] + 1];
            temp[0][splitSit(sit)[i]] = -1;
            maticeSite.add(temp);
            maticeSite.add(new double[splitSit(sit)[i] + 1][splitSit(sit)[i + 1]]);
        }
        maticeSite.add(new double[1][splitSit(sit)[splitSit(sit).length - 1]]);
        inicializujSit();
        vytvorMaticeUceni();
    }

    //vybavSit-----------------------------------------------------------------
    public void vybavSit() {
        for (int j = 0; j < maticeSite.size() - 2; j += 2) {
            double[][] trmp = nasobeniMatic(maticeSite.get(j), maticeSite.get(j + 1));
            for (int i = 0; i < trmp[0].length; i++) {
                maticeSite.get(j + 2)[0][i] = vystupniFunkce(trmp[0][i]);
            }
        }
    }

    public void vybavSitEpocha() {
        ArrayList<double[]> vektor1 = new ArrayList<>();
        double[][] temp1 = new double[vstupniVektorEpocha.length][maticeSite.get(maticeSite.size() - 1)[0].length];
        for (double[] doubles : vstupniVektorEpocha) {
            double[] temp = new double[vstupniVektorEpocha[0].length];
            System.arraycopy(doubles, 0, temp, 0, vstupniVektorEpocha[0].length);
            vektor1.add(temp);
        }

        for (int i = 0; i < vektor1.size(); i++) {
            setVstupniVektor(vektor1.get(i));
            vybavSit();
            for (int j = 0; j < maticeSite.get(maticeSite.size() - 1)[0].length; j++) {
                temp1[i][j] = getVystupniVektor()[j];
            }
        }
        vystupniVektorEpocha = temp1;
    }

    private double[][] nasobeniMatic(double[][] matice1, double[][] matice2) {
        double[][] temp = new double[matice1.length][matice2[0].length];
        for (int i = 0; i < matice1.length; i++) {
            for (int j = 0; j < matice2[0].length; j++) {

                temp[i][j] = 0;
                for (int k = 0; k < matice1[0].length; k++) {
                    temp[i][j] += matice1[i][k] * matice2[k][j];
                }
            }
        }
        return temp;
    }

    private double vystupniFunkce(double potencial) {
        return 1.0 / (1 + Math.exp(-1.0 * potencial));
    }

    //inicializuj matice---------------------------------------------------------
    private void inicializujMatici(int index) {
        double[][] temp = maticeSite.get(index);
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                temp[i][j] = 0.5 - Math.random();
            }
        }
    }

    public void inicializujSit() {
        for (int i = 1; i < maticeSite.size(); i += 2) {
            inicializujMatici(i);
        }

    }
    //---------------------------------------------------------------------------

    // ************************************************************************
    // *                   uceni                                              *
    // ************************************************************************
    private void aktualizujVahy() {
        for (int i = 1; i < maticeUceni.size(); i += 2) {
            double[][] temp = maticeUceni.get(i);
            double[][] temp1 = maticeSite.get(i);
            for (int radek = 0; radek < temp.length; radek++) {
                System.arraycopy(temp1[radek], 0, temp[radek], 0, temp[0].length);
            }
        }
    }

    private void vytvorMaticeUceni() {
        for (int i = 0; i < splitSit(sit).length - 1; i++) {
            maticeUceni.add(new double[1][splitSit(sit)[i]]);
            maticeUceni.add(new double[splitSit(sit)[i]][splitSit(sit)[i + 1]]);
        }
        maticeUceni.add(new double[1][splitSit(sit)[splitSit(sit).length - 1]]);
    }

    public double[][] transponovanaMatice(double[][] matice) {
        double[][] temp = new double[matice[0].length][matice.length];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                temp[i][j] = matice[j][i];
            }
        }
        return temp;
    }

    public void aktualizujMaticiVahEpocha() {
        aktualizujVahy();
        for (int i = 0; i < trenovaciVzoryVstup.size(); i++) {
            //System.out.println(Arrays.toString(trenovaciVzoryVstup.get(i)));
            setVstupniVektor(trenovaciVzoryVstup.get(i));
            vybavSit();
            zpetnaPropagaceChyby(trenovaciVzoryVystup.get(i));
            pridejKoeficientUceni();
            pridejDerivaciVystupu();
            MaticePrirustkuVah();
        }
    }

    private void zpetnaPropagaceChyby(double[] pole) {
        double[][] temp = maticeUceni.get(maticeUceni.size() - 1);
        for (int i = 0; i < temp[0].length; i++) {
            temp[0][i] = pole[i] - maticeSite.get(maticeSite.size() - 1)[0][i];
        }
        for (int i = maticeUceni.size() - 1; i > 2; i -= 2) {
            maticeUceni.set(i - 2, nasobeniMatic(maticeUceni.get(i), transponovanaMatice(maticeUceni.get(i - 1))));
        }
    }

    private void pridejKoeficientUceni() {
        for (int i = 2; i < maticeUceni.size(); i += 2) {
            double[][] temp = maticeUceni.get(i);
            for (int j = 0; j < temp[0].length; j++) {
                temp[0][j] = temp[0][j] * koeficientUceni;
            }
        }
    }

    private void pridejDerivaciVystupu() {
        for (int i = 2; i < maticeUceni.size(); i += 2) {
            double[][] temp = maticeUceni.get(i);
            for (int j = 0; j < temp[0].length; j++) {
                temp[0][j] = temp[0][j] * maticeSite.get(i)[0][j] * (1 - maticeSite.get(i)[0][j]);
            }
        }
    }

    private void MaticePrirustkuVah() {
        for (int i = 0; i < maticeSite.size() - 1; i += 2) {
            double[][] temp = nasobeniMatic(transponovanaMatice(maticeUceni.get(i + 2)), maticeSite.get(i));
            maticeSite.set(i + 1, sectiMatice(maticeSite.get(i + 1), transponovanaMatice(temp)));
        }
    }

    private double[][] sectiMatice(double[][] mat1, double[][] mat2) {
        double[][] temp = new double[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[0].length; j++) {
                temp[i][j] = mat1[i][j] + mat2[i][j];
            }
        }
        return temp;

    }
    //-------------------------------------------------------------------------


    public void vytiskniPole(double[][] pole) {
        for (double[] doubles : pole) {        //sloupec
            for (int j = 0; j < pole[0].length; j++) { //radek
                System.out.print(doubles[j] + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void vytiskniPole(int[] pole) {
        for (int j : pole) {
            System.out.print(j + " ");
        }
    }

    public void vytiskniPole(double[] pole) {
        for (double v : pole) {
            System.out.print(v + " ");
        }
        System.out.println("");
    }


}
