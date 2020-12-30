package de.linus.launcher;

import java.io.IOException;

import de.linus.advancedGui.AdvancedGUI;
import de.linus.advancedGui.util.Version;

public class CleanLauncher extends AdvancedGUI{
	private static CleanLauncher instance;
	public static final Version version = new Version("0.0.2");
	
	public CleanLauncher() {
		instance = this;
		Thread updateThread = new Thread(new Updater());
		updateThread.start();
		this.setWindow(new StartWindow());
	}
	
	public void openMC() {
		try {
			Runtime.getRuntime().exec("java -jar CleanLauncher.jar");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static CleanLauncher getCleanLauncher() {
		return instance;
	}
}
