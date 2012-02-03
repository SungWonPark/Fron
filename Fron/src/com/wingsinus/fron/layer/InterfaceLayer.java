package com.wingsinus.fron.layer;

import java.util.ArrayList;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.ccColor4B;

import android.util.Log;
import android.view.MotionEvent;

public class InterfaceLayer extends CCColorLayer {

	private RoundMenu roundMenu;
	private ArrayList<IInterfaceDelegate> buttonArray;
	private CGPoint firstPt;
	private Button marketButton;
	
	public InterfaceLayer(ccColor4B color) {
		super(color);
		buttonArray = new ArrayList<IInterfaceDelegate>();
	}

	public void init() {
		setIsTouchEnabled(true);
		makeRoundMenu();
		marketButton = new Button("interface/button_market.png");
		marketButton.setPosition(600, 50);
		addChild(marketButton);
		buttonArray.add(marketButton);
		
		
	}
	private void makeRoundMenu() {
		roundMenu = new RoundMenu();
		roundMenu.init(this);
	}
	
	public void showRoundMenu(CGPoint pt) {
		roundMenu.show(pt);
	}
	public void moveByRoundMenu(float x, float y) {
		roundMenu.moveBy(x,  y);
	}
	public boolean ccTouchesBegan(MotionEvent event){
		Log.i("interfacelayer", "touch");
		//check touch.
		CGPoint pt = CCDirector.sharedDirector().convertToGL(CGPoint.make(event.getX(), event.getY()));
		if(roundMenu.checkDown(pt)) {
			firstPt = pt;
			return true;
		}
		//other button check.
		int len = buttonArray.size();
		for(int i = 0; i < len; i++) {
			if(buttonArray.get(i).checkDown(pt)) {
				firstPt = pt;
				return true;
			}
		}
		return false;
	}

    public boolean ccTouchesMoved(MotionEvent event) {
//    	Log.i("interfacelayer", "move");
    	return false;
	}

    public boolean ccTouchesEnded(MotionEvent event) {
    	if(firstPt != null) {
	    	CGPoint pt = CCDirector.sharedDirector().convertToGL(CGPoint.make(event.getX(), event.getY()));
	    	if((Math.abs(pt.x - firstPt.x) < 5) && (Math.abs(pt.y - firstPt.y) < 5)) {
	    		if(roundMenu.checkAndClick(pt)) {
	    			firstPt = null;
	    			return true;
	    		}
	    		else {
	    			int len = buttonArray.size();
		    		for(int i = 0; i < len; i++) {
		    			if(buttonArray.get(i).checkAndClick(pt)) {
		    				buttonClickAction(buttonArray.get(i));
		    				firstPt = null;
		    				return true;
		    			}
		    		}
	    		}
	    		
	    		//other button check. buttonarray
	    	}
	    	else {
	    		//all button initialize.
	    		firstPt = null;
	    		return false;
	    	}
    	}
    	return false;
	}
    private void buttonClickAction(IInterfaceDelegate button) {
    	if(button == marketButton) {
    		SceneManager.getInstance().dlgLayer.openMarketDialog();
    	}
    }
}
