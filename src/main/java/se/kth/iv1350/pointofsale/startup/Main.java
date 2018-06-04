package se.kth.iv1350.pointofsale.startup;

import se.kth.iv1350.pointofsale.controller.Controller;
import se.kth.iv1350.pointofsale.intergration.ExternalAccountingSystem;
import se.kth.iv1350.pointofsale.intergration.ExternalInventorySystem;
import se.kth.iv1350.pointofsale.intergration.ItemDB;
import se.kth.iv1350.pointofsale.intergration.Printer;
import se.kth.iv1350.pointofsale.view.TotalRevenueView;
import se.kth.iv1350.pointofsale.view.View;

/**
 *  Created by Adam Rosell on 2018-05-30.
 */

/**
 *  The Main method in the project.
 *  Start up the whole program.
 */
public class Main {
    private static Controller currentController;
    private static View currentView;
    private static ExternalAccountingSystem currentEas;
    private static ExternalInventorySystem currentEis;
    private static ItemDB currentItemDB;
    private static Printer currentPrinter;
    private static TotalRevenueView trv;

    /**
     * Initializes all external systems then the controller and the view.
     * @param args unused
     */
    public static void main (String[]args){

        Main.startupExternalSystems();
        trv = new TotalRevenueView();
        currentController = new Controller(currentEas,currentEis,currentItemDB,currentPrinter, trv);
        currentView = View.getInstance(currentController);
        currentView.menu();
    }

    /**
     * Starts up the external systems.
     */
    private static void startupExternalSystems(){
        currentEas = ExternalAccountingSystem.getInstance();
        currentEis = ExternalInventorySystem.getInstance();
        currentItemDB = ItemDB.getInstance();
        currentPrinter = Printer.getInstance();
    }
}
