package Jeu;			// Contenu dans le package "Jeu"

import Configuration.Son;		// Import des bibliothèques à utiliser 


public class Monstre extends Personnage {		// Début de la classe Personnage 

	
	// Déclaration  des variables privées de la classe 
	
		private short nbrObstacleRencontrer = 0;		// Variable pour récupérer le nombre d'obstacle rencontré par le monstre 
		private int oldDirectionn = 1;				// Variable qui sert à comparer l'ancienne orientation pour calculer le nombre de tour à passer si besoins 
		
	
	public Monstre(Chateau donjon, String name) {			// Début du constructeur de la classe Monstre 
		super(donjon, "monstre.jpg", "Monstre" , name);		// On renvoi le terrain ur lequel il doit être, le chemin de l'image, son espèce, et son nom défini par l'utilisateur 
		this.monstre = true;							// On dit que c'est bien un monstre 
		this.vie = 100;									// Sa vie est définie à de base  
	}

		// Déclaration de Variable public ou protégée 
	
	
		public static short NOMBRE_ENFANT_MANGER = 0;		// Le nombre d'enfant que le monstre à manger 
		protected short nombreEnfantManger = 0;				// Meme que celle d'avant mais en protégée 
		
		
		
	public void mange() {								// Méthode qui permet de manger les enfants s'il y en as dans les parages 
		Thread playWave=new Son("musique/mort.wav");			// A chaque enfant mangé on joue un son qui préviens l'utilisateur 
        playWave.start();								// Start permet de le jour au dessus on le déclare et l'initialise 
		nombreEnfantManger++;							// Le nombre d'nefant mangé augmente de un 
		this.vie += 20;									// La vie du monstre augmente de 20 à chaqeu enfant mangé 
		} 

	public short getNbrEnfantManger() {					// Méthode qui nous retourne le nombre d'enfant mangé par le monstre 
		return nombreEnfantManger;						// On retourne notre variable 
	}

	protected void changerDirection() {		// Method for change the direction
		int tempX = x;						// On met les attributs x et y dans des variables temporaires pour les vérifications 
		int tempY = y; 
		
		switch (direction) {		// Calculate the possible future position
			case NORD  : tempY--;		// Suivant la position on va modifier les attributs X et Y 
					break;
			case EST   : tempX++;		// Bien sur on ne modifie que les variables temporaires en premier lieu 
					break;
			case SUD   : tempY++;
					break;
			case OUEST : tempX--;
					break;				// Break pour fermer le switch sinon tout s'éxécute à la suite 
		}

		if ( donjon.getXY(tempX, tempY).estVide() == false ) {		// We verify if the case is nothing
			
			if ( nbrObstacleRencontrer >= 3 ) {						// We calculate the number of obstacle				
				nbrObstacleRencontrer=0;
				
				if ( direction > 2 ){								// Si c'est le troisiÃ¨me ostacle on fait demie tour
					direction-=2;									// On change la direction 			
					this.NbrDeTourAPasser=2;							// Et on incrémente le nombre de tour à passer lors d'un demi tour 
					}
				else{
					this.NbrDeTourAPasser=2;					// Idem lorsque l'on fais demi tour de l'autre coté 
					direction+=2;
					}				
				
			} else {												// Sinon on compte le nombre d'ostacle
			nbrObstacleRencontrer++;								// On incrémente le nombre d'obstacle à chaque fois 
			direction++;											// On modifie la direction 
			this.NbrDeTourAPasser=1;								// A chaque changement de direction de 90 degré on à un tour à passer 
			}
		}
			if ( direction > OUEST ){								// Si la direction est supÃ©rieur Ã  OUEST on change Ã  NORD
				direction = NORD;									// La direction prend sa nouvelle valeur 
				this.NbrDeTourAPasser=2;								// Et on augmente le nombre de tour
			}
			else if ( direction < NORD ){							/// Sinon on vÃ©irfie aussi qu'il n'est infÃ©rieur Ã  nord
				direction = OUEST;									// On modifie la direction 
				this.NbrDeTourAPasser=2;							// Et on augmente le nombre de tour à passer 
			}
					
	}
	
	public void manger() {							// Méthode public qui permet de manger un enfant en utilisant l'ancienne méthode 
		
		int tempX, tempY, oldDirection;				// On utilise toujours des positions temporaires pour les vérifications et autres 
		
		oldDirection = direction;					// Même la direction est sauvegardée 
		
		
		for (int i = -1; i<2; i++)	{	// Explore all the possibilities
			
			direction = oldDirection + i ;	// We verify the positions
		
		tempX = calculerNouveauX();			// We calculate the position on X
		tempY = calculerNouveauY();			// Et aussi en Y 
		
			if ( donjon.getXY(tempX, tempY).verifierEnfant() == true){	// We look if the case hasn't heroes
				donjon.setXY(x, y, new Sol());							// Si c'est la cas alors on met du sol à l'ancienne place 
				donjon.setXY(tempX, tempY,this);						// On se colle sur la case du gosse
				this.NbrDeTourAPasser = 3;								// A chaque repas on saute trois tours 
				this.mange();											// Pour manger on utilise notre méthode du dessus 
				x=tempX;												// Les positions sont actualisées 
				y=tempY;												// En X et en Y 
				break;													// If the case has a heroes we stop the monster
			}
			
			direction=oldDirection; 			// La direction reprend son ancienne valeur 
		}
	}

			public void seBouger() {		// Méthode qui permet de bouger le monstre 
				this.vie--;						// A chaque fois qu'il bouge il perd de la vie 
				super.seBouger();				// On utilise la méthode se bouger de la classe Element Mobile 
			}
	
	}



