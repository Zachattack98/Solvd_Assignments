package randomnumberarray;

import java.util.Random;

/**
 * Generate many number for a single array
 */
public class RandomNumberArray {

    public static void main(String[] args) {
        Random rand = new Random();
        
        int num_elements = 15;
        int[] array = new int[num_elements];
        
        //create array of random numbers
        System.out.print("Randomly generated array:");
        for(int i = 0; i < num_elements; i++) {
            //generate random integers
            //each is a value between 0 and 100
            array[i] = rand.nextInt(101);
            
            System.out.print(" " + array[i]);
        }
        System.out.println();
    }
    
}
