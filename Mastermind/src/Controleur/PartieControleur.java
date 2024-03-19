package Controleur;

import Modele.Mastermind;
import Modele.PartieObservateur;
import Modele.Pion;

public class PartieControleur {
    private Mastermind mastermind;

    public PartieControleur( Mastermind mastermind){
        this.mastermind = mastermind;
    }
    public void ajouteObservateurPartie(PartieObservateur observa){
        mastermind.ajouteObservateur(observa);

    }

    public void creerPartie(String nom_utilisateur, Integer nb_manches, Integer nb_pions_dispo, Integer nb_tentatives, Integer nb_pions_combi){
        mastermind.creerPartie(nom_utilisateur,nb_manches,nb_pions_dispo,nb_tentatives,nb_pions_combi);

        mancheSuivante();
        //mastermind.avoirPartie().obtenirManche().obtenirDerniereTentative().placerPion(1,Pion.BLEU);
    }

    public void suppressionCombinaison(){

        mastermind.avoirPartie().obtenirManche().supprimerCombinaison();
    }

    public void ajoutPionCombinaison(Integer index, Integer num_pion){
        Pion pion = Pion.values()[num_pion];
        mastermind.avoirPartie().obtenirManche().obtenirDerniereTentative().placerPion(index,pion);
    }

    public void validerCombinaison(){
        mastermind.avoirPartie().obtenirManche().validerTentative();
    }

    public void mancheSuivante(){
        mastermind.avoirPartie().nouvelleManche();
    }

    public void abandonnerManche(){
        mastermind.avoirPartie().obtenirManche().finDeManche();
    }

    public void recommencerPartiePareil(){
        creerPartie(mastermind.avoirPartie().obtenirNom(),mastermind.avoirPartie().obtenirNbManches(),mastermind.avoirPartie().obtenirNbPionsDispo(),mastermind.avoirPartie().obtenirNbTentatives(),mastermind.avoirPartie().obtenirNbPionsCombi());
    }

    public void changerModeIndice(String mode_indice){
        mastermind.avoirPartie().changerModeIndice(mode_indice);
    }
}
