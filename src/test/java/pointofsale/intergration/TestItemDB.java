package pointofsale.intergration;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointofsale.intergration.DatabaseErrorException;
import se.kth.iv1350.pointofsale.intergration.InvalidItemIDException;
import se.kth.iv1350.pointofsale.intergration.ItemDB;
import se.kth.iv1350.pointofsale.intergration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;


/**
 *   Created by Adam Rosell on 2018-06-02.
 */
public class TestItemDB {
    /**
     * Tests that the current instance is the instance sent back
     */
    @Test
    public void testGetInstance() {
        assertTrue(ItemDB.getInstance() instanceof ItemDB);
    }

    /**
     * Tests that the return object from the database is correct.
     */
    @Test
    public void testGetItemInformationApple() {
        ItemDTO expectedItem = new ItemDTO().getItemDTO(1,"Apple","a green apple",10,1.30);
            try {
                ItemDTO testItem = ItemDB.getInstance().getItemInformation(1);
                assertEquals(expectedItem.getName(), testItem.getName());
                assertEquals(expectedItem.getDesc(), testItem.getDesc());
                assertEquals(expectedItem.getItemPricePerUnit(), testItem.getItemPricePerUnit());
                assertEquals(expectedItem.getItemTaxPercentage(), testItem.getItemTaxPercentage());
            } catch (InvalidItemIDException e) {
                fail("Invalid input, does not exists in the database");
            } catch (DatabaseErrorException e) {
                fail("The database had an unexpected error");
            }
    }

    /**
     * Tests that the correct item is sent back from the database
     */
    @Test
    public void testGetItemInformationOrangeIsNotApple(){
        ItemDTO expectedItem = new ItemDTO().getItemDTO(1,"Apple","a green apple",10,1.30);
        try {
            ItemDTO testItem = ItemDB.getInstance().getItemInformation(3);
            assertNotEquals(expectedItem.getName(), testItem.getName());
            assertNotEquals(expectedItem.getDesc(), testItem.getDesc());
            assertNotEquals(expectedItem.getItemPricePerUnit(), testItem.getItemPricePerUnit());
            assertNotEquals(expectedItem.getItemTaxPercentage(), testItem.getItemTaxPercentage());
        } catch (InvalidItemIDException e) {
            fail("Invalid input, does not exists in the database");
        } catch (DatabaseErrorException e) {
            fail("The database had an unexpected error");
        }
    }

    /**
     * Checks that the correct error type is thrown: DatabaseErrorException
     */
    @Test
    public void testGetItemInformationExceptionDatabaseError(){
        try {
            ItemDB.getInstance().getItemInformation(4);
            fail("Expected 'DatabaseErrorException' was not thrown");
        } catch (InvalidItemIDException e) {
            fail("Wrong Exception was thrown.\nInvalid ItemID");
        } catch (DatabaseErrorException e) {
            assertTrue(true, "Correct Exception was thrown");
        }
    }

    /**
     * Checks that the correct error type is thrown: InvalidItemIDException
     */
    @Test
    public void testGetItemInformationExceptionInvalidItemIDError(){
        try {
            ItemDB.getInstance().getItemInformation(5);
            fail("Expected 'InvalidItemIDException' was not thrown");
        } catch (InvalidItemIDException e) {
            assertTrue(true, "Correct Exception was thrown");
        } catch (DatabaseErrorException e) {
            fail("Wrong Exception was thrown.\nDatabase error");
        }
    }
}
