package com.ss.OfficialPackage.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ss.GMain;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.OfficialPackage.models.AnimalModel;
import com.ss.OfficialPackage.scenes.GameScene;
import com.ss.OfficialPackage.views.logicViews.CellUi;
import com.ss.OfficialPackage.views.logicViews.EndGameOption;
import com.ss.OfficialPackage.views.logicViews.PauseOption;
import com.ss.OfficialPackage.views.logicViews.Rocket;
import com.ss.OfficialPackage.views.logicViews.pools.PoolAnimalUi;
import com.ss.OfficialPackage.views.logicViews.pools.PoolBoxUi;
import com.ss.OfficialPackage.views.logicViews.pools.PoolCellUi;
import com.ss.OfficialPackage.views.logicViews.pools.PoolGShapeCustom;
import com.ss.commons.LabelC;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.gameLogic.objects.Combo;
import com.ss.gameLogic.objects.Timer;

public class GameMainController {
  private GLayerGroup boxUiGroup, animalUiGroup, gShapeCustomGroup, rocketGroup;
  private PoolBoxUi poolBoxUi;
  private PoolAnimalUi poolAnimalUi;
  private PoolGShapeCustom poolGShapeCustom;
  private PoolCellUi poolCellUi;
  private Array<CellUi> cellUis;
  private GameBoardController boardController;
  private Timer timer;
  private Combo combo;
  private PauseOption pauseOption;
  private EndGameOption endGameOption;
  private GScreen gameScene;
  private LabelC txtScore, txtLevel;

  private Array<Rocket> rockets;

  public GameMainController(GScreen gameScene, Combo combo){
    this.gameScene = gameScene;
    this.combo = combo;

    initGroup();
    initPool();
    initCellUis();
    initBoardController();
    pauseOption = new PauseOption(this);
    endGameOption = new EndGameOption(this);

    initRocket();
  }

  private void initRocket(){
    rockets = new Array<>();
    for(int i = 0; i < 10; i++) {
      Rocket rocket = new Rocket(rocketGroup);
      rockets.add(rocket);
    }
  }

  public Rocket getRocket(){
    for(Rocket r : rockets){
      if(r.getIsAvai()) {
        r.setIsAvai(false);
        return r;
      }
    }
    Gdx.app.error("getRocket function in GameMainController.java", "rocket null");
    return null;
  }

  private void initGroup(){
    boxUiGroup = new GLayerGroup();
    animalUiGroup = new GLayerGroup();
    gShapeCustomGroup = new GLayerGroup();
    rocketGroup = new GLayerGroup();
    GStage.addToLayer(GLayer.ui, boxUiGroup);
    GStage.addToLayer(GLayer.ui, animalUiGroup);
    GStage.addToLayer(GLayer.ui, gShapeCustomGroup);
    GStage.addToLayer(GLayer.top, rocketGroup);
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
    if(txtLevel != null){
      txtLevel.setText("Level: " + BoardConfig.level);
    }

    timer.setPause(false);
    timer.resetTime();
    this.timer.start(()->{
      Vector2 scoreAndBestCombo = boardController.getScoreAndBestCombo();
      winGame((int)scoreAndBestCombo.x, (int)scoreAndBestCombo.y);
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
    if(txtLevel != null){
      txtLevel.setText("Level: " + BoardConfig.level);
    }

    timer.start(()->{
      System.out.println("end Game ne nha");
      Vector2 scoreAndBestCombo = boardController.getScoreAndBestCombo();
      winGame((int)scoreAndBestCombo.x, (int)scoreAndBestCombo.y);
    });

    int time = 360;
    int timeRes = 0;
    boolean isContinue = GMain.prefs.getBoolean("isContinue", false);
    System.out.println("isContinue: " + isContinue);

    if(isContinue){
      timeRes = GMain.prefs.getInteger("resTime", 0);
      this.timer.setActionTime(time - timeRes);
      int resScore = GMain.prefs.getInteger("resScore");
      int level =  GMain.prefs.getInteger("level", 1);
      System.out.println("điểm dở dang: "+resScore);
      boardController.setScore(resScore);
      txtScore.setText("Score: "+ resScore);
      txtLevel.setText("Level: " + level);
    }






    boardController.startGame();
  }

  public void winGame(int score, int bestCombo){
    System.out.println("timer: " + timer.getResTime());
    int resTime = timer.getResTime();
    timer.setPause(true);
    if(resTime > 0){
      endGameOption.showEndGamePanel(true, true, resTime, score, bestCombo);

      //win game
      int officialScore = score + resTime*BoardConfig.timeBaseScore;
      saveGame(-1, false, true, BoardConfig.level + 1);
    }
    else{
      endGameOption.showEndGamePanel(true, false, resTime, score, bestCombo);
      //lose game
      saveGame( -1, false, false, 1);
    }

    //save Game

    poolGShapeCustom.reset();
  }

  public void saveGame(int resTime, boolean isContinueInLevel, boolean isContinue, int level){
    if(isContinueInLevel){
      String  arrStr = parseArrayAnimalModelToString();
      GMain.prefs.putString("arr", arrStr);
    }

    GMain.prefs.putBoolean("isContinueInLevel",isContinueInLevel);
    GMain.prefs.putBoolean("isContinue",isContinue);
    GMain.prefs.putInteger("resTime",resTime);
    GMain.prefs.putInteger("resScore",boardController.getScore());
    GMain.prefs.putInteger("level",level);
    GMain.prefs.flush();
  }

  private String parseArrayAnimalModelToString(){
    Array<Array<AnimalModel>> animals = boardController.getAnimalModel();
    String currentArray = "";
    for(int i = 0; i < animals.size; i++) {
      for(int j = 0; j < animals.get(i).size; j++) {
        currentArray += animals.get(i).get(j).getId() + "#";
      }
    }


    System.out.println("animals: " + animals.toString());
    System.out.println("row-col: " + animals.size + "-" + animals.get(0).size);
    return currentArray;
  }

  public Combo getCombo(){
    return combo;
  }

  public void pause(boolean isPause){
    if(timer != null) timer.setPause(isPause);
    if(combo != null) combo.setPause(isPause);
    pauseOption.showSettingPanel(isPause);
    if(timer != null)
      saveGame(timer.getResTime(), true, true, BoardConfig.level);
  }

  public void setScreen(GScreen gScreen){
    BoardConfig.countcombo = 0;

    gameScene.setScreen(gScreen);
  }

  public void addTxtScore(LabelC txtScore){
    this.txtScore = txtScore;
  }

  public void addTxtLevel(LabelC txtLevel){
    this.txtLevel = txtLevel;
  }

  public void updateScore(int score){
    txtScore.setText("Score: " + score);
  }

  public void playAgain(){
    endGameOption.showEndGamePanel(false, false, 0, 0, 0);
    updateScore(0);
    newGame();
  }

  public void nextLevel(){
    endGameOption.showEndGamePanel(false, false, 0, 0, 0);
    updateScore(0);
    BoardConfig.level++;
    newGame();
  }

  public int getResTime(){
    return timer.getResTime();
  }

}
