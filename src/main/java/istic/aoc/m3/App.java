package istic.aoc.m3;

import istic.aoc.m3.afficheur.Afficheur;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JFrame frame = new JFrame("test");
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        
        JPanel afficheurContainer = new JPanel();
    
        afficheurContainer.setLayout(new GridLayout(2, 2));
    
        afficheurContainer.add(new Afficheur(Color.WHITE));
        afficheurContainer.add(new Afficheur(Color.RED));
        afficheurContainer.add(new Afficheur(Color.GREEN));
        afficheurContainer.add(new Afficheur(Color.BLUE));
        
        contentPane.add(afficheurContainer);
    
        JPanel controlContainer = new JPanel();
        
        controlContainer.setLayout(new GridLayout(1, 2));
        
        controlContainer.add(new JButton("start"));
        controlContainer.add(new JButton("stop"));
        
        contentPane.add(controlContainer, BorderLayout.SOUTH);
        
        frame.setContentPane(contentPane);
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
