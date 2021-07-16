package Activities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class Activity1 {	
	
	static ArrayList<String> list = new ArrayList<String>();
	
	@BeforeAll
	public static void setup() {
	   // Add values to the list
		list.add("alpha"); // at index 0
		list.add("beta"); // at index 1
	}
	
	//@Test 
	@Order(1)
	public void insertTest() {
		
		assertEquals(2,list.size());
		list.add("gamma");
		assertEquals(3,list.size());	
		
		assertEquals("alpha",list.get(0));
		assertEquals("beta",list.get(1));
		assertEquals("gamma",list.get(2));
		
				
	}
	
	@Test
	@Order(2)
	public void replaceTest() {
		assertEquals(2,list.size());
		list.set(1,"charlie");		
          	
        // Assert individual elements
	
        assertEquals("alpha", list.get(0), "Wrong element");
	
        assertEquals("charlie", list.get(1), "Wrong element");
	}
	
	

}
