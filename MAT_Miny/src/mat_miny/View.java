package mat_miny;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;

public class View extends javax.swing.JFrame implements PropertyChangeListener {

    HerniLogika log;

    public View() {
        initComponents();
        log = HerniLogika.getInstance();
        log.addPropertyChangeListener(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        platno1 = new mat_miny.Platno();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Miny");
        setResizable(false);

        platno1.setPreferredSize(new java.awt.Dimension(421, 421));

        javax.swing.GroupLayout platno1Layout = new javax.swing.GroupLayout(platno1);
        platno1.setLayout(platno1Layout);
        platno1Layout.setHorizontalGroup(
            platno1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );
        platno1Layout.setVerticalGroup(
            platno1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(platno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(platno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private mat_miny.Platno platno1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("prohra".equals(evt.getPropertyName())) {
            Object[] options = {"Ano", "Ne"};
            int n = JOptionPane.showOptionDialog(this, "PROHRA: \n Chcete hrát znovu?", "PROHRA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (n == 1) {
                this.dispose();
            }
            if (n == 0) {
                log.vytvorPole();
            }
        }
        if ("vyhra".equals(evt.getPropertyName())) {
            Object[] options = {"Ano", "Ne"};
            int n = JOptionPane.showOptionDialog(this, "VÝHRA \n Chcete hrát znovu?", "VÝHRA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (n == 1) {
                this.dispose();
            }
            if (n == 0) {
                log.vytvorPole();
            }
        }
    }
}
