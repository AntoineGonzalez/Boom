package models.strategies.fighterstrat;

import models.utils.Actions;

/**
 * Interface qui va servir au developpement des strategies
 * @author Erwann
 */
public interface FighterStrategy 
{
    /**
     * Methode qui va permettre de choisir l'action a effectuer suivant une certaine stratégie
     * @return 
     *  Retourne l'action que le combattant doit realiser
     */
    public Actions doAction();
}