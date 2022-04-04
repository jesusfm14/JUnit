package com.example.demo.services;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.model.Articulo;

@RunWith(MockitoJUnitRunner.class)
public class CarritoCompraServiceImplTest {
	
	@InjectMocks
	private CarritoCompraServiceImpl carritoService = new CarritoCompraServiceImpl();
	
	@Mock
	private BaseDatosI baseDatos;

	@Test
	public void testLimpiarCesta() {
		carritoService.addArticulo(new Articulo("Camiseta", 15.99));
		assertFalse(carritoService.getArticulos().isEmpty());
		carritoService.limpiarCesta();
		assertTrue(carritoService.getArticulos().isEmpty());
	}

	@Test
	public void testAddArticulo() {		
		
		carritoService.addArticulo(new Articulo("Camiseta", 15.99));
		assertFalse(carritoService.getArticulos().isEmpty());
		
	}

	@Test
	public void testGetNumArticulos() {
		int tamaño = carritoService.getNumArticulos();
		assertFalse(carritoService.getArticulos().size() == -1);
		assertTrue(carritoService.getArticulos().size() == tamaño);
	}

	@Test
	public void testGetArticulos() {
		List<Articulo> articulos = carritoService.getArticulos();
		assertFalse(articulos != carritoService.getArticulos());
		assertTrue(articulos == carritoService.getArticulos());
	}

	@Test
	public void testTotalPrice() {
		carritoService.addArticulo(new Articulo("Camiseta", 15.99));
		assertFalse(carritoService.totalPrice()==0);
		assertTrue(carritoService.totalPrice()==15.99);
		
	}

	@Test
	public void testCalcularDescuento() {
		assertEquals(Double.valueOf(90D), carritoService.calcularDescuento(100D, 10D));
	}
	
	@Test 
	public void testAplicarDescuento() {
		Articulo articulo = new Articulo("Camiseta", 20.00);
		when(baseDatos.buscarArticulo(1)).thenReturn(articulo);
		Double res = carritoService.aplicarDescuento(1, 10D);
		assertEquals(Double.valueOf(18D), res);
		verify(baseDatos, atLeast(1)).buscarArticulo(1);
		
	}
	
	@Test
	public void testInsertar() {
		Articulo articulo = new Articulo("Pantalon", 89.9D);
		int id = 1;

		when(baseDatos.insertarArticulo(articulo)).thenReturn(6);
		id = carritoService.insertar(articulo);

		assertEquals(8, id);
		assertTrue(carritoService.getArticulos().contains(articulo));
		verify(baseDatos, atLeast(1)).insertarArticulo(any(Articulo.class));
		
	}

}
