package com.fxa.roguelike;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Map 
{	
	private int tileSize = SystemParams.tileSize;
	private final int mapWidth = 10;
	private int posX = 0, posY = 0;
	private int[] layout = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						 	0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						 	0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						 	0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						 	0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						 	0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						 	0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						 	0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						 	0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	
	private ArrayList<Rect> tileRectList;
	private LinkedList<InputStream> assets;
	private ArrayList<Bitmap> tileTypes;
	private Paint tileColor;
	
	public Map(LinkedList<InputStream> filePaths)
	{	
		this.assets = filePaths;
		tileTypes = new ArrayList<Bitmap>(assets.size());
		
		for(InputStream asset : assets)
		{
			Log.i(getClass().getSimpleName(), "Bitmap: " + asset.toString());
			tileTypes.add(BitmapFactory.decodeStream(asset));
		}
		Log.i(getClass().getSimpleName(), "Bitmap list size: " + tileTypes.size());
		
		tileRectList = new ArrayList<Rect>(layout.length);
		tileColor = new Paint();
		tileColor.setColor(Color.YELLOW);
		
		setupTiles();
	}
	public void draw(Canvas canvas)
	{
		for(Rect r : tileRectList)
		{
			canvas.drawBitmap(tileTypes.get(0), null, r, null);
		}
	}
	public void update()
	{
		if(this.tileSize != SystemParams.tileSize)
		{
			setTileSize();
			setupTiles();
		}
	}
	
	private Rect createSquare(int posX, int posY)
	{
		//left, top, right, bottom
		return new Rect(posX, posY, posX + tileSize, posY + tileSize);
	}
	
	private void setTileSize()
	{
		tileSize = SystemParams.tileSize;
	}
	
	private void setupTiles()
	{
		tileRectList.clear();
		tileRectList.ensureCapacity(layout.length);
		for (int i = 0; i <= mapWidth; i++)
		{
			if(tileRectList.size() < layout.length)
			{		
				if(i % mapWidth == 0 && i != 0)
				{
					i = 0;
					posY += tileSize;
				}
				posX = i * tileSize;
				tileRectList.add(createSquare(posX, posY));
			}
		}
	}
}
