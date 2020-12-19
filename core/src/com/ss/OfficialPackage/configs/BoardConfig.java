package com.ss.OfficialPackage.configs;

import com.ss.core.util.GStage;

public class BoardConfig {

  public static int thunderId = 0;
  public static int maxWidth = 17;
  public static int maxHeight = 8;
  public static int width = 17;
  public static int height = 8;

  public static int sizeAnimal = 16; // số lượng loại trong 1 board game
  public static int quantityAnimal = 47; // số lượng loại con vật lúc init
  public static int maxQuantityInitAnimal = 20; // số lượng con trong pool lúc init mỗi loại
  public static float widthScreen = GStage.getWorldWidth();
  public static float heightScreen = GStage.getWorldHeight();
  public static float scallingBox = 1.21f; // tỉ lệ scale boxUI
  public static float scallingAni = scallingBox*(float)1.35f/1.21f; // tỉ lệ scale aniUI
  public static float paddingCellWidth = 58*scallingBox; //khoản cách giữa các ô
  public static float paddingCellHeight = 66*scallingBox; //khoản cách giữa các ô
  public static float paddingGameWidth = (widthScreen - paddingCellWidth*width)/2; // padding board game so với biên màn hình
  public static float paddingGameHeight = (heightScreen - paddingCellHeight*height)/1.2f; // padding board game so với biên màn hình
  public static float paddingAniBoxW = 5; // padding animalUI so với biên BoxUI
  public static float paddingAniBoxH = 10; // padding animalUI so với biên BoxUI

  // animation start board:
  public static int isLeft2Right = 1;
  public static int isHorizontal = 1;
  public static int modeTestAniBoard = 1;
  public static int modeSlide = -1;
  public static int quantityAnimalThunder = 6;
  ///// test combo////////
  public static int countcombo = 0;

  //score
  public static int aScore = 3;
  public static int baseScore = 5;
  public static int timeBaseScore = 1;
  public static int toolScore = 30;
  public static int level = 1;


  public static int getTimerOfTimer(int level){
    switch (level){
      case 1: return 300;
      default: return 300;
    }
  }
}
