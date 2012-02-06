package com.wingsinus.fron.mapitem;

import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import android.graphics.Bitmap;
import android.util.Log;

import com.wingsinus.fron.FronBitmapMaker;
import com.wingsinus.fron.layer.IInterfaceDelegate;

public class MapItemController implements IInterfaceDelegate{

	private CCSprite mainImage;
	private String fileName;
	
	private int posX;
	private int posY;
	private int sizeX;
	private int sizeY;
	private float defaultX;
	private float defaultY;
	private float anchorX;
	private float anchorY;
	private boolean isRotated = false;
	
	public MapItemController(ItemModel model) {
		this.fileName = model.fileName;
		defaultX = model.defaultX;
		defaultY = model.defaultY;
		anchorX = model.anchorX;
		anchorY = model.anchorY;
		sizeX = model.sizeX;
		sizeY = model.sizeY;
		makeImage();
	}
	
	private void makeImage() {
		Bitmap bitmap = FronBitmapMaker.getInstance().makeBitmap(fileName, true);
		mainImage = new CCSprite(bitmap, fileName);
		mainImage.setPosition(defaultX, defaultY);
	}
	public void setRotateCenter(float x, float y) {
		anchorX = x;
		anchorY = y;
	}
	public void setSize(int x, int y) {
		sizeX = x;
		sizeY = y;
	}
	public void rotate() {
		isRotated = !isRotated;
		mainImage.setScaleX(mainImage.getScaleX()*-1);
	}
	public CCNode getImage() {
		return mainImage;
	}
	public void isoMoveTo(int x, int y) {
		posX = x;
		posY = y;
		mainImage.setPosition(30*x - 30*y+defaultX, 15*x+15*y+defaultY);
	}
	public void moveTo(float x, float y) {
		mainImage.setPosition(x+defaultX, y+defaultY);
	}
	public void moveBy(float x, float y) {
		CGPoint current = mainImage.getPositionRef();
		mainImage.setPosition(current.x + x,  current.y+y);
	}
	public boolean checkDown(CGPoint pt) {
		CGRect box = mainImage.getBoundingBox();
		if(box.contains(pt.x, pt.y)) {
			CGPoint origin = box.origin;
			Log.i("box touch", "a");
			if(FronBitmapMaker.getInstance().checkClickArea(this.fileName, pt.x - origin.x, box.size.height - (pt.y - origin.y))) {
				Log.i("area touch", "a");
				return true;
			}
			else
				return false;
		}
		return false;
	}

	public boolean checkAndClick(CGPoint pt) {
		CGRect box = mainImage.getBoundingBox();
		if(box.contains(pt.x, pt.y)) {
			CGPoint origin = box.origin;
			if(FronBitmapMaker.getInstance().checkClickArea(this.fileName, pt.x - origin.x, box.size.height - (pt.y - origin.y)))
				return true;
			else
				return false;
		}
		return false;
	}
}
