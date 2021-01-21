package com.ss.OfficialPackage.models;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.ss.OfficialPackage.configs.BoardConfig;
import com.ss.OfficialPackage.views.logicViews.CellUi;
import com.ss.core.util.GLayerGroup;

public class AnimationBoard {
  public static void aniByMode(Array<CellUi> cellUis, GLayerGroup group, int mode){
    switch (mode){
      case 1: {
        mode1(cellUis, group, 1, 1);
        break;
      }
      case 2: {
        mode1(cellUis, group, 2, 1);
        break;
      }
      case 3: {
        mode1(cellUis, group, 1, 2);
        break;
      }
      case 4: {
        mode1(cellUis, group, 2, 2);
        break;
      }
      case 5: {
        mode2(cellUis, group, true, true);
        break;
      }
      case 6: {
        mode2(cellUis, group, false, true);
        break;
      }
      case 7: {
        mode2(cellUis, group, true, false);
        break;
      }
      case 8: {
        mode2(cellUis, group, false, false);
        break;
      }
      case 9: {
        mode3(cellUis, group, true, true);
        break;
      }
      case 10: {
        mode3(cellUis, group, false, true);
        break;
      }
      case 11: {
        mode3(cellUis, group, true, false);
        break;
      }
      case 12: {
        mode3(cellUis, group, false, false);
        break;
      }
      case 13: {
        mode4(cellUis, group, 1, true);
        break;
      }
      case 14: {
        mode4(cellUis, group, 2, true);
        break;
      }
      case 15:{
        mode4(cellUis, group, 1, false);
        break;
      }
      case 16:{
        mode4(cellUis, group, 2, false);
        break;
      }
      default: break;
    }
  }

  // isLeft2Right == 1 ? left to right : right to left
  // isHorizontal == 1 ? horizontal : vertical
  private static void mode1(Array<CellUi> cellUis, GLayerGroup group, int isLeft2Right, int isHorizontal){
    for(CellUi cellUi : cellUis){
      cellUi.setScale(0, 0);
    }

    int size1 = isHorizontal == 1 ? BoardConfig.width : BoardConfig.height;
    int size2 = isHorizontal == 1 ? BoardConfig.height : BoardConfig.width;

    int start1 = isLeft2Right == 1 ? 0 : size1 - 1;
    float durationDelta = isLeft2Right == 1 ? 0 : size1 - 1;
    int i = start1;
    boolean logic1 = isLeft2Right == 1 ? i < size1 : i >= 0;
    int ope = isLeft2Right == 1 ? 1 : -1;

    float delayTime = isHorizontal == 1 ? 0.05f : (float)(0.05f*BoardConfig.width)/BoardConfig.height;

    for(i = start1; logic1; i = i + ope) {
      logic1 = isLeft2Right == 1 ? i < size1 : i >= 0;
      if(!logic1) return;
      final int itemp = i;
      group.addAction(Actions.sequence(
          Actions.delay(delayTime*(itemp*ope + durationDelta)),
          Actions.run(()->{
            for(int j = 0; j < size2; j++) {
              final int j1 = isHorizontal == 1 ? j : itemp;
              final int j2 = isHorizontal == 1 ? itemp : j;
              cellUis.get(j1*BoardConfig.width + j2).animation();
            }
          })
      ));
    }
  }

  //theo duong cheo
  private static void mode2(Array<CellUi> cellUis, GLayerGroup group, boolean isUp, boolean isLeft){
    for(CellUi cellUi : cellUis){
      cellUi.setScale(0, 0);
    }

    int idStart = isLeft ? 0 : BoardConfig.width - 1;
    int i = idStart;
    boolean logic = isLeft ? i < BoardConfig.width + BoardConfig.height - 1 : i >= -(BoardConfig.height - 1);
    int ope = isLeft ? 1 : -1;

    for(i = idStart; logic; i = i + ope) {
      logic = isLeft ? i < BoardConfig.width + BoardConfig.height - 1 : i >= -(BoardConfig.height - 1);
      if(!logic) return;

      final int j = getIJ(i, false, isUp, isLeft);
      final int itemp = getIJ(i, true, isUp, isLeft);
      final int indexDuration = isLeft ? i : (BoardConfig.width - 1 - i);
      group.addAction(Actions.sequence(
              Actions.delay(0.05f*indexDuration),
              Actions.run(()->{
                aniT(itemp, j, cellUis, isUp, isLeft);
                //System.out.println("\n");
              })
      ));
    }
  }

  private static int getIJ(int i, boolean isI, boolean isUp, boolean isLeft){
    int jLogic = isUp ? 0 : BoardConfig.height - 1;
    int ope = isUp ? -1 : 1;

//    int j = 0;
    int j = jLogic;
    int iTemp = i;
    int deltaTemp = isLeft ? BoardConfig.width - 1 - iTemp : i;

    int delta = deltaTemp;
    if(delta < 0){
//      j = j  - delta;
      j = j + delta*ope;
      iTemp = isLeft ? BoardConfig.width - 1 : 0;
//      iTemp = 0;
    }
    int rs = isI ? iTemp : j;
    return rs;
  }

  private static void aniT(int itemp, int j, Array<CellUi> cellUis, boolean isUp, boolean isLeft){
    //System.out.println("i-j: " + itemp + "-" + j);
    boolean logic = isUp ? j >= BoardConfig.height : j < 0;
    int ope = isUp ? 1 : -1;
    boolean logicI = isLeft ? itemp < 0 : itemp >= BoardConfig.width;
    int ope2 = isLeft ? -1 : 1;

    if(logicI || logic){
      return;
    }
    cellUis.get(j*BoardConfig.width + itemp).animation();
    itemp = itemp + ope2;
    j = j + ope;

    aniT(itemp, j, cellUis, isUp, isLeft);
  }

  //xen ke theo chieu ngang va khong xen ke
  private static void mode3(Array<CellUi> cellUis, GLayerGroup group, boolean isHorizontal, boolean isCross){
    for(CellUi cellUi : cellUis){
      cellUi.setScale(0, 0);
    }
    int isHori = isHorizontal ? 1 : 2;

    if(isCross){
      mode3S(cellUis, group, 1, isHori);
      mode3S(cellUis, group, 2, isHori);
    }
    else {
      mode3S2(cellUis, group, 1, isHori);
      mode3S2(cellUis, group, 2, isHori);
    }
  }

  private static void mode3S(Array<CellUi> cellUis, GLayerGroup group, int isLeft2Right, int isHorizontal){
    int size1 = isHorizontal == 1 ? BoardConfig.width : BoardConfig.height;
    int size2 = isHorizontal == 1 ? BoardConfig.height : BoardConfig.width;

    int start1 = isLeft2Right == 1 ? 0 : size1 - 1;
    float durationDelta = isLeft2Right == 1 ? 0 : size1 - 1;
    int i = start1;
    boolean logic1 = isLeft2Right == 1 ? i < size1 : i >= 0;
    int ope = isLeft2Right == 1 ? 1 : -1;

    float delayTime = isHorizontal == 1 ? 0.05f : (float)(0.05f*BoardConfig.width)/BoardConfig.height;
    final int mod = isLeft2Right == 1 ? 1 : 0;

    for(i = start1; logic1; i = i + ope) {
      logic1 = isLeft2Right == 1 ? i < size1 : i >= 0;
      if(!logic1) return;
      final int itemp = i;
      group.addAction(Actions.sequence(
              Actions.delay(delayTime*(itemp*ope + durationDelta)),
              Actions.run(()->{
                for(int j = 0; j < size2; j++) {
                  if(j%2 == mod) continue;
                  final int j1 = isHorizontal == 1 ? j : itemp;
                  final int j2 = isHorizontal == 1 ? itemp : j;
                  cellUis.get(j1*BoardConfig.width + j2).animation();
                }
              })
      ));
    }
  }

  private static void mode3S2(Array<CellUi> cellUis, GLayerGroup group, int isLeft2Right, int isHorizontal){
    int size1 = isHorizontal == 1 ? BoardConfig.width : BoardConfig.height;
    int size2 = isHorizontal == 1 ? BoardConfig.height : BoardConfig.width;

    int start1 = isLeft2Right == 1 ? 0 : size1 - 1;
    float durationDelta = isLeft2Right == 1 ? 0 : size1 - 1;
    int i = start1;
    boolean logic1 = isLeft2Right == 1 ? i < size1 : i >= 0;
    int ope = isLeft2Right == 1 ? 1 : -1;

    float delayTime = isHorizontal == 1 ? 0.05f : (float)(0.05f*BoardConfig.width)/BoardConfig.height;

    for(i = start1; logic1; i = i + ope) {
      logic1 = isLeft2Right == 1 ? i < size1 : i >= 0;
      if(!logic1) return;
      final int itemp = i;
      group.addAction(Actions.sequence(
              Actions.delay(delayTime*(itemp*ope + durationDelta)),
              Actions.run(()->{
                for(int j = 0; j < size2; j++) {
                  int size = isHorizontal == 1 ? BoardConfig.height : BoardConfig.width;
                  boolean logic = (isLeft2Right == 1) == (j > size / 2 - 1);
                  if(logic) continue;
                  final int j1 = isHorizontal == 1 ? j : itemp;
                  final int j2 = isHorizontal == 1 ? itemp : j;
                  cellUis.get(j1*BoardConfig.width + j2).animation();
                }
              })
      ));
    }
  }

  private static void mode4(Array<CellUi> cellUis, GLayerGroup group, int mode, boolean isCross){
    for(CellUi cellUi : cellUis){
      cellUi.setScale(0, 0);
    }

    if(mode == 1){
      mode4S(cellUis, group, true, true, isCross);
      mode4S(cellUis, group, false, false, isCross);
    }
    else {
      mode4S(cellUis, group, true, false, isCross);
      mode4S(cellUis, group, false, true, isCross);
    }
//    mode4S(cellUis, group, false, isLeft);
  }

  private static void mode4S(Array<CellUi> cellUis, GLayerGroup group, boolean isUp, boolean isLeft, boolean isCross){
    int idStart = isLeft ? 0 : BoardConfig.width - 1;
    int i = idStart;
    boolean logic = isLeft ? i < BoardConfig.width + BoardConfig.height - 1 : i >= -(BoardConfig.height - 1);
    int ope = isLeft ? 1 : -1;

    for(i = idStart; logic; i = i + ope) {
      logic = isLeft ? i < BoardConfig.width + BoardConfig.height - 1 : i >= -(BoardConfig.height - 1);
      if(!logic) return;

      final int j = getIJ(i, false, isUp, isLeft);
      final int itemp = getIJ(i, true, isUp, isLeft);
      final int indexDuration = isLeft ? i : (BoardConfig.width - 1 - i);
      group.addAction(Actions.sequence(
              Actions.delay(0.05f*indexDuration),
              Actions.run(()->{
                aniTs(itemp, j, cellUis, isUp, isLeft, isCross);
                //System.out.println("\n");
              })
      ));
    }
  }

  private static void aniTs(int itemp, int j, Array<CellUi> cellUis, boolean isUp, boolean isLeft, boolean isCross){
    if(isCross){
      int mod = isUp ? 0 : 1;
      if (itemp % 2 == mod) {
        if(j % 2 == 1) return;
      }
      else if(j%2 == 0) return;
    }
    else {
      boolean logic;
      if((isUp && !isLeft) || (!isUp && isLeft)){
        logic = isUp ? itemp < (BoardConfig.width - 1)/2 - (BoardConfig.height - 1)/2 : itemp >= (BoardConfig.width - 1)/2 + (BoardConfig.height)/2;
      }
      else {
        logic = isUp ? itemp > (BoardConfig.width - 1)/2 + (BoardConfig.height - 1)/2 : itemp <= (BoardConfig.width - 1)/2 - (BoardConfig.height)/2;
      }
      if(logic) return;
    }


    // System.out.println("i-j: " + itemp + "-" + j);
    boolean logic = isUp ? j >= BoardConfig.height : j < 0;
    int ope = isUp ? 1 : -1;
    boolean logicI = isLeft ? itemp < 0 : itemp >= BoardConfig.width;
    int ope2 = isLeft ? -1 : 1;

    if(logicI || logic){
      return;
    }
    cellUis.get(j*BoardConfig.width + itemp).animation();
    itemp = itemp + ope2;
    j = j + ope;

    aniT(itemp, j, cellUis, isUp, isLeft);
  }
}
