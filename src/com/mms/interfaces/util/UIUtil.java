package com.mms.interfaces.util;

import net.infonode.tabbedpanel.theme.ShapedGradientTheme;
import net.infonode.tabbedpanel.titledtab.TitledTabProperties;

public class UIUtil {

    public static String htmlWrap(String text) {
        return String.format("<html><a href='' color = black> %s </a></html>", text);
    }

    public static TitledTabProperties getTabProperties() {
        TitledTabProperties titledTabProperties = new TitledTabProperties();
        titledTabProperties.addSuperObject(new ShapedGradientTheme()
                .getTitledTabProperties());
        return titledTabProperties;
    }
}
