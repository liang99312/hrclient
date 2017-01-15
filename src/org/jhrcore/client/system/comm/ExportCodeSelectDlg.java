/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExportCodeSelectDlg.java
 *
 * Created on 2009-12-12, 11:55:14
 */
package org.jhrcore.client.system.comm;

import com.foundercy.pf.control.table.FTable;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import org.jhrcore.client.CommUtil;
import org.jhrcore.entity.Code;
import org.jhrcore.entity.base.TempFieldInfo;
import org.jhrcore.rebuild.EntityBuilder;
import org.jhrcore.ui.ContextManager;
import org.jhrcore.ui.ExportDialog;
import org.jhrcore.ui.JCheckBoxList;
import org.jhrcore.ui.action.CloseAction;

/**
 *
 * @author Administrator
 */
public class ExportCodeSelectDlg extends javax.swing.JDialog {

    private JCheckBoxList cbl_codes;
    private List code_list = new ArrayList();
    private String search_text = "";
    private Set<Object> selected_nodes = new HashSet<Object>();
    private FTable ftable;
    private List<String> fields = new ArrayList<String>();

    public ExportCodeSelectDlg() {
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
        jPanel2 = new javax.swing.JPanel();
        txfSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        selectAll = new javax.swing.JCheckBox();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        jLabel1.setText("查找：");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(96, 96, 96)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(101, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(13, Short.MAX_VALUE)))
        );

        pnlMain.setLayout(new java.awt.BorderLayout());

        selectAll.setText("全选");

        btnOk.setText("确定");

        btnCancel.setText("取消");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addComponent(btnOk)
                .addGap(50, 50, 50)
                .addComponent(btnCancel)
                .addGap(47, 47, 47))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectAll)
                    .addComponent(btnCancel)
                    .addComponent(btnOk))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JCheckBox selectAll;
    private javax.swing.JTextField txfSearch;
    // End of variables declaration//GEN-END:variables

    private void initOthers() {
        code_list = CommUtil.fetchEntities("from Code c where c.parent_id='ROOT'");
        cbl_codes = new JCheckBoxList(code_list);
        pnlMain.add(new JScrollPane(cbl_codes), BorderLayout.CENTER);
        ftable = new FTable(Code.class, false, false, false, "Code");

        List<TempFieldInfo> field_infos = EntityBuilder.getCommFieldInfoListOf(Code.class, EntityBuilder.COMM_FIELD_VISIBLE);
        for (TempFieldInfo tfi : field_infos) {
            fields.add(tfi.getField_name());
        }
        ftable.setFields(fields);
    }

    private void setupEvents() {
        cbl_codes.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (cbl_codes.getCheckedCount() == code_list.size()) {
                    selectAll.setSelected(true);
                } else {
                    selectAll.setSelected(false);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        txfSearch.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    searchNode();

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        selectAll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectAll.isSelected()) {
                    cbl_codes.SelectAll();
                } else {
                    cbl_codes.ClearSelectAll();
                }
                cbl_codes.updateUI();
            }
        });
        CloseAction.doCloseAction(btnCancel);
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int list_size = code_list.size();
                int check_size = cbl_codes.getCheckedCount();
                if (list_size == 0) {
                    return;
                }
                if (check_size == 0) {
                    JOptionPane.showMessageDialog(ContextManager.getMainFrame(),
                            "请选择编码",
                            "提示", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String hql = "from Code ";
                StringBuffer str_where = new StringBuffer();
                if (check_size == list_size) {
                } else {
                    if (check_size < list_size / 2) {
                        for (Object obj : cbl_codes.getCheckedObjects()) {
                            Code tmp_code = (Code) obj;
                            str_where.append("code_tag like '" + tmp_code.getCode_tag() + "%' or ");
                        }
                        str_where.append("code_key = '-1'");
                        hql += "where " + str_where;
                    } else {
                        for (Object obj : code_list) {
                            if (!cbl_codes.getCheckedObjects().contains(obj)) {
                                Code tmp_code = (Code) obj;
                                str_where.append("code_tag not like '" + tmp_code.getCode_tag() + "%' and ");
                            }
                        }
                        str_where.append("code_key != '-1'");
                        hql += "where " + str_where;
                    }
                }
                System.out.println("hql:::" + hql);
                hql += " order by code_tag";
                ftable.setObjects(CommUtil.fetchEntities(hql));
                ExportDialog eDlg = new ExportDialog(Code.class, ftable, "Code", ftable.getAll_fields());
                ContextManager.locateOnMainScreenCenter(eDlg);
                eDlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                eDlg.setVisible(true);

            }
        });

    }

    public void searchNode() {
        String val = txfSearch.getText();
        if (val.equals("")) {
            return;
        }
        val = val.toUpperCase();
        if (!search_text.equals(val)) {
            selected_nodes.clear();
        }
        search_text = val;
        Object node = locateEmp(search_text);
        if (node == null) {
            selected_nodes.clear();
            node = locateEmp(val);
        }
        if (node == null) {
            return;
        }
        //   cbl_codes.setSelectedIndex(index);
        cbl_codes.setSelectedValue(node, true);
    }

    public Object locateEmp(String val) {
        Pattern p = Pattern.compile(val);
        Object resultObj = null;
        try {
            for (int i = 0; i <= code_list.size(); i++) {
                Object obj = code_list.get(i);
                if (obj instanceof Code) {
                    Code code1 = (Code) obj;
                    Object field_val = null;
                    Matcher m;
                    field_val = code1.getCode_name();
                    if (field_val != null) {
                        m = p.matcher(field_val.toString());
                        if (m.find()) {
                            if (selected_nodes.contains(obj)) {
                                continue;
                            } else {
                                resultObj = obj;
                                selected_nodes.add(obj);
                                break;
                            }
                        }
                    }
                    field_val = code1.getPym();
                    if (field_val != null) {
                        p = Pattern.compile(val);
                        m = p.matcher(field_val.toString());
                        if (m.find()) {
                            if (selected_nodes.contains(obj)) {
                                continue;
                            } else {
                                resultObj = obj;
                                selected_nodes.add(obj);
                                break;
                            }
                        }
                    }
                }

            }

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return resultObj;
    }
}
