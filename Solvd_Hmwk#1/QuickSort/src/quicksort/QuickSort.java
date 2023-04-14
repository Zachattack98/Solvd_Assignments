package quicksort;

import java.util.Random;
import java.util.Scanner;  // Import the Scanner class

/**
 * Select element at highest index then place in correct position
 * in between lower and higher values then divide/partition array into
 * sub-arrays and repeat the sorting process again.
 */
public class QuickSort {

    //swap two elements in the given array.
    //static since same array is being used throughout the class.
    static void swap(int[] array, int i, int j) {
        int tempArray = array[i];
        array[i] = array[j];
        array[j] = tempArray;
    }
    
    //highest indexed element is pivot point.
    //smaller elements to left and higher elements to right of pivot.
    static int partition(int[] array, int highIndex) {
        //pivot element
        int pivot = array[highIndex];
        
        //index of smaller element.
        //starts as -1 since it will increment before swap.
        int i = -1;
        
        for(int j = 0; j < highIndex; j++) {
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
        swap(array, i+1, highIndex);
        return (i+1); //add 1 to counteract the -1 assigned to i.
    }
    
    static void qsort(int[] array, int low_index, int highIndex) {
        if(low_index < highIndex) {
            //partitioning index; now in correct spot
            int pi = partition(array, highIndex);
            
            //perform recursion; go through lower and higher
            //sub-arrays and repeat sorting process.
            qsort(array, 0, pi-1);
            qsort(array, pi+1, highIndex);
            
        }
    }
    
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);  // Create a Scanner object for input
        
        Random rand = new Random();
        
        System.out.println("Number of elements : ");
        int numElmts = obj.nextInt();
        System.out.println();
        
        int[] array = new int[numElmts];
        
        //create array of random numbers
        for(int i = 0; i < numElmts; i++) {
            array[i] = rand.nextInt(101);
        }
        //int[] array = {20, 57, 49, 100, 76, 79, 55, 22, 92, 34, 63, 80};
        int len = array.length;
        
        long START = System.nanoTime();    //Store starting time
        
        qsort(array, 0, len-1);
        
        long END = System.nanoTime();
        System.out.println("Time taken : " + ((END - START) / 1000_000f) + " seconds");
        System.out.println();
        
        for(int i = 0; i < len; i++) {
                System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    
}
