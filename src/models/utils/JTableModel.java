/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.utils;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import models.element.fighters.Fighter;
/**
 * Permet de represent un modele pour l'element graphique JTable
 * @author Erwann
 */
public class JTableModel extends AbstractTableModel{
    
    private ArrayList<Fighter> fighterList;
    
    private String[] entetes = { "Pseudo","Vie","balles","bombes","mines"};
    
    /**
     * Constructeur du modele
     * @param f 
     *  la liste de joueurs
     */
    public JTableModel(ArrayList<Fighter> f)
    {
        this.fighterList = f;
    }

    /**
     * Permet de recuperer le nombre de lignes
     * @return 
     *  Retourne le nombre de ligne
     */
    @Override
    public int getRowCount() {
        return this.fighterList.size();
    }

     /**
     * Permet de recuperer le nombre de colonnes
     * @return 
     *  Retourne le nombre de colonnes
     */
    @Override
    public int getColumnCount() {
        return this.entetes.length;
    }
    
    /**
     * Permet de recuperer le nom d'une colonne
     * @param columnIndex
     * l'index de la colonne dont on veut le nom
     * @return 
     *  Retourne le nom d'une colonnes
     */
    @Override
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    /**
     * Permet de recuperer la valeurs des cases en fonction de la colonne et de la ligne
     * @param rowIndex
     *  l'index de la ligne
     * @param columnIndex
     *  l'index de la colonne
     * @return 
     *  l'objet contenu dans cette case, ici une chaine de caractere
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: 
                return this.fighterList.get(rowIndex).getName();
            case 1:
                return this.fighterList.get(rowIndex).getEnergy();
            case 2:
                return this.fighterList.get(rowIndex).getNb_shot();
            case 3:
                return this.fighterList.get(rowIndex).getNb_bomb();
            case 4:
                return this.fighterList.get(rowIndex).getNb_mine();   
           
        }
        return null;
    }
}
