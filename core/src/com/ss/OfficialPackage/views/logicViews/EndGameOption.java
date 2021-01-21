package com.ss.OfficialPackage.views.logicViews;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ss.OfficialPackage.configs.BoardConfig;
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

public class EndGameOption {
  private Group group;
  private GShapeSprite gshape;
  private Image frame, frameTitle, btnLobby, btnAgain, btnNext;
  private LabelC txtLobby, txtAgain, txtNext, txtTitle;
  private LabelC txtLevel, txtTime, txtScore, txtBestCombo;
  private int clickCount = 0;
  private GameMainController gameMainController;

  public EndGameOption(GameMainController gameMainController){
    this.gameMainController = gameMainController;

    initGroup();
    initUI();
    addEventClick();
    showEndGamePanel(false, true, 0, 0, 0);
  }

  private void initGroup(){
    group = new Group();
    GStage.addToLayer(GLayer.top, group);
  }

  private void initUI(){
    gshape = new GShapeSprite();
    gshape.createRectangle(true, 0, 0, Config.widthDevice, Config.heightDevice);
    gshape.setColor(0, 0, 0, 0.4f);

    frame = GUI.createImage(TextureAtlasC.componentAtlas, "panel");
    frameTitle = GUI.createImage(TextureAtlasC.componentAtlas, "frameTitle");
    btnLobby = GUI.createImage(TextureAtlasC.componentAtlas, "btn_yellow");
    btnAgain = GUI.createImage(TextureAtlasC.componentAtlas, "btn_red");
    btnNext = GUI.createImage(TextureAtlasC.componentAtlas, "btn_yellow");
    txtLobby = new LabelC("LOBBY", new Label.LabelStyle(BitmapFontC.btnFont, null));
    txtAgain = new LabelC("AGAIN", new Label.LabelStyle(BitmapFontC.btnFont, null));
    txtNext = new LabelC("NEXT LV", new Label.LabelStyle(BitmapFontC.btnFont, null));
    txtTitle = new LabelC("LOSER", new Label.LabelStyle(BitmapFontC.btnFont, null));
    txtTime = new LabelC("Time remaining: 56s" , new Label.LabelStyle(BitmapFontC.btnFont, null));
    txtScore = new LabelC("Score: 2645", new Label.LabelStyle(BitmapFontC.btnFont, null));
    txtBestCombo = new LabelC("Best combo: 5", new Label.LabelStyle(BitmapFontC.btnFont, null));
    txtLevel = new LabelC("LEVEL ", new Label.LabelStyle(BitmapFontC.btnFont, null));

    group.addActor(gshape);
    group.addActor(frame);
    group.addActor(frameTitle);
    group.addActor(btnLobby);
    group.addActor(btnAgain);
    group.addActor(btnNext);
    group.addActor(txtLobby);
    group.addActor(txtAgain);
    group.addActor(txtNext);
    group.addActor(txtLobby);
    group.addActor(txtAgain);
    group.addActor(txtNext);
    group.addActor(txtTitle);
    group.addActor(txtTime);
    group.addActor(txtScore);
    group.addActor(txtBestCombo);
    group.addActor(txtLevel);

    frame.setSize(frame.getWidth()*1.5f, frame.getHeight()*1.5f);
    frameTitle.setSize(frameTitle.getWidth()*1.5f, frameTitle.getHeight()*1.5f);

    initLayoutUi();
  }

  private void initLayoutUi(){
    frame.setPosition((Config.widthDevice - frame.getWidth())/2,(Config.heightDevice - frame.getHeight())/2 + (float)Config.heightDevice/20);
    frameTitle.setPosition((Config.widthDevice - frameTitle.getWidth())/2,frame.getY() - frameTitle.getHeight()/2 + (float)Config.heightDevice/20);
    txtTitle.setPosition(frameTitle.getX() + (frameTitle.getWidth() - txtTitle.getWidth())/2, frameTitle.getY() + frameTitle.getHeight()*0.2f);

    btnAgain.setPosition(frame.getX() + (frame.getWidth() - btnAgain.getWidth())/2, frame.getY() + frame.getHeight() - btnAgain.getHeight()*1.2f);
    btnLobby.setPosition(btnAgain.getX() - btnLobby.getWidth()*1.1f, frame.getY() + frame.getHeight() - btnAgain.getHeight()*1.25f);
    btnNext.setPosition(btnAgain.getX() + btnAgain.getWidth()*1.1f, frame.getY() + frame.getHeight() - btnAgain.getHeight()*1.25f);
    txtAgain.setPosition(btnAgain.getX() + (btnAgain.getWidth() - txtAgain.getWidth())/2, btnAgain.getY() + (btnAgain.getHeight() - txtAgain.getHeight())/2);
    txtLobby.setPosition(btnLobby.getX() + (btnLobby.getWidth() - txtLobby.getWidth())/2, btnLobby.getY() + (btnLobby.getHeight() - txtLobby.getHeight())/2);
    txtNext.setPosition(btnNext.getX() + (btnNext.getWidth() - txtNext.getWidth())/2, btnNext.getY() + (btnNext.getHeight() - txtNext.getHeight())/2);

    txtLevel.setPosition(frame.getX() + (frame.getWidth() - txtLevel.getWidth())/2, frame.getY() + frame.getHeight()*.25f);
    txtTime.setPosition(frame.getX() + frame.getWidth()*0.3f, txtLevel.getY() + frame.getHeight()*0.1f);
    txtScore.setPosition(frame.getX() + frame.getWidth()*0.3f, txtTime.getY() + frame.getHeight()*0.1f);
    txtBestCombo.setPosition(frame.getX() + frame.getWidth()*0.3f, txtScore.getY() + frame.getHeight()*0.1f);
  }

  private void addEventClick(){
    btnLobby.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        clickCount++;
        if(clickCount <= 1){
          System.out.println("btn lobby click");
          SoundEffect.Playmusic(SoundEffect.click);
          gameMainController.setScreen(new StartScene());
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        clickCount--;
      }
    });

    btnAgain.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        clickCount++;
        if(clickCount <= 1){
          System.out.println("btn again click");
          SoundEffect.Playmusic(SoundEffect.click);
          gameMainController.playAgain();
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        clickCount--;
      }
    });

    btnNext.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        clickCount++;
        if(clickCount <= 1){
          System.out.println("btn next click");
          SoundEffect.Playmusic(SoundEffect.click);
          gameMainController.nextLevel();
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

  public void showEndGamePanel(boolean isShow, boolean isWin, int timeRemaining, int score, int bestCombo){
    String title = isWin ? "WINNER" : "LOSER";

    txtLevel.setText("LEVEL " + BoardConfig.level);
    txtTitle.setText(title);
    txtTime.setText("Time remaining: " + timeRemaining);
    txtScore.setText("Score: " + score);
    txtBestCombo.setText("BestCombo: "  + bestCombo);

    btnNext.setVisible(isWin);
    txtNext.setVisible(isWin);
    group.setVisible(isShow);
  }
}
