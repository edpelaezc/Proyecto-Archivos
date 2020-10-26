/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MLD;

import Admin.FileHandling;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import Data.Data;
import java.util.Arrays;
import java.util.regex.Pattern;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author kevin
 */
public class MLD extends javax.swing.JFrame {

    /**
     * Creates new form MLD
     */
    public MLD() {
        initComponents();
    }

    int cont_usr = 0;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_user = new javax.swing.JLabel();
        list_txt = new javax.swing.JTextField();
        description = new javax.swing.JTextField();
        status = new javax.swing.JRadioButton();
        lbl_Date = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        list_Name = new javax.swing.JLabel();
        date_create = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        find = new javax.swing.JButton();
        modify = new javax.swing.JButton();
        usr_count = new javax.swing.JLabel();
        usr_txt = new javax.swing.JTextField();
        Usuario = new javax.swing.JLabel();
        Create = new javax.swing.JButton();
        close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        lbl_user.setText("Usuario");

        list_txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                list_txtFocusGained(evt);
            }
        });
        list_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list_txtActionPerformed(evt);
            }
        });

        description.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                descriptionFocusGained(evt);
            }
        });

        status.setText("Status");
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });

        lbl_Date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Date.setText("Fecha");
        lbl_Date.setToolTipText("");

        delete.setText("Eliminar");
        delete.setEnabled(false);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        list_Name.setText("Nombre Lista");

        date_create.setText("Fecha Creación: ");

        jLabel4.setText("Descripción");

        find.setText("Buscar");
        find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findActionPerformed(evt);
            }
        });

        modify.setText("Modificar");
        modify.setEnabled(false);
        modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyActionPerformed(evt);
            }
        });

        usr_count.setText("Número Usuario: ");

        usr_txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usr_txtFocusGained(evt);
            }
        });
        usr_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usr_txtActionPerformed(evt);
            }
        });

        Usuario.setText("Usuario");

        Create.setText("Crear");
        Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateActionPerformed(evt);
            }
        });

        close.setText("Cerrar");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_user, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(list_Name))
                                .addGap(174, 174, 174)
                                .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Usuario))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(status)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(usr_count, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(date_create, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(62, 62, 62)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(list_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(usr_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(close)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(modify, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(find, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Create, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_user, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(list_Name)
                            .addComponent(list_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Usuario)
                            .addComponent(usr_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addComponent(usr_count)
                        .addGap(18, 18, 18)
                        .addComponent(date_create))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(155, 155, 155)
                        .addComponent(delete)
                        .addGap(18, 18, 18)
                        .addComponent(modify)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(status)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Create)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(find)
                            .addComponent(close))))
                .addContainerGap())
        );

        status.getAccessibleContext().setAccessibleDescription("Status will set at (#)");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void list_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list_txtActionPerformed

    }//GEN-LAST:event_list_txtActionPerformed

    private void list_txtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_list_txtFocusGained
                list_txt.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (list_txt.getText().length() >= 30){
                e.consume();
            }
        }
    });
    }//GEN-LAST:event_list_txtFocusGained

    private void descriptionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriptionFocusGained
    description.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (description.getText().length() >= 40){
                e.consume();
            }
        }
    });
    }//GEN-LAST:event_descriptionFocusGained

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String strDate = dateFormat.format(date);  
        lbl_Date.setText("Fecha: " + strDate);
        lbl_user.setText("Kevin");
    }//GEN-LAST:event_formComponentShown

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        if (!(list_txt.getText().equals(""))) {
            int status = 0;
            if (this.status.isSelected()){  status = 1;   }  
            
            String key = list_txt.getText() + "Kevin";
            FileHandling manejo = new FileHandling();
            String usr = manejo.Get_ListMLD(key);
            try{
                String[] values_usr = usr.split(Pattern.quote("|"));
                if (values_usr[0].equals(key)) {
                    String new_Line = "";
                    String ruta = "C:\\MEIA\\lista.txt";
                    while(!"".equals(usr)){
                        manejo.Remove_Line(usr, new_Line, ruta);
                        usr = manejo.Get_ListMLD(key);
                    }
                    showMessageDialog(null, "Lista " + values_usr[1] + " eliminada correctamente");
                }
                else{
                    showMessageDialog(null, "No se encuentran listas asociadas a su cuenta.");
                }
            }
            catch(Exception  e){
                showMessageDialog(null, "No se han agregado listas previas.");
            }
        }
        else{
            showMessageDialog(null, "Ingrese nombre de la lista.");
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        if (status.isSelected()) {
            status.setText("Status (1)");
        }
        else{
            status.setText("Status (0)");                
        }
    }//GEN-LAST:event_statusActionPerformed

    private void findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findActionPerformed
        if (!(list_txt.getText().equals(""))) {
            FileHandling manejo = new FileHandling();
            String key = list_txt.getText() + "Kevin";
            String usr = manejo.Get_ListMLD(key);
            
            try{
                String[] values_usr = usr.split(Pattern.quote("|"));
                list_txt.setText(values_usr[1]);
                description.setText(values_usr[3]);
                usr_count.setText("Número Usuario: " + values_usr[4]);
                cont_usr = Integer.parseInt(values_usr[4].trim());
                date_create.setText("Fecha creación:" + values_usr[5]);
                if (values_usr[6] == "1") {
                    this.status.setSelected(true);
                }
                else{
                    this.status.setSelected(false);
                }
                delete.setEnabled(true);
                modify.setEnabled(true);
            }catch(Exception e){
                showMessageDialog(null, "No se han agregado listas previas.");
            }
            
        }
        else{
            showMessageDialog(null, "Ingrese nombre de la lista.");
        }
    }//GEN-LAST:event_findActionPerformed

    private void modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyActionPerformed
        int status = 0;
        if (this.status.isSelected()){  status = 1;   }   
        String key = list_txt.getText() + "Kevin"; 
        if (!(list_txt.getText().equals("")) && !(description.getText().equals("")) ) {
            if (!(usr_txt.getText().equals(""))) {
                Modify_List(key, status, usr_txt.getText());
                showMessageDialog(null, "Lista modificada exitosamente."
                + "\nNuevos valores:"
                + "\nNombre Lista: " + list_txt.getText()
                + "\nDescripción: " + description.getText()
                + "\nNuevo usuario: " + usr_txt.getText()
                );
            }
            else{
                Modify_List(key, status, "");
                showMessageDialog(null, "Lista modificada exitosamente."
                + "\nNuevos valores:"
                + "\nNombre Lista: " + list_txt.getText()
                + "\nDescripción: " + description.getText()
                );
            }
        }
        else{
            showMessageDialog(null, "Por favor llene los campos faltantes");
        }
    }//GEN-LAST:event_modifyActionPerformed

    private void usr_txtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usr_txtFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_usr_txtFocusGained

    private void usr_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usr_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usr_txtActionPerformed

    private void CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateActionPerformed
        String key = list_txt.getText() + "Kevin";
        FileHandling manejo = new FileHandling();
        String linea = manejo.Get_ListMLD(key);
        if (linea == "") {
            int status = 0;
            if (this.status.isSelected()){  status = 1;   }
            if (!(list_txt.getText().equals("")) && !(description.getText().equals(""))) {
                String list_values = "";
                String [] new_values = {};
                if (!(usr_txt.getText().equals(""))) {
                    list_values = Create_List("1", status, key);
                }
                else{
                    list_values = Create_List("0", status, key);
                }
                new_values = list_values.split(Pattern.quote("|"));
                showMessageDialog(null,
                "\tLista: " + list_txt.getText() +" creada!"
                + "\nNombre Lista: " + new_values[1] 
                + "\nUsuario: " + new_values[2] 
                + "\nDescripción: " + new_values[3]
                + "\nCantidad Usuarios: " + new_values[4]
                + "\nFecha Creación: " + new_values[5]
                + "\nStatus: " + new_values[6]
                );
            }
            else{
                showMessageDialog(null, "Por favor coloque un nombre y descripción a la lista.");
            }
        }
        else{
            showMessageDialog(null, "Ya existe la lista " + list_txt.getText());
        }
    }//GEN-LAST:event_CreateActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeActionPerformed

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
            java.util.logging.Logger.getLogger(MLD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MLD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MLD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MLD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MLD().setVisible(true);
        });
    }
    public void Modify_List(String key, int status, String new_usr){
        FileHandling manejo = new FileHandling();
        String usr = manejo.Get_ListMLD(key);
        try{
            String[] old_Line = usr.split(Pattern.quote("|"));
            Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDate = dateFormat.format(date);
            if (!(new_usr.equals(""))) {
            cont_usr = cont_usr + 1;
            }
            String new_count = Integer.toString(cont_usr);   
            String[] new_Line = {
                key,
                list_txt.getText(),
                new_usr,
                description.getText(),
                new_count,
                strDate,
                Integer.toString(status)
            };
            String ruta = "C:\\MEIA\\lista.txt";
            manejo.Write_Text(old_Line, new_Line, ruta);
            String new_Data = Arrays.toString(new_Line);
            showMessageDialog(null, new_Data);
        }catch(Exception e){
            showMessageDialog(null, "No se han agregado listas previas.\n" + e.getMessage());
        }
    }
    
    
    /**
     * Crea la primera lista del usuario
     * @param usr
     * @param status
     * @param key 
     */
    public String Create_List(String usr, int status, String key){
            Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDate = dateFormat.format(date);
            String new_values = 
                    key + "|" 
                    + list_txt.getText() + "|"
                    + usr_txt.getText() + "|"
                    + description.getText() + "|"
                    + usr + "|"
                    + strDate + "|"
                    + Integer.toString(status);
            FileHandling manejo = new FileHandling();
            manejo.Write_MLD(new_values);
            return new_values;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Create;
    private javax.swing.JLabel Usuario;
    private javax.swing.JButton close;
    private javax.swing.JLabel date_create;
    private javax.swing.JButton delete;
    private javax.swing.JTextField description;
    private javax.swing.JButton find;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbl_Date;
    private javax.swing.JLabel lbl_user;
    private javax.swing.JLabel list_Name;
    private javax.swing.JTextField list_txt;
    private javax.swing.JButton modify;
    private javax.swing.JRadioButton status;
    private javax.swing.JLabel usr_count;
    private javax.swing.JTextField usr_txt;
    // End of variables declaration//GEN-END:variables
}
