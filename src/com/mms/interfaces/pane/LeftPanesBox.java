package com.mms.interfaces.pane;

import javax.swing.*;

public class LeftPanesBox {
    
    public static Box create() {
        Box leftPanesBox = Box.createVerticalBox();
        leftPanesBox.add(new JLabel("    "));
        leftPanesBox.add(new SalesPane());
        leftPanesBox.add(new JLabel("    "));
        leftPanesBox.add(new DeliveriesPane());
        leftPanesBox.add(new JLabel("    "));
        leftPanesBox.add(new ProductsPane());
        leftPanesBox.add(new JLabel("    "));
        leftPanesBox.add(new SettingsPane());
        return leftPanesBox;
    }
}
