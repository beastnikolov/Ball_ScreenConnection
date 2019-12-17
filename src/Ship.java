import java.awt.*;

public class Ship implements Runnable {
    private int ship_id;
    private String ship_name;
    private int shipX;
    private int shipY;
    private Color shipColor;
    private int shipSpeedX;
    private int shipSpeedY;
    private Viewer viewer;
    private boolean flying = true;
    private static int sleep = 10;

    public Ship(int ship_id, String ship_name,int shipX, int shipY, Color shipColor,int shipSpeedX,int shipSpeedY,Viewer viewer) {
        this.ship_id = ship_id;
        this.ship_name = ship_name;
        this.shipX = shipX;
        this.shipY = shipY;
        this.shipColor = shipColor;
        this.shipSpeedX = shipSpeedX;
        this.shipSpeedY = shipSpeedY;
        this.viewer = viewer;
    }


    @Override
    public void run() {
        while (flying) {
            moveShip(sleep);
        }
    }

    public void drawShip(Graphics g) {
        g.setColor(shipColor);
        g.fillOval(shipX,shipY,15,15);
        g.setColor(Color.white);
        g.drawString(ship_name,shipX-10,shipY-5);
    }

    public void moveShip(int Sleep) {
        if (shipSpeedY == 0 || shipSpeedX == 0) {
            shipSpeedX = -2;
            shipSpeedY = -3;
        }
        shipX = shipX + shipSpeedX;
        shipY = shipY + shipSpeedY;
        if (shipX <= 0 ){
            shipSpeedX =- shipSpeedX;
        }
        if (shipY <= 0) {
            shipSpeedY =- shipSpeedY;
        }
        if ((shipX + 15) >= 640) {
            shipSpeedX =- shipSpeedX;
        }
        if ((shipY + 15) >= 480) {
            shipSpeedY =- shipSpeedY;
        }
        try {
            Thread.sleep(Sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
