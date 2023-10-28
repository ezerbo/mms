package com.mms.interfaces.tab;

import com.mms.interfaces.sale.SalesUI;
import com.mms.interfaces.util.UIUtil;
import net.infonode.tabbedpanel.titledtab.TitledTab;

import javax.swing.*;

public class SalesTab extends TitledTab {

    public SalesTab() {
        super("Enregistrement des ventes" + "  "
                , new ImageIcon("ressources/images/enregistrement.png")
                , new SalesUI()
                , null);
        getProperties().addSuperObject(UIUtil.getTabProperties());
    }
}
