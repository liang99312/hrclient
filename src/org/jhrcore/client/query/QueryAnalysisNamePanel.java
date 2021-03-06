/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QueryAnalysisNamePanel.java
 *
 * Created on 2009-2-22, 17:31:16
 */

package org.jhrcore.client.query;

import java.util.List;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.SwingBindings;
import org.jhrcore.client.CommUtil;
import org.jhrcore.ui.WizardPanel;
import org.jhrcore.entity.query.QueryAnalysisScheme;

/**
 *
 * @author Administrator
 */
public class QueryAnalysisNamePanel extends WizardPanel {
    private QueryAnalysisScheme queryAnalysisScheme;

    /** Creates new form QueryAnalysisNamePanel */
    public QueryAnalysisNamePanel(QueryAnalysisScheme queryAnalysisScheme) {
        initComponents();
        this.queryAnalysisScheme = queryAnalysisScheme;
        jTextField1.setText(queryAnalysisScheme.getQueryAnalysisScheme_name());

        List<String> list = (List<String>) CommUtil.fetchEntities("select distinct queryAnalysisScheme_type from QueryAnalysisScheme where module_name='" + queryAnalysisScheme.getModule_name() + "'");
        SwingBindings.createJComboBoxBinding(UpdateStrategy.READ, list, jComboBox1).bind();
        jComboBox1.setSelectedItem(queryAnalysisScheme.getQueryAnalysisScheme_type());
        jComboBox1.setEditable(true);
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
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        jLabel1.setText("名称：");

        jTextField1.setText("jTextField1");

        jLabel2.setText("类别：");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(141, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean isValidate() {
        return true;
    }

    @Override
    public void beforeLeave() {
        queryAnalysisScheme.setQueryAnalysisScheme_type((jComboBox1.getSelectedItem() == null)? "" : jComboBox1.getSelectedItem().toString());
        queryAnalysisScheme.setQueryAnalysisScheme_name(jTextField1.getText());
    }

    @Override
    public String getTitle() {
        return "第一步：设置方案名称";
    }

}
