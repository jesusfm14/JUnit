package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Articulo;
import com.example.demo.services.BaseDatosI;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	@Autowired
	private BaseDatosI baseDatos;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		baseDatos.iniciar();
		Articulo articulo = new Articulo("Pantalones", 7.32);
		baseDatos.insertarArticulo(articulo);
		baseDatos.buscarArticulo(baseDatos.totalArticulos());
		
	}

}
