package com.ss.OfficialPackage.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ss.OfficialPackage.configs.BoardConfig;

public class AlgorithmModel {

  private static boolean checkRow(AnimalModel animal1, AnimalModel animal2, Array<Array<AnimalModel>> animals){
    if(Math.abs(animal1.getCol() - animal2.getCol()) == 0 && animal1.getId() == -1){
      return true;
    }

    int start = Math.min(animal1.getCol(), animal2.getCol());
    int end = Math.max(animal1.getCol(), animal2.getCol());
    int row = animal1.getRow();

    for(int i = start; i <= end; i++) {
      if(animals.get(row).get(i).getId() != -1){
        return false;
      }
    }
    return true;
  }

  private static boolean checkCol(AnimalModel animal1, AnimalModel animal2, Array<Array<AnimalModel>> animals){
    if(Math.abs(animal1.getRow() - animal2.getRow()) == 0 && animal1.getId() == -1){
      return true;
    }

    int start = Math.min(animal1.getRow(), animal2.getRow());
    int end = Math.max(animal1.getRow(), animal2.getRow());
    int col = animal1.getCol();

    for(int i = start; i <= end; i++) {
      if(animals.get(i).get(col).getId() != -1){
        return false;
      }
    }
    return true;
  }

  // kiểm tra theo chiều ngang 1
  public static Array<Vector2> checkHorizontalRect1(AnimalModel animal1, AnimalModel animal2, Array<Array<AnimalModel>> animals){
    Array<Vector2> path = new Array<>();
    path.add(new Vector2(animal1.getRow(), animal1.getCol()));

    //B1: duyet theo chieu ngang, lan luot ve den 0
    for(int i = animal1.getCol() - 1; i >= -1; i--){
      //neu i == -1 bo qua duyet theo chieu doc(B2), duyet den chieu ngang (B3)
      if(i == -1){
        path.add(new Vector2(animal1.getRow(), -1));
        path.add(new Vector2(animal2.getRow(), -1));

        int col = animal2.getCol() - 1;
        int officialCol = Math.max(col, 0);
        AnimalModel ani1 = animals.get(animal2.getRow()).get(0);
        AnimalModel ani2 = animals.get(animal2.getRow()).get(officialCol);
        if (col == -1 || checkRow(ani1, ani2, animals)){
          path.add(new Vector2(animal2.getRow(), animal2.getCol()));
          System.out.println("path: " + path);
          return path;
        }
        else {
          path.removeRange(0, path.size-1);
          return path;
        }
      }
      else {
        //B2: duyet theo chieu doc, xuong row ani2
        if(animals.get(animal1.getRow()).get(i).getId() == -1){
          if(i < animal2.getCol()){
            path.add(new Vector2(animal1.getRow(), i));

            AnimalModel ani1 = animals.get(animal1.getRow()).get(i);
            AnimalModel ani2 = animals.get(animal2.getRow()).get(i);
            boolean checkCol = checkCol(ani1, ani2, animals);
            //System.out.println("al: ani1: " + ani1);
            //System.out.println("al: ani2: " + ani2);
            //B3: duyet theo chieu ngang, den col ani2 - 1
            if(checkCol){
              path.add(new Vector2(animal2.getRow(), i));

              // System.out.println("ho1: ani1: " + ani1.toString());
              // System.out.println("ho1: ani2: " + ani2.toString());
              int col = Math.max(animal2.getCol() - 1, 0);
              AnimalModel aniR1 = ani2;
              AnimalModel aniR2 = animals.get(animal2.getRow()).get(col);
              if (checkRow(aniR1, aniR2, animals)){
                path.add(new Vector2(animal2.getRow(), animal2.getCol()));
                System.out.println("path: " + path);
                return path;
              }
              path.removeRange(0, path.size - 1);
              return path;
            }
            else path.pop();
          }
        }
        else {
          path.removeRange(0, path.size -1);
          return path;
        };
      }
    }
    path.removeRange(0, path.size -1);
    return path;
  }

  // kiểm tra theo chiều ngang 2
  private static Array<Vector2> checkHorizontalRect2(AnimalModel animal1, AnimalModel animal2, Array<Array<AnimalModel>> animals) {
    //B1: duyet theo chieu ngang, lan luot ve den widthBoard
    Array<Vector2> path = new Array<>();
    path.add(new Vector2(animal1.getRow(), animal1.getCol()));

    for(int i = animal1.getCol() + 1; i <= BoardConfig.width; i++) {
      //neu i == BoardConfig.width bo qua duyet theo chieu doc(B2), duyet den chieu ngang (B3)
      if(i == BoardConfig.width){
        path.add(new Vector2(animal1.getRow(), BoardConfig.width));
        path.add(new Vector2(animal2.getRow(), BoardConfig.width));

        int col = animal2.getCol() + 1;
        int officialCol = Math.min(col, BoardConfig.width - 1);
        AnimalModel ani1 = animals.get(animal2.getRow()).get(BoardConfig.width -1);
        AnimalModel ani2 = animals.get(animal2.getRow()).get(officialCol);
        if (col == BoardConfig.width || checkRow(ani1, ani2, animals)){
          path.add(new Vector2(animal2.getRow(), animal2.getCol()));
          System.out.println("path: " + path);
          return path;
        }
        else {
          path.removeRange(0, path.size -1);
          return path;
        }
      }
      else {
        //B2: duyet theo chieu doc, xuong row ani2
        if(animals.get(animal1.getRow()).get(i).getId() == -1){
          if(i > animal2.getCol()){
            path.add(new Vector2(animal1.getRow(), i));

            AnimalModel ani1 = animals.get(animal1.getRow()).get(i);
            AnimalModel ani2 = animals.get(animal2.getRow()).get(i);
            boolean checkCol = checkCol(ani1, ani2, animals);
            if(checkCol){
              //B3: duyet theo chieu ngang, den col ani2 + 1
              path.add(new Vector2(animal2.getRow(), i));

              int col = Math.min(animal2.getCol() + 1, BoardConfig.width -1);
              AnimalModel aniR1 = ani2;
              AnimalModel aniR2 = animals.get(animal2.getRow()).get(col);
              if(checkRow(aniR1, aniR2, animals)){
                path.add(new Vector2(animal2.getRow(), animal2.getCol()));
                System.out.println("path: " + path);
                return path;
              }
              path.removeRange(0, path.size - 1);
              return path;
            }
            else path.pop();
          }
        }
        else {
          path.removeRange(0, path.size - 1);
          return path;
        }
      }
    }
    path.removeRange(0, path.size - 1);
    return path;
  }

  // kiểm tra theo chiều ngang 3
  private static Array<Vector2> checkHorizontalRect3(AnimalModel animal1, AnimalModel animal2, Array<Array<AnimalModel>> animals){
    Array<Vector2> path = new Array<>();
    path.add(new Vector2(animal1.getRow(), animal1.getCol()));

    boolean logic = animal1.getCol() < animal2.getCol();
    int startId = logic ? animal1.getCol() + 1 : animal1.getCol() - 1;
    int endId = logic ? animal2.getCol() - 1 : animal2.getCol() + 1;
    int operator = logic ? 1 : -1;
    for(int i = startId; (logic && i <= endId) || (!logic && i >= endId); i += operator){
      if(animals.get(animal1.getRow()).get(i).getId() == -1){
        path.add(new Vector2(animal1.getRow(), i));

        AnimalModel ani1 = animals.get(animal1.getRow()).get(i);
        AnimalModel ani2 = animals.get(animal2.getRow()).get(i);
        boolean checkCol = checkCol(ani1, ani2, animals);
        if(checkCol){
          path.add(new Vector2(animal2.getRow(), i));

          AnimalModel aniR1 = ani2;
          AnimalModel aniR2 = animals.get(animal2.getRow()).get(endId);
          boolean checkRow = checkRow(aniR1, aniR2, animals);
          if(checkRow){
            path.add(new Vector2(animal2.getRow(), animal2.getCol()));
            System.out.println("Path: " + path);
            return path;
          }
          else {
            path.pop();
            path.pop();
          }
        }
        else path.pop();
      }
      else {
        path.removeRange(0, path.size - 1);
        return path;
      }
    }
    path.removeRange(0, path.size - 1);
    return path;
  }


}
