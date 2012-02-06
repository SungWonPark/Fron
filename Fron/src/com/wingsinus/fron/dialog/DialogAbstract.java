package com.wingsinus.fron.dialog;

import java.util.List;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.ccColor4B;

import com.wingsinus.fron.layer.DialogLayer;
import com.wingsinus.fron.layer.IInterfaceDelegate;

public class DialogAbstract extends CCColorLayer implements IInterfaceDelegate {
	protected CGPoint firstPt;
	public DialogAbstract() {
		// TODO Auto-generated constructor stub
		super(ccColor4B.ccc4(255, 255, 255, 0));
	}
	public void dialogInit() {
		
	}
	public void closeDialog() {
		((DialogLayer)getParent()).closeDialog(this);
		dispose(this);
	}
	
	public void dispose(CCNode node) {
		CCNode child = null;
		List<CCNode> children = node.getChildren();
		if(children!= null) {
			for(int i = 0; i < children.size(); i++) {
				child = children.get(i);
				dispose(child);
				node.removeChild(child,  true);
				if(child instanceof CCSprite) {
					CCTextureCache.sharedTextureCache().removeTexture(((CCSprite)child).getTexture());
				}
			}
		}
	}

	public boolean checkDown(CGPoint pt) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkAndClick(CGPoint pt) {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected boolean checkDownToChildren(CGPoint pt) {
		List<CCNode> children = getChildren();
		if(children != null) {
			IInterfaceDelegate sprite = null;
			CGPoint converted = this.convertToNodeSpace(pt);
			for(int i = 0; i < children.size(); i++) {
				if(children.get(i) instanceof IInterfaceDelegate) {
					sprite = (IInterfaceDelegate)children.get(i);
					if(sprite.checkDown(converted)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	protected IInterfaceDelegate isClicked(CGPoint pt) {
		if((firstPt != null) && (Math.abs(pt.x - firstPt.x) < 5) && (Math.abs(pt.y - firstPt.y) < 5)) {
			List<CCNode> children = getChildren();
			if(children != null) {
				IInterfaceDelegate sprite = null;
				CGPoint converted = this.convertToNodeSpace(pt);
				for(int i = 0; i < children.size(); i++) {
					if(children.get(i) instanceof IInterfaceDelegate) {
						sprite = (IInterfaceDelegate)children.get(i);
						if(sprite.checkAndClick(converted)) {
							return sprite;
						}
					}
				}
			}
		}
		return null;
	}

}
