package com.ss.OfficialPackage.views.logicViews;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ss.OfficialPackage.configs.Config;
import com.ss.OfficialPackage.controllers.GameMainController;
import com.ss.OfficialPackage.scenes.StartScene;
import com.ss.commons.BitmapFontC;
import com.ss.commons.LabelC;
import com.ss.commons.TextureAtlasC;
import com.ss.core.exSprite.GShapeSprite;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.effects.SoundEffect;

public class PauseOptionC {
  private Group group;
  private GShapeSprite gshape;
  private Image frame, frameTitle, btnMusicOn, btnMusicOff, btnVFXOn, btnVFXOff, btnExit, btnClose;
  private LabelC txtBtnLobby, txtBtnClose;
  private LabelC title;
  private int clickCount = 0;

  public PauseOptionC(){

    initGroup();
    initUI();
    addEventClick();
    showSettingPanel(false);
  }

  private void initGroup(){
    group = new Group();
    GStage.addToLayer(GLayer.top, group);
  }

  private void initUI(){
    gshape = new GShapeSprite();
    gshape.createRectangle(true, 0, 0, Config.widthDevice, Config.heightDevice);
    gshape.setColor(0, 0, 0, 0.8f);

    frame = GUI.createImage(TextureAtlasC.componentAtlas, "panel");
    frameTitle = GUI.createImage(TextureAtlasC.componentAtlas, "frameTitle");
    btnMusicOn = GUI.createImage(TextureAtlasC.componentAtlas, "btnMusicOn");
    btnMusicOff = GUI.createImage(TextureAtlasC.componentAtlas, "btnMusicOff");
    btnVFXOn = GUI.createImage(TextureAtlasC.componentAtlas, "btnSfxOn");
    btnVFXOff = GUI.createImage(TextureAtlasC.componentAtlas, "btnSfxOff");
    btnExit = GUI.createImage(TextureAtlasC.componentAtlas, "btn_red");
    btnClose = GUI.createImage(TextureAtlasC.componentAtlas, "btn_yellow");

    txtBtnLobby = new LabelC("LOBBY", new Label.LabelStyle(BitmapFontC.btnFont, null));
    txtBtnClose = new LabelC("CLOSE", new Label.LabelStyle(BitmapFontC.btnFont, null));

    frame.setSize(frame.getWidth()*1.5f, frame.getHeight()*1.5f);

    btnMusicOff.setVisible(false);
    btnVFXOff.setVisible(false);

    group.addActor(gshape);
    group.addActor(frame);
    group.addActor(frameTitle);
    group.addActor(btnMusicOn);
    group.addActor(btnMusicOff);
    group.addActor(btnVFXOn);
    group.addActor(btnVFXOff);
    group.addActor(btnExit);
    group.addActor(btnClose);

    group.addActor(txtBtnLobby);
    group.addActor(txtBtnClose);

    initLayoutUi();
  }

  private void initLayoutUi(){
    frame.setPosition((Config.widthDevice - frame.getWidth())/2,(Config.heightDevice - frame.getHeight())/2);
    frameTitle.setPosition((Config.widthDevice - frameTitle.getWidth())/2,frame.getY() - frameTitle.getHeight()/2);
    btnExit.setPosition((Config.widthDevice - btnExit.getWidth())/2 - 0.6f*btnExit.getWidth(), (Config.heightDevice - btnExit.getHeight())/2 + btnExit.getHeight());
    btnClose.setPosition((frame.getX() + (frame.getWidth() - btnClose.getWidth())/2), (Config.heightDevice - btnClose.getHeight())/2 + btnClose.getHeight());
    btnMusicOn.setPosition((Config.widthDevice - btnMusicOn.getWidth())/2 - 0.6f*btnMusicOn.getWidth(), (Config.heightDevice - btnMusicOn.getHeight())/2 - btnMusicOn.getHeight());
    btnMusicOff.setPosition((Config.widthDevice - btnMusicOn.getWidth())/2 - 0.6f*btnMusicOn.getWidth(), (Config.heightDevice - btnMusicOn.getHeight())/2 - btnMusicOn.getHeight());
    btnVFXOn.setPosition((Config.widthDevice - btnMusicOn.getWidth())/2 + 0.6f*btnVFXOn.getWidth(), (Config.heightDevice - btnVFXOn.getHeight())/2 - btnVFXOn.getHeight());
    btnVFXOff.setPosition((Config.widthDevice - btnMusicOn.getWidth())/2 + 0.6f*btnVFXOn.getWidth(), (Config.heightDevice - btnVFXOn.getHeight())/2 - btnVFXOn.getHeight());

    txtBtnLobby.setPosition(btnExit.getX() + (btnExit.getWidth() - txtBtnLobby.getWidth())/2,
            btnExit.getY() + (btnExit.getHeight() - txtBtnLobby.getHeight())/2);
    txtBtnClose.setPosition(btnClose.getX() + (btnClose.getWidth() - txtBtnClose.getWidth())/2,
            btnClose.getY() + (btnClose.getHeight() - txtBtnClose.getHeight())/2);

    btnExit.setVisible(false);
    txtBtnLobby.setVisible(false);
  }

  private void addEventClick(){
    btnMusicOn.setVisible(!SoundEffect.muteMusic);
    btnMusicOff.setVisible(SoundEffect.muteMusic);
    btnVFXOn.setVisible(!SoundEffect.muteSfx);
    btnVFXOff.setVisible(SoundEffect.muteSfx);

    btnMusicOn.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        clickCount++;
        if(clickCount <= 1){
          btnMusicOn.setVisible(false);
          btnMusicOff.setVisible(true);
          SoundEffect.setMuteMusic(true);
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        clickCount--;
      }
    });

    btnMusicOff.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        clickCount++;
        if(clickCount <= 1){
          btnMusicOff.setVisible(false);
          btnMusicOn.setVisible(true);
          SoundEffect.setMuteMusic(false);
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        clickCount--;
      }
    });

    btnVFXOn.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        clickCount++;
        if(clickCount <= 1){
          btnVFXOn.setVisible(false);
          btnVFXOff.setVisible(true);
          SoundEffect.setMuteVfx(true);
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        clickCount--;
      }
    });

    btnVFXOff.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        clickCount++;
        if(clickCount <= 1){
          btnVFXOff.setVisible(false);
          btnVFXOn.setVisible(true);
          SoundEffect.setMuteVfx(false);
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        clickCount--;
      }
    });

    btnClose.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        clickCount++;
        if(clickCount <= 1){
          SoundEffect.Play(SoundEffect.click);
          //gameMainController.pause(false);
          showSettingPanel(false);
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        clickCount--;
      }
    });

    btnExit.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        clickCount++;
        if(clickCount <= 1){
          SoundEffect.Play(SoundEffect.click);
          //gameMainController.setScreen(new StartScene());
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        clickCount--;
      }
    });
  }

  public void showSettingPanel(boolean isShow){
    btnMusicOn.setVisible(!SoundEffect.muteMusic);
    btnMusicOff.setVisible(SoundEffect.muteMusic);
    btnVFXOn.setVisible(!SoundEffect.muteSfx);
    btnVFXOff.setVisible(SoundEffect.muteSfx);

    group.setVisible(isShow);
  }
}
