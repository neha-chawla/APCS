package apcs;

//Openfile.java
//Utilities for opening a text file.
//The text file can be opened and read
//from, or the file can be opened (created)
//and written to.
//8/28/2015

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class OpenFile
{
	public static void main (String [] args)
	{
		Scanner infile = OpenFile.openToRead("g.txt");
		PrintWriter outfile = OpenFile.openToWrite("g2.txt");
		String temp = null;
		System.out.println("\n\n\n");
		while(infile.hasNext())
		{
			temp = infile.nextLine();
			System.out.println(temp);
			outfile.println(temp);
		}
		System.out.println("\n\n\n");
		infile.close();
		outfile.close();
	}
	
	public static Scanner openToRead(String fileString)
	{
		Scanner fromFile = null;
		File fileName = new File(fileString);
		try
		{
			fromFile = new Scanner(fileName);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("\nSorry, but the file could not be found.\n");
			System.exit(1);
		}
		return fromFile;
	}
	
	public static PrintWriter openToWrite(String fileString)
	{
		PrintWriter outFile = null;
		try
		{
			outFile = new PrintWriter(fileString);
		}
		catch(Exception e)
		{
			System.out.println("\nSorry, but the file could not be created.\n");
			System.exit(2);
		}
		return outFile;
	}
}