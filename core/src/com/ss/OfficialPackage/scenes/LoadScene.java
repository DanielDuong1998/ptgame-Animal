package com.ss.OfficialPackage.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.ss.OfficialPackage.configs.Config;
import com.ss.commons.TextureAtlasC;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.exSprite.GShapeSprite;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.effects.SoundEffect;

public class LoadScene extends GScreen {
  private GLayerGroup group;
  private boolean isFinishLoadAssets = false;
  private boolean isFinishTurnOfDarkShape = false;
  private GShapeSprite darkShape;

  @Override
  public void dispose() {

  }

  @Override
  public void init() {
    initGroup();
    loadImageLoading();

    SoundEffect.initSound();
    TextureAtlasC.initAtlas();
  }

  private void initGroup(){
    group = new GLayerGroup();
    GStage.addToLayer(GLayer.ui, group);
  }

  private void loadImageLoading(){
    Image bg = new Image(new Texture("textureAtlas/loading/bgload.png"));
    group.addActor(bg);
    bg.setSize(Config.widthDevice, Config.heightDevice);
    bg.setOrigin(Align.center);
    bg.setScaleY(-1);

    Image loadImg = new Image(new Texture("textureAtlas/loading/loadding.png"));
    group.addActor(loadImg);
    loadImg.setOrigin(Align.center);
    loadImg.setPosition(bg.getWidth()/2 - loadImg.getWidth()/2, bg.getHeight()/1.8f - loadImg.getHeight()/2);
    aniLoading(loadImg);

    darkShape = new GShapeSprite();
    darkShape.createRectangle(true, 0, 0, bg.getWidth(), bg.getHeight());
    darkShape.debug();
    group.addActor(darkShape);
    darkShape.setColor(0, 0, 0, 1);
    showDarkShape(darkShape, false);
  }

  private void aniLoading(Image loadImg){
    loadImg.addAction(Actions.sequence(
      Actions.rotateBy(360, 1f),
      Actions.run(()->{
        aniLoading(loadImg);
      })
    ));
  }

  private void showDarkShape(GShapeSprite darkShape, boolean isShow){
    float ap = isShow ? 1 : 0;
    darkShape.addAction(Actions.sequence(
        Actions.alpha(ap, Config.DURATION_DARK_SHAPE_LOAD_SCENE),
        GSimpleAction.simpleAction((d, a)->{
          if(!isShow) isFinishTurnOfDarkShape = true;
          return true;
        })
    ));
  }

  @Override
  public void run() {
    if(!isFinishLoadAssets){
      if(!GAssetsManager.isFinished()){
        System.out.println("chua xong");
      }
      else {
        isFinishLoadAssets = true;
        System.out.println("xong roi");
      }
    }

    if(isFinishLoadAssets && isFinishTurnOfDarkShape){
      isFinishTurnOfDarkShape = false;
      handleAfterLoading();
      System.out.println("is load finish and turn off dark shape finish");
    }
  }

  private void handleAfterLoading(){
    showDarkShape(darkShape, true);
    group.addAction(Actions.sequence(
        Actions.delay(Config.DURATION_DARK_SHAPE_LOAD_SCENE),
        Actions.run(()->{
          this.setScreen(new StartScene());
        })
    ));
    System.out.println("handle after loading");
  }

}
