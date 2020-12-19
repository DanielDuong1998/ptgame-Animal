package com.ss.OfficialPackage.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.ss.commons.TextureAtlasC;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;

public class DrawLineLaser {
  private static Group group = new Group();
  private static Image laser1,laser2;
  private static float Sw_Ls1,Sh_Ls1,Sw_Ls2,Sh_Ls2;
  private static Array<Image> arrLaser1 = new Array<>();
  private static Array<Image> arrLaser2 = new Array<>();

  public static void initDrawLineLaser(){
    GStage.addToLayer(GLayer.top,group);
    for (int i=0;i<40;i++){
      Image ls1 = GUI.createImage(TextureAtlasC.playAtlas, "laser1");
      Image ls2 = GUI.createImage(TextureAtlasC.playAtlas, "laser1_2");
      group.addActor(ls1);
      group.addActor(ls2);
      ls1.setVisible(false);
      ls2.setVisible(false);
      ls1.setName("inAlive");
      ls2.setName("inAlive");
      Sw_Ls1 = ls1.getWidth();
      Sh_Ls1 = ls1.getHeight();
      Sw_Ls2 = ls2.getWidth();
      Sh_Ls2 = ls2.getHeight();
      arrLaser1.add(ls1);
      arrLaser2.add(ls2);

    }
    // laser1.setVisible(false);
    //laser2.setVisible(false);

  }
  private static Image getLaser1(){
    for (Image img: arrLaser1){
      if(img.getName().equals("inAlive")){
        img.setName("Alive");
        img.setVisible(true);
        return img;
      }
    }
    Gdx.app.error("laser null","in getlaser1()");
    return null;
  }
  private static Image getLaser2(){
    for (Image img: arrLaser2){
      if(img.getName().equals("inAlive")){
        img.setName("Alive");
        img.setVisible(true);
        return img;
      }
    }
    Gdx.app.error("laser null","in getlaser1()");
    return null;
  }
  public static void DrawLine(Vector2 obj1, Vector2 obj2){

    float x1 = obj1.x;
    float x2 = obj2.x;
    float y1 = obj1.y;
    float y2 = obj2.y;
//    System.out.println("x1: "+x1+" y1: "+y1);
//    System.out.println("x2: "+x2+" y2: "+y2);
    if(x1==x2){

      laser1 = getLaser1();
      laser1.getColor().a=1;
      laser1.setScale(1);
      // laser = GUI.createImage(TextureAtlasC.playAtlas, "laser1");
      float Sw = Sw_Ls1;
      float Sh = Math.abs(y1-y2);
//      float Sh = y1-y2;
//      System.out.println("Sw: "+Sw);
//      System.out.println("Sh: "+Sh);
      laser1.setSize(Sw,Sh);
      laser1.setOrigin(Align.center);
      if(y1-y2 <0)
        laser1.setPosition(x1-Sw/2,y1);
      else
        laser1.setPosition(x1-Sw/2,y2);

//      System.out.println("same x");
//      System.out.println("isvisible: "+laser1.getColor().a);
      aniLaser(laser1,0,2f);

    }
    if(y1==y2){
      laser2 = getLaser2();
      laser2.getColor().a=1;
      laser2.setScale(1);
      //laser = GUI.createImage(TextureAtlasC.playAtlas, "laser1_2");
      float Sw =  Math.abs(x1-x2);
      float Sh =  Sh_Ls2;
      laser2.setSize(Sw,Sh);
      laser2.setOrigin(Align.center);
      if(x1-x2<0)
        laser2.setPosition(x1,y1-Sh/2);
      else
        laser2.setPosition(x2,y1-Sh/2);

      //group.addActor(laser2);
//      System.out.println("same y");
      aniLaser(laser2,1,2f);
    }
  }

  private static void aniLaser(Image laser,int type,float dura){
    float x=0;
    float y=0;
    if(type==0){
      x=0;y=1;
    }else {
      x=1;y=0;
    }
    laser.addAction(Actions.sequence(
            Actions.parallel(
                    Actions.scaleTo(x,y,dura, Interpolation.swingOut),
                    Actions.alpha(0,dura)
            ),
            GSimpleAction.simpleAction((d, a)->{
              laser.setName("inAlive");
              return true;
            })
    ));
  }
}
