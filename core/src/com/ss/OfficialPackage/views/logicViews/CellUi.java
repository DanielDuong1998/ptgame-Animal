package com.ss.OfficialPackage.views.logicViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.OfficialPackage.controllers.GameMainController;
import com.ss.OfficialPackage.scenes.GameScene;
import com.ss.OfficialPackage.views.logicViews.pools.PoolAnimalUi;
import com.ss.OfficialPackage.views.logicViews.pools.PoolBoxUi;
import com.ss.OfficialPackage.views.logicViews.pools.PoolGShapeCustom;
import com.ss.effects.SoundEffect;
import com.ss.effects.effectWin;

public class CellUi {
  private PoolBoxUi poolBoxUi;
  private PoolAnimalUi poolAnimalUi;
  private PoolGShapeCustom poolGShapeCustom;

  private boolean isAvailable;
  private int id;
  private int row, col;
  private BoxUi boxUi;
  private AnimalUi animalUi;
  private Image shadowShape;
  private GShapeCustom gShapeCustom;
  private GameMainController mainController;
  private int             idEffSelect = -1;
  private effectWin efSelect;

  private float x, y;

  public CellUi(PoolBoxUi poolBoxUi, PoolAnimalUi poolAnimalUi, PoolGShapeCustom poolGShapeCustom, GameMainController mainController){
    this.poolBoxUi = poolBoxUi;
    this.poolAnimalUi = poolAnimalUi;
    this.poolGShapeCustom = poolGShapeCustom;
    this.mainController = mainController;
    this.isAvailable = true;
  }

  public void setId(int id, int row, int col){
    this.id = id;
    this.row = row;
    this.col = col;
    boxUi = poolBoxUi.getBoxUi(id);
    animalUi = poolAnimalUi.getAnimalUi(id);
    gShapeCustom = poolGShapeCustom.getGShapeCustom(id);
    //createGShape();
    createShadowShape();
    fitLayoutCellUi();
    addClickGShape();
  }

  public void changeId(int id){
    animalUi.reset();
    this.id = id;
    animalUi = poolAnimalUi.getAnimalUi(id);
    animalUi.setPosition(boxUi.getX() + BoardConfig.paddingAniBoxW, boxUi.getY() + BoardConfig.paddingAniBoxH);
    addClickGShape();
  }

  //test fix
//  public void animalUiReset(){
//    animalUi.reset();
//  }
//
//  public void onlyChangeId(int id){
//    this.id = id;
//    animalUi = poolAnimalUi.getAnimalUi(id);
//    animalUi.setPosition(boxUi.getX() + BoardConfig.paddingAniBoxW, boxUi.getY() + BoardConfig.paddingAniBoxH);
//    addClickGShape();
//  }

  private void createShadowShape(){
    shadowShape = poolGShapeCustom.getShadowShape();
    if(shadowShape == null){
      Gdx.app.error("CellUI.java - initGShapeCustom", "shadowShape is null");
    }
    else {
      shadowShape.setSize(boxUi.getWidth(), boxUi.getHeight());
    }

  }

  private void createGShape(){
    gShapeCustom.createRectangle(true, boxUi.getX(), boxUi.getY(), boxUi.getWidth()*0.95f, boxUi.getHeight()*0.9f);
//    gShapeCustom.setColor(252, 186, 3, 0.25f);
    gShapeCustom.setColor(0, 0, 0, 0f);
    if(gShapeCustom == null){
      Gdx.app.error("CellUI.java - initGShapeCustom", "gShape is null");
    }
    gShapeCustom.setVisible(false);
  }

  private void fitLayoutCellUi(){
    if(boxUi != null) boxUi.setPosition(col* BoardConfig.paddingCellWidth + BoardConfig.paddingGameWidth, row*BoardConfig.paddingCellHeight + BoardConfig.paddingGameHeight);
    if(boxUi != null && animalUi != null) animalUi.setPosition(boxUi.getX() + BoardConfig.paddingAniBoxW, boxUi.getY() + BoardConfig.paddingAniBoxH);
    if(boxUi != null && gShapeCustom != null) gShapeCustom.setPosition(boxUi.getX(), boxUi.getY());
    if(boxUi != null && shadowShape != null) shadowShape.setPosition(boxUi.getX(), boxUi.getY());

    x = boxUi.getX();
    y = boxUi.getY();
  }

  public void animationMoveFitLayout(){
    if(this.isAvailable) return;
    float x1 = x, y1 = y;
    float x2 = 0, y2 = 0;

    if(boxUi != null){
      x1 = col* BoardConfig.paddingCellWidth + BoardConfig.paddingGameWidth;
      y1 = row*BoardConfig.paddingCellHeight + BoardConfig.paddingGameHeight;
      boxUi.move(x1, y1, 0.2f, Interpolation.pow2Out);
    }

    if (boxUi != null && animalUi != null) {
      x2 = x1 + BoardConfig.paddingAniBoxW;
      y2 = y1 + BoardConfig.paddingAniBoxH;
      animalUi.move(x2, y2, 0.2f, Interpolation.pow2Out);
    }

    if(boxUi != null && gShapeCustom != null){
      gShapeCustom.setPosition(x1, y1);
      x = x1;
      y = y1;
    }

    if(boxUi != null && shadowShape != null){
      shadowShape.setPosition(x1, y1);
      x = x1;
      y = y1;
    }

  }

//  private void addClickGShape(){
//    gShapeCustom.addListener(new ClickListener(){
//      @Override
//      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//        cellTouchDown();
//        //animation();
//        return super.touchDown(event, x, y, pointer, button);
//      }
//    });
//  }

  private void addClickGShape(){
    animalUi.getShape().addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        cellTouchDown();
        //animation();
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void cellTouchDown(){
    System.out.println("id click: " + id);
    SoundEffect.Play(SoundEffect.clickPop);

    animationCellTouchDown(true);
    handleCellTouchDown();
  }

//  public void animationCellTouchDown(boolean isShow){
//    if(gShapeCustom == null) return;
//    if(isShow){
//      gShapeCustom.setColor(0, 204, 0, 0.3f);
//    }
//    else {
//      gShapeCustom.setColor(0, 0, 0, 0);
//    }
//  }

  public void animationCellTouchDown(boolean isShow){
    //if(gShapeCustom == null) return;
    if(idEffSelect != -1){
      GameScene.effAni.FreeEfSelect(idEffSelect);
    }

    if(isShow){
      //animalUi.getShape().setColor(0, 204, 0, 0.3f);
      idEffSelect =  GameScene.effAni.StartEffSelect(getX()+BoardConfig.paddingCellWidth/2,getY()+BoardConfig.paddingCellHeight/1.9f);
      efSelect = GameScene.effAni.getEffSelect(idEffSelect);
    }
    else {
      GameScene.effAni.FreeEfSelect(idEffSelect);
      idEffSelect = -1;
      efSelect = null;
      //animalUi.getShape().setColor(1, 1, 1, 1);
    }
  }

  private void handleCellTouchDown(){
    System.out.println("row-col-id: " + row + "-" + col + "-" + id);
    mainController.cellTouchDown(row, col);
  }

  public void setPosition(float x, float y){
    boxUi.setPosition(x, y);
    animalUi.setPosition(boxUi.getX() + BoardConfig.paddingAniBoxW, boxUi.getY() + BoardConfig.paddingAniBoxH);
    gShapeCustom.setPosition(boxUi.getX(), boxUi.getY());

    x = boxUi.getX();
    y = boxUi.getY();
  }

  public void setIsAvailable(boolean isAvailable){
    this.isAvailable = isAvailable;
  }

  public boolean getIsAvailable(){
    return isAvailable;
  }

  public int getRow(){
    return row;
  }

  public int getCol(){
    return col;
  }

  public int getId(){return id; }

  public void setRow(int row){this.row = row;}
  public void setCol(int col){this.col = col;}

  public void matchCell(){
    //goi animation
    //reset cell
    //reset();
    animationMatchCell();

    if(boxUi != null) boxUi.reset();
    if(gShapeCustom != null) gShapeCustom.reset();
//    boxUi = null;
//    gShapeCustom = null;
    this.isAvailable = true;
  }

  private void animationMatchCell(){
    GameScene.effAni.FreeEfSelect(idEffSelect);
    efSelect = null;

    GameScene.effAni.StartEff(id,getX()+BoardConfig.paddingCellWidth/2,getY()+BoardConfig.paddingCellHeight/2);
    animalUi.animationMatchCell(()->{
      if(animalUi!=null) animalUi.reset();
//      animalUi = null;
    });
  }

  public float getX(){return col* BoardConfig.paddingCellWidth + BoardConfig.paddingGameWidth;}
  public float getY(){return row*BoardConfig.paddingCellHeight + BoardConfig.paddingGameHeight;}

  public void showHint(boolean isShow){
    animationCellTouchDown(isShow);

//    if(isShow)
//      gShapeCustom.setColor(255, 0, 0, 0.3f);
//    else gShapeCustom.setColor(0, 0, 0, 0);
  }

  public void animation(){
    if(boxUi != null) boxUi.animation();
    if(animalUi != null) animalUi.animation();
  }

  public void setScale(float x, float y){
    if(boxUi != null) boxUi.setScale(x, y);
    if(animalUi != null) animalUi.setScale(x, y);
  }

  public void enableClick(boolean isEnable){
    Touchable touchable = isEnable ? Touchable.enabled : Touchable.disabled;
    gShapeCustom.setTouchable(touchable);
  }

  public void reset(){
    this.isAvailable = true;
    if(boxUi != null) boxUi.reset();
    if(animalUi != null) animalUi.reset();
    if(gShapeCustom != null) {
      gShapeCustom.reset();
    }
    shadowShape.setName("");
    shadowShape.setVisible(false);
    boxUi = null;
    animalUi = null;
    gShapeCustom = null;
    idEffSelect = -1;
  }

  public void makeShadow(boolean isShadow){
    if(isShadow){
      System.out.println("shadow show");
      shadowShape.setVisible(true);
      //animalUi.showShadow(true);
    }
    else {
      //animalUi.showShadow(false);
      shadowShape.setVisible(false);
    }
  }
}
