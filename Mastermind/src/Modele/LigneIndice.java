package Modele;

import java.util.ArrayList;
import java.util.List;

public class LigneIndice {
	Indice[] indices;

	public LigneIndice(Integer nb_pions_combi) {
		this.indices = new Indice[nb_pions_combi];
	}

	//Getteur
	private Integer compteNombrePionListe(List<Pion> pion_ajoute, Pion pion){
		int compteur=0;
		for (Pion pion_liste : pion_ajoute){
			if(pion_liste == pion )
				compteur++;
		}
		return compteur;
	}

	public Indice avoirIndiceIndex(Integer index){
		return indices[index];
	}

	public Integer avoirNombreTypeIndice( Indice type_indice){
		Integer compteur=0;
		for(Indice indice : indices){
			if(type_indice == indice){
				compteur ++;
			}
		}
		return compteur;
	}
	public Integer tailleLigneIndice(){
		return indices.length;
	}

	//SETTEUR

	protected void remplissageLigneIndice(Combinaison tentative, Combinaison secrete){

		List<Integer> index_deja_prit = new ArrayList<>();
		List<Pion> pion_ajoute = new ArrayList<>();
		for(Integer i =0; i<indices.length; i++){
			if(tentative.lirePion(i) == secrete.lirePion(i)) {
				indices[i] = Indice.BIEN_PLACE;
				index_deja_prit.add(i);
				pion_ajoute.add(tentative.lirePion(i));
			}
		}
		for(int i =0 ; i< indices.length ;i++) {
			if (!index_deja_prit.contains(i)) {
				//System.out.println("Premiere cond : "+compteNombrePionListe(pion_ajoute, tentative.lirePion(i))+ " < "+ secrete.avoirNombreCouleur(tentative.lirePion(i)) + " = "+ (compteNombrePionListe(pion_ajoute, tentative.lirePion(i)) < secrete.avoirNombreCouleur(tentative.lirePion(i))));
				//System.out.println("Deuxieme : "+ secrete.pionEstPresent(tentative.lirePion(i)));
				//System.out.println((compteNombrePionListe(pion_ajoute, tentative.lirePion(i)) < secrete.avoirNombreCouleur(tentative.lirePion(i)) && secrete.pionEstPresent(secrete.lirePion(i))));
				if (compteNombrePionListe(pion_ajoute, tentative.lirePion(i)) < secrete.avoirNombreCouleur(tentative.lirePion(i)) && secrete.pionEstPresent(tentative.lirePion(i))) {
					index_deja_prit.add(i);
					pion_ajoute.add(tentative.lirePion(i));
					indices[i] = Indice.MAL_PLACE;
				} else {
					indices[i] = Indice.ABSENT;
				}
			}
		}

	}


	//AFFICHAGE

}
