package mca.filesmanagement.docs.infraestructure.model;

import org.junit.jupiter.api.Test;

import net.sf.beanrunner.BeanRunner;

public class ModelTest {

	/** Getters y setters. */
	@Test
    public void testGettersAndSetters() throws Exception {
		BeanRunner beanRunner = new BeanRunner();
		beanRunner.addTestValue(DocumentEntity.class, new DocumentEntity());
		beanRunner.testBean(new DocumentEntity());
	}
}
