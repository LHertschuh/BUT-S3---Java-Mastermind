package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Combinaison {
	private Pion[] pions;
	private Integer nb_pions_dispo;
	private List<PartieObservateur> observateurs;

	protected Combinaison(Integer nb_pions_combi, Integer nb_pions_dispo){
		this.nb_pions_dispo = nb_pions_dispo;
		this.pions = new Pion[nb_pions_combi];
		observateurs = new ArrayList<>();
	}


	// ####### GETTEURS
	protected Integer avoirNombreCouleur(Pion pion){
		int compteur=0;
		for(int i =0; i< pions.length; i++){
			if(this.pions[i] == pion){
				compteur ++;
			}
		}
		return compteur;
	}

	protected Boolean pionEstPresent(Pion check){
		for (Pion pion : pions){
			if(pion == check)
				return true;
		}
		return false;
	}
	public Pion lirePion(Integer index){
		//System.out.println("longueure "+ this.pions.length);
		//System.out.println("index "+index);
		return this.pions[index];
	}


	protected Boolean combinaisonRemplie(){
		for (Pion pion : pions){
			if(pion == null)
				return false;
		}
		return true;
	}


	// SETTEURS


	protected void genererSecrete(){
		Random ALEA = new Random();
		Pion[] valeurs = Pion.values(); 
		int taille = pions.length;
		for(int i = 0; i < taille; i++){
			this.pions[i] = valeurs[ALEA.nextInt(this.nb_pions_dispo)];
		}
	}

	public void placerPion(Integer index, Pion pion){
		this.pions[index] = pion;
		notifiePlacerPion(index,pion);
	}


	//AFFICHAGE


	// Observeur

	public void ajouteObservateur(PartieObservateur observateur) {
		observateurs.add(observateur);

	}

	//#### Notifie

	private void notifiePlacerPion(Integer index, Pion pion){
		for(PartieObservateur observateur : observateurs){
			observateur.majCombinaison(index, pion);
		}
	}


}
