package Jeu; // Contenue dans le package Jeu


 // Classe qui s'appelle arbre mais elle sert uniquement pour instancier l'objet "Pierre"


abstract class Arbre extends Case { // Déclaration en abstraite car on ne doit pas l'instancier 
	
	public Arbre(final String image) { // Définie uniquement avec le chemin de l'image. 
		
		super(image, false, true);  // On renvoi le chemin de l'image, si la case est deplacable ( false = non ), et si la case est un obstacle. 
	}
} // Fin de la classe.
