public class Light {

    public final Vec3 position = new Vec3();
    public final Vec3 color = new Vec3();
    public final double maxDistance;
    public final double k = 1.0;

    public Light(double px, double py, double pz, double cr, double cg, double cb, double maxDistance) {
        position.set(px, py, pz);
        color.set(cr, cg, cb);
        this.maxDistance = maxDistance;
    }

    public int getIntensity(double length, double energy) {
        if (length < 0) length = 0;
        if (length > maxDistance) length = maxDistance;
        double d = (length / maxDistance);
        double k = Math.max(0, 1.0 - d * d);
        //return (int) (255 * (length / (k * maxDistance * maxDistance)) * (1.0 - energy));
        int i = (int) (255 * energy * k);
        if (i < 0) i = 0;
        if (i > 255) i = 255;
        return i;
    }
    
}
