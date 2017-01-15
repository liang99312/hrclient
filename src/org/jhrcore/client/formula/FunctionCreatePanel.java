/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FunctionCreatePanel.java
 *
 * Created on 2009-5-9, 16:15:18
 */
package org.jhrcore.client.formula;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.jhrcore.ui.WizardPanel;
import org.jhrcore.entity.base.TempFieldInfo;

/**
 *
 * @author mxliteboss
 */
public class FunctionCreatePanel extends WizardPanel {

    private CreateNewFunctionParam createNewFunctionParam;
    private HashSet<String> fields = new HashSet<String>();
    private String field1;
    private String field2;

    /** Creates new form FunctionCreatePanel */
    public FunctionCreatePanel(CreateNewFunctionParam para) {
        createNewFunctionParam = para;
        initComponents();
        initOthers();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jcb1 = new javax.swing.JComboBox();
        jcb2 = new javax.swing.JComboBox();
        jcb3 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setText("�����ú�������Ĳ�����������ɡ�������");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Ӧ˰���ʶ");

        jLabel6.setText("���ÿ۳���׼��");

        jLabel7.setText("���ü��ࣺ");

        jcb3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "��˰����", "����˰����" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcb1, 0, 91, Short.MAX_VALUE)
                            .addComponent(jcb2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcb3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jcb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jcb3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("˵����");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public boolean isValidate() {
        if (field1 == null) {
            field1 = jcb1.getSelectedItem().toString();
        }
        if (field2 == null) {
            field2 = jcb2.getSelectedItem().toString();
        }
        if (field1 == null || field2 == null) {
            return false;
        }
        if (fields.contains(field1) && fields.contains(field2)) {
            return true;
        }
        if (!fields.contains(field1)) {
            for (int i = 0; i < field1.length(); i++) {
                if ((field1.charAt(i) < '0') || (field1.charAt(i) > '9')) {
                    JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(jcb1), "��ѡ���ֶλ�����������");
                    return false;
                }
            }
        } else if (!fields.contains(field2)) {
            for (int i = 0; i < field2.length(); i++) {
                if ((field2.charAt(i) < '0') || (field2.charAt(i) > '9')) {
                    JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(jcb1), "��ѡ���ֶλ�����������");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void beforeLeave() {
        List fun_infos = new ArrayList ();
        fun_infos.add(jcb1.getSelectedItem());
        fun_infos.add(jcb2.getSelectedItem());
        createNewFunctionParam.setFun_infos(fun_infos);
        createNewFunctionParam.setFun_para(jcb3.getSelectedItem());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox jcb1;
    private javax.swing.JComboBox jcb2;
    private javax.swing.JComboBox jcb3;
    // End of variables declaration//GEN-END:variables

    private void initOthers() {
        jcb1.setEditable(true);
        jcb2.setEditable(true);
        jLabel3.setText(createNewFunctionParam.getFunctionModel().getFunction_caption());
        jLabel4.setText(createNewFunctionParam.getFunctionModel().getFunction_name());
        List<TempFieldInfo> all_fields = createNewFunctionParam.getFun_fields();
        List<TempFieldInfo> binding_fields = new ArrayList<TempFieldInfo>();
        for (TempFieldInfo tfi : all_fields) {
            if (tfi.getField_type().equals("Integer") || tfi.getField_type().equals("int") || tfi.getField_type().equalsIgnoreCase("Float") || tfi.getField_type().equalsIgnoreCase("BigDecimal")) {
                binding_fields.add(tfi);
                fields.add(tfi.getCaption_name());
            }
        }
        if (createNewFunctionParam.getFunctionModel().getFuntion_code().equals("Tax")) {
            for (TempFieldInfo tfi : binding_fields) {
                jcb1.addItem(tfi);
                jcb2.addItem(tfi);
            }
        }
        final JTextField editorField = (JTextField) jcb1.getEditor().getEditorComponent();
        editorField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                Runnable r1 = new Runnable() {

                    @Override
                    public void run() {
                        if (editorField.getText() != null) {
                            field1 = editorField.getText();
                        }
                    }
                };
                SwingUtilities.invokeLater(r1);

            }
        });
        final JTextField editorField1 = (JTextField) jcb2.getEditor().getEditorComponent();

        editorField1.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                Runnable r1 = new Runnable() {

                    @Override
                    public void run() {
                        if (editorField1.getText() != null) {
                            field2 = editorField1.getText();
                        }
                    }
                };
                SwingUtilities.invokeLater(r1);

            }
        });
    }

    @Override
    public String getTitle() {
        return "���һ�������ú�����ز���";
    }
}