/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RoleEditDlg.java
 *
 * Created on 2010-3-29, 17:21:02
 */
package org.jhrcore.client.system.right;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.jhrcore.client.CommUtil;
import org.jhrcore.util.SysUtil;
import org.jhrcore.client.UserContext;
import org.jhrcore.util.UtilTool;
import org.jhrcore.entity.right.Role;
import org.jhrcore.entity.salary.ValidateSQLResult;
import org.jhrcore.ui.BeanPanel;
import org.jhrcore.ui.action.CloseAction;
import org.jhrcore.util.MsgUtil;

/**
 *
 * @author mxliteboss
 */
public class RoleEditDlg extends javax.swing.JDialog {

    private BeanPanel beanPanel = new BeanPanel();
    private Object parent_obj;
    private Role cur_role;
    private Role parent_role;
    private boolean click_ok = false;
    private boolean isNew = false;

    public boolean isClick_ok() {
        return click_ok;
    }

    public Role getCur_role() {
        return cur_role;
    }

    /** Creates new form RoleEditDlg */
    public RoleEditDlg(java.awt.Frame parent, Object role, boolean isNew) {
        super(parent);
        this.isNew = isNew;
        if (isNew) {
            parent_obj = role;
            this.setTitle("增加用户（角色）");
        } else {
            cur_role = (Role) role;
            this.setTitle("编辑用户（角色）");
        }
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

        pnlMain = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        pnlMain.setLayout(new java.awt.BorderLayout());

        btnOk.setText("确定");

        btnCancel.setText("取消");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(202, Short.MAX_VALUE)
                .addComponent(btnOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOk))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables

    private void initOthers() {
        if (isNew) {
            cur_role = (Role) UtilTool.createUIDEntity(Role.class);
            String tmpCode = "";
            if (parent_obj instanceof Role) {
                parent_role = (Role) parent_obj;
                cur_role.setParent_code(parent_role.getRole_code());
                tmpCode = SysUtil.getNewRoleCode(parent_role.getRole_code());
                cur_role.setRole_code(tmpCode);
            } else {
                tmpCode = SysUtil.getNewRoleCode("");
                cur_role.setParent_code("ROOT");
                cur_role.setRole_code(tmpCode);
            }
        }
        List<String> fields = new ArrayList<String>();
        fields.add("role_name");
        fields.add("role_code");
        fields.add("parent_code");
        beanPanel.setBean(cur_role);
        beanPanel.setColumns(1);
        beanPanel.setEditable(true);
        beanPanel.setFields(fields);
        beanPanel.bind();
        pnlMain.add(beanPanel, BorderLayout.CENTER);
    }

    private void setupEvents() {
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveObjects();
            }
        });
        CloseAction.doCloseAction(btnCancel);
    }

    private void saveObjects() {
        if (cur_role.getRole_name() == null) {
            JOptionPane.showMessageDialog(this,
                    "角色名称不可为空!", // message
                    "错误", // title
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        ValidateSQLResult result;
        if (isNew) {
            result = CommUtil.saveEntity(cur_role);
        } else {
            result = CommUtil.updateEntity(cur_role);
        }
        if (result.getResult() == 0) {
            click_ok = true;
            MsgUtil.showHRSaveSuccessMsg(btnOk);
            dispose();
        } else {
            MsgUtil.showHRSaveErrorMsg(result);
        }
    }
}
