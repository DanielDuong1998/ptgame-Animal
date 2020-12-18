package com.ss.OfficialPackage.views.logicViews.pools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.OfficialPackage.views.logicViews.AnimalUi;
import com.ss.core.util.GLayerGroup;

public class PoolAnimalUi {
  private Array<AnimalUi> animalUis;
  private GLayerGroup group;

  public PoolAnimalUi(GLayerGroup group){
    this.group = group;
    initAnimalUis();
  }

  private void initAnimalUis(){
    animalUis = new Array<>();
    //init animal id == -1
    for(int i = 0; i < BoardConfig.maxWidth*BoardConfig.maxHeight; i++) {
      AnimalUi animalUi = new AnimalUi(-1, group);
      animalUis.add(animalUi);
    }

    //init animal id != -1
    for(int i = 0; i < BoardConfig.quantityAnimal; i++) {
      for(int j = 0; j < BoardConfig.maxQuantityInitAnimal; j++) {
        AnimalUi animalUi = new AnimalUi(i, group);
        animalUis.add(animalUi);
      }
    }
  }

  public AnimalUi getAnimalUi(int id){
    if(id < -1 || id > 48) {
      Gdx.app.error("PoolAnimalUIs.java - getAnimaUIById()", "id < 0 or id > 48 -- id: " + id);
      return null;
    }

    int idStart, idEnd;
    boolean isShowShape;
    if(id == -1){
      idStart = 0;
      idEnd = BoardConfig.maxWidth*BoardConfig.maxHeight;
      isShowShape = false;
    }
    else {
      idStart = BoardConfig.maxWidth*BoardConfig.maxHeight + id*BoardConfig.maxQuantityInitAnimal;
      idEnd = BoardConfig.maxWidth*BoardConfig.maxHeight + (id + 1)*BoardConfig.maxQuantityInitAnimal;
      isShowShape = true;
    }

    for(int i = idStart; i < idEnd; i++) {
      if(animalUis.get(i).getIsAvailable()){
        animalUis.get(i).setIsAvailable(false);
        animalUis.get(i).showShape(isShowShape);
        return animalUis.get(i);
      }
    }

    Gdx.app.error("PoolAnimalUIs.java - getAnimaUIById()", "hasn't animal available!");
    return null;
  }

  public void reset(){
    for(AnimalUi animalUi : animalUis){
      if(!animalUi.getIsAvailable()){
        animalUi.reset();
      }
    }
  }
}
