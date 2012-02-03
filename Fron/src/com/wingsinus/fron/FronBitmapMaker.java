package com.wingsinus.fron;

import java.io.InputStream;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCTexture2D;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class FronBitmapMaker {

	private static FronBitmapMaker maker;
	private Context context;
	
	private FronBitmapMaker() {
		
	}
	public static Bitmap makeBitmap(String fileName) {
		try {
			InputStream is = CCDirector.sharedDirector().getActivity().getAssets().open(fileName);
	    	Bitmap bmp = BitmapFactory.decodeStream(is);
			is.close();
			return bmp;
		}
		catch(Exception e) {
			Log.e(e.getStackTrace()[0].getClassName(), e.toString());
		}
		return null;
	}
	public static FronBitmapMaker getInstance() {
		if(maker == null)
			maker = new FronBitmapMaker();
		return maker;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}
	
	public Bitmap loadBitmap(int id) {
		Bitmap ret = null;
		if(context != null)
			ret = BitmapFactory.decodeResource(context.getResources(), id);
		return ret;
	}
}
