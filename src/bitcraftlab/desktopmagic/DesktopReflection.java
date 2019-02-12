package bitcraftlab.desktopmagic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jnativehook.keyboard.NativeKeyEvent;
import processing.core.PApplet;

/**
 * Helper class to check for callback functions in the processing applet
 *  
 * @author ##author.name##
 */
public class DesktopReflection {
	
	PApplet app;
	
	/**
	 * @param app the processing app to be self-inspected
	 */
	DesktopReflection(PApplet app) {
		this.app = app;
	}
	
	/**
	 * try to invoke callback function, trying several signatures
	 * 
	 * @param name name of the callback function
	 * @param event native event to be passed if the signature allows for it
	 * @return true if a matching method was called
	 */
	public boolean invokeProcessingHooks(String name, NativeKeyEvent evt) {
		return invokeProcessingHook(name, evt)|| invokeProcessingHook(name);
	}

	/**
	 * try to invoke callback function
	 * 
	 * @param name name of the callback function
	 * @param evt native event passed to the callback function
	 * @return true if the callback was found in the processing app
	 */
	public boolean invokeProcessingHook(String name, NativeKeyEvent evt) {

		try {

			// create method object
			Method method = app.getClass().getDeclaredMethod(name, NativeKeyEvent.class);

			// try to call it
			method.invoke(app, evt);
			return true;

		} 
		catch (NoSuchMethodException e) {  }
		catch (IllegalAccessException e) {  }
		catch (InvocationTargetException e) { }

		return false;

	}

	/**
	 * @param name name of the callback function
	 * @return true if the callback was found in the processing app
	 */
	public boolean invokeProcessingHook(String name) {

		try {

			// create method object
			Method method = app.getClass().getDeclaredMethod(name);

			// try to call it
			method.invoke(app);
			return true;

		} 
		catch (NoSuchMethodException e) {  }
		catch (IllegalAccessException e) {  }
		catch (InvocationTargetException e) { }

		return false;

	}



}
