package Controller;

import java.awt.Toolkit;
import java.io.File;
import java.util.Arrays;

import javax.swing.JOptionPane;

import graphics.gui.MainFrame;
import graphics.GLUtility;
import graphics.face.Polygon;
import graphics.face.Vertex;
import graphics.shadingModes.FlatShading;
import graphics.shadingModes.PhongShading;
import graphics.shadingModes.Shading;

import mymath.Vector3;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

import collisions.PolygonCollision;

import test.AnimationTest;
import test.BasicFrameTest;
import test.BillboardTest;
import test.ExplosionTest;
import test.FontTest;

public class Main {

	public static void main(String[] args){

		try {
			GLUtility.initOpengl();
                        /*
			NewGameController ngc = new NewGameController();
			ngc.initDefaultGame();
			ngc.getMainFrame().loop();
                        */
			new BasicFrameTest().loop();
		} catch (Throwable e) {
			e.printStackTrace();
			error(e);
		}
	}

	public static void error(Throwable e){
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
