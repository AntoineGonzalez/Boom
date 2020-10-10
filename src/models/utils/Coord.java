/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.utils;

/**
 *  Classe permettant de representer une coordonnee ( x et y )
 * @author Erwann
 */
public class Coord {
    
    private int x;
    private int y;
    
    /**
     * Constructeur d'une coordonnee
     * @param x
     *  la coordonnee x
     * @param y 
     *  la coordonnee y
     */
    public Coord(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
}
