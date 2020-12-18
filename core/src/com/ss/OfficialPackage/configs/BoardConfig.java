package com.ss.OfficialPackage.configs;

public class BoardConfig {

  public static int width = 17;
  public static int height = 8;

  public static int maxWidth = 17;
  public static int maxHeight = 8;

  public static int quantityAnimal = 47; // số lượng loại con vật lúc init
  public static int maxQuantityInitAnimal = 20; // số lượng con trong pool lúc init mỗi loại
  public static float scallingBox = 1.21f; // tỉ lệ scale boxUI
  public static float scallingAni = scallingBox*(float)1.35f/1.21f; // tỉ lệ scale aniUI

}
