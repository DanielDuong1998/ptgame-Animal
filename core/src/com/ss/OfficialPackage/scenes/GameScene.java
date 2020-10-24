package com.ss.OfficialPackage.scenes;

//import com.ss.OfficialPackage.controllers.MainController;
//import com.ss.OfficialPackage.models.RSA.RSA;
//import com.ss.OfficialPackage.views.ClassicScene.HardView.ClassicHardView;
import com.ss.commons.TextureAtlasC;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;

public class GameScene extends GScreen {
  private GLayerGroup hardViewGroup;
//  private MainController mainController;
//  private ClassicHardView classicHardView;

  @Override
  public void dispose() {

  }

  @Override
  public void init() {
    System.out.println("initClassic scenes");
    //initAtlas();
    initGroup();
//    initController();
//    initHardView();
  }

//  private void rsa(){
//    try{
//      testRSA();
//    }
//    catch (Exception e){
//
//    }
//  }

//  private void testRSA(){
//    RSA rsa = new RSA();
//    try{
//      String str = rsa.Encrypt("Duong Khang");
//      System.out.println("str-: " + str);
//      String str2 = rsa.Decrypt(str);
//      System.out.println("str2: " + str2);
//    }catch (Exception e){
//
//    };
//
//  }

  @Override
  public void run() {

  }

//  private void initAtlas(){
//    TextureAtlasC.backgroundAtlas = GAssetsManager.getTextureAtlas("bg.atlas");
//    TextureAtlasC.playAtlas = GAssetsManager.getTextureAtlas("play.atlas");
//    TextureAtlasC.animalsAtlas = GAssetsManager.getTextureAtlas("Animals.atlas");
//  }

  private void initGroup(){
    hardViewGroup = new GLayerGroup();
    GStage.addToLayer(GLayer.ui, hardViewGroup);
  }

//  private void initController(){
//    mainController = new MainController();
//  }
//
//  private void initHardView(){
//    classicHardView = new ClassicHardView(hardViewGroup, mainController);
//  }
}
