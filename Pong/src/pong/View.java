package pong;

import javax.swing.SwingWorker;

public class View extends javax.swing.JFrame {

    public View() {
        initComponents();
        SwingWorker sw = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                addKeyListener(new Listener(38, 40)); 
                System.out.println(Thread.currentThread());
                return null;
            }
        };
        sw.execute();
        
        addKeyListener(new Listener(87, 83));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        platno1 = new pong.Platno();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout platno1Layout = new javax.swing.GroupLayout(platno1);
        platno1.setLayout(platno1Layout);
        platno1Layout.setHorizontalGroup(
            platno1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
        );
        platno1Layout.setVerticalGroup(
            platno1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(platno1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(platno1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pong.Platno platno1;
    // End of variables declaration//GEN-END:variables

}
