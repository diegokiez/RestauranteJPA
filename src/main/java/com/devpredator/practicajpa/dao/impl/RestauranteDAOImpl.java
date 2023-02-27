/**
 * 
 */
package com.devpredator.practicajpa.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Cache;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.SynchronizationType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

import com.devpredator.practicajpa.dao.RestauranteDAO;
import com.devpredator.practicajpa.entity.Restaurante;

/**
 * @author 4PF28LA_2004
 *
 */
public class RestauranteDAOImpl implements RestauranteDAO{
	
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = 
			Persistence.createEntityManagerFactory("persistenceDiegokiez");

	@Override
	public void guardar(Restaurante restaurante) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.persist(restaurante);
			et.commit();
		} catch (Exception e) {
			if(et!=null) et.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
	}

	@Override
	public void actualizar(Restaurante restaurante) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.merge(restaurante);
			et.commit();
		} catch (Exception e) {
			if(et!=null) et.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
		
	}

	@Override
	public void eliminar(Long id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Restaurante restauranteConsultado = consultarById(id);
		
		try {
			et.begin();
			em.remove(restauranteConsultado);
			et.commit();
		} catch (Exception e) {
			if(et!=null) et.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
		
	}

	@Override
	public List<Restaurante> consultar() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

		TypedQuery<Restaurante> queryTyped = (TypedQuery<Restaurante>) em
				.createQuery("FROM SubGenero ORDER BY descripcion");

		return queryTyped.getResultList();
	}

	@Override
	public Restaurante consultarById(Long id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		return em.find(Restaurante.class, id);
	}

}
