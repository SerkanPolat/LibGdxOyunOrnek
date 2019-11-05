package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemies {
	
	public float KordiX;
	public float KordiY;
	private Texture texture;
	private Sprite sprite;
	public float GenislikX;
	public float GenislikY;
	public Polygon polygon;
	Vector2 vec2;
	float HizCarpani = 1;
	boolean Patladi;
	long YokEdilmeZamani;
	
	public Enemies(float KordiX,float KordiY,float EkranBoyutX,float EkranBoyutY){
		
		Patladi = false;
		Random rnd = new Random();
		this.KordiX = KordiX;
		this.KordiY = KordiY;
		GenislikX = 55;
		GenislikY = 55;
		texture = new Texture("t"+(rnd.nextInt(3)+1)+".png");
		sprite = new Sprite(texture);
		sprite.setPosition(KordiX, KordiY);
		
		//EkranBoyutY = EkranBoyutY-this.KordiY;
		//System.out.println((this.KordiX)+"  "+(this.KordiY-EkranBoyutY));
		
		float DondurX = this.KordiX - (EkranBoyutX/2);
		float DondurY = this.KordiY - (EkranBoyutY/2);
		
		//System.out.println(DondurX+"  "+DondurY);
		
		polygon = new Polygon(new float[]{KordiX,KordiY,
										  KordiX+50,KordiY,
										  KordiX+50,KordiY+50,
										  KordiX,KordiY+50});
		RectAyarla();
		HareketVektoruAyarla(DondurX,DondurY);
		Dondur((float)Math.toDegrees(-Math.atan2(DondurX,DondurY)));
		
	}
	private void HareketVektoruAyarla(float DondurX,float DondurY) {
		
		vec2 = new Vector2();
		vec2.x = DondurX;
		vec2.y = DondurY;
		
	}
	private void RectAyarla() {
		//polygon.setPosition(KordiX, KordiY);
		polygon.setOrigin(KordiX+GenislikX/2, KordiY+GenislikY/2);
		
	}
	public void Ciz(SpriteBatch batch){
		sprite.setPosition(KordiX, KordiY);	
	//	System.out.println(Math.toDegrees(Math.atan2(KordiX,KordiY)));
		sprite.draw(batch);
		
	}
	public void Dondur(float degrees){
	//	System.out.println(polygon.getRotation());
		sprite.rotate(degrees);
		polygon.rotate(degrees);
		vec2.rotate(180);
	}
	
	public void HareketEt(){
		
		vec2.nor();
		
		float SpeedX = vec2.x*HizCarpani;
		float SpeedY = vec2.y*HizCarpani;
		
		polygon.translate(SpeedX,SpeedY);
		KordiX += SpeedX;
		KordiY += SpeedY;
		
	}
	
	public void YokEt(long SystemTime){
		YokEdilmeZamani = SystemTime;
		HizCarpani = 0;
		texture = new Texture("boom.png");
		sprite.setTexture(texture);
		Patladi = true;
	
	}
	
}
