/*
 * Project 4 for CS 1181.
 * This program defines the MergingThread class and all of its data fields and methods using the NetBeans IDE.
 * This program will implement the BaseThread class's abstract methods to merge the two arrays submitted
 * as parameters into one sorted array.
 */
package project4_meyer;

/**
 * @author Elijah Meyer
 * CS1181L-C07
 * Dr. Cheatham
 * Project 4
 */
public class MergingThread extends BaseThread 
{
    private int[] arr1;
    private int[] arr2;
    private int[] arr3;
    
    public MergingThread(int[] arr1, int[] arr2) 
    {
        this.arr1 = arr1;
        this.arr2 = arr2;
    }
    
    /**
     * Merges the two arrays submitted as parameters into one sorted array and assigns that array
     * to the calling MergingThread's third data field.
     * Precondition: none
     * Postcondition: the third data field of the calling MergingThread will have been assigned a sorted
     * array containing every integer value contained in either array submitted as a parameter
     */
    @Override
    public void run() 
    {
        // Initialize variables
        arr3 = new int[arr1.length + arr2.length];
        int arr1Pointer = 0;
        int arr2Pointer = 0;
        
        for (int i = 0; i < arr3.length; i++) 
        {
            // While both arrays have elements that have not been added to third array...
            if (arr1Pointer < arr1.length && arr2Pointer < arr2.length)
            {
                
                // Assign smallest array value to next position in third array and increment appropriate pointer
                if (arr1[arr1Pointer] < arr2[arr2Pointer]) 
                {
                    arr3[i] = arr1[arr1Pointer];
                    arr1Pointer++;
                }
               
                else 
                {
                    arr3[i] = arr2[arr2Pointer];
                    arr2Pointer++;
                }
            }
            
            // If first array has added all elements to third array, add second array's next element and increment pointer
            else if (arr1Pointer == arr1.length) 
            {
                arr3[i] = arr2[arr2Pointer];
                arr2Pointer++;
            }
            
            // If second array has added all elements, add first array's next element and increment pointer
            else 
            {
                arr3[i] = arr1[arr1Pointer];
                arr1Pointer++;
            }
        }
    }
    
    /**
     * Returns the calling MergingThread's merged array.
     * Precondition: the calling MergingThread has been started and its run() method has completed
     * Postcondition: the calling MergingThread's merged array will have been returned
     * @return the MergingThread's merged array
     */
    @Override
    public int[] getArray() 
    {
        return this.arr3;
    }
}