package com.mms.ui.tab;

import com.mms.ui.inventory.InventoryUI;
import com.mms.ui.util.UIUtil;
import net.infonode.tabbedpanel.titledtab.TitledTab;

import javax.swing.*;

public class InventoryTab extends TitledTab {

    public InventoryTab() {
        super("Enregistrement des livraisons"
                , new ImageIcon("ressources/images/enregistrement.png")
                , new InventoryUI()
                , null);
        getProperties().addSuperObject(UIUtil.getTabProperties());
    }
}
