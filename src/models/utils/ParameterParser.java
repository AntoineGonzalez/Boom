package models.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *<b>Classe ParrameterParser qui permet d'instancier un objet qui va parcourir un fichier de parametre au format JSON.</b>
 * Un parameterParser se caractérise par:
 * <ul>
 * <li>Un JSONParser, objet permetant de parsé/decomposé un fichier JSON .</li>
 * <li>Un JSONObject, objet qui va représenter le contenue parsé/décomposé d'un fichier JSON.</li>
 * </ul>
 * @author antoine
 */
public class ParameterParser{
    JSONParser parse = new JSONParser();
    JSONObject jsonObject;
    
    /**
     * Constructeur ParameterParser.
     * <p>
     * A la construction d'un objet ParameterParser on vient lire le contenue du fichier passer en paramettre.
     * Puis on le parse avec le JSONParser pour créer un JSONObject permettant de manipuler notre fichier de manière décomposé/parsé.
     * </p>
     * 
     * @param pFichier String contenant le chemin du fichier de paramètre JSON. 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException 
     */
    public ParameterParser(String pFichier) throws FileNotFoundException, IOException, ParseException{
        Object obj=this.parse.parse(new FileReader(pFichier));
        this.jsonObject = (JSONObject) obj;
    }
    
    /**
     * Méthode qui permet de récupérer les paramètre lié à un fighter en fonction de son type sous le forme
     * d'un dictionnaire associant un parametre à sa valeur.
     * 
     * @param pType Charactère représentant le type de fighter (exemple: 'B' = "Bomber") 
     * @return dictionnaire associant un parametre à sa valeur
     */
    public HashMap<String,Integer> getFighterParameters(char  pType){
        HashMap<String,Integer> res =new HashMap<>();
        JSONObject mapSection = (JSONObject) jsonObject.get("Fighter");
        mapSection.keySet().forEach((Object paramKey) -> { //on parcours chaque paramètre.
            JSONObject subMapSection = (JSONObject) mapSection.get(paramKey);  
            subMapSection.keySet().forEach((Object type) -> { //on parcours chaque type présent dans le fichier de paramètre(Bombre,Shooter,Gardian).
                if(type.toString().charAt(0)==pType){ //On vérifie que le type est égale à pType passé en paramètre.
                    Integer paramValue = Integer.parseInt((String) subMapSection.get(type));
                    res.put((String) paramKey,paramValue); // on ajoute le paramètre au dictionnaire.
                }
            });
        });
        return res;
    }
    
    /**
     * Méthode qui permet de savoir quelles stratégies de générations de map sont active.
     * @return Un dictionnaire associant une stratégie(représanté par un String)  à un booléen. Si le booléen vaut "False" alors la stratégie n'est pas active.
     */
    public HashMap<String,Boolean> getMapStrategyParameters(){
        HashMap<String,Boolean> res =new HashMap<>();
        JSONObject mapSection = (JSONObject) jsonObject.get("MapStrategy");
        mapSection.keySet().forEach((Object paramKey) -> { // on parcours chaque stratégie
           boolean paramValue = (boolean)mapSection.get(paramKey); // on récupére sa valeur de vérité
           String paramKeyString= (String) paramKey;
           res.put(paramKeyString, paramValue); // on ajoute au dictionnaire
        });
        
        return res;
    }
    
    /**
     * Méthode qui permet de savoir quelles stratégies sont active parmis c'elles qui définissent le comportement des fighters.
     * @return Un dictionnaire associant une stratégie(représanté par un String) à un booléen. Si le booléen vaut "False" alors la stratégie n'est pas active.
     */
    public HashMap<String,Boolean> getFighterStrategyParameters(){
        HashMap<String,Boolean> res =new HashMap<>();
        JSONObject mapSection = (JSONObject) jsonObject.get("FighterStrategy");
        mapSection.keySet().forEach((Object paramKey) -> {
           boolean paramValue = (boolean)mapSection.get(paramKey);
           String paramKeyString= (String) paramKey;
           res.put(paramKeyString, paramValue);
        });
        return res;
    }
    
    /**
     * Méthode qui permet de récupérer le nombre d'élément à générer dans notre aréne.
     * @return Un dictionnaire associant un élément(représanté par un String) à son nombre. 
     */
    public HashMap<String,Integer> getElementNumber(){
        HashMap<String,Integer> res =new HashMap<>();
        JSONObject mapSection = (JSONObject) jsonObject.get("ElementNumber");
        mapSection.keySet().forEach((Object paramKey) -> {
           Integer paramValue = Integer.parseInt((String)mapSection.get(paramKey));
           String paramKeyString= (String) paramKey;
           res.put(paramKeyString, paramValue);
        });
        return res;
    }
}