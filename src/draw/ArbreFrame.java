package draw;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import type.Position;
import java.util.*;

public class ArbreFrame extends Canvas {

	private final int width;
	private final int height;

    private final ArrayList<Noeud> noeuds;
	private final ArrayList<Arete> aretes;

    public ArbreFrame(int nombreDeNode) {
		noeuds = new ArrayList<>();
		aretes = new ArrayList<>();
		this.setHeight(400);
		this.setWidth(800);
		this.setVisible(true);
		this.width = 30;
		this.height = 30;
		for(int i=0;i<nombreDeNode;i++){
			noeuds.add(new Noeud(""+i,new Position(0,0)));
		}
    }

	public void ajouterNoeud(int index, Position pos) {
		noeuds.get(index).setPos(pos);
    }
    public void ajouterArrete(int i, int j) {
		aretes.add(new Arete(i,j));
    }

    public void paint() {
		GraphicsContext gc = this.getGraphicsContext2D();
		for (Arete arete : aretes) {
			gc.strokeLine(posXNoeud(arete.i()), posYNoeud(arete.i()), posXNoeud(arete.j()), posYNoeud(arete.j()));
		}

		for (Noeud noeud : noeuds) {
			if(noeud.name.equals("0"))
				continue;
			gc.setFill(Color.LIGHTGREY);
			gc.fillOval(noeud.x()-width/2, noeud.y()-height/2, width, height);
			gc.setFill(Color.BLACK);
			gc.strokeOval(noeud.x()-width/2, noeud.y()-height/2,width, height);
			gc.strokeText(noeud.name, noeud.x()-4*noeud.name.length(), noeud.y()+5);
		}
    }

	private int posYNoeud(int i) {
		return noeuds.get(i).y();
	}

	private int posXNoeud(int i) {
		return noeuds.get(i).x();
	}
}