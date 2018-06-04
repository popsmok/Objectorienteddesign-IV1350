package se.kth.iv1350.pointofsale.intergration;

        import se.kth.iv1350.pointofsale.model.Sale;

        import java.util.Map;
        import java.util.Set;

/**
 * Created by Adam Rosell on 2018-06-01.
 */
public class Printer {
    private static Printer printer = new Printer ();

    private Printer(){
    }

    /**
     * get the active instance of the printer
     * @return printer the instance of the current printer.
     */
    public static Printer getInstance(){
        return printer;
    }

    /**
     * Prints out the receipt
     * @param saleDTO The Sale represented int the receipt
     */
    public void printReceipt(Sale saleDTO){
        System.out.println(" Thank you for shopping at PoS ");
        System.out.println(" The purchase was done: " + saleDTO.getTs());
        System.out.println(" You bought:");
        System.out.println();
        System.out.println("Quantity    Name    Price each  Tax value  Total price each");
        Set<Map.Entry<Integer,ItemDTO>> setOfItems = saleDTO.getGoods();
        for(Map.Entry<Integer,ItemDTO> entry : setOfItems ) {
            printGoods(entry);
        }
        System.out.println("Total cost for this purchase: "+saleDTO.getRunningTotal()+"kr\n");
    }

    /**
     * Prints out 1 line with item information on the receipt
     * @param entry the item which information should be printed
     */
    private void printGoods(Map.Entry<Integer,ItemDTO> entry){
        System.out.print(entry.getValue().getQuantity()+"    ");
        System.out.print(entry.getValue().getName()+"   ");
        System.out.print(entry.getValue().getItemPricePerUnit()+"kr  ");
        System.out.print((entry.getValue().getItemTaxPercentage()) +"  ");
        System.out.print(entry.getValue().getItemTotalPricePerUnit()+"kr");
        System.out.println();
    }

}

