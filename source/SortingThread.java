/*
 * Project 4 for CS 1181.
 * This program defines the SortingThread class and all of its data fields and methods using the NetBeans IDE.
 * This program will implement the BaseThread class's abstract methods to sort the array 
 * submitted as a parameter using the algorithm indicated by the parameter.
 */
package project4_meyer;

import java.util.Arrays;

/**
 * @author Elijah Meyer
 * CS1181L-C07
 * Dr. Cheatham
 * Project 4
 */
public class SortingThread extends BaseThread
{
    private int[] arr;
    private int sortIndex;
    
    public SortingThread(int[] arr, int sortIndex)
    {
        this.arr = arr;
        this.sortIndex = sortIndex;
    }
    
    /**
     * Returns the calling SortingThread's array.
     * Precondition: none
     * Postcondition: the SortingThread's array will have been returned
     * @return the calling SortingThread's array
     */
    @Override
    public int[] getArray() 
    {
        return this.arr;
    }
        
    /**
     * Sorts the calling SortingThread's array using the insertion sort algorithm
     * Precondition: none
     * Postcondition: the calling SortingThread's array will have been sorted using the insertion sort 
     * algorithm
     */
    public void insertionSort()
    {
        // Start with second value in array
        for (int i = 1; i < arr.length; i++)
        {
            for (int j = i; j > 0; j--) 
            {
                // If any value in array is greater than previous value, swap the two values
                if (arr[j] < arr[j - 1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }
    
    /**
     * Sorts the calling SortingThread's array using the selection sort algorithm
     * Precondition: none
     * Postcondition: the calling SortingThread's array will have been sorted using the selection sort 
     * algorithm
     */
    public void selectionSort()
    {
        for (int i = 0; i < arr.length; i++) 
        {
            // Start with first value in array and work your way up
            int min = i;
            for (int j = i + 1; j < arr.length; j++)
            {
                // Find smallest value in rest of array
                if (arr[min] > arr[j]) 
                {
                    min = j;
                }
            }
            
            // Swap value in designated position with smallest value
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }
    
    /**
     * Sorts the calling SortingThread's array using the bubble sort algorithm
     * Precondition: none
     * Postcondition: the calling SortingThread's array will have been sorted using the bubble sort 
     * algorithm
     */
    public void bubbleSort() 
    {
        // Start with last value in array
        for (int i = arr.length - 1; i > 0; i--) 
        {
            // Keep track of swaps per pass
            int swapCount = 0;
            
            for (int j = 0; j < i; j++) 
            {
                // If any value in array is less than next value, swap the two values and record swap
                if (arr[j] > arr[j + 1]) 
                {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapCount++;
                }
            }
            
            // If no swaps were necessary this pass, array is sorted
            if (swapCount == 0)
            {
                return;
            }
        }
    }
   
    /**
     * Sorts the array submitted as a parameter using the quick sort algorithm
     * Precondition: none
     * Postcondition: the submitted array will have been sorted using the quick sort
     * @param array - the array to be sorted
     */
    public void quickSort(int[] array)
    {
        // If array is empty or has only one item, it is already sorted
        if (array.length == 0 || array.length == 1) 
        {
            return;
        }
        
        // Initialize variables
        int pivot = array.length - 1;
        int low = 0;
        int high = array.length - 2;
        boolean notDone = true;
        
        while (notDone) 
        {
            // Increment low pointer until value at low is greater than value at pivot or low is equal to high
            while (array[low] <= array[pivot] && low < high)
            {
                low++;
            }
            
            // Decrement high pointer until value at high is less than or equal to value at pivot or high is equal to low
            while (array[high] > array[pivot] && high > low) 
            {
                high--;
            }
            
            if (low == high) 
            {
                // If low equals high and the low condition is satisfied, swap pivot value with array value at low + 1
                // and change pivot pointer accordingly
                if (array[low] <= array[pivot]) 
                {
                    int temp = array[low + 1];
                    array[low + 1] = array[pivot];
                    array[pivot] = temp;
                    pivot = low + 1;
                    notDone = false;
                }
                
                // Otherwise, swap value at pivot with value at low and change pivot pointer accordingly
                else 
                {
                    int temp = array[low];
                    array[low] = array[pivot];
                    array[pivot] = temp;
                    pivot = low;
                    
                    // Exit loop
                    notDone = false;
                }
            }
            
            // If low is not equal to high, swap values at low and high
            else 
            {
                int temp = array[low];
                array[low] = array[high];
                array[high] = temp;
            }
        }
        
        // Initialize variable
        int[] lowArray;
        
        // Create array of length pivot - 1, or length 0 if pivot is 0
        if (pivot == 0) 
        {
            lowArray = new int[0];
        }
        
        else 
        {
            lowArray = new int[pivot - 1];
        }
        
        // Copy low half of submitted array into new array
        lowArray = Arrays.copyOfRange(array, 0, pivot);
        
        // Initialize variable
        int[] highArray;
        
        // Create array of the length of submitted array - pivot - 1, or length 0 if pivot is submitted array's
        // length - 1
        if (pivot == array.length - 1) 
        {
            highArray = new int[0];
        }
        
        else 
        {
            highArray = new int[array.length - pivot -1];
        }
        
        // Copy high half of submitted array into new array
        highArray = Arrays.copyOfRange(array, pivot + 1, array.length);
        
        // Recurse on both new arrays
        quickSort(lowArray);
        quickSort(highArray);
        
        // Copy newly sorted array halves into submitted array
        System.arraycopy(lowArray, 0, array, 0, lowArray.length);
        System.arraycopy(highArray, 0, array, lowArray.length + 1, highArray.length);
    }   
    
    /**
     * Sorts the array of the calling SortingThread with the algorithm specified by the index value
     * submitted as a parameter.
     * Precondition: none
     * Postcondition: the calling SortingThread's array will have been sorted using the algorithm 
     * indicated by the user
     */
    @Override
    public void run() {
        switch (this.sortIndex) 
        {
            case 1: 
            {
                this.selectionSort();
            } break;
            
            case 2: 
            {
                this.bubbleSort();
            } break;
            
            case 3: 
            {
                this.insertionSort();
            } break;
            
            case 4: 
            {
                this.quickSort(this.arr);
            }
        }
    }
}