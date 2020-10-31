package com.ss.OfficialPackage.views.hardViews;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ss.OfficialPackage.configs.Config;
import com.ss.OfficialPackage.controllers.GameMainController;
import com.ss.commons.TextureAtlasC;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;

public class GameHardView {
  private GLayerGroup group;
  private GameMainController gameMainController;
  private Image bg;

  public GameHardView(GLayerGroup group, GameMainController gameMainController){
     this.group = group;
     this.gameMainController = gameMainController;

    initUi();
    initEvent();
  }

  private void initUi(){
    bg = GUI.createImage(TextureAtlasC.bgAtlas, "bg1");

    group.addActor(bg);

    bg.setSize(Config.widthDevice, Config.heightDevice);
  }

  private void initEvent(){

  }
}
