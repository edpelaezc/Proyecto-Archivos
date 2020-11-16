/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Data.Data;
import Tree.Correo;
import Tree.HandleTree;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author llaaj
 */
public class Email extends javax.swing.JFrame {

    HandleTree handler = new HandleTree();

    /**
     * Creates new form Email
     */
    public Email() {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        handler.readTree();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bandejaSalida = new javax.swing.JButton();
        bandejaEntrada = new javax.swing.JButton();
        redactarBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        bandejaTable = new javax.swing.JTable();
        EliminarBtn = new javax.swing.JButton();
        verBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bandejaSalida.setActionCommand("enviados");
        bandejaSalida.setLabel("Bandeja de Salida");
        bandejaSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bandejaSalidaActionPerformed(evt);
            }
        });

        bandejaEntrada.setText("Bandeja de Entrada");
        bandejaEntrada.setActionCommand("recibidos");
        bandejaEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bandejaEntradaActionPerformed(evt);
            }
        });

        redactarBtn.setActionCommand("enviar");
        redactarBtn.setLabel("+ Redactar");
        redactarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redactarBtnActionPerformed(evt);
            }
        });

        bandejaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(bandejaTable);

        EliminarBtn.setText("Eliminar");
        EliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarBtnActionPerformed(evt);
            }
        });

        verBtn.setText("Ver");
        verBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bandejaEntrada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bandejaSalida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(verBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(EliminarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(redactarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bandejaEntrada)
                    .addComponent(bandejaSalida)
                    .addComponent(redactarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EliminarBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(verBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void redactarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redactarBtnActionPerformed
        // TODO add your handling code here:
        Redactar redactar = new Redactar();
        redactar.setVisible(true);
    }//GEN-LAST:event_redactarBtnActionPerformed

    private void bandejaEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bandejaEntradaActionPerformed
        // TODO add your handling code here:
        showTable(2);
        Data.Instance().actual = 2;
    }//GEN-LAST:event_bandejaEntradaActionPerformed

    private void bandejaSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bandejaSalidaActionPerformed
        // TODO add your handling code here:
        showTable(1);
        Data.Instance().actual = 1;
    }//GEN-LAST:event_bandejaSalidaActionPerformed

    private void EliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarBtnActionPerformed
        // TODO add your handling code here:
        int selected = bandejaTable.getSelectedRow();
        Correo temp = null;
        // buscar dependiendo de la bandeja
        if (Data.Instance().actual == 2) {
            temp = Data.Instance().tree.search(bandejaTable.getValueAt(selected, 0).toString(), Data.Instance().user.getUsuario(), bandejaTable.getValueAt(selected, 2).toString());
        } else {
            temp = Data.Instance().tree.search(Data.Instance().user.getUsuario(), bandejaTable.getValueAt(selected, 0).toString(), bandejaTable.getValueAt(selected, 2).toString());
        }
        Data.Instance().tree.logicalDelete(temp);
    }//GEN-LAST:event_EliminarBtnActionPerformed

    private void verBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verBtnActionPerformed
        // TODO add your handling code here:
        int selected = bandejaTable.getSelectedRow();

        if (selected != -1) {
            Correo temp = null;
            // buscar dependiendo de la bandeja
            if (Data.Instance().actual == 2) {
                temp = Data.Instance().tree.search(bandejaTable.getValueAt(selected, 0).toString(), Data.Instance().user.getUsuario(), bandejaTable.getValueAt(selected, 2).toString());
            } else {
                temp = Data.Instance().tree.search(Data.Instance().user.getUsuario(), bandejaTable.getValueAt(selected, 0).toString(), bandejaTable.getValueAt(selected, 2).toString());
            }

            Data.Instance().ver = temp;
            Redactar redactar = new Redactar();
            redactar.setVisible(true);
        }
    }//GEN-LAST:event_verBtnActionPerformed

    public void showTable(int bandeja) {
        String[] columns = {"Usuario", "Asunto", "Fecha"};
        String[] tupla = new String[3];

        DefaultTableModel model = new DefaultTableModel(null, columns);

        // conseguir datos 
        ArrayList<Correo> correos = Data.Instance().tree.query(Data.Instance().user.getUsuario(), bandeja);

        for (int i = 0; i < correos.size(); i++) {

            // dependiendo la bandeja mostrar el usuario
            if (bandeja == 2) {
                tupla[0] = correos.get(i).getEmisor();
            } else {
                tupla[0] = correos.get(i).getReceptor();
            }
            tupla[1] = correos.get(i).getAsunto();
            tupla[2] = correos.get(i).getFecha();

            model.addRow(tupla);
        }

        bandejaTable.setModel(model);
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
            java.util.logging.Logger.getLogger(Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Email().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EliminarBtn;
    private javax.swing.JButton bandejaEntrada;
    private javax.swing.JButton bandejaSalida;
    private javax.swing.JTable bandejaTable;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton redactarBtn;
    private javax.swing.JButton verBtn;
    // End of variables declaration//GEN-END:variables
}
