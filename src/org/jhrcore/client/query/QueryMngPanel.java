/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QueryAnalysisModulePanel.java
 *
 * Created on 2009-2-21, 17:02:51
 */
package org.jhrcore.client.query;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import org.apache.log4j.Logger;
import org.jhrcore.client.CommUtil;
import org.jhrcore.ui.WizardDialog;
import org.jhrcore.comm.CodeManager;
import org.jhrcore.util.UtilTool;
import org.jhrcore.entity.annotation.ObjectListHint;
import org.jhrcore.entity.base.ModuleInfo;
import org.jhrcore.entity.query.QueryAnalysisScheme;
import org.jhrcore.entity.query.QueryAnalysisField;
import org.jhrcore.entity.query.QueryScheme;
import org.jhrcore.query3.QueryParamDialog;
import org.jhrcore.rebuild.EntityBuilder;
import org.jhrcore.ui.task.IModulePanel;
import org.jhrcore.ui.renderer.HRRendererView;

/**
 *
 * @author Administrator
 *
 * 注意在查询分析结果里面，如果是编码字段并且是普通类型，
 * 值转换成 编码名称?code_id编码，比如 男?code_id0 或者 女?code_id1，在显示的时候仅显示 ?code_id 前面的值，后面的编码用于穿透的时候使用
 */
public class QueryMngPanel extends JPanel implements IModulePanel {

    private static Logger log = Logger.getLogger(QueryMngPanel.class.getName());
    private JTree schemeTree;
    private ModuleSchemeModel moduleSchemeModel;
    private JButton btnExecute = new JButton("执行查询");
    private JButton btnNew = new JButton("新建查询");
    private JButton btnEdit = new JButton("编辑");
    private JButton btnSave = new JButton("另存");
    private JButton btnDel = new JButton("删除");
    private JButton btnRight = new JButton("授权");
    private JCheckBox btnShape = new JCheckBox("图形显示");
    private JButton btnSaveShape = new JButton("保存图形");
    private JButton btnCopyShape = new JButton("复制图形");
    private JButton btnPrintShape = new JButton("打印图形");
    private JTable table;
    private QATableModel model = new QATableModel();
    private QueryAnalysisScheme cur_QueryAnalysisScheme;
    private ModuleInfo module_info;
    private static Hashtable<String, String> ht_codes = new Hashtable<String, String>();
    // 单独的模块查询功能
    private List list_module = null;
    public static final String module_code = "QueryMng";

    /** Creates new form QueryAnalysisModulePanel */
    public QueryMngPanel() {
        initComponents();
        initUI();
        setupEvents();

    }

    public QueryMngPanel(List list_module) {
        initComponents();
        this.list_module = list_module;
        initUI();
        setupEvents();
    }

    @Override
    public void setFunctionRight() {

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        pnlLeft = new javax.swing.JPanel();
        pnlRight = new javax.swing.JPanel();
        toolbar = new javax.swing.JToolBar();
        pnlMain = new javax.swing.JPanel();

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setDividerSize(3);

        pnlLeft.setLayout(new java.awt.BorderLayout());
        jSplitPane1.setLeftComponent(pnlLeft);

        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        pnlMain.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout pnlRightLayout = new javax.swing.GroupLayout(pnlRight);
        pnlRight.setLayout(pnlRightLayout);
        pnlRightLayout.setHorizontalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
        );
        pnlRightLayout.setVerticalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRightLayout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(pnlRight);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables

    public static String buildSQL(JPanel pnlLeft, QueryAnalysisScheme queryAnalysisScheme, QueryScheme queryScheme2) {
        // 从查询的实体开始，到包含该属性的实体，所有实体名，用分号分开。如"BasePerson;Dept"
        // 表示该字段与所属实体之间的关系，直到查询实体，用分号分开。如“2；1”，1：普通属性；2：实体属性；3：集合属性
        boolean bhasGroupField = false;
        for (QueryAnalysisField qaf : queryAnalysisScheme.getQueryAnalysisFields()) {
            if (!qaf.getStat_type().equals("普通")) {
                bhasGroupField = true;
                break;
            }
        }

        ht_codes.clear();

        String fields = "";
        String group_fields = "";
        String order_fields = "";

        String tables = "";
        String joins = "";

        int ind = -1;

        for (QueryAnalysisField qaf : queryAnalysisScheme.getQueryAnalysisFields()) {
            ind++;
            String field_name = qaf.getEntity_name() + "." + qaf.getField_name();
            if (qaf.getField_name().endsWith("_code_")) {
                if (qaf.getStat_type().equals("普通")) {
                    String fullClassName = EntityBuilder.getHt_entity_classes().get(qaf.getEntity_name());
                    try {
                        Class aclass = Class.forName(fullClassName);
                        Field field = aclass.getField(qaf.getField_name());
                        ObjectListHint objectListHint = field.getAnnotation(ObjectListHint.class);
                        ht_codes.put("" + ind, objectListHint.hqlForObjectList().substring(objectListHint.hqlForObjectList().indexOf("=") + 1));
                    } catch (NoSuchFieldException ex) {
                        log.error(ex);
                    } catch (SecurityException ex) {
                        log.error(ex);
                    } catch (ClassNotFoundException ex) {
                        log.error(ex);
                    }
                }
                field_name = qaf.getEntity_name() + "." + qaf.getField_name().substring(0, qaf.getField_name().length() - 6);
            }
            if (!fields.equals("")) {
                fields = fields + ",";
            }
            if (qaf.getStat_type().equals("普通")) {
                fields = fields + field_name + " " + qaf.getField_caption();//+ qaf.getField_name();
                if (bhasGroupField) {
                    if (!group_fields.equals("")) {
                        group_fields = group_fields + ",";
                    }
                    group_fields = group_fields + field_name;
                }
            } else if (qaf.getStat_type().equals("计数")) {
                fields = fields + "count(" + field_name + ") " + qaf.getField_name();
            } else if (qaf.getStat_type().equals("平均")) {
                fields = fields + "avg(" + field_name + ") " + qaf.getField_name();
            } else if (qaf.getStat_type().equals("求和")) {
                fields = fields + "sum(" + field_name + ") " + qaf.getField_name();
            }

            if (qaf.getOrder_type().equals("不排序")) {
            } else if (qaf.getOrder_type().equals("升序")) {
                if (!order_fields.equals("")) {
                    order_fields = order_fields + ",";
                }
                order_fields = order_fields + field_name;
            } else if (qaf.getOrder_type().equals("降序")) {
                if (!order_fields.equals("")) {
                    order_fields = order_fields + ",";
                }
                order_fields = order_fields + field_name + " desc";
            }

            if (!tables.contains(qaf.getEntity_name())) {
                if (!tables.equals("")) {
                    tables = tables + ",";
                }
                tables = tables + qaf.getEntity_name();
            }

            String[] tmp_entityNames = qaf.getEntityNames().split(";");
            String[] tmp_fieldClasses = qaf.getFieldClasses().split(";");
            if (tmp_entityNames.length > 1) {
                for (int i = 0; i < (tmp_entityNames.length - 1); i++) {
                    String tmp_entity_1 = tmp_entityNames[i];
                    String tmp_entity_2 = tmp_entityNames[i + 1];
                    if (!tables.contains(tmp_entity_1)) {
                        if (!tables.equals("")) {
                            tables = tables + ",";
                        }
                        tables = tables + tmp_entity_1;
                    }
                    if (!tables.contains(tmp_entity_2)) {
                        if (!tables.equals("")) {
                            tables = tables + ",";
                        }
                        tables = tables + tmp_entity_2;
                    }
                    //1：普通属性；2：实体属性；3：集合属性
                    if (tmp_fieldClasses[i].equals("1")) {
                    } else if (tmp_fieldClasses[i].equals("2")) {
                        String tmp_join = tmp_entity_1 + "." + EntityBuilder.getEntityField(tmp_entity_2) + "_key" + "="
                                + tmp_entity_2 + "." + EntityBuilder.getEntityField(tmp_entity_2) + "_key";
                        if (!joins.contains(tmp_join)) {
                            if (!joins.equals("")) {
                                joins = joins + " and ";
                            }
                            joins = joins + tmp_join;
                        }
                    } else if (tmp_fieldClasses[i].equals("3")) {
                        String tmp_join = tmp_entity_1 + "." + EntityBuilder.getEntityField(tmp_entity_1) + "_key" + "="
                                + tmp_entity_2 + "." + EntityBuilder.getEntityField(tmp_entity_1) + "_key";
                        if (!joins.contains(tmp_join)) {
                            if (!joins.equals("")) {
                                joins = joins + " and ";
                            }
                            joins = joins + tmp_join;
                        }
                    }
                }
            }
        }
        //移到下面，原因 table中可能会漏掉一个实体名称
//        String sql = "select " + fields + " from " + tables;

        String scheme_type = "查询统计(" + queryAnalysisScheme.getQueryAnalysisScheme_key() + ")";
        QueryScheme queryScheme = queryScheme2;
        if (queryScheme == null) {
            queryScheme = (QueryScheme) CommUtil.fetchEntityBy("from QueryScheme qs left join fetch qs.conditions where qs.scheme_type='" + scheme_type + "'");
        }

        if (queryScheme != null) {
            if (pnlLeft != null) {
                if (!QueryParamDialog.ShowQueryParamDialog(pnlLeft, queryScheme)) {
                    return null;
                }
            }
            if (!joins.equals("")) {
                joins = joins + " and ";
            }

            String entity_name = queryAnalysisScheme.getEntity_fullname().substring(queryAnalysisScheme.getEntity_fullname().lastIndexOf(".") + 1);
            joins = joins + entity_name + "." + EntityBuilder.getEntityField(entity_name) + "_key in (" + queryScheme.buildSql() + ")";


            if (!tables.contains(entity_name)) {
                if (!tables.equals("")) {
                    tables = tables + ",";
                }
                tables = tables + entity_name;
            }
        }

        String sql = "select " + fields + " from " + tables;

        if (!joins.equals("")) {
            sql = sql + " where " + joins;
        }

        if (!group_fields.equals("")) {
            sql = sql + " group by " + group_fields;
        }
        if (!order_fields.equals("")) {
            sql = sql + " order by " + order_fields;
        }

        return sql;
    }

    private void excute(QueryAnalysisScheme queryAnalysisScheme) {
        String sql = buildSQL(pnlLeft, queryAnalysisScheme, null);
        if (sql == null) {
            return;
        }
        List list = CommUtil.selectSQL(sql);

        for (String s_ind : ht_codes.keySet()) {
            String code_type_name = ht_codes.get(s_ind);
            int i_ind = Integer.valueOf(s_ind);
            for (Object obj : list) {
                Object[] record = (Object[]) obj;
                if (record[i_ind] == null) {
                    record[i_ind] = "";
                }
                String code_name = CodeManager.getCodeManager().getCodeNameBy(code_type_name, record[i_ind].toString());
                record[i_ind] = code_name;// + "?code_id" + record[i_ind];
            }
        }

        model.setNames(queryAnalysisScheme.getFieldCaptions());
        model.setObjects(list);
        model.fireTableStructureChanged();
        model.fireTableDataChanged();
    }

    private void initUI() {
        initToolBar();
        if (list_module != null) {
            moduleSchemeModel = new ModuleSchemeModel(list_module);
        } else {
            moduleSchemeModel = new ModuleSchemeModel();
        }
        schemeTree = new JTree(moduleSchemeModel);
        HRRendererView.getQuerySchemeMap(schemeTree).initTree(schemeTree);
        schemeTree.setRootVisible(false);
        schemeTree.setShowsRootHandles(true);
        schemeTree.expandRow(1);
        pnlLeft.add(new JScrollPane(schemeTree), BorderLayout.CENTER);
        table = new JTable(model);
        pnlMain.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void initToolBar() {
        toolbar.setFloatable(false);
        toolbar.add(btnExecute);
        toolbar.add(btnNew);
        toolbar.add(btnEdit);
        toolbar.add(btnSave);
        toolbar.add(btnDel);
        // toolbar.add(btnRight);
        toolbar.addSeparator();
        toolbar.add(btnShape);
//        toolbar.add(btnSaveShape);
//        toolbar.add(btnCopyShape);
//        toolbar.add(btnPrintShape);
    }
    //获取工具栏  添加或移除相应的控件

    public JToolBar getToolBar() {
        return toolbar;
    }

    public JCheckBox getShapeCheckBox() {
        return btnShape;
    }

    private void changeShowStyle(boolean selected) {
        pnlMain.removeAll();
        if (selected) {
            if (cur_QueryAnalysisScheme == null) {
                return;
            }
            pnlMain.setLayout(new GridLayout(2, 1));
            pnlMain.add(new JScrollPane(table), BorderLayout.CENTER);
            pnlMain.add(new ShapePanel(cur_QueryAnalysisScheme, model), BorderLayout.CENTER);
        } else {
            pnlMain.setLayout(new BorderLayout());
            pnlMain.add(new JScrollPane(table), BorderLayout.CENTER);
        }

        pnlMain.updateUI();
    }

    private void setupEvents() {
        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (schemeTree.getSelectionPath() == null) {
                    return;
                }
                if (!(schemeTree.getSelectionPath().getLastPathComponent() instanceof DefaultMutableTreeNode)) {
                    return;
                }
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) schemeTree.getSelectionPath().getLastPathComponent();
                Object obj = node.getUserObject();
                if (!(obj instanceof QueryAnalysisScheme)) {
                    return;
                }
                QueryAnalysisScheme queryAnalysisScheme = (QueryAnalysisScheme) obj;
                String schemeName = JOptionPane.showInputDialog("请输入方案名:");
                if (schemeName == null || schemeName.equals("")) {
                    return;
                }
                queryAnalysisScheme.setQueryAnalysisScheme_name(schemeName);
                CommUtil.updateEntity(queryAnalysisScheme);
                node.setUserObject(queryAnalysisScheme);
                schemeTree.updateUI();
            }
        });
        btnShape.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changeShowStyle(btnShape.isSelected());
            }
        });
        btnDel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (schemeTree.getSelectionPath() == null) {
                    return;
                }

                if (!(schemeTree.getSelectionPath().getLastPathComponent() instanceof DefaultMutableTreeNode)) {
                    return;
                }

                DefaultMutableTreeNode node = (DefaultMutableTreeNode) schemeTree.getSelectionPath().getLastPathComponent();

                if (!(node.getUserObject() instanceof QueryAnalysisScheme)) {
                    return;
                }

                if (JOptionPane.showConfirmDialog(JOptionPane.getFrameForComponent(btnDel),
                        "确定要删除[" + node.getUserObject() + "]吗?", "询问", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION) {
                    return;
                }

                CommUtil.deleteEntity(node.getUserObject());
                node.removeFromParent();
                schemeTree.updateUI();
            }
        });
        schemeTree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if (schemeTree.getSelectionPath() == null) {
                    return;
                }
                if (!(schemeTree.getSelectionPath().getLastPathComponent() instanceof DefaultMutableTreeNode)) {
                    return;
                }
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) schemeTree.getSelectionPath().getLastPathComponent();
                Object obj = node.getUserObject();
                if (obj instanceof QueryAnalysisScheme) {
                    cur_QueryAnalysisScheme = (QueryAnalysisScheme) obj;
                    module_info = cur_QueryAnalysisScheme.getModuleInfo();
                } else if (obj instanceof ModuleInfo) {
                    module_info = (ModuleInfo) obj;
                    cur_QueryAnalysisScheme = null;
                } else {
                    module_info = (ModuleInfo) ((DefaultMutableTreeNode) node.getParent()).getUserObject();
                    cur_QueryAnalysisScheme = null;
                }

            }
        });
        schemeTree.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() < 2) {
                    return;
                }
                if (schemeTree.getSelectionPath() == null) {
                    return;
                }
                if (!(schemeTree.getSelectionPath().getLastPathComponent() instanceof DefaultMutableTreeNode)) {
                    return;
                }
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) schemeTree.getSelectionPath().getLastPathComponent();
                Object obj = node.getUserObject();
                if (obj instanceof QueryAnalysisScheme) {
                    cur_QueryAnalysisScheme = (QueryAnalysisScheme) obj;
                    excute(cur_QueryAnalysisScheme);
                } else {
                    cur_QueryAnalysisScheme = null;
                }
            }
        });
        btnExecute.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cur_QueryAnalysisScheme == null) {
                    return;
                }
                excute(cur_QueryAnalysisScheme);
            }
        });
        btnNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (schemeTree.getSelectionPath() == null) {
                    return;
                }

                if (!(schemeTree.getSelectionPath().getLastPathComponent() instanceof DefaultMutableTreeNode)) {
                    return;
                }

                DefaultMutableTreeNode node = (DefaultMutableTreeNode) schemeTree.getSelectionPath().getLastPathComponent();
                if (node.getLevel() == 0) {
                    return;
                }
                if (module_info == null) {
                    return;
                }
                QueryAnalysisScheme queryAnalysisScheme = (QueryAnalysisScheme) UtilTool.createUIDEntity(QueryAnalysisScheme.class);
                queryAnalysisScheme.setModuleInfo(module_info);
                if (node.getUserObject() instanceof QueryAnalysisScheme) {
                    QueryAnalysisScheme tmp = (QueryAnalysisScheme) node.getUserObject();
                    queryAnalysisScheme.setQueryAnalysisScheme_type(tmp.getQueryAnalysisScheme_type());
                }
                if (WizardDialog.showWizard(new QuerySchemeWizardModel(queryAnalysisScheme))) {
                    if (list_module != null) {
                        moduleSchemeModel.buildTree(list_module);
                    } else {
                        moduleSchemeModel.buildTree();
                    }
                    schemeTree.updateUI();
                }
            }
        });
        btnEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (schemeTree.getSelectionPath() == null) {
                    return;
                }

                if (!(schemeTree.getSelectionPath().getLastPathComponent() instanceof DefaultMutableTreeNode)) {
                    return;
                }

                DefaultMutableTreeNode node = (DefaultMutableTreeNode) schemeTree.getSelectionPath().getLastPathComponent();
                Object obj = node.getUserObject();
                if (!(obj instanceof QueryAnalysisScheme)) {
                    return;
                }
                QueryAnalysisScheme queryAnalysisScheme = (QueryAnalysisScheme) obj;
                if (WizardDialog.showWizard(new QuerySchemeWizardModel(queryAnalysisScheme))) {
                    if (list_module != null) {
                        moduleSchemeModel.buildTree(list_module);
                    } else {
                        moduleSchemeModel.buildTree();
                    }
                    schemeTree.updateUI();
                }
            }
        });
        schemeTree.setSelectionRow(0);
    }

    @Override
    public void pickClose() {
    }

    @Override
    public void refresh() {
    }

    @Override
    public String getModuleCode() {
        return module_code;
    }
}
