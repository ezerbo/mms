package com.mms.interfaces.tab;

import net.infonode.tabbedpanel.TabbedPanel;
import net.infonode.tabbedpanel.theme.ShapedGradientTheme;

public class TabsPanel extends TabbedPanel {

    public TabsPanel() {
        getProperties().addSuperObject(new ShapedGradientTheme().getTabbedPanelProperties());
        addTab(new SalesTab());
        addTab(new DeliveriesTab());
    }
}
