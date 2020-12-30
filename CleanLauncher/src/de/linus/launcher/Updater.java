package de.linus.launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import de.linus.advancedGui.util.FileHelper;
import de.linus.advancedGui.util.JsonReader;
import de.linus.advancedGui.util.Version;

public class Updater implements Runnable {

	private JSONObject json;
	private Version newVersion;

	public Updater() {
	}

	@Override
	public void run() {
		
		/** Gets the json from the site. **/
		
		try {
			json = JsonReader.readJsonFromUrl("https://muster.gmbh/wp-json/wp/v2/posts");
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}

		/** Gets the version from the json and checks if 
		 *  updates needs to be downloaded. Otherwise checks
		 *  if a old version needs to be deleted.
		 **/
		
		newVersion = new Version(getVersionFromJSON());
		if (newVersion.compareTo(CleanLauncher.version) == 1) {
			update();
		} else {
			if (oldVersionExists())
				try {
					deleteOldVersion();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	private String getVersionFromJSON() {
		String[] key = json.get("title").toString().split(":");
		return key[1].substring(key[1].indexOf("\"") + 1, key[1].indexOf("_", 1));
	}

	private void update() {
		/** Downloads the new update **/
		
		System.out.println("Start downloading to: ");
		System.out.println(new File(".").getAbsolutePath());
		System.out.print("Fine. \n");
		FileHelper.downloadFile(getFileLink(), new File(".").getAbsolutePath() + "/CleanLauncher.zip");
		try {
			/** Unzips the downloaded zip **/
			
			System.out.println("Start unzipping to: ");
			System.out.println(new File(".").getAbsolutePath());
			FileHelper.unzipFile(new File(".").getAbsolutePath(), "CleanLauncher.zip");
			System.out.print("Fine. \n");
			
			/** Adds a tmp file to tell the new update to delete this. **/
			createDelFile();
			
			/** Closes this program and starts the new one **/
			Runtime.getRuntime().exec("java -jar CleanLauncher-" + newVersion + ".jar");
			Thread.sleep(1000L);
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	private String getFileLink() {
		String[] key = json.get("title").toString().split(":");
		key[1] = key[1] + ":" + key[2];
		return key[1].substring(key[1].indexOf("_") + 1, key[1].indexOf("\"", 2));
	}

	private void createDelFile() throws IOException {
		File delFile = new File("./delete.txt");
		delFile.createNewFile();
		FileWriter myWriter = new FileWriter("./delete.txt");
		myWriter.write(new File(".").getAbsolutePath() + "/CleanLauncher-" + CleanLauncher.version + ".jar");
		myWriter.close();
	}

	private boolean oldVersionExists() {
		if(new File("./delete.txt").exists()) {
			return true;
		}
		return false;
	}

	private void deleteOldVersion() throws IOException {	
		  File file = new File("./delete.txt"); 
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  String oldPath = br.readLine(); 
		  
		  new File(oldPath).delete();
		  new File("./CleanLauncher.zip").delete();
		  file.delete();
		  br.close();
	}

}
