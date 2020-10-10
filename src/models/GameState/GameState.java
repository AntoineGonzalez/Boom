/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.gamestate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.element.EnergyShard;
import models.element.Wall;
import models.element.explosives.Bomb;
import models.element.explosives.Explosive;
import models.element.explosives.Mine;
import models.element.fighters.*;
import models.factories.FighterFactory;
import models.strategies.fighterstrat.AgressiveStrategy;
import models.strategies.fighterstrat.BalancedStrategy;
import models.strategies.fighterstrat.DefensiveStrategy;
import models.strategies.fighterstrat.FighterStrategy;
import models.strategies.mapstrat.EnergyStrat;
import models.strategies.mapstrat.FightersInCorner;
import models.strategies.mapstrat.FightersInMiddle;
import models.strategies.mapstrat.MapStrategy;
import models.strategies.mapstrat.WallStrat;
import models.utils.Actions;
import models.utils.AvailableColors;
import models.utils.Coord;
import models.utils.Direction;
import models.utils.Observable;
import models.utils.Observer;
import models.utils.ParameterParser;
import models.utils.SoundPlayer;
import org.json.simple.parser.ParseException;
import views.Arena;

/**
 * Classe qui permet de representer le jeu
 * @author Erwann
 */
public class GameState extends AbstractModel implements Observable
{
    
    private String previousAction = "";
    
    private Object[][] arena;
    
    private ArrayList<Observer> obs;
    
    private FighterFactory factory;
    
    private ArrayList<Fighter> fighterList;
    
    private int rows;
    
    private AvailableColors colors;
    
    private SoundPlayer player;
    
    private int nbPlayers;
    
    private int damage;
    
    /**
     * Constructeur d'un jeu
     * @param rows
     *  le nombre de lignes,colonnes
     * @param nbPlayers
     *  le nombre de joueurs
     * @param dmg
     *  les degats qu'infligent un tir une bombe ou une mine
     * @param parser 
     *  true si on utiliser le parser, sinon false et on lance le jeu avec des parametres par defaut
     */
    public GameState(int rows,int nbPlayers,int dmg,boolean parser)
    {
        this.nbPlayers = nbPlayers;
        
        this.damage = dmg;
        
        this.colors = new AvailableColors();
        
        this.player = new SoundPlayer();
                
        this.arena = new Object[rows][rows];
        
        this.fighterList = new ArrayList();

        this.factory = new FighterFactory();
        
        this.obs = new ArrayList();
        
        this.rows = rows;
        
        //Si on n'utilise pas le parser on genere tout par defaut
        if(!parser) {
            FighterStrategy balanced = new BalancedStrategy();
            FighterStrategy ag = new AgressiveStrategy();
            FighterStrategy def = new DefensiveStrategy();

            if(this.nbPlayers >= 1) {
                Fighter f1 = this.factory.createFighter("Arthur",'S', "Red",balanced); 
                this.fighterList.add(f1);
            }
            if(this.nbPlayers >= 2) {
                Fighter f2 = this.factory.createFighter("Perceval",'B', "Green",balanced); 
                this.fighterList.add(f2);
            }
            if(this.nbPlayers >= 3) {
               Fighter f3 = this.factory.createFighter("Lancelot",'S', "Blue",balanced); 
               this.fighterList.add(f3);
            }
            if(this.nbPlayers >= 4) {
               Fighter f4 = this.factory.createFighter("Genievre",'G', "Yellow",balanced);  
               this.fighterList.add(f4);
            }    

            MapStrategy corners = new FightersInCorner();
            MapStrategy middle = new FightersInMiddle();
            MapStrategy strat = new WallStrat(10,5);
            MapStrategy stratEnergy = new EnergyStrat(2);

            middle.generateItems(arena, fighterList);
            strat.generateItems(arena,this.fighterList);
            stratEnergy.generateItems(arena,this.fighterList);
        } else {
            
            ParameterParser paramParser = null;
            
            try {
                paramParser = new ParameterParser("resources/parameters.json");
            } catch (IOException | ParseException ex) {
                System.out.println("Error while parsing");
            }
            
            if(paramParser != null)
            {
                HashMap<String,Integer> fighterParamB = paramParser.getFighterParameters('B');
				HashMap<String,Integer> fighterParamS = paramParser.getFighterParameters('S');
				HashMap<String,Integer> fighterParamG = paramParser.getFighterParameters('G');
                HashMap<String,Boolean> mapParam = paramParser.getMapStrategyParameters();
                HashMap<String,Boolean> stratParam = paramParser.getFighterStrategyParameters();
                
                ArrayList<MapStrategy> mapStrats = new ArrayList();
                ArrayList<FighterStrategy> fighterStrats = new ArrayList();
                
                if(stratParam.get("DefensiveStrategy"))
                {
                    fighterStrats.add(new DefensiveStrategy());
                }
                if(stratParam.get("BalancedStrategy"))
                {
                    fighterStrats.add(new BalancedStrategy());
                }
                if(stratParam.get("AgressiveStrategy"))
                {
                    fighterStrats.add(new AgressiveStrategy());
                }
                
                if(mapParam.get("EnergyStrat"))
                {
                    mapStrats.add(new EnergyStrat(2));
                }
                if(mapParam.get("FightersInCorner"))
                {
                    mapStrats.add(new FightersInCorner());
                } else {
                    mapStrats.add(new FightersInMiddle());
                }
                if(mapParam.get("WallStrat"))
                {
                    mapStrats.add(new WallStrat(10,5));
                }
                
                
                 
                 Random r = new Random();
            int whichStrat = 0;
            if(this.nbPlayers >= 1) {
                whichStrat = r.nextInt(fighterStrats.size()-1);
                Fighter f1 = this.factory.createFighter("Arthur",'G', "Red",fighterParamG,fighterStrats.get(whichStrat)); 
                this.fighterList.add(f1);
            }
            if(this.nbPlayers >= 2) {
                Fighter f2 = this.factory.createFighter("Perceval",'B', "Green",fighterParamB,fighterStrats.get(whichStrat)); 
                this.fighterList.add(f2);
            }
            if(this.nbPlayers >= 3) {
               Fighter f3 = this.factory.createFighter("Lancelot",'S', "Blue",fighterParamS,fighterStrats.get(whichStrat)); 
               this.fighterList.add(f3);
            }
            if(this.nbPlayers >= 4) {
               Fighter f4 = this.factory.createFighter("Genievre",'B', "Yellow",fighterParamB,fighterStrats.get(whichStrat));  
               this.fighterList.add(f4);
            }  
            
            for(MapStrategy s : mapStrats)
                {
                    s.generateItems(arena, fighterList);
                }
        }
    }
        
        
        this.notifyObserver(Actions.Action.nothing,null);
    }
    
    
    /**
     * Permet de verifier si les conditions de victoires sont satisfaites
     * @return 
     *  true si le jeu est termine sinon false
     */
    public boolean checkEnd()
    {
        Iterator<Fighter> it = this.fighterList.iterator() ;
        while(it.hasNext()){
            Fighter o = it.next();   
            if(o.getEnergy() <= 0)
            {
                Coord c = this.getElementPosition(o);
                int x = c.getX();
                int y = c.getY();
                this.arena[x][y] = null;
                
                it.remove();
                this.setNbPlayers(this.fighterList.size());
            }
        }    

            
        if(this.fighterList.size() == 1)
        {
            this.previousAction = this.fighterList.get(0).getName()+" won !";
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Permet de lancer une partie qui fait jouer des robots
     */
    public void playRandomIA()
    {
        this.timerBomb();
        
            Random r = new Random();
            int whichFighter = r.nextInt(this.nbPlayers + 1 - 1) + 1; 
            Fighter current = null;
            switch(whichFighter)
            {
                case 1:
                    current = this.fighterList.get(0);
                    break;
                case 2:
                    current = this.fighterList.get(1);
                    break;
                case 3:
                    current = this.fighterList.get(2);
                    break;
                case 4:
                    current = this.fighterList.get(3);
                    break;
            }
            
            Coord currentCoord = this.getElementPosition(current);
            Actions ac = current.getStrat().doAction();
            
            switch(ac.getAction()) 
            {
                case bomb:
                    this.previousAction = current.getName()+ " tried to put a bomb";
                    int bombX = r.nextInt(1 + 1 + 1) - 1; 
                    int bombY = r.nextInt(1 + 1 + 1) - 1;
                    this.putBomb(current, new Coord(currentCoord.getX()+bombX,currentCoord.getY()+bombY));
                    break;
                case move:
                    this.previousAction = current.getName()+ " tried to move";
                    int moveX = r.nextInt(1 + 1 + 1) - 1; 
                    int moveY = r.nextInt(1 + 1 + 1) - 1; 
                    this.checkMoveFighter(current,new Coord(currentCoord.getX()+moveX,currentCoord.getY()+moveY));    
                    break;
                case shoot:
                    this.previousAction = current.getName()+ " tried to shoot";
                    int chooseDir = r.nextInt(this.nbPlayers + 1 - 1) + 1; 
                   
                    switch(chooseDir)
                    {
                        case 1:
                            this.shoot(current,Direction.UP);
                            break;
                        case 2:
                            this.shoot(current,Direction.RIGHT);
                            break;
                        case 3:
                            this.shoot(current,Direction.DOWN);
                            break;
                        case 4:
                            this.shoot(current,Direction.LEFT);
                            break;
                    }
                    break;
                case mine:
                    this.previousAction = current.getName()+ " tried to put a mine";
                    int mineX = r.nextInt(1 + 1 + 1) - 1; 
                    int mineY = r.nextInt(1 + 1 + 1) - 1;
                    this.putMine(current, new Coord(currentCoord.getX()+mineX,currentCoord.getY()+mineY));
                    break;        
                case shield:
                    this.previousAction = current.getName()+ " use shield";
                    this.useShield(current);
                    break;
            }
            this.resetMsg();
            this.resetShield();
            this.checkEnd();
        
    }
    
    /**
     * Permet de reinitialiser le message de l'action precedente
     */
    public void resetMsg()
    {
        this.previousAction = "";
    }
    
    /**
     * Permet de modifier le nombre de joueurs restants
     * @param nb 
     */
    public void setNbPlayers(int nb)
    {
        this.nbPlayers = nb;
    }
    
    /**
     * Permet de desactiver les boucliers a la fin d'un tour
     */
    public void resetShield()
    {
        for(Fighter f : this.fighterList)
        {
            f.setShield(false);
        }
    }
    /**
     * Permet de verifier si un deplacement est possible
     * @param f
     *  le joueur qui veut se deplacer
     * @param coord 
     *  l'emplacement ou il veut se deplacer
     */
    public void checkMoveFighter(Fighter f, Coord coord)
    {
        Coord current = this.getElementPosition(f);
        // Calcul des deltas
        int currentX = current.getX();
        int currentY = current.getY();
        int newX = coord.getX();
        int newY = coord.getY();
        if(Math.abs(currentY - newY) <= 1 && Math.abs(currentX - newX) <= 1
                && Math.abs(currentY - newY) <= 1 && Math.abs(currentX - newX) <= 1
                && newY >= 0 && newX >= 0 && newY < this.rows && newX < this.rows)
        {
            Object ob = this.arena[coord.getX()][coord.getY()];
            
            if(ob != null)
            {
                if(ob instanceof Explosive)
                {
                    if(ob instanceof Bomb)
                    {
                        this.bomb((Bomb) ob);
                    }
                    if(ob instanceof Mine)
                    {
                        this.mine((Mine) ob);
                    }
                    this.moveFighter(f, coord);
                }
                if(!(ob instanceof Wall) && !(ob instanceof Fighter))
                {
                    this.moveFighter(f, coord);
                }
            } else {
                this.moveFighter(f, coord);
            }
        } else {
            System.out.println("I can't move, X : "+currentX+",Y : "+currentY);
        }
    }
    
    /**
     * Permet d'activer le bouclier
     * @param f 
     *  le joueur qui a activer son bouclier
     */
    public void useShield(Fighter f)
    {
        f.setShield(true);
    }
    
    /**
     * Permet de deposer une mine
     * @param f
     *  le joueur qui depose la mine
     * @param c 
     *  l'emplacement ou il veut deposer la mine
     */
    public void putMine(Fighter f,Coord c)
    {
        if(c.getX() >= 0 && c.getX() < this.rows && c.getY() >= 0 && c.getY() < this.rows ){
            if(f.getNb_mine() < 1)
            {
                this.previousAction = f.getName()+" has no longer bomb";
            } else if(this.arena[c.getX()][c.getY()] == null){
                Explosive m = new Mine(f);
                this.arena[c.getX()][c.getY()] = m;
                f.setNb_mine(f.getNb_mine() - 1);
                f.setEnergy(f.getEnergy() - this.damage);
            }
            this.notifyObserver(Actions.Action.nothing, f);
        }
        
    }
    
    /**
     * Permet de deposer une bombe
     * @param f
     *  le joueur qui depose la bombe
     * @param c 
     *  l'emplacement ou la bombe doit etre deposee
     */
    public void putBomb(Fighter f,Coord c)
    {
        if(c.getX() >= 0 && c.getX() < this.rows && c.getY() >= 0 && c.getY() < this.rows )
        {
            if(this.arena[c.getX()][c.getY()] == null && f.getNb_bomb() < 1)
            {
            } else if(this.arena[c.getX()][c.getY()] == null){
                Explosive b = new Bomb(f);
                this.arena[c.getX()][c.getY()] = b;
                f.setNb_bomb(f.getNb_bomb() - 1);
                f.setEnergy(f.getEnergy() - this.damage);
            }
            this.notifyObserver(Actions.Action.nothing, f);
        }    
    }
    
    /**
     * Permet de deplacer un joueur
     * @param f
     *  le joueur a deplacers
     * @param coord 
     *  la nouvelle coordonnee
     */
    public void moveFighter(Fighter f,Coord coord)
    {
        Coord current = this.getElementPosition(f);
        Object element = this.arena[coord.getX()][coord.getY()];
        if(element instanceof Bomb) 
        {
            this.bomb((Bomb) element);
        }
        else if(element instanceof Mine) 
        {
            this.mine((Mine) element);
        }
        else if(element instanceof EnergyShard)
        {
            this.heal(f);
        }; 
        this.arena[current.getX()][current.getY()] = null;
        this.arena[coord.getX()][coord.getY()] = f;
        f.setEnergy(f.getEnergy() - this.damage);
        this.notifyObserver(Actions.Action.nothing, f);
    }

    /**
     * Permet de soigner un joueur
     * @param f 
     *  le joueur a soigner
     */
    public void heal(Fighter f)
    {
        f.setEnergy(f.getEnergy()+50);
        Coord coord = this.getElementPosition(f);
        this.arena[coord.getX()][coord.getY()] = null;
        this.notifyObserver(Actions.Action.nothing,f);
        this.player.playSound(Actions.Action.heal);
    }
    
    /**
     * Cette fonction va permettre de tirer selon une direction et va frapper le premier combattant
     * rencontre 
     * @param shooter
     * @param dir 
     */
    public void shoot(Fighter shooter,Direction dir)
    {
        this.notifyObserver(Actions.Action.shoot,shooter);
        this.resetMsg();
        player.playSound(Actions.Action.shoot);
        Coord fighterPosition = this.getElementPosition(shooter);
        int fighterX = fighterPosition.getX();
        int fighterY = fighterPosition.getY();
        switch(dir)
        {
            case UP:
                for(int y = fighterY-1; y >= 0; y--) 
                {
                    
                    Object e = this.arena[fighterX][y];
                    if(e instanceof Fighter)
                    {
                        ((Fighter) e).setEnergy(((Fighter) e).getEnergy() - this.damage);
                        break;
                    }
                    else if(e instanceof Wall)
                    {
                        System.out.println("Nice one, you shooted a wall !");
                        break;
                    }
                }
                break;
            case DOWN:
                for(int y = fighterY+1; y < this.rows; y++) 
                {
                    Object e = this.arena[fighterX][y];
                    if(e instanceof Fighter)
                    {
                        ((Fighter) e).setEnergy(((Fighter) e).getEnergy() - this.damage);
                        break;
                    }
                    else if(e instanceof Wall)
                    {
                        System.out.println("Nice one, you shooted a wall !");
                        break;
                    }
                }
                break;
            case RIGHT:
                for(int x = fighterX+1; x < this.rows; x++) 
                {
                    Object e = this.arena[x][fighterY];
                    if(e instanceof Fighter)
                    {
                        ((Fighter) e).setEnergy(((Fighter) e).getEnergy() - this.damage);
                        break;
                    }
                    else if(e instanceof Wall)
                    {
                        System.out.println("Nice one, you shooted a wall !");
                        break;
                    }
                    
                }
                break;
            case LEFT:
                for(int x = fighterX-1; x >= 0; x--) 
                {
                    Object e = this.arena[x][fighterX];
                    if(e instanceof Fighter)
                    {
                        ((Fighter) e).setEnergy(((Fighter) e).getEnergy() - this.damage);
                        break;
                    }else if(e instanceof Wall)
                    {
                        System.out.println("Nice one, you shooted a wall !");
                        break;
                    }
                    
                }
                break;
        }
        try {    
            Thread.sleep(1000);                  
        } catch (InterruptedException ex) {
            System.out.println("Can't Stop");        
        }
        shooter.setNb_shot(shooter.getNb_shot()-1);
        shooter.setEnergy(shooter.getEnergy() - this.damage);
        this.notifyObserver(Actions.Action.nothing,shooter);
    }
    
    /**
     * Permet de faire exploser une bombe
     * @param b 
     *  la bombe a faire exploser
     */
    public void bomb(Bomb b){       
        this.notifyObserver(Actions.Action.bomb,b);
        this.resetMsg();
        player.playSound(Actions.Action.bomb);
        try {    
            Thread.sleep(1000);                  
        } catch (InterruptedException ex) {
            System.out.println("Error while sleep");
        }
        //Parcours des cases voisines
        Coord coord = this.getElementPosition(b);
        ArrayList<Object> elements = this.getVoisins(coord);
        for(Object e : elements)
        {
            if(e instanceof Fighter)
            {
                ((Fighter) e).setEnergy(((Fighter) e).getEnergy() - this.damage);
            }
        }
        b.getFighter().setEnergy(b.getFighter().getEnergy() - b.getCost());
        this.arena[coord.getX()][coord.getY()] = null;
        this.notifyObserver(Actions.Action.nothing,b);
    }
    
    /**
     * Permet de recuperer les coordonnes des 8 cases voisines
     * @param coord
     *  la case dont on veut recuperer les voisines
     * @return 
     *  la liste des coordonnes des cases voisines
     */
    public ArrayList getVoisins(Coord coord)
    {
        //Parcours des cases voisines
        ArrayList<Object> elements = new ArrayList();
        int x = coord.getX();
        int y = coord.getY();
        if(y - 1 <= 0)
        {
           Object top = this.arena[x][y-1]; 
           elements.add(top);
           if(x - 1 <= 0)
           {
                Object topLeft = this.arena[x-1][y-1];
                elements.add(topLeft);
           }
           if( x + 1 <= this.rows)
           {
               Object topRight = this.arena[x+1][y-1];
               elements.add(topRight);
           }  
        }
        
        if(y + 1 <= this.rows) 
        {
            Object bot = this.arena[x][y+1];
            elements.add(bot);
            if(x - 1 <= 0)
            {
                Object botLeft = this.arena[x-1][y+1];
                elements.add(botLeft);
            }
            if( x + 1 <= this.rows)
            {
                Object botRight = this.arena[x+1][y+1];
                elements.add(botRight);
            }
        }
        
        if(x - 1 <= 0)
        {
            Object left = this.arena[x-1][y];
            elements.add(left);
        }
        
        if(x + 1 <= this.rows) 
        {
            Object right = this.arena[x+1][y];
            elements.add(right);
        }  
        return elements;
    }
    
    public void mine(Mine m){
        this.notifyObserver(Actions.Action.bomb,m);
        player.playSound(Actions.Action.bomb);
        try {    
            Thread.sleep(2000);                  
        } catch (InterruptedException ex) {
            System.out.println("Can't Stop");
        }
        Coord coord = this.getElementPosition(m);
        this.arena[coord.getX()][coord.getY()] = null;
        this.notifyObserver(Actions.Action.nothing,m);
    }
    
    
    public FighterFactory getFactory() 
    {
        return this.factory;
    }
    
    /**
     * Permet d'afficher le contenu de la grille en mode console
     */
    public void display()
    {
        for(int y = 0; y < this.rows; y++) 
        {
             for(int x = 0; x < this.rows; x++) 
            {
                System.out.println(this.arena[x][y]+" in "+(x+","+y));  
            }
        }
    }
    
    /**
     * Permet de decrementer le nombre de tour avant qu'une bombe explose
     */
    public void timerBomb()
    {
        for(int y = 0; y < this.rows; y++)
        {
            for(int x = 0; x < this.rows; x++)
            {
                if(this.getArena()[x][y] instanceof Bomb)
                {
                    Bomb bomb = (Bomb) this.getArena()[x][y];
                    bomb.addTour();
                    if(bomb.getTour() == 0)
                    {   
                        this.bomb(bomb);
                    }
                }
            }
        }
    }
    
    /**
     * Permet de retourner les coordonnees X et Y de l'element
     * @param element
     *  l'element dont on veut les coordonnes
     * @return
     * Retourne les coordonnes de l'element
     */
    public Coord getElementPosition(Object element)
    {
        for(int y = 0; y < this.arena.length; y++)
        {
            for(int x = 0; x < this.arena.length; x++)
            {
                if(this.arena[x][y] == element)
                {
                    return new Coord(x,y);
                }
            }
        }
        return null;
    }
    
    /**
     * Permet d'ajouter un observer
     * @param o 
     *  l'observeur a ajouter
     */
    @Override
    public void addObserver(Observer o) {
        this.obs.add(o);
        
    }
    
    /**
     * Permet de supprimer un observer
     * @param o 
     *  l'observer a supprimer
     */
    @Override
    public void removeObserver(Observer o) {
        this.obs.remove(o);
    }
    
    /**
     * Permet de notifier tous les observers d'un changement possiblement accompagne d'une animations
     * @param anim
     *  l'animation a jouer si il y en a une
     * @param f 
     *  le joueur ou l'objet qui doit realiser l'animation
     */
    @Override
    public void notifyObserver(Actions.Action anim, Object f) {
        for(Observer ob : this.obs) {
            ob.update(anim,f);
        }
    }
    
    
    // GUETTERS
    @Override
    public Object[][] getArena()
    {
        return this.arena;
    }
    
    public ArrayList<Fighter> getFighters()
    {
        return this.fighterList;
    }
    
    public void setFighterList(ArrayList<Fighter> f)
    {
        this.fighterList = f;
    }
    
    public String getPreviousAction()
    {
        return this.previousAction;
    }
    
}
