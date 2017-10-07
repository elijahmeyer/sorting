/*
 * Project 4 for CS 1181.
 * This program defines the BaseThread class and its methods using the NetBeans IDE.
 * This program will serve as a base for the SortingThread and MergingThread classes, allowing
 * instances of them to be stored in and polled from the same Queue without a need for type casting
 * and manipulated with the same method calls, allowing threads of both kinds to be merged in a loop
 * without the need for type checking.
 */
package project4_meyer;

/**
 * @author Elijah Meyer
 * CS1181L-C07
 * Dr. Cheatham
 * Project 4
 */
public abstract class BaseThread extends Thread 
{
    public BaseThread() 
    {
    }
    
    @Override
    public abstract void run();
    
    public abstract int[] getArray();
}