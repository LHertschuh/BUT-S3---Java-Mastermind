package Vue;

import Modele.Indice;
import Modele.LigneIndice;

import javax.swing.*;
import java.awt.*;

import static Vue.EcranDeJeu.rogneurImageIcon;

public class IndiceClassique implements IndiceStrategie {
   @Override
    public JPanel ajoutIndice(LigneIndice ligne_indice) {

        ImageIcon imageIcon ;
        JPanel indices= new JPanel(new GridLayout(0,2));
        indices.setPreferredSize(new Dimension(80, 80));
        JLabel[] indices_jlabel = new JLabel[ligne_indice.tailleLigneIndice()];
        int bien_place = ligne_indice.avoirNombreTypeIndice(Indice.BIEN_PLACE);
        int mal_place = ligne_indice.avoirNombreTypeIndice(Indice.MAL_PLACE);

        for (int i = 0; i< bien_place; i++){
           // System.out.println("BIEN PLACE "+i);
            imageIcon = EcranDeJeu.rogneurImageIcon("Mastermind/src/images/pion_7.png",15);
            indices_jlabel[i] = new JLabel(imageIcon);
            indices_jlabel[i].setPreferredSize(new Dimension(15, 17));
            indices_jlabel[i].setBackground(null);
            indices_jlabel[i].setOpaque(true);
            indices.add(indices_jlabel[i]);
        }

        for (int i = bien_place; i< bien_place+mal_place; i++){
           // System.out.println("MAL PLACE "+i);
            imageIcon = EcranDeJeu.rogneurImageIcon("Mastermind/src/images/pion_8.png",15);
            indices_jlabel[i] = new JLabel(imageIcon);
            indices_jlabel[i].setPreferredSize(new Dimension(15, 17));
            indices_jlabel[i].setBackground(null);
            indices_jlabel[i].setOpaque(true);
            indices.add(indices_jlabel[i]);
        }

        for (int i = bien_place + mal_place; i < indices_jlabel.length; i++) {
            System.out.println("ABSENT "+i);
            //imageIcon = RogneurImageIcon("Mastermind/src/images/pion_secret.png", 15);
            //indices_jlabel[i] = new JLabel(imageIcon);
            indices_jlabel[i] = new JLabel();

            indices_jlabel[i].setPreferredSize(new Dimension(15, 17));
            indices_jlabel[i].setBackground(null);
            indices_jlabel[i].setOpaque(true);
            indices.add(indices_jlabel[i]);
        }

     //  indices.setBorder(new LineBorder(Color.GRAY,1));



        return indices;
    }

}
