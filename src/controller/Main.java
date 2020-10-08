package controller;
import draw.ArbreFrame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lecture.Lecture;
import type.ListeDAdjacence;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        TextArea textArea = new TextArea();
        Button button = new Button("Valider");
        root.setCenter(textArea);
        root.setTop(new ArbreFrame(0));
        BorderPane.setAlignment(button,Pos.CENTER);
        root.setBottom(button);
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Lecture l = new Lecture();
                try {
                    l.ecriture(textArea.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ListeDAdjacence listeDAdjacence = l.lecture("1.in");
                ParcoursEnProfondeur parcours = new ParcoursEnProfondeur(listeDAdjacence);
                parcours.tracerArbre(choixDuSommet(parcours));
                root.setTop(parcours.getArbre());
                primaryStage.show();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static int choixDuSommet(ParcoursEnProfondeur parcours) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.getDialogPane().getButtonTypes().remove(1);
        dialog.setHeaderText("Depuis quel sommet souhaitez vous explorer le graphe ?");
        int sommet = 1;
        try{
            sommet = Integer.parseInt(dialog.showAndWait().get());
        }
        catch (Exception ignored){}
        if(sommet <= 0 || parcours.nombreDeSommet() <= sommet)
            sommet= 1;
        return sommet;
    }
}
