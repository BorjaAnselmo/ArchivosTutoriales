package com.example.si;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;


public class BaseActivity extends SimpleBaseGameActivity{
	

	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;

	

	private BitmapTextureAtlas mBitmapTextureAtlas;
	private ITextureRegion mBotonTextureRegion;
	private ButtonSprite boton;
	

	

	
	public EngineOptions onCreateEngineOptions() {
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	}

	
	public void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 512, 512);
		mBotonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this,
						"botontuto.png", 0, 0);
		this.mBitmapTextureAtlas.load();
		
	}

	
	public Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene();
		scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		
		boton = new ButtonSprite(100, 100, this.mBotonTextureRegion, this.getVertexBufferObjectManager(), new OnClickListener() {
            
           
            public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX,
                            float pTouchAreaLocalY) {      
                   boton.setPosition(boton.getX()+20, boton.getY()+20);
            }
    });
		
		scene.attachChild(boton);
		scene.registerTouchArea(boton);
		
		return scene;
	}


	


	

	
	
}
