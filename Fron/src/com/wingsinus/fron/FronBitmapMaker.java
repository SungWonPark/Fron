package com.wingsinus.fron;

import java.io.InputStream;
import java.util.Hashtable;

import org.cocos2d.nodes.CCDirector;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class FronBitmapMaker {

	private static FronBitmapMaker maker;
	private Hashtable<String, boolean[][]> name2AreaHash;
	
	private FronBitmapMaker() {
		name2AreaHash = new Hashtable<String, boolean[][]>();
	}
	public Bitmap makeBitmap(String fileName, boolean checkArea) {
		try {
			long current = System.currentTimeMillis();
			InputStream is = CCDirector.sharedDirector().getActivity().getAssets().open(fileName);
	    	Bitmap bmp = BitmapFactory.decodeStream(is);
			is.close();
			Log.i("make bitmap", System.currentTimeMillis() - current + ",");
			if(checkArea && name2AreaHash.get(fileName) == null) {
				makeClickArea(fileName, bmp);
		
			}
			Log.i("elapse", System.currentTimeMillis() - current + "");
			return bmp;
		}
		catch(Exception e) {
			Log.e(e.getStackTrace()[0].getClassName(), e.toString());
		}
		return null;
	}
	private void makeClickArea(String name, Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		boolean[][] area = new boolean[height][width];
		int[] buf = new int[width*height];
		bitmap.getPixels(buf,  0, width, 0, 0, width, height);
		for(int i= 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				area[i][j] = (buf[i*width+j] != 0);
			}
		}
		name2AreaHash.put(name,  area);
	}
	
	public boolean checkClickArea(String name, float x, float y) {
		try {
			boolean[][] area = name2AreaHash.get(name);
			if(area != null) {
				return area[Math.round(y)][Math.round(x)];
			}
		}
		catch(Exception e) {
			return false;
		}
		return false;
	}
	
	public static FronBitmapMaker getInstance() {
		if(maker == null)
			maker = new FronBitmapMaker();
		return maker;
	}
}
