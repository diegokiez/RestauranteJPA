/**
 * 
 */
package com.devpredator.practicajpa.dao.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.devpredator.practicajpa.dao.RestauranteDAO;
import com.devpredator.practicajpa.entity.Menu;
import com.devpredator.practicajpa.entity.Restaurante;
import com.devpredator.practicajpa.entity.TipoRestaurante;

/**
 * @author Diego
 *
 */
class RestauranteDAOTest {
RestauranteDAO rDAO = new RestauranteDAOImpl();
	/**
	 * Test method for {@link com.devpredator.practicajpa.dao.impl.RestauranteDAOImpl#guardar(com.devpredator.practicajpa.entity.Restaurante)}.
	 */
	@Test
	void testGuardar() {
		Restaurante r = new Restaurante();
		r.setNombre("BurgerKing");
		r.setImagen("BG");
		r.setSlogan("You rule");
		
		TipoRestaurante tr = new TipoRestaurante();
		tr.setDescripcion("Comida rapida");
		tr.setFechaCreacion(LocalDateTime.now());
		tr.setEstatus(true);
		r.setTipoRestaurante(tr);
		
		r.setFechaCreacion(LocalDateTime.now());
		r.setEstatus(true);
		
		Menu m = new Menu();
		m.setClave("ASO01");
		m.setDescripcion("Comida para toda la familia");
		m.setFechaCreacion(LocalDateTime.now());
		m.setEstatus(true);
		r.setMenu(m);
		this.rDAO.guardar(r);
	}

	/**
	 * Test method for {@link com.devpredator.practicajpa.dao.impl.RestauranteDAOImpl#actualizar(com.devpredator.practicajpa.entity.Restaurante)}.
	 */
	@Test
	void testActualizar() {
		Restaurante rC = rDAO.consultarById(1L);
		rC.setSlogan("A que no puedes comer solo una");
		rC.getTipoRestaurante().setEstatus(false);
		rC.getMenu().setDescripcion("Especial para niños");
		rC.setFechaModificacion(LocalDateTime.now());
		rC.getTipoRestaurante().setFechaModificacion(LocalDateTime.now());
		rC.getMenu().setFechaModificacion(LocalDateTime.now());
		this.rDAO.actualizar(rC);
		}

	/**
	 * Test method for {@link com.devpredator.practicajpa.dao.impl.RestauranteDAOImpl#eliminar(java.lang.Long)}.
	 */
	@Test
	void testEliminar() {
		this.rDAO.eliminar(11L);
	}

	/**
	 * Test method for {@link com.devpredator.practicajpa.dao.impl.RestauranteDAOImpl#consultar()}.
	 */
	@Test
	void testConsultar() {
		List<Restaurante> restaurantes = this.rDAO.consultar();
		assertTrue(restaurantes.size() > 0);
		for(Restaurante res : restaurantes) {
			System.out.println("Nombre: " + res.getNombre());
			System.out.println("Tipo de restaurante: " + res.getTipoRestaurante().getDescripcion());
			System.out.println("Menu: " + res.getMenu().getDescripcion());
		}
	}

	/**
	 * Test method for {@link com.devpredator.practicajpa.dao.impl.RestauranteDAOImpl#consultarById(java.lang.Long)}.
	 */
	@Test
	void testConsultarById() {
		Restaurante resC = this.rDAO.consultarById(1L);
		System.out.println("Nombre: " + resC.getNombre());
		System.out.println("Tipo de restaurante: " + resC.getTipoRestaurante().getDescripcion());
		System.out.println("Menu: " + resC.getMenu().getDescripcion());
	}

}
