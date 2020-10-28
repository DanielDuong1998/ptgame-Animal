package com.ss.commons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ss.core.util.GAssetsManager;

public class TextureAtlasC {
  public static TextureAtlas playAtlas;
  public static TextureAtlas bgAtlas;
  public static TextureAtlas componentAtlas;

  public static void initAtlas(){
    playAtlas = GAssetsManager.getTextureAtlas("Play.atlas");
    bgAtlas = GAssetsManager.getTextureAtlas("Bg.atlas");
    componentAtlas = GAssetsManager.getTextureAtlas("Component.atlas");
  }
}
