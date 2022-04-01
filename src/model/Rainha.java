package model;

public class Rainha extends Peca {
	
	public static int next = 1;
	
	public Rainha() {
		super("Rainha " + next);
		next++;
	}

}