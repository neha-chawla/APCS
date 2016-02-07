// Coins.java
// Inputs an integer number of cents and output
// every combination of quarters, dimes, nickels,
// and pennies that equal that amount.
// Neha Chawla
// 9/8/2015

package apcs; 

public class Coins{
	private int input;
	private int combonumber = 0;
	private int quarternumber;
	private int dimenumber;
	private int nickelnumber;
	private int pennynumber;
	private int maxquarters;
	private int maxdimes;
	private int maxnickels;
	private int leftafterquarters;
	private int leftafterdimes;
	private int leftafternickels;
	
    public static void main(String[]args){
        Coins run = new Coins();
        run.getInput();
        run.printCombos();
        run.printTotalCombos();
    }
    
    public void getInput(){
    	System.out.println("\n\n\n");
    	input = Prompt.getInt("Please enter a number" + " (from " + 1 + " to " + 1000 + "): ");
    }
    
    public void printCombos(){
    	System.out.print("\n\n");
    	maxquarters = (input/25); 
    	for(quarternumber = maxquarters; quarternumber >=0; quarternumber--){
    		leftafterquarters = input-(quarternumber*25);
	    	if(leftafterquarters==0){
	    		quarternumber = maxquarters;
	    		dimenumber = 0;
	    		nickelnumber = 0;
	    		pennynumber = 0;
	    	}
	    	else{
	    		maxdimes = (leftafterquarters/10);
	    		dimenumber = maxdimes;
		    	for(dimenumber = maxdimes; dimenumber>=0; dimenumber--){
		    		leftafterdimes = leftafterquarters-(dimenumber*10);
		    		if(leftafterdimes==0){
		    			dimenumber = maxdimes;
		    			nickelnumber = 0;
		    			pennynumber = 0;
		    		}
		    		else{ 
		    			maxnickels = (leftafterdimes/5);
		    			nickelnumber = maxnickels;
			    		for(nickelnumber = maxnickels; nickelnumber>=0; nickelnumber--){
			    			leftafternickels = (leftafterdimes-(nickelnumber*5));
		    				if(leftafternickels==0){
			    				pennynumber = 0;
			    			}
			    			else{
			    				pennynumber = (leftafternickels);
			    			}
 		    				System.out.printf("%d", input);
 		    				System.out.printf("\t=\t %d", quarternumber);
 		    				System.out.printf(" quarters + \t  %d", dimenumber);
 		    				System.out.printf(" dimes + \t %d", nickelnumber);
 		    				System.out.printf(" nickels + \t%d", pennynumber);
 		    				System.out.println(" pennies");
		    				combonumber++;
			    		}
		    		}
		    	}
	    	}
    	}
    }
    
    public void printTotalCombos(){
    	System.out.print("\n\nA total of " + combonumber + " combinations are possible with " + input + " cents.");
    }

}
