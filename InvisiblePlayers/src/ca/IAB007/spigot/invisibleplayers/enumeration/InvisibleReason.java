package ca.IAB007.spigot.invisibleplayers.enumeration;

/**
CREATED BY IAB007

DO NOT COPY WITHOUT PERMISSION
*/

public enum InvisibleReason {

	ALL("everybody"), COMMAND("commands"), INVISIBLE("invisible");
	
	public String s;
	
	InvisibleReason(String s) {
		this.s = s;
	}
	
	public String getString() {
		return s;
	}
	
}
