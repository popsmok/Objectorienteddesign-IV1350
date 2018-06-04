package se.kth.iv1350.pointofsale.intergration;

/**
 *  Created by Adam Rosell on 2018-06-02.
 */

/**
 * The Exception thrown when the database fails to load
 */
public class DatabaseErrorException extends Exception {
    public DatabaseErrorException(String message){
        super(message);
    }
}
