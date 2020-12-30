package de.linus.advancedGui.util;

public class Version implements Comparable<Version>{
	private String version;
	private String[] parts;
	private Integer[] partsInt;
	private String suffix;
	
	public Version(String version) {
		this.version = version;
		this.parts = version.split("\\.");
		
		try {
			Integer.parseInt(parts[parts.length - 1]);
		}catch(NumberFormatException e) {
			String[] split = this.parts[parts.length - 1].split("-");
			this.parts[parts.length - 1] = split[0];
			this.suffix = split[1];
		}
		
		partsInt = new Integer[parts.length];
		for(int i = 0; i < parts.length; i++) {
			partsInt[i] = Integer.parseInt(parts[i]);
		}
	}
	
	@Override
	public int compareTo(Version arg0) {	
		if(this.partsInt[0].compareTo(arg0.partsInt[0]) == 0) {
			if(this.partsInt[1].compareTo(arg0.partsInt[1]) == 0) {
					return this.partsInt[2].compareTo(arg0.partsInt[2]);
			}else {
				return this.partsInt[1].compareTo(arg0.partsInt[1]);
			}
		}else {
			return this.partsInt[0].compareTo(arg0.partsInt[0]);
		}
	}
	
	public static Version toVersion(String version) {
		return new Version(version);
	}
	
	@Override
	public String toString() {
		return this.version;
	}
	
	/**
	 * Returns true if the version has a suffix.
	 * 
	 * @return
	 */
	public boolean isPrerelease() {
		return suffix != null ? true : false;
	}
}
