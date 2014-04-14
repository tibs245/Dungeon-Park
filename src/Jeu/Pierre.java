package Jeu;		// Contenu dans le package "Jeu"

public class Pierre extends ElementMobile{	// Début de la classe Pierre qui est héritière de Element Mobile 
	
	
	public Pierre(Chateau terrain,int x, int y) {		// Début du constructeur 
		super(terrain,"barricade.jpg",x,y,0,"Sol");		// On renvoi le terrain, le chemin de l'image, les positions, et son type 
		this.enfant = false;							// Ce n'est pas un enfant
		this.obstacle = true;							// Mais c'est un obstacle 
	}
	
	@Override
	protected void changerDirection() { // Méthode pour le changement de direction elle doit obligatoirement être présente 
		// TODO Auto-generated method stub
		
	}
}
