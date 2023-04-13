/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectionsort;

/**
 * Find the lowest value in the array then
 * swap it with the first element then
 * the next lowest is swapped with second
 * element. Repeat until fully sorted.
 */
public class SelectionSort {

    void sort(int array[]){
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
        SelectionSort obj = new SelectionSort(); //create an object
        int arr[] = {40, 20, 30, 50, 10};
        obj.sort(arr); //call sort function from class
        //print each element of the now sorted array
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
}
