package com.nhlstenden.amazonsimulatie.models;

import com.nhlstenden.amazonsimulatie.base.Grid;
import com.nhlstenden.amazonsimulatie.base.Idle;
import com.nhlstenden.amazonsimulatie.base.Tile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/*
 * Deze class is een versie van het model van de simulatie. In dit geval is het
 * de 3D wereld die we willen modelleren (magazijn). De zogenaamde domain-logic,
 * de logica die van toepassing is op het domein dat de applicatie modelleerd, staat
 * in het model. Dit betekent dus de logica die het magazijn simuleert.
 */
public class World implements Model {
    /*
     * De wereld bestaat uit objecten, vandaar de naam worldObjects. Dit is een lijst
     * van alle objecten in de 3D wereld. Deze objecten zijn in deze voorbeeldcode alleen
     * nog robots. Er zijn ook nog meer andere objecten die ook in de wereld voor kunnen komen.
     * Deze objecten moeten uiteindelijk ook in de lijst passen (overerving). Daarom is dit
     * een lijst van Object3D onderdelen. Deze kunnen in principe alles zijn. (Robots, vrachrtwagens, etc)
     */
    private List<Object3D> worldObjects;
    private ArrayList<Robot> robots;
    private ArrayList<Stelling> stellingList;
    private List<Tile> tiles;
    /*
     * Dit onderdeel is nodig om veranderingen in het model te kunnen doorgeven aan de controller.
     * Het systeem werkt al as-is, dus dit hoeft niet aangepast te worden.
     */
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private Idle idle;


    public World() {
        this.worldObjects = new ArrayList<>();
        this.robots = new ArrayList<>();
        this.stellingList = new ArrayList<>();
        com.nhlstenden.amazonsimulatie.base.Map map = new com.nhlstenden.amazonsimulatie.base.Map();
        for(int i = 0; i < 6; i++) {
            // Robot
            Robot robot = new Robot(map);
            com.nhlstenden.amazonsimulatie.models.Stelling stelling = new com.nhlstenden.amazonsimulatie.models.Stelling(map,robots);
            robot.SetMap(map);
            this.worldObjects.add(robot);
            this.robots.add(robot);
            this.worldObjects.add(stelling);
            this.stellingList.add(stelling);


            //loopt over lijsten van stellingen en robots heen en zet begin posities en doelen
            stellingList.get(i).setX((map.storage.get(5).getX() * 3) - 1.5);
            stellingList.get(i).setZ((map.storage.get(5).getX() * 3) - 1.5);
            robots.get(i).stellings = stellingList;
            robots.get(i).assignedStelling = stellingList.get(i);
            robots.get(i).SetDestinationX(map.storage.get(10).getX());
            robots.get(i).SetDestinationZ(map.storage.get(1).getZ());


        }


    }

    /*
     * Deze methode wordt gebruikt om de wereld te updaten. Wanneer deze methode wordt aangeroepen,
     * wordt op elk object in de wereld de methode update aangeroepen. Wanneer deze true teruggeeft
     * betekent dit dat het onderdeel daadwerkelijk geupdate is (er is iets veranderd, zoals een positie).
     * Als dit zo is moet dit worden geupdate, en wordt er via het pcs systeem een notificatie gestuurd
     * naar de controller die luisterd. Wanneer de updatemethode van het onderdeel false teruggeeft,
     * is het onderdeel niet veranderd en hoeft er dus ook geen signaal naar de controller verstuurd
     * te worden.
     */
    @Override
    public void update() {
        for (Object3D object : this.worldObjects) {
            if (object instanceof Updatable) {
                if (((Updatable) object).update()) {
                    pcs.firePropertyChange(Model.UPDATE_COMMAND, null, new ProxyObject3D(object));
                }
            }
        }
    }

    /*
     * Standaardfunctionaliteit. Hoeft niet gewijzigd te worden.
     */
    @Override
    public void addObserver(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    /*
     * Deze methode geeft een lijst terug van alle objecten in de wereld. De lijst is echter wel
     * van ProxyObject3D objecten, voor de veiligheid. Zo kan de informatie wel worden gedeeld, maar
     * kan er niks aangepast worden.
     */
    @Override
    public List<Object3D> getWorldObjectsAsList() {
        ArrayList<Object3D> returnList = new ArrayList<>();

        for(Object3D object : this.worldObjects) {
            returnList.add(new ProxyObject3D(object));
        }

        return returnList;
    }

    /*
     *  This method creates tiles for the robots and racks to ride and stand on.
     */
    public List<Tile> CreateTiles(){
        List<Tile> tiles = new ArrayList<>();
        for(int i = 0; i<50; i++){
            tiles.add(new Tile(i,i));
        }
        return tiles;
    }
}