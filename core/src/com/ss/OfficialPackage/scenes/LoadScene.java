package com.ss.OfficialPackage.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.ss.OfficialPackage.configs.Config;
import com.ss.core.exSprite.GShapeSprite;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.effects.SoundEffect;

public class LoadScene extends GScreen {
  private GLayerGroup group;

  @Override
  public void dispose() {

  }

  @Override
  public void init() {
    initGroup();
    loadImageLoading();

    SoundEffect.initSound();
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

    GShapeSprite darkShape = new GShapeSprite();
    darkShape.createRectangle(true, 0, 0, bg.getWidth(), bg.getHeight());
    darkShape.debug();
    group.addActor(darkShape);
    darkShape.setColor(0, 0, 0, 1);
    showDarkShape(darkShape);
  }

  private void aniLoading(Image loadImg){
    loadImg.addAction(Actions.sequence(
      Actions.rotateBy(360, 1f),
      Actions.run(()->{
        aniLoading(loadImg);
      })
    ));
  }

  private void showDarkShape(GShapeSprite darkShape){
    darkShape.addAction(Actions.sequence(
        Actions.alpha(0, 2f)
    ));
  }

  @Override
  public void run() {

  }

}
