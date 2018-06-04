package pointofsale.model;

import org.junit.jupiter.api.Test;
import pointofsale.view.TotalRevenueViewMock;
import se.kth.iv1350.pointofsale.intergration.DatabaseErrorException;
import se.kth.iv1350.pointofsale.intergration.InvalidItemIDException;
import se.kth.iv1350.pointofsale.intergration.ItemDTO;
import se.kth.iv1350.pointofsale.model.Sale;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Created by Adam Rosell on 2018-06-03.
 */
public class TestSale {
    /**
     * Tests that the item collected from the database
     * is stored in the HashMap correctly and
     * retrieved as the same object.
     */
    @Test
    public void TestAddItemCorrectItemFromDB() {
        ItemDTO expectedItem = new ItemDTO().getItemDTO(1, "Apple", "a green apple", 10, 1.30);
        TotalRevenueViewMock trv = new TotalRevenueViewMock();
        Sale testSale = new Sale(trv);
        try {
            ItemDTO testItem = testSale.addItem(1);
            assertEquals(expectedItem.getName(), testItem.getName());
            assertEquals(expectedItem.getDesc(), testItem.getDesc());
            assertEquals(expectedItem.getItemPricePerUnit(), testItem.getItemPricePerUnit());
            assertEquals(expectedItem.getItemTaxPercentage(), testItem.getItemTaxPercentage());

            Set<Map.Entry<Integer,ItemDTO>> testSetOfItems = testSale.getGoods();
            for(Map.Entry<Integer,ItemDTO> entry : testSetOfItems ) {
                assertEquals(expectedItem.getName(), entry.getValue().getName());
                assertEquals(expectedItem.getDesc(), entry.getValue().getDesc());
                assertEquals(expectedItem.getItemPricePerUnit(), entry.getValue().getItemPricePerUnit());
                assertEquals(expectedItem.getItemTaxPercentage(), entry.getValue().getItemTaxPercentage());
            }
        } catch (InvalidItemIDException e) {
            fail("Invalid input, does not exists in the database\nError code: " + e);
        } catch (DatabaseErrorException e) {
            fail("The database had an unexpected error\nError code: " + e);
        }
    }

    /**
     *  Tests that the Database exception is catch correctly
     */
    @Test
    public void TestAddItemExceptionDatabaseError(){
        TotalRevenueViewMock trv = new TotalRevenueViewMock();
        Sale testSale = new Sale(trv);
        try {
            testSale.addItem(4);
            fail("Expected 'DatabaseErrorException' was not thrown");
        } catch (InvalidItemIDException e) {
            fail("Wrong Exception was thrown.\nInvalid ItemID");
        } catch (DatabaseErrorException e) {
            assertTrue(true, "Correct Exception was thrown");
        }
    }
    /**
     *  Tests that the InvalidItemID exception is catch correctly
     */
    @Test
    public void TestGetItemInformationExceptionInvalidItemIDError(){
        TotalRevenueViewMock trv = new TotalRevenueViewMock();
        Sale testSale = new Sale(trv);
        try {
            testSale.addItem(5);
            fail("Expected 'InvalidItemIDException' was not thrown");
        } catch (InvalidItemIDException e) {
            assertTrue(true, "Correct Exception was thrown");
        } catch (DatabaseErrorException e) {
            fail("Wrong Exception was thrown.\nDatabase error");
        }
    }

    /**
     * Tests that the getGoods method returns the correct items from the HashMap.
     */
    @Test
    public void TestGetGoodsCorrectItemCollected() {
        ItemDTO expectedItem = new ItemDTO().getItemDTO(1, "Apple", "a green apple", 10, 1.30);
        TotalRevenueViewMock trv = new TotalRevenueViewMock();
        Sale testSale = new Sale(trv);
        try {
            testSale.addItem(1);
            Set<Map.Entry<Integer, ItemDTO>> testSetOfItems = testSale.getGoods();
            for (Map.Entry<Integer, ItemDTO> entry : testSetOfItems) {
                assertEquals(expectedItem.getName(), entry.getValue().getName());
                assertEquals(expectedItem.getDesc(), entry.getValue().getDesc());
                assertEquals(expectedItem.getItemPricePerUnit(), entry.getValue().getItemPricePerUnit());
                assertEquals(expectedItem.getItemTaxPercentage(), entry.getValue().getItemTaxPercentage());
            }
        } catch (InvalidItemIDException e) {
            fail("Invalid input, does not exists in the database");
        } catch (DatabaseErrorException e) {
            fail("The database had an unexpected error");
        }
    }

    /**
     * Tests that getRunningTotal calculates the correct total cost.
     */
    @Test
    public void TestGetRunningTotal() {
        double expectedRunningTotal = 13.0;
        TotalRevenueViewMock trv = new TotalRevenueViewMock();
        Sale testSale = new Sale(trv);
        try {
            testSale.addItem(1);
            double testRunningTotal = testSale.getRunningTotal();
            assertEquals(expectedRunningTotal, testRunningTotal);
        } catch (InvalidItemIDException e) {
            fail("Invalid input, does not exists in the database");
        } catch (DatabaseErrorException e) {
            fail("The database had an unexpected error");
        }
    }

    /**
     * Tests that the GetTs returns the saved timestamp.
     */
    @Test
    public void TestGetTs(){
        TotalRevenueViewMock trv = new TotalRevenueViewMock();
        Sale testSale = new Sale(trv);
        assertTrue(testSale.getTs() instanceof String);
    }
}
