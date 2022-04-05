package com.drafael.springboot.webflux.app;

import com.drafael.springboot.webflux.app.models.documents.Categoria;
import com.drafael.springboot.webflux.app.models.documents.Producto;
import com.drafael.springboot.webflux.app.models.services.ProductoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootWebfluxApirestApplicationTests {

	@Autowired
	private WebTestClient client;

	@Autowired
	private ProductoService service;


	@Test
	void listarTest() {
		client.get()
				.uri("/api/v2/productos")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Producto.class)
				.hasSize(10);
	}

	@Test
	void verTest() {
		Producto producto = service.findByNombre("Sony Notebook").block();
		client.get()
				.uri("/api/v2/productos/{id}", Collections.singletonMap("id", producto.getId()))
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.nombre").isEqualTo("Sony Notebook");

	}

	@Test
	void crearTest() {
		Categoria categoria = service.findCategoriaByNombre("Muebles").block();

		Producto producto = new Producto("Prueba",15.00, categoria);
		client.post()
				.uri("/api/v2/productos/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(producto), Producto.class)
				.exchange()
				.expectStatus().isCreated()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.nombre").isEqualTo("Prueba")
				.jsonPath("$.categoria.nombre").isEqualTo("Muebles");
	}

	@Test
	void crear2Test() {
		Categoria categoria = service.findCategoriaByNombre("Muebles").block();

		Producto producto = new Producto("Prueba",15.00, categoria);
		client.post()
				.uri("/api/v2/productos/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(producto), Producto.class)
				.exchange()
				.expectStatus().isCreated()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody(Producto.class)
				.consumeWith(response -> {
					Producto p = response.getResponseBody();
					Assertions.assertThat(p.getId()).isNotEmpty();
					Assertions.assertThat(p.getNombre()).isNotEmpty();
					Assertions.assertThat(p.getCategoria().getNombre()).isNotEmpty();

				});
	}


	/*@Test
	void listarTextFail() {
		client.get()
				.uri("/api/v2/productos")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Producto.class)
				.hasSize(9);
	}*/

}
