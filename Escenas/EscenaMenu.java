package com.example.prueba;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;

public class EscenaMenu extends MenuScene implements IOnMenuItemClickListener {
	
	Principal activity;
	
	public EscenaMenu()
	{
		super(Principal.getSharedInstance().mCamara);
		
		activity = Principal.getSharedInstance();
		setBackground(new Background(0.3f, 0.7f, 0.7f));
		IMenuItem BotonImagen = new SpriteMenuItem(0 , activity.imagen, activity.getVertexBufferObjectManager());
		IMenuItem BotonTexto = new TextMenuItem(1 ,activity.mFuente,"Principal", activity.getVertexBufferObjectManager());
		BotonImagen.setPosition(100,100);
		BotonTexto.setPosition(500,150);
		addMenuItem(BotonImagen);
		addMenuItem(BotonTexto);
		setOnMenuItemClickListener(this);
		
		
	}
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch (pMenuItem.getID()) {
		
		case 0:
			activity.setCurrentScene(new EscenaPrincipal());
   		 return true;
		case 1:
			activity.finish();
   		 	return true;
			
		 default:
				
	            break;
	
	    }
	
	    return false;

	}
		

}
