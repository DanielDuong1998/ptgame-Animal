package com.ss.gameLogic.objects;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.exSprite.GShapeSprite;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;

public class EffHammer {
  private EffAniDead      effAniDead;
  private Group group         = new Group();
  private GShapeSprite darkBg        = new GShapeSprite();
  private Array<Vector2> arrPos;
  private int             indexHammer,indexThunder;
  public EffHammer(EffAniDead effAniDead, Array<Vector2> arrPos,Runnable callbackHammer, Runnable callbackThunder){
    this.effAniDead = effAniDead;
    this.arrPos     = arrPos;
    GStage.addToLayer(GLayer.ui,group);
    darkBg.createRectangle(true,-GStage.getWorldWidth()/2,-GStage.getWorldHeight()/2,GStage.getWorldWidth(),GStage.getWorldHeight());
    darkBg.setColor(0,0,0,0.7f);
    group.addActor(darkBg);
    group.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2);
    group.setScale(0);
    group.addAction(Actions.sequence(
            Actions.scaleTo(1,1,0.5f, Interpolation.swingOut),
            GSimpleAction.simpleAction((d, a)->{
              effectHamer(()->{
                darkBg.setColor(0,0,0,0);
                group.addAction(Actions.run(callbackHammer));
                System.out.println("done effect hammer");
                effectThunder(()->{
                  System.out.println("done effect thunder");
                  darkBg.remove();
                  group.addAction(Actions.run(callbackThunder));
                });
              });
              return true;
            })
    ));


  }
  private void effectHamer(Runnable runnable){
    indexHammer   = effAniDead.StartEffHammer(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2);
    group.addAction(GSimpleAction.simpleAction((d,a)->{
      if(effAniDead.checkOnCompleHammer(indexHammer)){
        group.addAction(Actions.run(runnable));
        return true;
      }
      return false;
    }));
  }
  private void effectThunder(Runnable runnable){
    if(arrPos.size!=0){
      for (int i=0; i<arrPos.size; i++){
//        int finalI = i;
//        Tweens.setTimeout(group,0.5f*i,()->{
        indexThunder =    effAniDead.StartEffThunder(arrPos.get(i).x,arrPos.get(i).y);
        if(i==arrPos.size-1){
          group.addAction(GSimpleAction.simpleAction((d,a)->{
            if(effAniDead.checkOnCompleThunder(indexThunder)){
              group.addAction(Actions.run(runnable));
              return true;
            }
            return false;
          }));
        }
//        });
      }
    }

  }
}
