package Modele;

import java.util.ArrayList;
import java.util.List;

public class Mastermind {
    private Partie partie;
    private List<PartieObservateur> observateurs;
    public Mastermind(){
        this.partie = null;
        observateurs= new ArrayList<>();
    }
    public void creerPartie(String nom_utilisateur, Integer nb_manches, Integer nb_pions_dispo, Integer nb_tentatives, Integer nb_pions_combi){
        this.partie = null;
        this.partie = new Partie(nom_utilisateur,nb_manches,nb_pions_dispo,nb_tentatives,nb_pions_combi);
       // partie.nouvelleManche();
        ajouteObservateurAPartie();
        //ajouteObservateurAPartie();
    }

    public Partie avoirPartie(){
        //partie.obtenirManche().obtenirDerniereTentative().placerPion(1, Pion.BLANC);
        return this.partie;
    }

    public void ajouteObservateur(PartieObservateur observateur) {

        observateurs.add(observateur);

    }

    public void ajouteObservateurAPartie(){

        for(PartieObservateur obser : observateurs){
            partie.ajouteObservateur(obser);

        }
    }
}
