package com.example.prueba;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.modifier.IModifier;

import android.graphics.Color;

public class EscenaSplash extends Scene {
	Principal activity;
	public Text borjaAnselmo;
	public EscenaSplash(){
		setBackground(new Background(0.5f, 0.6f, 0.8f));
		activity = Principal.getSharedInstance();
		borjaAnselmo= new Text(0, 0, activity.mFuente, ("BORJAANSELMO.COM"),
				activity.getVertexBufferObjectManager());
		borjaAnselmo.setPosition(400-borjaAnselmo.getWidth()/2,240-borjaAnselmo.getHeight()/2);
		attachChild(borjaAnselmo);
		DelayModifier dMod = new DelayModifier(3,
				new IEntityModifierListener() {
					public void onModifierStarted(IModifier arg0, IEntity arg1) {
					}

					public void onModifierFinished(IModifier arg0, IEntity arg1) {
						activity.setCurrentScene(new EscenaMenu());
					}
				});
		registerEntityModifier(dMod);
		
	}
	
}
