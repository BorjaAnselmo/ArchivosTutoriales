package com.example.prueba;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Typeface;




public class Principal extends SimpleBaseGameActivity {
	
	
	static final int CAMARA_ANCHO = 800;
	static final int CAMARA_ALTO = 480;
	public Camera mCamara;
	public BitmapTextureAtlas mBitmapTextureAtlas;
	public ITextureRegion imagen;
	public Font mFuente;
	public Scene mActualEscena;
	public static Principal instance;
	
	public static Principal getSharedInstance() {
		return instance;
	}

	@Override
	public EngineOptions onCreateEngineOptions() {
		instance = this;
		mCamara = new Camera(0, 0, CAMARA_ANCHO, CAMARA_ALTO);
		
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,new RatioResolutionPolicy(CAMARA_ANCHO, CAMARA_ALTO), mCamara);
	}

	@Override
	protected void onCreateResources() {
		//Fuentes de texto
		this.mFuente = FontFactory.create(this.getFontManager(), this.getTextureManager(), 500, 500, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 60);
		this.mFuente.load();
		//Sprites
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(),
				300, 300, TextureOptions.NEAREST_PREMULTIPLYALPHA);
		imagen = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mBitmapTextureAtlas, this, "x.png", 0, 0);
		mBitmapTextureAtlas.load();
	}

	@Override
	protected Scene onCreateScene() {
		
		mEngine.registerUpdateHandler(new FPSLogger());
		mActualEscena = new EscenaSplash();
		return mActualEscena;

	}
	public void setCurrentScene(Scene escena) {

		mActualEscena = escena;

		getEngine().setScene(mActualEscena);
		
	}

	
}
