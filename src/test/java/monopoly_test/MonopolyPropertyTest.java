package monopoly_test;

import monopoly.gameplay.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonopolyPropertyTest {
	    private MonopolyProperty property;

	    @BeforeEach
	    public void setUp() {
	        property = new MonopolyProperty(200, 50, 2, "Park Place", 100);
	    }

	    @Test
	    public void testCostOfRent() {
	        assertEquals(200, property.costOfRent());
	    }

	    @Test
	    public void testCostOfHouse() {
	        assertEquals(50, property.costOfHouse());
	    }

	    @Test
	    public void testNumOfHouses() {
	        assertEquals(2, property.numOfHouses());
	    }

	    @Test
	    public void testName() {
	        assertEquals("Park Place", property.name());
	    }

	    @Test
	    public void testBuybackRate() {
	        assertEquals(100, property.buybackRate());
	    }

	    @Test
	    public void testEquals() {
	        MonopolyProperty anotherProperty = new MonopolyProperty(200, 50, 2, "Park Place", 100);
	        assertEquals(property, anotherProperty);
	    }

	    @Test
	    public void testHashCode() {
	        MonopolyProperty anotherProperty = new MonopolyProperty(200, 50, 2, "Park Place", 100);
	        assertEquals(property.hashCode(), anotherProperty.hashCode());
	    }

	    @Test
	    public void testToString() {
	        String expected = "MonopolyProperty[costOfRent=200, costOfHouse=50, numOfHouses=2, name=Park Place, buybackRate=100]";
	        assertEquals(expected, property.toString());
	    }
	}
