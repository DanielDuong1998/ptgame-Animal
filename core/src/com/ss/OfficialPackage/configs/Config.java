package com.ss.OfficialPackage.configs;

import com.ss.core.util.GStage;

public class Config {
  public static float ratioX = GStage.getWorldWidth()/1280;
  public static float ratioY = GStage.getWorldHeight()/720;
  public static float widthDevice =  GStage.getStageWidth();
  public static float heightDevice =  GStage.getStageHeight();

  public final static float DURATION_DARK_SHAPE_LOAD_SCENE = 2f;
  public final static float RATIO_MARGIN_CIRCLE_BUTTON = 0.5f;
}
