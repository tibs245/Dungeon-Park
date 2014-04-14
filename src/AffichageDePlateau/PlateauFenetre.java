package AffichageDePlateau;

import javax.swing.JFrame;

class PlateauFenetre extends JFrame {
	
	private static final long serialVersionUID = -6563585351564617603L;
	private final PlateauPanneau plateauPanneau;

	public PlateauFenetre(final int largeur, final int hauteur, final PlateauCase plateauCases[][]) {
		this.setTitle("Dungeon Park");
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.plateauPanneau = new PlateauPanneau(largeur, hauteur, plateauCases);
		this.setContentPane(this.plateauPanneau);
		this.setVisible(true);
	}

	public void placerPiece(final PlateauPiece piece) {
		this.plateauPanneau.placerPiece(piece);
	}

	public void rafraichir() {
		this.plateauPanneau.repaint();
	}
}
