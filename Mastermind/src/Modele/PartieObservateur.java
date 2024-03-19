package Modele;

public interface PartieObservateur {
    public void majTentative();
    public void majSuppTentative();

    public void majCombinaison(Integer index, Pion pion);

    public void majCombiSecrete(Combinaison combinaison);

    public void majFinDeManche();
    public void majNouvelleManche();
    public void majFinDePartie(String nom_joueur, Boolean[] victoire, Integer[] scores);
    public void majIndice(LigneIndice ligne_indice);
}
