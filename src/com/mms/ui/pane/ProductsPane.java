package com.mms.ui.pane;

import com.l2fprod.common.swing.JTaskPaneGroup;
import com.mms.ui.product.Catalogue;
import com.mms.ui.product.Inventaire;
import com.mms.ui.product.NouveauProduit;
import com.mms.ui.util.ImagePaths;
import com.mms.ui.util.UIUtil;
import com.mms.util.Listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductsPane extends JTaskPaneGroup {

    private final JLabel inventoryLabel = new JLabel("Inventaire");;
    private final JLabel newProductLabel = new JLabel("Nouveau produit");;
    private final JLabel productCatalogueLabel = new JLabel("Catalogue de vente");;

    public ProductsPane() {
        init();
        registerEvents();
    }

    private void init() {
        setTitle("Gestion des produits");
        newProductLabel.setIcon(new ImageIcon(ImagePaths.NEW_PRODUCT_IMG));
        inventoryLabel.setIcon(new ImageIcon(ImagePaths.INVENTORY_IMG));
        productCatalogueLabel.setIcon(new ImageIcon(ImagePaths.PRODUCT_CATALOGUE_IMG));
        inventoryLabel.setToolTipText("Inventaire des produits");
        newProductLabel.setToolTipText("Enregistrer un nouveau produit");
        productCatalogueLabel.setToolTipText("catalogue des produits");
        add(newProductLabel);
        add(productCatalogueLabel);
        add(inventoryLabel);
    }

    private void registerEvents() {
        inventoryLabel.addMouseListener(new Listener(inventoryLabel
                , UIUtil.htmlWrap(inventoryLabel.getText())
                , "Inventaire"));
        inventoryLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new Inventaire().setVisible(true);
            }
        });

        newProductLabel.addMouseListener(new Listener(newProductLabel
                , UIUtil.htmlWrap(newProductLabel.getText())
                , "Nouveau produit"));
        newProductLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new NouveauProduit().setVisible(true);
            }
        });

        productCatalogueLabel.addMouseListener(new Listener(productCatalogueLabel
                        , UIUtil.htmlWrap(productCatalogueLabel.getText())
                        , "Catalogue de vente"));
        productCatalogueLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new Catalogue().setVisible(true);
            }
        });
    }
}
