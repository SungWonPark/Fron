package com.wingsinus.fron.dialog.market;

import java.util.List;

import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import com.wingsinus.fron.dialog.DialogAbstract;
import com.wingsinus.fron.layer.Button;
import com.wingsinus.fron.layer.IInterfaceDelegate;
import com.wingsinus.fron.mapitem.ItemModel;
import com.wingsinus.fron.mapitem.MapItemFactory;

public class MarketDialog extends DialogAbstract {

	private Button closeButton;
	private CCNode marketItemBg;
	
	
	public MarketDialog() {
		// TODO Auto-generated constructor stub
		super();
	}

	public void dialogInit() {
		CCSprite bg = new CCSprite("interface/shop/board_big.png");
		bg.setAnchorPoint(0,  0);
		addChild(bg);
		
		closeButton = new Button("interface/shop/button_close.png");
		closeButton.setPosition(760, 440);
		addChild(closeButton);
	
		marketItemBg = CCNode.node();
		//make items and  add items on marketitembg.
		
		ItemModel model = MapItemFactory.makeItemModel(MapItemFactory.CHERRY_PINK);
	}

	public boolean checkDown(CGPoint pt) {
		// TODO Auto-generated method stub
		if(checkDownToChildren(pt)) {
			firstPt = pt;
			return true;
		}
		
		return false;
	}

	public boolean checkAndClick(CGPoint pt) {
		// TODO Auto-generated method stub
		IInterfaceDelegate sprite = isClicked(pt);
		if(sprite != null) {
			//do click action.
			doClickAction(sprite);
			return true;
		}
		return false;
	}
	
	private void doClickAction(IInterfaceDelegate sprite) {
		//가지고 있는 버튼들과 모두 비교하여 어떤 버튼이 눌렸는지 체크한다.
		if(sprite == closeButton) {
			closeDialog();
		}
	}
}
