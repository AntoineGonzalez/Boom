
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.utils;





/**
 * Classe permettant de donner a une classe la capacite d'en observer une autre
 * @author Erwann
 */
public interface Observer {
    public void update(Actions.Action anim, Object e);
}
