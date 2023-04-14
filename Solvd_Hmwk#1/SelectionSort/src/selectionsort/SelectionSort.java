/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectionsort;

//import java.util.Random;
//import java.util.Scanner;

/**
 * Find the lowest value in the array then
 * swap it with the first element then
 * the next lowest is swapped with second
 * element. Repeat until fully sorted.
 */
public class SelectionSort {

    static void sort(int array[]){
        //get length of array
        int arrlen = array.length;
        
        //loop through each index to find the approriate element for that index.
        //when one iteration is finished, move to the next index.
        //skip the last index as it will be the highest value.
        for (int i = 0; i < arrlen-1; i++){
            //element at the index is currently considered the smallest value
            int index_min = i;
            //check all elements after current element
            for (int j = i+1; j < arrlen; j++){
                if (array[j] < array[index_min])
                    index_min = j; //newest smallest element
            }
            
            //swap minimum element with that at the current index
            //assuming the current element is not the smallest among those left.
            //store in temporary variable.
            int temp_index = array[index_min];
            array[index_min] = array[i];
            array[i] = temp_index;
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
        
        sort(array);
        
        long END = System.nanoTime();
        System.out.println("Time taken : " + ((END - START) / 1000_000f) + " seconds");
        System.out.println();
        
        for(int i = 0; i < len; i++)
                System.out.print(array[i] + " ");
        System.out.println();
    }
    
}
