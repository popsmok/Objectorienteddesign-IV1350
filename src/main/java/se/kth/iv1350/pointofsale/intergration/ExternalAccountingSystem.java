package se.kth.iv1350.pointofsale.intergration;

/**
 * Created by Adam Rosell on 2018-05-30.
 */

/**
 * The mock class for an accounting System.
 * does not do anything.
 */
public class ExternalAccountingSystem {
    private static ExternalAccountingSystem eas = new ExternalAccountingSystem();

    private ExternalAccountingSystem(){
    }

    public static ExternalAccountingSystem getInstance(){
        return eas;
    }

    public boolean processPayment(){
        return true;
    }


}
