import java.awt.*;

public class SimpleShip {
    private String ship_name;
    private int shipX;
    private int shipY;
    private Color shipColor;

    public SimpleShip(String ship_name, int shipX, int shipY,Color shipColor) {
        this.ship_name = ship_name;
        this.shipX = shipX;
        this.shipY = shipY;
        this.shipColor = shipColor;
    }

    public String getShip_name() {
        return ship_name;
    }

    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }

    public int getShipX() {
        return shipX;
    }

    public void setShipX(int shipX) {
        this.shipX = shipX;
    }

    public int getShipY() {
        return shipY;
    }

    public void setShipY(int shipY) {
        this.shipY = shipY;
    }

    public Color getShipColor() {
        return shipColor;
    }

    public void setShipColor(Color shipColor) {
        this.shipColor = shipColor;
    }
}
