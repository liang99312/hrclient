package org.jhrcore.client.system;

import org.jhrcore.ui.task.CommTask;

public class SysCodePlugin extends CommTask {

    private static final long serialVersionUID = 1L;

    @Override
    public Class getModuleClass() {
        return SysCodePanel.class;
    }

    @Override
    public String getGroupCode() {
        return "ϵͳά��";
    }

    @Override
    public String getClassName() {
        return "ģ��ά��";
    }
}