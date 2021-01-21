package com.ss.OfficialPackage.views.logicViews;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ss.commons.TextureAtlasC;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;

public class Rocket {
  private GLayerGroup group;
  private Image rocketImage;
  private boolean isAvai = true;

  public Rocket(GLayerGroup group){
    this.group = group;

    rocketImage = GUI.createImage(TextureAtlasC.playAtlas, "rocket");
    group.addActor(rocketImage);
    rocketImage.setSize(rocketImage.getWidth()*1.5f, rocketImage.getHeight()*1.5f);
    rocketImage.setPosition(-50, -50);
  }

  public void moveRocket(Vector2 pos1, Vector2 pos2){
    float degree = calDegree(pos1, pos2);
    rocketImage.setRotation(degree);
    rocketImage.setPosition(pos1.x, pos1.y);
    rocketImage.addAction(Actions.sequence(
      Actions.moveTo(pos2.x, pos2.y, 0.3f, Interpolation.linear),
      Actions.run(()->{
        showRocket(false);
      })
    ));
  }

  public void showRocket(boolean isShow){
    isAvai = !isShow;
    rocketImage.setVisible(isShow);
  }

  public boolean getIsAvai(){
    return isAvai;
  }

  public void setIsAvai(boolean isAvai){
    this.isAvai = isAvai;
  }

  private float calDegree(Vector2 vt1, Vector2 vt2) {
    Vector2 vt  = (new Vector2(vt2.x - vt1.x, vt2.y - vt1.y));
    float   cos = (float) (-vt.y/Math.sqrt(vt.x*vt.x + vt.y*vt.y));
    float rotation = (float) (Math.toDegrees(Math.acos(cos)));
    if(vt.x < 0){
      return (180 - rotation)*2 + rotation;
    }
    return rotation;
  }
}
