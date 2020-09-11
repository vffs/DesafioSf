package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author valeria
 */
public class Persistencia {

    private static Persistencia persiste = null;

    public static Persistencia getCurrentIntance() {

        if (persiste == null) {
            persiste = new Persistencia();
        }

        return persiste;
    }

    private EntityManagerFactory emf = null;

    private Persistencia() {
        this.emf = Persistence.createEntityManagerFactory("DesafioSf");
    }

    public void insert(Object o) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(o);
            em.flush();
            em.getTransaction().commit();
            
        }catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            
        }
        finally{
         if(em != null) {
            em.close();
         }
        }
    }

    public void update(Object o) {
        EntityManager em = emf.createEntityManager();
        
        try{
        em.getTransaction().begin();

        em.merge(o);
        em.getTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        finally{
            if(em != null){
              em.close();  
            }
        }
        
    }

    public List read(String query, Class c) {
        List listaRetornada;
        EntityManager em = emf.createEntityManager();
        
        try{
             listaRetornada = em.createQuery(query, c).getResultList();
             
        }finally{
            em.close();
        }

        return listaRetornada;
    }

    public void delete(Object o) {
        EntityManager em = emf.createEntityManager();
        try{
        Object oDelete = o;

        if (!em.contains(o)) {
            oDelete = em.merge(o);
        }

        em.getTransaction().begin();
        em.remove(oDelete);
        em.getTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        finally{
            if(em != null){
               em.close();
            }
        }
       

    }
    
    

}
