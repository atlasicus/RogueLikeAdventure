package com.fxa.roguelike;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;


public class Square 
{
	float posX = 0.0f, posY = 0.0f;
	private FloatBuffer vertexBuffer;
	private float verticies[] = { 
									-1.0f + posX, -1.0f + posY, 0.0f,
									 1.0f + posX, -1.0f + posY, 0.0f,
									-1.0f + posX,  1.0f + posY, 0.0f,
									 1.0f + posX,  1.0f + posY, 0.0f
								};
	
	public Square(float x, float y)
	{
		posX = x;
		posY = y;
		
		verticies[0]  = posX - 10;
		verticies[1]  = posY - 10;
		verticies[2]  = 0;
		verticies[3]  = posX + 10;
		verticies[4]  = posY - 10;
		verticies[5]  = 0;
		verticies[6]  = posX - 10;
		verticies[7]  = posY + 10;
		verticies[8]  = 0;
		verticies[9]  = posX + 10;
		verticies[10] = posY + 10;
		verticies[11] = 0;
		
		setup();
	}
	
	public Square()
	{
		setup();
	}
	
	public void draw()
	{
		GLES10.glFrontFace(GL10.GL_CW);
		
		GLES10.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		GLES10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		GLES10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, verticies.length / 3);
		
		GLES10.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
		posX++;
		
		if(posX > 100)
			posX = 1.0f;
		
		update();
	}
	
	private void setup()
	{
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(verticies.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuffer.asFloatBuffer();
		vertexBuffer.put(verticies);
		vertexBuffer.position(0);
	}
	
	public void update()
	{
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(verticies.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuffer.asFloatBuffer();
		vertexBuffer.put(verticies);
		vertexBuffer.position(0);
	}
}
