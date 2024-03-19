package Vue;

import Modele.Indice;
import Modele.LigneIndice;

import javax.swing.*;
import java.awt.*;

import static Vue.EcranDeJeu.rogneurImageIcon;

 class IndiceFacile implements IndiceStrategie{
    @Override
    public JPanel ajoutIndice(LigneIndice ligne_indice) {

        ImageIcon imageIcon ;
        JPanel indices= new JPanel(new GridLayout(0,2));
        indices.setPreferredSize(new Dimension(80, 80));
        JLabel[] indices_jlabel = new JLabel[ligne_indice.tailleLigneIndice()];
        int bien_place = ligne_indice.avoirNombreTypeIndice(Indice.BIEN_PLACE);
        int mal_place = ligne_indice.avoirNombreTypeIndice(Indice.MAL_PLACE);


        for(int i = 0; i< ligne_indice.tailleLigneIndice(); i++){
            if(ligne_indice.avoirIndiceIndex(i)== Indice.ABSENT){
                indices_jlabel[i] = new JLabel();
            }
            else{
                if(ligne_indice.avoirIndiceIndex(i)==Indice.BIEN_PLACE){
                    imageIcon = EcranDeJeu.rogneurImageIcon("Mastermind/src/images/pion_7.png",15);
                }
                else{
                    imageIcon = EcranDeJeu.rogneurImageIcon("Mastermind/src/images/pion_8.png",15);
                }
                indices_jlabel[i] = new JLabel(imageIcon);
            }
            indices_jlabel[i].setPreferredSize(new Dimension(15, 17));
            indices_jlabel[i].setBackground(null);
            indices_jlabel[i].setOpaque(true);
            indices.add(indices_jlabel[i]);
        }



        return indices;
    }
}
