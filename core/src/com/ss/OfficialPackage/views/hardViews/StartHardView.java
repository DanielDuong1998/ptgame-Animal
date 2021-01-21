package com.ss.OfficialPackage.views.hardViews;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ss.GMain;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.OfficialPackage.configs.Config;
import com.ss.OfficialPackage.controllers.StartMainController;
import com.ss.OfficialPackage.scenes.GameScene;
import com.ss.OfficialPackage.views.logicViews.PauseOption;
import com.ss.commons.BitmapFontC;
import com.ss.commons.LabelC;
import com.ss.commons.TextureAtlasC;
import com.ss.core.exSprite.GShapeSprite;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;
import com.ss.effects.SoundEffect;

public class StartHardView {
  private GLayerGroup group;
  private StartMainController startMainController;
  private Image btnContinue, btnNewGame, btnSettingUd, btnShop, btnLeaderBoard, btnSetting, btnGuide;
  private GShapeSprite gshapeBtnContinue, gshapeBtnNewGame, gshapeBtnShop, gshapeBtnLeaderBoard, gshapeBtnSetting, gshapeBtnGuide;
  private LabelC txtBtnContinue, txtBtnNewGame, txtBtnSetting;
  private boolean isContinue;
  private boolean isClick = false;


  public StartHardView(GLayerGroup group, StartMainController startMainController){
    this.group = group;
    this.startMainController = startMainController;

    initIsContinue();
    initUi();
    initEvent();
    //PauseOption pauseOption = new PauseOption();
  }

  private void initIsContinue(){
    isContinue = GMain.prefs.getBoolean("isContinue", false);
  }

  private void initUi(){
    Image bg = GUI.createImage(TextureAtlasC.bgAtlas, "bg1");
    Image logo = GUI.createImage(TextureAtlasC.componentAtlas, "logo");
    btnShop = GUI.createImage(TextureAtlasC.componentAtlas, "btn_shop");
    btnLeaderBoard = GUI.createImage(TextureAtlasC.componentAtlas, "btn_leaderboard");
    btnSetting = GUI.createImage(TextureAtlasC.componentAtlas, "btn_setting");
    btnGuide = GUI.createImage(TextureAtlasC.componentAtlas, "btn_guide");

    group.addActor(bg);
    group.addActor(logo);
    group.addActor(btnShop);
    group.addActor(btnLeaderBoard);
    group.addActor(btnSetting);
    group.addActor(btnGuide);

    bg.setSize(Config.widthDevice, Config.heightDevice);
    logo.setSize(logo.getWidth()*1.2f, logo.getHeight()*1.2f);

    logo.setPosition(Config.widthDevice/2 - logo.getWidth()/2, Config.heightDevice*0.1f);
    btnShop.setPosition(Config.widthDevice/2 - btnShop.getWidth()/2 - 2.25f*btnShop.getWidth(), Config.heightDevice*0.75f);
    btnLeaderBoard.setPosition(btnShop.getX() + btnShop.getWidth() + btnShop.getWidth()*Config.RATIO_MARGIN_CIRCLE_BUTTON, btnShop.getY());
    btnSetting.setPosition(btnLeaderBoard.getX() + btnLeaderBoard.getWidth() + btnLeaderBoard.getWidth()*Config.RATIO_MARGIN_CIRCLE_BUTTON, btnShop.getY());
    btnGuide.setPosition(btnSetting.getX() + btnSetting.getWidth() + btnSetting.getWidth()*Config.RATIO_MARGIN_CIRCLE_BUTTON, btnShop.getY());
    btnSetting.setVisible(false);
    btnShop.setVisible(false);
    btnLeaderBoard.setVisible(false);
    btnGuide.setVisible(false);


    logo.setOrigin(Align.center);
    aniLogo(logo, true);

    initBtnHasText();
    initBtnGshape();
  }

  private void aniLogo(Image logo, boolean isOut){
    float scaleX = isOut ? 1.1f : 0.9f;
    float scaleY = isOut ? 0.9f : 1.1f;
    logo.addAction(Actions.sequence(
       Actions.scaleTo(scaleX, scaleY, 3f),
       Actions.run(()-> aniLogo(logo, !isOut))
    ));
  }

  private void initBtnHasText(){
    btnContinue = GUI.createImage(TextureAtlasC.componentAtlas, "btn_yellow");
    btnNewGame = GUI.createImage(TextureAtlasC.componentAtlas, "btn_yellow");
    btnSettingUd = GUI.createImage(TextureAtlasC.componentAtlas, "btn_yellow");

    group.addActor(btnContinue);
    group.addActor(btnNewGame);
    group.addActor(btnSettingUd);

    btnContinue.setVisible(isContinue);

    btnContinue.setPosition(Config.widthDevice/2 - btnContinue.getWidth()/2, Config.heightDevice/2 - btnContinue.getHeight()/2);
    float x = isContinue ? Config.widthDevice/2 - btnNewGame.getWidth()/2 : btnContinue.getX();
    float y = isContinue ? Config.heightDevice/2 - btnNewGame.getHeight()/2 + btnContinue.getHeight()*1.2f : btnContinue.getY() + btnNewGame.getHeight()/2;
    btnNewGame.setPosition(x, y);
    btnSettingUd.setPosition(btnNewGame.getX(), btnNewGame.getY() + btnNewGame.getHeight()*1.2f);

    initTextOfBtn();
  }

  private void initBtnGshape(){
    gshapeBtnContinue     = new GShapeSprite();
    gshapeBtnNewGame      = new GShapeSprite();
    gshapeBtnShop         = new GShapeSprite();
    gshapeBtnLeaderBoard  = new GShapeSprite();
    gshapeBtnSetting      = new GShapeSprite();
    gshapeBtnGuide        = new GShapeSprite();

    gshapeBtnContinue.createRectangle(true, btnContinue.getX(), btnContinue.getY(), btnContinue.getWidth(), btnContinue.getHeight());
    gshapeBtnNewGame.createRectangle(true, btnNewGame.getX(), btnNewGame.getY(), btnNewGame.getWidth(), btnNewGame.getHeight());
    gshapeBtnShop.createRectangle(true, btnShop.getX(), btnShop.getY(), btnShop.getWidth(), btnShop.getHeight());
    gshapeBtnLeaderBoard.createRectangle(true, btnLeaderBoard.getX(), btnLeaderBoard.getY(), btnLeaderBoard.getWidth(), btnLeaderBoard.getHeight());
    gshapeBtnSetting.createRectangle(true, btnSettingUd.getX(), btnSettingUd.getY(), btnSettingUd.getWidth(), btnSettingUd.getHeight());
    gshapeBtnGuide.createRectangle(true, btnGuide.getX(), btnGuide.getY(), btnGuide.getWidth(), btnGuide.getHeight());

    group.addActor(gshapeBtnContinue);
    group.addActor(gshapeBtnNewGame);
    group.addActor(gshapeBtnShop);
    group.addActor(gshapeBtnLeaderBoard);
    group.addActor(gshapeBtnSetting);
    group.addActor(gshapeBtnGuide);

    gshapeBtnContinue.setColor(0, 0, 0, 0);
    gshapeBtnNewGame.setColor(0, 0, 0, 0);
    gshapeBtnShop.setColor(0, 0, 0, 0);
    gshapeBtnLeaderBoard.setColor(0, 0, 0, 0);
    gshapeBtnSetting.setColor(0, 0, 0, 0);
    gshapeBtnGuide.setColor(0, 0, 0, 0);

    gshapeBtnContinue.setVisible(isContinue);

    gshapeBtnLeaderBoard.setVisible(false);
    gshapeBtnGuide.setVisible(false);
    gshapeBtnShop.setVisible(false);
//    gshapeBtnSetting.setVisible(false);

  }

  private void initTextOfBtn(){
    txtBtnContinue = new LabelC("Continue", new Label.LabelStyle(BitmapFontC.btnFont, null));
    txtBtnNewGame = new LabelC("New Game", new Label.LabelStyle(BitmapFontC.btnFont, null));
    txtBtnSetting = new LabelC("Setting", new Label.LabelStyle(BitmapFontC.btnFont, null));

    group.addActor(txtBtnContinue);
    group.addActor(txtBtnNewGame);
    group.addActor(txtBtnSetting);

    txtBtnContinue.setVisible(isContinue);

    txtBtnContinue.setPosition(btnContinue.getX() + (btnContinue.getWidth()-txtBtnContinue.getWidth())/2, btnContinue.getY() + (btnContinue.getHeight()- txtBtnContinue.getHeight())/2);
    txtBtnNewGame.setPosition(btnNewGame.getX() + (btnNewGame.getWidth()-txtBtnNewGame.getWidth())/2, btnNewGame.getY() + (btnNewGame.getHeight()- txtBtnNewGame.getHeight())/2);
    txtBtnSetting.setPosition(btnSettingUd.getX() + (btnSettingUd.getWidth() - txtBtnSetting.getWidth())/2, btnSettingUd.getY() + (btnSettingUd.getHeight() - txtBtnSetting.getHeight())/2);
  }

  private void initEvent(){
    gshapeBtnContinue.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!isClick){
          isClick = true;
          System.out.println("new game click");
          SoundEffect.Play(SoundEffect.click);
          startMainController.setScreen(new GameScene());
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        isClick = false;
      }
    });

    gshapeBtnNewGame.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!isClick){
          isClick = true;
          System.out.println("new game click");
          SoundEffect.Play(SoundEffect.click);
          GMain.prefs.putBoolean("isContinue", false);
          GMain.prefs.putBoolean("isContinueInLevel", false);
          BoardConfig.level = 1;
          GMain.prefs.flush();
          startMainController.setScreen(new GameScene());
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        isClick = false;
      }
    });

    gshapeBtnShop.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!isClick){
          isClick = true;
          SoundEffect.Play(SoundEffect.click);
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        isClick = false;
      }
    });

    gshapeBtnLeaderBoard.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!isClick){
          isClick = true;
          SoundEffect.Play(SoundEffect.click);
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        isClick = false;
      }
    });

    gshapeBtnSetting.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!isClick){
          isClick = true;
          SoundEffect.Play(SoundEffect.click);
          startMainController.showSettingPanel(true);
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        isClick = false;
      }
    });

    gshapeBtnGuide.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!isClick){
          isClick = true;
          SoundEffect.Play(SoundEffect.click);
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


}
