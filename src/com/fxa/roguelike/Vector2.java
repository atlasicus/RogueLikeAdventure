package com.fxa.roguelike;

public class Vector2 
{
	float x;
	float y;
	
	public static final Vector2 Zero = new Vector2(0, 0);
	public static final Vector2 One = new Vector2(1, 1);
	
	public Vector2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
}
