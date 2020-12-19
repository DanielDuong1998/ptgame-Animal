package com.ss.gameLogic.objects;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.commons.TextureAtlasC;
import com.ss.core.exSprite.particle.GParticleSprite;
import com.ss.core.exSprite.particle.GParticleSystem;
import com.ss.core.util.GClipGroup;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;

public class Timer extends GLayerGroup {
  private Image frmTime,TimeBar;
  private GClipGroup clipGroup               = new GClipGroup();
  private TemporalAction action;
  private Runnable                  onComplete;
  private int                     duration,resDura;
  private Group Greff;
  private GParticleSprite ef;

  public Timer(float x, float y,int duration){
    this.duration       = duration;
    this.resDura        = duration;
    this.setPosition(x,y, Align.center);
    frmTime = GUI.createImage(TextureAtlasC.playAtlas,"barframe");
    frmTime.setOrigin(Align.center);
    frmTime.setPosition(0,0,Align.center);
    addActor(frmTime);
    TimeBar = GUI.createImage(TextureAtlasC.playAtlas,"bar");
    TimeBar.setOrigin(Align.center);
    this.clipGroup.addActor(this.TimeBar);
    clipGroup.setPosition(frmTime.getX()+frmTime.getHeight()*0.14f,frmTime.getY()+frmTime.getHeight()*0.14f);
    this.addActor(clipGroup);


  }
  public void start(Runnable callback){
    if(ef != null) {
      ef.free();
      this.clearActions();
    }
    System.out.println("let go!");
    this.onComplete     = callback;
    Effect();
    ActionScaleTime();
  }

  public void setTime(int time){
    this.duration =time;
    this.resDura  =this.duration;
  }
  public void ActionScaleTime(){
    this.action = new TemporalAction(duration, Interpolation.linear) {
      /* access modifiers changed from: protected */
      public void update(float f) {
        Timer.this.clipGroup.setClipArea( -(Timer.this.TimeBar.getWidth() * f),0, Timer.this.TimeBar.getWidth(), Timer.this.TimeBar.getHeight());
        ef.setPosition(frmTime.getX()+frmTime.getWidth()-5-TimeBar.getWidth()*f,frmTime.getY()+frmTime.getHeight()*0.4f);
        resDura =  duration-(int)(duration*f);
//        System.out.println("resTime: "+resDura);


        if (f == 1.0f) {
          Timer.this.onComplete.run();
          ef.free();

        }
      }
    };
    addAction(this.action);
  }
  public void resetTime(){
    ef.free();
    this.clearActions();
    Effect();
    ActionScaleTime();
  }

  public int getResTime(){
    return resDura;
  }

  public String getResTimeStr(){
    int minute = resDura/60;
    int second = resDura%60;
    return minute+":"+second;
  }
  public void setPause(Boolean set){
    this.setPause(set);
  }

  private void Effect(){
    ef = GParticleSystem.getGParticleSystem("fireTime").create(this, frmTime.getX()+TimeBar.getWidth(),frmTime.getY()+TimeBar.getHeight()/2);
  }

}
