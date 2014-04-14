package Jeu;		// Contenu dans le package "Jeu"

public class PersoNoob extends Personnage{		// Création de la classe PersoNoob 
	private int avance =0;
	private int oldDirection;	// Déclarartion de la variable qui sauvegarde l'ancienne direction 
	
	
	public PersoNoob(Chateau donjon) {	// Début du constructeur de Perso Noob 
		super(donjon, "personnage_un.jpg", "Peureux", "Stan");	// On renvoi el terrain, le chemin de l'image, son type, et son nom 
		this.enfant = true;			// Bien sur il est un enfant et non un monstre ou un obstacle 
	}
	

	protected void changerDirection() {		// Method for change the direction
		int tempX = x;						// On sauvegarde dans les variables temporaires les positions 
		int tempY = y;
		int lol = 0;
		
		
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
