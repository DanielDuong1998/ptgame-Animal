package com.ss.OfficialPackage.scenes;

//import com.ss.OfficialPackage.controllers.MainController;
//import com.ss.OfficialPackage.models.RSA.RSA;
//import com.ss.OfficialPackage.views.ClassicScene.HardView.ClassicHardView;
import com.ss.OfficialPackage.controllers.GameMainController;
import com.ss.OfficialPackage.views.hardViews.GameHardView;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;

public class GameScene extends GScreen {
  private GLayerGroup group;
  private GameMainController gameMainController;
  private GameHardView gameHardView;

  @Override
  public void dispose() {

  }

  @Override
  public void init() {
    System.out.println("initClassic scenes");

    startMusic();
    initGroup();
    initController();
    initHardView();
  }

  private void startMusic(){

  }

  private void initGroup(){
    group = new GLayerGroup();
    GStage.addToLayer(GLayer.ui, group);
  }

  private void initController(){
    gameMainController = new GameMainController(this);
  }

  private void initHardView(){
   gameHardView = new GameHardView(group, gameMainController);
  }

  @Override
  public void run() {

  }
}
