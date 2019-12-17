import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Viewer extends Canvas implements Runnable {
    private static int ship_id = 1;
    private boolean go = true;
    private Thread thread;
    private Ship ship;
    private ArrayList<Ship> shipArrayList = new ArrayList<>();



    public Viewer() {
    }

    @Override
    public void run() {
        init();
        while (go) {
            paint();
        }
    }

    private void init() {
        this.createBufferStrategy(2);
       // createShip();
    }

    private void paint() {
        BufferStrategy bs;
        Graphics graphics;


        bs = this.getBufferStrategy();
        if (bs == null) {
            return;
        }
        graphics = bs.getDrawGraphics();
        for (Ship s: shipArrayList) {
            s.drawShip(graphics);
        }
        bs.show();
        super.paint(graphics);
        graphics.dispose();
    }

    private void createShip() {
        ship = new Ship(1,"127.0.0.1",200,200,Color.red,-2,-3,this);
        thread = new Thread(ship);
        thread.start();
        shipArrayList.add(ship);
    }

    public ArrayList<Ship> getShipArrayList() {
        return shipArrayList;
    }

    public void setShipArrayList(ArrayList<Ship> shipArrayList) {
        this.shipArrayList = shipArrayList;
    }

    public void createClientShip(SimpleShip simpleShip) {
        ship = new Ship(ship_id,simpleShip.getShip_name(),simpleShip.getShipX(),simpleShip.getShipY(),simpleShip.getShipColor(),-2,-3,this);
        thread = new Thread(ship);
        thread.start();
        shipArrayList.add(ship);
        ship_id++;
    }
}
