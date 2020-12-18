package com.ss.OfficialPackage.models;

public class AnimalModel {
  private int id;
  private int row;
  private int col;
  private boolean isSelected = false;

  public AnimalModel(int id, int row, int col){
    this.id = id;
    this.row = row;
    this.col = col;
  }

  public int getId() { return id; }

  public void setId(int id) { this.id = id; }

  public int getRow() { return row; }

  public void setRow(int row) { this.row = row; }

  public int getCol() { return col; }

  public void setCol(int col) { this.col = col; }

  public boolean getIsSelected(){
    return isSelected;
  }

  public void setIsSelected(boolean isSelected){
    this.isSelected = isSelected;
  }

  public void matchCell(){
    setIsSelected(false);
    setId(-1);
  }

  @Override
  public String toString() {
    return "AnimalModel{" +
            "id=" + id +
            ", row=" + row +
            ", col=" + col +
            '}';
  }
}

