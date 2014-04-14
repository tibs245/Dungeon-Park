package Jeu;			// Contenu dans le package "Jeu" 

public abstract class Personnage extends ElementMobile {			// Début de la classe Perosnnage qui hérite de Element Mobile 
	
	public short vie = 100;			// Chaque personnage commence avec une vie déclarée a 100 de base. 
	protected String name;			// Chaque Personnage Possède un nom pour le différencier 	
	private int tourSurvecu = 0;	//  Nombre de tour survecu par personnage 
	
	
	// Constructeur de la classe Personnage, défini comme celui de element mobile 
	public Personnage (final Chateau donjon,  final String image, final int x, final int y, final int direction, final String espece, String name) {
		super(donjon, image, x, y, direction, espece);
		this.name = name;
	}
	
	
	// Deuxième constructeur qui hérite de la classe Element mobile 
	public Personnage (final Chateau donjon,  final String image, final String espece, String name) {
		super(donjon, image, espece);
		this.name = name;
	}

	
	
	public void mourir(Chateau donjon){ // Méthode qui permet à un personnage de mourir 
		this.vie = 0;					// Quand il meurt sa vie reviens à 0 
	}
	
	public boolean estMort() {			// Deuxième méthode qui définie que le eprsonnage est bien mort 
		
		if ( donjon.getXY(getX(), getY()).verifierMonstre() == true && monstre == false )	// On vérifie qu'il y a bien un monstre sur la case quand on meurt 
			this.mourir(donjon);			// Si c'est bien le cas alors on utilise la fonction mourir pour la vie 
		
		if ( vie <= 0 )			// Si la vie est bien à 0 car il est mort 
			return true;		// On retourne True 
		else
			return false;		// Sinon c'est False il est pas mort 
	}
	
	public short getVie() {		// Méthode pour récupérr la vie d'un personnage
		return this.vie; 		// On retourne son attribut vie 
		}

	public void setVie(short vie, boolean signe) {		// Méthode qui servira lors d'une amélioration 
		if (signe == true) {							// Elle servira pour redonner de la vie à un perso 
			this.vie += vie;
		} else if (signe == false){
			this.vie -= vie; 
		} else {
			System.out.println("Erreur Argument Fonction ! : setVie");} // Si elle ne marche pas on renvoi une erreur 
	}
	

	public void seDeplacer() {			// Méthode qui permet de se déplacer sur la carte 
			if ( estMort() == false )		// S'il n'est pas mort 
				tourSurvecu++;				// On incremente son nombre de tour survecu 
				super.seDeplacer();			// Et on se déplace grâce à une seconde méthode 
		}
	    
	public void seBouger(){					// Se bouger est aussi une méthode de déplacement 
			if ( estMort() == false )  {		// Bien sur que si le personnage n'est pas mort 
			donjon.setXY(getX(),getY(), new Sol()); 	// On met du sol à son ancienne position			
			seDeplacer();								// Pius on midifie ses attributs x et y 
			donjon.setXY(getX(),getY(),this);			// et on le recolle au bon endroit 
			}
	}
	
	public String toString() {			// Méthode pour récupérer els attributs d'un personnage 
		return "Nom : " + name + "	Espece : " + espece + "	Vie : " + getVie() + " PV";
	}
	
	public String toString(String choix) {	// Méthode qui convertit en String les attributs d'un personnage Mais que celui choisis
		if (choix == "name" )
		return name;
		else if (choix == "espece" )
		return espece;
		else if (choix == "vie" )
			return "" + getVie() + " PV";
		else if (choix == "toursurvecu")
			return "" + tourSurvecu;
		else
			return "Bad Choose";
	}
	
	public int getNbrTourSurvecu () {					// Getter de nbrTourSucurvecu
		return tourSurvecu;
	}
	
}

