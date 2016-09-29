/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author xboxm
 */
public class ValidationErrorException extends Exception {

    /**
     * Creates a new instance of <code>ValidationErrorException</code> without
     * detail message.
     */
    public ValidationErrorException() {
    }

    /**
     * Constructs an instance of <code>ValidationErrorException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ValidationErrorException(String msg) {
        super(msg);
    }
}
