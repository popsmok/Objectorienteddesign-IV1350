package se.kth.iv1350.pointofsale.controller;

import se.kth.iv1350.pointofsale.intergration.*;
import se.kth.iv1350.pointofsale.model.Sale;
import se.kth.iv1350.pointofsale.view.TotalRevenueView;

import java.util.Observable;

/**
 *  Created by Adam Rosell on 2018-05-30.
 */

/**
 * The controller class in between the view and the model in a classic MVC structure.
 */
public class Controller extends Observable{

    private Sale currentSale;
    private ExternalAccountingSystem currentEas;
    private ExternalInventorySystem currentEis;
    private ItemDB currentItemDB;
    private Printer currentPrinter;
    private TotalRevenueView trv;

    /**
     * The Controller needs the parameters to establish a connection to the external systems.
     * @param Eas reference to the External Accounting System in use.
     * @param Eis reference to the Inventroy Accounting System in use.
     * @param itemDB reference to the database containing all the items.
     * @param printer reference to the printer responsible for printing receipts.
     * @param trv reference to the TotalRevenueView observer.
     */
    public Controller(ExternalAccountingSystem Eas, ExternalInventorySystem Eis, ItemDB itemDB, Printer printer, TotalRevenueView trv){
        this.currentEas = Eas;
        this.currentEis = Eis;
        this.currentItemDB = itemDB;
        this.currentPrinter = printer;
        this.trv = trv;
    }

    /**
     *  Starts a new instance of Sale
     *  sends the TotalRevenueView trv observer into the Sale object.
     */
    public void startSale() {
        currentSale = new Sale(trv);
    }

    /**
     * Adds a new item into the ongoing Sale
     * @param itemID Used to identify the item collected from the "database"
     * @return addedItem the full item Object found in the "database", if an exception is caught return a null-object.
     */
    public ItemDTO addItem(int itemID) {
        ItemDTO addedItem = null;
        try {
            addedItem = currentSale.addItem(itemID);
            System.out.println("running total: " + currentSale.getRunningTotal());
        }
        catch (InvalidItemIDException i){
            System.out.println("The entered ItemID does not exist in the database: "+itemID);
            System.err.println("LOG: Error: an item does not exists in the database.\nLOG: error code: 420");
        }
        catch (DatabaseErrorException d){
            System.out.println("An unexpected database error has occurred\n");
            System.err.println("LOG: Error: The ItemDatabase did not respond.\nLOG: error code: 1337\n");
        }
            return addedItem;
    }

    /**
     * Ends the current sale.
     * Calls the external systems responsible for logging the Sale.
     */
    public void endCurrentSale() {
        currentSale.notifyView();
        currentSale.notifyObservers();
        currentEas.processPayment();
        currentEis.processSale();
        currentPrinter.printReceipt(currentSale);
    }
}