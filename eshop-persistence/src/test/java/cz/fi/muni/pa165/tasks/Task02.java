package cz.fi.muni.pa165.tasks;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Category;
import cz.fi.muni.pa165.entity.Product;
import java.util.List;

 
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class Task02 extends AbstractTestNGSpringContextTests {

	@PersistenceUnit
	private EntityManagerFactory emf;
        
        private Category electro;
        private Category kitchen;
        
        private Product flashlight;
        private Product kitchenRobot;
        private Product plate;
        
        @BeforeClass
        public void setUpClass() {
            electro = new Category();
            electro.setName("Electro");
            kitchen = new Category();
            kitchen.setName("Kitchen");
            
            flashlight = new Product();
            flashlight.setName("Flashlight");
            flashlight.addCategory(electro);
            kitchenRobot = new Product();
            kitchenRobot.setName("KitchenRobot");
            kitchenRobot.addCategory(electro);
            kitchenRobot.addCategory(kitchen);
            plate = new Product();
            plate.setName("Plate");
            plate.addCategory(kitchen);
            
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(electro);
            em.persist(kitchen);
            em.persist(flashlight);
            em.persist(kitchenRobot);
            em.persist(plate);
            em.getTransaction().commit();
            em.close();
        }
        
        @Test
        void testElectroContent() {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            Category c = em.find(Category.class, electro.getId());
            em.getTransaction().commit();
            c.getProducts().size();
            em.close();
            
            assertContainsProductWithName(c.getProducts(), "Flashlight");
            assertContainsProductWithName(c.getProducts(), "KitchenRobot");
        }
        
        void testKitchenContent() {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            Category c = em.find(Category.class, electro.getId());
            em.getTransaction().commit();
            c.getProducts().size();
            em.close();
            
            assertContainsProductWithName(c.getProducts(), "Flashlight");
            assertContainsProductWithName(c.getProducts(), "KitchenRobot");
        }
        
        @Test
        void testFlashlightContent() {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            Product p = em.find(Product.class, flashlight.getId());
            em.getTransaction().commit();
            p.getCategories().size();
            em.close();
            
            assertContainsCategoryWithName(p.getCategories(), "Electro");
        }
        
        @Test
        void testKitchenRobotContent() {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            Product p = em.find(Product.class, kitchenRobot.getId());
            em.getTransaction().commit();
            p.getCategories().size();
            em.close();
            
            assertContainsCategoryWithName(p.getCategories(), "Electro");
            assertContainsCategoryWithName(p.getCategories(), "Kitchen");
        }
        
        @Test
        void testPlateContent() {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            Product p = em.find(Product.class, plate.getId());
            em.getTransaction().commit();
            p.getCategories().size();
            em.close();
            
            assertContainsCategoryWithName(p.getCategories(), "Kitchen");
        }
        
        @Test(expectedExceptions=ConstraintViolationException.class)
	public void testDoesntSaveNullName(){
            EntityManager em = emf.createEntityManager();
            Product p = new Product();
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            em.close();
        }

	
	private void assertContainsCategoryWithName(Set<Category> categories,
			String expectedCategoryName) {
		for(Category cat: categories){
			if (cat.getName().equals(expectedCategoryName))
				return;
		}
			
		Assert.fail("Couldn't find category "+ expectedCategoryName+ " in collection "+categories);
	}
	private void assertContainsProductWithName(Set<Product> products,
			String expectedProductName) {
		
		for(Product prod: products){
			if (prod.getName().equals(expectedProductName))
				return;
		}
			
		Assert.fail("Couldn't find product "+ expectedProductName+ " in collection "+products);
	}

	
}
