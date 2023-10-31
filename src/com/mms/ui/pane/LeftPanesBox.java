package com.mms.ui.pane;

import javax.swing.*;

public class LeftPanesBox {
    
    public static Box create() {
        Box leftPanesBox = Box.createVerticalBox();
        leftPanesBox.add(new JLabel("    "));
        leftPanesBox.add(new SalesPane());
        leftPanesBox.add(new JLabel("    "));
        leftPanesBox.add(new InventoriesPane());
        leftPanesBox.add(new JLabel("    "));
        leftPanesBox.add(new ProductsPane());
        leftPanesBox.add(new JLabel("    "));
        leftPanesBox.add(new SettingsPane());
        return leftPanesBox;
    }
}
