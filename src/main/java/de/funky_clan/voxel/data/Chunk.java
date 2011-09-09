package de.funky_clan.voxel.data;

/**
 * @author synopia
 */
public class Chunk extends OctreeNode {
    private int[] map;
    private boolean dirty;

    public Chunk(int x, int y, int z, int size) {
        super(x, y, z, size);

        map = new int[size*size*size];
        visible = false;
    }

    @Override
    public void setPixel(int x, int y, int z, int color) {
        int relX = x-this.x;
        int relY = y-this.y;
        int relZ = z-this.z;
        map[relX + ( relY*size+relZ ) * size] = color;
        visible = true;
        dirty   = true;
    }

    @Override
    public int getPixel(int x, int y, int z) {
        int relX = x-this.x;
        int relY = y-this.y;
        int relZ = z-this.z;
        return map[relX + ( relY*size+relZ ) * size];
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }
}
