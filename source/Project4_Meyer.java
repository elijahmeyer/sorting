/*
 * Project 4 for CS 1181.
 * This program enables the user to experiment with various sorting algorithms in the NetBeans IDE.
 * This program will prompt the user for input using a GUI, verify that the input is
 * valid, and spawn threads to implement sorting algorithms based on that input. 
 * Then, it will merge all threads into one array, print the array to the console, and display an 
 * Alert informing the user how long sorting the array took.
 */
package project4_meyer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Elijah Meyer
 * CS1181L-C07
 * Dr. Cheatham
 * Project 4
 */
public class Project4_Meyer extends Application {

    static int[] baseArray;
    static int arraySize;
    static int blockSize;
    static int sortIndex;
    static int inputIndex;
    
    /**
     * Launches the Graphical User Interface for this program.
     * Precondition: none
     * Postcondition: the user will have specified the details of the array they want to sort and what
     * algorithm to sort it with, the array will have been sorted accordingly, the array will have been
     * printed to the console, and an Alert informing the user how much time the sorting took will have
     * been displayed
     * @param args - the command line arguments
     */
    public static void main(String[] args) 
    {
        Application.launch(args);
    }
    
    /**
     * Creates an array of the specified size that is sorted in descending order.
     * Precondition: none
     * Postcondition: an array with the length submitted as a parameter that is sorted in 
     * descending order will have been returned
     * @param size - the desired length of the array
     * @return the array
     */
    public static int[] makeOppositeArray(int size) 
    {
        // Initialize array
        int [] arr = new int[size];
        
        // Fill array with integers in descending order and return
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = arr.length - i;
        }
        
        return arr;
    }
    
    /**
     * Creates an array of the specified size that is filled with random integer values from 0 to 99.
     * Precondition: none
     * Postcondition: an array with the length submitted as a parameter that is filled with
     * random integer values will have been returned
     * @param size - the desired length of the array
     * @return the array
     */
    public static int[] makeRandomArray(int size) 
    {
        // Initialize array
        int[] arr = new int[size];
        
        // Fill array with random integer values from 0 to 99 and return
        for (int i = 0; i < arr.length; i++) 
        {
            arr[i] = (int)(Math.random() * 100);
        }
        
        return arr;
    }
    
    /**
     * Creates an array of the specified size that is sorted in ascending order.
     * Precondition: none
     * Postcondition: an array with the length submitted as a parameter that is sorted in 
     * ascending order will have been returned
     * @param size - the desired length of the array
     * @return the array
     */
    public static int[] makeOrderedArray(int size) 
    {
        // Initialize array
        int[] arr = new int[size];
        
        // Fill array with integers in ascending order and return
        for (int i = 0; i < arr.length; i++) 
        {
            arr[i] = i;
        }
        
        return arr;
    }
    
    /**
     * Creates a Graphical User Interface to prompt for input, verify that it is valid, and sort the array
     * in the manner specified.
     * Precondition: method is in the scope of global variables baseArray, arraySize, blockSize, sortIndex,
     * and inputIndex
     * Postcondition: the user will have specified the size of array to be sorted, the size of the blocks to
     * break the array into, the sorting algorithm to use, and how the array should be sorted to begin with,
     * an array matching all of these characteristics will have been sorted and printed to the console, and
     * an Alert informing the user how long the sorting took will be displayed
     * @param primaryStage - the Stage to be shown
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // Initialize main pane
        VBox vb = new VBox(15);
        vb.setAlignment(Pos.CENTER);
        
        // Initialize pane for first sub-group
        VBox vb1 = new VBox(5);
        
        // Create and add label for sorting algorithms
        Label l1 = new Label("Sorting Algorithm");
        vb1.getChildren().add(l1);
        
        // Create group for algorithm buttons
        ToggleGroup tg = new ToggleGroup();
        
        // Create, activate, and add SelectionSort button to appropriate groups
        RadioButton rb1 = new RadioButton("Selection");
        rb1.setOnAction(e -> 
        {
            sortIndex = 1;
        });
        rb1.setToggleGroup(tg);
        vb1.getChildren().add(rb1);
        
        // Create, activate, and add BubbleSort button to appropriate groups
        RadioButton rb2 = new RadioButton("Bubble");
        rb2.setOnAction(e -> 
        {
            sortIndex = 2;
        });
        rb2.setToggleGroup(tg);
        vb1.getChildren().add(rb2);
        
        // Create, activate, and add InsertionSort button to appropriate groups
        RadioButton rb3 = new RadioButton("Insertion");
        rb3.setOnAction(e -> 
        {
            sortIndex = 3;
        });
        rb3.setToggleGroup(tg);
        vb1.getChildren().add(rb3);
        
        // Create, activate, and add QuickSort button to appropriate groups
        RadioButton rb4 = new RadioButton("Quick");
        rb4.setOnAction(e -> 
        {
            sortIndex = 4; 
        });
        rb4.setToggleGroup(tg);
        vb1.getChildren().add(rb4);
        
        // Add pane for first sub-group to main pane
        vb.getChildren().add(vb1);
        
        // Initialize pane for second sub-group
        VBox vb2 = new VBox(5);
        
        // Create and add label for input type
        Label l2 = new Label("Input Type");
        vb2.getChildren().add(l2);
        
        // Create group for input type buttons
        ToggleGroup tg2 = new ToggleGroup();
        
        // Create, activate, and add sorted array button to appropriate groups
        RadioButton rb5 = new RadioButton("Already sorted");
        rb5.setOnAction(e -> 
        {
            inputIndex = 1;
        });
        rb5.setToggleGroup(tg2);
        vb2.getChildren().add(rb5);
        
        // Create, activate, and add reverse-order array button to appropriate groups
        RadioButton rb6 = new RadioButton("Reverse order");
        rb6.setOnAction(e -> 
        {
            inputIndex = 2;
        });
        rb6.setToggleGroup(tg2);
        vb2.getChildren().add(rb6);
        
        // Create, activate, and add random array button to appropriate groups
        RadioButton rb7 = new RadioButton("Random");
        rb7.setOnAction(e -> 
        {
            inputIndex = 3;
        });
        rb7.setToggleGroup(tg2);
        vb2.getChildren().add(rb7);
        
        // Add pane for second sub-group to main pane
        vb.getChildren().add(vb2);
        
        // Initialize and adjust pane for third sub-group
        GridPane gp = new GridPane();
        gp.setHgap(5);
        gp.setVgap(5);
        
        // Create and add label for input size
        Label l3 = new Label("Input Size");
        gp.add(l3, 0, 0);
        
        // Create and add text field for input size
        TextField iSize = new TextField();
        gp.add(iSize, 1, 0);
        
        // Create and add label for block size
        Label l4 = new Label("Block Size");
        gp.add(l4, 0, 1);
        
        // Create and add text field for block size
        TextField bSize = new TextField();
        gp.add(bSize, 1, 1);
        
        // Add pane for third sub-group to main pane
        vb.getChildren().add(gp);
        
        // Create, activate, and add go button to appropriate group
        Button b1 = new Button("Go");
        b1.setMaxWidth(300);
        b1.setOnAction(e -> 
        {
            try 
            {
                // Obtain user input from text fields
                arraySize = Integer.parseInt(iSize.getText());
                blockSize = Integer.parseInt(bSize.getText());
            } catch (NumberFormatException ex) 
            {
                // If text field was empty or had non-integer in it, display Alert informing user
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("A text field contained inappropriate input");
                alert.setContentText("Please enter integers in the text fields");
                alert.showAndWait();
                return;
            }
            
            // If arraySize or blockSize is not greater than 0, display Alert informing user
            if (arraySize <= 0 || blockSize <= 0) 
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Array sizes must be greater than 0");
                alert.setContentText("Please enter positive numbers in the text fields");
                alert.showAndWait();
                return;
            }
            
            // If blockSize is greater than arraySize, display Alert informing user
            if (blockSize > arraySize) 
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Block size is greater than input size");
                alert.setContentText("Please adjust your inputs");
                alert.showAndWait();
                return;
            }
            
            // If user didn't select sorting algorithm or input type, display Alert informing user
            if (inputIndex == 0 || sortIndex == 0) 
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Required input is missing");
                alert.setContentText("Please select a sorting algorithm and an input type");
                alert.showAndWait();
                return;
            }
            
            // Create array based on size and ordering specified by user
            switch (inputIndex) 
            {
                case 1: 
                {
                    baseArray = makeOrderedArray(arraySize);
                } break;
                
                case 2: 
                {
                    baseArray = makeOppositeArray(arraySize);
                } break;
                
                case 3: 
                {
                    baseArray = makeRandomArray(arraySize);
                } break;
            }
            
            // Determine number of threads to allocate
            int threadNumber = baseArray.length / blockSize;
            
            // Initialize queue of threads
            Queue<BaseThread> threadQueue = new LinkedList();
            
            // Record starting time
            long before = System.currentTimeMillis();
        
            // Assign blocks of array to SortingThreads and put in queue
            for (int i = 0; i < threadNumber; i++) 
            {
                int[] temp = Arrays.copyOfRange(baseArray, blockSize * i, blockSize * (i + 1));
                SortingThread thread = new SortingThread(temp, sortIndex);
                thread.start();
                threadQueue.offer(thread);
            }
        
            // If block size does not divide evenly into array size, assign remainder to thread and put in queue
            if (baseArray.length % blockSize != 0) 
            {
                int[] remainder = Arrays.copyOfRange(baseArray, blockSize * threadNumber, baseArray.length);
                SortingThread thread = new SortingThread(remainder, sortIndex);
                thread.start();
                threadQueue.offer(thread);
                threadNumber++;
            }
        
            // Poll each thread in queue, wait until it completes, and put it back in queue
            for (int j = 0; j < threadNumber; j++) 
            {
                BaseThread temp = threadQueue.poll();
                try 
                {
                temp.join();
                } catch (Exception ex) 
                {
                    System.out.println(ex.getMessage());
                }
                
                threadQueue.offer(temp);
            }
            
            // While there is more than one thread in queue...
            while (threadQueue.size() > 1) 
            {
                BaseThread temp1 = threadQueue.poll();
                BaseThread temp2 = threadQueue.poll();
                try {
                    temp1.join();
                    temp2.join();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                MergingThread thread = new MergingThread(temp1.getArray(), temp2.getArray());
                thread.start();
                threadQueue.offer(thread);
            }
            
            BaseThread last = threadQueue.poll();
            try {
                last.join();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            // Record ending time
            long after = System.currentTimeMillis();
            
            // Print sorted array to console
            System.out.println(Arrays.toString(last.getArray()));
            
            // Display Alert informing user about how long sorting took
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Threaded Sorting");
            alert.setHeaderText("Finished");
            alert.setContentText("Sort completed in " + (after - before) + " milliseconds");
            alert.showAndWait();
        });
        vb.getChildren().add(b1);
        
        // Initialize AnchorPane and set anchor
        AnchorPane ap = new AnchorPane();
        AnchorPane.setLeftAnchor(vb, 10.0);
        
        // Add main pane to AnchorPane
        ap.getChildren().add(vb);
        
        // Pass AnchorPane to Scene
        Scene scene = new Scene(ap, 227, 330);
        
        // Pass Scene to Stage, change title, and show
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sorting Algorithms");
        primaryStage.show();
    }
}