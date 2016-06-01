/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BusinessModel.Manager;
import static BusinessModel.Manager.addUser;
import static BusinessModel.Manager.deleteUser;
import static BusinessModel.Manager.updateUser;
import Model.Profile;
import Model.User;
import static com.oracle.jrockit.jfr.ContentType.Timestamp;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Andrea
 */
public class UserGui extends javax.swing.JInternalFrame {
 MyTableModel userModel;
    /**
     * Creates new form User
     */
    public UserGui() {
        setClosable(true);
        initComponents();
        userModel = new MyTableModel();
	jTable2.setModel(userModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        legalDepartment = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        telephone = new javax.swing.JTextField();
        btnRegister = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        telephone1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        telephone2 = new javax.swing.JTextField();
        telephone3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        email1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        nameText1 = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();

        setTitle("Mantenimiento de Usuarios");

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Usuarios"));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellidos", "Contraseña", "Documento ", "Telefono"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jScrollPane1.setViewportView(jScrollPane2);

        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211))
        );

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulario de Creación"));

        jLabel1.setText("Nombre*");

        nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextActionPerformed(evt);
            }
        });

        jLabel3.setText("Apellidos*");

        jLabel4.setText("Tipo de Documento*");

        btnRegister.setText("Registrar");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnCancel.setText("Dar de baja");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnUpdate.setText("Modificar");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel6.setText("Contraseña*");

        jLabel7.setText("Telefono*");

        jLabel8.setText("Número de documento*");

        jLabel2.setText("Fecha de Nacimiento* (dd/mm/yyyy)");

        jLabel9.setText("Rol*");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 - Administrador", "2 - Colaborador" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel10.setText("ID");

        nameText1.setEditable(false);
        nameText1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameText1ActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(nameText, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(legalDepartment, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(telephone, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnRegister, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnCancel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnUpdate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(telephone1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(telephone2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(telephone3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(email1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jComboBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(nameText1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane2Layout.createSequentialGroup()
                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(42, 42, 42)
                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(telephone, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(telephone1)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(nameText1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nameText, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(legalDepartment, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(2, 2, 2))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane2Layout.createSequentialGroup()
                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(67, 67, 67)
                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(telephone2)
                                    .addComponent(telephone3, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(email1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(217, 217, 217))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(20, 20, 20)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(legalDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telephone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telephone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telephone3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(email1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 66, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancel)
                            .addComponent(btnUpdate)
                            .addComponent(btnRegister))
                        .addContainerGap())))
        );

        btnBack.setText("Regresar");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane2)
                    .addComponent(jLayeredPane1))
                .addGap(0, 13, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:
        try {
                                String name = nameText.getText();
                                String lastname= legalDepartment.getText();
                                String password = telephone.getText();
                                String typedocument= telephone1.getText();
                                String document= telephone2.getText();
                                String telephone= telephone3.getText();

                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                Date bornday = formatter.parse(email1.getText());
                                
                                try{                       
                                if(telephone.length()>=7 && telephone.length()<=9 && Integer.parseInt(telephone)>0){
                                
                                    User user = new User();
                                user.setBornDay(bornday);
                                user.setDocCode(document);
                                user.setDocType(typedocument);
                                user.setLastName(lastname);
                                user.setName(name);
                                user.setPassword(password);
                                user.setPhone(telephone);
                                
                                String role= (String)jComboBox1.getSelectedItem();
                                Profile profile;
                                profile=  Manager.queryProfileById(Long.parseLong(role.substring(0,1)));
                                user.setProfile(profile);
                                user.setStatus("Activo");
                                addUser(user);
                                 java.lang.System.out.println("Agrego nuevo usuario");
                                 refreshTblUser();
                                
                                
                                }else{
                                    JOptionPane.showMessageDialog(this, "Telefono debe contener entre 7 y 9 números", "Alerta", JOptionPane.WARNING_MESSAGE);
                                }
                                }catch (NumberFormatException ex) {
                               //  Logger.getLogger(ElectoralProcess.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(this, "Telefono contiene caracteres", "Alerta", JOptionPane.WARNING_MESSAGE);
				} 
                                
                                }catch (ParseException ex) {
                               //  Logger.getLogger(ElectoralProcess.class.getName()).log(Level.SEVERE, null, ex);					e.printStackTrace();
				} 
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
          try {
                                java.lang.System.out.println("Entro al update user ");
                                String name = nameText.getText();
                                java.lang.System.out.println("Nombre ");
                                String lastname= legalDepartment.getText();
                                java.lang.System.out.println("Apellido ");
                                String password = telephone.getText();
                                java.lang.System.out.println("Contrasena ");
                                String typedocument= telephone1.getText();
                                java.lang.System.out.println("Tipo de documento");
                                String document= telephone2.getText();
                                java.lang.System.out.println("Documento ");
                                String telephone= telephone3.getText();
                                java.lang.System.out.println("Telefono");
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                Date bornday = formatter.parse(email1.getText());
                                java.lang.System.out.println("Fecha ");
                                long idUser = Long.parseLong("" + nameText1.getText());
                                java.lang.System.out.println("Id user: " + idUser);
                                User user = Manager.queryUserById(idUser);
                                java.lang.System.out.println("Usuario: " + user.getId() + " - " + user.getName());
                                user.setBornDay(bornday);
                                user.setDocCode(document);
                                user.setDocType(typedocument);
                                user.setLastName(lastname);
                                user.setName(name);
                                user.setPassword(password);
                                user.setPhone(telephone);
                                String role= (String)jComboBox1.getSelectedItem();
                                Profile profile;
                                profile=  Manager.queryProfileById(Long.parseLong(role.substring(0,1)));
                                user.setProfile(profile);
                                user.setStatus("Activo");
                                updateUser(user);
                                 java.lang.System.out.println("Usuario modificado");
                                 refreshTblUser();
                                 }catch (ParseException ex) {
                               //  Logger.getLogger(ElectoralProcess.class.getName()).log(Level.SEVERE, null, ex);					e.printStackTrace();
				} 
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        JFrame frame1 = null;
        // TODO add your handling code here:
         int res = JOptionPane.showConfirmDialog(frame1, "¿Está seguro?");
			if (res == JOptionPane.OK_OPTION) {
				try {
					deleteUser(Integer.parseInt(nameText1.getText()));
                                        refreshTblUser();
                                        //jTable2.clear();
                                        //jTable2.addAll(tableQuery.getResultList());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			//	refreshTblSalesmans();
			}
    }//GEN-LAST:event_btnCancelActionPerformed

    private void nameText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameText1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameText1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        try {
        int selRow = jTable2.getSelectedRow();
				int userId = Integer.parseInt(
						jTable2.getValueAt(selRow, 0).toString());
				User p = Manager.queryUserById(userId);
				nameText1.setText("" + p.getId());
				nameText.setText(p.getName());
                                legalDepartment.setText(p.getLastName());
                                telephone.setText(p.getPassword());
                                telephone1.setText(p.getDocType());
                                telephone2.setText(p.getDocCode());
                                telephone3.setText(p.getPhone());
                                 String bornDay= p.getBornDay().toString();
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                Date bornday = formatter.parse(bornDay);
                                email1.setText(""+bornday);
                                 java.lang.System.out.println("Usuario seleccionado");
                                 }catch (ParseException ex) {
                               //  Logger.getLogger(ElectoralProcess.class.getName()).log(Level.SEVERE, null, ex);					e.printStackTrace();
				} 
    }//GEN-LAST:event_jTable2MouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed
     class MyTableModel extends AbstractTableModel {
        ArrayList<Model.User> userList = Manager.queryAllUsers();
		String [] titles = {"ID", "Nombre","Apellidos", "Contraseña", "Documento","Telefono"};
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 6;
		}

		public int getRowCount() {
			// TODO Auto-generated method stub
			return userList.size();
		}

		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			String value = "";
			switch (col) {
			case 0:
				value = "" + userList.get(row).getId();
				break;
			case 1:
                                value = "" + userList.get(row).getName();
				break;
			case 2:
				value = "" + userList.get(row).getLastName();
				break;	
                        case 3:
				value = "" + userList.get(row).getPassword();
				break;	
                        case 4:
				value = "" + userList.get(row).getDocCode();
				break;	
                        case 5:
				value = "" + userList.get(row).getPhone();
				break;	
			}
			return value;
		}
		public String getColumnName(int col){
			return titles[col];
		}
    }
    public void refreshTblUser() {
		userModel.userList = Manager.queryAllUsers();
		userModel.fireTableChanged(null);
    }
    
    public void mouseClicked(MouseEvent e){
				int selRow = jTable2.getSelectedRow();
				int userId = Integer.parseInt(
						jTable2.getValueAt(selRow, 0).toString());
				User p = Manager.queryUserById(userId);
				nameText1.setText("" + p.getId());
				nameText.setText(p.getName());
                                legalDepartment.setText(p.getLastName());
                                telephone.setText(p.getPassword());
                                telephone1.setText(p.getDocType());
                                telephone2.setText(p.getDocCode());
                                telephone3.setText(p.getPhone());
                                email1.setText(""+p.getBornDay());
                                String profile= p.getProfile().getName();
                                jComboBox1.setSelectedItem(profile);
                                 java.lang.System.out.println("Usuario seleccionado");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField email1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField legalDepartment;
    private javax.swing.JTextField nameText;
    private javax.swing.JTextField nameText1;
    private javax.swing.JTextField telephone;
    private javax.swing.JTextField telephone1;
    private javax.swing.JTextField telephone2;
    private javax.swing.JTextField telephone3;
    // End of variables declaration//GEN-END:variables
}
