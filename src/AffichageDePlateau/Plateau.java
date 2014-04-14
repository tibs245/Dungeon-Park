package AffichageDePlateau;

public class Plateau {
	private final PlateauFenetre plateauFenetre;

	public Plateau(final int largeur, final int hauteur, final PlateauCase plateauCases[][]) {
		this.plateauFenetre = new PlateauFenetre(largeur, hauteur, plateauCases);
	}

	public void placerPiece(final PlateauPiece piece) {
		this.plateauFenetre.placerPiece(piece);
	}

	public void rafraichir() {
		this.plateauFenetre.rafraichir();
	}
}
