package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.Polygon;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor{

	ShapeRenderer shaperenderer;
	SpriteBatch batch;
	int viewPortX,viewPortY;
	OrthographicCamera kamera;
	Viewport viewport;
    int KameraGenislik;
    int KameraUzunluk;
	Texture ArkaPlan;
	World Dunya;
	Vector3 worldCoordinates;
	@Override
	public void create () {
		
		Gdx.input.setInputProcessor(this);
        KameraGenislik = 200*4;
        KameraUzunluk = 200*3;
        batch = new SpriteBatch();
        kamera = new OrthographicCamera(KameraGenislik,KameraUzunluk);
		viewport = new FitViewport(KameraGenislik, KameraUzunluk);
		viewport.setCamera(kamera);
		viewport.apply();
        kamera.position.set(kamera.viewportWidth/2,kamera.viewportHeight/2,0);
        ArkaPlan = new Texture("arka1.jpg");
        Dunya = new World(kamera.position.x,kamera.position.y);
        kamera.update();
		shaperenderer = new ShapeRenderer();
		
	}
	float skor=0;
	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); 
		if(Dunya.Game_State == Dunya.GAME_RUNNING)
			OyunMantiginiGuncelle();
		
		OyunGrafikleriniGuncelle();
		
	}
	private void OyunGrafikleriniGuncelle() {

		batch.setProjectionMatrix(kamera.combined);
		kamera.update();
		batch.begin();
		
		batch.draw(ArkaPlan,0,0,KameraGenislik,KameraUzunluk);
		
		Dunya.DunyayiCiz(batch);
		
		batch.end();
/*
		shaperenderer.setProjectionMatrix(kamera.combined);
		shaperenderer.begin(ShapeType.Line);
		shaperenderer.setColor(Color.WHITE);
		
		for(int i = 0;i<Dunya.enemies.size;i++) {
			
			shaperenderer.rect(Dunya.enemies.get(i).EnemyRect.x, Dunya.enemies.get(i).EnemyRect.y,
					Dunya.enemies.get(i).EnemyRect.width,
					Dunya.enemies.get(i).EnemyRect.height);
			
			shaperenderer.polygon(Dunya.enemies.get(i).polygon.getTransformedVertices());
			shaperenderer.polygon(Dunya.korunacakBolge.BaseDomain.getTransformedVertices());
			
		}
		
		shaperenderer.end();
	
	*/	
	}
	private void OyunMantiginiGuncelle() {
		Dunya.DusmanGuncelle(System.nanoTime());
		Dunya.DusmaniHareketEttir();
		Dunya.CarpismaKontrolu();
		
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		worldCoordinates = kamera.unproject(new Vector3(screenX, screenY, 0));
		
		Dunya.TiklamaKontrolcusu(worldCoordinates,System.nanoTime());
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		System.out.println(screenX+"  "+screenY);
		
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		
		return true;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}















































