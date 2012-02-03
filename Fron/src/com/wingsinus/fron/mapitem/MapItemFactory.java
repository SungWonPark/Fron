package com.wingsinus.fron.mapitem;

public class MapItemFactory {

	//�۹� 100~200
	//���� 201~300
	//���� 301~400
	//�ǹ� 401~1000
	//���� 1001~2000
	//������ ,���, ������ 2001~4000
	//��Ÿ 4001~
	public static int CHERRY_PINK = 1001;
	public static int WATERFEED = 1002;
	
	public static MapItemController makeMapItem(int type) {
		MapItemController item = null;
		switch(type)
		{
		case 1001:
			item = new MapItemController("cherry_pink.png", 0, -100);
			item.setRotateCenter(0.5f, 0.3f);
			break;
		case 1002:
			item = new MapItemController("water_feed.png", -10, -5);
			item.setRotateCenter(0.7f, 0.7f);
			break;
		}
		return item;
	}
}
