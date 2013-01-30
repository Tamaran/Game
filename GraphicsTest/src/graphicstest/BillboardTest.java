package graphicstest;

import mymath.Vector3;

import org.lwjgl.opengl.GL11;

import graphics.Billboard;
import graphics.gui.BasicFrame;
import graphics.textures.TextureLoader;

public class BillboardTest extends BasicFrame {

    private Billboard b = new Billboard(1, 1);

    public void render() {
        util.ResourceLoader resLoader = new util.ResourceLoader("../res/");
        TextureLoader texLoader = new TextureLoader(resLoader);
        b.setTexture(texLoader.getTexture("explosion0.png"));
        b.setTextureCoords(5f / 16, 5f / 16, 6f / 16, 6f / 16);
        b.setFrom(new Vector3(0, 0, 0));
        b.setTo(getCamera().getPosition());

        GL11.glColor3f(1f, 0, 0);
        b.render();

    }
}
