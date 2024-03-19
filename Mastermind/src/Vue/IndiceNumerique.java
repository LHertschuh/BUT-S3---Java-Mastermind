package Vue;

import Modele.Indice;
import Modele.LigneIndice;

import javax.swing.*;
import java.awt.*;

public class IndiceNumerique implements IndiceStrategie {
    @Override
    public JPanel ajoutIndice(LigneIndice ligne_indice) {
        ImageIcon imageIcon ;
        JPanel indices= new JPanel(new GridLayout(0,2));
        indices.setPreferredSize(new Dimension(80, 80));

        int bien_place = ligne_indice.avoirNombreTypeIndice(Indice.BIEN_PLACE);
        int mal_place = ligne_indice.avoirNombreTypeIndice(Indice.MAL_PLACE);

        JLabel indices_bien_place = new JLabel(String.valueOf(bien_place));
        JLabel indices_mal_place = new JLabel(String.valueOf(mal_place));

        indices_bien_place.setHorizontalAlignment(JLabel.CENTER);
        indices_mal_place.setHorizontalAlignment(JLabel.CENTER);

        Font police = new Font("Arial", Font.PLAIN, 28); // Vous pouvez changer "Arial" à la police de votre choix
        indices_bien_place.setFont(police);
        indices_mal_place.setFont(police);

        indices.add(indices_bien_place );
        indices_bien_place.setAlignmentX(Component.CENTER_ALIGNMENT);
        indices.add(indices_mal_place );
        indices_mal_place.setAlignmentX(Component.CENTER_ALIGNMENT);

        return indices;
    }
}
