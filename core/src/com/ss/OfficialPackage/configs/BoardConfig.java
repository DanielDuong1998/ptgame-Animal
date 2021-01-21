package com.ss.OfficialPackage.configs;

import com.ss.GMain;
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
  public static float scallingBox = 1.05f; // tỉ lệ scale boxUI
  //    public static float scallingAni = scallingBox*(float)1.35f/1.21f; // tỉ lệ scale aniUI
  public static float scallingAni = scallingBox*0.9f; // tỉ lệ scale aniUI
  public static float paddingCellWidth = 65*scallingBox; //khoản cách giữa các ô
  public static float paddingCellHeight = 78*scallingBox; //khoản cách giữa các ô
  public static float paddingGameWidth = (widthScreen - paddingCellWidth*width)/2; // padding board game so với biên màn hình
  public static float paddingGameHeight = (heightScreen - paddingCellHeight*height)/1.2f; // padding board game so với biên màn hình
  public static float paddingAniBoxW = 1.05f*65*0.05f; // padding animalUI so với biên BoxUI
  public static float paddingAniBoxH = 1.05f*78*0.05f; // padding animalUI so với biên BoxUI

  // animation start board:
  public static int isLeft2Right = 1;
  public static int isHorizontal = 1;
  public static int modeTestAniBoard = 1;
  public static int modeSlide = -1;
  public static int quantityAnimalThunder = 6;
  ///// combo////////
  public static int countcombo   = 0;
  ///// duration rocket fly////
  public static float duraRocket = 0.025f;
  //// init suport itemt /////
  public static int    ItHint          = 3;
  public static int    ItSwap          = 4;
  public static int    ItThunder       = 5;
  public static int    RewardHint      = 1;
  public static int    RewardSwap      = 2;
  public static int    RewardThunder   = 1;
  public static String nameHint        = "Hint";
  public static String nameSwap        = "Swap";
  public static String nameThunder     = "Thunder";

  //score
  public static int aScore = 3;
  public static int baseScore = 5;
  public static int timeBaseScore = 1;
  public static int toolScore = 30;
  public static int level = 1;
}
