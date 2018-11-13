package istic.aoc.m3.afficheur;

import istic.aoc.m3.generator.Generator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

/**
 * @author VinYarD
 * created : 13/11/2018, 16:41
 */


public class Afficheur extends JPanel implements Observer {
	
	private long value;
	private Color color;
	
	public Afficheur(Color color) {
		this.color = color;
		this.setOpaque(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		this.setBackground(this.color);
		
		Rectangle2D bounds = g2.getFontMetrics().getStringBounds(String.valueOf(this.value), g2);
		g2.drawString(String.valueOf(this.value), (int) (this.getWidth() / 2 - bounds.getCenterX()), (int) (this.getHeight() / 2 - bounds.getCenterY()));
	}
	
	/**
	 * This method is called whenever the observed object is changed. An
	 * application calls an <tt>Observable</tt> object's
	 * <code>notifyObservers</code> method to have all the object's
	 * observers notified of the change.
	 *
	 * @param o   the observable object.
	 * @param arg an argument passed to the <code>notifyObservers</code>
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Generator) {
			this.value = ((Generator) o).getValue();
			this.repaint();
		}
	}
}
