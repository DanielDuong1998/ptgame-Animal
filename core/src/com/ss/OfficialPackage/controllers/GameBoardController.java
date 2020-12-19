package com.ss.OfficialPackage.controllers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.OfficialPackage.models.AnimalModel;
import com.ss.OfficialPackage.models.BoardModel;
import com.ss.OfficialPackage.views.logicViews.BoardUi;
import com.ss.OfficialPackage.views.logicViews.CellUi;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GStage;
import com.ss.gameLogic.objects.Combo;

public class GameBoardController {
  private GameMainController mainController;
  private Array<CellUi> cellUis;
  private BoardModel boardModel;
  private GLayerGroup pathGroup;
  private BoardUi boardUi;
  private Combo combo;
  private int bestCombo = 0;
  private int score = 0;

  public GameBoardController(GameMainController mainController, Array<CellUi> cellUis){
    this.mainController = mainController;
    this.cellUis = cellUis;
    this.combo = mainController.getCombo();

    initBoardModel();
    initBoardUi();

  }

  private void initBoardModel(){
    boardModel = new BoardModel(this);
  }

  private void generateCellUi(){
    for(int i = 0; i < BoardConfig.height; i++) {
      for(int j = 0; j < BoardConfig.width; j++) {
        cellUis.get(i*BoardConfig.width + j).setId(boardModel.getAnimals().get(i).get(j).getId(), i, j);
      }
    }
  }

  private void initBoardUi(){
    pathGroup = new GLayerGroup();
    GStage.addToLayer(GLayer.ui, pathGroup);
    boardUi = new BoardUi(cellUis, pathGroup);
  }

  public void cellTouchDown(int row, int col){
    Array<Vector2> path = boardModel.cellTouchDown(row, col);
    if(path.size == 0){

    }
    else if(path.size == 1){
      //
      int rowUnSelected = (int)path.get(0).x;
      int colUnSelected = (int)path.get(0).y;
      System.out.println("path h: " + path);
      int r = cellUis.get(rowUnSelected* BoardConfig.width + colUnSelected).getRow();
      int c = cellUis.get(rowUnSelected* BoardConfig.width + colUnSelected).getCol();
      System.out.println("r-c vua unselect: " + r + "-" + c);
      cellUis.get(rowUnSelected* BoardConfig.width + colUnSelected).animationCellTouchDown(false);
    }
    else {
      System.out.println("match");
      BoardConfig.countcombo++;
      updateBestCombo();
      combo.upTime(BoardConfig.countcombo);

      score += (BoardConfig.baseScore + ((Math.max(BoardConfig.countcombo - 1, 0)) * BoardConfig.aScore));
      System.out.println("score: " + score);

      Vector2 vt1 = path.get(0);
      Vector2 vt2 = path.get(path.size - 1);
      boolean thunderFlag = cellUis.get((int)vt1.x * BoardConfig.width + (int)vt1.y).getId() == BoardConfig.thunderId;
//      boolean thunderFlag = true;

      cellUis.get((int)vt1.x * BoardConfig.width + (int)vt1.y).matchCell();
      cellUis.get((int)vt2.x * BoardConfig.width + (int)vt2.y).matchCell();
      boardUi.drawPath(path);
      System.out.println("path: " + path);

      pathGroup.addAction(Actions.sequence(
              Actions.delay(0.1f),
              Actions.run(()->{
                slideBoard(path);
              })));

      float dur = thunderFlag ? 0.38f : 0.35f;

      pathGroup.addAction(Actions.sequence(
              Actions.delay(dur),
//        Actions.run(this::shuffleBoardIfNotHintOrWinGame)
              Actions.run(()->{
                if(thunderFlag){
                  final Array<Vector2> listVt = boardModel.thunder();
                  System.out.println("listVt: "+ listVt);
                  // ten lua tai day

                  thunder(listVt);
                }
                else {
                  shuffleBoardIfNotHintOrWinGame();
                }
              })
      ));
    }
  }

  private void slideBoard(Array<Vector2> path){
    System.out.println("slide board");
    Array<Array<AnimalModel>> animals = boardModel.getAnimals();
    Array<Array<Vector2>> arr = SlideBoardModel.slideAnimalModels(animals, path.get(0), path.get(path.size - 1), BoardConfig.modeSlide);
//    Array<Array<Vector2>> arr = SlideBoardModel.slideAnimalModels(animals, path.get(0), path.get(path.size - 1), 22);
    updateCellsUI(arr);
    fitSlidePosition();
  }

  private void slideBoardManyAnimal(Array<Vector2> list){
    Array<Array<AnimalModel>> animals = boardModel.getAnimals();
    Array<Array<Vector2>> arr = new Array<>();
    for(int i = 0; i < list.size; i = i+2){
//      Array<Array<Vector2>> arr1 = SlideBoardModel.slideAnimalModels(animals, list.get(i), list.get(i+1), BoardConfig.modeSlide);
      Array<Array<Vector2>> arr1 = SlideBoardModel.slideAnimalModels(animals, list.get(i), list.get(i+1), 22);
      for(int j = 0; j < arr1.size; j++) {
        arr.add(arr1.get(j));
      }
    }
    updateCellsUI(arr);
    fitSlidePosition();
  }
  private void shuffleBoardIfNotHintOrWinGame(){
    if(boardModel.getQuantityAnimal() > 0){
      shuffleBoardIfNotHint();
    }
    else {
      winGame();
    }
  }

  private void winGame(){
    mainController.winGame(score);
    System.out.println("win game, best combo: " + bestCombo);
  }

  // -> 1, ham sẽ shuffle, -> 1, hàm không shuffle
  public void shuffleBoardIfNotHint(){
    Array<Vector2> hint = boardModel.findHint();
    if(hint.size < 2){
      shuffleBoard();
    }
  }

  public void shuffleBoard(){
    Array<Vector2> hint = new Array<>();

    while(hint.size == 0){
      boardModel.shuffle();
      hint = boardModel.findHint();
    }
    Array<Array<AnimalModel>> animalModels = boardModel.getAnimals();


    for(int i = 0; i < animalModels.size; i++) {
      for(int j = 0; j < animalModels.get(i).size; j++) {
        if(!cellUis.get(i*BoardConfig.width + j).getIsAvailable())
          cellUis.get(i*BoardConfig.width + j).changeId(animalModels.get(i).get(j).getId());
      }
    }


  }

  public void showHint(Array<Vector2> hint){
    cellUis.get((int)hint.get(0).x*BoardConfig.width + (int)hint.get(0).y).showHint(true);
    cellUis.get((int)hint.get(1).x*BoardConfig.width + (int)hint.get(1).y).showHint(true);
  }

  public void getHint(){
    boardModel.unSelect();
    Array<Vector2> hint = boardModel.findHint();
    showHint(hint);
  }

  public void unSelectCellUi(Vector2 pos){
    cellUis.get((int)pos.x*BoardConfig.width + (int)pos.y).animationCellTouchDown(false);
  }

  public void unHintCellUi(Vector2 pos){
    cellUis.get((int)pos.x*BoardConfig.width + (int)pos.y).showHint(false);
  }

  public void newGame(Array<CellUi> cellUis){
    this.cellUis = cellUis;
    boardUi.newGame(cellUis);
    boardModel.newGame();
    bestCombo = 0;
    score = 0;
    generateCellUi();

    //AnimationBoard.mode(cellUis, pathGroup, BoardConfig.isLeft2Right, BoardConfig.isHorizontal);
    //AnimationBoard.mode2(cellUis, pathGroup, false, true);
    animationStartGame();

  }

  public void thunder(Array<Vector2> listVt){
//    Array<Vector2> listVt = boardModel.thunder();
    if(listVt.size == 0){
      winGame();
      return;
    }
    Vector2 vt1 = listVt.get(0);
    Vector2 vt2 = listVt.get(1);
    boolean thunderFlag = cellUis.get((int)vt1.x * BoardConfig.width + (int)vt1.y).getId() == BoardConfig.thunderId;
    cellUis.get((int)vt1.x * BoardConfig.width + (int)vt1.y).enableClick(false);
    cellUis.get((int)vt2.x * BoardConfig.width + (int)vt2.y).enableClick(false);
    BoardConfig.countcombo++;
    combo.upTime(BoardConfig.countcombo);
    cellUis.get((int)vt1.x * BoardConfig.width + (int)vt1.y).matchCell();
    cellUis.get((int)vt2.x * BoardConfig.width + (int)vt2.y).matchCell();
    //boardUi.drawPath(path);
    // System.out.println("path: " + path);

    pathGroup.addAction(Actions.sequence(
            Actions.delay(0.1f),
            Actions.run(()->{
              slideBoard(listVt);
            })));

    float dur = thunderFlag ? 0.38f : 0.35f;
    pathGroup.addAction(Actions.sequence(
            Actions.delay(dur),
//      Actions.run(this::shuffleBoardIfNotHintOrWinGame)
            Actions.run(()->{
              if(thunderFlag){
                final Array<Vector2> listVt2 = boardModel.thunder();
                // ten lua tai day
                System.out.println("listvt: " + listVt2);

                thunder(listVt2);
              }
              else {
                shuffleBoardIfNotHintOrWinGame();
              }
            })
    ));
  }

  public void thunder2(){
    Array<Vector2> listVts = boardModel.thunder2();
    System.out.println("list vts: "+ listVts);
    if(listVts.size == 0){
      winGame();
      return;
    }

    Array<Vector2> listPosition = new Array<>();
    for(Vector2 vt : listVts){
      listPosition.add(new Vector2(cellUis.get((int)vt.x * BoardConfig.width + (int)vt.y).getX() + BoardConfig.paddingCellWidth/2,cellUis.get((int)vt.x * BoardConfig.width + (int)vt.y).getY() + BoardConfig.paddingCellHeight/2));
    }

    new EffHammer(ClassicScene.effAni, listPosition,()->{
      for(int i = 0; i < listVts.size; i++) {
        cellUis.get((int)listVts.get(i).x * BoardConfig.width + (int)listVts.get(i).y).makeShadow(false);
      }
    },()->{
      shadowAllCellUi(false);
    });
    shadowAllCellUi(true);

    pathGroup.addAction(Actions.sequence(
            Actions.delay(4),
            Actions.run(()->{
              for(int i = 0; i < listVts.size; i++) {
                cellUis.get((int)listVts.get(i).x * BoardConfig.width + (int)listVts.get(i).y).matchCell();
              }

              pathGroup.addAction(Actions.sequence(
                      Actions.delay(0.1f),
                      Actions.run(()->{
                        slideBoardManyAnimal(listVts);
                      })
              ));

              pathGroup.addAction(Actions.sequence(
                      Actions.delay(0.35f),
                      Actions.run(this::shuffleBoardIfNotHintOrWinGame)
              ));
            })
    ));

    // sam set tai day
//    for(int i = 0; i < listVts.size; i++) {
//      cellUis.get((int)listVts.get(i).x * BoardConfig.width + (int)listVts.get(i).y).matchCell();
//    }
//
//    pathGroup.addAction(Actions.sequence(
//      Actions.delay(0.1f),
//      Actions.run(()->{
//        slideBoardManyAnimal(listVts);
//      })
//    ));
//
//    pathGroup.addAction(Actions.sequence(
//      Actions.delay(0.35f),
//      Actions.run(this::shuffleBoardIfNotHintOrWinGame)
//    ));

  }

  private void shadowAllCellUi(boolean isShadow){
    System.out.println("isShadow: " + isShadow);
    for(CellUi cellUi : cellUis){
      if(!cellUi.getIsAvailable())
        cellUi.makeShadow(isShadow);
    }
  }

  private void animationStartGame(){
    int mode = BoardConfig.modeTestAniBoard;
    AnimationBoard.aniByMode(cellUis, pathGroup, mode);
    BoardConfig.modeTestAniBoard++;
    if(BoardConfig.modeTestAniBoard > 16){
      BoardConfig.modeTestAniBoard = 1;
    }

    BoardConfig.modeSlide++;
    if(BoardConfig.modeSlide > 21)
      BoardConfig.modeSlide = 0;
  }

  private void updateRowColCellUI(Array<Array<AnimalModel>> animals){
    for(int i = 0; i < animals.size; i++) {
      for(int j = 0; j < animals.get(i).size; j++) {
        cellUis.get(i*animals.get(0).size + j).setRow(animals.get(i).get(j).getRow());
      }
    }
  }

  private void updateCellsUI(Array<Array<Vector2>> arr){
    for(Array<Vector2> a : arr){
      int first = (int)a.get(0).x*BoardConfig.width + (int)a.get(0).y;
      int second = (int)a.get(1).x*BoardConfig.width + (int)a.get(1).y;
      cellUis.swap(first, second);
      cellUis.get(first).setRow((int)a.get(0).x);
      cellUis.get(first).setCol((int)a.get(0).y);

      cellUis.get(second).setRow((int)a.get(1).x);
      cellUis.get(second).setCol((int)a.get(1).y);
    }
  }

  private void fitSlidePosition(){
    for(int i = 0 ; i < cellUis.size; i++){
      cellUis.get(i).animationMoveFitLayout();
    }
  }

  public void startGame(){
    generateCellUi();
    animationStartGame();
  }

  private void  updateBestCombo(){
    if(BoardConfig.countcombo > bestCombo) bestCombo = BoardConfig.countcombo;
  }
}
