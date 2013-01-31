package graphicstest;

import mymath.Vector3;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;


import graphics.Background;
import graphics.Light;
import graphics.MeshLoader;
import graphics.Model;
import graphics.gui.BasicFrame;
import graphics.textures.TextureLoader;

import static org.lwjgl.opengl.GL11.*;
import util.ResourceLoader;

public class BasicFrameTest extends BasicFrame {

    private Model cube;
    private Model ship;
    private Model earth;
    private Model sun;
    private Background bg;

    public BasicFrameTest() throws LWJGLException {
        super();
    }

    public void render() {
        bg.render(getCamera().getPosition());
        cube.render();
        ship.render();
        earth.render();
        sun.render();
    }

    @Override
    public void init() {
        ResourceLoader resLoader = new ResourceLoader("../res/");
        TextureLoader texLoader = new TextureLoader(resLoader);
        MeshLoader meshLoader = new MeshLoader(resLoader);
        Light.setModelAmbient(1,1,1);
        getCamera().setPosition(new Vector3(-10, 0, 0));
        getCamera().setSight(new Vector3(1, 0, 0));
        cube = new Model(meshLoader.get("cube.obj"),
                new Vector3(0, 0, 0));
        ship = new Model(meshLoader.get("Fighter.obj"),
                new Vector3(10, 0, 0));
        earth = new Model(meshLoader.get("earth.obj"),
                new Vector3(-5, 0, 0));
        sun = new Model(meshLoader.get("sun.obj"),
                new Vector3(0, 10, 0));
        
        bg = new Background(texLoader.get("Star_Texture_Pack_by_Zephroth.jpg"), 1000);
    }
}
