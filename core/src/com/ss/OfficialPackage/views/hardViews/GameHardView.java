package com.ss.OfficialPackage.views.hardViews;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ss.GMain;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.OfficialPackage.configs.Config;
import com.ss.OfficialPackage.controllers.GameMainController;
import com.ss.commons.BitmapFontC;
import com.ss.commons.LabelC;
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
  private Image btnNextLv;
  private Timer timer;
  private Image btnShuffle, btnHint, btnThunder, btnPause;
  private boolean isClick = false;
  private LabelC txtScore, txtLevel;

  public GameHardView(GLayerGroup group, GameMainController gameMainController){
     this.group = group;
     this.gameMainController = gameMainController;

    initUi();
    initEvent();
    initTimeUI();

    group.addAction(Actions.sequence(
      Actions.delay(.8f),
      Actions.run(()->{
        gameMainController.startGame(timer);
      })
    ));
  }

  private void initUi(){
    bg = GUI.createImage(TextureAtlasC.bgAtlas, "bg1");
    btnShuffle = GUI.createImage(TextureAtlasC.playAtlas, "btn_random");
    btnHint = GUI.createImage(TextureAtlasC.playAtlas, "btn_hint");
    btnThunder = GUI.createImage(TextureAtlasC.playAtlas, "btn_firework");
    btnPause = GUI.createImage(TextureAtlasC.playAtlas, "btn_pause");
    btnNextLv = GUI.createImage(TextureAtlasC.playAtlas, "cucxilau1");

    txtScore = new LabelC("Score: 0", new Label.LabelStyle(BitmapFontC.btnFont, null));;
    txtLevel = new LabelC("Level: 1", new Label.LabelStyle(BitmapFontC.btnFont, null));;


    group.addActor(bg);
    group.addActor(btnNextLv);
    group.addActor(btnShuffle);
    group.addActor(btnHint);
    group.addActor(btnThunder);
    group.addActor(btnPause);
    group.addActor(txtScore);
    group.addActor(txtLevel);

    btnNextLv.setVisible(BoardConfig.isShowBtnNextLv);

    bg.setSize(Config.widthDevice, Config.heightDevice);

    btnShuffle.setPosition(20, 0);
    btnHint.setPosition(btnShuffle.getX() + btnShuffle.getWidth() + 20, 0);
    btnThunder.setPosition(btnHint.getX() + btnHint.getWidth() + 20, 0);
    btnPause.setPosition(Config.widthDevice - btnPause.getWidth() - 20, 0);
    txtScore.setPosition(btnThunder.getX() + btnHint.getWidth() + 20, 0);
    txtLevel.setPosition(Config.widthDevice*0.65f, 0);
    btnNextLv.setPosition(txtLevel.getX() + txtLevel.getWidth() + 20, 0);

    gameMainController.addTxtScore(txtScore);
    gameMainController.addTxtLevel(txtLevel);
  }

  private void initEvent(){
    btnNextLv.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!isClick){
          isClick = true;
          SoundEffect.Play(SoundEffect.click);
          GMain.prefs.putBoolean("isContinueInLevel", false);
          GMain.prefs.flush();
          gameMainController.newGame();
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
          SoundEffect.Play(SoundEffect.click1);
          isClick = true;
          gameMainController.pause(true);
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
    timer = new Timer(GStage.getWorldWidth()/2, 30, 360);
    timer.setScale(0.5f);
    group.addActor(timer);
    timer.setVisible(false);
  }
}
