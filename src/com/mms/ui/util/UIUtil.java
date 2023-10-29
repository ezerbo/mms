package com.mms.ui.util;

import net.infonode.tabbedpanel.theme.ShapedGradientTheme;
import net.infonode.tabbedpanel.titledtab.TitledTabProperties;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class UIUtil {

    private final static String PHONE_NUMBER_MASK = "(226) - ## - ## - ## - ##";

    public static String htmlWrap(String text) {
        return String.format("<html><a href='' color = black> %s </a></html>", text);
    }

    public static TitledTabProperties getTabProperties() {
        TitledTabProperties titledTabProperties = new TitledTabProperties();
        titledTabProperties.addSuperObject(new ShapedGradientTheme()
                .getTitledTabProperties());
        return titledTabProperties;
    }

    public static MaskFormatter getPhoneNumberFormatter() {
        try {
            return new MaskFormatter(PHONE_NUMBER_MASK);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
