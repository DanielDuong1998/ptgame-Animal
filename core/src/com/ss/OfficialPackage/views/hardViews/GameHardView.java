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
  private Image btnShuffle, btnHint, btnThunder, btnPause;
  private boolean isClick = false;

  public GameHardView(GLayerGroup group, GameMainController gameMainController){
     this.group = group;
     this.gameMainController = gameMainController;

    initUi();
    initEvent();
    initTimeUI();

  }

  private void initUi(){
    bg = GUI.createImage(TextureAtlasC.bgAtlas, "bg1");
    btnShuffle = GUI.createImage(TextureAtlasC.playAtlas, "btn_random");
    btnHint = GUI.createImage(TextureAtlasC.playAtlas, "btn_hint");
    btnThunder = GUI.createImage(TextureAtlasC.playAtlas, "btn_firework");
    btnPause = GUI.createImage(TextureAtlasC.playAtlas, "btn_pause");
    newGameBtn = GUI.createImage(TextureAtlasC.playAtlas, "cucxilau1");


    group.addActor(bg);
    group.addActor(newGameBtn);
    group.addActor(btnShuffle);
    group.addActor(btnHint);
    group.addActor(btnThunder);
    group.addActor(btnPause);

    bg.setSize(Config.widthDevice, Config.heightDevice);

    newGameBtn.setPosition(200, 200);
    btnShuffle.setPosition(20, 0);
    btnHint.setPosition(btnShuffle.getX() + btnShuffle.getWidth() + 20, 0);
    btnThunder.setPosition(btnHint.getX() + btnHint.getWidth() + 20, 0);
    btnPause.setPosition(Config.widthDevice - btnPause.getWidth() - 20, 0);
  }

  private void initEvent(){
    newGameBtn.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!isClick){
          isClick = true;
          SoundEffect.Play(SoundEffect.click);
          gameMainController.startGame(timer);
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        isClick = false;
      }
    });

    btnShuffle.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!isClick){
          isClick = true;
          SoundEffect.Play(SoundEffect.click1);
          gameMainController.shuffle();
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        isClick = false;
      }
    });
    btnHint.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!isClick){
          isClick = true;
          SoundEffect.Play(SoundEffect.click1);
          gameMainController.findHint();
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        isClick = false;
      }
    });
    btnThunder.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!isClick){
          isClick = true;
          SoundEffect.Play(SoundEffect.click1);
          gameMainController.thunder2();
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        isClick = false;
      }
    });
    btnPause.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!isClick){
          isClick = true;
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        isClick = false;
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
