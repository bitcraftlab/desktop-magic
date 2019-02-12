package bitcraftlab.desktopmagic;

import processing.core.PApplet;
import static processing.core.PApplet.platform;
import static processing.core.PConstants.MACOSX;

/**
 *  Desktop bezel class, for hiding and showing bezels, that are triggered by media keys
 *  
 * @author ##author.name##
 * 
 */

public class DesktopBezel {
	
	PApplet app;
	static boolean showBezel = true;
	
	
	/**
	 * Hide bezels
	 */
	static void hide() {
		if(platform == MACOSX) {
			PApplet.exec("launchctl", "unload", "-F", "/System/Library/LaunchAgents/com.apple.OSDUIHelper.plist"); 
		}
		showBezel = false;
	}

	
	/**
	 * Show bezels 
	 */
	static void show() {
		if(PApplet.platform == MACOSX) {
			PApplet.exec("launchctl", "load", "-F", "/System/Library/LaunchAgents/com.apple.OSDUIHelper.plist");
		}
		showBezel = true;
	}
	
	
	/**
	 *  Reset bezels
	 */
	static void dispose() {
		// show the bezel again
		if(!showBezel) {
			show();
		}
	}


}
