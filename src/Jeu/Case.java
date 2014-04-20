package Jeu;			// Contenu dans le package "Jeu" 


import java.awt.Image;  		// Toutes les bibliothèques importées. 
import java.io.IOException;

import javax.imageio.ImageIO;




public class Case implements AffichageDePlateau.PlateauCase { 		// Début de la classe 
	
	
	protected Image image = null;		// La variable qui contient les images à afficher 
	protected boolean vide;				// Variable pour savoir si une case est vide ou pas 
	protected boolean obstacle = false;	// Variable pour savoir si la case est un obstacle ou pas 
	protected boolean enfant = false;  // Variable pour savoir si la case est un enfant ou pas 
	protected boolean monstre = false; // Idem pour savoir si c'est un monstre ou pas 
	protected String imagePath;			// Attribut pour connaitre le chemin de l'image

	
	
	
	public Case(final String image, final Boolean vide, final Boolean obstacle) {   // Constructeur de la classe Case 
		
																					// On envoi le chemin de l'image, si la case est vide ou pas et idem pour l'obstacle
		this.vide = vide;															// On donne à la variable la valeur voulue 
		
		try {																		// On essaye de lire l'image dans nos fichier avec le bloc Try/Catch
			this.image = ImageIO.read(this.getClass().getResource("/Jeu/image/" + image));					// Les images sont contenues dans le dossier image a la racine du projet 
			this.imagePath = image;													// On retient le chemin de l'image 
		} catch (final IOException e) {												// Si cela ne marche pas on renvoi une erreur 
			e.printStackTrace();
		}
	}
	
	
	public Boolean verifierEnfant(){		// Méthode qui permet de savoir si dans la case on a un enfant ( True oui , False non ) 
		return this.enfant;			// On lis dans notre variable enfant, on retourne le résultat suivant la variable 
	}
	
	
	
	public Boolean verifierMonstre(){		// Méthode qui permet de verifier s'il y a un monstre dans la case 
		return this.monstre;			// On lis dans notre variable monstre, on retourne le résultat comme dans la méthode d'avant. 
	}
	
	public Boolean estVide() {				// On veux savoir si la case est vide ou non 
		return this.vide;					// Pour savoir on renvoi notre variable vide 
	}

	public Boolean getObstacle() {   // Idem pour les obstacles 
		return this.obstacle;        // On retourne l'obstacle 
	}
	@Override
	public Image getImage() {		// Pour avoir l'image 
		return this.image;			// On renvoi l'image 
	}
	
	
	public String getPathImage() {			// Fontion pour voir le chemin de l'image	#####
		return imagePath;
	}
}
