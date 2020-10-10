package models.strategies.fighterstrat;

/**
 * Classe qui permet de creer une strategie agressive
 * @author Erwann
 */
public class AgressiveStrategy extends GeneralStrategy
{
    /**
     * Constructeur d'une strategie agressive qui va permettre de definir les chances de realisation de chaques action
     */
    public AgressiveStrategy()
    {
        super(0.1,0.2,0.0,0.2,0.2);
    } 
}