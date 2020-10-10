
package models.element;

/**
 *<b>Classe représentant un mur dans notre modèle de jeu<\b>
 * un mur se caractèrise par un nombre de point de vie et un boolèen qui décrit si il est destructible ou pas
 */
public class Wall{
    
    private boolean destructible;
    private int hp;
    /**
     * Constructeur qui permet d'avoir une instance de mur, à sa création un mur à 50 hp.
     * @param destructible 
     * booléen qui determine si le mur est destructible où non
     */
    public Wall(boolean destructible) {

        this.destructible = destructible;   
        if(destructible)
        {
            this.hp = 50;
        }
    }
    /**
     * retourne true si un mur est destructible, false sinon.
     * @return
     */
    public boolean getDestructible()
    {
        return this.destructible;
    }
    /**
     * renvoie le nombre de point de vie d'un mur.
     * @return 
     */
    public int getHp()
    {
        return this.hp;
    }
}
