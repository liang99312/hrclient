/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormulaFieldSelectDialog.java
 *
 * Created on 2009-1-10, 1:09:03
 */
package org.jhrcore.client.formula;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.jhrcore.entity.FormulaDetail;
import org.jhrcore.util.UtilTool;
import org.jhrcore.entity.base.TempFieldInfo;
import org.jhrcore.ui.action.CloseAction;
import org.jhrcore.ui.renderer.HRRendererView;

/**
 *
 * @author DB2INST3
 */
public class FormulaFieldSelectDialog extends javax.swing.JDialog {

    private String root_text;
    private List field_list;//字段列表
    private List field_list1;//字段列表1，主要用于部门工资在人员工资中的体现
    private JTree field_tree;
    private JTree field_tree1;
    private FormulaFieldTreeModel formulaFieldTreeModel;
    private FormulaFieldTreeModel formulaFieldTreeModel1;
    private List<IPickFormulaDetailListener> iPickFormulaDetailListeners = new ArrayList<IPickFormulaDetailListener>();
    private Object cur_obj;
    private boolean hasManyTap = false;
    private String search_text = "";
    private Set<DefaultMutableTreeNode> selected_nodes = new HashSet<DefaultMutableTreeNode>();
    private String formulaType = "项目";

    public void addPickFormulaDetailListener(IPickFormulaDetailListener listener) {
        iPickFormulaDetailListeners.add(listener);
    }

    public void delPickFormulaDetailListener(IPickFormulaDetailListener listener) {
        iPickFormulaDetailListeners.remove(listener);
    }

    public FormulaFieldSelectDialog(String root_text, List field_list) {
        this(root_text, "工资项目", field_list);
    }

    public FormulaFieldSelectDialog(String root_text, String formulaType, List field_list) {
        super();
        this.setTitle("选择公式字段");
        this.formulaType = formulaType;
        this.root_text = root_text;
        this.field_list = field_list;
        hasManyTap = field_list1 != null && field_list1.size() > 0;
        initComponents();
        initOthers();
        setupEvents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        pnlMain = new javax.swing.JPanel();
        jtpMain = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfDetailCaption = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        jLabel1.setText("字段查找：");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtpMain, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtpMain, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
        );

        jLabel2.setText("公式描述：");

        jCheckBox1.setText("启用公式向导");

        btnOk.setText("确定");

        btnCancel.setText("取消");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfDetailCaption, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnOk)
                        .addGap(26, 26, 26)
                        .addComponent(btnCancel)
                        .addGap(46, 46, 46))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDetailCaption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jCheckBox1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jtfDetailCaption;
    private javax.swing.JTabbedPane jtpMain;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables

    private void initOthers() {
        JPanel pnl = new JPanel(new BorderLayout());
        formulaFieldTreeModel = new FormulaFieldTreeModel(root_text, field_list);
        field_tree = new JTree(formulaFieldTreeModel);
        HRRendererView.getFieldTypeMap(field_tree).initTree(field_tree);
        pnl.add(new JScrollPane(field_tree), BorderLayout.CENTER);
        jtpMain.add(formulaType, pnl);
        if (hasManyTap) {
            JPanel pnl1 = new JPanel(new BorderLayout());
            formulaFieldTreeModel1 = new FormulaFieldTreeModel(root_text, field_list1);
            field_tree1 = new JTree(formulaFieldTreeModel1);
            HRRendererView.getFieldTypeMap(field_tree1).initTree(field_tree1);
            field_tree1.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        if (cur_obj == null) {
                            return;
                        }
                        if (cur_obj instanceof TempFieldInfo) {
                            TempFieldInfo tfi = (TempFieldInfo) cur_obj;
                            FormulaDetail fd = (FormulaDetail) UtilTool.createUIDEntity(FormulaDetail.class);
                            if (tfi.getEntity_name().equalsIgnoreCase("k_day") || tfi.getEntity_name().equalsIgnoreCase("k_bday") || tfi.getEntity_name().equalsIgnoreCase("k_month") || tfi.getEntity_name().equalsIgnoreCase("j_plandeptitem")) {
                                fd.setDetail_name(tfi.getField_name());
                            } else if (tfi.getEntity_name().equalsIgnoreCase("K_leave_standard") || tfi.getEntity_name().equalsIgnoreCase("PersonDayRecord") || tfi.getEntity_name().equalsIgnoreCase("PersonMonthRecord")) {
                                fd.setDetail_name(tfi.getField_name());
                            } else if (tfi.getEntity_name().equalsIgnoreCase("In_bill") || tfi.getEntity_name().startsWith("Inc") || tfi.getEntity_name().startsWith("DeptInc")) {
                                fd.setDetail_name(tfi.getField_name());
                            } else if (tfi.getEntity_name().equalsIgnoreCase("Sales_record") || tfi.getEntity_name().equalsIgnoreCase("Sales_detail") || tfi.getEntity_name().equalsIgnoreCase("Sales_record_ym")) {
                                fd.setDetail_name(tfi.getField_name());
                            } else if (tfi.getEntity_name().equalsIgnoreCase("Dd_detail") || tfi.getEntity_name().equalsIgnoreCase("Twpy_a01") || tfi.getEntity_name().equalsIgnoreCase("Gxjx_a01")
                                    || tfi.getEntity_name().equalsIgnoreCase("Twfd_butget") || tfi.getEntity_name().equalsIgnoreCase("Twfd_sum") || tfi.getEntity_name().equalsIgnoreCase("Twfd_detail")
                                    || tfi.getEntity_name().equalsIgnoreCase("Twfd_osum") || tfi.getEntity_name().equalsIgnoreCase("Twfd_odetail")) {
                                fd.setDetail_name(tfi.getField_name().replace("_code_", ""));
                            } else {
                                fd.setDetail_name(tfi.getEntity_name() + "_" + tfi.getField_name());
                            }
                            fd.setDetail_caption(jtfDetailCaption.getText());
                            fd.setEntity_name(tfi.getEntity_name());
                            fd.setEntity_caption(tfi.getEntity_caption());
                            fd.setUse_flag(true);
                            for (IPickFormulaDetailListener listener : iPickFormulaDetailListeners) {
                                listener.pickFormulaDetail(fd);
                            }
                            dispose();
                        }
                    }
                }
            });
            pnl1.add(new JScrollPane(field_tree1), BorderLayout.CENTER);
            jtpMain.add("部门工资项目", pnl1);
        }
    }

    private void setupEvents() {
        if (hasManyTap) {
            field_tree1.addTreeSelectionListener(new TreeSelectionListener() {

                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) field_tree1.getLastSelectedPathComponent();
                    if (node == null) {
                        return;
                    }
                    cur_obj = node.getUserObject();
                    if (cur_obj == null) {
                        return;
                    }
                    jtfDetailCaption.setText(cur_obj.toString());
                    jtfDetailCaption.updateUI();
                }
            });
        }
        field_tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) field_tree.getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }
                cur_obj = node.getUserObject();
                if (cur_obj == null) {
                    return;
                }
                jtfDetailCaption.setText(cur_obj.toString());
                jtfDetailCaption.updateUI();
            }
        });
        field_tree.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 2) {
                    selectField();
                }
            }
        });
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectField();
            }
        });
        CloseAction.doCloseAction(btnCancel);
        jtpMain.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                selected_nodes.clear();
            }
        });
        jTextField1.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    searchCode();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
    //选择项目

    private void selectField() {
        if (cur_obj == null) {
            return;
        }
        if (cur_obj instanceof TempFieldInfo) {
            TempFieldInfo tfi = (TempFieldInfo) cur_obj;
            String[] entitys = new String[]{
                "k_day", "k_bday", "k_month", "j_plandeptitem", "k_leave_standard", "in_bill", "persondayrecord",
                "personmonthrecord", "paystand", "sales_detail", "sales_record", "sales_record_ym", "dd_detail",
                "twfd_butget", "twfd_sum", "twfd_detail", "twfd_osum", "twfd_odetail", "twpy_a01", "gxjx_a01"};
            List<String> ignoreEntitys = Arrays.asList(entitys);
            FormulaDetail fd = (FormulaDetail) UtilTool.createUIDEntity(FormulaDetail.class);
            if (ignoreEntitys.contains(tfi.getEntity_name().toLowerCase())) {
                fd.setDetail_name(tfi.getField_name().replace("_code_", ""));
            } else if (tfi.getEntity_name().startsWith("In")) {
                fd.setDetail_name(tfi.getField_name());
            } else {
                fd.setDetail_name(tfi.getEntity_name() + "_" + tfi.getField_name());
            }
            fd.setDetail_caption(jtfDetailCaption.getText());
            fd.setEntity_name(tfi.getEntity_name());
            fd.setEntity_caption(tfi.getEntity_caption());
            fd.setUse_flag(true);
            for (IPickFormulaDetailListener listener : iPickFormulaDetailListeners) {
                listener.pickFormulaDetail(fd);
            }
            dispose();
        }
    }

    public void searchCode() {
        String str = jTextField1.getText();
        if (str.equals("")) {
            return;
        }
        str = str.toUpperCase();
        if (!search_text.equals(str)) {
            selected_nodes.clear();
        }
        search_text = str;
        int tabIndex = jtpMain.getSelectedIndex();
        JTree search_tree = field_tree;
        if (tabIndex == 1) {
            search_tree = field_tree1;
        }
        DefaultMutableTreeNode node = locateEmp(search_text, search_tree);
        if (node == null) {
            selected_nodes.clear();
            node = locateEmp(search_text, search_tree);
        }
        if (node == null) {
            return;
        }
        TreeNode[] nodes = node.getPath();
        TreePath path = new TreePath(nodes);
        search_tree.expandPath(path);
        search_tree.scrollPathToVisible(path);
        search_tree.setSelectionPath(path);

    }

    public DefaultMutableTreeNode locateEmp(String val, JTree search_tree) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) search_tree.getModel().getRoot();
        DefaultMutableTreeNode resultNode = null;
        Enumeration deptEnum = node.children();
        Pattern p = Pattern.compile(val);
        try {
            while (deptEnum.hasMoreElements()) {
                DefaultMutableTreeNode tmpNode = (DefaultMutableTreeNode) deptEnum.nextElement();
                TempFieldInfo tfi = (TempFieldInfo) tmpNode.getUserObject();
                String field_val = null;
                Matcher m;
                field_val = tfi.getCaption_name();
                if (field_val != null) {
                    m = p.matcher(field_val);
                    if (m.find()) {
                        if (selected_nodes.contains(tmpNode)) {
                            continue;
                        } else {
                            resultNode = tmpNode;
                            selected_nodes.add(resultNode);
                            break;
                        }
                    }
                }
                field_val = tfi.getPym();
                if (field_val != null) {
                    p = Pattern.compile(val);
                    m = p.matcher(field_val);
                    if (m.find()) {
                        if (selected_nodes.contains(tmpNode)) {
                            continue;
                        } else {
                            resultNode = tmpNode;
                            selected_nodes.add(resultNode);
                            break;
                        }
                    }
                }
            }

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return resultNode;
    }

    class FormulaFieldTreeModel extends DefaultTreeModel {

        private static final long serialVersionUID = 1L;
        private DefaultMutableTreeNode rootNode;
        private List model_list = new ArrayList();

        public FormulaFieldTreeModel(String root_text, List list) {
            super(new DefaultMutableTreeNode());
            rootNode = new DefaultMutableTreeNode(root_text);
            model_list = list;
            this.setRoot(rootNode);
            buildTree();
        }

        public DefaultMutableTreeNode getROOT() {
            return rootNode;
        }

        private void buildTree() {
            rootNode.removeAllChildren();
            for (Object obj : model_list) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(obj);
                rootNode.add(node);
            }
        }
    }
}
