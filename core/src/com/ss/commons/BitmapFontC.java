package com.ss.commons;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.ss.core.util.GAssetsManager;

public class BitmapFontC {
  public static BitmapFont alert;
  public static BitmapFont btnFont;


  public static void initBitmapFont(){
    alert = GAssetsManager.getBitmapFont("alert_font.fnt");
    btnFont = GAssetsManager.getBitmapFont("font.fnt");
  }
}
