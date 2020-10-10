package controllers;

import models.gamestate.AbstractModel;
import models.gamestate.GameState;

/**
 *<b>la classe IAController definissant le controleur de notre application.</b>
 * le controlleur vient modifier le model qu'il a en attribut lors d'une intéraction utilisateur.
 */
public class IAController extends AbstractController {
    /**
     * Constructeur abstrait du controlleur
     * @param model Instance de notre model qui sera controller par le controlleur.
     */
    public IAController(AbstractModel model)
    {
        super(model);
    }
    /**
     * méthode appeler lors du clique sur le bouton suivant.
     * il prend le modèle et lui fait jouer le nouveau tour de jeu.
     */
    public void nextButtonClicked()
    {
        GameState game = (GameState)this.model;
        game.playRandomIA();
    }
    
    
    
}
