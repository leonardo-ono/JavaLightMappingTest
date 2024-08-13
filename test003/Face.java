import java.awt.image.BufferedImage;

public class Face {

    // 16x16
    public static final int LIGHT_MAP_SIZE = 32;

    public enum ORIENTATION { FRONT, BACK, LEFT, RIGHT, TOP, BOTTOM };
    
    public final Vec3[] vertices = new Vec3[4];
    public final Vec3[] uvs = new Vec3[4];
    public final Vec3 normal = new Vec3();

    // [row][col]
    public final Vec3[][] lightMap = new Vec3[LIGHT_MAP_SIZE][LIGHT_MAP_SIZE];
    private final BufferedImage lightMapImage = new BufferedImage(LIGHT_MAP_SIZE, LIGHT_MAP_SIZE, BufferedImage.TYPE_INT_RGB);        

    // texture to world space conversion
    public final Vec3 wp = new Vec3();
    public final Vec3 wXAxis = new Vec3();
    public final Vec3 wYAxis = new Vec3();

    public Face(Vec3 p, ORIENTATION orientation) {
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vec3();
            uvs[i] = new Vec3();
        }

        for (int row = 0; row < LIGHT_MAP_SIZE; row++) {
            for (int col = 0; col < LIGHT_MAP_SIZE; col++) {
                lightMap[row][col] = new Vec3();
            }
        }

        switch (orientation) {
            case FRONT -> {
                vertices[0].set(0.5, 0.5, 0.5);
                vertices[1].set(-0.5, 0.5, 0.5);
                vertices[2].set(-0.5, -0.5, 0.5);
                vertices[3].set(0.5, -0.5, 0.5);
                normal.set(0, 0, 1);
                wp.set(vertices[1]);
                wp.add(p);
                wXAxis.set(1, 0, 0);
                wYAxis.set(0, -1, 0);
                uvs[0].set(0.99, 0.99, 0);
                uvs[1].set(0.01, 0.99, 0);
                uvs[2].set(0.01, 0.01, 0);
                uvs[3].set(0.99, 0.01, 0);
            }

            case BACK -> {
                vertices[0].set(0.5, -0.5, -0.5);
                vertices[1].set(-0.5, -0.5, -0.5);
                vertices[2].set(-0.5, 0.5, -0.5);
                vertices[3].set(0.5, 0.5, -0.5);
                normal.set(0, 0, -1);
                wp.set(vertices[3]);
                wp.add(p);
                wXAxis.set(-1, 0, 0);
                wYAxis.set(0, -1, 0);
                uvs[0].set(0.01, 0.01, 0);
                uvs[1].set(0.99, 0.01, 0);
                uvs[2].set(0.99, 0.99, 0);
                uvs[3].set(0.01, 0.99, 0);
            }

            case LEFT -> {
                vertices[0].set(-0.5, 0.5, 0.5);
                vertices[1].set(-0.5, 0.5, -0.5);
                vertices[2].set(-0.5, -0.5, -0.5);
                vertices[3].set(-0.5, -0.5, 0.5);
                normal.set(-1, 0, 0);
                wp.set(vertices[1]);
                wp.add(p);
                wXAxis.set(0, 0, 1);
                wYAxis.set(0, -1, 0);
                uvs[0].set(0.99, 0.99, 0);
                uvs[1].set(0.01, 0.99, 0);
                uvs[2].set(0.01, 0.01, 0);
                uvs[3].set(0.99, 0.01, 0);
            }
            
            case RIGHT -> {
                vertices[0].set(0.5, 0.5, -0.5);
                vertices[1].set(0.5, 0.5, 0.5);
                vertices[2].set(0.5, -0.5, 0.5);
                vertices[3].set(0.5, -0.5, -0.5);
                normal.set(1, 0, 0);
                wp.set(vertices[1]);
                wp.add(p);
                wXAxis.set(0, 0, -1);
                wYAxis.set(0, -1, 0);
                uvs[0].set(0.99, 0.99, 0);
                uvs[1].set(0.01, 0.99, 0);
                uvs[2].set(0.01, 0.01, 0);
                uvs[3].set(0.99, 0.01, 0);
            }

            case TOP -> {
                vertices[0].set(0.5, 0.5, 0.5);
                vertices[1].set(0.5, 0.5, -0.5);
                vertices[2].set(-0.5, 0.5, -0.5);
                vertices[3].set(-0.5, 0.5, 0.5);
                normal.set(0, 1, 0);
                wp.set(vertices[2]);
                wp.add(p);
                wXAxis.set(1, 0, 0);
                wYAxis.set(0, 0, 1);
                uvs[0].set(0.99, 0.01, 0);
                uvs[1].set(0.99, 0.99, 0);
                uvs[2].set(0.01, 0.99, 0);
                uvs[3].set(0.01, 0.01, 0);
            }

            case BOTTOM -> {
                vertices[0].set(-0.5, -0.5, 0.5);
                vertices[1].set(-0.5, -0.5, -0.5);
                vertices[2].set(0.5, -0.5, -0.5);
                vertices[3].set(0.5, -0.5, 0.5);
                normal.set(0, -1, 0);
                wp.set(vertices[0]);
                wp.add(p);
                wXAxis.set(1, 0, 0);
                wYAxis.set(0, 0, -1);
                uvs[0].set(0.01, 0.99, 0);
                uvs[1].set(0.01, 0.01, 0);
                uvs[2].set(0.99, 0.01, 0);
                uvs[3].set(0.99, 0.99, 0);
            }
        }
        vertices[0].add(p);
        vertices[1].add(p);
        vertices[2].add(p);
        vertices[3].add(p);
    }


    private final Vec3 wa = new Vec3();
    private final Vec3 wb = new Vec3();

    // +---> x
    // |
    // v
    // y
    public void getLightTexelWorldSpace(int x, int y, Vec3 wv) {
        double rx = (x + 0.5) / LIGHT_MAP_SIZE;
        double ry = (y + 0.5) / LIGHT_MAP_SIZE;
        wa.set(wXAxis);
        wa.scale(rx);
        wb.set(wYAxis);
        wb.scale(ry);

        wv.set(wa);
        wv.add(wb);
        wv.add(wp);
    }

    public void setLightMapValue(int row, int col, double r, double g, double b) {
        lightMap[row][col].set(r, g, b);   
        int rgb = ((int) r << 16) | ((int) g << 8) | (int) b;
        lightMapImage.setRGB(col, row, rgb);
    }

    public BufferedImage getLightMapImage() {
        return lightMapImage;
    }

}
