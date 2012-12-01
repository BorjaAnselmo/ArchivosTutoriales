package com.example.si;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class BaseActivity extends SimpleBaseGameActivity implements
		IOnSceneTouchListener {

	private static final int CAMERA_WIDTH = 400;
	private static final int CAMERA_HEIGHT = 240;

	private BoundCamera mBoundCamera;

	private BitmapTextureAtlas mBitmapTextureAtlas;
	private ITextureRegion mJugadorTextureRegion;
	private TMXTiledMap mTMXTiledMap;
	private Sprite jugador;
	private Scene scene;

	public EngineOptions onCreateEngineOptions() {

		this.mBoundCamera = new BoundCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				this.mBoundCamera);
	}

	@Override
	public void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		this.mBitmapTextureAtlas = new BitmapTextureAtlas(
				this.getTextureManager(), 40, 40,
				TextureOptions.NEAREST_PREMULTIPLYALPHA);
		this.mJugadorTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "x.png", 0, 0);

		this.mBitmapTextureAtlas.load();
	}

	@Override
	public Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		scene = new Scene();

		try {
			final TMXLoader tmxLoader = new TMXLoader(this.getAssets(),
					this.mEngine.getTextureManager(),
					TextureOptions.NEAREST_PREMULTIPLYALPHA,
					this.getVertexBufferObjectManager());
			this.mTMXTiledMap = tmxLoader.loadFromAsset("tmx/map.tmx");

		} catch (final TMXLoadException e) {
			
		}

		final TMXLayer tmxLayer = this.mTMXTiledMap.getTMXLayers().get(0);
		scene.attachChild(tmxLayer);

		/* Make the camera not exceed the bounds of the TMXEntity. */
		this.mBoundCamera.setBounds(0, 0, tmxLayer.getWidth(),
				tmxLayer.getHeight());
		this.mBoundCamera.setBoundsEnabled(true);

		/* Create the sprite and add it to the scene. */
		jugador = new Sprite(0, 0, this.mJugadorTextureRegion,
				this.getVertexBufferObjectManager());
		this.mBoundCamera.setChaseEntity(jugador);
		scene.attachChild(jugador);

		scene.setOnSceneTouchListener(this);

		return scene;
	}

	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {

		if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {

			final float touchX = pSceneTouchEvent.getX();
			final float touchY = pSceneTouchEvent.getY();

			jugador.setPosition(touchX - (jugador.getWidth() / 2), touchY
					- (jugador.getHeight() / 2));

		}

		return false;

	}

}
