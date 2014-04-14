package Jeu;			// Contenu dans le package "Jeu"

public class PersoIntelligent extends Personnage {		// Début de la classe du Personnage Intelligent qui hérite de la classe Personnage 
	
	// Déclaration de la variable Privée 
	
	private int oriental; // Oriental qui va contenir les orientation à rtenir 
	private int oldDirection;
	private int avance = 0;
	
	
	public PersoIntelligent(Chateau donjon) { // Début du constructeur de la classe 
		super(donjon, "personnage_deux.jpg", "Discret","Kenny");		// On renvoi le terrain sur lequel il doit etre, le chemin de l'image, sa classe, et son nom 
		this.enfant = true;				// Bien sur c'est un enfant et pas un monstre donc on le dit 
		// TODO Auto-generated constructor stub
	}
	
	private Boolean ogrePresent(){				// Méthode qui va permettre à l'enfant de détecter l'ogre en fonction de sa position 
		int tempX = getX();						// On récupère les positions du personnage dans un e variable temporaire 
		int tempY = getY();						// En X et en Y 
		
		switch (this.oriental){						// Suivant la dernière position de l'enfant pour calculer son champ de vision
		
		case NORD: 									// Si c'est Nord alors on vérifie les cases du champ de vision 
			
			// Le tout en utilisant les positions temporaires
		
			if ( donjon.getXY(tempX, tempY-1).verifierMonstre() == true ){return true;}
			
			// On vérifie bien que l'on ne met pas le champ de vision de l'enfant dans le vide 
			if ( y > 1 ){
			if ( donjon.getXY(tempX, tempY-2).verifierMonstre() == true ){return true;}}  // Si un monstre est présent on renvoi True comme valeur 
			
			if ( donjon.getXY(tempX+1, tempY-1).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX+1, tempY).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX+1, tempY+1).verifierMonstre() == true ){return true;}
			
			if ( x < Chateau.LARGEUR-2 ){
			if ( donjon.getXY(tempX+2, tempY).verifierMonstre() == true ){return true;}}
			
			if ( donjon.getXY(tempX-1, tempY-1).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX-1, tempY).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX-1, tempY+1).verifierMonstre() == true ){return true;}
			
			if ( x > 1 ){
			if ( donjon.getXY(tempX-2, tempY).verifierMonstre() == true ){return true;}}  
			break;
			
			
		case SUD:
			if ( donjon.getXY(tempX, tempY+1).verifierMonstre() == true ){return true;}
			
			if ( y < Chateau.HAUTEUR-2 ){
			if ( donjon.getXY(tempX, tempY+2).verifierMonstre() == true ){return true;}}
			
			if ( donjon.getXY(tempX+1, tempY-1).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX+1, tempY).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX+1, tempY+1).verifierMonstre() == true ){return true;}
			
			if ( x < Chateau.LARGEUR-2 ){
			if ( donjon.getXY(tempX+2, tempY).verifierMonstre() == true ){return true;}}
			
			if ( donjon.getXY(tempX-1, tempY-1).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX-1, tempY).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX-1, tempY+1).verifierMonstre() == true ){return true;}
			
			if ( x > 1 ){
			if ( donjon.getXY(tempX-2, tempY).verifierMonstre() == true ){return true;}}
			break;
			
			
		case OUEST:
			
			if ( x < Chateau.LARGEUR-2 ){
			if ( donjon.getXY(tempX+2, tempY).verifierMonstre() == true ){return true;}}
			if ( donjon.getXY(tempX+1, tempY).verifierMonstre() == true ){return true;}
			
			if ( donjon.getXY(tempX-1, tempY-1).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX, tempY-1).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX+1, tempY-1).verifierMonstre() == true ){return true;}
			
			if ( y > 1 ){
			if ( donjon.getXY(tempX, tempY-2).verifierMonstre() == true ){return true;}}
			
			if ( donjon.getXY(tempX-1, tempY+1).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX, tempY+1).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX+1, tempY+1).verifierMonstre() == true ){return true;}
			
			if ( y < Chateau.HAUTEUR-2 ){
			if ( donjon.getXY(tempX, tempY+2).verifierMonstre() == true ){return true;}}
			break;
			
			
		case EST:
			
			if ( x > 1 ){
			if ( donjon.getXY(tempX-2, tempY).verifierMonstre() == true ){return true;}}
			
			if ( donjon.getXY(tempX-1, tempY).verifierMonstre() == true ){return true;}
			
			if ( donjon.getXY(tempX-1, tempY-1).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX, tempY-1).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX+1, tempY-1).verifierMonstre() == true ){return true;}
			
			if ( y > 1 ){
			if ( donjon.getXY(tempX, tempY-2).verifierMonstre() == true ){return true;}}
			
			
			if ( donjon.getXY(tempX-1, tempY+1).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX, tempY+1).verifierMonstre() == true ){return true;}
			if ( donjon.getXY(tempX+1, tempY+1).verifierMonstre() == true ){return true;}
			
			
			if ( y < Chateau.HAUTEUR-2 ){
			if ( donjon.getXY(tempX, tempY+2).verifierMonstre() == true ){return true;}}
			break;
		}
		
		
		return false;  // Si le monstre n'est pas présent on renvoi False comme valeur 
	}
	
	private Boolean seCacher(){					// Méthode qui va permettre à l'enfant de se cacher 
		int cacher = 0;						// La variable cacher sert à compter le nombre d'obstacles autour de nous 
		
		if ( donjon.getXY(getX()+1, getY()).estVide() == false ){ cacher++; }			// Suivant les positions on analyse ce qu'il y a autour 
		if ( donjon.getXY(getX()-1, getY()).estVide() == false ){ cacher++; }			// Si il y a un obstacle alors on incrémente cacher 
		if ( donjon.getXY(getX(), getY()+1).estVide() == false ){ cacher++; }			// On utilise la méthode estVide() pour savoir si la case est vide ou non 
		if ( donjon.getXY(getX(), getY()-1).estVide() == false ){ cacher++; }
		if ( cacher >= 2 ){ cacher = 0; return true; }							// Si il a plus de 2 obstacles alors on dit que l'enfant est cacher et on renvoi true en valeur 
		else {return false;}														// Sinon on renvoi false 
	}
	@Override
	protected void changerDirection() {		// Method for change the direction
		int tempX = x;						// Nos variables temporaires pour les positions et vérifications
		int tempY = y;						// En x et en Y 
		if ( avance <= 0){
			this.avance = (int) (Math.random()*5)+1;		// On tire un nombre au hasard pour choisir de combien de case le personnage avancera 
			
			if ( seCacher() == false  || ogrePresent() == true) {		// Si l'enfant n'est pas cacher ou que  l'ogre est présent 
				this.oriental = (int) (Math.random()*4);	}				// ALors od tire une direction au hasar 
			
			switch (this.oriental){
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
	}
}
