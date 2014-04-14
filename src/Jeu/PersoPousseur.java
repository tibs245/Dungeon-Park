package Jeu;			// Contenu dans le package "Jeu"

public class PersoPousseur extends Personnage{		// Début de la classe PersoPousseur qui est héritière de la classe Personnage 
	
	
	private int oldDirection;		// Déclaration de oldDirectoion pour avoir une trace de l'ancienne direction 
	private int avance = 0;
			
	public PersoPousseur(Chateau donjon) {		// Début du constructeur 
		super(donjon, "personnage_trois.jpg", "Pousseur", "Kyle");		// on Renvoi le terrain sur lequel il doit être, le chemin de l'image, le type, et le nom 
		this.enfant = true;											// Bien sur, c'est aussi un enfant 
		// TODO Auto-generated constructor stub
	}
	
	
	public void bougerPierre(){				// Méthode pour que le personnage puisse bouger les pierres autours de lui 
		int x = this.getX();					// On récupère les positions du perso
		int y = this.getY();
		if ( donjon.getXY(x+1, y).getObstacle() == true ){		// On vérifie autour s'il y a des obstacles 
			if ( donjon.getXY(x+2, y).estVide() == true ){		// S'il y en a 
				donjon.setXY(x+2, y, new Pierre(donjon, x, y));		// Alors on colle l'image de notre obstacle qui bouge à sa nouvelle position
				donjon.setXY(x+1, y, new Sol());					// Et du sol à l'ancienne position
			}
		}
		else if ( donjon.getXY(x-1, y).getObstacle() == true ){		// Pareil pour toutes les case autours du perso 
			if ( donjon.getXY(x-2, y).estVide() == true ){
				donjon.setXY(x-2, y, new Pierre(donjon, x, y));
				donjon.setXY(x-1, y, new Sol());
			}
		}
		else if ( donjon.getXY(x, y+1).getObstacle() == true ){
			if ( donjon.getXY(x, y+2).estVide() == true ){
				donjon.setXY(x, y+2, new Pierre(donjon, x, y));
				donjon.setXY(x, y+1, new Sol());
			}
		}
		else if ( donjon.getXY(x, y-1).getObstacle() == true ){
			if ( donjon.getXY(x, y-2).estVide() == true ){
				donjon.setXY(x, y-2, new Pierre(donjon, x, y));
				donjon.setXY(x, y-1, new Sol());
			}
		}
		
	}

	protected void changerDirection() {		// Method for change the direction
		int tempX = x;						// On sauvegarde dans les variables temporaires les positions 
		int tempY = y;
		int lol = 0;
		bougerPierre();
		
		if ( avance <= 0){
			this.avance = (int) (Math.random()*5)+1;		// On tire un nombre au hasard pour choisir de combien de case le personnage avancera 	
			lol = (int) (Math.random()*4);	// On tire un nombre au hasard pour avoir la direction 
			
			switch (lol){
			case 0 : direction = NORD;break;		// Suivant le nombre on envoi la direction
			case 1 : direction = SUD;break;
			case 2 : direction = OUEST;break;
			case 3 : direction = EST;break;
			}
			if ( oldDirection-direction == 2 || direction-oldDirection ==2 ){   // Si changement de direction de 180 degré alors on passe deux tours 
				this.NbrDeTourAPasser+=2;
			}
			else if ( oldDirection-direction == 1 || direction-oldDirection ==1 ){		// Si changement de direction de 90 degré passe un tour 
				this.NbrDeTourAPasser++;		
			}
			this.oldDirection=direction;
		}
		else{avance--;}		
		
		
		switch (direction) {		// Calculate the possible future position
			case NORD  : tempX--;	// Suivant la direction on modifie les attributs de la position du perso 
					break;
			case EST   : tempY++;
					break;
			case SUD   : tempX++;
					break;
			case OUEST : tempY--;
					break;
		
		}		
	}
	
}
