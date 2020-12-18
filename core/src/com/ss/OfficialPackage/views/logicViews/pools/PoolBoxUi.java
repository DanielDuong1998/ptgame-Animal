package com.ss.OfficialPackage.views.logicViews.pools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.OfficialPackage.views.logicViews.BoxUi;
import com.ss.core.util.GLayerGroup;

public class PoolBoxUi {
  private Array<BoxUi> boxUis;
  private GLayerGroup group;

  public PoolBoxUi(GLayerGroup group){
    this.group = group;
    initBoxUis();
  }

  private void initBoxUis(){
    boxUis = new Array<>();
    for(int i = 0; i < BoardConfig.maxWidth*BoardConfig.maxHeight; i++) {
      BoxUi boxUi = new BoxUi(group);
      boxUis.add(boxUi);
    }
  }

  public BoxUi getBoxUi(int id){
    for(int i = 0; i < boxUis.size; i++) {
      if(boxUis.get(i).getIsAvailable()){
        boolean logic = id != -1;
        boxUis.get(i).setIsAvailable(false);
        boxUis.get(i).showShape(logic);
        return boxUis.get(i);
      }
    }

    Gdx.app.error("PoolBoxUIs.java - getBoxUI", "hasn't box available!");
    return null;
  }

  public int getQuantityIsAvailable(boolean isAvailable){
    int rs = 0;
    for(BoxUi boxUi : boxUis){
      if(boxUi.getIsAvailable() == isAvailable){
        rs++;
      }
    }
    return rs;
  }

  public void reset(){
    for(BoxUi boxUi : boxUis){
      if(!boxUi.getIsAvailable()){
        boxUi.reset();
      }
    }
  }
}
