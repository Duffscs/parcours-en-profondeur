package controller;
import draw.ArbreFrame;
import type.ListeDAdjacence;
import type.Position;
import java.util.ArrayList;
import java.util.List;

public class ParcoursEnProfondeur {
    private final ListeDAdjacence l;
    private final List<Integer> sommetExplore;
    private int niveauActuel;
    private final ArbreFrame arbre;
    private final List<Position> posUtilise;
    private int verticalMax;

    public ParcoursEnProfondeur(ListeDAdjacence listeDAdjacence) {
        this.l = listeDAdjacence;
        this.sommetExplore = new ArrayList<>();
        this.niveauActuel= 0;
        this.sommetExplore.add(0);
        this.sommetExplore.add(l.listePointeur().size()-1);
        this.arbre = new ArbreFrame(l.nombreSommet());
        this.verticalMax = 0;
        this.posUtilise = new ArrayList<Position>(){
            @Override
            public boolean contains(Object o) {
                Position p = (Position) o;
                for(Position pos : this){
                    if(p.x() == pos.x() && p.y() == pos.y())
                        return true;
                }
                return false;
            }
        };
    }

    public void tracerArbre(int sommet) {
        while(sommet != -1){
            this.explorerDepuisSommet(sommet, verticalMax +1);
            sommet = premierSommetNonExplore();
        }
        arbre.paint();
    }

    private int premierSommetNonExplore(){
        int retour=-1;
        int i=0;
        while (i<l.nombreSommet() && sommetExplore.contains(i) ){ i++; }
        if(!sommetExplore.contains(i))
            retour= i;
        return retour;
    }

    public void explorerDepuisSommet(int sommet, int vertical){
        vertical = desinerUneNode(sommet, vertical);
        sommetExplore.add(sommet);
        int prochain = prochainSucesseur(sommet);
        while(prochain != -1){
            explorerDepuisSommet(prochain,vertical);
            arbre.ajouterArrete(sommet,prochain);
            vertical++;
            prochain = prochainSucesseur(sommet);
        }
        niveauActuel--;
    }

    private int desinerUneNode(int sommet, int vertical) {
        niveauActuel++;
        Position pos=new Position(50*niveauActuel-25,50*vertical-25);
        vertical = trouverUnePositionLibre(vertical,pos);
        arbre.ajouterNoeud(sommet,pos);
        if(vertical> verticalMax)
            verticalMax = vertical;
        return vertical;
    }

    private int trouverUnePositionLibre(int vertical,Position pos){
        while(posUtilise.contains(pos)){
            pos.setXY(50*niveauActuel-25,50*(vertical)-25);
            vertical++;
        }
        posUtilise.add(pos);
        return vertical;
    }

    public int prochainSucesseur(int sommet){
        int prochain =-1;
        int i = l.listePointeur().get(sommet);

        while (i<l.listePointeur().get(sommet+1) && sommetExplore.contains(l.listeSommet().get(i))){ i++; }

        if(!sommetExplore.contains(l.listeSommet().get(i)))
            prochain = l.listeSommet().get(i);

        return prochain;
    }

    public ArbreFrame getArbre() {
        return arbre;
    }
    public int nombreDeSommet(){
        return this.l.nombreSommet();
    }
}
