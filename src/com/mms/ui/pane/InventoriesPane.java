package com.mms.ui.pane;

import com.l2fprod.common.swing.JTaskPaneGroup;
import com.mms.ui.inventory.CreditFournisseur;
import com.mms.ui.inventory.HistoriqueLivraison;
import com.mms.ui.inventory.ListeFournisseur;
import com.mms.ui.util.ImagePaths;
import com.mms.ui.util.UIUtil;
import com.mms.util.Listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InventoriesPane extends JTaskPaneGroup {

    private final JLabel savedInventoriesLabel = new JLabel("Livraisons enregistrées");
    private final JLabel suppliersCreditLabel = new JLabel("Credits fournisseurs");
    private final JLabel suppliersListLabel = new JLabel("Liste des fournisseurs");

    public InventoriesPane() {
        init();
        registerEvents();
    }

    private void init() {
        setTitle("Gestion des Livraisons");

        savedInventoriesLabel.setIcon(new ImageIcon(ImagePaths.HISTORY_IMG));
        suppliersListLabel.setIcon(new ImageIcon(ImagePaths.LIST_IMG));
        suppliersCreditLabel.setIcon(new ImageIcon(ImagePaths.CREDIT_MG));

        add(savedInventoriesLabel);
        add(suppliersCreditLabel);
        add(suppliersListLabel);

        savedInventoriesLabel.setToolTipText("Liste des achats réalisés");
        suppliersCreditLabel.setToolTipText("Liste des credits fournisseur");
        suppliersListLabel.setToolTipText("Liste des fournisseurs enregistrés");
    }

    private void registerEvents() {
        savedInventoriesLabel.addMouseListener(new Listener(savedInventoriesLabel
                , UIUtil.htmlWrap(savedInventoriesLabel.getText())
                , "Livraisons enregistrées"));
        savedInventoriesLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new HistoriqueLivraison();
            }
        });

        suppliersCreditLabel.addMouseListener(new Listener(suppliersCreditLabel
                , UIUtil.htmlWrap(suppliersCreditLabel.getText())
                , "Credits fournisseurs"));

        suppliersCreditLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new CreditFournisseur();
            }
        });

        suppliersListLabel.addMouseListener(new Listener(suppliersListLabel
                , UIUtil.htmlWrap(suppliersListLabel.getText())
                , "Liste des fournisseurs"));
        suppliersListLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new ListeFournisseur().setVisible(true);
            }
        });
    }
}
