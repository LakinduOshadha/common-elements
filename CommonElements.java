import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * CommonElements prints out all elements that appear in given two arrays.
 * The output is in sorted order.
 * algorithm runs in n * log m time.
 *
 * @author Lakindu Oshadha (lakinduoshadha98@gmail.com)
 */
public class CommonElements {

    /**
     * Takes the first element of given array ( arr )  as pivot and
     * arranges the elements less than pivot in left ( arr[0] - arr[pivot] ) and
     * the elements greater than pivot in right ( arr[pivot] - arr([high] ).
     *
     * @param arr unPartitioned array
     * @param low least index of arr
     * @param high highest index of arr
     * @return index of pivot
     */
    public static int partition(int[] arr, int low, int high){

        // Initializing local variables
        int pivot = arr[low];   // Taking the 1st element as the pivot
        int i = low;
        int temp;

        // arranging the elements less than pivot in left ( arr[0] - arr[pivot] ) and
        // the elements greater than pivot in right ( arr[pivot] - arr([high] ).
        for (int j = low + 1; j <= high; j++){
            if (arr[j] <= pivot){
                i++;
                // Swap elements
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap pivot and arr[i]
        arr[low] = arr[i];
        arr[i] = pivot;

        return i;
    }

    /**
     * Takes a random element of given array ( arr )  as pivot and
     * arranges the elements less than pivot in left ( arr[0] to arr[pivot] ) and
     * the elements greater than pivot in right ( arr[pivot] to arr([high] ).
     *
     * @param arr unPartitioned array
     * @param low least index of arr
     * @param high highest index of arr
     * @return index of pivot
     */
    public static int randomizedPartition (int[] arr, int low, int high) {

        // Creating a random int between low & high
        Random rdm = new Random();
        int random = rdm.nextInt(high - low + 1) + low;

        // Swap the first element with the random element
        int temp = arr[low];
        arr[low] = arr[random];
        arr[random] = temp;

        return partition(arr,low,high);
    }

    /**
     * Sorts the given array in Ascending order with randomized quick sort algorithm.
     *
     * @param arr unSorted array
     * @param low leasr index of arr
     * @param high highest index of arr
     */
    public static void randomizedQuickSort (int[] arr, int low, int high) {

        // Initializing local variables
        int q;

        // Calling randomizedQuickSort method recursively
        if(low < high){
            q = randomizedPartition(arr, low, high);
            randomizedQuickSort(arr, low,q - 1);
            randomizedQuickSort(arr, q+1, high);
        }

    }

    /**
     * Takes the array and the size of the array from the user.
     *
     * @param numEl Symbol of No. of elements in a array
     * @return inputArray
     * @throws IOException
     */
    public static int[] getInputArray(String numEl) throws IOException{
        // Giving a brief Introduction to the user
        System.out.print("\n" + numEl + " - " + "No. of integers in input Array." + "\nEnter" +
                " input arr size(" + numEl + "): ");

        // Getting input size from user
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        // Getting the arr from the user
        int[] arr = new int[n];
        int inputSize = 0;
        String input;
        do {
            System.out.print("Enter " + n + " integers, separated using space" +
                    " (" + numEl + "1 "+ numEl +"2 " + numEl + "3 ...): ");
            input = reader.readLine();
            inputSize = input.split(" ").length;
        } while (inputSize != n);

        String[] numbers = input.split(" ");
        for (int j = 0; j < n; j++) {
            arr[j] = Integer.parseInt(numbers[j]);
        }

        return arr;
    }

    /**
     * Checks whether the given element is available in the given array.
     *
     * @param arr Array
     * @param low minIndex of array
     * @param high maxIndex of array
     * @param x Value which is to be searched
     * @return ifAvailable == true , ifNotAvailable == false
     */
    static boolean isElAvailable(int[] arr, int low, int high, int x) {
        // Initializing local variables
        int middleIndex = low + (high - low) / 2;

        // Checking conditions & Calling isElAvailable recursively
        if(low <= high) {
            if (x == arr[middleIndex]) {
                return true;    // Returns true if x is available
            } else if (x < arr[middleIndex]) {
                return isElAvailable(arr, low, middleIndex - 1, x); // Calling isElAvailable recursively
            } else {
                return isElAvailable(arr, middleIndex + 1, high, x);    // Calling isElAvailable recursively
            }
        }
        return false;   // Returns false if x is not available

    }

    /**
     * main
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // Giving a brief Introduction to the user
        System.out.print("This program will find Elements that appear in both lists in given 2 Arrays" +
                " using Recursive Algorithm.\n");

        // Getting input arrays & cloning them
        int[] Arr1 = getInputArray("n");    // Getting the array 1 from the user
        int[] Arr2 = getInputArray("m");    // Getting the array 2 from the user
        int[] sortedArr1 = Arr1.clone();  // Cloning the unSorted Array 1
        int[] sortedArr2 = Arr2.clone();  // Cloning the unSorted Array 2

        // Sorting input 2 arrays with randomizedQuickSort
        randomizedQuickSort(sortedArr1,0,sortedArr1.length - 1);  // Calling the randomizedQuickSort with the clone
        randomizedQuickSort(sortedArr2,0,sortedArr2.length - 1);  // Calling the randomizedQuickSort with the clone

        System.out.print("\nElements that appear in both lists : ");

        // Checks the Elements that appear in both lists & prints them
        for(int i = 0; i < sortedArr1.length; i++) {
            if( isElAvailable(sortedArr2, 0,sortedArr2.length - 1, sortedArr1[i]) ){
                System.out.print(sortedArr1[i] + " ");
            }
        }
        System.out.println();
    }

}
