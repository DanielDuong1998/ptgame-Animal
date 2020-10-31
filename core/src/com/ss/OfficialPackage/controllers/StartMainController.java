package com.ss.OfficialPackage.controllers;

import com.ss.OfficialPackage.scenes.StartScene;
import com.ss.core.util.GScreen;

public class StartMainController {
  private StartScene startScene;

  public StartMainController(StartScene startScene){
    this.startScene = startScene;
  }

  public void setScreen(GScreen gScreen){
    startScene.setScreen(gScreen);
  }
}
