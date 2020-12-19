package com.ss.OfficialPackage.views.logicViews;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.OfficialPackage.models.DrawLineLaser;
import com.ss.commons.Tweens;
import com.ss.core.util.GLayerGroup;

public class BoardUi {
  private GLayerGroup pathGroup;
  private Array<CellUi> cellUis;

  public BoardUi(Array<CellUi> cellUis, GLayerGroup pathGroup){
    this.cellUis = cellUis;
    this.pathGroup = pathGroup;
  }

  public void drawPath(Array<Vector2> path){
    GLayerGroup pGroup = new GLayerGroup();
    pathGroup.addActor(pGroup);
    if(path.size < 2) return;
    for(int i = 0; i < path.size - 1; i++) {
      Vector2 vt1 = getPositionByRowCol((int)path.get(i).x, (int)path.get(i).y);
      Vector2 vt2 = getPositionByRowCol((int)path.get(i + 1).x, (int)path.get(i + 1).y);
      drawLine(vt1, vt2, pGroup);
    }

    Tweens.setTimeout(pGroup, 1, ()->{
      pGroup.clear();
      pGroup.remove();
    });
  }

  private void drawLine(Vector2 vt1, Vector2 vt2, GLayerGroup pGroup){
//    GShapeSprite line = new GShapeSprite();
//    line.createLine( vt1.x, vt1.y, vt2.x, vt2.y);
//    line.setColor(Color.RED);
//    pGroup.addActor(line);
    DrawLineLaser.DrawLine(vt1,vt2);

  }

  private Vector2 getPositionByRowCol(int row, int col){
    Vector2 vt = new Vector2(0, 0);
//    float originX = cellUis.get(0).getX();
//    float originY = cellUis.get(0).getY();
    float originX = 0* BoardConfig.paddingCellWidth + BoardConfig.paddingGameWidth;
    float originY = 0*BoardConfig.paddingCellHeight + BoardConfig.paddingGameHeight;

    if(row == -1){
      vt.set(vt.x, originY - BoardConfig.paddingCellHeight/2);
    }
    else if(row == BoardConfig.height){
      vt.set(vt.x, originY + BoardConfig.paddingCellHeight*(BoardConfig.height) + BoardConfig.paddingCellHeight/2);
    }
    else {
      int colTemp = col == -1 ? 0 : col == BoardConfig.width ? BoardConfig.width -1: col;
//      float y = cellUis.get(row*BoardConfig.width + colTemp).getY() + BoardConfig.paddingCellHeight/2;
      float y = row*BoardConfig.paddingCellHeight + BoardConfig.paddingGameHeight + BoardConfig.paddingCellHeight/2;
      vt.set(vt.x, y);
    }

    if(col == -1){
      vt.set(originX - BoardConfig.paddingCellWidth/2, vt.y);
    }
    else if(col == BoardConfig.width){
      vt.set(originX + BoardConfig.paddingCellWidth*(BoardConfig.width ) + BoardConfig.paddingCellWidth/2, vt.y);
    }
    else {
      int rowTemp = row == -1 ? 0 : row == BoardConfig.height ? BoardConfig.height - 1 : row;
//      float x = cellUis.get(rowTemp*BoardConfig.width + col).getX() + BoardConfig.paddingCellWidth/2;
      float x = col* BoardConfig.paddingCellWidth + BoardConfig.paddingGameWidth + BoardConfig.paddingCellWidth/2;
      vt.set(x, vt.y);
    }

    return vt;
  }

  public void newGame(Array<CellUi> cellUis){
    this.cellUis = cellUis;
  }
}
