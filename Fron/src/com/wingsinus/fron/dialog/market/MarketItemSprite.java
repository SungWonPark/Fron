package com.wingsinus.fron.dialog.market;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCSpriteSheet;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGRect;

import com.wingsinus.fron.layer.Button;
import com.wingsinus.fron.mapitem.ItemModel;
import com.wingsinus.fron.mapitem.MapItemFactory;

import android.graphics.Bitmap;

public class MarketItemSprite extends CCSprite {

	private ItemModel model;
	private CCSprite image;
	private Button buyButton;
	private float size = 120;
	public MarketItemSprite(ItemModel model) {
		super("interface/shop/board_small.png");
		this.model = model;
		image = new CCSprite(model.fileName);
		float width = image.getTexture().getWidth();
		float height = image.getTexture().getHeight();
		float wdiff = width - size;
		float hdiff = height - size;
		float diff = (wdiff > hdiff)?wdiff:hdiff;
		if(diff > 0) {
			float scalediff = size/width;
			image.setScale(getScale()*scalediff);
		}
		addChild(image);
	}
	
	
	public void dispose() {
		removeAllChildren(true);
		CCTextureCache.sharedTextureCache().removeTexture(model.fileName);
		CCTextureCache.sharedTextureCache().removeTexture("interface/shop/board_small.png");
		//remove button.
	}
}
