package Configuration;

import Jeu.*;
import DataBase.databaseConnect;


public class main {
	private static Configuration configuration = new Configuration();	// On ouvre une fenetre de configuration
	public static int VITESSE = 500;									// On prépare les paramètres par défaut du jeu
	public static int NBR_MONSTRE = 1;
	public static int NBR_COUILLON = 1;
	public static int NBR_DEBILE = 1;
	public static int NBR_POUSSEUR = 1;
	public static Boolean PAUSE = false;
	public static int ID_PARTIE=1;										// On définit l'ID de la partie
	public static databaseConnect database = new databaseConnect();
	
	protected static Chateau donjon;									// On définit la cartes et les personnages
	protected static Personnage mesPersonnages[];
	protected static Monstre mesMonstres[];
	
	protected static Interaction fenetreInteraction;					// On définit la fenetre d'interaction

	public static void main(final String[] args) throws InterruptedException {
	for (;;) {															// On attend un ordre pour lancer le jeu
		if ( configuration.getLancer() == true ) {						// Si le joueur demande de lancer le jeu on le lance
		Lancer();
		break;
		} else if ( configuration.getReplay() == true ) {				// Si le joueur demande un replay, on lance un replay
			replayGame();
			break;
		}

		Thread.sleep(500);
	}
	
	}
	
	public static void Lancer() throws InterruptedException {			// On lance le jeu
		
		configuration.halt();											// On ferme la configuration
		
		database.clean(main.ID_PARTIE);												// On nettoie la base de donnée	
		database.initie(Chateau.LARGEUR, Chateau.HAUTEUR, main.NBR_POUSSEUR+main.NBR_COUILLON+main.NBR_DEBILE+NBR_MONSTRE);
		
		donjon = new Chateau();														// On initie le donjon
		mesPersonnages = new Personnage[NBR_COUILLON + NBR_DEBILE + NBR_POUSSEUR];	// On initie les personnages
		mesMonstres = new Monstre[NBR_MONSTRE];
		
			
			
			for (int i=0; i<NBR_COUILLON; i++)										// On initie les couillon
				mesPersonnages[i] = new PersoIntelligent(donjon);
			
			for (int i=NBR_COUILLON; i<NBR_COUILLON+NBR_DEBILE; i++)				// On initie les débiles
				mesPersonnages[i]=new PersoNoob(donjon);
			
			for (int i=NBR_COUILLON+NBR_DEBILE; i<NBR_COUILLON+NBR_DEBILE+NBR_POUSSEUR; i++)	// On initie les pousseurs
				mesPersonnages[i]=new PersoPousseur(donjon);
			
			for (int i=0; i<NBR_MONSTRE; i++)										// On initie les monstres
				mesMonstres[i]= new Monstre(donjon, "Hector");
			

			fenetreInteraction = new Interaction(NBR_MONSTRE, NBR_DEBILE+NBR_COUILLON+NBR_POUSSEUR, mesMonstres, mesPersonnages );	// On lance la fenetre d'interaction
			
			while ( jeuEnCours(mesMonstres, mesPersonnages) ) {
				if ( PAUSE == false ) {														// Si le mode pause est sur false on passe
				nextLap();
	        	Thread.sleep(VITESSE);														// On attend VITESSE ms
				}
			}

	}
	
	private static boolean jeuEnCours(Monstre mesMonstres[], Personnage mesPersonnages[]) {	// Méthode pour vérifier si le jeu est en cour
		Boolean monstreVivant = false , persoVivant = false ;								// Attribut pour savoir si le monstre ET les enfants sont vivant
		
		for (int i=0; i<NBR_MONSTRE; i++)													// On vérifie si les monstres sont vivant
			if ( mesMonstres[i].estMort() == false )
				monstreVivant = true;
		
		for (int i=0; i<NBR_POUSSEUR+NBR_COUILLON+NBR_DEBILE; i++)							// On vérifie si les enfants sont vivant
			if ( mesPersonnages[i].estMort() == false )
				persoVivant = true;
		
		if ( persoVivant == true && monstreVivant == true )									// Si les deux sont vivants le jeu continu
			return true;
		else																				// Sinon il s'arrête
			return false;
	}
	
	public static void nextLap() {			// Méthode pour changer de tour
		for (int i=0; i<NBR_MONSTRE; i++)	{										// We move the monsters
			mesMonstres[i].manger();
			mesMonstres[i].seBouger();
			database.insert(mesMonstres[i].toString("name"),mesMonstres[i].toString("espece"),mesMonstres[i].getVie(),mesMonstres[i].getNbrTourSurvecu());

		for (i=0; i<NBR_COUILLON+NBR_DEBILE+NBR_POUSSEUR; i++) {					// We move the children
			mesPersonnages[i].seBouger();
			database.insert(mesPersonnages[i].toString("name"),mesPersonnages[i].toString("espece"),mesPersonnages[i].getVie(),mesPersonnages[i].getNbrTourSurvecu());
		}
		
		for (int x=0; x<Chateau.LARGEUR; x++)						// On sauvegarde le tour
			for (int y=0; y<Chateau.HAUTEUR; y++)
				database.insert(donjon.getXY(x, y).getPathImage(), x, y, Chateau.Tours);
	}
		
    		fenetreInteraction.actualise();
    		donjon.rafraichir();			// We reactualise the map
	}
	
	public static void replayGame() throws InterruptedException {		// Méthode pur revoir un partie
		configuration.halt();											// On ferme la fenetre de configuration
		int maxTour = database.tourMax();								// On récupère le nombre de tour à jouer
		
		database.initie(1);					// On initie le replay
		
		donjon = new Chateau();				// On initie le donjon
		
	for (int i=0; i<maxTour; i++) {			// On joue tout les tours 1 par 1
		String plateau[][] = database.Select(i);	// On récupère les cases et leur posiition
		
		for (int x=0; x<Chateau.LARGEUR; x++)		// On actualise les cases du donjon
			for (int y=0; y<Chateau.HAUTEUR;y++)
				donjon.setXY(x, y, new Case(plateau[x][y], false, false));
		
		donjon.rafraichir();						// On actualise l'affichage
		Thread.sleep(VITESSE);						// On attend VITESSE ms
		
	}
	}
}