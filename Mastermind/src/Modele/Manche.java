package Modele;

import java.util.ArrayList;
import java.util.List;

public class Manche {
	private Integer num_tentative ;
	private Integer nb_pions_combi;
 	private Integer nb_pions_dispo;
	private Combinaison secrete;
	private Combinaison[] tentatives;
	private LigneIndice[] lignes_indices;
	private List<PartieObservateur> observateurs;



	public Manche(Integer nb_tentatives, Integer nb_pions_combi, Integer nb_pions_dispo){
		this.num_tentative = -1;
		this.nb_pions_combi = nb_pions_combi;
		this.nb_pions_dispo = nb_pions_dispo;
		this.secrete = new Combinaison(nb_pions_combi, nb_pions_dispo) ;
			secrete.genererSecrete();
		this.tentatives = new Combinaison[nb_tentatives] ;
		this.lignes_indices = new LigneIndice[nb_tentatives];
		observateurs = new ArrayList<>();

	}

	//Getteur

	public Combinaison obtenirDerniereTentative(){
		if(verifieNumTentatives()==true)
		{
			return tentatives[num_tentative];
		}
		else
		{
			return null;
		}
	}

	protected LigneIndice obtenirDerniereLigneIndice(){

		if(num_tentative<=0){
			return null;
		}
		if(verifierVictoire()){
			return lignes_indices[num_tentative];
		}
		return lignes_indices[num_tentative-1];

	}

	protected Boolean verifierVictoire(){
		Combinaison derniereTentative = obtenirDerniereTentative();
		for(int i = 0; i < nb_pions_combi; i++){
			if(derniereTentative.lirePion(i) != secrete.lirePion(i)){
				return false;
			}
		}
		//notifieLigneIndice();
		//
		// finDeManche();
		return true;
	}



	private Boolean verifieNumTentatives(){
		return (num_tentative<tentatives.length);
	}

	//Lecture de mode_indice à implémenter
	protected Integer calculerScore(String mode_indice){
		Integer score = 0;
		LigneIndice ligne_indice = obtenirDerniereLigneIndice();
		if(ligne_indice!=null) {
			for (Indice indice : ligne_indice.indices) {
				if (indice == Indice.MAL_PLACE)
					score++;
				else if (indice == Indice.BIEN_PLACE)
					score += 3;
			}
		}
		//System.out.println(mode_indice);
		if(mode_indice.equals("Classique")) {
			score += 4;
		}

		return score;
	}


	//Setteur

	
		protected void nouvelleTentative(){

		if(!(num_tentative+1>=tentatives.length) && verifieNumTentatives()) {
			num_tentative++;
			tentatives[num_tentative] = new Combinaison(nb_pions_combi, nb_pions_dispo);
			ajouteObservateurACombinaison(tentatives[num_tentative]);
			lignes_indices[num_tentative] = new LigneIndice(nb_pions_combi);
		}
		else{
			finDeManche();

		}
	}
	public void supprimerCombinaison(){
		notifieSuppressionCombi();
		tentatives[num_tentative] = new Combinaison(nb_pions_combi, nb_pions_dispo);
		ajouteObservateurACombinaison(tentatives[num_tentative]);
		lignes_indices[num_tentative] = new LigneIndice(nb_pions_combi);
	}


	public void finDeManche(){
		notifieAfficheCombiSecrete(secrete);
		notifieFinDeManche();
	}
	public void validerTentative(){
		if(tentatives[num_tentative].combinaisonRemplie()){
			lignes_indices[num_tentative].remplissageLigneIndice(tentatives[num_tentative], secrete);

			if(!verifierVictoire()){
				nouvelleTentative();
				//notifieNouvelleTentative();
				if(num_tentative<tentatives.length) {
					//notifieNouvelleTentative();
				}
			}
			else{
				finDeManche();
			}
			//num_tentative++; // ICI
			notifieNouvelleTentative();
			notifieLigneIndice();
		}
	}

	//Ajoute observeur
	public void ajouteObservateur(PartieObservateur observateur) {

		observateurs.add(observateur);
	}

	public void ajouteObservateurACombinaison(Combinaison combi){

		for(PartieObservateur obser : observateurs){
			combi.ajouteObservateur(obser);
		}
	}

	//Notifie

	private void notifieNouvelleTentative(){
		for(PartieObservateur observateur : observateurs){
			observateur.majTentative();
		}
	}
	private void notifieAfficheCombiSecrete(Combinaison combinaison){
		for(PartieObservateur observateur : observateurs) {
			observateur.majCombiSecrete(combinaison);
		}
	}

	private void notifieSuppressionCombi(){
		for(PartieObservateur observateur : observateurs) {
			observateur.majSuppTentative();
		}
	}
	private void notifieFinDeManche(){
		for(PartieObservateur observateur : observateurs) {
			observateur.majFinDeManche();
		}
	}

	private void notifieLigneIndice(){
		for(PartieObservateur observateur : observateurs){

			observateur.majIndice(obtenirDerniereLigneIndice());
		}
	}

}
