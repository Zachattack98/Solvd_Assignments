package quicksort;

//import java.util.Random;
//import java.util.Scanner;  // Import the Scanner class

/**
 * Select element at highest index then place in correct position
 * in between lower and higher values then divide/partition array into
 * sub-arrays and repeat the sorting process again.
 */
public class QuickSort {

    //swap two elements in the given array.
    //static since same array is being used throughout the class.
    static void swap(int[] array, int i, int j) {
        int temp_array = array[i];
        array[i] = array[j];
        array[j] = temp_array;
    }
    
    //highest indexed element is pivot point.
    //smaller elements to left and higher elements to right of pivot.
    static int subdivide(int[] array, int high_index) {
        //pivot element
        int pivot = array[high_index];
        
        //index of smaller element.
        //starts as -1 since it will increment before swap.
        int i = -1;
        
        for(int j = 0; j < high_index; j++) {
            //if current element is smaller than pivot element.
            //incrementing i ensures current element stays to the left.
            if(array[j] < pivot){
                //increment index of smaller element.
                i++;
                swap(array,i,j);
            }
        }
        //now that the elements have been sorted according to the pivot
        //increment once more to get a value that is for sure higher
        //than pivot, and all those after it, and swap it with pivot.
        swap(array, i+1, high_index);
        return (i+1); //add 1 to counteract the -1 assigned to i.
    }
    
    static void Qsort(int[] array, int low_index, int high_index) {
        if(low_index < high_index) {
            //partitioning index; now in correct spot
            int pi = subdivide(array, high_index);
            
            //perform recursion; go through lower and higher
            //sub-arrays and repeat sorting process.
            Qsort(array, 0, pi-1);
            Qsort(array, pi+1, high_index);
            
        }
    }
    
    public static void main(String[] args) {
        /*Scanner obj = new Scanner(System.in);  // Create a Scanner object for input
        
        Random rand = new Random();
        
        System.out.println("Number of elements : ");
        int num_elements = obj.nextInt();
        System.out.println();
        
        int[] array = new int[num_elements];
        
        //create array of random numbers
        for(int i = 0; i < num_elements; i++)
            array[i] = rand.nextInt(101);*/
        int[] array = {20, 57, 49, 100, 76, 79, 55, 22, 92, 34, 63, 80};
        int len = array.length;
        
        long START = System.nanoTime();    //Store starting time
        
        Qsort(array, 0, len-1);
        
        long END = System.nanoTime();
        System.out.println("Time taken : " + ((END - START) / 1000_000f) + " seconds");
        System.out.println();
        
        for(int i = 0; i < len; i++)
                System.out.print(array[i] + " ");
        System.out.println();
    }
    
}
