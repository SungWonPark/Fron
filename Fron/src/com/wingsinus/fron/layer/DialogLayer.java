package com.wingsinus.fron.layer;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.ccColor4B;

import android.view.MotionEvent;

import com.wingsinus.fron.FronUtil;
import com.wingsinus.fron.dialog.DialogAbstract;
import com.wingsinus.fron.dialog.market.MarketDialog;

public class DialogLayer extends CCColorLayer {
	private CGPoint firstPt;
	private ArrayList<DialogAbstract> dialogQue;
	public boolean isDialogOpend = false;
	
	public DialogLayer(ccColor4B color) {
		super(color);
		dialogQue = new ArrayList<DialogAbstract>();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		this.setIsTouchEnabled(true);
	}
	public void openMarketDialog() {
		MarketDialog dlg = new MarketDialog();
		dialogQue.add(dlg);
		notifyQue();
	}
	private void notifyQue() {
		if(dialogQue.size() > 0) {
			 DialogAbstract newdlg = dialogQue.remove(0);
			 newdlg.dialogInit();
			 addChild(newdlg);
			 isDialogOpend = true;
		 }
	}
	public void closeDialog(DialogAbstract dlg) {
		 removeChild(dlg,  true);
		 isDialogOpend = false;
		 notifyQue();
	}
	
	//que 만들어서 다이얼로그 순차로 뜰수있게 해야함.
	//큐 외에 바로 발생하는 다이얼로그 뜰수있게 해야함.

	
	public boolean ccTouchesBegan(MotionEvent event){
		List<CCNode> children = getChildren();
		if(children != null) {
			CGPoint pt = FronUtil.convertTogl(event.getX(), event.getY());
			for(int i = 0; i < children.size(); i++) {
				if(children.get(i) instanceof IInterfaceDelegate) {
					if(((IInterfaceDelegate)children.get(i)).checkDown(pt)) {
						firstPt = pt;
						return true;
					}
				}
			}
		}
		return false;
	}

    public boolean ccTouchesMoved(MotionEvent event) {
    	return false;
	}

    public boolean ccTouchesEnded(MotionEvent event) {
    	if(firstPt != null) {
	    	CGPoint pt = CCDirector.sharedDirector().convertToGL(CGPoint.make(event.getX(), event.getY()));
	    	if((Math.abs(pt.x - firstPt.x) < 5) && (Math.abs(pt.y - firstPt.y) < 5)) {
	    		List<CCNode> children = getChildren();
	    		if(children != null) {
		    		for(int i = 0; i < children.size(); i++) {
						if(children.get(i) instanceof IInterfaceDelegate) {
							if(((IInterfaceDelegate)children.get(i)).checkAndClick(pt)) {
								return true;
							}
						}
					}
	    		}
	    	}
	    	else {
	    		//all button initialize.
	    		firstPt = null;
	    		return false;
	    	}
    	}
    	return false;
	}
}
