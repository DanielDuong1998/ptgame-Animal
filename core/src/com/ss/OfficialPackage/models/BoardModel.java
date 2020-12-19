package com.ss.OfficialPackage.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ss.OfficialPackage.configs.BoardConfig;

public class BoardModel {
  private BoardController boardController;
  private Array<Array<AnimalModel>> animals;
  private int width, height;
  private int sizeAnimal;
  private Array<Vector2> listAniSelected;
  private Array<Vector2> listAniHint;

  public BoardModel(BoardController boardController){
    this.boardController = boardController;
    updateInfoConfigBoard();
    initAnimals();
//    initAnimalsForTest();

    initListSelected();
    initListHint();
  }

  private void updateInfoConfigBoard(){
    this.width = BoardConfig.width;
    this.height = BoardConfig.height;
    this.sizeAnimal = BoardConfig.sizeAnimal;
  }

  private void initAnimalsForTest(){
    int ids[][] = {
            {10,  1,  2, 11,  4,  5,  6,  7,  8,  9, 10, 19, 12, 13, 14,  3, 16},
            {-1,  7,  4, 15, -1, -1,  7, -1,  6,  3,  1,  8,  0,  4, 12, 17, 13},
            {-1,  1,  2, 11,  6, -1, -1,  3, -1, -1, 10, 11, 12, -1,  2, -1,  1},
            {-1, -1, -1,  6,  5, -1,  7,  1,  7,  1,  9,  4,  3,  2,  9, -1,  6},
            {-1, -1, -1, -1,  1, -1,  6, -1,  8,  1, 10, 11, 12, 13, 14, -1, -1},
            {-1,  3,  4, -1,  1,  4, -1, -1,  2, -1, -1, -1,  0,  4, 12, -1, 14},
            { 1,  1,  2, -1, -1,  1, -1, -1,  8,  9, 10, 11, 12, 13, 14,  1, 12},
            {-1, -1, -1, -1,  1, -1, -1,  4,  6,  3,  1,  8,  0,  4, 12, 17, 19},
    };
    int i1 = 0, j1 = 5;
    int i2 = 3, j2 = 4;
    animals = new Array<>();
    for(int i = 0; i < 8; i++){
      Array<AnimalModel> animalRow = new Array<>();
      animals.add(animalRow);
      for(int j = 0; j < 17; j++) {
        AnimalModel animal = new AnimalModel(ids[i][j], i, j);
        animalRow.add(animal);
      }
    }

    Array<Vector2> hints = BoardAlgorithmsModel.findHint(animals);
    if(hints.size > 0){
      System.out.println("has a hint");
    }
    else System.out.println("hasn't hint");
    //System.out.println("hints: " + hints);


    AnimalModel animal1 = animals.get(i1).get(j1); //=> 4
    AnimalModel animal2 = animals.get(i2).get(j2); //=> 4
    //System.out.println("boardMode: ani1: " + animal1.toString());
    //  System.out.println("boardMode: ani2: " + animal2.toString());

    Array<Vector2> path = AlgorithmModel.checkAnimals(animal1, animal2, animals);
//        Array<Vector2> path = AlgorithmModel.checkHorizontalRect4(animal1, animal2, animals);
//        Array<Vector2> path = AlgorithmModel.checkVerticalRectExtra(animal1, animal2, animals);
    //Array<Vector2> path = AlgorithmModel.checkHorizontalRectExtra(animal2, animal1, animals);
    // System.out.println("path size: " + path.size + " path info: " + path);

  }

  private void initAnimals(){
    Array<Integer> ids = initIdsArray();
    initAnimalsWithIdsArray(ids);

    Array<Vector2> hint = findHint();
    while(hint.size == 0){
      shuffle();
      hint = findHint();
    }
  }

  private Array<Integer> initIdsArray(){
    // quantityDoubleAni: Số lượng cặp thú trùng trong mảng
    // thêm các id vào mảng ids, mỗi id thú sẽ có quantityDoubleAni cặp trong mảng
    int quantityDoubleAni = width*height/(2*sizeAnimal);
    Array<Integer> ids = new Array<>();
    for(int i = 0; i < sizeAnimal; i++) {
      for(int j = 0; j < quantityDoubleAni; j++) {
        ids.add(i);
        ids.add(i);
      }
    }


    // số lượng cặp thú trùng trong mảng bị dư ra
    // tìm ra các id thú ngẫu nhiên cho các cặp thú bị dư ra
    int quantityDoubleAniExtra = (width*height - quantityDoubleAni*sizeAnimal*2)/2;
    Array<Integer> idsExtra = new Array<>();
    for(int i = 0; i < sizeAnimal; i++) {
      idsExtra.add(i);
    }
    idsExtra.shuffle();

    //thêm các cặp id thú được random vào mảng ids
    for(int i = 0; i < quantityDoubleAniExtra; i++ ){
      for(int j = 0; j < 2; j++) {
        ids.add(idsExtra.get(i));
      }
    }
    ids.shuffle();
    return ids;
  }

  private void initAnimalsWithIdsArray(Array<Integer> ids){
    if(animals == null) {
      animals = new Array<>();
    }
    else {
      for(int i = 0; i < animals.size; i++){
        animals.get(i).removeRange(0, animals.size - 1);
      }
      animals.removeRange(0, animals.size - 1);
    }

    for(int i = 0; i < height; i++) {
      Array<AnimalModel> animalRow = new Array<>();
      animals.add(animalRow);
      for(int j = 0; j < width; j++) {
        AnimalModel animal = new AnimalModel(ids.get(i*width + j), i, j);
        animalRow.add(animal);
      }
    }
  }

  private void initListSelected(){
    listAniSelected = new Array<>();
  }

  private void initListHint(){
    listAniHint = new Array<>();
  }

  public Array<Vector2> findHint(){
    Array<Vector2> hint = BoardAlgorithmsModel.findHint(animals);
    if(listAniHint != null){
      if(listAniHint.size > 0){
        listAniHint.removeRange(0, listAniHint.size - 1);
      }
      if(hint.size>0){
        listAniHint.add(new Vector2(hint.get(0).x, hint.get(0).y));
        listAniHint.add(new Vector2(hint.get(1).x, hint.get(1).y));
      }

    }
    return hint;
  }

  public void unSelect(){
    if(listAniSelected != null && listAniSelected.size > 0){
      int rowUnSelected = (int) listAniSelected.get(0).x;
      int colUnSelected = (int) listAniSelected.get(0).y;
      boardController.unSelectCellUi(listAniSelected.get(0));
      listAniSelected.removeIndex(0);
      animals.get(rowUnSelected).get(colUnSelected).setIsSelected(false);
    }
  }

  public void shuffle(){
    if(listAniSelected != null && listAniSelected.size > 0){
      int rowUnSelected = (int) listAniSelected.get(0).x;
      int colUnSelected = (int) listAniSelected.get(0).y;
      boardController.unSelectCellUi(listAniSelected.get(0));
      listAniSelected.removeIndex(0);
      animals.get(rowUnSelected).get(colUnSelected).setIsSelected(false);
    }
//
    //System.out.println("size list HInt: " + listAniHint.size);
    if(listAniHint != null && listAniHint.size > 0){
      boardController.unHintCellUi(listAniHint.get(0));
      boardController.unHintCellUi(listAniHint.get(1));
    }

    BoardAlgorithmsModel.shuffle(animals);
  }

  public void logAnimalId(){
    for(int i = 0; i < height; i++) {
      for(int j = 0; j < width; j++) {
        System.out.print(animals.get(i).get(j).getId() + " ");
      }
      System.out.println("\n");
    }
  }

  public Array<Array<AnimalModel>> getAnimals(){
    return animals;
  }

  public int getQuantityAnimal(){
    int rs = 0;
    for(int i = 0; i < BoardConfig.height; i++){
      for(int j = 0; j < BoardConfig.width; j++) {
        if(animals.get(i).get(j).getId() != -1){
          rs++;
        }
      }
    }
    return rs;
  }

  public Array<Vector2> cellTouchDown(int row, int col){
    Array<Vector2> path = new Array();
    if(listAniSelected.size == 1 && listAniSelected.get(0).x == row && listAniSelected.get(0).y == col) return path;

    animals.get(row).get(col).setIsSelected(true);
    listAniSelected.add(new Vector2(row, col));
    if(listAniSelected.size > 1){
      AnimalModel ani1 = animals.get((int) listAniSelected.get(0).x).get((int) listAniSelected.get(0).y);
      AnimalModel ani2 = animals.get((int) listAniSelected.get(1).x).get((int) listAniSelected.get(1).y);
      if(ani1.getId() == ani2.getId()){
        path = AlgorithmModel.checkAnimals(ani1, ani2, animals);
        if(path.size > 1){
          AnimalModel aniM1 = animals.get((int) listAniSelected.get(0).x).get((int) listAniSelected.get(0).y);
          AnimalModel aniM2 = animals.get((int) listAniSelected.get(1).x).get((int) listAniSelected.get(1).y);
          aniM1.matchCell();
          aniM2.matchCell();

          listAniSelected.removeIndex(0);
          listAniSelected.removeIndex(0);
          return path;
        }
        else {
          System.out.println("ani1: " + ani1.toString() + " - ani2: " + ani2.toString());
          int rowUnSelected = (int) listAniSelected.get(0).x;
          int colUnSelected = (int) listAniSelected.get(0).y;
          listAniSelected.removeIndex(0);
          animals.get(rowUnSelected).get(colUnSelected).setIsSelected(false);
          path.add(new Vector2(rowUnSelected, colUnSelected));
          System.out.println("cung id nhung khong path duoc: " + path);
          return path;
        }
      }
      else {
        int rowUnSelected = (int) listAniSelected.get(0).x;
        int colUnSelected = (int) listAniSelected.get(0).y;
        listAniSelected.removeIndex(0);
        animals.get(rowUnSelected).get(colUnSelected).setIsSelected(false);
        path.add(new Vector2(rowUnSelected, colUnSelected));
        return path;
      }
    }
    else {
      return path;
    }
  }

  public void newGame(){
    updateInfoConfigBoard();
    initAnimals();
  }

  public Array<Vector2> thunder(){
    Array<Vector2> listVt = getRandomPositionThunder();
    if(listVt.size == 0){
      return listVt;
    }

    if(listVt.size == 1){
      System.out.println("listvt: " + listVt);
      return listVt;
    }

    AnimalModel ani1 = animals.get((int)listVt.get(0).x).get((int)listVt.get(0).y);
    AnimalModel ani2 = animals.get((int)listVt.get(1).x).get((int)listVt.get(1).y);
    ani1.matchCell();
    ani2.matchCell();
    return listVt;
  }

  public Array<Vector2> thunder2(){
    Array<Vector2> listVts = getListAnimalThunder();
    for(int i = 0;i < listVts.size; i++) {
      AnimalModel ani = animals.get((int)listVts.get(i).x).get((int)listVts.get(i).y);
      ani.matchCell();
    }
    return listVts;
  }

//  private Array<Vector2> getListAnimalThunder(){
//    Array<Vector2> list = new Array<>();
//    int quantityAni = getQuantityAnimal();
//    if (BoardConfig.quantityAnimalThunder*2 >= quantityAni){
//      for(int i = 0; i < animals.size; i++) {
//        for(int j = 0; j < animals.get(i).size; j++) {
//          if(animals.get(i).get(j).getId() != -1){
//            list.add(new Vector2(i, j));
//          }
//        }
//      }
//      return list;
//    }
//
//    int anchorPoint = quantityAni/BoardConfig.quantityAnimalThunder;
//    Array<Integer> listIndex = new Array<>();
//    for(int i = 0; i < BoardConfig.quantityAnimalThunder; i++) {
//      int id = (int)Math.floor(Math.random()*anchorPoint) + 1;
//      id = id + i*anchorPoint;
//      listIndex.add(id);
//    }
//
//    //tim ani theo id, luu vao list
//    //tim ani tuong ung co id khac trong listindex, luu vao list
//    for(int i = 0; i < listIndex.size; i++ ){
//      int index = 0;
//      for(int j = 0; j < animals.size; j++){
//        for(int k = 0; k < animals.get(j).size; k++) {
//          if(animals.get(j).get(k).getId() != -1){
//            index++;
//            if(index == listIndex.get(i)){
//              list.add(new Vector2(j, k));
//              break;
//            }
//          }
//        }
//        if(i == list.size - 1){
//          break;
//        }
//      }
//    }
//
//
//    int sizeList = list.size;
//    for(int i = 0; i < sizeList; i++) {
//      for(int j = 0; j < animals.size; j++ ){
//        for(int k = 0; k < animals.get(j).size; k++) {
//          AnimalModel ani1 = animals.get((int)list.get(i).x).get((int)list.get(i).y);
//          AnimalModel ani2 = animals.get(j).get(k);
//          if(ani1.getId() == ani2.getId() && checkVtInList(list, new Vector2(j, k))){
//            list.add(new Vector2(j, k));
//            break;
//          }
//        }
//        if(list.size == sizeList + 1 + i){
//          break;
//        }
//      }
//    }
//
//    return list;
//  }

  private Array<Vector2> getListAnimalThunder(){
    Array<Vector2> list = new Array<>();
    int quantityAni = getQuantityAnimal();
    if (BoardConfig.quantityAnimalThunder*2 >= quantityAni){
      for(int i = 0; i < animals.size; i++) {
        for(int j = 0; j < animals.get(i).size; j++) {
          if(animals.get(i).get(j).getId() != -1){
            list.add(new Vector2(i, j));
          }
        }
      }
      return list;
    }

    int anchorPoint = quantityAni/BoardConfig.quantityAnimalThunder;
    Array<Integer> listIndex = new Array<>();
    for(int i = 0; i < BoardConfig.quantityAnimalThunder; i++) {
      int id = (int)Math.floor(Math.random()*anchorPoint) + 1;
      id = id + i*anchorPoint;
      listIndex.add(id);
    }

    Array<Integer> listIndexTemp = new Array<>();
    for(int i = 0; i < listIndex.size - 1; i++) {
      System.out.println("i: " + i);
      for(int j = i + 1; j < listIndex.size; j++ ){
        if(getIdAnimalById(listIndex.get(i)) == getIdAnimalById(listIndex.get(j))){
          listIndexTemp.add(listIndex.get(i), listIndex.get(j));
          listIndex.removeIndex(j);
          listIndex.removeIndex(i);
          int id = getIdRandom(listIndex, listIndexTemp, quantityAni);
          listIndex.add(id);
          i = -1;
          System.out.println("break;");
          break;
        }
      }
    }

    for(int i = 0; i < listIndexTemp.size; i++) {
      Vector2 vt = getVectorById(listIndexTemp.get(i));
      list.add(vt);
    }

    Array<Vector2> listTemp = new Array<>();
    for(int i = 0; i < listIndex.size; i++) {
      Vector2 vt = getVectorById(listIndex.get(i));
      listTemp.add(vt);
    }

    System.out.println("size list: " + list.size);
    System.out.println("size list temp: " + listTemp.size);
    for(int i = 0; i < listIndexTemp.size; i++ ){
      System.out.println("id list: " + getIdAnimalById(listIndexTemp.get(i)));
    }
    for(int i = 0; i < listIndex.size; i++) {
      System.out.println("id list temp: " + getIdAnimalById(listIndex.get(i)));
    }

    int sizeList = listTemp.size;
    for(int i = 0; i < sizeList; i++) {
      for(int j = 0; j < animals.size; j++) {
        for(int k = 0; k < animals.get(j).size; k++){
          AnimalModel ani1 = animals.get((int)listTemp.get(i).x).get((int)listTemp.get(i).y);
          AnimalModel ani2 = animals.get(j).get(k);
          if (ani1.getId() == ani2.getId() && checkVtInList(listTemp, new Vector2(j, k)) && checkVtInList(list, new Vector2(j, k))) {
            listTemp.add(new Vector2(j, k));
            break;
          }
        }
        if(listTemp.size == sizeList + 1 + i){
          break;
        }
      }
    }
    for(int i = 0; i < listTemp.size; i++ ){
      list.add(listTemp.get(i));
    }

    return list;
  }

  private int getIdAnimalById(int id){
    int index = 0;
    for(int i = 0; i < animals.size; i++) {
      for(int j = 0; j < animals.get(i).size; j++) {
        if(animals.get(i).get(j).getId() != -1){
          index++;
          if(index == id){
            return animals.get(i).get(j).getId();
          }
        }
      }
    }
    return -1;
  }

  private Vector2 getVectorById(int id){
    int index = 0;
    for(int i = 0; i < animals.size; i++) {
      for(int j = 0; j < animals.get(i).size; j++) {
        if(animals.get(i).get(j).getId() != -1){
          index++;
          if(index == id){
            return new Vector2(i, j);
          }
        }
      }
    }
    return new Vector2(-1, -1);
  }

  private int getIdRandom(Array<Integer> listIndex, Array<Integer> listIndexTemp, int quantity){
    int id = -1;
    boolean logic1, logic2, logic = true;
    while(logic){
      id = (int)Math.floor(Math.random()*quantity) + 1;
      logic1 = isExistInList(listIndex, id);
      logic2 = isExistInList(listIndexTemp, id);
      logic = logic1 || logic2;
    }
    return id;
  }

  private boolean isExistInList(Array<Integer> list, int id){
    for(int i : list){
      if(i == id){
        return true;
      }
    }
    return false;
  }

  private boolean checkVtInList(Array<Vector2> list, Vector2 vt){
    for(int i = 0; i < list.size; i++ ){
      if(vt.x == list.get(i).x && vt.y == list.get(i).y){
        return false;
      }
    }
    return true;
  }

  private Array<Vector2> getRandomPositionThunder(){
    Array<Vector2> vts = new Array<>();
    int firstIndex = (int)Math.floor(Math.random()*getQuantityAnimal()) + 1;
    int index = 0;
    for(int i = 0; i < animals.size; i++) {
      for(int j = 0; j < animals.get(i).size; j++){
        if(animals.get(i).get(j).getId() != -1){
          index++;
          if(index >= firstIndex){
            vts.add(new Vector2(i, j));
            break;
          }
        }
      }
      if(vts.size >= 1){
        break;
      }
    }
    if(vts.size == 0){
      return vts;
    }

    int x = (int)vts.get(0).x;
    int y = (int)vts.get(0).y;
    for(int i = 0; i < animals.size; i++) {
      for(int j = 0; j < animals.get(i).size; j++){

        if((i != x || j != y) && animals.get(i).get(j).getId() == animals.get(x).get(y).getId()){
          vts.add(new Vector2(i, j));
          break;
        }
      }
      if(vts.size >= 2){
        break;
      }
    }
    return vts;
  }
}
