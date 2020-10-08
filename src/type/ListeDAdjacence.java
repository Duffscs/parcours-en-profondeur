package type;

import java.util.List;

public class ListeDAdjacence {
    private final List<Integer> listePointeur;
    private final List<Integer> listeSommet;
    private final int nombreSommet;

    public ListeDAdjacence(List<Integer> listePointeur, List<Integer> listeSommet) {
        this.listePointeur = listePointeur;
        this.listeSommet = listeSommet;
        this.nombreSommet = listePointeur.size()-1;
    }

    public void afficherListe(){
        for (int l1 : listePointeur)
            System.out.print(l1+" ");
        System.out.println();
        for (int l2 : listeSommet)
            System.out.print(l2+" ");
        System.out.println();
    }

    public List<Integer> listePointeur() {
        return this.listePointeur;
    }

    public List<Integer> listeSommet() {
        return this.listeSommet;
    }

    public int nombreSommet() {
        return this.nombreSommet;
    }
}
