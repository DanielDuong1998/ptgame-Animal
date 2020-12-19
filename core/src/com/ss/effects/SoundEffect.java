package com.ss.effects;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GSound;

/* renamed from: com.ss.effect.SoundEffect */
public class SoundEffect {
  public static int MAX_COMMON = 17;
  public static Music bgSound = null;
  public static Sound[] commons = null;
//  public static GSound[] commons = null;
  public static boolean muteMusic = false;
  public static boolean muteSfx = false;

  public static int click      = 0;
  public static int click1     = 1;
  public static int e1         = 2;
  public static int e2         = 3;
  public static int e3         = 4;
  public static int e4         = 5;
  public static int e5         = 6;
  public static int e6         = 7;
  public static int entry      = 8;
  public static int lose       = 9;
  public static int match      = 10;
  public static int pick       = 11;
  public static int score      = 12;
  public static int unlockrank = 13;
  public static int win        = 14;
  public static int clickPop   = 15;
  public static int mmatch      = 16;


  private static Sound[] explode;


  public static void initSound() {
    commons = new Sound[MAX_COMMON];

    commons[click]      = GAssetsManager.getSound("click.mp3");
    commons[click1]     = GAssetsManager.getSound("click1.mp3");
    commons[e1]         = GAssetsManager.getSound("e1.mp3");
    commons[e2]         = GAssetsManager.getSound("e2.mp3");
    commons[e3]         = GAssetsManager.getSound("e3.mp3");
    commons[e4]         = GAssetsManager.getSound("e4.mp3");
    commons[e5]         = GAssetsManager.getSound("e5.mp3");
    commons[e6]         = GAssetsManager.getSound("e6.mp3");
    commons[entry]      = GAssetsManager.getSound("e6.mp3");
    commons[lose]       = GAssetsManager.getSound("e6.mp3");
    commons[match]      = GAssetsManager.getSound("e6.mp3");
    commons[pick]       = GAssetsManager.getSound("e6.mp3");
    commons[score]      = GAssetsManager.getSound("e6.mp3");
    commons[unlockrank] = GAssetsManager.getSound("e6.mp3");
    commons[win]        = GAssetsManager.getSound("e6.mp3");
    commons[clickPop]   = GAssetsManager.getSound("clickpop.mp3");
    commons[mmatch]     = GAssetsManager.getSound("mmatch.mp3");

    bgSound = GAssetsManager.getMusic("bg.ogg");

  }

  public static long Play(int i) {
    long id = -1;
    if (!muteSfx) {
      id = commons[i].play();
      commons[i].setVolume(id,0.5f);
    }
    return id;
  }

  public static void Playmusic(int mode) {
    muteMusic = false;
    switch (mode) {
      default: bgSound.play();
    }
  }

  public static void Stopmusic(int mode) {
    muteMusic = true;
    switch (mode){
      default: bgSound.stop();
    }
  }
}
