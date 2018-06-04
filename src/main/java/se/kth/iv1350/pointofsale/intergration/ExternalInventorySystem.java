package se.kth.iv1350.pointofsale.intergration;

/**
 * Created by Adam Rosell on 2018-05-30.
 */
/**
 * The mock class for an Inventory System.
 * does not do anything.
 */
public class ExternalInventorySystem {
    private static ExternalInventorySystem eis = new ExternalInventorySystem();

    private ExternalInventorySystem () {
    }

    public static ExternalInventorySystem getInstance(){
        return eis;
    }

    public boolean processSale(){
        return true;
    }
}
