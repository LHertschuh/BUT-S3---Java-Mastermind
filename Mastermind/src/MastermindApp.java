import Controleur.PartieControleur;
import Modele.*;
import Vue.EcranDeDemarrage;
import Vue.*;

public class MastermindApp {
    public static void main(String[] args){

        Mastermind mastermind = new Mastermind();
        PartieControleur controleur_partie = new PartieControleur(mastermind);
        EcranDeDemarrage ecran = new EcranDeDemarrage(controleur_partie);

    }
}

