
package boom;

import controllers.AbstractController;
import controllers.IAController;
import models.gamestate.AbstractModel;
import models.gamestate.GameState;
import models.gamestate.GameStateProxy;
import models.utils.Actions;
import models.utils.JTableModel;
import views.Arena;

/**
 * <b>Classe principale et executable de notre application.</b>
 * C'est aussi que l'on vient instancier le modèle, la Vue et le Controlleur. 
 */
public class Boom {

    private static final int players = 4;
    
    private static final int rowsOrCols = 10;
    
    private static final int dmg = 20;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        GameState model = null;
        
		if(args.length >= 1)
		{
				if(args[0].equals("parser"))
				{
					model = new GameState(rowsOrCols,players,dmg,true);
				}
        }else {
			
					model = new GameState(rowsOrCols,players,dmg,false);
				}
        
        JTableModel m = new JTableModel(model.getFighters());
        
        AbstractController controler = new IAController(model);
        
        Arena mainArena = new Arena(model,controler,true,m);
        model.addObserver(mainArena);
        
        if(players >= 1) {
            AbstractModel p1 = new GameStateProxy(model.getFighters().get(0),model);   
            Arena arena = new Arena(p1,controler,false,m);
            model.addObserver(arena);
        }
        if(players >= 2) {
            AbstractModel p2 = new GameStateProxy(model.getFighters().get(1),model);
            Arena arena1 = new Arena(p2,controler,false,m);
            model.addObserver(arena1);
        }
        if(players >= 3) {
            AbstractModel p3 = new GameStateProxy(model.getFighters().get(2),model);
            Arena arena2 = new Arena(p3,controler,false,m);
            model.addObserver(arena2);
        }
        if(players >= 4) {
            AbstractModel p4 = new GameStateProxy(model.getFighters().get(3),model);
            Arena arena3 = new Arena(p4,controler,false,m);
            model.addObserver(arena3);
        }    
        
        model.notifyObserver(Actions.Action.nothing, null);
    }   
}
