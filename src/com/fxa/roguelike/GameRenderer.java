package com.fxa.roguelike;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

public class GameRenderer implements GLSurfaceView.Renderer
{
	//LinkedList<Square> squareList;
	Square squareList[];
	
	private float rotation = 0.0f;
	
	@Override
	public void onDrawFrame(GL10 gl) 
	{
		GLES10.glClear( GLES10.GL_COLOR_BUFFER_BIT | GLES10.GL_DEPTH_BUFFER_BIT);
		GLES10.glMatrixMode(GLES10.GL_PROJECTION);
		GLES10.glPushMatrix();
		GLES10.glLoadIdentity();
		GLES10.glOrthof(0f, SystemParams.screenWidth, SystemParams.screenHeight, 0F, -1, 1);
		GLES10.glMatrixMode(GLES10.GL_MODELVIEW); // Select Modelview Matrix
		GLES10.glPushMatrix(); // Push The Matrix
		GLES10.glLoadIdentity(); // Reset The Matrix
		
		for(int i = 0; i < squareList.length; i++)
			squareList[i].draw();
		
		rotation += 1f;
		
		if(rotation > 360f)
			rotation = 0f;
		SystemParams.CalcFramesPerSecond();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) 
	{
		if(height == 0) 
		{ 										//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}

		gl.glViewport(0, 0, width, height); 	//Reset The Current Viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix

		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity(); 
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) 
	{
		squareList = new Square[10];
		
		for(int i = 0; i < squareList.length; i++)
		{
			squareList[i] = new Square((i) * 20, (i) * 20);
		}
		
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(1.0f, 0.0f, 0.0f, 0.5f); 	//Red Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do
		
		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 
	}
}
