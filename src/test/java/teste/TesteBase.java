
package teste;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author valeria
 */
public class TesteBase {
    
    protected static EntityManagerFactory emf;
	protected static Logger logger = Logger.getGlobal();
	protected EntityManager em;
	protected EntityTransaction et;
	
	
	public TesteBase() {
		
	}
	
	@BeforeClass
	public static void setUpClass() {
		logger.setLevel(Level.SEVERE);
		emf = Persistence.createEntityManagerFactory("DesafioSf");
                 DbUnitUtil.inserirDados();
		
	}
	
	@AfterClass
	public static void tearDownClass() {
		emf.close();
	}
	
	@Before
	public void seTup() {
		em = emf.createEntityManager();
		beginTransaction();
	}
	
	@After
	public void tearDown() {
		commitTransaction();
		em.close();
	}

	private void commitTransaction() {
		try {
			et.commit();
		}
		catch(Exception ex) {
			if(et.isActive()) {
				et.rollback();
			}
		}
	}

	private void beginTransaction() {
		et = em.getTransaction();
		et.begin();
		
	}
	
}
