package com.ss.OfficialPackage.views.logicViews;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.commons.TextureAtlasC;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;

public class BoxUi {
  private boolean isAvailable;
  private GLayerGroup group;
  private Image shape;

  public BoxUi(GLayerGroup group){
    this.group = group;
    this.isAvailable = true;
    initShape();
    showShape(false);
  }

  private void initShape(){
    shape = GUI.createImage(TextureAtlasC.playAtlas, "cucxilau3");
    shape.setSize(shape.getWidth()* BoardConfig.scallingBox, shape.getHeight()*BoardConfig.scallingBox);
    group.addActor(shape);
  }

  public void setPosition(float x, float y){
    shape.setPosition(x, y);
  }

  public void showShape(boolean isShowShape){
    shape.setVisible(isShowShape);
  }

  public void setIsAvailable(boolean isAvailable){
    this.isAvailable = isAvailable;
  }

  public boolean getIsAvailable(){
    return isAvailable;
  }

  public float getX(){ return shape.getX();}

  public float getY(){ return shape.getY();}

  public float getWidth(){ return shape.getWidth();}

  public float getHeight(){ return shape.getHeight();}

  public void animation(){
    shape.setOrigin(Align.center);
    shape.setScale(0);
    shape.addAction(Actions.sequence(
        Actions.scaleTo(1, 1, 0.3f, Interpolation.swingOut),
        Actions.run(()->{
        })
    ));
  }

  public void setScale(float x, float y){
    shape.setOrigin(Align.center);
    shape.setScale(x, y);
  }

  public void move(float x, float y, float dur, Interpolation interpolation){
    shape.addAction(Actions.sequence(
      Actions.moveTo(x, y, dur, interpolation),
      Actions.run(()->{

      })
    ));
  }

  public void reset(){
    setIsAvailable(true);
    showShape(false);
    setPosition(0, 0);
  }
}
