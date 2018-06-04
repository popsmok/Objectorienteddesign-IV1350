package se.kth.iv1350.pointofsale.intergration;

/**
 *  Created by Adam Rosell on 2018-05-30.
 */

/**
 * The fake Database class. Used as an database.
 */
public class ItemDB {
    private static ItemDB itemDB = new ItemDB();

    private ItemDB (){
    }

    /**
     * Establish a connection with the database
     * @return itemDB the singleton instance of the database
     */
    public static ItemDB getInstance(){
        return itemDB;
    }

    /**
     *
     * @param itemID The identifier for an item.
     * @return the whole Item found in the database.
     * @throws InvalidItemIDException If the itemID doesnt exist in the database
     * @throws DatabaseErrorException If the database "crashes".
     */
    public ItemDTO getItemInformation(int itemID) throws InvalidItemIDException, DatabaseErrorException {
        String name;
        String desc;
        double price;
        double tax;

        switch (itemID) {
            case 1:
                name = "Apple";
                desc = "a green apple";
                price = 10;
                tax = 1.30;
                break;
            case 2:
                name = "Pear";
                desc = "a fresh pear";
                price = 5;
                tax = 1.10;
                break;
            case 3:
                itemID = 3;
                name = "Orange";
                desc = "perfect for juice";
                price = 20;
                tax = 1.50;
                break;
            case 4:
                throw new DatabaseErrorException("unexpected database error");
            default:
                throw new InvalidItemIDException("Invalid itemID");
        }
        return new ItemDTO().getItemDTO(itemID, name, desc, price, tax);
    }
}
