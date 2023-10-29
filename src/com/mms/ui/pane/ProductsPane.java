package com.mms.ui.pane;

import com.l2fprod.common.swing.JTaskPaneGroup;
import com.mms.ui.product.Catalogue;
import com.mms.ui.product.Inventaire;
import com.mms.ui.product.NouveauProduit;
import com.mms.util.Listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductsPane extends JTaskPaneGroup {

    private JLabel l_inventaire;
    private JLabel l_nouveauProduit;
    private JLabel l_catalogueProduit;

    public ProductsPane() {
        init();
    }

    private void init() {
        setTitle("Gestion des produits            ");
        l_inventaire = new JLabel("Inventaire");
        l_nouveauProduit = new JLabel("Nouveau produit");
        l_catalogueProduit = new JLabel("Catalogue de vente");
        l_nouveauProduit.setIcon(new ImageIcon("ressources/images/nouveauProduit.png"));
        l_inventaire.setIcon(new ImageIcon("ressources/images/inventaire.png"));
        l_catalogueProduit.setIcon(new ImageIcon("ressources/images/catalogue.png"));
        add(l_nouveauProduit);
        add(l_catalogueProduit);
        add(l_inventaire);

        l_inventaire.addMouseListener(new Listener(l_inventaire,
                "<html><a href='' color = black>" +
                        l_inventaire.getText() + "</a></html>", "Inventaire"));
        l_inventaire.setToolTipText("Inventaire des produits");
        l_inventaire.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new Inventaire().setVisible(true);
                System.gc();
            }
        });
        l_nouveauProduit.addMouseListener(new Listener(l_nouveauProduit,
                "<html><a href='' color = black>" +
                        l_nouveauProduit.getText() +
                        "</a></html>", "Nouveau produit"));
        l_nouveauProduit.setToolTipText("Enregistrer un nouveau produit");
        l_nouveauProduit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new NouveauProduit().setVisible(true);
            }
        });
        l_catalogueProduit
                .addMouseListener(new Listener(l_catalogueProduit,
                        "<html><a href='' color = black>" +
                                l_catalogueProduit.getText() +
                                "</a></html>",
                        "Catalogue de vente"));
        l_catalogueProduit.setToolTipText("catalogue des produits");
        l_catalogueProduit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new Catalogue().setVisible(true);
            }
        });
    }
}
