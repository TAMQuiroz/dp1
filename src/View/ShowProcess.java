/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import BusinessModel.Manager;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.table.AbstractTableModel;
import Model.*;
import javax.swing.JOptionPane;
/**
 *
 * 
 */
public class ShowProcess extends JInternalFrame {
private javax.swing.JDesktopPane jDesktopPane1;
     Integer idElectoralProcess;

    public ShowProcess() {
        setClosable(true);
        initComponents();
        electoralProcessModel = new MyTableModel();
	jTable1.setModel(electoralProcessModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de Procesos Electorales");
        setToolTipText("");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Tipo de proceso", "Etapa", "Fecha de proceso"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setToolTipText("Ingresar nombre...");

        jButton1.setText("Buscar proceso");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Ver Partidos Politicos");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ver procesos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Busqueda:");

        jLabel1.setText("Proceso Electoral Seleccionado: ");

        jTextField2.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addGap(226, 226, 226))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addComponent(jToggleButton1)
                .addGap(39, 39, 39))
        );

        jScrollPane1.getAccessibleContext().setAccessibleParent(jTable1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
     /* ElectoralProcessGui If= new ElectoralProcessGui();
      jDesktopPane1.add(If);
       If.show();*/
        if(!jTextField2.getText().equals("")){
            idElectoralProcess=Integer.parseInt(jTextField2.getText());
            new ElectoralProcessGui(idElectoralProcess).setVisible(true);
        }else{
            new ElectoralProcessGui().setVisible(true);
        }
         
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
       
        int row = jTable1.getSelectedRow();
        if(row != -1){
            String processId = (String) jTable1.getValueAt(row, 0);
            idElectoralProcess= Integer.parseInt(processId);
            String processName = (String) jTable1.getValueAt(row, 1);
            String processStage = (String) jTable1.getValueAt(row, 3);
            //java.lang.System.out.println(processName);
            if(UtilLib.checkStage(idElectoralProcess)==5){
                PoliticalPartyGui view = new PoliticalPartyGui(Integer.parseInt(processId),processName, processStage);
                view.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(this, "No se encuentra en etapa de Registro", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "No se eligio un proceso electoral", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         int selRow = jTable1.getSelectedRow();
				int ProcessId = Integer.parseInt(
						jTable1.getValueAt(selRow, 0).toString());
                                 java.lang.System.out.println(ProcessId);
				ElectoralProcess p = Manager.queryElectoralProcessById(ProcessId);
                                jTextField2.setText("" + p.getId());
                           java.lang.System.out.println("Ubigeo seleccionado");
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(ShowProcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowProcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowProcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowProcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowProcess().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
    MyTableModel electoralProcessModel;
    
    class MyTableModel extends AbstractTableModel {
        ArrayList<Model.ElectoralProcess> electoralProcessList = Manager.queryAllElectoralProcess();
		String [] titles = { "ID","Nombre", "Tipo Proceso","Etapa","Fecha Proceso"};
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 5;
		}

		public int getRowCount() {
			// TODO Auto-generated method stub
			return electoralProcessList.size();
		}

		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			String value = "";
			switch (col) {
                        case 0:
				value = "" + electoralProcessList.get(row).getId();
				break;    
			case 1:
				value = "" + electoralProcessList.get(row).getName();
				break;
			case 2:
				value = "" + electoralProcessList.get(row).getProcessType().getName();
				break;
                        case 3: 
                                int stage=electoralProcessList.get(row).getStage();
                                String stagename=null;
                                if(stage==0)stagename="Primera Recepción";
                                if(stage==1)stagename="Primera Validación";
                                if(stage==2)stagename="Segunda Recepción";
                                if(stage==3)stagename="Segunda Validación";
                                if(stage==4)stagename="Finalizado";
                                if(stage==5)stagename="Fase de Registro";
                                if(stage==-1)stagename="En Proceso";
				value = "" + stagename;
				break;	        
			case 4:
				value = "" + electoralProcessList.get(row).getDate();
				break;			
			}
			return value;
		}
		public String getColumnName(int col){
			return titles[col];
		}
    }
    public void refreshTblElectoralProcess() {
		electoralProcessModel.electoralProcessList = Manager.queryAllElectoralProcess();
		electoralProcessModel.fireTableChanged(null);
    }
}
