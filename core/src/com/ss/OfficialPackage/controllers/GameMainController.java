package com.ss.OfficialPackage.controllers;

import com.ss.OfficialPackage.scenes.GameScene;
import com.ss.core.util.GScreen;

public class GameMainController {
  private GameScene gameScene;

  public GameMainController(GameScene gameScene){
    this.gameScene = gameScene;
  }

  public void setScreen(GScreen gScreen){
    gameScene.setScreen(gScreen);
  }
}
