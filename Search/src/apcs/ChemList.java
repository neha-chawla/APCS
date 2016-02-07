package apcs;

// Neha Chawla
// Period 3
// 1/4/2015
// ChemList.java
// ChemList.java populates an array of Elements called elementsList from the
// textfile elements.txt. It provided 4 different sorting options for the user
// along with a symbol-searching option with the 4th type of search. The sorting
// options are bubble sort, selection sort, merge sort, and insertion sort.

import java.util.Scanner;
import java.util.ArrayList;

public class ChemList{
	private ArrayList<Element> elementsList;
	
	public static void main(String[]args){
		ChemList start = new ChemList();
		start.menu();
	}

	public void loadFile(){
		int i;
		int[]atomicNumbers = new int[118];
		String[]symbols = new String[118];
		String[]names = new String[118];
		double[]atomicMasses = new double[118];
		Scanner infile = OpenFile.openToRead("elements.txt");
		i = 0;
		while(infile.hasNextInt()){
			atomicNumbers[i] = (infile.nextInt());
			symbols[i] = (infile.next());
			names[i] = (infile.next());
			atomicMasses[i] = (infile.nextDouble());
			if(infile.hasNextLine()){
				infile.nextLine();
			}
			i++;
		}
		elementsList = new ArrayList<Element>();
		for(i = 0; i < 118; i++){
			Element adder = new Element(atomicNumbers[i], symbols[i], names[i], atomicMasses[i]);
			elementsList.add(i, adder);
		}
	}
	
	public void menu(){
		System.out.println("\n\n\n1: Display Elements sorted by name");
		System.out.println("2: Display Elements sorted by number");
		System.out.println("3: Display Elements sorted by atomic mass");
		System.out.println("4: Search for an Element");
		System.out.println("5: Exit\n");
		callSort();
	}
	
	public void callSort(){
		int i;
		int sortType = promptUser();
		
		System.out.print("\n\n");
		if(sortType < 4){
			String headings = String.format("%9s %10s %8s %23s", "| #", "Symbol", "Name", "Atomic Mass |");
			System.out.println("      +----------------+");
			System.out.println("      |List of Elements| ");
			System.out.println("      +----------------+----------------------------+");
			System.out.println(headings);
		}
		if(sortType == 1){
			loadFile();
			bubbleSort(elementsList);
			int count = 0;
			for(i = 0; i < 118; i++){
				Element element1 = elementsList.get(i);
				String elementString = element1.toStringWithLines();
				if(count%5 == 0){
					System.out.println("      |                                             |");
				}
				System.out.print(elementString);
				count++;
			}
		}
		else if(sortType == 2){
			loadFile();
			selectionSort(elementsList);
			int count = 0;
			for(i = 0; i < 118; i++){
				Element element1 = elementsList.get(i);
				String elementString = element1.toStringWithLines();
				if(count%5 == 0){
					System.out.println("      |                                             |");
				}
				System.out.print(elementString);
				count++;
			}
		}
		else if(sortType == 3){
			loadFile();
			mergeSort(elementsList, 0, 118);
			int count = 0;
			for(i = 0; i < 118; i++){
				Element element1 = elementsList.get(i);
				String elementString = element1.toStringWithLines();
				if(count%5 == 0){
					System.out.println("      |                                             |");
				}
				System.out.print(elementString);
				count++;
			}
		}
		else if(sortType == 4){
			loadFile();
			insertionSort(elementsList);
			System.out.println("----------------------------------------------------------------------- ");
			String symbolToFind = promptSymbol();
			while(!symbolToFind.equals("-1")){
				int foundIndex = binarySearch(elementsList, symbolToFind);
				if(foundIndex == -1){
					System.out.println("----------------------------------------------------------------------- ");
				}
				else{
					System.out.print(elementsList.get(foundIndex));
					System.out.println("\n----------------------------------------------------------------------- ");
				}
				symbolToFind = promptSymbol();
			}
		}
		else if(sortType == 5){
			System.out.println("\n\nThanks for checking through the Elements!");
			System.exit(1);
		}
		if(sortType < 4){
			System.out.println("      +---------------------------------------------+");
		}
			menu();
	}
	
	public int promptUser(){
		int elementSort = Prompt.getInt("\nPlease Enter 1 through 5, indicating your choice from the menu above: "); //prompt
		return elementSort;
	}
	
	public String promptSymbol(){
		String symbolChosen = Prompt.getString("\nPlease enter an Atomic Symbol to search for (-1 to exit): ");
		return symbolChosen;
	}
	
	public void bubbleSort (ArrayList <Element> elementsList){
		for(int outer = 0; outer < elementsList.size(); outer++) { 
			for(int inner = outer + 1; inner < elementsList.size(); inner++) { 
				if(elementsList.get(inner).compareToName(elementsList.get(outer)) < 0) { 
					swap(inner, outer);
				} 
			}
		}
    }
	
	public void selectionSort (ArrayList <Element> elementsList){
        int min = 0;
        for (int outer = 0; outer < elementsList.size() - 1; outer++){
            min = outer;
            for (int inner = outer + 1; inner < elementsList.size(); inner++){
				if (elementsList.get(inner).compareToNum(elementsList.get(min)) < 0){
					swap(inner, outer);
				}
            }
        }
    }
	
	public void mergeSort (ArrayList <Element> elementsList, int from, int to){
		if (to - from < 2){
			if (to > from && elementsList.get(to).compareToMass(elementsList.get(from)) < 0){
				swap(to, from); 
			}
		}
		else{
			int middle = (from + to) / 2;
			mergeSort(elementsList, from, middle);
			mergeSort(elementsList, middle + 1, to);
			merge(elementsList, from, middle, to);
		}
    }

    public void merge (ArrayList <Element> elementsList, int from, int middle, int to){
		int i = from;
		int j = middle + 1;
		int k = from;
		ArrayList<Element> temp = new ArrayList<Element>();
		
		for(int m = 0; m < 118; m++){
			temp.add(m,  null);
		}
		
		if(i < 118 && j < 118 && k < 118 && to < 118){
			while (i <= middle && j <= to){
				if (elementsList.get(i).compareToMass(elementsList.get(j)) < 0){
					temp.set(k, elementsList.get(i));
					i++;
				}
				else{
					temp.set(k, elementsList.get(j));
					j++;
				}
				k++;
			}
			
			while (i <= middle){
				temp.set(k, elementsList.get(i));
				i++;
				k++;
			}
			
			while (j <= to){
				temp.set(k, elementsList.get(j));
				j++;
				k++;
			}
			
			for (k = from; k <= to; k++){
				elementsList.set(k, temp.get(k));
			}
		}
    }
	
	public void insertionSort (ArrayList <Element> elementsList){
        for (int outer = 1; outer < elementsList.size(); outer++){
            int position = outer;
            Element key = elementsList.get(position);
            while (position > 0 && elementsList.get(position - 1).compareToSym(key) > 0){
                elementsList.set(position, elementsList.get(position - 1));
                position--;
            }
            elementsList.set(position, key);
        }
    }
	
	public int binarySearch(ArrayList <Element> elementsList, String symbol){
		int NOT_FOUND = -1;
		int low = 0;
        int high = elementsList.size() - 1;
        int mid;
        int steps = 0;

        while( low <= high ){
        	steps++;
        	mid = ( low + high ) / 2;
        	Element binaryElement = elementsList.get(mid);
            if( binaryElement.compareToSym(symbol) < 0 ){	
            	low = mid + 1;
            }
            else if( binaryElement.compareToSym(symbol) > 0 ){ //elementsList.symbol
            	high = mid - 1;
            }
            else{
            	System.out.println("\nThe binary search took " + steps + " steps to find this Element\n\nThis element is: \n");
                return mid;
            }
        }
        System.out.println("\nThe binary search took " + steps + " to determine that this Element does not exist.\n");
        return NOT_FOUND;     // NOT_FOUND = -1
	}
	
	public ArrayList<Element> swap(int i, int j){
		Element temp = elementsList.get(j);
		elementsList.set(j, elementsList.get(i)); 
		elementsList.set(i, temp);
		return elementsList;
	}
}