package Vue;

import Controleur.PartieControleur;

import javax.swing.*;
import java.awt.*;

public class EcranDeFin extends JFrame {
    PartieControleur controleur_partie;
    //Pas propre les 20k paremtres
    public EcranDeFin(String nom_joueur, Boolean[] victoire, Integer[] scores,Integer[] parametres,IndiceStrategie indice_strategie,PartieControleur controleur_partie){
        super("Mastermind - Fin de partie - Hertschuh Louis");
        this.controleur_partie = controleur_partie;
        setResizable(false);
        setSize(400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();



        JLabel fin_partie = new JLabel("Fin de partie : "+nom_joueur);

        constraints.gridx = 0;
        constraints.gridy=0;

        panel.add(fin_partie,constraints);

        JPanel jpanel_victoire = new JPanel(new GridLayout(1, parametres[0]));

        for (int i = 0; i < parametres[0]+1; i++) {
            JPanel manchePanel = new JPanel();
            manchePanel.setLayout(new BoxLayout(manchePanel, BoxLayout.Y_AXIS));

            if(i==parametres[0]){
                JLabel label = new JLabel("Total :");
                manchePanel.add(label);
            }
            else{
                JLabel label = new JLabel("Manche " + (i + 1) + ": ");
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                manchePanel.add(label);
            }



            JLabel resultatLabel;

            if(i==parametres[0]){

                if(totalVictoire(victoire)){
                    resultatLabel = new JLabel("Victoire");
                    resultatLabel.setForeground(Color.GREEN);
                }
                else{
                    resultatLabel = new JLabel("Défaite");
                    resultatLabel.setForeground(Color.RED);
                }
                manchePanel.add(resultatLabel);
            }
            else {
                if (victoire[i]) {
                    resultatLabel = new JLabel("Victoire");
                    resultatLabel.setForeground(Color.GREEN);
                } else {
                    resultatLabel = new JLabel("Défaite");
                    resultatLabel.setForeground(Color.RED);
                }
            }
            manchePanel.add(resultatLabel);


            if(i==parametres[0]){
                JLabel scoreLabel_fin = new JLabel("Score total: " + totalScore(scores));
                manchePanel.add(scoreLabel_fin);
            }
            else {
                JLabel scoreLabel = new JLabel("Score: " + scores[i]);
                manchePanel.add(scoreLabel);
            }
            jpanel_victoire.add(manchePanel);



        }

        panel.add(jpanel_victoire);
        JPanel parametre = new JPanel();
        parametre.setLayout(new GridLayout(5, 2));

        JLabel lblNbManches = new JLabel();
        lblNbManches.setText("Nombre de manches : ");


        JLabel lblNbPionsDispo = new JLabel();
        lblNbPionsDispo.setText("Nombre de pions disponibles : ");

        JLabel lblNbPionsCombinaison = new JLabel();
        lblNbPionsCombinaison.setText("Nombre de pions dans les combinaisons : ");

        JLabel lblNbTentatives = new JLabel();
        lblNbTentatives.setText("Nombre de tentatives : ");


        JTextField txtNbManches = new JTextField("3");

        JTextField txtNbPionsDispo = new JTextField("8");

        JTextField txtNbPionsCombinaison = new JTextField("4");

        JTextField txtNbTentatives = new JTextField("10");


        parametre.add(lblNbManches);
        parametre.add(txtNbManches);

        parametre.add(lblNbPionsDispo);
        parametre.add(txtNbPionsDispo);

        parametre.add(lblNbPionsCombinaison);
        parametre.add(txtNbPionsCombinaison);

        parametre.add(lblNbTentatives);
        parametre.add(txtNbTentatives);

        JButton rejouer_ecran_jeu = new JButton("Rejouer");

        JButton rejouer_ecran_demarrage = new JButton("Rejouer avec d'autres paramètres");


        rejouer_ecran_demarrage.addActionListener(actionEvent -> {
            EcranDeDemarrage ecran = new EcranDeDemarrage(controleur_partie);
            dispose(); // Assurez-vous de disposer de la fenêtre actuelle
        });

        rejouer_ecran_jeu.addActionListener(actionEvent -> {
            EcranDeJeu ecran = new EcranDeJeu(parametres[0], parametres[1], parametres[2], parametres[3], controleur_partie);
            controleur_partie.changerModeIndice("Classique");
            controleur_partie.ajouteObservateurPartie(ecran);
            controleur_partie.recommencerPartiePareil();
            ecran.changerIndiceStrategie(indice_strategie);

            if (indice_strategie instanceof IndiceFacile) {
                controleur_partie.changerModeIndice("Facile");
            } else if ((indice_strategie instanceof IndiceNumerique)) {
                controleur_partie.changerModeIndice("Numerique");
            }

            dispose(); // Assurez-vous de disposer de la fenêtre actuelle
        });
        constraints.gridx=0;
        constraints.gridy=1;
        panel.add(rejouer_ecran_demarrage,constraints);
        constraints.gridx=1;
        panel.add(rejouer_ecran_jeu,constraints);



        this.add(panel);
        pack();
        setVisible(true);
    }

    private Boolean totalVictoire(Boolean[] victoire){
        int total =0;
        for(Boolean bool : victoire){
            if(bool){
                total++;
            }
        }

        return ((double)total+0.0>=((double) victoire.length /2));
    }

    private Integer totalScore(Integer[] score){
        int total =0;
        for(Integer points : score){

            total+=points;

        }

        return total;
    }


}
