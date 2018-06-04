package se.kth.iv1350.pointofsale.view;

import se.kth.iv1350.pointofsale.controller.Controller;
import se.kth.iv1350.pointofsale.intergration.ItemDTO;

import java.util.Scanner;

/**
 *  Created by Adam Rosell on 2018-05-30.
 */

/**
 *  The View class is a simple commandline interface (CLI) handling the different actions of the program.
 */
public class View {
    private static Controller currentController;
    private static View currentView = null;

    private View(Controller controller){
        this.currentController = controller;
    }

    /**
     *  Construct a new View if there is no already existing View.
     * @param controller the Controller instance that the View will connect to.
     * @return currentView the singelton instance of View.
     */
    public static View getInstance(Controller controller){
        if (currentView == null){
            return currentView = new View(controller);
        }
        return currentView;
    }

    /**
     *  The actual CLI. Prints the menu and let the operator choose what to do.
     */
    public void menu(){
        System.out.println("\nWhat do you like to do?");
        System.out.println("Press the the number of the option you like");
        System.out.println("1. Start a new sale");
        System.out.println("2. Add a new item to the current sale");
        System.out.println("3. End the current sale and go to checkout");
        // print out menu options
        Scanner sc = new Scanner(System.in);
        int menuPick = sc.nextInt();
        switch (menuPick){
            case (1):
                currentController.startSale();
                System.out.println("New sale started!");
                menu();
                break;
            case (2):
                System.out.println("Please enter itemID:");
                ItemDTO boughtItemDTO = currentController.addItem(sc.nextInt());
                if(boughtItemDTO == null){menu();}
                System.out.println("name: " + boughtItemDTO.getName() + "\nItem description: " + boughtItemDTO.getDesc()
                        +"\nPrice including taxes: " + boughtItemDTO.getItemTotalPricePerUnit() +"\n");
                menu();
                break;
            case(3):
                currentController.endCurrentSale();
                menu();
                break;
            default:
                System.out.println(menuPick +" is not a number in the menu.\nTry again!!!");
                menu();




        }
    }
}
