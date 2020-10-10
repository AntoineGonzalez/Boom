/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


/**
 * Classe abstraite qui permet de definir la maniere dont on dessine une case  
 * @author Erwann
 */
public abstract class FighterView extends JPanel {
    
    protected BufferedImage image;
    
    protected void changeAnimation(BufferedImage newImg)
    {
        this.image = newImg;
    };
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), this);      
    }
}
