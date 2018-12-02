import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.util.*;


public class Sector extends Applet implements Runnable {

    final static int WIDTH = 150 , HEIGHT = 150;
    static int radius;
    int angle = 0;
    Thread runner = null;

    public Color getHtmlColor( String strRGB, Color def ) {
        if ( strRGB != null && strRGB.charAt(0)== '#' ) {
            try {
                return new Color(
                        Integer.parseInt( strRGB.substring( 1 ), 16 ) );
            } catch ( NumberFormatException e ) {
                return def;
            }
        }
        return def;
    }
    public void init(){
        setSize(WIDTH,HEIGHT);
        Color background = getHtmlColor(getParameter( "AppBkColor" ), new Color(24, 160, 139));
        setBackground( background );

        if(WIDTH < HEIGHT)
            radius =(WIDTH - 30) / 2;
        else
            radius =(HEIGHT - 30) / 2;

        runner = new Thread(this);
        runner.start();
    }
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.rotate(Math.toRadians(angle),radius+15,radius+15);
        Color sectorColor = getHtmlColor(getParameter( "DrawBkColor" ), new Color(19, 91, 53));
        g2.setColor(sectorColor);
        g2.fillArc(15, 15,WIDTH - 30,HEIGHT - 30, 0,45);

        angle++;
        if(angle > 360)
            angle -= 360;
    }

    public void run(){
        while (true){
            repaint();
            try{
                Thread.sleep(20);
            }
            catch (InterruptedException e){
                break;
            }
        }
    }
}