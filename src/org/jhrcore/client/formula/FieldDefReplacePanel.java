/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FieldDefReplacePanel.java
 *
 * Created on 2009-8-17, 15:09:09
 */
package org.jhrcore.client.formula;

import org.jhrcore.ui.listener.IPickFieldSelectListener;
import org.jhrcore.ui.FormulaParaFieldSelectPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jhrcore.client.CommUtil;
import org.jhrcore.util.ComponentUtil;
import org.jhrcore.client.UserContext;
import org.jhrcore.client.system.autoexcute.ExcuteTreeModel;
import org.jhrcore.entity.Code;
import org.jhrcore.comm.CodeManager;
import org.jhrcore.entity.DeptCode;
import org.jhrcore.entity.annotation.ObjectListHint;
import org.jhrcore.entity.base.EntityDef;
import org.jhrcore.entity.base.FieldDef;
import org.jhrcore.entity.base.TempFieldInfo;
import org.jhrcore.entity.salary.ValidateSQLResult;
import org.jhrcore.ui.CodeSelectDialog;
import org.jhrcore.ui.ContextManager;
import org.jhrcore.ui.FormulaEditorPanel;
import org.jhrcore.ui.HrTextPane;
import org.jhrcore.ui.ModelFrame;
import org.jhrcore.ui.listener.IPickFormulaEditorListener;
import org.jhrcore.ui.renderer.HRRendererView;
import org.jhrcore.util.MsgUtil;

/**
 *
 * @author Administrator
 */
public class FieldDefReplacePanel extends javax.swing.JPanel {

    private FormulaEditorPanel pnlEditor = new FormulaEditorPanel();
    private HrTextPane jtaFormulaText;
    private JTree para_tree;
    private ExcuteTreeModel para_model;
    private String sql;
    private String qstr;
    private String module_code;
    private String entity_name;
    private JComboBoxBinding entity_binding;
    private JComboBoxBinding field_binding;
    private DeptCode dept;
    private IFieldDefReplaceListener listener;

    public void addListener(IFieldDefReplaceListener listener) {
        this.listener = listener;
    }

    /** Creates new form FieldDefReplacePanel */
    public FieldDefReplacePanel(String module_code, String entity_name, DeptCode dept, String qstr) {
        this.module_code = module_code;
        this.entity_name = entity_name;
        this.dept = dept;
        this.qstr = qstr;
        initComponents();
        initOthers();
        setupEvents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        pnlForEditor = new javax.swing.JPanel();
        pnlPara = new javax.swing.JPanel();
        btnCheck = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        jSplitPane1.setDividerLocation(150);
        jSplitPane1.setDividerSize(3);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(645, 500));

        jPanel3.setLayout(new java.awt.BorderLayout());
        jSplitPane1.setTopComponent(jPanel3);

        pnlForEditor.setLayout(new java.awt.BorderLayout());

        pnlPara.setBorder(javax.swing.BorderFactory.createTitledBorder("字段属性"));
        pnlPara.setLayout(new java.awt.BorderLayout());

        btnCheck.setText("校验");

        btnClear.setText("清空");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(pnlForEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlPara, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(btnClear)
                        .addGap(64, 64, 64)
                        .addComponent(btnCheck)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCheck)
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pnlForEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(47, Short.MAX_VALUE))
                    .addComponent(pnlPara, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
        );

        jSplitPane1.setRightComponent(jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("表名：");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("字段：");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnOk.setText("确定");

        btnCancel.setText("取消");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(304, Short.MAX_VALUE)
                .addComponent(btnOk)
                .addGap(82, 82, 82)
                .addComponent(btnCancel)
                .addGap(145, 145, 145))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel pnlForEditor;
    private javax.swing.JPanel pnlPara;
    // End of variables declaration//GEN-END:variables

    private void initOthers() {
        pnlForEditor.add(pnlEditor, BorderLayout.CENTER);
        jtaFormulaText = new HrTextPane();
        jPanel3.add(jtaFormulaText);

        List entity_list = new ArrayList();
        List para_list = new ArrayList();
        entity_list.addAll(CommUtil.fetchEntities("from EntityDef ed  where ed.entityName = '" + entity_name + "'"));
        para_list.addAll(CommUtil.fetchEntities("from QueryCommPara qcp where qcp.para_code ='" + module_code + "'"));
        para_model = new ExcuteTreeModel(module_code);
        para_tree = new JTree(para_model);
        HRRendererView.getParaFieldTypeMap(para_tree).initTree(para_tree);
        pnlPara.add(new JScrollPane(para_tree), BorderLayout.CENTER);
        entity_binding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, entity_list, jComboBox1);
        entity_binding.bind();
        EntityDef entityDef = ((EntityDef) entity_list.get(0));
        List field_list = CommUtil.fetchEntities("from FieldDef fd join fetch fd.entityDef where fd.entityDef.entity_key='" + entityDef.getEntity_key() + "'");
        String tmp_str = entityDef.getEntityName().toUpperCase() + ".";
        List tmp_list = new ArrayList();
        for (Object obj : field_list) {
            FieldDef fieldDef = (FieldDef) obj;
            String str2 = tmp_str + fieldDef.getField_name().toUpperCase();
            if (UserContext.hasFieldRight(str2) && UserContext.getFieldRight(str2) == 1) {
                tmp_list.add(fieldDef);
            }
        }
        field_binding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, tmp_list, jComboBox2);
        field_binding.bind();
        jtaFormulaText.revokeDocumentKeys(para_model.getLookups(), para_model.getKeyword_groups(), para_model.getK_keywords());
    }

    private void setupEvents() {
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sql = transfer_to_SQL(jtaFormulaText.getText());
                ValidateSQLResult validateSQLResult = CommUtil.validateSQL(sql, true);
                if (validateSQLResult.getResult() == 0) {
                    CommUtil.excuteSQL(sql);
                    JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(btnOk), "替换成功",
                            "提示", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.getFrameForComponent(btnCancel).dispose();
                    listener.reflesh();
                } else {
                    JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(btnOk), "替换失败",
                            "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.getFrameForComponent(btnCancel).dispose();
            }
        });
        btnCheck.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sql = transfer_to_SQL(jtaFormulaText.getText());
                ValidateSQLResult validateSQLResult = CommUtil.validateSQL(sql, true);
                String sql_msg = sql;
                if (validateSQLResult.getResult() != 0) {
                    sql_msg += ";\n错误提示：\n    " + validateSQLResult.getMsg();
                }
                MsgUtil.showHRValidateMsg(sql_msg, "", validateSQLResult.getResult() == 0);
            }
        });
        pnlEditor.addPickFormulaEditorListener(new IPickFormulaEditorListener() {

            @Override
            public void pickEditor(String operator) {
                //operator:公式编辑器Panel返回的运算符,如：where
                int tmp = jtaFormulaText.getSelectionStart();
                jtaFormulaText.replaceSelection(operator.toLowerCase());
                jtaFormulaText.setCaretPosition(tmp + operator.length());
                jtaFormulaText.requestFocus();
            }
        });
        btnClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jtaFormulaText.setText("");
            }
        });
        para_tree.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() < 2) {
                    return;
                }

                if (para_tree.getSelectionPath() == null) {
                    return;
                }

                if (para_tree.getSelectionPath().getLastPathComponent() == para_tree.getModel().getRoot()) {
                    return;
                }
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) para_tree.getSelectionPath().getLastPathComponent();
                Object obj = node.getUserObject();
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
                final int tmp = jtaFormulaText.getSelectionStart();
                String operator = "";
                if (obj instanceof String || obj instanceof EntityDef) {
                    if (node.getLevel() == 0) {
                        return;
                    }
                    List<TempFieldInfo> fields = new ArrayList<TempFieldInfo>();
                    Enumeration enumt = node.children();
                    while (enumt.hasMoreElements()) {
                        DefaultMutableTreeNode child = (DefaultMutableTreeNode) enumt.nextElement();
                        fields.add((TempFieldInfo) child.getUserObject());
                    }
                    FormulaParaFieldSelectPanel pnlFpfs = new FormulaParaFieldSelectPanel(obj.toString(), fields);
                    pnlFpfs.addPickFieldSelectListener(new IPickFieldSelectListener() {

                        @Override
                        public void pickFieldString(String text) {
                            jtaFormulaText.replaceSelection(text);
                            jtaFormulaText.setCaretPosition(tmp + text.length());
                            jtaFormulaText.requestFocus();
                        }
                    });
                    ModelFrame.showModel((JFrame) JOptionPane.getFrameForComponent(jtaFormulaText), pnlFpfs, true, "请选择字段:", 750, 650);
                    return;
                }

                String entity_name = "[" + parent.getUserObject().toString() + ".";
                boolean isPara = obj.toString().startsWith("@");
                if (obj instanceof TempFieldInfo && ((TempFieldInfo) obj).getField_name().endsWith("_code_")) {
                    TempFieldInfo tfi = (TempFieldInfo) obj;
                    ObjectListHint objHint = tfi.getField().getAnnotation(ObjectListHint.class);
                    if (objHint != null && objHint.hqlForObjectList().startsWith("from Code ")) {
                        String hql = objHint.hqlForObjectList();
                        String code_type = hql.substring(hql.indexOf("=") + 1);
                        CodeSelectDialog csmDlg = new CodeSelectDialog(CodeManager.getCodeManager().getCodeListBy(code_type), code_type);

                        ContextManager.locateOnMainScreenCenter(csmDlg);
                        csmDlg.setVisible(true);
                        if (csmDlg.isClick_ok()) {
                            List<Code> codes = csmDlg.getSelectCodes(false);
                            if (codes.size() == 0) {
                                return;
                            }
                            String str = "";
                            List<String> like_str = new ArrayList<String>();
                            for (Code c : codes) {
                                if (c.isEnd_flag()) {
                                    str += "'[" + code_type + "." + c.getCode_name() + "]',";
                                } else {
                                    like_str.add("[" + code_type + "." + c.getCode_name() + "]");
                                }
                            }
                            if (!str.equals("")) {
                                str = str.substring(0, str.length() - 1);
                            }
                            for (String s : like_str) {
                                operator += " " + entity_name + tfi.getCaption_name() + "] like '" + s + "%' or ";
                            }
                            if (!str.equals("")) {
                                operator += " " + entity_name + tfi.getCaption_name() + "] in(" + str + ") or ";
                            }
                            operator = operator.substring(0, operator.length() - 3);
                        }
                    } else {
                        operator = " " + entity_name + obj.toString() + "] ";
                    }
                } else {
                    operator = " " + entity_name + obj.toString() + "] ";
                }
                if (isPara) {
                    operator = operator.replace("[", "");
                    operator = operator.replace("]", "");
                }
                jtaFormulaText.replaceSelection(operator);
                jtaFormulaText.setCaretPosition(tmp + operator.length());
                jtaFormulaText.requestFocus();

            }
        });
        ComponentUtil.refreshJSplitPane(jSplitPane1, "JSplitPane.FieldDefReplacePanel.jSplitPane1");
    }

    private String transfer_to_SQL(String formula_meaning) {
        String tmp_str = ((EntityDef) jComboBox1.getSelectedItem()).getEntityName();
        String str = "update " + tmp_str + " set " + ((EntityDef) jComboBox1.getSelectedItem()).getEntityName() + "." + ((FieldDef) jComboBox2.getSelectedItem()).getField_name() + " = ";
        formula_meaning = str + formula_meaning;
        Hashtable<String, String> k_keywords = jtaFormulaText.getK_keywords();
        for (String key : k_keywords.keySet()) {
            formula_meaning = formula_meaning.replace(key, k_keywords.get(key));
        }
        if ("A01".equals(tmp_str)) {
            formula_meaning += " where " + tmp_str + ".deptCode_key in(select deptCode_key from DeptCode where dept_code like '" + dept.getDept_code() + "%')";
        } else {
            formula_meaning = formula_meaning + qstr;
        }
        return formula_meaning;
    }
}
