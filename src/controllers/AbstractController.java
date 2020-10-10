/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.gamestate.AbstractModel;


/**
 *
 * @author Erwann
 */
public class AbstractController {
    
    protected AbstractModel model;
    
    public AbstractController(AbstractModel model)
    {
        this.model = model;
    }
}
