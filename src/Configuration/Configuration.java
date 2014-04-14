package Configuration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Jeu.Chateau;

public class Configuration extends JFrame implements  ActionListener {
	JButton newParty = new JButton("Nouvelle Partie");				// We declare a Button
	JButton replayParty = new JButton("Revoir Partie");		
	JButton newFastParty = new JButton("Nouvelle Partie Rapide");
	
	JLabel accueil = new JLabel();						// JLabel de l'accueil
	
	private JTextField tailleMapXChoose;				// We declare a Button
	private JTextField tailleMapYChoose;
	private JTextField vitesseChoose;
	private JTextField nbrMonstreChoose;
	private JTextField nbrDebileChoose;
	private JTextField nbrCouillonChoose;
	private JTextField nbrPousseurChoose;
	private JTextField nbrMursChoose;
	private JTextField nbrPierresChoose; 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean lancer;
	private boolean replay;
	
	public Configuration () {
		super("Configuration de Dungeon Park");
		lancer=false;
		replay=false;
		this.setLayout(new GridLayout(1,2));
		this.setSize(1024,768);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		affMenuPrincipal();
	}
	
	public void affMenuPrincipal() {
		
		accueil.setBackground(new Color(0,0,0));
		accueil = new JLabel(new ImageIcon("images/Garde.jpg"));
		
		accueil.add(newParty);												// We add the buttons
		accueil.add(replayParty);
		accueil.add(newFastParty);
		
		newParty.setBounds(300,630,200,60);
		replayParty.setBounds(520,630,200,60);
		newFastParty.setBounds(740,630,200,60);

		this.add(accueil);
		
		this.revalidate();
		
		newParty.addActionListener(this);
		replayParty.addActionListener(this);
		newFastParty.addActionListener(this);
	}
	
	@SuppressWarnings("deprecation")
	public void affMenuConfiguration() {
		JPanel titre = new JPanel();
		JPanel choix = new JPanel();
		JPanel validation = new JPanel();

		tailleMapXChoose = new JTextField("" + Chateau.HAUTEUR);		// On prépare les valeurs par défauts
		tailleMapYChoose = new JTextField("" + Chateau.LARGEUR);
		vitesseChoose = new JTextField("" + main.VITESSE);
		nbrMonstreChoose = new JTextField("" + main.NBR_MONSTRE);
		nbrDebileChoose = new JTextField("" + main.NBR_DEBILE);
		nbrCouillonChoose = new JTextField("" + main.NBR_COUILLON);
		nbrPousseurChoose = new JTextField("" + main.NBR_POUSSEUR);		
		nbrMursChoose = new JTextField("" + Chateau.NB_Murs);
		nbrPierresChoose = new JTextField("" + Chateau.NB_Pierres); 
		
		
		JButton jouer = new JButton("Jouer");			// On paramètre le boutton pour jouer
		jouer.addActionListener(this);

		
		this.setLayout(new BorderLayout());
		titre.setBackground(new Color(190,80,80));
		
		
		JLabel titreText = new JLabel("Configuration Avancée");
		titreText.setBackground(titre.getBackground());
		titreText.setFont(new Font("Serif", Font.PLAIN, 36));
		
		titre.add(titreText);
		
		choix.setLayout(new GridLayout(9,2));
		choix.add(new JLabel("Taille de la carte X :"));
		choix.add(tailleMapXChoose);	// Technique pour mettre un nombre en chaine de caractère
		choix.add(new JLabel("Taille de la carte Y :"));
		choix.add(tailleMapYChoose);
		choix.add(new JLabel("Vitesse : ( ms )"));
		choix.add(vitesseChoose);
		choix.add(new JLabel("Nombre de Monstre :"));
		choix.add(nbrMonstreChoose);
		choix.add(new JLabel("Nombre de Cachotier :"));
		choix.add(nbrCouillonChoose);
		choix.add(new JLabel("Nombre de Débiles :"));
		choix.add(nbrDebileChoose);
		choix.add(new JLabel("Nombre de Pousseur :"));
		choix.add(nbrPousseurChoose);
		choix.add(new JLabel("Nombre de Murs :"));
		choix.add(nbrMursChoose);
		choix.add(new JLabel("Nombre de Pierres :"));
		choix.add(nbrPierresChoose);

		validation.add(jouer);
		this.add(validation);
		
		this.add(titre, BorderLayout.NORTH);
		this.add(choix, BorderLayout.CENTER);
		this.add(validation, BorderLayout.SOUTH);
		
		this.remove(accueil);
		this.repaint();
		this.revalidate();
		this.resize(500,500);
		this.setLocationRelativeTo(null);		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Nouvelle Partie"))
			affMenuConfiguration();
		else if (e.getActionCommand().equals("Revoir Partie"))
				replay=true;
		else if (e.getActionCommand().equals("Nouvelle Partie Rapide")) {
				this.setVisible(false);
				lancer=true;
		} else if (e.getActionCommand().equals("Jouer")) {
			if (	Integer.parseInt(tailleMapXChoose.getText()) != 0 &&		// Condition pour évité des mauvaise entrée
					Integer.parseInt(tailleMapYChoose.getText()) != 0 ) {		// Fin de la méchante condition
				
					this.setVisible(false);
					Chateau.HAUTEUR=Integer.parseInt(tailleMapXChoose.getText());	// On met à jour les variables du jeu
					Chateau.LARGEUR=Integer.parseInt(tailleMapYChoose.getText());
					main.VITESSE=Integer.parseInt(vitesseChoose.getText());
					main.NBR_MONSTRE=Integer.parseInt(nbrMonstreChoose.getText());
					main.NBR_COUILLON=Integer.parseInt(nbrCouillonChoose.getText());
					main.NBR_DEBILE=Integer.parseInt(nbrDebileChoose.getText());
					main.NBR_POUSSEUR=Integer.parseInt(nbrPousseurChoose.getText());
					Chateau.NB_Murs=Integer.parseInt(nbrMursChoose.getText());				
					Chateau.NB_Pierres=Integer.parseInt(nbrPierresChoose.getText());
					
					lancer=true;		// On lance ! 
			}
		}
	}
	
	public void halt () {
		this.dispose();
	}
	
	public boolean getLancer() {
		return lancer;
	}
	
	public boolean getReplay() {
		return replay;
	}

		}
