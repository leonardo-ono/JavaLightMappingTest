public class Level {
    
    public static final int LEVEL_HEIGHT = 5;
    public static final int LEVEL_ROWS = 10;
    public static final int LEVEL_COLS = 10;

    // [y][z][x]
    public static int[][][] map = {
        {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 1
            {1, 1, 1, 1, 0, 0, 1, 1, 1, 1}, // 1
            {0, 1, 1, 0, 0, 0, 0, 1, 1, 0}, // 2
            {0, 1, 1, 1, 0, 0, 1, 1, 1, 0}, // 3
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 4
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 5
            {0, 1, 1, 0, 0, 0, 0, 1, 1, 0}, // 6
            {0, 0, 1, 1, 1, 1, 1, 1, 0, 0}, // 7
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8
        },        
        {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0}, // 1
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 2
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0}, // 3
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 4
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 5
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 6
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 7
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0}, // 8
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8
        },        
        {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0}, // 1
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 2
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0}, // 3
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 4
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 5
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 6
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 7
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0}, // 8
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8
        },        
        {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0}, // 1
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 2
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0}, // 3
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 4
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 5
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 6
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 7
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0}, // 8
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8
        },        
        {
            {0, 0, 0, 1, 1, 1, 1, 0, 0, 0}, // 1
            {0, 0, 0, 1, 1, 1, 1, 0, 0, 0}, // 1
            {0, 0, 0, 1, 1, 1, 1, 0, 0, 0}, // 2
            {0, 0, 0, 1, 1, 1, 1, 0, 0, 0}, // 3
            {0, 0, 0, 1, 1, 1, 1, 0, 0, 0}, // 4
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 5
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 6
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 7
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8
        },        
    };

    // [y][z][x]
    public static int[][][] map2 = {
        {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        },
        {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 0
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 1
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 2
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 3
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 4
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 5
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 6
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 7
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 8
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 9
        },
        {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 0
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 1
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 2
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 3
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 4
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 5
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 6
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 7
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 8
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 9
        },
        {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 0
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 1
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 2
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 3
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 4
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 5
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 6
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 7
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 8
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 9
        },
        {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        },
        
    };

    public static int get(int x, int y, int z) {
        if (x < 0 || x > LEVEL_COLS - 1) return 0;
        if (y < 0 || y > LEVEL_HEIGHT - 1) return 0;
        if (z < 0 || z > LEVEL_ROWS - 1) return 0;
        return map[y][z][x];
    }
    public static void generate(World world, double tx, double ty, double tz) {
        for (int y = 0; y < LEVEL_HEIGHT; y++) {
            for (int z = 0; z < LEVEL_ROWS; z++) {
                for (int x = 0; x < LEVEL_COLS; x++) {
                    if (get(x, y, z) == 1) {
                        double cx = x + tx;
                        double cy = y + ty;
                        double cz = z + tz;
                        
                        if (get(x, y, z + 1) == 0) world.addFace(cx, cy, cz, Face.ORIENTATION.FRONT);
                        if (get(x, y, z - 1) == 0) world.addFace(cx, cy, cz, Face.ORIENTATION.BACK);
                        if (get(x - 1, y, z) == 0) world.addFace(cx, cy, cz, Face.ORIENTATION.LEFT);
                        if (get(x + 1, y, z) == 0) world.addFace(cx, cy, cz, Face.ORIENTATION.RIGHT);
                        if (get(x, y + 1, z) == 0) world.addFace(cx, cy, cz, Face.ORIENTATION.TOP);
                        if (get(x, y - 1, z) == 0) world.addFace(cx, cy, cz, Face.ORIENTATION.BOTTOM);                            
                    }
                }
            }
        }
    }
}
