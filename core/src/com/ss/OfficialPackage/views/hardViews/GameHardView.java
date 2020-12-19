package com.ss.OfficialPackage.views.hardViews;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ss.OfficialPackage.configs.Config;
import com.ss.OfficialPackage.controllers.GameMainController;
import com.ss.commons.TextureAtlasC;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.effects.SoundEffect;
import com.ss.gameLogic.objects.Timer;

public class GameHardView {
  private GLayerGroup group;
  private GameMainController gameMainController;
  private Image bg;
  private Image newGameBtn;
  private Timer timer;


  public GameHardView(GLayerGroup group, GameMainController gameMainController){
     this.group = group;
     this.gameMainController = gameMainController;

    initUi();
    initEvent();
    initTimeUI();

  }

  private void initUi(){
    bg = GUI.createImage(TextureAtlasC.bgAtlas, "bg1");
    newGameBtn = GUI.createImage(TextureAtlasC.playAtlas, "cucxilau1");


    group.addActor(bg);
    group.addActor(newGameBtn);

    bg.setSize(Config.widthDevice, Config.heightDevice);

    newGameBtn.setPosition(200, 200);
  }

  private void initEvent(){
    newGameBtn.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        SoundEffect.Play(SoundEffect.click);
        gameMainController.startGame(timer);
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void initTimeUI(){
    timer = new Timer(GStage.getWorldWidth()/2, 30, 100);
    timer.setScale(0.5f);
    group.addActor(timer);
    timer.setVisible(false);
  }
}
