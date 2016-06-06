/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import BusinessModel.Manager;
import Model.AdherentImage;
import Model.Person;
import Model.PoliticalParty;
import static View.SignatureLib.validarFirmas;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract1;
/**
 *
 * @author Andrea
 */
public class adherentListi extends javax.swing.JFrame {

    /**
     * Creates new form adherentListi
     */
    long id;
    String name;
    int signaturesVal;
    boolean primera_etapa;
    
    public adherentListi() {

        initComponents();
    }
    
    public adherentListi(long idParty,String nameParty) {

        initComponents();
        id = idParty;
        name = nameParty;
      //  primera_etapa = check_etapa();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        paneAnulados = new javax.swing.JTabbedPane();
        jPaneSinValidar = new javax.swing.JPanel();
        btnValidate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton3 = new javax.swing.JButton();
        jPaneValidados = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableValidated = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jPaneRechazados = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jPaneAnulados = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jButton7 = new javax.swing.JButton();
        jPaneBaneados = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jButton9 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setTitle("Lista de adherente - Proceso Electoral Municipal 2016");

        jLabel1.setText("Cantidad restante:");

        btnValidate.setText("Validar");
        btnValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidateActionPerformed(evt);
            }
        });

        jLabel2.setText("Cantidad:");

        txtAmount.setEditable(false);
        txtAmount.setBackground(new java.awt.Color(204, 204, 204));
        txtAmount.setText("4");
        txtAmount.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmountActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jLabel4.setText("Progreso:");

        jProgressBar1.setToolTipText("");
        jProgressBar1.setValue(25);

        jButton3.setText("Reporte");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPaneSinValidarLayout = new javax.swing.GroupLayout(jPaneSinValidar);
        jPaneSinValidar.setLayout(jPaneSinValidarLayout);
        jPaneSinValidarLayout.setHorizontalGroup(
            jPaneSinValidarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneSinValidarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPaneSinValidarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPaneSinValidarLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(51, 51, 51)
                        .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(btnValidate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3)
                    .addGroup(jPaneSinValidarLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPaneSinValidarLayout.setVerticalGroup(
            jPaneSinValidarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneSinValidarLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPaneSinValidarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneSinValidarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jButton3))
                    .addComponent(btnValidate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addContainerGap())
        );

        paneAnulados.addTab("Sin Validar", jPaneSinValidar);

        tableValidated.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "DNI", "Nombre", "Apellido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableValidated);
        if (tableValidated.getColumnModel().getColumnCount() > 0) {
            tableValidated.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel3.setText("Cantidad:");

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(204, 204, 204));

        jButton5.setText("Reporte");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPaneValidadosLayout = new javax.swing.GroupLayout(jPaneValidados);
        jPaneValidados.setLayout(jPaneValidadosLayout);
        jPaneValidadosLayout.setHorizontalGroup(
            jPaneValidadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneValidadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPaneValidadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                    .addGroup(jPaneValidadosLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(50, 50, 50)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addComponent(jSeparator3))
                .addContainerGap())
        );
        jPaneValidadosLayout.setVerticalGroup(
            jPaneValidadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneValidadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPaneValidadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        paneAnulados.addTab("Validados", jPaneValidados);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "DNI", "Nombre", "Apellido", "Firma", "Huella"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable1);

        jButton2.setText("Admitir Adherente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Cantidad:");

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(204, 204, 204));

        jButton4.setText("Reporte");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPaneRechazadosLayout = new javax.swing.GroupLayout(jPaneRechazados);
        jPaneRechazados.setLayout(jPaneRechazadosLayout);
        jPaneRechazadosLayout.setHorizontalGroup(
            jPaneRechazadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneRechazadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPaneRechazadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneRechazadosLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(51, 51, 51)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPaneRechazadosLayout.setVerticalGroup(
            jPaneRechazadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneRechazadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPaneRechazadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        paneAnulados.addTab("Rechazados", jPaneRechazados);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "DNI", "Nombre", "Apellido", "Firma", "Huella"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable4);

        jLabel6.setText("Cantidad:");

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(204, 204, 204));

        jButton7.setText("Reporte");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPaneAnuladosLayout = new javax.swing.GroupLayout(jPaneAnulados);
        jPaneAnulados.setLayout(jPaneAnuladosLayout);
        jPaneAnuladosLayout.setHorizontalGroup(
            jPaneAnuladosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneAnuladosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPaneAnuladosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneAnuladosLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(51, 51, 51)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPaneAnuladosLayout.setVerticalGroup(
            jPaneAnuladosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneAnuladosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPaneAnuladosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        paneAnulados.addTab("Anulados", jPaneAnulados);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "DNI", "Nombre", "Apellido", "Firma", "Huella"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable5);

        jLabel7.setText("Cantidad:");

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(204, 204, 204));

        jButton9.setText("Reporte");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPaneBaneadosLayout = new javax.swing.GroupLayout(jPaneBaneados);
        jPaneBaneados.setLayout(jPaneBaneadosLayout);
        jPaneBaneadosLayout.setHorizontalGroup(
            jPaneBaneadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneBaneadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPaneBaneadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneBaneadosLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(51, 51, 51)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPaneBaneadosLayout.setVerticalGroup(
            jPaneBaneadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneBaneadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPaneBaneadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        paneAnulados.addTab("Baneados", jPaneBaneados);

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setText("Regresar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paneAnulados)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(196, 196, 196)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneAnulados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        FileOutputStream archivo;
        try {
            archivo = new FileOutputStream("ReporteRechazados.pdf");
            Document documento = new Document();
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            documento.add(new Paragraph("Reporte de Rechazados"));
            documento.add(new Paragraph("DNI        Nombre          Apellido Paterno        Apellido Materno     "));
            /*     for (int i =0; i<listSales.size();i++){
                Sales s = listSales.get(i);
                Customer cus = null;
                if (s.getCustomer() instanceof Person){
                    cus = (Person)s.getCustomer();
                    documento.add(new Paragraph(""+ listSales.get(i).getId() + " " + ((Person)s.getCustomer()).getName()+ " " + s.getTotal() + " "+ s.getDetails().size() ));
                }
                else{
                    cus = (Company)s.getCustomer();
                    documento.add(new Paragraph(""+ listSales.get(i).getId() + " " + ((Company)s.getCustomer()).getName()+ " " + s.getTotal() + " "+ s.getDetails().size() ));
                }
            }*/
            documento.close();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (DocumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new addAdherent().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        FileOutputStream archivo;
        try {
            archivo = new FileOutputStream("ReporteValidados.pdf");
            Document documento = new Document();
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            String titulo="Reporte de Lista Validada del Partido " + name;
            documento.add(new Paragraph(titulo));
            String cuerpo="Se encontraron validas "+signaturesVal+" firmas";
            documento.add(new Paragraph(cuerpo));
            //documento.add(new Paragraph("DNI        Nombre          Apellido Paterno        Apellido Materno     "));
            /*     for (int i =0; i<listSales.size();i++){
                Sales s = listSales.get(i);
                Customer cus = null;
                if (s.getCustomer() instanceof Person){
                    cus = (Person)s.getCustomer();
                    documento.add(new Paragraph(""+ listSales.get(i).getId() + " " + ((Person)s.getCustomer()).getName()+ " " + s.getTotal() + " "+ s.getDetails().size() ));
                }
                else{
                    cus = (Company)s.getCustomer();
                    documento.add(new Paragraph(""+ listSales.get(i).getId() + " " + ((Company)s.getCustomer()).getName()+ " " + s.getTotal() + " "+ s.getDetails().size() ));
                }
            }*/
            documento.close();
            JOptionPane.showMessageDialog(this, "Reporte creado!", "Mensaje", JOptionPane.WARNING_MESSAGE);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (DocumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:jskdkdsjkjds
        FileOutputStream archivo;
        try {
            archivo = new FileOutputStream("ReporteSinValidar.pdf");
            Document documento = new Document();
            PdfWriter.getInstance(documento, archivo);
            String titulo="Reporte de Lista Sin Validar del Partido " + name;
            documento.open();
            documento.add(new Paragraph(titulo));
            documento.add(new Paragraph("DNI        Nombre          Apellido Paterno        Apellido Materno     "));

            /*     for (int i =0; i<listSales.size();i++){
                Sales s = listSales.get(i);
                Customer cus = null;
                if (s.getCustomer() instanceof Person){
                    cus = (Person)s.getCustomer();
                    documento.add(new Paragraph(""+ listSales.get(i).getId() + " " + ((Person)s.getCustomer()).getName()+ " " + s.getTotal() + " "+ s.getDetails().size() ));
                }
                else{
                    cus = (Company)s.getCustomer();
                    documento.add(new Paragraph(""+ listSales.get(i).getId() + " " + ((Company)s.getCustomer()).getName()+ " " + s.getTotal() + " "+ s.getDetails().size() ));
                }
            }*/
            documento.close();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (DocumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAmountActionPerformed

    private boolean check_route(long id){
        String filePathString= "test\\auxiliar\\cortes\\"+id;
        File f = new File(filePathString);
        return f.exists() && f.isDirectory();
    }
    
    private void btnValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidateActionPerformed

        PoliticalParty partido = Manager.queryPoliticalPartyById(id);
        if(Manager.getSession().getId() == partido.getIdWorker()){
            if (check_route(partido.getId())){
                ArrayList<AdherentImage> registros = Manager.queryAdherentImageNoValidatedbyPartyId(partido.getId());
                ITesseract instance_num = new Tesseract1();
                ITesseract instance_let = new Tesseract1();
                instance_num.setTessVariable("tessedit_char_whitelist", "0123456789");
                instance_let.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
                for (AdherentImage registro : registros) {
                    Person persona = ocrLib.ocr(instance_num, instance_let, registro.getDniSource(), registro.getNameSource(), registro.getLastNameSource());
                  /*  if(persona != null){
                        boolean esta_apto = busca_apto(persona, partido.getElectoralProcess().getId());
                        if(esta_apto){
                            long party_id = buscar_duplicidad(persona, partido.getElectoralProcess().getId());
                            if(party_id == -1){
                                double puntuacion1 = huellas(persona.getFingerprint(), registro.getFingerprintSource());
                                double puntuacion2 = firmas(persona.getSignature(), registro.getSignatureSource());
                                boolean resultado = analizar_resultado(puntuacion1, puntuacion2);
                                if(resultado){
                                    java.lang.System.out.println("Se pudo validar a esta persona");
                                    agregar_adherente(persona);
                                    borrar_cortes(registro);
                                    borrar_registro(registro);
                                }else{
                                    java.lang.System.out.println("No se pudo validar a esta persona");
                                    if(primera_etapa){
                                        registro.setStatus(1);
                                    }else{
                                        registro.setStatus(2);
                                    }
                                }
                            }else{
                                java.lang.System.out.println("Se encontro duplicidad referida a esta persona");
                                if(primera_etapa){
                                    retirar_adherente(persona, partido.getId());
                                    banear_adherente(persona);
                                }
                                borrar_cortes(registro);
                                registro.setStatus(2);
                            }
                        }else{
                            java.lang.System.out.println("Esta persona esta baneada, no pertenece al ubigeo, o no esta en condiciones de ejercer la ciudadania");
                            borrar_cortes(registro);
                            registro.setStatus(2);
                        }
                    }else{
                        java.lang.System.out.println("No se pudo determinar quien es esta persona");
                        if(primera_etapa){
                            registro.setStatus(1);
                        }else{
                            registro.setStatus(2);
                        }
                    }*/
                }
                
                JOptionPane.showMessageDialog(this, "Se termino de validar al partido, podra apreciar los resultados en las pestañas correspondientes", "Resultado", JOptionPane.OK_OPTION);
            }else{
                JOptionPane.showMessageDialog(this, "No existe la ruta de los cortes para este partido", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "El trabajador no esta asignado para este partido politico", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
        /*
        Estados encontrados: 0 - Sin validar, 1 - rechazado, 2 - anulado.
        */

        /*
        String filePathString= "test\\auxiliar\\cortes\\"+id+"\\padron";
        File f = new File(filePathString);
        if(f.exists() && !f.isDirectory()) {
            SignatureLib.console=jTextArea1;
            signaturesVal=validarFirmas(id);
        }else{
            JOptionPane.showMessageDialog(this, "No se han procesado los padrones", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
        */
    }//GEN-LAST:event_btnValidateActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnValidate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPaneAnulados;
    private javax.swing.JPanel jPaneBaneados;
    private javax.swing.JPanel jPaneRechazados;
    private javax.swing.JPanel jPaneSinValidar;
    private javax.swing.JPanel jPaneValidados;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTabbedPane paneAnulados;
    private javax.swing.JTable tableValidated;
    private javax.swing.JTextField txtAmount;
    // End of variables declaration//GEN-END:variables
}
