package com.ss.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.ss.commons.TextureAtlasC;

//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.graphics.g2d.ParticleEffect;
//import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
//import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.Group;
//import com.badlogic.gdx.utils.Array;
//import com.ss.commons.TextureAtlasC;
//
//
//
public class effectWin extends Actor {
  FileHandle light            = Gdx.files.internal("particle/light3");
  FileHandle deadAni          = Gdx.files.internal("particle/aniDead");
  FileHandle select           = Gdx.files.internal("particle/select");
  FileHandle combo            = Gdx.files.internal("particle/combo");
  FileHandle thunder          = Gdx.files.internal("particle/thunder");
  FileHandle hammer           = Gdx.files.internal("particle/hammer");
  //////// index handel ///////
  public static int DeadAni = 1;
  public static int Light = 2;
  public static int Select = 3;
  public static int Combo = 4;
  public static int Thunder = 5;
  public static int Hammer = 6;
  public ParticleEffect effect;
  public ParticleEffectPool effectPool;
  public ParticleEffectPool.PooledEffect pooledEffect;
  private Actor parent = this.parent;
  private Group group;
  private Array<Array<Sprite>> arrSprite= new Array<>();
  public boolean isAlive = false;

  public effectWin(int id, float f, float f2, Group group) {

    this.group = group;
    this.effect = new ParticleEffect();
    this.effectPool = new ParticleEffectPool(effect,0,100);
    this.pooledEffect = effectPool.obtain();
    setX(f);
    setY(f2);
    if(id==DeadAni){
      this.effect.load(deadAni, TextureAtlasC.effectAtlas);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        Array<Sprite> arr = new Array<>();
        arr.addAll(this.effect.getEmitters().get(i).getSprites());
        arrSprite.add(arr);
      }

      this.effect.scaleEffect(1f);
//                this.effect.setDuration(4);

    }else if(id==Light){

      this.effect.load(light, TextureAtlasC.playAtlas);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
        ((ParticleEmitter) this.effect.getEmitters().get(i)).setFlip(true,false);
      }
      this.effect.scaleEffect(1.5f);
    }else if(id==Select){

      this.effect.load(select, TextureAtlasC.playAtlas);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
        ((ParticleEmitter) this.effect.getEmitters().get(i)).setFlip(true,false);
      }
      this.effect.scaleEffect(0.4f);
    }else if(id==Combo){

      this.effect.load(combo, TextureAtlasC.playAtlas);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
        ((ParticleEmitter) this.effect.getEmitters().get(i)).setFlip(true,false);
      }
      this.effect.scaleEffect(2f);
    }else if(id==Thunder){

      this.effect.load(thunder, TextureAtlasC.playAtlas);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
        ((ParticleEmitter) this.effect.getEmitters().get(i)).setFlip(true,false);
      }
      this.effect.scaleEffect(1.5f);
    }
    else if(id==Hammer){

      this.effect.load(hammer, TextureAtlasC.playAtlas);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
        ((ParticleEmitter) this.effect.getEmitters().get(i)).setFlip(true,false);
      }
      this.effect.scaleEffect(4f);
    }
    this.effect.setPosition(f, f2);
  }
  public void resetSprites(){
    for (int i=0;i<arrSprite.size;i++){
      for (int j=0;j<arrSprite.get(i).size;j++){
        arrSprite.get(i).get(j).setFlip(false,true);
      }
    }
    for (int i=0;i<arrSprite.size;i++){
      this.effect.getEmitters().get(i).getSprites().clear();
      this.effect.getEmitters().get(i).getSprites().addAll(arrSprite.get(i));
    }


  }
  public void changeSprites(int id){
    resetSprites();
    for (int i=0;i<this.effect.getEmitters().size;i++){
      if(this.effect.getEmitters().get(i).getSprites()!=null){
        this.effect.getEmitters().get(i).getSprites().swap(0,(id));
      }
    }

  }
  public void changeEffect(int id){

  }
  public void SetPosition(float x,float y){
    this.effect.setPosition(x,y);
  }

  @Override
  public boolean remove() {
    if(pooledEffect!=null)
      pooledEffect.free();
    return super.remove();
  }

  @Override
  public void act(float delta) {
    super.act(delta);
    this.effect.setPosition(getX(), getY());
    this.effect.update(delta);
    if(this.effect.isComplete()){
      remove();
      isAlive=false;
    }
  }



  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
    if (!this.effect.isComplete()) {
      this.effect.draw(batch);
      return;
    }
    this.effect.dispose();
  }


  public void setScale(float ratio){
    this.effect.scaleEffect(ratio);
  }

  public void setScale(float ratioX, float ratioY){
    this.effect.scaleEffect(ratioX, ratioY);
  }

  public void start() {
    isAlive = true;
    this.group.addActor(this);
    this.effect.start();
  }
}
