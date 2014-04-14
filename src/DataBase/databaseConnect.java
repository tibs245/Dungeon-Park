package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Configuration.main;
import Jeu.Chateau;

public class databaseConnect {

	protected Connection connex;					// Objet de connection à la BDD
	protected Statement instruction;				// Objet afin d'envoyer des instruction à la BDD
	protected int idPartie;							// ID de la partie en cours
	protected int largeur;							// Largeur de la carte
	protected int hauteur;							// Hauteur de la carte
	
	public databaseConnect () {						// Constructeur de la BDD
		try {
		     Class.forName("org.hsqldb.jdbc.JDBCDriver");			// Vérifier les drivers
		 } catch (Exception e) {
		     System.err.println("EN - ERROR: failed to load HSQLDB JDBC driver.");
		     System.err.println("FR - ERREUR: Impossible de charger le driver HSQLDB JDBC.");
		     e.printStackTrace();
		 }
		try {
			 connex = DriverManager.getConnection("jdbc:hsqldb:file:DataBase;ifexists=false", "Jsim", "");		// On crée la base de données
			 instruction = connex.createStatement();                    // Create Statement \\
			 createDatabase();											// On crée les tables de la BDD
		} catch (SQLException e) {
			e.printStackTrace(); }
		
		idPartie = 1;
	}
	
	public void insert(String image, int x, int y, int tour) {				// Méthode pour insérer des cases
		try {
			 instruction.executeQuery("INSERT INTO Position(id_partie, id_tour, image, pos_x, pos_y)" +
					" VALUES ( " + this.idPartie + ", '"+ tour +"', '"+ image +"', '"+ x +"', '"+ y +"')");

		 } catch (SQLException e) {
			e.printStackTrace(); }
	}
	
	public void insert(String name, String espece, int vie, int nbrTour) {	// Méthode pour insérer des personnages


		try {
			 instruction.executeQuery("INSERT INTO Personnage(id_partie, nom, espece, nbr_vie, nbr_tour)" +
					" VALUES ( " + this.idPartie + ", '"+ name +"', '"+ espece +"', '"+ vie +"', '"+ nbrTour +"')");

		 } catch (SQLException e) {
			e.printStackTrace(); }
	}
	
	public String[][] Select(int tour) {									// Méthode pour sélectionner les cases d'un tour donnée
		String information[][] = new String[this.largeur][this.hauteur];

		try {
					 
			 		 String sql = "SELECT image, pos_x, pos_y FROM Position WHERE id_partie=" + this.idPartie + " AND id_tour=" + tour;     // Request Sql
				 	 ResultSet resultat = instruction.executeQuery(sql);
				 	 resultat.isFirst();							// On positionne le curseur au début
				 	 resultat.next();
				 	 
			for(int x = 0 ; x  < this.largeur ; x++)
				 for(int y = 0; y < this.hauteur ; y++) {
					 information[resultat.getInt("pos_x")][resultat.getInt("pos_y")] = resultat.getString("image");		// Méchante ligne pour attribuer les variables
					 resultat.next();								// On va au suivant
				 }
		        resultat.close();

		 } catch (SQLException e) {
			e.printStackTrace(); }

		return information;	
	}
	
	public void close() throws SQLException {						// Méthode pour fermer la BDD
		connex.close();
	}
	
	public void clean (int idPartie) {								// Rendre la BDD clean sur la partie

		try {
			
		 instruction.executeQuery("DELETE FROM Position WHERE id_partie = " + idPartie + ";");
		 instruction.executeQuery("DELETE FROM Configuration WHERE id_partie = " + idPartie + ";");
		 instruction.executeQuery("DELETE FROM Personnage WHERE id_partie = " + idPartie + ";");

		 } catch (SQLException e) {
			e.printStackTrace(); }
	}
	
	public int tourMax() {											// Méthode pour connaitre le nombre de tour à une partie
		 ResultSet resultat;
		try {
			
			 resultat = instruction.executeQuery("SELECT COUNT(*) AS nbrTour FROM Position WHERE id_partie =" + this.idPartie + " AND pos_x=0 AND pos_y=0");
			        resultat.isFirst();
			        resultat.next();
			        return resultat.getInt("nbrTour");
			 } catch (SQLException e) {
				e.printStackTrace(); }
		return 0;													// Si la requête ne réussi pas, alors on renvoie 0
	}
	
	public int createDatabase() {									// Méthode pour crée les tables de la BDD
		try {
			
			 instruction.executeQuery("CREATE TABLE IF NOT EXISTS Position " +	// Table Position permet d'enregistrer les cases et leur position
					 "( id_partie TINYINT NOT NULL," + 
					 " id_tour SMALLINT NOT NULL," + 
					 " image VARCHAR(128)  NOT NULL," +
					 " pos_x INTEGER NOT NULL," +
					 " pos_y INTEGER NOT NULL )");
			 
			 instruction.executeQuery("CREATE TABLE IF NOT EXISTS Configuration " +	// Table configuration qui permet de sauvegarder la configuration de la partie
				 "( id_partie TINYINT  NOT NULL," +
				 " taille_x INTEGER NOT NULL," +
				 " taille_y INTEGER NOT NULL," +
				 " nbr_personnage SMALLINT NOT NULL )");
			 
			 instruction.executeQuery("CREATE TABLE IF NOT EXISTS Personnage " +	// Table personnage pour sauvegarder les informations sur les personnages
				"( id_partie TINYINT  NOT NULL," +
				" nom VARCHAR(25) NOT NULL," +
				" espece VARCHAR(25) NOT NULL," +
				" nbr_vie SMALLINT NOT NULL," +
				" nbr_tour SMALLINT NOT NULL )");
			 
			 } catch (SQLException e) {
				e.printStackTrace(); }
		return 0;
	}
	
	public void initie(int tailleX, int tailleY, int nbrPersonnage) {		// On initie une nouvelle partie
		try {
			 ResultSet resultat = instruction.executeQuery("INSERT INTO Configuration(id_partie, taille_x, taille_y, nbr_personnage)" +
					" VALUES ( " + main.ID_PARTIE + ", " + tailleX + ", " + tailleY + ", " + nbrPersonnage + ")");
			 this.idPartie = main.ID_PARTIE;
			 this.largeur = tailleX;
			 this.hauteur = tailleY;
		        resultat.close();

		 } catch (SQLException e) {
			e.printStackTrace(); }
	}
	
	
	public void initie(int nPartie) {				// On initie un replay
		try {
			 ResultSet resultat = instruction.executeQuery("SELECT taille_x, taille_y FROM Configuration WHERE id_partie = " + nPartie);
			 resultat.isFirst();
			 resultat.next();
			 
			 this.idPartie = nPartie;						// On configure nos variables avec les paramètres du replay
			 this.largeur = resultat.getInt("taille_x");
			 this.hauteur = resultat.getInt("taille_y");
			 
			 Chateau.HAUTEUR=this.hauteur;					// On configure le Chateau ave les paramètres du replay
			 Chateau.LARGEUR=this.largeur;
			 Chateau.NB_Murs = 0;
			 Chateau.NB_Pierres = 0;
			 
		        resultat.close();

		 } catch (SQLException e) {
			e.printStackTrace(); }
	}
	}
