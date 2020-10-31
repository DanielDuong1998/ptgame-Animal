package com.ss.OfficialPackage.scenes;

import com.ss.OfficialPackage.controllers.StartMainController;
import com.ss.OfficialPackage.views.hardViews.StartHardView;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.effects.SoundEffect;

public class StartScene extends GScreen {
  private GLayerGroup group;
  private StartMainController startMainController;
  private StartHardView startHardView;


  @Override
  public void dispose() {

  }

  @Override
  public void init() {
    System.out.println("start Scene");

    startMusic();
    initGroup();
    initController();
    initHardView();
  }

  private void initGroup(){
    group = new GLayerGroup();
    GStage.addToLayer(GLayer.ui, group);
  }

  private void initController(){
    startMainController = new StartMainController(this);
  }

  private void initHardView(){
    startHardView = new StartHardView(group, startMainController);
  }

  private void startMusic(){
    SoundEffect.Playmusic(1);
  }

  @Override
  public void run() {

  }
}
