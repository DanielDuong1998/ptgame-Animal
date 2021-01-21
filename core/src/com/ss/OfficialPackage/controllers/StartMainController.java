package com.ss.OfficialPackage.controllers;

import com.ss.OfficialPackage.scenes.StartScene;
import com.ss.OfficialPackage.views.logicViews.PauseOption;
import com.ss.OfficialPackage.views.logicViews.PauseOptionC;
import com.ss.core.util.GScreen;

public class StartMainController {
  private StartScene startScene;
  private PauseOptionC pauseOption;

  public StartMainController(StartScene startScene){
    this.startScene = startScene;
    pauseOption = new PauseOptionC();
  }

  public void setScreen(GScreen gScreen){
    startScene.setScreen(gScreen);
  }

  public void showSettingPanel(boolean isShow){
   pauseOption.showSettingPanel(isShow);
  }
}
