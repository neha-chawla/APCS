package apcs;

// Neha Chawla
// Period 3
// 1/4/2015
// Element.java
// Element.java takes an atomic number, symbol, name, and an
// atomic mass value. Elements are converted to string with 
// toString methods and elements are arranged in order with
// compareTo methods.

import java.text.*;

public class Element{
private int atomicNumber;
	private String symbol;
	private String name;
	private double atomicMass;

	public Element(int an, String s, String n, double am)
	{
		atomicNumber = an;
		symbol = s;
		name = n;
		atomicMass = am;
	}
	
	public String toString(){
		DecimalFormat df = new DecimalFormat("##.00");
	    return String.format("      " + "%-7s %-10s %-13s %15s", atomicNumber, symbol, name, df.format(atomicMass) + "\n"); //2 DECIMAL PLACES & FORMAT
	}
	
	public String toStringWithLines(){
		DecimalFormat df = new DecimalFormat("##.00");
	    return String.format("      " + "%-7s %-10s %-13s %15s", "| " + atomicNumber, symbol, name, df.format(atomicMass) + "  |\n"); //2 DECIMAL PLACES & FORMAT
	}

	public int compareToName(Element other){
		int diff = this.name.compareTo(other.name);
		return diff;
	}
	
	public int compareToNum(Element other){
		int diff = this.atomicNumber - other.atomicNumber;
		return diff;
	}
	
	public int compareToSym(Element other){
		int diff = this.symbol.compareTo(other.symbol);
		return diff;
	}
	
	public double compareToMass(Element other){
		double diff = this.atomicMass - other.atomicMass;
		return diff;
	}
	
	public int compareToSym(String s){
		int diff = this.symbol.compareToIgnoreCase(s);
		return diff;
	}
}
