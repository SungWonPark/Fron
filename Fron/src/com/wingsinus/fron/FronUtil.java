package com.wingsinus.fron;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;

public class FronUtil {

	public FronUtil() {
		
	}
	
	public static CGPoint isoToScreen(CGPoint pt) {
		CGPoint ret = CGPoint.make(0,  0);
		ret.x = pt.x*30 - pt.y*30;
		ret.y = 15 * (pt.x + pt.y);
		
		return ret;
	}
	
	public static CGPoint convertTogl(float x, float y) {
		return CCDirector.sharedDirector().convertToGL(CGPoint.make(x, y));
	}
}
