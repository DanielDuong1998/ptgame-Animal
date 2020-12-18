package com.ss.OfficialPackage.views.logicViews;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.ss.core.exSprite.GShapeSprite;
import com.ss.core.util.GLayerGroup;

public class GShapeCustom extends GShapeSprite {
  private GLayerGroup group;
  private boolean isAvailable;

  public GShapeCustom(GLayerGroup group){
    this.group = group;
    this.isAvailable = true;
    initGshape();
  }

  private void initGshape(){
    group.addActor(this);
  }

  public void showShape(boolean isShowShape){
    setVisible(isShowShape);
  }

  public void setIsAvailable(boolean isAvailable){
    this.isAvailable = isAvailable;
  }

  public boolean getIsAvailable(){
    return isAvailable;
  }

  public void reset(){
    setPosition(0, 0);
    setIsAvailable(true);
    showShape(false);
    setTouchable(Touchable.enabled);
    this.clearListeners();
  }
}
