package com.ss.OfficialPackage.views.logicViews;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.commons.TextureAtlasC;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;

public class AnimalUi {
  private boolean isAvailable;
  private int id;
  private GLayerGroup group;
  private Image shape;

  public AnimalUi(int id, GLayerGroup group){
    this.id = id;
    this.group = group;
    this.isAvailable = true;
    initShape();
    showShape(false);
//    shape.debug();
  }

  private void initShape(){
    String strName = id == -1 ? "1" : id + "";
    shape = GUI.createImage(TextureAtlasC.playAtlas, strName);
    shape.setSize(shape.getWidth()* BoardConfig.scallingAni, shape.getHeight()*BoardConfig.scallingAni);
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

  public void animationMatchCell(Runnable runnable){
    shape.setOrigin(Align.center);
    shape.addAction(Actions.sequence(
            Actions.parallel(
//        Actions.scaleTo(2f, 2f, .5f, Interpolation.linear),
//        Actions.alpha(0, .5f, Interpolation.linear)
            ),
            Actions.run(runnable)
    ));
  }

  public void reset(){
    shape.setScale(1);
    showShape(false);
    shape.getColor().a = 1;
    setIsAvailable(true);
    setPosition(0, 0);
    shape.clearListeners();
    //shape.setColor(1, 1, 1, 1);
  }

  public Image getShape(){
    return shape;
  }

  public void showShadow(boolean isShow){
    if(isShow){
      shape.setColor(0,0,0,0.3f);
    }
    else shape.setColor(Color.WHITE);
  }
}
