package com.mms.interfaces.tab;

import com.mms.interfaces.supplier.DeliveriesUI;
import com.mms.interfaces.util.UIUtil;
import net.infonode.tabbedpanel.titledtab.TitledTab;

import javax.swing.*;

public class DeliveriesTab extends TitledTab {

    public DeliveriesTab() {
        super("Enregistrement des livraisons",
                new ImageIcon("ressources/images/enregistrement.png"),
                new DeliveriesUI(), null);
        getProperties().addSuperObject(UIUtil.getTabProperties());
    }
}
