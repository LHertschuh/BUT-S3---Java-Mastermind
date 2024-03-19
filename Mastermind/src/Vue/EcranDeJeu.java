package Vue;


import Controleur.PartieControleur;
import Modele.*;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EcranDeJeu extends JFrame implements PartieObservateur {
    private JPanel panel;
    private JButton supprimer;
private JButton abandonner;
    private JButton valider;
    private JButton continuer;
    private GridBagConstraints constraints;
    private JPanel[] combis;
    private Integer num_tentative = 0;
    private JPanel panel_combi_secrete;
    private PartieControleur controleur;
    JPanel panel_pion;
    private Integer pion_selectionne = null;
    private Boolean jouable = true;
    private Integer[] parametres;
    private JPanel[] indices;
    private JPanel panel_indices;
    private IndiceStrategie indice_strategie;
    private JLabel lbl_manche;
    private Integer num_manche;
    private Integer nb_manches_max;
    private Integer nb_pions_combi;
    public EcranDeJeu(Integer nb_manches, Integer nb_pions_dispo, Integer nb_tentatives, Integer nb_pions_combi, PartieControleur controleur) {

        super("Mastermind - Jeu - Hertschuh Louis"); // ou setTitle("My app")
        try {
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch(Exception ignored){}
        nb_manches_max=nb_manches;
        parametres = new Integer[4];
        parametres[0] = nb_manches;
        parametres[1] = nb_pions_dispo;
        parametres[2] = nb_tentatives;
        parametres[3] = nb_pions_combi;
        this.nb_pions_combi = nb_pions_combi;
        num_manche=1;
        indice_strategie = new IndiceClassique();
        this.controleur = controleur;
        setSize(250, 599);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

    //setLocationRelativeTo(null);
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        setContentPane(panel);

        ImageIcon imageIcon = rogneurImageIcon("Mastermind/src/images/pion_vide.png", 64);
         valider = new JButton("Valider combinaison");
        valider.addActionListener(actionEvent-> {
            if(jouable) {
                controleur.validerCombinaison();
                setCursor(Cursor.getDefaultCursor());
                pion_selectionne = null;
            }
        });

         abandonner = new JButton("Abandonner manche");
         abandonner.addActionListener(actionEvent->{
            controleur.abandonnerManche();
         });
         supprimer = new JButton("Supprimer combinaison");
        continuer = new JButton("Manche Suivante");
        supprimer.addActionListener(actionEvent -> {
            if(jouable) {
                controleur.suppressionCombinaison();
            }
        });
        valider.setAlignmentX(Component.CENTER_ALIGNMENT);
        abandonner.setAlignmentX(Component.CENTER_ALIGNMENT);
        supprimer.setAlignmentX(Component.CENTER_ALIGNMENT);
        continuer.setAlignmentX(Component.CENTER_ALIGNMENT);


        continuer.addActionListener(actionEvent->{
            if(!jouable) {
                controleur.mancheSuivante();

            }
        });
        continuer.setEnabled(false);

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel panel_combi = new JPanel();
        panel_combi.setLayout(new BoxLayout(panel_combi, BoxLayout.X_AXIS));


        combis = new JPanel[nb_tentatives];
        JLabel[] pions = new JLabel[nb_pions_combi];


        constraints.gridx = 0;
        constraints.gridy = 1;
        for (int i = 0; i < nb_tentatives; i++) {
            combis[i] = new JPanel(new GridLayout(nb_pions_combi, 1)); // Utilisation de GridLayout
            combis[i].setBackground(Color.LIGHT_GRAY);
            combis[i].setOpaque(true);

            panel_combi.add(combis[i]);
            MouseHandlerPionCombinaison myMouseHandler = new MouseHandlerPionCombinaison();
            for (int j = 0; j < nb_pions_combi; j++) {
                pions[j] = new JLabel(imageIcon);
                pions[j].setPreferredSize(new Dimension(80, 80));
                pions[j].setBackground(null);
                pions[j].setOpaque(true);
                pions[j].addMouseListener(myMouseHandler);
                pions[j].setName(""+j);
                combis[i].add(pions[j]);
            }
        }

        panel.add(panel_combi, constraints);
        ajoutLigneCombiSecrete(nb_pions_combi);
        selectionPion(nb_pions_dispo);


         lbl_manche = new JLabel("",JLabel.LEFT);
         lbl_manche.setPreferredSize(new Dimension(100,30));
        lbl_manche.setFont(new Font("Arial", Font.BOLD, 15));


        //constraints.anchor = GridBagConstraints.WEST;
        JPanel panel_bouton = new JPanel();
        panel_bouton.setLayout(new BoxLayout(panel_bouton, BoxLayout.Y_AXIS));
        constraints.gridx = 2;
        constraints.gridy = 1;

        panel_bouton.add(lbl_manche);
        constraints.anchor = GridBagConstraints.CENTER;
        panel_bouton.add(valider);

        panel_bouton.add(abandonner);

        panel_bouton.add(supprimer);

        panel_bouton.add(continuer);

        panel.add(panel_bouton, constraints);

        //#############
         panel_indices  = new JPanel();
        panel_indices.setLayout(new BoxLayout(panel_indices, BoxLayout.X_AXIS));
         indices = new JPanel[nb_tentatives];
        constraints.gridx=0;
        constraints.gridy=0;

        for (int i=0;i<indices.length;i++){
            indices[i] = new JPanel(new GridLayout(0,2));
            indices[i].setPreferredSize(new Dimension(80, 80));
            //indices[i].setBorder(new LineBorder(Color.GRAY,1));
            panel_indices.add(indices[i]);

            //JSeparator separator = new JSeparator(JSeparator.VERTICAL);
            //separator.setPreferredSize(new Dimension(1, 80)); // Ajustez la taille de la ligne selon vos besoins

            //panel_indices.add(separator);

        }

        panel.add(panel_indices, constraints);


        pack();
        setVisible(true);

    }


private void viderIndice(){
    for (int i=0; i<indices.length;i++) {
        panel_indices.remove(i);
        panel_indices.add(new JPanel(new GridLayout(0,2)), i);
        ((JPanel) (panel_indices.getComponent(i))).setPreferredSize(new Dimension(80, 80));
        panel_indices.revalidate();
    }
}

    private void ajoutLigneCombiSecrete(Integer nb_pions_combi) {

        panel_combi_secrete = new JPanel(new GridLayout(nb_pions_combi, 1));
        JLabel[] pions = new JLabel[nb_pions_combi];

        ImageIcon imageIcon = rogneurImageIcon("Mastermind/src/images/pion_secret.png", 64);
        for (int j = 0; j < nb_pions_combi; j++) {
            pions[j] = new JLabel(imageIcon);
            pions[j].setPreferredSize(new Dimension(80, 80));
            //pions[j].setBackground(Color.GRAY);
            pions[j].setOpaque(true);

            panel_combi_secrete.add(pions[j]);
        }

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(panel_combi_secrete, constraints);

    }



    private void selectionPion(Integer nb_pions_dispo) {
        panel_pion = new JPanel();
        int nb_pions_grid_x = 2;
        if (nb_pions_dispo >= 7) {
            nb_pions_grid_x = 4;
        } else if (nb_pions_dispo >= 5) {
            nb_pions_grid_x = 3;
        }
        panel_pion.setLayout(new GridLayout(0, nb_pions_grid_x));

        JLabel[] pions = new JLabel[nb_pions_dispo];
        ImageIcon imageIcon = rogneurImageIcon("Mastermind/src/images/pion_vide.png", 24);
        MouseHandlerPionASelectionner myMouseHandler = new MouseHandlerPionASelectionner();
        for (int i = 0; i < nb_pions_dispo; i++) {
            imageIcon = rogneurImageIcon("Mastermind/src/images/pion_" + (i + 1) + ".png", 40);
            pions[i] = new JLabel(imageIcon);
            pions[i].setPreferredSize(new Dimension(40, 45));
            pions[i].setBackground(null);
            pions[i].setOpaque(true);
           pions[i].setName(""+(i+1));

            pions[i].addMouseListener(myMouseHandler);
            // pions[i].addMouseListener();
            panel_pion.add(pions[i]);
        }
        constraints.gridx = 2;
        constraints.gridy = 0;
        panel.add(panel_pion, constraints);

    }

    protected static ImageIcon rogneurImageIcon(String source, Integer x) {
        ImageIcon imageIcon = new ImageIcon(source);
        Image img = imageIcon.getImage();
        Image newImg = img.getScaledInstance(x, x, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        return imageIcon;
    }

    protected static ImageIcon rogneurImageIcon(String source, Integer x, Integer y) {
        ImageIcon imageIcon = new ImageIcon(source);
        Image img = imageIcon.getImage();
        Image newImg = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        return imageIcon;
    }


    public void changerIndiceStrategie(IndiceStrategie algorithme){
    indice_strategie = algorithme;
}
    @Override
    public void majCombinaison(Integer index, Pion pion) {
        Component[] components = combis[num_tentative].getComponents(); // Récupère les composants du premier JPanel

        int labelCounter = 0;

        for (Component component : components) {

            if (component instanceof JLabel) {
                if (labelCounter == index) {
                    JLabel troisiemeLabel = (JLabel) component; // Renvoie le troisième JLabel trouvé
                    troisiemeLabel.setIcon(rogneurImageIcon("Mastermind/src/images/pion_" + (pion.ordinal() + 1) + ".png", 64));
                    break;
                }
                labelCounter++;
            }
        }


    }

    @Override
    public void majCombiSecrete(Combinaison combinaison) {
        Component[] components = panel_combi_secrete.getComponents(); // Récupère les composants du premier JPanel
        //System.out.println("--------COMBI SECRETE " + (combinaison.lirePion(0).ordinal() + 1) + " OK");
        int labelCounter = 0;
        //System.out.println("Compte nombre composants "+ components.length);
        for (Component component : components) {

            if (component instanceof JLabel label && labelCounter<nb_pions_combi) {
              //  System.out.println(labelCounter+" < "+ nb_pions_combi+" label orginal" + combinaison.lirePion(labelCounter).ordinal());
                label.setIcon(rogneurImageIcon("Mastermind/src/images/pion_" + (combinaison.lirePion(labelCounter).ordinal() + 1) + ".png", 64));
                //System.out.println("COMBI SECRETE " + (combinaison.lirePion(labelCounter).ordinal() + 1) + " OK");
                labelCounter++;
            }
        }
    }

    @Override
    public void majFinDeManche() {
       // System.out.println("############################");
        jouable=false;
        valider.setEnabled(false);
        supprimer.setEnabled(false);
        abandonner.setEnabled(false);
        continuer.setEnabled(true);



    }

    @Override
    public void majNouvelleManche() {
        ImageIcon imageIcon = rogneurImageIcon("Mastermind/src/images/pion_vide.png", 64);
        Component[] components;
        for(JPanel combi : combis){
            components = combi.getComponents();
            combi.setBackground(Color.LIGHT_GRAY);
            for (Component component : components) {

                if (component instanceof JLabel label) {
                    label.setIcon(imageIcon);
                    label.setBackground(null);

                }
            }

        }
        combis[0].setBackground(Color.GRAY);

        imageIcon = rogneurImageIcon("Mastermind/src/images/pion_secret.png", 64);
        components = panel_combi_secrete.getComponents();
        for(Component component: components){
            if(component instanceof JLabel label ){
                label.setIcon(imageIcon);
                label.setBackground(Color.GRAY);
            }
        }



        jouable = true;
        valider.setEnabled(true);
        supprimer.setEnabled(true);
        abandonner.setEnabled(true);
        continuer.setEnabled(false);
        //System.out.println(num_tentative);
        viderIndice();
        num_tentative=0;

        lbl_manche.setText("Manche "+num_manche+"/"+nb_manches_max);
        num_manche++;
    }

    @Override
    public void majFinDePartie(String nom_joueur, Boolean[] victoire, Integer[] scores) {
        EcranDeFin ecran = new EcranDeFin(nom_joueur,victoire,scores,parametres, indice_strategie,controleur);
       // controleur_partie.creerPartie("Louis", nbManches, nbPionsDispo, nbTenta,nbPionsCombi);

        //controleur_partie.AjouteObservateurPartie(ecran);
        // controleur_partie.MancheSuivante();

        dispose();
    }
    @Override
    public void majSuppTentative() {
        Component[] components = combis[num_tentative].getComponents();  // Récupère les composants du premier JPanel

        int labelCounter = 0;

        for (Component component : components) {
            if (component instanceof JLabel label) {

                label.setIcon(rogneurImageIcon("Mastermind/src/images/pion_vide.png", 64));

                labelCounter++;
            }
        }
    }

    @Override
    public void majTentative() {
        combis[num_tentative].setBackground(Color.LIGHT_GRAY);
        num_tentative++;
        if(jouable) {
            combis[num_tentative].setBackground(Color.GRAY);
        }
       // System.out.println("NUM LMANCHE " + num_tentative);
    }

    @Override
    public void majIndice(LigneIndice ligne_indice){



        panel_indices.remove(num_tentative-1);
        panel_indices.add(indice_strategie.ajoutIndice(ligne_indice),num_tentative-1);
        ((JPanel)(panel_indices.getComponent(num_tentative-1))).setBorder(new LineBorder(Color.GRAY,1));
        panel_indices.revalidate();
    }


    private class MouseHandlerPionASelectionner extends MouseAdapter {

            @Override
            public void mouseEntered(MouseEvent evt) {
                Component[] components = panel_pion.getComponents();
                JLabel source = (JLabel) evt.getSource();
                for (Component component : components) {
                    if (component instanceof JLabel && component.equals(source)) {
                        component.setBackground(Color.GRAY);
                    } else if (component instanceof JLabel) {
                        component.setBackground(null);
                    }
                }
            }

        @Override
        public void mousePressed(MouseEvent evt) {
            if (jouable) {
                JLabel source = (JLabel) evt.getSource();
                ImageIcon icon = rogneurImageIcon("Mastermind/src/images/pion_" + source.getName() + ".png", 100); // Récupère l'icône du JLabel
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        icon.getImage(),
                        new Point(0, 0),
                        "custom cursor"
                ));
                pion_selectionne = Integer.parseInt(source.getName());
            }
        }
        }

    private class MouseHandlerPionCombinaison extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent evt) {
            if(jouable) {
            JLabel source = (JLabel) evt.getSource();
            Component[] components = combis[num_tentative].getComponents();
            for (Component component : components) {
                if (component.equals(source)) {
                    source.setBackground(Color.DARK_GRAY);
                    break;
                }
            }
            }
        }

        @Override
        public void mouseExited(MouseEvent evt) {
            if(jouable) {
                JLabel source = (JLabel) evt.getSource();
                Component[] components = combis[num_tentative].getComponents();
                for (Component component : components) {
                    if (component.equals(source)) {
                        source.setBackground(null);
                        break;
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent evt) {
            if(pion_selectionne!=null) {
                JLabel source = (JLabel) evt.getSource();
                Component[] components = combis[num_tentative].getComponents();
                for (Component component : components) {
                    if (component instanceof JLabel && component.equals(source)) {
                       // System.out.println(source.getName());
                        controleur.ajoutPionCombinaison(Integer.parseInt(source.getName()), pion_selectionne - 1);

                        break; // Sortir de la boucle après avoir trouvé le JLabel correspondant
                    }
                }
            }
        }
    }
    }

