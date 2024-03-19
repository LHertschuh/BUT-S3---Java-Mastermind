package Vue;

import Controleur.PartieControleur;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import static Vue.EcranDeJeu.rogneurImageIcon;

public class EcranDeDemarrage extends JFrame {



    private PartieControleur controleur_partie;
    private JPanel parametre;
    private GridBagConstraints constraints;
    private JLabel image_indice;
    private JComboBox<String> comboBoxMode;

    public EcranDeDemarrage(PartieControleur controleur_partie) {
        super("Mastermind - Menu - Hertschuh Louis");
        this.controleur_partie = controleur_partie;
        setSize(400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        parametre = new JPanel();
        parametre.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.gridx=0;
        constraints.gridy=0;

        JLabel nom = new JLabel("Hertschuh Louis");
        nom.setFont(new Font("Arial", Font.BOLD, 25) );
        nom.setForeground(new Color(244,162,97));
        parametre.add(nom,constraints);
       // parametre.setBackground(new Color(38,70,83));
        parametre.setBackground(new Color(42,157,143));


        JLabel mastermind = new JLabel("Mastermind",JLabel.CENTER);
        mastermind.setForeground(new Color(231,111,81));
        mastermind.setFont(new Font("Arial", Font.BOLD, 40) );
        constraints.gridx=0;
        constraints.gridy=1;
        parametre.add(mastermind,constraints);



        miseEnPlaceMenu();
        pack();
        setVisible(true);
    }

    private void miseEnPlaceMenu(){
        JLabel lblMode = new JLabel();
        lblMode.setText("Mode d'indice  : ");

        // Ajouter les modes disponibles à la JComboBox
        String[] modes = {"Classique", "Facile", "Numerique"};
        comboBoxMode = new JComboBox<>(modes);
        comboBoxMode.addActionListener(e -> {
            // Code pour réagir au changement de sélection
            String selectedMode = (String) comboBoxMode.getSelectedItem();
            // Appeler une méthode pour changer l'image en fonction du mode sélectionné
            changerImageEnFonctionDuMode(selectedMode);
        });

        JPanel panel_boutons = new JPanel();
        panel_boutons.setLayout( new GridBagLayout() );





        image_indice = new JLabel(rogneurImageIcon("Mastermind/src/images/IndiceClassique.png",210,237));
        constraints.gridy=2;
        constraints.gridx=1;
        parametre.add(image_indice,constraints);




        JLabel lblnb_manches = new JLabel();
        lblnb_manches.setText("Nombre de manches : ");


        JLabel lblnb_pions_dispo = new JLabel();
        lblnb_pions_dispo.setText("Nombre de pions disponibles : ");

        JLabel lblnb_pions_combinaison = new JLabel();
        lblnb_pions_combinaison.setText("Nombre de pions dans les combinaisons : ");

        JLabel lbl_nb_tentatives = new JLabel();
        lbl_nb_tentatives.setText("Nombre de tentatives : ");


        JLabel lbl_nom_utilisateur = new JLabel("Nom du joueur");

        JTextField txt_nom_utilisateur = new JTextField("Joueur");
        txt_nom_utilisateur.setPreferredSize(new Dimension(200,20));
        txt_nom_utilisateur.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String texteSaisi = txt_nom_utilisateur.getText();
                if(txt_nom_utilisateur.getText().isEmpty()){
                    txt_nom_utilisateur.setText("Joueur");
                }
                //JOptionPane.showMessageDialog(ExempleTextField.this, "Texte saisi : " + texteSaisi);
            }
        });
        txt_nom_utilisateur.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String texteSansEspaces = txt_nom_utilisateur.getText().trim();
                if (texteSansEspaces.isEmpty()) {
                    txt_nom_utilisateur.setText("Joueur");
                } else {
                    txt_nom_utilisateur.setText(texteSansEspaces);
                }
            }
        });

        JLabel txt_nb_slider_manche = new JLabel("3");

        JSlider slider_nb_manche = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);


        slider_nb_manche.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                txt_nb_slider_manche.setText(String.valueOf(slider_nb_manche.getValue()));
            }

        });

        JLabel txt_pions_dispo = new JLabel("8");

        JSlider slider_pions_dispo = new JSlider(JSlider.HORIZONTAL, 4, 8, 8);
        slider_pions_dispo.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                txt_pions_dispo.setText(String.valueOf(slider_pions_dispo.getValue()));
            }

        });


        JLabel txt_pions_combinaison = new JLabel("4");

        JSlider slider_pions_combinaison = new JSlider(JSlider.HORIZONTAL, 2, 6, 4);
        slider_pions_combinaison.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                txt_pions_combinaison.setText(String.valueOf(slider_pions_combinaison.getValue()));
            }

        });

        JLabel txt_nb_tentatives = new JLabel("10");

        JSlider slider_nb_tentatives = new JSlider(JSlider.HORIZONTAL, 2, 12, 10);
        slider_nb_tentatives.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                txt_nb_tentatives.setText(String.valueOf(slider_nb_tentatives.getValue()));
            }

        });



        Color couleur_slider = new Color(74,189,175);


        slider_nb_tentatives.setBackground(couleur_slider);
        slider_nb_manche.setBackground(couleur_slider);
        slider_pions_combinaison.setBackground(couleur_slider);
        slider_pions_dispo.setBackground(couleur_slider);

        JLabel vide = new JLabel();
        vide.setPreferredSize(new Dimension(0,0));
        constraints.gridx=0;
        constraints.gridy=0;

        panel_boutons.add(lblMode,constraints);
        constraints.gridx=1;
        panel_boutons.add(comboBoxMode,constraints);


        constraints.gridx=0;
        constraints.gridy=1;
        panel_boutons.add(lblnb_manches,constraints);
        constraints.gridx=1;
        //slider_nb_manche.setPreferredSize(new Dimension(30, 0));
        panel_boutons.add(slider_nb_manche,constraints);
        constraints.gridx=2;
        txt_nb_slider_manche.setPreferredSize(new Dimension(60,25));
        txt_nb_slider_manche.setHorizontalAlignment(JTextField.CENTER);
        panel_boutons.add(txt_nb_slider_manche,constraints);

        constraints.gridx=0;
        constraints.gridy=2;
        panel_boutons.add(lblnb_pions_dispo,constraints);
        constraints.gridx=1;
        panel_boutons.add(slider_pions_dispo,constraints);
        constraints.gridx=2;
        txt_pions_dispo.setPreferredSize(new Dimension(60,25));
        txt_pions_dispo.setHorizontalAlignment(JTextField.CENTER);
        panel_boutons.add(txt_pions_dispo,constraints);

        constraints.gridx=0;
        constraints.gridy= 3;
        panel_boutons.add(lblnb_pions_combinaison,constraints);
        constraints.gridx=1;
        panel_boutons.add(slider_pions_combinaison,constraints);
        constraints.gridx=2;
        txt_pions_combinaison.setPreferredSize(new Dimension(60,25));
        txt_pions_combinaison.setHorizontalAlignment(JTextField.CENTER);
        panel_boutons.add(txt_pions_combinaison,constraints);

        constraints.gridx=0;
        constraints.gridy=4;
        panel_boutons.add(lbl_nb_tentatives,constraints);
        constraints.gridx=1;
        panel_boutons.add(slider_nb_tentatives,constraints);
        constraints.gridx=2;
        txt_nb_tentatives.setPreferredSize(new Dimension(60,25));
        txt_nb_tentatives.setHorizontalAlignment(JTextField.CENTER);
        panel_boutons.add(txt_nb_tentatives,constraints);

        constraints.gridx=0;
        constraints.gridy=5;
        panel_boutons.add(lbl_nom_utilisateur,constraints);
        constraints.gridx=1;
        panel_boutons.add(txt_nom_utilisateur,constraints);

        constraints.gridx=0;
        constraints.gridy=2;

        panel_boutons.setBackground(new Color(26,141,127));
        parametre.add(panel_boutons,constraints);



        JButton validerButton = new JButton("Valider");
        validerButton.setPreferredSize(new Dimension(200, 50));
        validerButton.addActionListener( actionEvent  -> {
          //  int nb_manches = Integer.parseInt(txtnb_manches.getText());
            int nb_manches = slider_nb_manche.getValue();
            int nb_pions_dispo =slider_pions_dispo.getValue();
            int nb_pions_combi = slider_pions_combinaison.getValue();
            int nb_tentatives = slider_nb_tentatives.getValue();
            String selectedMode = (String) comboBoxMode.getSelectedItem();


            EcranDeJeu ecran = new EcranDeJeu(nb_manches,nb_pions_dispo,nb_tentatives,nb_pions_combi,controleur_partie);
            controleur_partie.ajouteObservateurPartie(ecran);
            controleur_partie.creerPartie( txt_nom_utilisateur.getText(),nb_manches, nb_pions_dispo, nb_tentatives,nb_pions_combi);
            IndiceStrategie strategie = new IndiceClassique();
            if(selectedMode=="Facile"){
                strategie = new IndiceFacile();
                controleur_partie.changerModeIndice("Facile");
            }
            else if(selectedMode=="Numerique"){
                strategie= new IndiceNumerique();
                controleur_partie.changerModeIndice("Numerique");
            }


            ecran.changerIndiceStrategie(strategie);




            dispose();
        });


        JButton reintialiserButton = new JButton("Réinitialiser");
        reintialiserButton.addActionListener( actionEvent  -> {
            slider_nb_manche.setValue(3);
            slider_pions_combinaison.setValue(4);
            slider_nb_tentatives.setValue(10);
            slider_pions_dispo.setValue(8);
            txt_nom_utilisateur.setText("Joueur");
            comboBoxMode.setSelectedIndex(0);
                });

        constraints.gridx=0;
        constraints.gridy=3;

        parametre.add(validerButton,constraints);
        constraints.gridx=1;
        parametre.add(reintialiserButton,constraints);
        this.add(parametre);
        }

    private void changerImageEnFonctionDuMode(String selectedMode) {
        // Code pour changer l'image en fonction du mode sélectionné
        ImageIcon nouvelleImage = null;

        // Exemple : Charger différentes images pour chaque mode
        switch (selectedMode) {
            case "Facile":

                nouvelleImage = rogneurImageIcon("Mastermind/src/images/IndiceFacile.png",210,237);
                break;
            case "Numerique":
                nouvelleImage = rogneurImageIcon("Mastermind/src/images/IndiceNumerique.png",210,237);
                break;
            case "Classique":
                nouvelleImage = rogneurImageIcon("Mastermind/src/images/IndiceClassique.png",210,237);
                break;

        }

        // Appliquer la nouvelle image à un JLabel, par exemple
        image_indice.setIcon(nouvelleImage);


    }

    }
