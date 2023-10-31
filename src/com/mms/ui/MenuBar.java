package com.mms.ui;

import com.mms.ui.client.ClientListUI;
import com.mms.ui.client.CreditClient;
import com.mms.ui.client.NouveauClient;
import com.mms.ui.product.Catalogue;
import com.mms.ui.product.Inventaire;
import com.mms.ui.product.NouveauProduit;
import com.mms.ui.inventory.CreditFournisseur;
import com.mms.ui.inventory.ListeFournisseur;
import com.mms.ui.inventory.NouveauFournisseur;
import com.mms.ui.user.Activite;
import com.mms.ui.user.UpdateUserUI;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    private JMenu menuClient;
    private JMenu menuFournisseur;
    private JMenu menuUtilisateur;
    private JMenu menuAPropos;
    private JMenu menuProduits;
    private JMenuItem itemNouveauClient;
    private JMenuItem itemListeClient;
    private JMenuItem itemCreditClient;
    private JMenuItem itemNouveauFournisseur;
    private JMenuItem itemListeFournisseur;
    private JMenuItem itemCreditFournisseur;
    private JMenuItem itemChangerIdentifiant;
    private JMenuItem itemActiviteUtilisateur;
    private JMenuItem itemLogiciel;
    private JMenuItem itemDeveloppeurs;
    private JMenuItem itemNouveauProduit;
    private JMenuItem itemCatalogue;
    private JMenuItem itemInventaire;

    public MenuBar() {
        init();
        registerEvents();
    }

    public void init() {
        menuClient = new JMenu("Clients");
        menuFournisseur = new JMenu("Fournisseurs");
        menuUtilisateur = new JMenu("Utilisateurs");
        menuAPropos = new JMenu("A propos");
        menuProduits = new JMenu("Produits");
        add(menuProduits);
        add(menuClient);
        add(menuFournisseur);
        add(menuUtilisateur);
        add(menuAPropos);
        itemNouveauClient = new JMenuItem("Nouveau client");
        itemListeClient = new JMenuItem("Liste des clients");
        itemCreditClient = new JMenuItem("Credits client");
        itemNouveauFournisseur = new JMenuItem("Nouveau fournisseur");
        itemListeFournisseur = new JMenuItem("Liste fournisseur");
        itemCreditFournisseur = new JMenuItem("Credits fournisseur");
        itemChangerIdentifiant = new JMenuItem("Modifier les identifiants");
        itemActiviteUtilisateur = new JMenuItem("Voir les activités d'un utilisateur");
        itemLogiciel = new JMenuItem("A propos du logiciels");
        itemDeveloppeurs = new JMenuItem("Développeurs");
        itemNouveauProduit = new JMenuItem("Nouveau produit");
        itemCatalogue = new JMenuItem("Catalogue des produits");
        itemInventaire = new JMenuItem("Inventaire des produits");
        menuClient.add(itemNouveauClient);
        menuClient.add(itemListeClient);
        menuClient.add(itemCreditClient);
        menuFournisseur.add(itemNouveauFournisseur);
        menuFournisseur.add(itemListeFournisseur);
        menuFournisseur.add(itemCreditFournisseur);
        menuUtilisateur.add(itemActiviteUtilisateur);
        menuUtilisateur.add(itemChangerIdentifiant);
        menuAPropos.add(itemLogiciel);
        menuAPropos.add(itemDeveloppeurs);
        menuProduits.add(itemNouveauProduit);
        menuProduits.add(itemCatalogue);
        menuProduits.add(itemInventaire);
    }

    private void registerEvents() {
        itemInventaire.addActionListener(e -> new Inventaire());
        itemCatalogue.addActionListener(e -> new Catalogue().setVisible(true));
        itemNouveauProduit.addActionListener(e -> new NouveauProduit().setVisible(true));
        itemDeveloppeurs.addActionListener(e -> System.out.println("Showing Devs"));
        itemLogiciel.addActionListener(e -> System.out.println("About the App"));
        itemNouveauClient.addActionListener(e -> new NouveauClient().setVisible(true));
        itemListeClient.addActionListener(e -> new ClientListUI().setVisible(true));
        itemCreditClient.addActionListener(e -> new CreditClient());
        itemNouveauFournisseur.addActionListener(e -> new NouveauFournisseur().setVisible(true));
        itemListeFournisseur.addActionListener(e -> new ListeFournisseur().setVisible(true));
        itemCreditFournisseur.addActionListener(e -> new CreditFournisseur());
        itemActiviteUtilisateur.addActionListener(e -> new Activite());
        itemChangerIdentifiant.addActionListener(e -> new UpdateUserUI().setVisible(true));
    }
}
