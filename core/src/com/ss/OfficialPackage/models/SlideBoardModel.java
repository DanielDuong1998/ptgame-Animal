package com.ss.OfficialPackage.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class SlideBoardModel {
  public static Array<Array<Vector2>> slideAnimalModels(Array<Array<AnimalModel>> animals, Vector2 vt1, Vector2 vt2, int mode){
    Array<Array<Vector2>> arr = new Array<>();
    switch(mode){
      case 0: {
        return slideHorizontalOrVerticalOfficial(animals, vt1, vt2, true, true);
      }
      case 1: {
        return slideHorizontalOrVerticalOfficial(animals, vt1, vt2, false, true);
      }
      case 2: {
        return slideHorizontalOrVerticalOfficial(animals, vt1, vt2, false, false);
      }
      case 3: {
        return slideStraightInfAllBoardS1(animals);
      }
      case 4: {
        return slideFocus(animals, vt1, vt2, true, false);
      }
      case 5: {
        return slideCircleMode1(animals);
      }
      case 6: {
        return slideFocus(animals, vt1, vt2, false, true);
      }
      case 7: {
        return slideFocus(animals, vt1, vt2, false, false);
      }
      case 8: {
        return slideReverse2D(animals, vt1, vt2, true, true);
      }
      case 9: {
        return slideReverse2D(animals, vt1, vt2, true, false);
      }
      case 10: {
        return slideReverse2D(animals, vt1, vt2, false, true);
      }
      case 11: {
        return slideReverse2D(animals, vt1, vt2, false, false);
      }
      case 12: {
        return slideStraightInfAllBoard(animals, true, false,true);
      }
      case 13: {
        return slideStraightInfAllBoard(animals, true, false,false);
      }
      case 14: {
        return slideStraightInfAllBoard(animals, false, false, true);
      }
      case 15: {
        return slideStraightInfAllBoard(animals, false, false, false);
      }
      case 17: {
        return slideStraightInfAllBoard(animals, true, true,true);
      }
      case 16: {
        return slideStraightInfAllBoard(animals, true, true,false);
      }
      case 18: {
        return slideStraightInfAllBoard(animals, false, true, true);
      }
      case 19: {
        return slideStraightInfAllBoard(animals, false, true, false);
      }
      case 20: {
        return slideFocus(animals, vt1, vt2, true, true);
      }
//      case 20: {
//        return slideCircle(animals, new Vector2(0, 0), 17, 8, true);
//      }
      case 21: {
        return slideHorizontalOrVerticalOfficial(animals, vt1, vt2, true, false);
//        return slideStraightInfAllBoardS1(animals);
      }
      case 22:{
        //start circle
        Array<Array<Vector2>> arrs = new Array<>();
//        Array<Array<Vector2>> arr1;
//        Array<Array<Vector2>> arr2;
//        arr1 = slideCircle(animals, new Vector2(0, 0), 3, 3, false); //
//        arr2 = slideCircle(animals, new Vector2(3, 0), 3, 3, false);
//
//
//        for(int j = 0; j < arr1.size; j++) {
//          arrs.add(arr1.get(j));
//        }
//
//        for(int j = 0; j < arr2.size; j++) {
//          arrs.add(arr2.get(j));
//        }
        //end circle
        arrs = slideStraightInf(animals, false, true, 5, 1, 5);
        //Array<Array<AnimalModel>> animals, boolean isHorizontal, boolean isMinToMax, int number, int num1, int num2



        return arrs;
//        return slideStraightInf(animals, new Vector2(0, 0), 3, 3, false);

      }
      default:{
        return arr;
      }
    }
  }

  public static Array<Array<Vector2>> slideHorizontalOrVerticalOfficial(Array<Array<AnimalModel>> animals, Vector2 vt1, Vector2 vt2, boolean isEndToStart, boolean isHorizontal){
    Array<Array<Vector2>> arr = new Array<>();
    Array<Array<Vector2>> arr1 = slideHorizontalOrVertical(animals, vt1, isEndToStart, isHorizontal);
    Array<Array<Vector2>> arr2 = slideHorizontalOrVertical(animals, vt2, isEndToStart, isHorizontal);
    for(int i = 0; i < arr1.size; i++) {
      arr.add(arr1.get(i));
    }

    for(int i = 0; i < arr2.size; i++) {
      arr.add(arr2.get(i));
    }
    return arr;
  }

  private static Array<Array<Vector2>> slideHorizontalOrVertical(Array<Array<AnimalModel>> animals, Vector2 vt, boolean isEndToStart, boolean isHorizontal){
    Array<Array<Vector2>> arrChangePositionCell = new Array<>();
    int i = isHorizontal ? (int)vt.y : (int)vt.x;
    int itemp = isHorizontal ? (int)vt.y : (int)vt.x;
    int sizeLoop = isHorizontal ? animals.get(0).size : animals.size;

    int size = isEndToStart ? sizeLoop : 0;
    boolean logic = isEndToStart == (i < size);
    int ope = isEndToStart ? 1 : -1;

    if(animals.get((int)vt.x).get((int)vt.y).getId() != -1){
      return arrChangePositionCell;
    }

    int unit = 1;
    for(i = itemp; logic; i = i + ope) {
      logic = isEndToStart ? i < sizeLoop : i >= 0;
      int x1 = isHorizontal ? (int)vt.x : (i + ope*unit);
      int y1 = isHorizontal ? (i + ope*unit) : (int)vt.y;
      int x2 = isHorizontal ? (int)vt.x : i;
      int y2 = isHorizontal ? i : (int)vt.y;
      if((i + ope*unit >= sizeLoop && isEndToStart)||(i + ope*unit < 0 && !isEndToStart)){
        return arrChangePositionCell;
      }
      else if(animals.get(x1).get(y1).getId() != -1){
        int r1 = animals.get(x2).get(y2).getRow();
        int c1 = animals.get(x2).get(y2).getCol();
        int r2 = animals.get(x1).get(y1).getRow();
        int c2 = animals.get(x1).get(y1).getCol();

        if(isHorizontal)
          animals.get((int)vt.x).swap(i, i + ope*unit);
        else swapCustom(animals, new Vector2(i, (int)vt.y), new Vector2(i + ope*unit, (int)vt.y));

        animals.get(x2).get(y2).setRow(r1);
        animals.get(x2).get(y2).setCol(c1);
        animals.get(x1).get(y1).setRow(r2);
        animals.get(x1).get(y1).setCol(c2);

        Array<Vector2> vts = new Array<>();
        vts.add(new Vector2(x2, y2), new Vector2(x1, y1));
        arrChangePositionCell.add(vts);
      }
      else if(animals.get(x1).get(y1).getId() == -1){
        unit++;
        if((i + ope*unit >= sizeLoop && isEndToStart)||(i + ope*unit < 0 && !isEndToStart))
          return arrChangePositionCell;
        int x11 = isHorizontal ? (int)vt.x : (i + ope*unit);
        int y11 = isHorizontal ? (i + ope*unit) : (int)vt.y;
        int x22 = isHorizontal ? (int)vt.x : i;
        int y22 = isHorizontal ? i : (int)vt.y;
        while(animals.get(x11).get(y11).getId() == -1){
          unit++;
          x11 = isHorizontal ? (int)vt.x : (i + ope*unit);
          y11 = isHorizontal ? (i + ope*unit) : (int)vt.y;
          if((i + ope*unit >= sizeLoop && isEndToStart)||(i + ope*unit < 0 && !isEndToStart))
            return arrChangePositionCell;
        }

        int r1 = animals.get(x22).get(y22).getRow();
        int c1 = animals.get(x22).get(y22).getCol();
        int r2 = animals.get(x11).get(y11).getRow();
        int c2 = animals.get(x11).get(y11).getCol();

        if(isHorizontal)
          animals.get((int)vt.x).swap(i, i + ope*unit);
        else swapCustom(animals, new Vector2(i, (int)vt.y), new Vector2(i + ope*unit, (int)vt.y));

        animals.get(x22).get(y22).setRow(r1);
        animals.get(x22).get(y22).setCol(c1);
        animals.get(x11).get(y11).setRow(r2);
        animals.get(x11).get(y11).setCol(c2);

        Array<Vector2> vts = new Array<>();
        vts.add(new Vector2(x22, y22), new Vector2(x11, y11));
        arrChangePositionCell.add(vts);
      }
    }
    return arrChangePositionCell;
  }

  public static Array<Array<Vector2>> slideFocus(Array<Array<AnimalModel>> animals, Vector2 vt1, Vector2 vt2, boolean isHorizontal, boolean rightMode){
    Array<Array<Vector2>> arr = new Array<>();
    Array<Array<Vector2>> arr1, arr2;

    arr1 = slideOutToInHorizontalOrVertical(animals, isHorizontal, vt1, rightMode);
    arr2 = slideOutToInHorizontalOrVertical(animals, isHorizontal, vt2, rightMode);

    for(int i = 0; i < arr1.size; i++) {
      arr.add(arr1.get(i));
    }

    for(int i = 0; i < arr2.size; i++) {
      arr.add(arr2.get(i));
    }
    return arr;
  }

  private static Array<Array<Vector2>> slideOutToInHorizontalOrVertical(Array<Array<AnimalModel>> animals, boolean isHorizontal, Vector2 vt, boolean rightMode){
    Array<Array<Vector2>> arrChangePositionCell = new Array<>();
    //check vt is left or right
    int size = isHorizontal ? animals.get(0).size : animals.size;
    int movePos = isHorizontal ? (int)vt.y : (int)vt.x;

    int midId = size/2 - 1;
    boolean isLeft = movePos <= midId;
    if(animals.get((int)vt.x).get((int)vt.y).getId() != -1){
      return arrChangePositionCell;
    }

    int ope1 = isLeft ? -1 : 1;
    int ope2 = isLeft ? 1 : -1;
    int ope = rightMode ? ope1 : ope2;
    int i = movePos;
    boolean logic1 = isLeft ? i >= 0 : i < size;
    boolean logic2 = isLeft == (i <= midId);
    boolean logic = rightMode ? logic1 : logic2;

    int unit = 1;
    for(i = movePos; logic; i = i + ope){
      logic1 = isLeft ? i >= 0 : i < size;
      logic2 = isLeft == (i <= midId);
      logic = rightMode ? logic1 : logic2;

      boolean logicReturn1 = (isLeft && i - unit < 0) || (!isLeft && i + unit >= size);
      boolean logicReturn2 = (isLeft && i + unit > midId) || (!isLeft && i - unit <= midId);
      boolean logicReturn = rightMode ? logicReturn1 : logicReturn2;

      int x1 = isHorizontal ? (int)vt.x : i + ope*unit;
      int y1 = isHorizontal ? i + ope*unit : (int)vt.y;
      int x2 = isHorizontal ? (int)vt.x : i;
      int y2 = isHorizontal ? i : (int)vt.y;

      if(logicReturn){
        return arrChangePositionCell;
      }
      else if(animals.get(x1).get(y1).getId() != -1){
        int r1 = animals.get(x2).get(y2).getRow();
        int c1 = animals.get(x2).get(y2).getCol();
        int r2 = animals.get(x1).get(y1).getRow();
        int c2 = animals.get(x1).get(y1).getCol();

//        animals.get((int)vt.x).swap(i, i + ope *unit);
        swapCustom(animals, new Vector2(x2, y2), new Vector2(x1, y1));

        animals.get(x2).get(y2).setRow(r1);
        animals.get(x2).get(y2).setCol(c1);
        animals.get(x1).get(y1).setRow(r2);
        animals.get(x1).get(y1).setCol(c2);

        Array<Vector2> vts = new Array<>();
        vts.add(new Vector2(x2, y2), new Vector2(x1, y1));
        arrChangePositionCell.add(vts);
      }
      else if(animals.get(x1).get(y1).getId() == -1){
        unit++;
        boolean logicReturn11 = (isLeft && i - unit < 0) || (!isLeft && i + unit >= size);
        boolean logicReturn22 = (isLeft && i + unit > midId) || (!isLeft && i - unit <= midId);
        boolean logicReturn00 = rightMode ? logicReturn11 : logicReturn22;

        if(logicReturn00){
          return arrChangePositionCell;
        }

        x1 = isHorizontal ? (int)vt.x : i + ope*unit;
        y1 = isHorizontal ? i + ope*unit : (int)vt.y;
        x2 = isHorizontal ? (int)vt.x : i;
        y2 = isHorizontal ? i : (int)vt.y;

        while(animals.get(x1).get(y1).getId() == -1){
          unit++;
          logicReturn11 = (isLeft && i - unit < 0) || (!isLeft && i + unit >= size);
          logicReturn22 = (isLeft && i + unit > midId) || (!isLeft && i - unit <= midId);
          logicReturn00 = rightMode ? logicReturn11 : logicReturn22;
          x1 = isHorizontal ? (int)vt.x : i + ope*unit;
          y1 = isHorizontal ? i + ope*unit : (int)vt.y;
          x2 = isHorizontal ? (int)vt.x : i;
          y2 = isHorizontal ? i : (int)vt.y;
          if(logicReturn00){
            return arrChangePositionCell;
          }
        }
        int r1 = animals.get(x2).get(y2).getRow();
        int c1 = animals.get(x2).get(y2).getCol();
        int r2 = animals.get(x1).get(y1).getRow();
        int c2 = animals.get(x1).get(y1).getCol();

        swapCustom(animals, new Vector2(x2, y2), new Vector2(x1, y1));

        animals.get(x2).get(y2).setRow(r1);
        animals.get(x2).get(y2).setCol(c1);
        animals.get(x1).get(y1).setRow(r2);
        animals.get(x1).get(y1).setCol(c2);

        Array<Vector2> vts = new Array<>();
        vts.add(new Vector2(x2, y2), new Vector2(x1, y1));
        arrChangePositionCell.add(vts);
      }
    }

    return arrChangePositionCell;
  }

  private static Array<Array<Vector2>> slideReverse2D(Array<Array<AnimalModel>> animals, Vector2 vt1, Vector2 vt2, boolean isHorizontal, boolean isUpFirst){
    Array<Array<Vector2>> arr = new Array<>();
    Array<Array<Vector2>> arr1, arr2;
    int midCol = animals.get(0).size/2 - 1;
    int midRow = animals.size/2 - 1;

    if(isHorizontal){
      if(isUpFirst){
        if((int)vt1.x <= midRow){
          arr1 = slideHorizontalOrVertical(animals, vt1, false, true);
        }
        else {
          arr1 = slideHorizontalOrVertical(animals, vt1, true, true);
        }
        if((int)vt2.x <= midRow){
          arr2 = slideHorizontalOrVertical(animals, vt2, false, true);
        }
        else {
          arr2 = slideHorizontalOrVertical(animals, vt2, true, true);
        }
      }
      else {
        if((int)vt1.x <= midRow){
          arr1 = slideHorizontalOrVertical(animals, vt1, true, true);
        }
        else {
          arr1 = slideHorizontalOrVertical(animals, vt1, false, true);
        }
        if((int)vt2.x <= midRow){
          arr2 = slideHorizontalOrVertical(animals, vt2, true, true);
        }
        else {
          arr2 = slideHorizontalOrVertical(animals, vt2, false, true);
        }
      }
    }
    else {
      if(isUpFirst){
        if((int)vt1.y <= midCol){
          arr1 = slideHorizontalOrVertical(animals, vt1, false, false);
        }
        else {
          arr1 = slideHorizontalOrVertical(animals, vt1, true, false);
        }
        if((int)vt2.y <= midCol){
          arr2 = slideHorizontalOrVertical(animals, vt2, false, false);
        }
        else {
          arr2 = slideHorizontalOrVertical(animals, vt2, true, false);
        }
      }
      else {
        if((int)vt1.y <= midCol){
          arr1 = slideHorizontalOrVertical(animals, vt1, true, false);
        }
        else {
          arr1 = slideHorizontalOrVertical(animals, vt1, false, false);
        }
        if((int)vt2.y <= midCol){
          arr2 = slideHorizontalOrVertical(animals, vt2, true, false);
        }
        else {
          arr2 = slideHorizontalOrVertical(animals, vt2, false, false);
        }
      }
    }
    for(int i = 0; i < arr1.size; i++) {
      arr.add(arr1.get(i));
    }

    for(int i = 0; i < arr2.size; i++) {
      arr.add(arr2.get(i));
    }
    return arr;
  }

  //neu num1 <= -1 luot toan bo, luot tu num1 den num2
  public static Array<Array<Vector2>> slideStraightInf(Array<Array<AnimalModel>> animals, boolean isHorizontal, boolean isMinToMax, int number, int num1, int num2){
    Array<Array<Vector2>> arrChangePositionCell = new Array<>();
    if(number <= -1) return arrChangePositionCell;

    int size = isHorizontal ? animals.get(0).size - 1: animals.size- 1;
    int startId1 = num1 <= -1 ? size : num2;
    int startId2 = num1 <= -1 ? 0 : num1;
    int endId1 = num1 <= -1 ? 0 : num1;
    int endId2 = num1 <= -1 ? size : num2;
    int startId = isMinToMax ? startId1 : startId2;
    int endId = isMinToMax ? endId1 : endId2;
    int ope = isMinToMax ? -1 : 1;
    int i = startId;
    boolean logic = isMinToMax ? i > endId : i < endId;
    for(i = startId; logic; i = i + ope){
      logic = isMinToMax ? i > endId : i < endId;
      if(!logic) return arrChangePositionCell;

      int x1 = isHorizontal ? number : i;
      int y1 = isHorizontal ? i : number;
      int x2 = isHorizontal ? number : i + ope;
      int y2 = isHorizontal ? i + ope : number;
      int r1 = animals.get(x1).get(y1).getRow();
      int c1 = animals.get(x1).get(y1).getCol();
      int r2 = animals.get(x2).get(y2).getRow();
      int c2 = animals.get(x2).get(y2).getCol();

      swapCustom(animals, new Vector2(x1, y1), new Vector2(x2, y2));

      animals.get(x1).get(y1).setRow(r1);
      animals.get(x1).get(y1).setCol(c1);
      animals.get(x2).get(y2).setRow(r2);
      animals.get(x2).get(y2).setCol(c2);

      Array<Vector2> vts = new Array<>();
      vts.add(new Vector2(x1, y1), new Vector2(x2,y2));
      arrChangePositionCell.add(vts);
    }

    return arrChangePositionCell;
  }

  private static Array<Array<Vector2>> slideStraightInfAllBoard(Array<Array<AnimalModel>> animals, boolean isHorizontal, boolean isCross, boolean isMinToMaxFirst){
    Array<Array<Vector2>> arr = new Array<>();
    int size = isHorizontal ? animals.size : animals.get(0).size;
    for(int i = 0; i < size; i++) {
      int rs = isCross ? 0 : i % 2;
      boolean isMinToMax = (i % 2 == rs) == isMinToMaxFirst;
      Array<Array<Vector2>> arr1 = slideStraightInf(animals, isHorizontal, isMinToMax, i, -1, -1);
      for(int j = 0; j < arr1.size; j++) {
        arr.add(arr1.get(j));
      }
    }
    return arr;
  }

  private static Array<Array<Vector2>> slideCircle(Array<Array<AnimalModel>> animals, Vector2 vt, int width, int height, boolean isRightToLeft){
    Array<Array<Vector2>> arrChangePositionCell = new Array<>();
    if((int)vt.x + height > animals.size || (int)vt.y + width > animals.get(0).size || width <= 0 || height <= 0){
      return arrChangePositionCell;
    }
    if(height == 1){
      arrChangePositionCell = slideStraightInf(animals, true, !isRightToLeft, (int)vt.x, (int)vt.y, (int)vt.y + width - 1);
      return arrChangePositionCell;
    }
    else if(width == 1){
      arrChangePositionCell = slideStraightInf(animals, false, !isRightToLeft, (int)vt.y, (int)vt.x, (int)vt.x + height - 1);
      return arrChangePositionCell;
    }


    int size = width*height - (width - 2)*(height - 2) - 1;
    int point3 = isRightToLeft ? 2*width + height - 3 : 2*height + width - 3;
    int point2 = width + height - 2;
    int point1 = isRightToLeft ? width - 1 : height - 1;
    for(int i = 0; i < size; i++){
      Vector2 vt1, vt2;
      int delta;

      if(i >= point3){
        delta = i - point3;
        int x1 = isRightToLeft ? (int) vt.x + height - 1 - delta : (int)vt.x;
        int y1 = isRightToLeft ? (int)vt.y : (int)vt.y + width - 1 - delta;
        int x2 = isRightToLeft ? (int)vt.x + height - 2 - delta : (int)vt.x;
        int y2 = isRightToLeft ? (int)vt.y : (int)vt.y + width - 2 - delta;

        vt1 = new Vector2(x1, y1);
        vt2 = new Vector2(x2, y2);
      }
      else if(i >= point2){
        delta = point3 - i;
        int x1 = isRightToLeft ? (int)vt.x + height - 1 : (int)vt.x + delta;
        int y1 = isRightToLeft ? (int)vt.y + delta : (int)vt.y + width - 1;
        int x2 = isRightToLeft ? (int)vt.x + height - 1 : (int)vt.x + delta - 1;
        int y2 = isRightToLeft ? (int)vt.y + delta - 1 : (int)vt.y + width - 1;
        vt1 = new Vector2(x1, y1);
        vt2 = new Vector2(x2, y2);
      }
      else if(i >= point1){
        delta = i - point1;
        int x1 = isRightToLeft ? (int)vt.x + delta : (int)vt.x + height - 1;
        int y1 = isRightToLeft ? (int)vt.y + width - 1 : (int)vt.y + delta;
        int x2 = isRightToLeft ? (int)vt.x + delta + 1 : (int)vt.x + height - 1;
        int y2 = isRightToLeft ? (int)vt.y + width - 1 : (int)vt.y + delta + 1;
        vt1 = new Vector2(x1, y1);
        vt2 = new Vector2(x2, y2);

      }
      else{
        int x1 = isRightToLeft ? (int)vt.x : (int)vt.x + i;
        int y1 = isRightToLeft ? (int)vt.y + i : (int)vt.y;
        int x2 = isRightToLeft ? (int)vt.x : (int)vt.x + i + 1;
        int y2 = isRightToLeft ? (int)vt.y + i + 1 : (int)vt.y;
        vt1 = new Vector2(x1, y1);
        vt2 = new Vector2(x2, y2);
      }


      int r1 = animals.get((int)vt1.x).get((int)vt1.y).getRow();
      int c1 = animals.get((int)vt1.x).get((int)vt1.y).getCol();
      int r2 = animals.get((int)vt2.x).get((int)vt2.y).getRow();
      int c2 = animals.get((int)vt2.x).get((int)vt2.y).getCol();
      swapCustom(animals, vt1, vt2);
      animals.get((int)vt1.x).get((int)vt1.y).setCol(c1);
      animals.get((int)vt1.x).get((int)vt1.y).setRow(r1);
      animals.get((int)vt2.x).get((int)vt2.y).setCol(c2);
      animals.get((int)vt2.x).get((int)vt2.y).setRow(r2);

      Array<Vector2> arr = new Array<>();
      arr.add(vt1, vt2);
      arrChangePositionCell.add(arr);
    }

    return arrChangePositionCell;
  }

  private static Array<Array<Vector2>> slideCircleMode1(Array<Array<AnimalModel>> animals){
    Array<Array<Vector2>> arr = new Array<>();
    int delta = (animals.size/2)%2 == 1 ? 1 : 0;
    for(int i = 0; i < animals.size/2 + delta; i++){
//      int w = animals.get(0).size - 2*i == -1 ? 1 : animals.get(0).size - 2*i;
//      int h = animals.size - 2*i == -1 ? 1 : animals.size - 2*i;
      int w = animals.get(0).size - 2*i;
      int h = animals.size - 2*i;

      Array<Array<Vector2>> arr1 = slideCircle(animals, new Vector2(i, i), w, h, i%2==0);
      for(int j = 0; j < arr1.size; j++) {
        arr.add(arr1.get(j));
      }
    }
    return arr;
  }

  private static Array<Array<Vector2>> slideStraightInfAllBoardS1(Array<Array<AnimalModel>> animals){
    Array<Array<Vector2>> arr = new Array<>();
    int anchorWidth = animals.get(0).size/4;
    int anchorHeight = animals.size/4;
    for(int i = 0; i < animals.size; i++) {
      Array<Array<Vector2>> arr1 = slideStraightInf(animals, true, false, i, 0, anchorWidth - 1);
      for(int j = 0; j < arr1.size; j++) {
        arr.add(arr1.get(j));
      }
    }
    for(int i = anchorWidth; i < animals.get(0).size - anchorWidth; i++) {
      Array<Array<Vector2>> arr1 = slideStraightInf(animals, false, true, i, 0, anchorHeight - 1);
      for(int j = 0; j < arr1.size; j++) {
        arr.add(arr1.get(j));
      }
    }
    for(int i = 0; i < animals.size; i++) {
      Array<Array<Vector2>> arr1 = slideStraightInf(animals, true, true, i, animals.get(0).size - anchorWidth, animals.get(0).size - 1);
      for(int j = 0; j < arr1.size; j++) {
        arr.add(arr1.get(j));
      }
    }
    for(int i = anchorWidth; i < animals.get(0).size - anchorWidth; i++) {
      Array<Array<Vector2>> arr1 = slideStraightInf(animals, false, false, i, animals.size - anchorHeight, animals.size - 1);
      for(int j = 0; j < arr1.size; j++) {
        arr.add(arr1.get(j));
      }
    }

    for(int i = anchorHeight; i < animals.size; i++) {
      Array<Array<Vector2>> arr1 = slideStraightInf(animals, true, true, i, anchorWidth, 2*anchorWidth);
      for(int j = 0; j < arr1.size; j++) {
        arr.add(arr1.get(j));
      }
    }
    for(int i = anchorHeight; i < animals.size; i++) {
      Array<Array<Vector2>> arr1 = slideStraightInf(animals, true, false, i, 2*anchorWidth + 1, animals.get(0).size - anchorWidth - 1);
      for(int j = 0; j < arr1.size; j++) {
        arr.add(arr1.get(j));
      }
    }
    return arr;
  }

  private static void swapCustom(Array<Array<AnimalModel>> animals, Vector2 vt1, Vector2 vt2){
    int r1 = (int)vt1.x;
    int c1 = (int)vt1.y;
    int r2 = (int)vt2.x;
    int c2 = (int)vt2.y;

    if(r1 == r2){
      animals.get(r1).swap(c1, c2);
      return;
    }

    AnimalModel ani1 = animals.get(r1).get(c1);
    AnimalModel ani2 = animals.get(r2).get(c2);

    animals.get(r1).insert(c1 + 1, ani2);
    animals.get(r2).insert(c2 + 1, ani1);

    animals.get(r1).removeIndex(c1);
    animals.get(r2).removeIndex(c2);


  }
}
