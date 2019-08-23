package bitcraftlab.desktopmagic;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

//Import static key codes 
import static org.jnativehook.keyboard.NativeKeyEvent.*;

import java.util.logging.Logger;
import processing.core.PApplet;


/**
 * The DesktopListener class listens for native desktop events
 * 
 * @author ##author.name##
 */

public class DesktopListener implements NativeKeyListener {

	PApplet app;
	
	final static String VOLUME_UP = "desktopVolumeUp";
	final static String VOLUME_DOWN = "desktopVolumeDown";
	
	final static String KEY_RELEASED = "desktopKeyReleased";
	final static String KEY_PRESSED = "desktopKeyPressed";
	final static String KEY_TYPED = "desktopKeyTyped";

	static boolean debug = false;

	int mutecounter = 0;

	public int keyCode;
	public String key;

	public int mouseX;
	public int pmouseX;

	public int mouseY;
	public int pmouseY;

	private DesktopReflection reflection;

	
	/**
	 * @param app the processing applet
	 */
	public DesktopListener(PApplet app) {

		// Register processing app
		this.app = app;

		// Disable extensive logging of native events
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setUseParentHandlers(false);
		
		// Activate callbacks on the current Processing App
		reflection = new DesktopReflection(app);

		// Activate Native hook
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			System.exit(1);
		}
		GlobalScreen.addNativeKeyListener(this);



	}


	public void nativeKeyReleased(NativeKeyEvent event) {

		// assign key variables
		keyCode =event.getKeyCode();
		key = NativeKeyEvent.getKeyText(keyCode);

		// invoked key released hook
		if(!call(KEY_RELEASED, event)) {
			if(debug) System.err.println("Key Released: " + NativeKeyEvent.getKeyText(event.getKeyCode()));
		}

		// invoke special triggers
		invokeReleaseTriggers(event);

	}

	
	public void nativeKeyTyped(NativeKeyEvent event) {
		// For some reason nativeKeyTyped returns "Undefined" ...
		if(!call(KEY_TYPED, event)) {
			if(debug) System.err.println("Key Typed: " + NativeKeyEvent.getKeyText(event.getKeyCode()));
		}
	}

	
	public void nativeKeyPressed(NativeKeyEvent event) {

		// assign key variables
		keyCode =event.getKeyCode();
		key = NativeKeyEvent.getKeyText(keyCode);

		// invoke keypressed hook
		if(!call(KEY_PRESSED, event)) {
			if(debug) System.err.println("Key Pressed: " + NativeKeyEvent.getKeyText(event.getKeyCode()));   
		}

		// invoke special triggers
		invokePressTriggers(event);

	}

	public void invokePressTriggers(NativeKeyEvent event) {

		switch(event.getKeyCode()) {
		}

	}

	public void invokeReleaseTriggers(NativeKeyEvent event) {

		switch(event.getKeyCode()) {
		case VC_VOLUME_MUTE:
			// this is a hack since the mute event is triggered twice!
			mutecounter++;
			if (mutecounter % 2 == 1) {
				call("desktopMute");
			}
			break;
		case VC_VOLUME_UP:
			call(VOLUME_UP);
			break;
		case VC_VOLUME_DOWN:
			call(VOLUME_DOWN);
			break;
		}

	}

	static void dispose() {
		try {
			GlobalScreen.unregisterNativeHook();
		} 
		catch (NativeHookException e) {
			e.printStackTrace();
		}
	}
	
	private boolean call(String hook) {
		return reflection.invokeProcessingHook(hook);
	}
	
	private boolean call(String hook, NativeKeyEvent event) {
		return reflection.invokeProcessingHook(hook, event);
	}

	public static void showDebug(){
		debug = true; 
	}



}
