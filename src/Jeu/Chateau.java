package Jeu;  					// Contenu dans "Jeu"

import java.util.ArrayList;   // Bibliothèques importées. 
import java.util.Random;

import AffichageDePlateau.Plateau;

public class Chateau{			// Début de la classe Chateau 
	
	// La classe Chateau est la classe qui va nous permettre de créer le terrain de jeu 
	
	// Variables Publiques 
	
	public static int LARGEUR = 20;		// Variable public qui contient le nombre de case sur l'axe des abscissses ( X ) 
	public static int HAUTEUR = 20;		// Variable public qui contient le nombre de case sur l'axe des ordonnées ( Y )

	public static int NB_Murs = 4;		// Nombre de mur qui seront présent sur le Terrain 
	public static int NB_Pierres = 6 ; 	// Nombre de Pierre qui seront présentes sur le Terrain, une pirre peut être déplacée un mur non 
	public static int Tours = 0;		// Variable qui s'incrémente à chaque tour, pour donner à l'utilisateur le nombre de tours passé depuis le début 
	
	
	// Variables privées 
	
	
	private final Case cases[][];		// Déclaration d'un tableau à 2 dimensions de case, c'est ce qu'on affichera dans notre fenetre 
										// Chaque Case contient une image est des attributs 
	
	private final ArrayList<ElementMobile> mobiles;		// Liste qui va contenir tous nos elements mobiles 

	private Plateau plateau;			// Le plateau correspond à la fenetre de jeu, il viens du package Affichage de Plateau 
	
	
	
	
	
	
	
	public Chateau() { // Début du constructeur de Chateau 
		
		this.cases = new Case[LARGEUR][HAUTEUR]; // Notre Tableau est maintenant défini selon la largeur et la hauteur voulue ( Nbr de cases ) 
		
		this.mobiles = new ArrayList<>();  // La liste est initialisée pour recevoir les élements mobiles 

		Case uneCase; // Petite déclartion de variable qui va nous servir pour remplir le tableau au fur et à mesure 
		
		
		for (int y = 0; y < HAUTEUR; y++) {			// Les deux boucles For nous permettent d'englober la totalité du tableau 
			for (int x = 0; x < LARGEUR; x++) {
				
				if ((x == 0) || (x == (LARGEUR - 1)) || (y == 0) || (y == (HAUTEUR - 1))) {  // Boucle If pour créer la bordure 
					uneCase =  new Bordure();					// Appel du constructeur pour les bordures de la map
				}
				else {											// Quand c'est pas une Bordure on met du Sol 
					uneCase = this.construireSol();				// Appel du constructeur pour mettre le sol sur la map 
				}
				this.setXY(x, y, uneCase);						// Grace à la méthode setXY on colle dans notre tableau nos images une par une avec la position
			}
		}
		
		
		for ( int v = 0 ; v < NB_Murs ; v++){					// Boucle If pour placer le nombre de murs que l'utilisateur souhaite ou la valeur par défaut 
			
			int x = (int) (Math.random()*LARGEUR - 1);		// Les positions en X et Y sont prises aléatoirement 
			int y = (int) (Math.random()*HAUTEUR - 1);		// Grâce à la fonction Random intégrée à Java 
			
			if ( this.cases[x][y].estVide() == true ){			// On vérifie que la case visée est vide pour ne rien remplacer 
				this.setXY(x,y,new Bordure());}				// Si c'est le cas on met notre mur 
			else{v--;}										// Sinon on décrémente la boucle et on recommence 
		}
		
		
		for ( int v = 0 ; v < NB_Pierres ; v++){				// Boucle If pour placer le nombre de Pierre 
			
			int x = (int) (Math.random()*LARGEUR - 1);		// Comme la boucle du dessus on prend X et Y en aléatoire 
			int y = (int) (Math.random()*HAUTEUR - 1);
			
			if ( this.cases[x][y].estVide() == true ){		// On vérifie toujours que la case est vide 
				this.setXY(x,y,construirePierre(null, x, y));}		// On construit si c'est bon 
			else{v--;}											// Sinon on décrémente la boucle 
		}
		this.plateau = new Plateau(LARGEUR, HAUTEUR, this.cases);	// On charge le plateau avec les tailles définie et y envoi nos images à travers le tableau Case
		
	} // Fin du constructeur 
	

	
	
	
	public void placerPiece(ElementMobile perso){		// Méthode qui permet de placer les pièces sur notre plateau de jeu
		
		int x = 0 , y = 0;								// On définie deux variables x et y pour donner les positions voulues à la pièce 
		
		while ( x == 0 )		// On vérifie que x n'est pas égal à zéro pour ne pas aller sur la bordure 
		{
		x = (int) (Math.random()*LARGEUR-1);}		// et on tire un nombre au hasard entre 1 et la taille du terrain (-1 pour la bordure ) 
		
		while ( y == 0 )		// On vérifie que y n'est pas égal à zéro pour ne pas aller sur la bordure 
		{
		y = (int) (Math.random()*HAUTEUR-1);} 		// On tire notre nombre au hasard entre 1et la taille du terrain (- 1 pour la bordure ) 
		
		
		
		if ( this.cases[x][y].vide == true ){		// On vérifie bien que notre case visée est une case vide pour ne rien supprimer 
			this.setXY(x,y, perso);					// Grace à la fonction setXY on colle notre image dans le tableau 
			perso.setX(x);							// Et le personnage prend x et y comme nouveau attribut pour sa position 
			perso.setY(y);
		}
		else placerPiece(perso);					// Si la case n'est pas vide alors on utilise la méthode récursive et on recommence 
	}
	
	
	
	
	private Case construirePierre(Chateau terrain,int x, int y) {		// Constructeur qui nous permet de placer les pierres sur le terrain 
		return new Pierre(terrain,x,y);									// On créer l'objet Pierre en le collant sur le bon terrain et avec ses positions
	}
	
	
	
	private Case construireSol() {					// Idem que construire Pierre 
		return new Sol();							// Sauf que l'on ne précise pas les positions que le sol doit avoir ici 
	}

	public void setXY(final int x, final int y, final Case c) {  // Méthode très importantes car c'est elle qui nous permet de coller une image dans le tableau
		this.cases[x][y] = c;					// On met l'image directement dans notre tableau Cases qui est défini comme attribut de Chateau 
	}

	public Case getXY(final int x, final int y) {			// La méthode qui nous permet de savoir ce qui se situe dans une case 
		return this.cases[x][y];						// On envoi les positions de la case à analyser, puis la méthode nous renvois ce qu'elle contient 
	}
	
	
	public void rafraichir(){			// Méthode permettant d'actualiser le plateau 
		this.plateau.rafraichir();		// Grâce à cette Méthode les images reprennent leurs bonnes positions 
		Chateau.Tours++;				// De plus à chaque tour on rafraichis donc on incrémente la variable Tour
	}
	
}			// Fin de la classe Chateau
