/*
 * Custom exception used when a combination report
 */

package REDO.csv;

public class DataMismatchException extends Exception{
    public DataMismatchException(String message) {
        super(message);
    }
}
