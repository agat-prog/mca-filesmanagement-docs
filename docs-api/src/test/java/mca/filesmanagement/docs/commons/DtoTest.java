package mca.filesmanagement.docs.commons;

import org.junit.jupiter.api.Test;

import net.sf.beanrunner.BeanRunner;

public class DtoTest {

	/** Getters y setters. */
	@Test
    public void testGettersAndSetters() throws Exception {
		BeanRunner beanRunner = new BeanRunner();
		beanRunner.addTestValue(DocumentDto.class, new DocumentDto());
		beanRunner.testBean(new DocumentDto());
		
		beanRunner = new BeanRunner();
		beanRunner.addTestValue(DocumentNewDto.class, new DocumentNewDto());
		beanRunner.testBean(new DocumentNewDto());
	}
}
