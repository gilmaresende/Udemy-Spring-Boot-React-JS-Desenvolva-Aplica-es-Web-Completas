package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {

		// Salvando

		/*
		 * Pessoa p1 = new Pessoa(null, "Joao", "Joao@gmail.com"); Pessoa p2 = new
		 * Pessoa(null, "Maria", "Maria@gmail.com"); Pessoa p3 = new Pessoa(null,
		 * "Pedro", "Pedro@gmail.com");
		 * 
		 * EntityManagerFactory emf =
		 * Persistence.createEntityManagerFactory("java16Jpa"); EntityManager em =
		 * emf.createEntityManager();
		 * 
		 * em.getTransaction().begin();
		 * 
		 * em.persist(p1); em.persist(p2); em.persist(p3);
		 * 
		 * em.getTransaction().commit(); System.out.println("Pronto!");
		 */

		// Buscando

		/*
		 * EntityManagerFactory emf =
		 * Persistence.createEntityManagerFactory("java16Jpa"); EntityManager em =
		 * emf.createEntityManager();
		 * 
		 * Pessoa p = em.find(Pessoa.class, 2); System.out.println(p);
		 * 
		 * 
		 * System.out.println("Pronto!"); em.close(); emf.close();
		 */

		// Removendo

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("java16Jpa");
		EntityManager em = emf.createEntityManager();

		 em.getTransaction().begin();
	
		Pessoa p = em.find(Pessoa.class, 2);

		em.remove(p);
		 em.getTransaction().commit();
		System.out.println(p);

		System.out.println("Pronto!");
		em.close();
		emf.close();

	}

}
