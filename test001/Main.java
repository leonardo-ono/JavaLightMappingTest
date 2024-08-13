import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            View view = new View();
            JFrame frame = new JFrame("Direct Light Mapping - Test #1");
            frame.getContentPane().add(view);
            view.setPreferredSize(new Dimension(800, 600));
            view.setBackground(Color.WHITE);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            view.requestFocus();
        });
    }

    private static void test() {
        System.out.println("test");
        
        Vec3 va = new Vec3(-6.16127, -12.66271, 5);
        Vec3 vb = new Vec3(8.89428, 6.52529, 5);
        Vec3 vc = new Vec3(-12.14329, 14.64377, 2);
        Vec3 la = new Vec3(-8.97735, 11.05687, 10);
        Vec3 lb = new Vec3(5.91096, 9.21557, -3);
        Vec3 ip = new Vec3();

        boolean intersects = Geom.getIntersectionPoint(va, vb, vc, la, lb, ip);

        System.out.println(intersects + " ip=" + ip);
    }

}