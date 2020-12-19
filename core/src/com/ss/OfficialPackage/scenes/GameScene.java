package com.ss.OfficialPackage.scenes;

//import com.ss.OfficialPackage.controllers.MainController;
//import com.ss.OfficialPackage.models.RSA.RSA;
//import com.ss.OfficialPackage.views.ClassicScene.HardView.ClassicHardView;
import com.ss.OfficialPackage.configs.Config;
import com.ss.OfficialPackage.controllers.GameMainController;
import com.ss.OfficialPackage.views.hardViews.GameHardView;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.gameLogic.objects.Combo;
import com.ss.gameLogic.objects.EffAniDead;

public class GameScene extends GScreen {
  private GLayerGroup group;
  private GameMainController gameMainController;
  private GameHardView gameHardView;
  private Combo combo;

  public static EffAniDead effAni;

  @Override
  public void dispose() {

  }

  @Override
  public void init() {
    System.out.println("initClassic scenes");

    startMusic();
    initCombo();
    initGroup();
    initController();
    initHardView();
  }

  private void startMusic(){

  }

  private void initCombo(){
    combo = new Combo(Config.widthDevice - 300, 10, 2);
  }

  private void initGroup(){
    group = new GLayerGroup();
    GStage.addToLayer(GLayer.ui, group);
  }

  private void initController(){
    gameMainController = new GameMainController(combo);
  }

  private void initHardView(){
   gameHardView = new GameHardView(group, gameMainController);
  }

  @Override
  public void run() {

  }
}
