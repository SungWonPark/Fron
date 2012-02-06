package com.wingsinus.fron.mapitem;

public class MapItemFactory {

	//작물 100~200
	//나무 201~300
	//동물 301~400
	//건물 401~1000
	//데코 1001~2000
	//리워드 ,재료, 선물등 2001~4000
	//기타 4001~
	public static int CHERRY_PINK = 1001;
	public static int WATERFEED = 1002;
	
	public static ItemModel makeItemModel(int type) {
		ItemModel model = new ItemModel();
		switch(type)
		{
		case 1001:
			model.fileName = "cherry_pink.png";
			model.defaultX = 0;
			model.defaultY = -100;
			model.anchorX = 0.5f;
			model.anchorY = 0.5f;
			break;
		case 1002:
			model.fileName = "water_feed.png";
			model.defaultX = -10;
			model.defaultY = -5;
			model.anchorX = 0.7f;
			model.anchorY = 0.7f;
			break;
		}
		return model;
	}
}
//public String fileName;
//public int sizeX;
//public int sizeY;
//public float defaultX;
//public float defaultY;
//public float anchorX;
//public float anchorY;
//
//public int coinPrice;
//public int couponPrice;
//public int woodPrice;
//public float fronIndex;
