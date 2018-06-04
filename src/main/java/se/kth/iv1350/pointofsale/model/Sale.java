package se.kth.iv1350.pointofsale.model;

import se.kth.iv1350.pointofsale.intergration.*;
import se.kth.iv1350.pointofsale.view.TotalRevenueView;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

/**
 *  Created by Adam Rosell on 2018-05-30.
 */

/**
 * The Sale object representing an ongoing sale
 */
public class Sale extends Observable{

    private double runningTotal = 0.0;
    private HashMap<Integer, ItemDTO> goods = new HashMap<Integer,ItemDTO>();
            // HashMap<Key,Value>
    private String ts = new Timestamp(System.currentTimeMillis()).toLocalDateTime().toString();

    /**
     * Starts a new Sale object with the total revenue observer included.
     * @param trv the total revenue observer
     */
    public Sale(TotalRevenueView trv){
        this.addObserver(trv);
    }

    /**
     * Collects an Item from the database and adds it to the Sale objects hashmap.
     * increasing the quantity if needed.
     * @param ItemID The identifier for an item
     * @return the whole Item found in the database.
     * @throws InvalidItemIDException If the itemID doesnt exist in the database
     * @throws DatabaseErrorException If the database "crashes".
     */
    public ItemDTO addItem(int ItemID) throws InvalidItemIDException,DatabaseErrorException{
            ItemDTO itemInformation = ItemDB.getInstance().getItemInformation(ItemID);
            if (goods.containsKey(ItemID)) {
                ItemDTO currentItem = goods.get(ItemID);
                currentItem.setQuantity(currentItem.getQuantity() + 1);
            }
            else {
                goods.put(ItemID, itemInformation);
            }
        return itemInformation;
    }

    /**
     * Collects a list of all items in the current sale
     * @return goods.entrySet all Items in the current sale
     */
    public Set getGoods(){
        return goods.entrySet();
    }

    /**
     * Calculates the running total of current Sale.
     * @return runningTotal the running total of the current Sale.
     */
    public double getRunningTotal(){
        runningTotal = 0.0;
        Set<Map.Entry<Integer,ItemDTO>> setOfItems = goods.entrySet();
        for (Map.Entry<Integer,ItemDTO> entry : setOfItems){
           runningTotal += entry.getValue().getQuantity() * entry.getValue().getItemTotalPricePerUnit();
        }
        return runningTotal;
    }

    /**
     * Collects the Timestamp of the Sale
     * @return ts the time the Sale was initialized
     */
    public String getTs(){
        return ts;
    }

    /**
     * notifies the observer to update.
     */
    public void notifyView(){
        this.setChanged();
    }
}
