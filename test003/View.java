import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class View extends JPanel implements KeyListener {
    
    private final World world = new World();
    
    //private final Face[] cube1;
    //private final Face[] cube2;
    //private final Face[] cube3;

    private static final int FLOOR_SIZE = 6;
    private Face[] floor = new Face[FLOOR_SIZE * FLOOR_SIZE];

    private Light light;
    private Light light2;
    private Vec3 lightCenter = new Vec3();
    private double angle = 245.0;
    private Light light3;

    public View() {
    
        //for (int i = 0; i < floor.length; i++) {
        //    int c = i % FLOOR_SIZE;
        //    int r = i / FLOOR_SIZE;
        //    floor[i] = world.addFace(c - 2.5, -1.5, r - FLOOR_SIZE, Face.ORIENTATION.TOP);
        //}

        Level.generate(world, -5, -1.5, -5);
        
        //cube1 = world.addCube(0.95, -0.25, -3);
        //cube2 = world.addCube(-1.0, 0.5, -4);
        //cube3 = world.addCube(4.5, 2.5, -7);
        
        light = world.addLight(0.0, 0.25, -3.4, 1.0, 1.0, 1.0, 5.0);
        light2 = world.addLight(4, 6, -0.5, 1.0, 0.0, 0.0, 10.0);
        lightCenter.set(0.0, 0.05, -3.5);
        light3 = world.addLight(-6.5, 5, 8, 1.0, 0.0, 0.0, 20.0);

        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {

        //angle += 0.1;
        //double dx = 2.5 * Math.cos(angle);
        //double dy = 0.15 * Math.sin(angle * 2.317) - 0.12 * Math.cos(angle * 2.475);
        //double dz = 3 * Math.sin(angle);
        //light.position.x = lightCenter.x + dx;
        //light.position.y = lightCenter.y + dy;
        //light.position.z = lightCenter.z + dz;
        
        //g.drawLine(0, 0, 800, 600);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        g2d.translate(0, getHeight());
        g2d.scale(1, -1);

        world.render(g2d);

        /*
        for (int i = 0; i < cube1.length; i++) {
            world.processFaceLightMap(g2d, cube1[i]);
        }
        for (int i = 0; i < cube2.length; i++) {
            world.processFaceLightMap(g2d, cube2[i]);
        }

        for (int i = 0; i < floor.length; i++) {
            world.processFaceLightMap(g2d, floor[i]);
        }    
         */
        try {
            Thread.sleep(1000 / 30);
        } catch (InterruptedException e) { }

        //repaint();
    }

    // key handler 

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            light.position.x -= 0.1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_D) {
            light.position.x += 0.1;
        }

        if (e.getKeyCode() == KeyEvent.VK_Q) {
            light.position.y -= 0.1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_E) {
            light.position.y += 0.1;
        }

        if (e.getKeyCode() == KeyEvent.VK_W) {
            light.position.z -= 0.1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            light.position.z += 0.1;
        }

        if (e.getKeyCode() == KeyEvent.VK_0) {
            world.bakeLight();
        }
        else {
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
