package com.fxa.roguelike;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Player 
{
	private Bitmap texture;
	
	private int tileSize = SystemParams.tileSize;
	
	private float speed = 0.000003f;
	
	private boolean isTouched = false, lastTouched = false, canMove = true;
	
	private Vector2 pos = Vector2.One;
	
	public Player(InputStream texLoc)
	{
		texture = BitmapFactory.decodeStream(texLoc);
	}
	
	public void draw(Canvas canvas)
	{
		canvas.scale(8, 8);
		canvas.drawBitmap(texture, pos.x / 8, pos.y / 8, null);
	}
	
	public void update()
	{
		lastTouched = isTouched;
		isTouched = Input.isTouched;
		canMove = isTouched == true && lastTouched == false;
		
		if(this.tileSize != SystemParams.tileSize)
			setTileSize();
		
		if(canMove)
		{
			Input.consumeTouch();
			pathTo(getTile(Input.touchLocation()));
			//setPos(Input.touchLocation());
		}
	}
	
	private void pathTo(Vector2 dest)
	{
		int distanceX = (int) Math.abs((pos.x / tileSize) - (dest.x / tileSize));
		int distanceY = (int) Math.abs((pos.y / tileSize) - (dest.y / tileSize));
		
//		Log.i("Pathing", "posX: " + pos.x + " posY: " + pos.y);
//		Log.i("Pathfinding", "Distance X: " + distanceX + " Distance Y: " + distanceY);
		
		while(distanceX > 0 || distanceY > 0)
		{
			distanceX = (int) Math.abs(pos.x - dest.x);
			distanceY = (int) Math.abs(pos.y - dest.y);

			if(distanceX > 0)
			{
				if(pos.x > dest.x)
				{
					pos.x -= speed * SystemParams.timeDelta;
//					Log.i("Pathing", "posX: " + pos.x + " posY: " + pos.y);
				}
				else if(pos.x < dest.x)
				{
					pos.x += speed * SystemParams.timeDelta;
//					Log.i("Pathing", "posX: " + pos.x + " posY: " + pos.y);
				}
			}
			if(distanceY > 0)
			{
				if(pos.y > dest.y)
				{
					pos.y -= speed * SystemParams.timeDelta;
//					Log.i("Pathing", "posX: " + pos.x + " posY: " + pos.y);
				}
				else if(pos.y < dest.y)
				{
					pos.y += speed * SystemParams.timeDelta;
//					Log.i("Pathing", "posX: " + pos.x + " posY: " + pos.y);
				}
			}
		}
	}
	
	private void setPos(Vector2 dest)
	{
		pos = dest;
//		Log.i("Pathing", "posX: " + pos.x + " posY: " + pos.y);
	}
	
	private Vector2 getTile(Vector2 pos)
	{
		pos.x = pos.x - (pos.x % tileSize);
		pos.y = pos.y - (pos.y % tileSize);
		return pos;
	}
	
	private void setTileSize()
	{
		tileSize = SystemParams.tileSize;
	}
}
