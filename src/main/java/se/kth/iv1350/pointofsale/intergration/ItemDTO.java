package se.kth.iv1350.pointofsale.intergration;

/**
 * Created by Adam Rosell on 2018-05-31.
 */
public class ItemDTO {
    private int itemID = -1;
    private int itemQuantity = 1;
    private String itemName = "no name";
    private String itemDescription = "no desc";
    private double itemPricePerUnit = -1.0;
    private double itemTaxPercentage = 0.0;
    private double itemTotalPricePerUnit;


    /**
     * Creates an ItemDTO according to the param values
     * @param itemID identifies the item
     * @param itemName the name of the item
     * @param itemDescription a short description of the item
     * @param itemPricePerUnit the price for one unit without taxes.
     * @param itemTaxPercentage the tax amount for the item
     * @return A ItemDTO representing an item from the mock database.
     */
    public ItemDTO getItemDTO(int itemID, String itemName, String itemDescription, double itemPricePerUnit,double itemTaxPercentage) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPricePerUnit = itemPricePerUnit;
        this.itemTaxPercentage = itemTaxPercentage;
        this.itemTotalPricePerUnit = taxCalculation(itemPricePerUnit, itemTaxPercentage);
        return ItemDTO.this;
    }

    /**
     * Calculates the total price for 1 unit of an item
     * @param unitPrice the price for 1 unit without taxes
     * @param taxPercentage the tax value for the item
     * @return totalPrice for 1 unit of the item
     */
    private double taxCalculation(double unitPrice, double taxPercentage){
        return unitPrice*taxPercentage;
    }

    /**
     * Gets the quantity of an item.
     * @return itemQuantity the quantity of the current item
     */
    public int getQuantity(){
       return itemQuantity;
    }
    /**
     * Sets the quantity of an item.
     */
    public void setQuantity(int newQuantity){
        itemQuantity = newQuantity;
    }
    /**
     * Gets the name of an item.
     * @return itemName the name of the current item
     */
    public String getName(){
        return itemName;
    }
    /**
     * Gets the description of an item.
     * @return itemDescription the description of the current item
     */
    public String getDesc(){
        return itemDescription;
    }
    /**
     * Gets the price per unit of an item.
     * @return itemPricePerUnit the price per unit of the current item
     */
    public double getItemPricePerUnit(){
        return itemPricePerUnit;
    }
    /**
     * Gets the tax percentage of an item.
     * @return itemTaxPercantage the tax percentage of the current item
     */
    public double getItemTaxPercentage(){
        return itemTaxPercentage;
    }
    /**
     * Gets the total price of an item.
     * @return itemTotalPricePerUnit the total price per unit of the current item
     */
    public double getItemTotalPricePerUnit(){
        return itemTotalPricePerUnit;
    }
}