package Modele;

import java.util.ArrayList;
import java.util.List;

public class Partie {
	private Manche manche;
	private String nom_utilisateur;
	private Integer nb_manches;
	private Integer num_manche ;
	private Integer nb_pions_dispo;
	private Integer[] scores;
	private Boolean[] victoires;
	private Integer nb_tentatives;
	private Integer nb_pions_combi;
	private List<PartieObservateur> observateurs;
	private String mode_indice;



	//####################################
	//        CONSTRUCTEUR             //
	//####################################
	public Partie(String nom_utilisateur, Integer nb_manches, Integer nb_pions_dispo, Integer nb_tentatives, Integer nb_pions_combi ){
		this.manche = null;
		this.nom_utilisateur = nom_utilisateur;
		this.nb_manches = nb_manches;
		this.num_manche = -1;
		this.nb_pions_dispo = nb_pions_dispo;
		this.scores = new Integer[nb_manches];
		this.victoires = new Boolean[nb_manches];
		this.nb_tentatives = nb_tentatives;
		this.nb_pions_combi = nb_pions_combi ;
		observateurs = new ArrayList<>();
		this.mode_indice="Classique";
	}

	//getteur

	public Integer[] obtenirScores(){
		return this.scores;
	}

	public String obtenirNom(){
		return this.nom_utilisateur;
	}

	public Manche obtenirManche(){
		return this.manche;
	}

	public Boolean[] obtenirVictoires() {
		return this.victoires;
	}


	public Integer obtenirNbTentatives(){
		return nb_tentatives;
	}
	public Integer obtenirNbPionsCombi(){
		return nb_pions_combi;
	}
	public Integer obtenirNbPionsDispo(){
		return nb_pions_dispo;
	}
	public Integer obtenirNbManches(){
		return nb_manches;
	}



	//setteur

	public void nouvelleManche(){

		if(num_manche>=nb_manches-1)
		{
			scores[num_manche]= manche.calculerScore(mode_indice);
			victoires[num_manche] = manche.verifierVictoire();

			notifieFinPartie();
		}
		else{
			if (num_manche >= 0) {
				scores[num_manche]= manche.calculerScore(mode_indice);
				victoires[num_manche] = manche.verifierVictoire();
				//scores[num_manche] = manche.calculerScore();

			}
			this.manche = new Manche(this.nb_tentatives, this.nb_pions_combi, this.nb_pions_dispo);

			ajouteObservateurAManche(manche);
			manche.nouvelleTentative();
			notifieNouvelleManche();
			//manche.creeCombiSecrete();
			num_manche++;
		}

	}

	public void changerModeIndice(String mode_indice){
		this.mode_indice = mode_indice;
	}



	//observeur

	public void ajouteObservateur(PartieObservateur observateur) {

		observateurs.add(observateur);
	}
	public void ajouteObservateurAManche(Manche manche){

		for(PartieObservateur obser : observateurs){

			manche.ajouteObservateur(obser);
		}
	}

	//notifie



	private void notifieNouvelleManche(){
		for(PartieObservateur obser : observateurs){
			obser.majNouvelleManche();
		}
	}
	private void notifieFinPartie(){
		for(PartieObservateur obser : observateurs){
			obser.majFinDePartie(nom_utilisateur,victoires,scores);
		}
	}

}
