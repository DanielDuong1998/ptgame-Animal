package com.ss.OfficialPackage.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ss.OfficialPackage.configs.BoardConfig;

public class BoardAlgorithmsModel {
  public static void shuffle(Array<Array<AnimalModel>> animals){
    Array<Integer> ids = new Array<>();
    for(int i = 0; i < BoardConfig.height; i++)
      for(int j = 0; j < BoardConfig.width; j++)
        if(animals.get(i).get(j).getId() != -1)
          ids.add(animals.get(i).get(j).getId());

    ids.shuffle();
    int index = 0;

    for(int i = 0; i < BoardConfig.height; i++)
      for(int j = 0; j < BoardConfig.width; j++)
        if(animals.get(i).get(j).getId() != -1)
          animals.get(i).get(j).setId(ids.get(index++));
  }

  public static Array<Vector2> findHint(Array<Array<AnimalModel>> animals){
    Array<Vector2> hints = new Array<>();
    Array<Vector2> path = new Array<>();
    for(int i = 0; i < BoardConfig.height; i++) {
      for(int j = 0; j < BoardConfig.width; j++){
        if(animals.get(i).get(j).getId() != -1 ){
          for(int k = i * BoardConfig.width + j + 1; k < BoardConfig.height*BoardConfig.width; k++){
            int i1 = k/BoardConfig.width;
            int j1 = k%BoardConfig.width;
            AnimalModel ani = animals.get(i1).get(j1);
            if(ani.getId() != -1 && ani.getId() == animals.get(i).get(j).getId()){
              path = AlgorithmModel.checkAnimals(animals.get(i).get(j), ani, animals);
              if(path.size > 0){
                hints.add(new Vector2(i, j));
                hints.add(new Vector2(i1, j1));
                return hints;
              }
            }
          }
        }
      }
    }
    //duyet tung ptu trong board
    //- duyet tu ptu + 1 tro di
    //-- neu id == ptu.id, checkANI, neu ok return
    //-- thoat for, add vao array de bao la da tim.
    return hints;
  }

  private static boolean checkIdInArray(Array<Integer> idChecked, int id){
    for(int idCheck : idChecked){
      if(id == idCheck){
        return true;
      }
    }
    return false;
  }
}
