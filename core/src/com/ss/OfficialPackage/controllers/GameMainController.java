package com.ss.OfficialPackage.controllers;

import com.badlogic.gdx.utils.Array;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.OfficialPackage.scenes.GameScene;
import com.ss.OfficialPackage.views.logicViews.CellUi;
import com.ss.OfficialPackage.views.logicViews.pools.PoolAnimalUi;
import com.ss.OfficialPackage.views.logicViews.pools.PoolBoxUi;
import com.ss.OfficialPackage.views.logicViews.pools.PoolCellUi;
import com.ss.OfficialPackage.views.logicViews.pools.PoolGShapeCustom;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.gameLogic.objects.Combo;

public class GameMainController {
  private GLayerGroup boxUiGroup, animalUiGroup, gShapeCustomGroup;
  private PoolBoxUi poolBoxUi;
  private PoolAnimalUi poolAnimalUi;
  private PoolGShapeCustom poolGShapeCustom;
  private PoolCellUi poolCellUi;
  private Array<CellUi> cellUis;
  private GameBoardController boardController;
  private Timer timer;
  private Combo combo;

  public MainController(Combo combo){
    this.combo = combo;

    initGroup();
    initPool();
    initCellUis();
    initBoardController();
  }

  private void initGroup(){
    boxUiGroup = new GLayerGroup();
    animalUiGroup = new GLayerGroup();
    gShapeCustomGroup = new GLayerGroup();
    GStage.addToLayer(GLayer.ui, boxUiGroup);
    GStage.addToLayer(GLayer.ui, animalUiGroup);
    GStage.addToLayer(GLayer.ui, gShapeCustomGroup);
  }

  private void initPool(){
    poolBoxUi = new PoolBoxUi(boxUiGroup);
    poolAnimalUi = new PoolAnimalUi(animalUiGroup);
    poolGShapeCustom = new PoolGShapeCustom(gShapeCustomGroup);
    poolCellUi = new PoolCellUi(poolBoxUi, poolAnimalUi, poolGShapeCustom, this);
  }

  private void initCellUis(){
    cellUis = new Array<>();
    for(int i = 0; i < BoardConfig.width*BoardConfig.height; i++) {
      CellUi cellUi = poolCellUi.getCellUi();
      cellUis.add(cellUi);
    }
  }

  private void initBoardController(){
    boardController = new GameBoardController(this, cellUis);
  }

  public void cellTouchDown(int row, int col){
    boardController.cellTouchDown(row, col);
  }

  public void findHint(){
    System.out.println("find Hint");
    boardController.getHint();
  }

  public void shuffle(){
    System.out.println("shuffle");
    boardController.shuffleBoard();
  }

  public void newGame(){
    this.timer.start(()->{
      System.out.println("MainController - newGame(): endGame");
    });
    System.out.println("new Game");
    poolGShapeCustom.reset();
    for(CellUi cellUi : cellUis){
      cellUi.reset();
    }

    cellUis.removeRange(0, cellUis.size - 1);
    initCellUis();
    boardController.newGame(cellUis);

    GameScene.effAni.FreeAllEfSelect();
  }

  public void thunder(){
    //boardController.thunder();
  }

  public void thunder2(){
    boardController.thunder2();
  }

  public void startGame(Timer timer){
    this.timer = timer;
    this.timer.setVisible(true);
    timer.start(()->{
      System.out.println("end Game");
    });
    boardController.startGame();
  }

  public void winGame(int score){
    System.out.println("timer: " + timer.getResTime());
    int resTime = timer.getResTime();
    timer.setPause(true);
    if(resTime > 0){
      //win game
      int officialScore = score + resTime*BoardConfig.timeBaseScore;
    }
    else{
      //lose game

    }

    poolGShapeCustom.reset();
  }

  public Combo getCombo(){
    return combo;
  }

  public void pause(boolean isPause){
    timer.setPause(isPause);
    combo.setPause(isPause);
  }
}
