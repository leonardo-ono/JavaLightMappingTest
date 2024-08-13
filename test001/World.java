import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class World {
    
    private final List<Light> lights = new ArrayList<>();
    private final List<Face> faces = new ArrayList<>();

    private final Color[] rayIntensity = new Color[256];

    public World() {
        for (int i = 0; i < rayIntensity.length; i++) {
            rayIntensity[i] = new Color(i, i, i);
        }
    }

    public Light addLight(double px, double py, double pz, double cr, double cg, double cb, double maxDistance) {
        Light light = new Light(px, py, pz, cr, cg, cb, maxDistance);
        lights.add(light);
        return light;
    }

    public Face addFace(double x, double y, double z, Face.ORIENTATION orientation) {
        Face face = new Face(new Vec3(x, y, z), orientation);
        faces.add(face);
        return face;
    }
    
    public Face[] addCube(double x, double y,double z) {
        Face f1 = addFace(x, y, z, Face.ORIENTATION.FRONT);
        Face f2 = addFace(x, y, z, Face.ORIENTATION.BACK);
        Face f3 = addFace(x, y, z, Face.ORIENTATION.LEFT);
        Face f4 = addFace(x, y, z, Face.ORIENTATION.RIGHT);
        Face f5 = addFace(x, y, z, Face.ORIENTATION.TOP);
        Face f6 = addFace(x, y, z, Face.ORIENTATION.BOTTOM);        
        return new Face[] { f1, f2, f3, f4, f5, f6 };
    }

    public void render(Graphics2D g2d) {
        for (Face face : faces) {
            render3DTriangle(g2d, face, face.vertices[0], face.vertices[1], face.vertices[2]);
            render3DTriangle(g2d, face, face.vertices[0], face.vertices[2], face.vertices[3]);
        }

        //Vec3 a = new Vec3(0, 0, 1);
        //Vec3 b = new Vec3(0, 0, 1);
        //Vec3 c = new Vec3(0, 0, 1);
        //render3DTriangle(g2d, a, b, c);
    }

    final double dist = 400;
    final double half_screen_width = 400;
    final double half_screen_height = 300;

    public void render3DPoint(Graphics2D g2d, Vec3 a) {
        render3DPoint(g2d, Color.BLUE, a);
    }

    public void render3DPoint(Graphics2D g2d, Color color, Vec3 a) {
        double pax = -dist * (a.x / a.z) + half_screen_width;
        double pay = -dist * (a.y / a.z) + half_screen_height;
        g2d.setColor(color);
        g2d.fillOval((int) (pax - 3), (int) (pay - 3), 6, 6);
    }

    public void render3DLine(Graphics2D g2d, Vec3 a, Vec3 b) {
        double pax = -dist * (a.x / a.z) + half_screen_width;
        double pay = -dist * (a.y / a.z) + half_screen_height;
        double pbx = -dist * (b.x / b.z) + half_screen_width;
        double pby = -dist * (b.y / b.z) + half_screen_height;
        g2d.setColor(Color.BLACK);
        g2d.drawLine((int) pax, (int) pay, (int) pbx, (int) pby);
        g2d.setColor(Color.RED);
        g2d.fillOval((int) (pax - 3), (int) (pay - 3), 6, 6);
    }

    private final Vec3 bfa = new Vec3();
    private final Vec3 bfb = new Vec3();
    private final Vec3 bf = new Vec3();
    private final Polygon polygon = new Polygon();
    
    public void render3DTriangle(Graphics2D g2d, Face face, Vec3 a, Vec3 b, Vec3 c) {
        double pax = -dist * (a.x / a.z) + half_screen_width;
        double pay = -dist * (a.y / a.z) + half_screen_height;
        double pbx = -dist * (b.x / b.z) + half_screen_width;
        double pby = -dist * (b.y / b.z) + half_screen_height;
        double pcx = -dist * (c.x / c.z) + half_screen_width;
        double pcy = -dist * (c.y / c.z) + half_screen_height;
        
        
        // back face culling
        bfa.set(pbx - pax, pby - pay, 0);
        bfb.set(pcx - pax, pcy - pay, 0);
        bfa.cross(bfb, bf);
        if (bf.z <= 0) {
            return;
        }

        polygon.reset();
        polygon.addPoint((int) pax, (int) pay);
        polygon.addPoint((int) pbx, (int) pby);
        polygon.addPoint((int) pcx, (int) pcy);

        g2d.setColor(Color.BLACK);
        g2d.fill(polygon);

        g2d.setColor(Color.BLACK);
        g2d.drawLine((int) pax, (int) pay, (int) pbx, (int) pby);
        g2d.drawLine((int) pbx, (int) pby, (int) pcx, (int) pcy);
        g2d.drawLine((int) pcx, (int) pcy, (int) pax, (int) pay);

        //g2d.setColor(Color.RED);
        //g2d.fillOval((int) (pax - 3), (int) (pay - 3), 6, 6);
        //g2d.fillOval((int) (pbx - 3), (int) (pby - 3), 6, 6);
        //g2d.fillOval((int) (pcx - 3), (int) (pcy - 3), 6, 6);

        processFaceLightMap(g2d, face);
    }

    private final Vec3 la = new Vec3();
    private final Vec3 lb = new Vec3();
    private final Vec3 lc = new Vec3();

    public Face castRay(Vec3 rayA, Vec3 rayB, Vec3 closestPoint) {
        double minDistance = Double.MAX_VALUE;
        Face intersectionFace = null;

        lb.set(rayB);
        lb.sub(rayA);
        lb.normalize();
        lb.scale(100.00);
        lb.add(rayA);

        for (Face face : faces) {
            if (Geom.getIntersectionPoint(face.vertices[0], face.vertices[1], face.vertices[2], rayA, lb, lc)) {
                la.set(rayA);
                la.sub(lc);
                double distance = la.getLength();
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPoint.set(lc);
                    intersectionFace = face;
                }
            }
            
            if (Geom.getIntersectionPoint(face.vertices[0], face.vertices[2], face.vertices[3], rayA, lb, lc)) {
                la.set(rayA);
                la.sub(lc);
                double distance = la.getLength();
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPoint.set(lc);
                    intersectionFace = face;
                }
            }
        }

        return intersectionFace;
    }

    private final Vec3 rayA = new Vec3();
    private final Vec3 rayB = new Vec3();
    private final Vec3 rayC = new Vec3();
    private final Vec3 closestPoint = new Vec3();

    public void processFaceLightMap(Graphics2D g2d, Face face) {

        for (int y = 0; y < Face.LIGHT_MAP_SIZE; y++) {
            for (int x = 0; x < Face.LIGHT_MAP_SIZE; x++) {
                
                face.getLightTexelWorldSpace(x, y, rayB);
                int ar = 0;
                int ag = 0;
                int ab = 0;

                for (int l = 0; l < lights.size(); l++) {
                    Light light = lights.get(l);

                    rayA.set(light.position);
        
                    Face intersectionFace = castRay(rayA, rayB, closestPoint);
                    if (intersectionFace == face) {
                        
                        rayC.set(rayA);
                        rayC.sub(rayB);
                        double lightPointDistance = rayC.getLength();
                        rayC.normalize();
                        double energy = rayC.dot(face.normal);

                        if (l == 0) {
                            ar += rayIntensity[light.getIntensity(lightPointDistance, energy)].getRed();
                            ab += 16;
                        }
                        else if (l == 1) {
                            ag += rayIntensity[light.getIntensity(lightPointDistance, energy * 0.25)].getRed();
                            ab += 16;
                        }
                        //else if (l == 2) {
                        //    ar += rayIntensity[light.getIntensity(lightPointDistance, energy * 0.25)].getRed();
                        //    ag += rayIntensity[light.getIntensity(lightPointDistance, energy * 0.25)].getRed();
                        //    ab += rayIntensity[light.getIntensity(lightPointDistance, energy * 0.25)].getRed() + 16;
                        //}
                        //render3DPoint(g2d, rayIntensity[light.getIntensity(lightPointDistance, energy)], rayB);
                        //render3DLine(g2d, rayA, rayB);
                    }
                }
                if (ar > 255) ar = 255;
                if (ag > 255) ag = 255;
                if (ab > 255) ab = 255;
                Color c = new Color(ar, ag, 16);
                //rayB.x += (0.001 * Math.random()) - 0.0005;
                //rayB.y += (0.001 * Math.random()) - 0.0005;
                //rayB.z += (0.001 * Math.random()) - 0.0005;
                render3DPoint(g2d, c, rayB);
                face.setLightMapValue(y, x, ar, ag, ab);
                
            }
        }

        for (int l = 0; l < lights.size(); l++) {
            Light light = lights.get(l);
            if (l == 0) {
                render3DPoint(g2d, Color.RED, light.position);
            }
            else if (l == 1) {
                render3DPoint(g2d, Color.GREEN, light.position);
            }            
            else if (l == 2) {
                render3DPoint(g2d, Color.WHITE, light.position);
            }            
        }

    }

    public void bakeLight() {
        generateAllTexturePngFiles();
        generateWavefrontMaterialFile();
        generateWavefrontFile();
    }
    
    private void generateAllTexturePngFiles() {
        for (int f = 0; f < faces.size(); f++) {
            Face face = faces.get(f);
            BufferedImage texture = face.getLightMapImage();
            try {
                ImageIO.write(texture, "png", new File("texture/texture" + f + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("error saving texture image file !");
            }
        }
    }

    private void generateWavefrontMaterialFile() {
        try (PrintWriter pw = new PrintWriter("texture/test.mtl")) {

            pw.println("# Arquivo de materiais lightmap");
            pw.println("");

            for (int f = 0; f < faces.size(); f++) {
                //Face face = faces.get(f);
                pw.println("newmtl material" + f);
                pw.println("Ka 1.000 1.000 1.000");
                pw.println("Kd 1.000 1.000 1.000");
                pw.println("Ks 0.000 0.000 0.000");
                pw.println("d 1.0");
                pw.println("illum 2");
                pw.println("map_Kd texture" + f +".png");
                pw.println("");
            }
            
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("error saving texture material file!");
        }
    }

    private void generateWavefrontFile() {
        try (PrintWriter pw = new PrintWriter("texture/test.obj")) {
            
            pw.println("# Arquivo obj mesh lightmap");
            pw.println("");
            pw.println("mtllib test.mtl");
            pw.println("");

            
            pw.println("# vertices");
            for (int f = 0; f < faces.size(); f++) {
                Face face = faces.get(f);
                for (Vec3 vertex : face.vertices) {
                    pw.println("v " + vertex.x + " " + vertex.y + " " + vertex.z);
                }
            }

            pw.println("");
            pw.println("# texture coordinates");
            for (int f = 0; f < faces.size(); f++) {
                Face face = faces.get(f);
                for (Vec3 uv : face.uvs) {
                    pw.println("vt " + uv.x + " " + uv.y);
                }
            }

            pw.println("");
            pw.println("# polygons");
            pw.println("");
            for (int f = 0; f < faces.size(); f++) {
                
                pw.println("usemtl material" + f);
                //Face face = faces.get(f);
                int vertexIndex = 4 * f + 1;
                int uvIndex = 4 * f + 1;
                pw.println("f " + vertexIndex + "/" + uvIndex + " " + (vertexIndex + 1) + "/" + (uvIndex + 1) + " " + (vertexIndex + 2) + "/" + (uvIndex + 2) + " ");
                pw.println("f " + vertexIndex + "/" + uvIndex + " " + (vertexIndex + 2) + "/" + (uvIndex + 2) + " " + (vertexIndex + 3) + "/" + (uvIndex + 3) + " ");
                pw.println("");
            }

            pw.println("");
            
            /*
            
            
            # Face inferior
            usemtl Material001
            f 1/1/3 2/2/3 3/3/3
            f 1/1/3 3/3/3 4/4/3
            
            # Face superior
            f 5/1/4 8/4/4 7/3/4
            f 5/1/4 7/3/4 6/2/4
            
            # Face frontal
            f 1/1/1 5/2/1 6/3/1
            f 1/1/1 6/3/1 2/4/1
            
            # Face traseira
            f 2/1/6 6/2/6 7/3/6
            f 2/1/6 7/3/6 3/4/6
            
            # Face esquerda
            f 3/1/5 7/2/5 8/3/5
            f 3/1/5 8/3/5 4/4/5
            
            # Face direita
            f 5/1/2 1/2/2 4/3/2
            f 5/1/2 4/3/2 8/4/2

            */

            
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("error saving wavefront mesh file!");
        }
    }
}
