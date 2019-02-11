package bitcraftlab.desktopmagic;


import processing.core.*;

/**
 *  Desktop magic class, for controlling everything outside your window ...
 *
 * @example Hello 
 */

public class DesktopMagic {
	
	PApplet app;
	public final static String VERSION = "##library.prettyVersion##";
	

	/**
	 * a Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the Library.
	 * 
	 * @example Hello
	 * @param app reference to the processing app
	 */
	public DesktopMagic(PApplet app) {
		this.app = app;
		info();
	}
	
	/**
	 * Print info about the Library.
	 * 
	 * @return String
	 */
	private void info() {
		System.out.println("##library.name## ##library.prettyVersion## by ##author##");
	}
	
	/**
	 * return the version of the Library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}

}

