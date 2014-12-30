package first;

import static org.junit.Assert.*;

import org.junit.Test;

public class ObtuseTest {

	@Test
	public void testIsEven() {
		Obtuse obtuse = new Obtuse();
		assertTrue(obtuse.IsEven(2));
	}
}
