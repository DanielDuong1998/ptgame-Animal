package com.ss.OfficialPackage.views.logicViews.pools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.OfficialPackage.controllers.GameMainController;
import com.ss.OfficialPackage.views.logicViews.CellUi;

public class PoolCellUi {
  private Array<CellUi> cellUis;
  private PoolBoxUi poolBoxUi;
  private PoolAnimalUi poolAnimalUi;
  private PoolGShapeCustom poolGShapeCustom;
  private GameMainController mainController;

  public PoolCellUi(PoolBoxUi poolBoxUi, PoolAnimalUi poolAnimalUi, PoolGShapeCustom poolGShapeCustom, GameMainController mainController){
    this.poolBoxUi = poolBoxUi;
    this.poolAnimalUi = poolAnimalUi;
    this.poolGShapeCustom = poolGShapeCustom;
    this.mainController = mainController;
    initCellUis();
  }

  private void initCellUis(){
    cellUis = new Array<>();
    for(int i = 0; i < BoardConfig.maxWidth*BoardConfig.maxHeight; i++) {
      CellUi cellUi = new CellUi(poolBoxUi, poolAnimalUi, poolGShapeCustom, mainController);
      cellUis.add(cellUi);
    }
  }

  public CellUi getCellUi(){
    for(CellUi cellUi : cellUis){
      if(cellUi.getIsAvailable()){
        cellUi.setIsAvailable(false);
        return cellUi;
      }
    }

    Gdx.app.error("PoolCellUi.java - getCellUi", "hasn't cellUi");
    return null;
  }

  public void reset(){
    for(CellUi cellUi : cellUis){
      if(!cellUi.getIsAvailable()){
        cellUi.reset();
      }
    }
  }
}
