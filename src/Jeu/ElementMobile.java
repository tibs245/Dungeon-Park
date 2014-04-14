package Jeu;		// Contenu dans le package "Jeu"


public abstract class ElementMobile extends Case implements AffichageDePlateau.PlateauPiece {  // Début de la classe Element Mobile 
															// Elle est héritée de case car chaque élement doit etre une case 
															// De plus elle à besoins de l'interface Affichage de Plateau 
	
	// Déclaration des variables Publiques 
	
	public final static int NORD = 0;		// Chaque direction correpond à un int entre 0 et 3 Nord pour 0
	public final static int EST = 1;		// Est pour 1
	public final static int SUD = 2;		// Sud pour 2
	public final static int OUEST = 3;		// Et Ouest pour toi, les variables nous servirons à la comparaison des changements de direction 
											// En effet les String posent problemes au niveau de la comparaison 
	
	// Déclaration des variables Privées 
	
	protected int NbrDeTourAPasser;		// Variable qui sert pour savoir le nombre de tour à passer 
	protected int x;					// Variable X qui permet de définir les positions
	protected int y;					// Variable Y qui permet de définir les positions avec x 
	protected int direction;			// Direction qui nous servira à la comparaison avec Nord, Sud, Est, Ouest.
	protected Chateau donjon;			// Déclaration d'un terrain car on agit sur le terrain en meme temps. 
	protected String espece;			// Variable espece qui nous servira à comparer les espèces et faire la méthode pour manger les personnages. 
	
	
	// Début du constructeur pour Element Mobile 
	
	public ElementMobile(final Chateau donjon, final String image, final int x, final int y, final int direction, final String espece) {	
		// Pour définir un element il faudra indiquer le terrain sur lequel il doit se situer 
		// Image correspond au chemin de l'image à afficher à l'écran 
		// X et Y servent pour définir la position voulue 
		// Direction pour les comparaisons et les tours à passer 
		// Espece qui fait parti des attributs pour savoir différencier les perso 
		
		super(image, false, false);		// On renvoi uniquement l'image voulue, et la case n'est pas vide, ni un obstacle 
		this.donjon = donjon;			// On définie le terrain sur lequel il va se situer 
		this.x = x;						// On définie le x comme voulu pour connaitre la position actuelle du perso 
		this.y = y;						// Pareil pour le y 
		this.direction = direction;		// La direction pour savoir ou il va 
		this.espece = espece;			// L'espece pour les comparaisons 
		
		if ( x==0 && y==0 )				// On vérifie qu'il ne soit pas encore déjà placer
		donjon.placerPiece(this);
	}

	
	// Deuxième constructeur pour la classe Element Mobile 
	public ElementMobile(final Chateau donjon, final String image, final String espece) {
		// Celui ci ne prend en parametre que le terrain, le chemin de l'image et l'espece de notre element. 
		
		this(donjon, image, 0, 0, NORD, espece); // Il renverra le donjon, l'image, des positions à 0 et une direction qui va vers le Nord.
	}

	
	protected int calculerNouveauX() {		// Fonction qui va calculer la nouvelle position de l'objet que l'on veut. 
		switch (this.direction) {			// On agit en fonction de sa direction 		
		case EST:							// Si celle ci est orientée vers l'est
			if (this.x < (Chateau.LARGEUR - 1)) {		// SI la position est fais bien partie du tableau on lui rajoute 1 
				return this.x + 1;			// Que si l'orientation est définie vers l'est 
			}
		case OUEST:							// Si l'orientation est ouest 
			if (this.x > 0) {				// Et que x est supérieur à 0 ( Donc dans le tableau ) 
				return this.x - 1;			// Alors on modifie la position 
			}
		default:							// Si l'orientation ne fais pas partie des choix alors on ne change rien 
			return this.x;
		}
	}

	protected int calculerNouveauY() {		// Meme principe que calculer nouveau X 
		switch (this.direction) {			// On analyse la direction à prendre 
		case NORD:							// SI c'est Nord 
			if (this.y > 0) {				// Et que y est supérieur à 0 alors 
				return this.y - 1;			// On décremente y pour bouger les positions
			}
		case SUD:							// Si c'est le sud 
			if (this.y < (Chateau.HAUTEUR - 1)) {		// Et que la position n'est pas déjà au bord de la map 
				return this.y + 1;			// Alors on incrémente la position en Y 
			}
		default:							// Par défaut si l'orientation n'est pas Nord ou Sud 
			return this.y;					// Alors on ne modifie Rien 
		}
	}

	protected abstract void changerDirection();  // Méthode pour changer de direction 

	public String getEspece() {			// Méthode pour récupérer l'espèce présente dans une case 
		return this.espece;				// On retourne l'espece de l'objet
	}

	@Override
	public int getPositionHorizontale() {		// Méthode pour récupérer la position en X d'un objet 
		return this.x;							// On retourne sa position 
	}

	@Override
	public int getPositionVerticale() {			// Méthode pour récupérer la position en Y d'un objet 
		return this.y;							// On retourne sa position 
	}

	public int getX() {						// Idem que getPosition Horizontal sauf que l'on a créer une deuxième méthode 
		return this.x;						// On retourne le X
	}

	public int getY() {						// GetX et getY on été crées car elle sont plus rapide à taper au clavier 
		return this.y;						// On retourne le Y 
	}

	public void seDeplacer() {				// Méthode qui permet à un monstre ou personnage de se déplacer 
		
		this.changerDirection();			// On change sa direction 
		if ( this.NbrDeTourAPasser == 0 ){		// S'il n'a aucun tour à passer 
		final int x = this.calculerNouveauX();		// alors on calcule sa nouvelle position en X 
		final int y = this.calculerNouveauY();		// Puis la nouvelle en Y 

		if (this.donjon.getXY(x, y).estVide()) {	// On vérifie bien que la case est vide 
			this.x = x;									// Si c'est le cas on y colle les attribut de notre objet 
			this.y = y;									// En x et en Y 
		}}
		else {					// Sinon c'est qu'il restait un tour ou plusieurs à passer 		
			this.NbrDeTourAPasser --;		// Donc on décremente le tour à chaque fois qu'il faut 
		}
		
		
	}

	public void setX(final int x) {		// Méthode pour coller les attributs à une position définie
		this.x = x;						// X et égal au x voulu
	}

	public void setY(final int y) {		// Méthode pour coller les attributs à une position définie 			
		this.y = y;						// Y est déshormais égal au Y donné 
	}

}
