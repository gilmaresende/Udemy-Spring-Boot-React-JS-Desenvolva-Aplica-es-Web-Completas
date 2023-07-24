package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("java16Jpa");
        EntityManager em = emf.createEntityManager();

        remove(em);
        em.close();
        emf.close();
        System.out.println("Pronto!");
    }

    private static void remove(EntityManager em) {
        em.getTransaction().begin();
        Pessoa p = em.find(Pessoa.class, 5);
        em.remove(p);
        em.getTransaction().commit();
    }

    private static void reade(EntityManager em) {
        Pessoa p = em.find(Pessoa.class, 5);
        System.out.println(p);
    }

    private static void save(EntityManager em) {
        Pessoa p1 = new Pessoa(null, "Joao 2", "Joao@gmail.com");
        Pessoa p2 = new Pessoa(null, "Maria 2", "Maria@gmail.com");
        Pessoa p3 = new Pessoa(null, "Pedro 2", "Pedro@gmail.com");

        em.getTransaction().begin();

        em.persist(p1);
        em.persist(p2);
        em.persist(p3);

        em.getTransaction().commit();
        System.out.println("Pronto!");
    }


}
