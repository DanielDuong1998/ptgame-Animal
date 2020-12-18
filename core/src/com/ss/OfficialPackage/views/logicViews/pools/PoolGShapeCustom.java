package com.ss.OfficialPackage.views.logicViews.pools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.OfficialPackage.views.logicViews.GShapeCustom;
import com.ss.commons.TextureAtlasC;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;

public class PoolGShapeCustom {
  private Array<GShapeCustom> gShapeCustoms;
  private Array<Image> shadowShapes;
  private GLayerGroup group;

  public PoolGShapeCustom(GLayerGroup group){
    this.group = group;
    initGShapeCustoms();
    initShadowShapes();
  }

  private void initGShapeCustoms(){
    gShapeCustoms = new Array<>();
    for(int i = 0; i < BoardConfig.maxWidth*BoardConfig.maxHeight ; i++) {
      GShapeCustom gShape = new GShapeCustom(group);
      gShapeCustoms.add(gShape);
    }
  }

  private void initShadowShapes(){
    shadowShapes = new Array<>();
    for(int i = 0; i < BoardConfig.maxWidth*BoardConfig.maxHeight; i++) {
      Image shadowShape = GUI.createImage(TextureAtlasC.playAtlas, "shadowShape");
      shadowShape.setName("");
      shadowShapes.add(shadowShape);
      group.addActor(shadowShape);
      shadowShape.setVisible(false);
    }
  }

  public GShapeCustom getGShapeCustom(int id){
    for(int i = 0; i < gShapeCustoms.size; i++) {
      if(gShapeCustoms.get(i).getIsAvailable()){
        boolean logic = id != -1;
        gShapeCustoms.get(i).setIsAvailable(false);
        gShapeCustoms.get(i).showShape(logic);
        return gShapeCustoms.get(i);
      }
    }

    Gdx.app.error("PoolGShapeCustom.java - getGShapeCustom", "hasn't gShapeCustom available!");
    return null;
  }

  public Image getShadowShape(){
    for(int i = 0; i < shadowShapes.size; i++) {
      if(shadowShapes.get(i).getName().equals("")){
        shadowShapes.get(i).setName("1");
        return shadowShapes.get(i);
      }
    }

    Gdx.app.error("PoolGShapeCustom.java - getShadowShape", "hasn't getShadowShape available!");
    return null;
  }

  public void reset(){
    for(GShapeCustom gShape : gShapeCustoms){
      gShape.reset();
    }
  }
}
