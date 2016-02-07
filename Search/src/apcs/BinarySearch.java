package apcs;

public class BinarySearch{
    public static final int NOT_FOUND = -1;

    /**
     * Performs the standard binary search
     * using two comparisons per level.
     * @return index where item is found, or NOT_FOUND.
     */
		public static int binarySearch( Integer [ ] a, Integer x ){
    	int low = 0;
        int high = a.length - 1;
        int mid;

        while( low <= high ){
        	mid = ( low + high ) / 2;

            if( a[ mid ].compareTo( x ) < 0 ){
            	low = mid + 1;
            }
            else if( a[ mid ].compareTo( x ) > 0 ){
            	high = mid - 1;
            }
            else{
                return mid;
            }
        }
        return NOT_FOUND;     // NOT_FOUND = -1
	}

    // Test program
    public static void main( String [ ] args ){
    	int SIZE = 20;
        Integer [ ] a = new Integer [ SIZE ];
        for( int i = 0; i < SIZE; i++ ){
        	a[ i ] = new Integer( i * 2 );
        }

        for( int i = 0; i < SIZE * 2; i++ ){
        	System.out.println( "Found " + i + " at " + binarySearch( a, new Integer( i ) ) );
        }
	}
}
