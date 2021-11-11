package com.mintic.tiendafront.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.mintic.tiendafront.modelo.LoginDto;

import reactor.core.publisher.Mono;

@Service
public class ClienteImp implements IClienteTienda {

	// URL del Backend incluye contextPath definido en el servidor
	private static final String URL = "http://localhost:8090/tiendagenerica/v1";

	@Autowired
	private WebClient.Builder webClient;
	
	@Override
	public int login(LoginDto loginDto) {
		try {
			/*
			 * aqui nos conectamos al back  directamente al controlador donde estan las rutas 
			 * el back espera recibir un dto  por eso enviamos el dto login dto
			  * */

			Mono<Integer> response = webClient.build().post().uri(URL + "/loginclient")
					.accept(MediaType.APPLICATION_JSON).body(Mono.just(loginDto), LoginDto.class).retrieve()
					.bodyToMono(Integer.class);

			//Aqui se captura la respuesta del back 
			return response.block();

		} catch (WebClientResponseException e) {
			e.getMessage();
			System.out.println("---->" + e.getMessage());
			return 0;
		}
	}

}
