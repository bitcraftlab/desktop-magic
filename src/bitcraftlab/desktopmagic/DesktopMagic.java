package bitcraftlab.desktopmagic;


import processing.core.*;


/**
 * Desktop magic class, for controlling everything outside your window.
 * 
 * @author ##author.name##
 * @example VolumeControl
 */
public class DesktopMagic {
	
	public final static String VERSION = "##library.prettyVersion##";
	public final static String NAME = "##library.name##";
	public final static String AUTHOR = "##author.name##";
	
	PApplet app;
	DesktopListener listener;
	
	/**
	 * a Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the Library.
	 * 
	 * @example Hello
	 * @param app reference to the processing app
	 */
	public DesktopMagic(PApplet app) {
		
		this.app = app;
		listener = new DesktopListener(app);
		
		info();
	
		// make sure dispose is called when processing shuts down
		app.registerMethod("dispose", this);
	}
	
	/**
	 * Print info about the Library.
	 * 
	 * @return String
	 */
	private void info() {
		System.out.printf("%s %s by %s\n", NAME, VERSION, AUTHOR);
	}
	
	/**
	 * return the version of the Library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}
	
	
	/**
	 * Show bezels when changing sound or brightness
	 */
	
	public void showBezel() {
		DesktopBezel.show();
	}
	
	/**
	 * Hide bezels when changing sound or brightness
	 */
	public void hideBezel() {
		DesktopBezel.hide();
	}
	
	
	/**
	 * Reset and unregister everything
	 */
	public void dispose() {
		DesktopListener.dispose();
		DesktopBezel.dispose();
		//System.out.println("done.");
	}
	

}

