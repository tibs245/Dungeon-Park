package Configuration;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Jeu.Personnage;
import Jeu.Monstre;

public class Interaction extends JFrame implements ActionListener{
	private int nbrMonstre, nbrPersonnage;
	private JPanel infoJeu = new JPanel();
	private JPanel header = new JPanel();
	private Monstre monstres[];
	private Personnage personnages[];
	private JLabel infoVie[];
	private JLabel infoTour[];
	
	private JButton play;
	private JButton back;
	private JButton next;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Interaction (int nbrMonstre, int nbrPersonnage, Monstre monstres[], Personnage personnages[]) {
		this.setLayout(new BorderLayout());		
		
		this.setTitle("Interaction | Dungeon Park");
		this.personnages=personnages;
		this.monstres=monstres;
		this.nbrPersonnage = nbrPersonnage;
		this.nbrMonstre=nbrMonstre;
		this.infoVie = new JLabel[nbrMonstre+nbrPersonnage];
		this.infoTour = new JLabel[nbrMonstre+nbrPersonnage];
		int hauteurInfo;
		
		back = new JButton("Back");		
		play = new JButton("Pause");		
		next = new JButton("Next");
		
		back.addActionListener(this);
		play.addActionListener(this);
		next.addActionListener(this);
		
		header.add(back);
		header.add(play);
		header.add(next);
		
		
		infoJeu.setLayout(new GridLayout(nbrMonstre+nbrPersonnage+1,4));
		
		if ( nbrPersonnage < 10 )
			hauteurInfo = 50;
		else if ( nbrPersonnage < 20 )
			hauteurInfo = 35;
		else
			hauteurInfo = 25;
			
			this.setSize(400,75+hauteurInfo*nbrPersonnage+nbrMonstre);
		
		infoJeu.add(new JLabel("Nom :"));
		infoJeu.add(new JLabel("Espece :"));
		infoJeu.add(new JLabel("Vie :"));
		infoJeu.add(new JLabel("Nbr Tour :"));
		
		for (int i = 0; i < nbrPersonnage; i++) {
			infoVie[i]=new JLabel(personnages[i].toString("vie"));
			infoTour[i]=new JLabel(personnages[i].toString("toursurvecu"));
			
			infoJeu.add(new JLabel(personnages[i].toString("name")));
			infoJeu.add(new JLabel(personnages[i].toString("espece")));
			infoJeu.add(infoVie[i]);
			infoJeu.add(infoTour[i]);
		}
		
		for (int i = 0; i < nbrMonstre; i++) {
			infoVie[nbrPersonnage+i]=new JLabel(monstres[i].toString("vie"));
			infoTour[nbrPersonnage+i]=new JLabel(monstres[i].toString("toursurvecu"));
			
			infoJeu.add(new JLabel(monstres[i].toString("name")));
			infoJeu.add(new JLabel(monstres[i].toString("espece")));
			infoJeu.add(infoVie[nbrPersonnage+i]);
			infoJeu.add(infoTour[nbrPersonnage+i]);
		}
		
		this.add(header, BorderLayout.NORTH);
		this.add(infoJeu, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void actualise () {
		
		for (int i = 0; i < nbrPersonnage; i++) {
			infoVie[i].setText(personnages[i].toString("vie"));	
			infoTour[i].setText(personnages[i].toString("toursurvecu"));
		}

		
		for (int i = 0; i < nbrMonstre; i++) {
			infoVie[nbrPersonnage+i].setText(monstres[i].toString("vie"));
			infoTour[i+nbrPersonnage].setText(monstres[i].toString("toursurvecu"));
		}
		
		infoJeu.repaint();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Play")) {
				main.PAUSE=false;
				play.setText("Pause");
		} else if (e.getActionCommand().equals("Pause")) {
				main.PAUSE=true;
				play.setText("Play");
		} else if ( e.getActionCommand().equals("Next") )
			main.nextLap();
	}

}
