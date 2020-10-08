package lecture;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LecteurFichier {

    private final Scanner lecteur;
    private int entier;
    private String chaine;
    private List<Integer> liste;
    private boolean reussite;

    public LecteurFichier(FileReader in) {
        this.lecteur = new Scanner(in);
        this.entier = 0;
        this.chaine = "";
        this.liste =  new ArrayList<>();
        this.reussite = true;
    }

    public int lectureDEntier() {
        if (lecteur.hasNextInt() && this.reussite)
            this.entier = lecteur.nextInt();
        else
            this.reussite = false;
        return this.entier;
    }

    public String lectureDeChaine() {
        if (lecteur.hasNext() && this.reussite)
            this.chaine = lecteur.nextLine();
        else
            this.reussite = false;
        return this.chaine;
    }

    public List<Integer> lectureDeListe(int nombreElementALire) {
        this.liste = new ArrayList<>();
        this.liste.add(0);
        if(this.aucunProblemeDeLecture()){
            for(int i = 0;i<nombreElementALire;i++){
                liste.add(this.lectureDEntier());
            }
        }
        return this.liste;
    }

    public int nextInt() {
        return this.lecteur.nextInt();
    }
    public void close() {
        this.lecteur.close();
    }

    public boolean aucunProblemeDeLecture() {
        return this.reussite;
    }

}
