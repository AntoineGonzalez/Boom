/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.AbstractController;
import controllers.IAController;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import models.gamestate.AbstractModel;
import models.gamestate.GameState;
import models.utils.Actions;
import models.utils.JTableModel;
import models.utils.Observer;

/**
 * Classe qui permet de creer l'interface graphique
 * @author Erwann
 */
public class Arena extends JFrame implements Observer{

    //Reference sur le modele
    AbstractModel model;
    //Reference sur le controleur
    AbstractController controler;
    
    //Variables de classe
    
    private final boolean isMainArena;
    
    private JTableModel jTablemodel;
    
    private final JButton bNext;

    private JTable jTable;
    
    private DefaultListModel dialogs;
    
    private SubArena sub;
    
    private final String resourcesRep = "resources/imgs/";
    
    
    /**
     * Constructeur de la JFrame
     * @param model
     *  le modele de jeu
     * @param controler
     *  le controleur du jeu
     * @param main
     *  boolean qui definit si la fenetre est la fenetre principale
     * @param jtableModel 
     *  le modele de la JTable contenant les stats
     */
    public Arena(AbstractModel model,AbstractController controler,boolean main,JTableModel jtableModel)
    {
        this.jTablemodel = jtableModel;
        
        this.isMainArena = main;
        
        this.bNext = new JButton("Go Next !");
        
        this.bNext.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                IAController ia = (IAController) controler;  
                ia.nextButtonClicked();
            }
        });
        
        this.model = model;
        
        this.controler = controler;
        
        this.dialogs = new DefaultListModel();
        
        this.jTable = new JTable(this.jTablemodel);
        
        this.sub = new SubArena(this.model.getArena());
        
        this.setTitle("Boom Boom");
        
        this.setSize(300, 300);
        
        this.setLocationRelativeTo(null);               

        ImageIcon img = new ImageIcon(resourcesRep+"icon.jpg");
        
        this.setIconImage(img.getImage());
                   
        this.setVisible(true);
    }
    
    /**
     * Methode qui va se lancer quand notre classe sera notifie d'un changement par le modele
     * @param anim
     *  l'animation a jouer ( tir, bombe, etc..)
     * @param e 
     *  l'objet sur lequel jouer l'animation
     */
    @Override
    public void update(Actions.Action anim, Object e) {
        //Suppression du contenu actuel
        this.getContentPane().removeAll();
        
        this.sub.setMap(this.model.getArena());
        //La main Arena possedera les boutons suivant et la JTable des stats
        if(!this.isMainArena)
        {
            this.setContentPane(sub);
        } 
        else
        {
            this.jTablemodel = new JTableModel(((GameState)this.model).getFighters());
            this.jTable = new JTable(jTablemodel);
            JList list = new JList(dialogs); //data has type Object[]
            list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            list.setVisibleRowCount(-1);
            
            JPanel eastPart = new JPanel(new BorderLayout());
            eastPart.add(this.bNext,BorderLayout.NORTH);
            JScrollPane scrollPane = new JScrollPane(list);
            
            eastPart.add(scrollPane,BorderLayout.SOUTH);
            
            this.getContentPane().add(this.jTable,BorderLayout.SOUTH);
            this.getContentPane().add(sub,BorderLayout.CENTER);
            this.getContentPane().add(eastPart,BorderLayout.EAST);
            this.addDialog(((GameState)this.model).getPreviousAction());
        }
        sub.paintSubArena(anim, e);
        this.revalidate();
        this.repaint();
    }
    
    /**
     * Permet d'ajouter un texte a la fenetre de dialogue sur la frame principale
     * @param dialog 
     *  l'action qui a ete execute par un joueur
     */
    public void addDialog(String dialog)
    {
        if(!dialog.equals(""))
        {
            this.dialogs.addElement(dialog);
        }
        
    }
    
}
