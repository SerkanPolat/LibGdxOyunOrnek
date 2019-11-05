package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class World {
	
	Array<Enemies> enemies;
	Random rnd;
	KorunacakBolge korunacakBolge;
	float EkranBoyutuX;
	float EkranBoyutuY;
	boolean GAME_RUNNING = true;
	boolean GAME_STOPPED = false;
	boolean Game_State;
	int OyunSkor;
	
	public World(float EkranMerkezX,float EkranMerkezY){
		Game_State = GAME_RUNNING;
		OyunSkor = 0;
		
		EkranBoyutuX = EkranMerkezX*2;
		EkranBoyutuY = EkranMerkezY*2;
		
		rnd = new Random();
		enemies = new Array<Enemies>();
        korunacakBolge = new KorunacakBolge(new Texture("base.png"), EkranMerkezX,EkranMerkezY);
        
        
	}
	boolean CollisionCheck;
	public void DusmanOlustur(){
		float KordiX=0;
		float KordiY=0;
		CollisionCheck = true;
		while(CollisionCheck){
			KordiX = rnd.nextInt(600);
			KordiY = rnd.nextInt(800);
		
			if(rnd.nextInt(2)==1){
		
				if(KordiX<EkranBoyutuX/2){
					KordiX-=EkranBoyutuX/2;
			
				}else{
					KordiX+=EkranBoyutuX/2;
				}
			}
			else{
				if(KordiY<EkranBoyutuY/2){

					KordiY-=EkranBoyutuY/2;
				}else{

					KordiY+=EkranBoyutuY/2;
				}
			}
			if(enemies.size==0){
				enemies.add(new Enemies(KordiX,KordiY,EkranBoyutuX,EkranBoyutuY));
				CollisionCheck = false;
			}
			else
			for(int i = 0;i<enemies.size;i++){
				if(!Intersector.overlapConvexPolygons(new Polygon(new float[]{KordiX,KordiY,
										  KordiX+50,KordiY,
										  KordiX+50,KordiY+50,
										  KordiX,KordiY+50}), enemies.get(i).polygon)){
					
					CollisionCheck = false;
				
					
				}else{
					CollisionCheck = true;
				}
			}
		}
		
		enemies.add(new Enemies(KordiX,KordiY,EkranBoyutuX,EkranBoyutuY));
		
	}
	
	public void DusmaniHareketEttir(){
		
		//enemies.get(2).Dondur(1);
		
		for(int i = 0;i<enemies.size;i++){
			
			enemies.get(i).HareketEt();
			
		}
	}
	
	
	public void DunyayiCiz(SpriteBatch batch){
		
		for(int i = 0 ;i<enemies.size;i++){
			
			enemies.get(i).Ciz(batch);
			
		}
		korunacakBolge.Ciz(batch);
	}
	
	public boolean CarpismaKontrolu(){
		
		for(int i = 0;i<enemies.size;i++){
			
			if(Intersector.overlapConvexPolygons(enemies.get(i).polygon, korunacakBolge.BaseDomain)){
				
				Game_State = GAME_STOPPED;
			
			}
			
		}
		
		return false;
	}
	public void DusmanYokEt(Enemies enemy,long SystemTime) {
		
		enemy.YokEt(SystemTime);
		
	}
	public void DusmanGuncelle(long SystemTime) {
		
		if(enemies.size<20)
			DusmanOlustur();
		
		for(int i = 0;i<enemies.size;i++){
			
			if(enemies.get(i).Patladi==true){
				if(SystemTime-enemies.get(i).YokEdilmeZamani>500000000){
					OyunSkor++;
					enemies.removeIndex(i);

					System.out.println(OyunSkor);
				}
				
			}
			
		}	
	}
	
	public void TiklamaKontrolcusu(Vector3 worldCoordinates,long SystemTime){

		if(Game_State == GAME_RUNNING){
		Polygon pol = new Polygon(new float[]{worldCoordinates.x,worldCoordinates.y,
				  worldCoordinates.x+1,worldCoordinates.y,
				  worldCoordinates.x+1,worldCoordinates.y+1,
				  worldCoordinates.x,worldCoordinates.y+1});
		
		for(int i = 0;i < enemies.size;i++){
			
			if(Intersector.overlapConvexPolygons(pol, enemies.get(i).polygon)){
				
					DusmanYokEt(enemies.get(i),SystemTime);
				
				}
			}
		}
	}
}
