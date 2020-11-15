/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import org.codehaus.plexus.util.FileUtils;

/**
 *
 * @author llaaj
 */
public class Redactar extends javax.swing.JFrame {

    /**
     * Creates new form Redactar
     */
    public Redactar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sendBtn = new javax.swing.JButton();
        destinatarioTxt = new javax.swing.JTextField();
        adjuntoTxt = new javax.swing.JTextField();
        msgTxt = new javax.swing.JTextField();
        adjuntarBtn = new javax.swing.JButton();
        asuntoTxt1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sendBtn.setText("Enviar");
        sendBtn.setToolTipText("");

        destinatarioTxt.setText("Destinatario");

        adjuntoTxt.setText("Archivo adjunto");
        adjuntoTxt.setEnabled(false);

        msgTxt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        msgTxt.setText("Mensaje");
        msgTxt.setToolTipText("");

        adjuntarBtn.setText("Adjuntar");
        adjuntarBtn.setToolTipText("");
        adjuntarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjuntarBtnActionPerformed(evt);
            }
        });

        asuntoTxt1.setText("Asunto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(adjuntarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendBtn))
                    .addComponent(destinatarioTxt)
                    .addComponent(adjuntoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(msgTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(asuntoTxt1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(destinatarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(asuntoTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msgTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendBtn)
                    .addComponent(adjuntarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adjuntoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adjuntarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adjuntarBtnActionPerformed
        // TODO add your handling code here:
        JFileChooser dialog = new JFileChooser();
        // dialog.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));

        File fichero;
        String path;
        int valor = dialog.showOpenDialog(this);

        if (valor == JFileChooser.APPROVE_OPTION) {
            fichero = dialog.getSelectedFile();
            path = fichero.getPath();

            adjuntoTxt.setText(path);
        }
        
        System.out.println(moverAdjunto(adjuntoTxt.getText()));

    }//GEN-LAST:event_adjuntarBtnActionPerformed

    private String moverAdjunto(String path) {
        path = path.replaceAll(" ", "");
        if (!"".equals(path)) {
            File source = new File(path);
            int index = source.getName().lastIndexOf('.');
            File dest = new File("C:\\MEIA\\Adjuntos\\" + source.getName());
            System.out.println(dest.getName());
            try {
                FileUtils.copyFile(source, dest);
                return dest.getPath();
            } catch (IOException e) {
                return e.getMessage();
            }
        }
        return "";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Redactar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Redactar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Redactar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Redactar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Redactar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adjuntarBtn;
    private javax.swing.JTextField adjuntoTxt;
    private javax.swing.JTextField asuntoTxt1;
    private javax.swing.JTextField destinatarioTxt;
    private javax.swing.JTextField msgTxt;
    private javax.swing.JButton sendBtn;
    // End of variables declaration//GEN-END:variables
}
