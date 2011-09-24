package de.funky_clan.voxel;

import de.funky_clan.coregl.BaseEngine;
import de.funky_clan.coregl.GameWindow;
import de.funky_clan.octree.WritableRaster;
import de.funky_clan.voxel.data.OctreeNode;
import de.funky_clan.voxel.renderer.OctreeRenderer;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

/**
 * @author synopia
 */
public class VoxelEngine extends BaseEngine implements WritableRaster {
    private OctreeNode root;
    private OctreeRenderer octreeRenderer;

    public VoxelEngine(int size) {
        root = new OctreeNode(0,0,0,size);
    }

    @Override
    public void init(GameWindow window) {
        super.init(window);
        octreeRenderer = new OctreeRenderer(getRenderer(), root);
    }

    @Override
    public void update(int delta) {
        if( Mouse.isButtonDown(0) ) {
            int x = (int) camera.getX();
            int y = (int) camera.getY();
            int z = (int) camera.getZ();
            if( x>0 && y>0 && z>0 ) {
                setPixel(x, y, z, 1);
            }
        }
        super.update(delta);
    }

    public void render( int delta ) {
        beginRender(delta);
        octreeRenderer.render(root, getCamera());
        endRender(delta);
    }

    @Override
    public void setPixel(int x, int y, int z, int color) {
        root.setPixel(x, y, z, color);
    }

    @Override
    public int getPixel(int x, int y, int z) {
        return root.getPixel(x, y, z);
    }

    public OctreeNode getRoot() {
        return root;
    }

    @Override
    protected ArrayList<String> getDebugInfo() {
        ArrayList<String> info = super.getDebugInfo();
        info.addAll( octreeRenderer.getDebugInfo() );
        return info;
    }
}
