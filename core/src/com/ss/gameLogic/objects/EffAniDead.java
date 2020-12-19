package com.ss.gameLogic.objects;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;
import com.ss.effects.effectWin;

public class EffAniDead {
  private Group group = new Group();
  private Array<effectWin> arrEffDeadAni      = new Array<>();
  private Array<effectWin> arrEffLight        = new Array<>();
  private Array<effectWin> arrEffSelect       = new Array<>();
  private Array<effectWin> arrEffCombo        = new Array<>();
  private Array<effectWin> arrEffHammer       = new Array<>();
  private Array<effectWin> arrEffThunder      = new Array<>();
  private int              idTemp             = 0;

  public EffAniDead(){
    GStage.addToLayer(GLayer.top,group);
    initEff();
  }
  public void initEff(){
    for (int i=0;i<20;i++){
      effectWin ef = new effectWin(effectWin.DeadAni,0,0,group);
      arrEffDeadAni.add(ef);
      effectWin ef2 = new effectWin(effectWin.Light,0,0,group);
      arrEffLight.add(ef2);
      effectWin ef3 = new effectWin(effectWin.Select,0,0,group);
      arrEffSelect.add(ef3);
      effectWin ef4 = new effectWin(effectWin.Combo,0,0,group);
      arrEffCombo.add(ef4);
      effectWin ef5 = new effectWin(effectWin.Hammer,0,0,group);
      arrEffHammer.add(ef5);
      effectWin ef6 = new effectWin(effectWin.Thunder,0,0,group);
      arrEffThunder.add(ef6);
    }
  }
  public effectWin getEfDeadAni(){
    if(arrEffDeadAni != null && arrEffDeadAni.size != 0){
      for (int i=0;i<arrEffDeadAni.size;i++){
        if(!arrEffDeadAni.get(i).isAlive){
//          System.out.println("get effect in index: "+i);
          return arrEffDeadAni.get(i);
        }
      }
    }
    return null;
  }
  public effectWin getEfLight(){
    if(arrEffLight != null && arrEffLight.size != 0){
      for (int i=0;i<arrEffLight.size;i++){
        if(arrEffLight.get(i).isAlive==false){
          return arrEffLight.get(i);
        }
      }
    }
    return null;
  }
  public effectWin getEfSelect(){
    if(arrEffSelect != null && arrEffSelect.size != 0){
      for (int i = 0 ; i < arrEffSelect.size; i++){
        if(!arrEffSelect.get(i).isAlive){
          arrEffSelect.get(i).isAlive = true;
          return arrEffSelect.get(i);
        }
      }
    }
    return null;
  }

  public effectWin getEfCombo(){
    if(arrEffCombo.size!=0&&arrEffCombo!=null){
      for (int i=0;i<arrEffCombo.size;i++){
        if(arrEffCombo.get(i).isAlive==false){
          return arrEffCombo.get(i);
        }
      }
    }
    return null;
  }

  public effectWin getEfHammer(){
    if(arrEffHammer.size!=0&&arrEffHammer!=null){
      for (int i=0;i<arrEffHammer.size;i++){
        if(arrEffHammer.get(i).isAlive==false){
          return arrEffHammer.get(i);
        }
      }
    }
    return null;
  }
  public effectWin getEfThunder(){
    if(arrEffThunder.size!=0&&arrEffThunder!=null){
      for (int i=0;i<arrEffThunder.size;i++){
        if(arrEffThunder.get(i).isAlive==false){
          return arrEffThunder.get(i);
        }
      }
    }
    return null;
  }
  public void StartEff(int id, float x, float y){
    System.out.println("check id animal: "+id);
    effectWin ef          = getEfDeadAni();
    effectWin eflight     = getEfLight();
    if(ef!=null && eflight!=null){
      eflight.setPosition(x,y);
      eflight.start();

    }
  }
  public int StartEffHammer(float x, float y){
    effectWin ef         = getEfHammer();
    if(ef!=null ){
      ef.setPosition(x,y);
      ef.start();
      return arrEffHammer.indexOf(ef,true);
    }
    return -1;
  }
  public boolean checkOnCompleHammer(int id){
    if(arrEffHammer.get(id)!=null){
      return arrEffHammer.get(id).effect.isComplete();
    }
    return false;
  }
  public boolean checkOnCompleThunder(int id){
    if(arrEffThunder.get(id)!=null){
      return arrEffThunder.get(id).effect.isComplete();
    }
    return false;
  }
  public int StartEffThunder(float x, float y){
    effectWin ef         = getEfThunder();
    if(ef!=null ){
      ef.setPosition(x,y);
      ef.start();
      return arrEffThunder.indexOf(ef,true);
    }
    return -1;
  }
  public int StartEffSelect(float x, float y){
    effectWin ef         = getEfSelect();
    if(ef!=null ){
      ef.setPosition(x,y);
      ef.start();
      idTemp = arrEffSelect.indexOf(ef,true);
      return idTemp;
    }
    return -1;
  }

  public effectWin getEffSelect(int id){
    return arrEffSelect.get(id);
  }

  public void StartEffCombo(float x, float y){
    effectWin ef         = getEfCombo();
    if(ef!=null ){
      ef.setPosition(x,y);
      ef.start();
    }
  }

  public void FreeEfSelect(int id){
    if(id==-1)
      return;
    System.out.println("size :"+arrEffSelect.size);
    System.out.println("size-idtemp :"+idTemp);

    if(id<arrEffSelect.size && arrEffSelect!=null){
      arrEffSelect.get(id).remove();
      arrEffSelect.get(id).isAlive = false;
    }
  }

  public void FreeAllEfSelect(){
    for(int i = 0; i < arrEffSelect.size; i++) {
      if(arrEffSelect.get(i).isAlive){
        FreeEfSelect(i);
      }
    }
  }
}
