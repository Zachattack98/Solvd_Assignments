/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectionvsquick;

import java.util.Random;

/**
 *
 * @author zachary
 */
public class SelectionvsQuick {

    static void swap(int[] array, int i, int j) {
        int tempArray = array[i];
        array[i] = array[j];
        array[j] = tempArray;
    }
    
    static void ssort(int array[]){
        //get length of array
        int arrlen = array.length;
        
        //loop through each index to find the approriate element for that index.
        //when one iteration is finished, move to the next index.
        //skip the last index as it will be the highest value.
        for (int i = 0; i < arrlen-1; i++){
            //element at the index is currently considered the smallest value
            int indexMin = i;
            //check all elements after current element
            for (int j = i+1; j < arrlen; j++){
                if (array[j] < array[indexMin]) {
                    indexMin = j; //newest smallest element
                }
            }
            
            //swap minimum element with that at the current index
            //assuming the current element is not the smallest among those left.
            //store in temporary variable.
            swap(array, indexMin, i);
        }
    }
    
    //highest indexed element is pivot point.
    //smaller elements to left and higher elements to right of pivot.
    private static int partition(int[] array, int lowIndex, int highIndex) {
        //pivot element
        int pivot = array[highIndex];
        
        //index of smaller element.
        //starts as -1 since it will increment before swap.
        int i = lowIndex - 1;
        
        for(int j = lowIndex; j < highIndex; j++) {
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
    
    public static void qsort(int[] array, int lowIndex, int highIndex) {
        if(lowIndex < highIndex) {
            //partitioning index; now in correct spot
            int pi = partition(array, lowIndex, highIndex);
            
            //perform recursion; go through lower and higher
            //sub-arrays and repeat sorting process.
            qsort(array, lowIndex, pi-1);
            qsort(array, pi+1, highIndex);
            
        }
    }
    
    public static void main(String[] args) {
        //Scanner obj = new Scanner(System.in);  // Create a Scanner object for input
        
        Random rand = new Random();
        
        //System.out.println("Number of elements : ");
        //int numElmts = obj.nextInt();
        //System.out.println();
        
        int[] array = new int[2000];
        
        //create array of random numbers
        for(int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(2001);
        }
        //int[] array = {20, 57, 49, 100, 76, 79, 55, 22, 92, 34, 63, 80};
        int len = array.length;
        
        long qSTART = System.nanoTime();    //Store starting time
        
        ssort(array);
        
        long qEND = System.nanoTime();
        System.out.println("Time taken (quick sort): " + ((qEND - qSTART) / 1000_000f) + " seconds");
        System.out.println();
        
        for(int i = 0; i < len; i++) {
                System.out.print(array[i] + " ");
        }
        System.out.println();
        System.out.println();
        
        long sSTART = System.nanoTime();    //Store starting time
        
        qsort(array, 0, len-1);
        
        long sEND = System.nanoTime();
        System.out.println("Time taken (selection sort): " + ((sEND - sSTART) / 1000_000f) + " seconds");
        System.out.println();
        
        for(int i = 0; i < len; i++) {
                System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    
}