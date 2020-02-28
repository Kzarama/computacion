package co.icesi.edo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class AppTest {
    
    @Test
    public final void addEmptyString () {
    	StringCalculator.add("");
    	assertTrue(true);
    }
    
    @Test
    public final void addOneString () {
    	assertEquals(1, StringCalculator.add("//;\n1"));
    }
    
    @Test
    public final void addTwoString () {
    	assertEquals(3, StringCalculator.add("//;\n1;2"));
    }
    
    @Test
    public final void addMoreThanTwoInt () {
    	assertEquals(6, StringCalculator.add("//;\n1;2;3"));
    }
    
    @Disabled
    @Test
    public final void addMoreThanTwoIntDeprecated () {
    	assertThrows(RuntimeException.class, () -> {
    		StringCalculator.add("//;\n1\n2\n3");
    	});
    }
    
    @Test
    public final void addNotInt () {
    	assertThrows(RuntimeException.class, () -> {
    		StringCalculator.add("//;\n1\na");
    	});
    }
    
    @Test
    public final void negativeNumbersThrowsException1 () {
    	assertThrows(RuntimeException.class, () -> {
    		StringCalculator.add("//;\n-1");
    	});
    }
    
    @Test
    public final void negativeNumbersThrowsException2 () {
    	assertThrows(RuntimeException.class, () -> {
    		StringCalculator.add("//;\n2;-1");
    	});
    }
    
    @Test
    public final void ignoreNumbersMoreBiggerThan1000 () {
    	assertEquals(10, "//;\n1;2;3;4;1500"); 
    }
    
    @Test
    public final void newDelimiters () {
    	assertEquals(6, StringCalculator.add("//[—]\\n1—2—3"));
    }
    
    
    
}
