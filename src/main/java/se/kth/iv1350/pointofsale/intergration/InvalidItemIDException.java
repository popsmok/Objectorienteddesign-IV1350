package se.kth.iv1350.pointofsale.intergration;

/**
 *  Created by Adam Rosell on 2018-06-02.
 */

/**
 *  The Exception thrown when the item is not found in the database
 */
public class InvalidItemIDException extends Exception{

    /**
     *
     * @param message
     */
    public InvalidItemIDException(String message){
        super(message);
    }

}
