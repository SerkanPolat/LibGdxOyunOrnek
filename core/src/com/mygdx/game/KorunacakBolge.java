package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class KorunacakBolge {
	
	private Texture KorunacakBolgeTexture;
	private float BolgeXKordi;
	private float BolgeYKordi;
	Polygon BaseDomain;
	private float BaseH = 60;
	private float BaseW = 60;
	
	public KorunacakBolge(Texture KorunacakBolgeTexture,float BolgeXKordi,float BolgeYKordi){
		
		this.KorunacakBolgeTexture = KorunacakBolgeTexture;
		this.BolgeXKordi = BolgeXKordi-25;
		this.BolgeYKordi = BolgeYKordi-14;
		BaseDomainAyarla();
		
	}
	
	private void BaseDomainAyarla() {
		
		BaseDomain = new Polygon(new float[]{BolgeXKordi,BolgeYKordi,
											 BolgeXKordi+BaseH,BolgeYKordi,
											 BolgeXKordi+BaseH,BolgeYKordi+BaseW,
											 BolgeXKordi,BolgeYKordi+BaseW});
		
		
	}

	public void Ciz(SpriteBatch batch){
		
		batch.draw(KorunacakBolgeTexture,BolgeXKordi,BolgeYKordi,BaseH,BaseW);
		
	}
	
}
