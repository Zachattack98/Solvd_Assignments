package quicksort;

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
        int[] array = { 65, 41, 33, 20, 57, 49};
        int len = array.length;
        
        Qsort(array, 0, len-1);
        
        for(int i = 0; i < len; i++)
                System.out.print(array[i] + " ");
        
        System.out.println();
    }
    
}
