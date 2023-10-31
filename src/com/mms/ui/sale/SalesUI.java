package com.mms.ui.sale;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import com.l2fprod.common.swing.JTaskPaneGroup;
import com.mms.event.ClientEvent;
import com.mms.event.ProduitEvent;
import com.mms.event.VenteEvent;
import com.mms.ui.client.NouveauClient;
import com.mms.ui.common.ComboBoxEditable;
import com.mms.ui.common.TextFieldTable;
import com.mms.listener.ClientListener;
import com.mms.listener.ProduitListener;
import com.mms.listener.VenteListener;
import com.mms.domain.Client;
import com.mms.domain.Session;
import com.mms.service.CategorieService;
import com.mms.service.ClientService;
import com.mms.service.UserService;
import com.mms.service.SalesService;
import com.mms.ui.tablemodels.LigneDeVente;
import com.mms.ui.tablemodels.TableModelVente;
import com.mms.util.GenereDate;
import org.apache.commons.lang3.StringUtils;

public class SalesUI extends JPanel implements VenteListener, ClientListener, ProduitListener {

    private JPanel panelSud;
    private JPanel panelNord;
    private JPanel panelChoixClient;
    private JPanel panelSudChoixClient;
    private JPanel panelCompte;
    private JPanel panelMontantTotalTTC;
    private JPanel panelMontantTotalHT;
    private JPanel panelMontantTotalTVA;
    private JPanel panelLibelleMontantTotalTTC;
    private JPanel panelLibelleMontantTotalHT;
    private JPanel panelLibelleMontantTotalTVA;
    private JPanel panelFacturation;
    private JPanel panelTypeVente;
    /*****/
    private JComboBox<String> comboListeClient;
    private ComboBoxEditable comboboxDesignation;
    private JScrollPane scrollPane;
    private JButton b_nouveauClient;
    private JButton b_apercuFacture;
    private JButton b_enregistrer;
    private JTable table;
    private TableModelVente tableModel;
    private JTaskPaneGroup taskPaneChoixClient;
    private JTaskPaneGroup taskPaneCompte;
    private JTaskPaneGroup taskPaneFacturation;
    private JPopupMenu popupMenu;
    private JMenuItem itemAjouterLigne;
    private JMenuItem itemSupprimerToutesLesLignes;
    private JMenuItem itemSupprimerLigneSelectionnee;
    private JLabel labelClient;
    private JLabel labelLibelleMontantTotalTTC;
    private JLabel labelLibelleMontantTotalTVA;
    private JLabel labelLibelleMontantTotalHT;
    private JLabel labelMontantTotalTTC;
    private JLabel labelMontantTotalTVA;
    private JLabel labelMontantTotalHT;
    private CategorieService categorieService;
    private ClientService clientService;
    private SalesService salesService;
    private UserService userService;
    private static String VENTECOMPTANT = "COMPTANT";
    private static String VENTECREDIT = "CREDIT";
    private NouveauClient nouveauClient;
    private JCheckBox venteComptant;
    private JCheckBox venteCredit;

    public SalesUI() {
        setLayout(new BorderLayout());
        panelSud = new JPanel();
        panelNord = new JPanel();
        panelCompte = new JPanel();
        panelMontantTotalHT = new JPanel();
        panelMontantTotalTTC = new JPanel();
        panelMontantTotalTVA = new JPanel();
        panelLibelleMontantTotalHT = new JPanel();
        panelLibelleMontantTotalTTC = new JPanel();
        panelLibelleMontantTotalTVA = new JPanel();
        panelTypeVente = new JPanel();
        taskPaneChoixClient = new JTaskPaneGroup();
        taskPaneCompte = new JTaskPaneGroup();
        taskPaneFacturation = new JTaskPaneGroup();
        panelChoixClient = new JPanel();
        panelSudChoixClient = new JPanel();
        panelFacturation = new JPanel();
        labelClient = new JLabel("Liste des clients");
        labelLibelleMontantTotalTTC = new JLabel("Montant total TTC :");
        labelLibelleMontantTotalHT = new JLabel("Montant total HT : ");
        labelLibelleMontantTotalTVA = new JLabel("Montant total TVA : ");

        labelMontantTotalHT = new JLabel("0.0");
        labelMontantTotalTTC = new JLabel("0.0");
        labelMontantTotalTVA = new JLabel("0.0");
        comboListeClient = new JComboBox<>();
        comboboxDesignation = new ComboBoxEditable();
        b_nouveauClient = new JButton("Nouveau client");
        b_apercuFacture = new JButton("Aperçu de la facture");
        b_enregistrer = new JButton("Enregistrer");
        itemSupprimerToutesLesLignes = new JMenuItem("Supprimer toutes les lignes");
        itemSupprimerToutesLesLignes.setEnabled(false);
        itemSupprimerLigneSelectionnee = new JMenuItem("Supprimer la ligne sélectionnée");
        itemSupprimerLigneSelectionnee.setEnabled(false);
        itemAjouterLigne = new JMenuItem("Ajouter une nouvelle ligne");
        categorieService = new CategorieService();
        clientService = new ClientService();
        salesService = new SalesService();
        userService = new UserService();
        nouveauClient = new NouveauClient();
        venteComptant = new JCheckBox("Vente au comptant");
        venteCredit = new JCheckBox("Vente à crédit");

        nouveauClient.addClientListener(this);
        tableModel = new TableModelVente();
        tableModel.addVenteListener(this);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        panelSud.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelNord.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelNord.add(new JLabel("Date :  " + (new GenereDate()).getDateCourante()));
        panelCompte.setLayout(new GridLayout(3, 2));

        panelFacturation.setLayout(new FlowLayout(FlowLayout.LEFT));
        b_enregistrer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b_apercuFacture.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelFacturation.add(b_apercuFacture);
        panelFacturation.add(b_enregistrer);
        taskPaneFacturation.setTitle("Facturation");
        taskPaneFacturation.setLayout(new BorderLayout());
        taskPaneFacturation.add(panelFacturation, BorderLayout.SOUTH);
        panelTypeVente.setLayout(new GridLayout(2, 1));
        panelTypeVente.add(venteComptant);
        panelTypeVente.add(venteCredit);
        venteComptant.setSelected(true);
        taskPaneFacturation.add(panelTypeVente, BorderLayout.CENTER);
        panelSud.add(taskPaneFacturation);

        taskPaneChoixClient.setTitle("Choix du client");
        taskPaneChoixClient.setLayout(new BorderLayout());
        panelChoixClient.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelChoixClient.add(labelClient);
        panelChoixClient.add(comboListeClient);
        taskPaneChoixClient.add(panelChoixClient, BorderLayout.CENTER);
        panelSudChoixClient.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelSudChoixClient.add(b_nouveauClient);
        taskPaneChoixClient.add(panelSudChoixClient, BorderLayout.SOUTH);
        panelSud.add(taskPaneChoixClient);

        taskPaneCompte.setTitle("Le compte");
        taskPaneCompte.setLayout(new BorderLayout(0, 0));
        taskPaneCompte.add(panelCompte, BorderLayout.CENTER);
        panelSud.add(taskPaneCompte);

        panelLibelleMontantTotalHT.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelLibelleMontantTotalHT.add(labelLibelleMontantTotalHT);
        panelCompte.add(panelLibelleMontantTotalHT);
        panelMontantTotalHT.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelMontantTotalHT.add(labelMontantTotalHT);
        panelCompte.add(panelMontantTotalHT);

        panelLibelleMontantTotalTVA.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelLibelleMontantTotalTVA.add(labelLibelleMontantTotalTVA);
        panelCompte.add(panelLibelleMontantTotalTVA);
        panelMontantTotalTVA.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelMontantTotalTVA.add(labelMontantTotalTVA);
        panelCompte.add(panelMontantTotalTVA);

        panelLibelleMontantTotalTTC.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelLibelleMontantTotalTTC.add(labelLibelleMontantTotalTTC);
        panelCompte.add(panelLibelleMontantTotalTTC);
        panelMontantTotalTTC.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelMontantTotalTTC.add(labelMontantTotalTTC);
        panelCompte.add(panelMontantTotalTTC);

        popupMenu = new JPopupMenu();
        popupMenu.add(itemAjouterLigne);
        popupMenu.add(itemSupprimerLigneSelectionnee);
        popupMenu.add(itemSupprimerToutesLesLignes);
        add(panelNord, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelSud, BorderLayout.NORTH);
        abonnement();
        afficheListeClient();
        afficheLigne();
        afficheComposantTable();
        afficheListeProduit();

    }

    private void abonnement() {
        registerKeyboardAction(new AbstractAction() {
                                   @Override
                                   public void actionPerformed(ActionEvent arg0) {
                                       tableModel.ajouteLigne();
                                   }
                               }, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        registerKeyboardAction(
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        nouveauClient.setVisible(true);
                    }
                },
                KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK
                        | InputEvent.ALT_MASK),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        registerKeyboardAction(
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        effectuerVente();
                    }
                },
                KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK | InputEvent.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        comboListeClient.registerKeyboardAction(new AbstractAction() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent arg0) {
                                                        comboListeClient.setPopupVisible(true);
                                                    }
                                                }, KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        scrollPane.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    tableModel.ajouteLigne();
                    if (tableModel.getRowCount() >= 2) {
                        itemSupprimerLigneSelectionnee.setEnabled(true);
                        itemSupprimerToutesLesLignes.setEnabled(true);
                    }
                }
            }
        });
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                showIfPopupTrigger(event);
            }

            public void mouseReleased(MouseEvent event) {
                showIfPopupTrigger(event);
            }

            public void showIfPopupTrigger(MouseEvent event) {
                if (event.isPopupTrigger()) {
                    popupMenu.show(table, event.getX(), event.getY());
                }
            }
        });
        itemAjouterLigne.addActionListener(e -> {
            tableModel.ajouteLigne();
            if (tableModel.getRowCount() > 2) {
                itemSupprimerLigneSelectionnee.setEnabled(true);
                itemSupprimerToutesLesLignes.setEnabled(true);
            }
        });

        itemSupprimerLigneSelectionnee.addActionListener(e -> {
            tableModel.supprimerLigne(table.getSelectedRow());
            if (tableModel.getRowCount() == 2) {
                itemSupprimerLigneSelectionnee.setEnabled(false);
                itemSupprimerToutesLesLignes.setEnabled(false);
            }
        });

        itemSupprimerToutesLesLignes.addActionListener(e -> {
            tableModel.supprimerLignes();
            tableModel.ajouteLigne();
            tableModel.ajouteLigne();
            itemSupprimerToutesLesLignes.setEnabled(false);
        });

        b_nouveauClient.addActionListener(arg0 -> nouveauClient.setVisible(true));
        venteComptant.addActionListener(arg0 -> {
            venteCredit.setSelected(venteComptant.isSelected());
        });

        venteCredit.addActionListener(e -> {
            venteComptant.setSelected(venteCredit.isSelected());
        });
        b_enregistrer.addActionListener(e -> effectuerVente());
        b_apercuFacture.addActionListener(e -> new InterfaceFacture(tableModel.getLigneDeVente()));
    }


    private void afficheComposantTable() {
        table.getColumn("DESIGNATION").setCellEditor(new DefaultCellEditor(comboboxDesignation));
        table.getColumn("QUANTITÉ").setCellEditor(new DefaultCellEditor(new TextFieldTable()));
        table.getColumn("PRIX UNITAIRE H.T.").setCellEditor(new DefaultCellEditor(new TextFieldTable()));
        table.getColumn("TOTAL H.T.").setCellEditor(new DefaultCellEditor(new TextFieldTable()));
        table.getColumn("TAUX T.V.A").setCellEditor(new DefaultCellEditor(new TextFieldTable()));
        table.getColumn("MONTANT TVA").setCellEditor(new DefaultCellEditor(new TextFieldTable()));
        table.getColumn("TOTAL T.T.C.").setCellEditor(new DefaultCellEditor(new TextFieldTable()));
    }


    private void afficheListeClient() {
        comboListeClient.removeAllItems();
        comboListeClient.addItem("");
        LinkedList<Client> listeClient = clientService.listeClient();
        for (Client client : listeClient) {
            comboListeClient.addItem(client.getNomClient() + " " + client.getPrenomClient());
        }
    }

    public int enregistrerVente(String etatVente, String nomClient, String prenomClient) {
        Session session = userService.getLatestSession(UserService.getLoggedInUser().getIdutilisateur());
        return salesService.enregistreOperationDeVente(tableModel.getLigneDeVente()
                , session.getIdsession()
                , etatVente
                , Double.parseDouble(labelMontantTotalHT.getText())
                , Double.parseDouble(labelMontantTotalTVA.getText())
                , nomClient
                , prenomClient
                , Double.parseDouble(labelMontantTotalTTC.getText()));
    }

    public void effectuerVente() {
        Client client = getClient((String) comboListeClient.getSelectedItem());
        if (verifierListeVide()) {
            if (verifierLesLignesDeVente()) {
                if (venteComptant.isSelected()) {
                    if (enregistrerVente(VENTECOMPTANT, client.getNomClient(), client.getPrenomClient()) == SalesService.SUCCES_OPERATION) {
                        afficheLigne();
                        afficheListeClient();
                        reinitilaiseMontants();
                    }
                } else {
                    if (enregistrerVente(VENTECREDIT, client.getNomClient(), client.getPrenomClient()) == SalesService.SUCCES_OPERATION) {
                        afficheLigne();
                        afficheListeClient();
                        reinitilaiseMontants();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(nouveauClient, "Verifier les différentes lignes de vente", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(nouveauClient, "Aucune ligne de vente trouvée", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void afficheListeProduit() {
        categorieService.listeCategorie().forEach(comboboxDesignation::addItem);

    }

    @Override
    public void nouvelleLigneDeVente(VenteEvent event) {
        ListIterator<LigneDeVente> listeDeVente = tableModel.getLigneDeVente().listIterator();
        LigneDeVente ligneCourante;
        double montantTotalHT = 0, montantTotalTVA = 0, montantTotalTTC = 0;
        while (listeDeVente.hasNext()) {
            ligneCourante = listeDeVente.next();
            if (!ligneCourante.getDesignation().equals("")) {
                montantTotalHT += Double.parseDouble(ligneCourante.getPrixGlobalHt());
                montantTotalTVA += Double.parseDouble(ligneCourante.getMontantTva());
                montantTotalTTC += Double.parseDouble(ligneCourante.getPrixGlobalTtc());
            }
        }
        labelMontantTotalHT.setText(montantTotalHT + "");
        labelMontantTotalTVA.setText(montantTotalTVA + "");
        labelMontantTotalTTC.setText(montantTotalTTC + "");
    }

    public Client getClient(String nom_prenom) {
        Client client = new Client();
        if (!nom_prenom.equals("")) {
            for (int i = 0; i < nom_prenom.length(); i++) {
                if (nom_prenom.charAt(i) == ' ') {
                    client.setNomClient(nom_prenom.substring(0, i));
                    client.setPrenomClient(nom_prenom.substring(i + 1));
                    break;
                }
            }
        } else {
            client.setNomClient("DEFAULT");
            client.setPrenomClient("DEFAULT");
        }
        return client;
    }

    private void afficheLigne() {
        tableModel.getLigneDeVente().clear();
        tableModel.ajouteLigne();
        tableModel.ajouteLigne();
        comboListeClient.setSelectedItem("");
    }

    private boolean verifierLesLignesDeVente() {
        boolean returnValue = true;
        ListIterator<LigneDeVente> listeDeVente = tableModel.getLigneDeVente().listIterator();
        LigneDeVente ligneCourante;
        while (listeDeVente.hasNext()) {
            ligneCourante = listeDeVente.next();
            if ((ligneCourante.getDesignation().equals("") && !ligneCourante.getQuantite().equals(""))
                    || (!ligneCourante.getDesignation().equals("") && ligneCourante.getQuantite().equals(""))) {
                returnValue = false;
                break;
            }
        }
        return returnValue;
    }

    private boolean verifierListeVide() {
        return tableModel.getLigneDeVente().stream()
                .anyMatch(lv -> StringUtils.isNotBlank(lv.getDesignation()) || StringUtils.isNotBlank(lv.getQuantite()));
    }

    public void reinitilaiseMontants() {
        labelMontantTotalHT.setText("0.0");
        labelMontantTotalTTC.setText("0.0");
        labelMontantTotalTVA.setText("0.0");
    }

    @Override
    public void ajouterClient(ClientEvent event) {
        afficheListeClient();
    }

    @Override
    public void ajouterProduit(ProduitEvent event) {
        afficheListeProduit();
    }
}
