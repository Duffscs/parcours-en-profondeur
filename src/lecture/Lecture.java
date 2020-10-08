package lecture;

import type.ListeDAdjacence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lecture {
    private LecteurFichier lecteur;
    private List<Integer> liste1;
    private List<Integer> liste2;
    private ListeDAdjacence listeDAdjacence;
    private int nbSommet;

    public Lecture() {
        this.liste1 = new ArrayList<>();
        this.liste2 = new ArrayList<>();
    }

    public void ecriture(String str) throws IOException {
        FileWriter fichier = new FileWriter("./1.in");
        final PrintWriter printWriter = new PrintWriter(fichier);
        printWriter.print(str);
        printWriter.close();
    }

    public ListeDAdjacence lecture(String chemin) {
        try(FileReader file = new FileReader(chemin)) {
            this.lecteur = new LecteurFichier(file);
            String type = lecteur.lectureDeChaine();
            switch (type){
                case "adjacence" : lectureAdjacence(); break;
                case "matrice" : lectureMatrice(); break;
                case "incidence" : lectureIncidence(); break;
                default: throw new IOException("Erreur ligne 1");
            }
            listeDAdjacence();
            lecteur.close();
        }catch (IOException e){
            System.err.print("Erreur lors de la lecture du fichier");
            if(e.getMessage()!= null)
                System.err.println(e.getMessage());
            System.exit(1);
        }
        File file = new File("./1.in");
        file.delete();
        return listeDAdjacence;
    }

    private void lectureAdjacence() {
        nbSommet = lecteur.nextInt();
        liste1=lecteur.lectureDeListe(nbSommet + 1);
        liste2=lecteur.lectureDeListe(liste1.get(nbSommet) + 1);
    }

    private void lectureIncidence(){
        nbSommet = lecteur.nextInt();
        int somme = 0;
        liste1.add(0);
        liste1.add(1);
        for (int i=0;i<nbSommet;i++){
            int nb = lecteur.lectureDEntier();
            somme += nb;
            liste1.add(somme+1);
        }
        liste2 = lecteur.lectureDeListe(somme+1);
        liste2.add(-1);
    }

    private void lectureMatrice() {
        int nbsommet = lecteur.lectureDEntier();
        liste1.add(0);
        liste1.add(1);
        liste2.add(0);
        int nbelem = 1;
        for (int i=1;i<nbsommet+1;i++) {
            for (int o = 1; o < nbsommet + 1; o++) {
                int nb = lecteur.lectureDEntier();
                if (nb!=0 ) {
                    liste2.add(o);
                    nbelem += 1;
                }
            }
            int g = (liste1.size() - 1);
            if (nbelem == (liste1.get(g))) {
                nbelem++;
                liste1.add(nbelem);
                liste2.add(-1);
            }else{ liste1.add(nbelem); }
        }
        liste2.add(-1);
    }

    private void listeDAdjacence() throws IOException {
        if(lecteur.aucunProblemeDeLecture())
            listeDAdjacence = new ListeDAdjacence(liste1,liste2);
        else
            throw new IOException();
    }

}