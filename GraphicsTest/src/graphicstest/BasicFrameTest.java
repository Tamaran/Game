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
        //bg.render(getCamera().getPosition());
        cube.render();
        ship.render();
        earth.render();
        //sun.render();
    }

    public void renderInterface() {

    }

    public void init() {
        ResourceLoader resLoader = new ResourceLoader("../res/");
        TextureLoader texLoader = new TextureLoader(resLoader);
        MeshLoader meshLoader = new MeshLoader(resLoader);
        //Light.setModelAmbient(1,1,1);
        //camera.setLight(true);
        getCamera().setPosition(new Vector3(-10, 0, 0));
        getCamera().setSight(new Vector3(1, 0, 0));
        cube = new Model(meshLoader.getMesh("cube.obj"),
                new Vector3(0, 0, 0));
        ship = new Model(meshLoader.getMesh("Fighter.obj"),
                new Vector3(10, 0, 0));
        earth = new Model(meshLoader.getMesh("earth.obj"),
                new Vector3(-5, 0, 0));
        //sun = new Model(meshLoader.getMesh("sun.obj"),
        //        new Vector3(0, 10, 0));
        
        //bg = new Background(texLoader.getTexture("Star_Texture_Pack_by_Zephroth.jpg"), 1000);
        /*
         cube = ModelLoader.loader.getModel("cube.obj", ModelLoader.SHADING_FLAT);
         ship = ModelLoader.loader.getModel("Fighter.obj", ModelLoader.SHADING_PHONG);
         earth = ModelLoader.loader.getModel("earth.obj", ModelLoader.SHADING_SHPERE);
         sun = ModelLoader.loader.getModel("sun.obj");
         bg = new Background("texture__space_by_Mjag.jpg", 1000);
         */
    }
}
