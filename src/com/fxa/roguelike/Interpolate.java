package com.fxa.roguelike;

public class Interpolate 
{
	public static int calcX(float t, int startX, int endX)
	{
		float u = 1 - t;
		float tt = t * t;
		float uu = u * u;
		float uuu = uu * u;
		float ttt = tt * t;
		
		int pointX = (int) (startX * uuu);
		
		pointX += 3 * uu * t * startX;
		pointX += 3 * u * tt * endX;
		pointX += ttt * endX;
		
		return pointX;
	}
	
	public static int calcY(float t, int startY, int endY)
	{
		float u = 1 - t;
		float tt = t * t;
		float uu = u * u;
		float uuu = uu * u;
		float ttt = tt * t;
		
		int pointY = (int) (startY * uuu);
		
		pointY += 3 * uu * t * startY;
		pointY += 3 * u * tt * endY;
		pointY += ttt * endY;
		
		return pointY;
	}
}
