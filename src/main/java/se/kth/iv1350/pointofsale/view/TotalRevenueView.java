package se.kth.iv1350.pointofsale.view;

import se.kth.iv1350.pointofsale.model.Sale;

import java.util.Observable;
import java.util.Observer;

/**
 *  Created by Adam Rosell on 2018-06-02.
 */

/**
 * The Observer that tracks the total revenue on one point of sale.
 */
public class TotalRevenueView implements Observer{
    double totalRevenue;

    @Override
    /**
     *
     *  updates the total revenue from this point of sale and prints it of to the terminal.
     */
    public void update(Observable o, Object arg) {
            if(o.getClass() == Sale.class){
                Sale sale = (Sale) o;
                totalRevenue += sale.getRunningTotal();
                System.out.println("total revenue for this session: "+totalRevenue+"kr");
            }
    }
}

