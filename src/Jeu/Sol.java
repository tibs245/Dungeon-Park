package Jeu;		// Contenu dans le package "Jeu"

public class Sol extends Case { // Début de la classe Sol héritière de la classe Case 
	
	public Sol() {		// Début du constructeur 
		super("sol.jpg",true,false); // On renvoi le chemin de l'image et les attributs vide a oui et obstacle à non. 
		this.enfant = false; // Ce n'est pas un enfant 
	}
}
