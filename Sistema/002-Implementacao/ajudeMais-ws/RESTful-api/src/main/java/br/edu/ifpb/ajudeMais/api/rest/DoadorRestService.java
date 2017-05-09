/**
 * <p>
 * Ajude Mais - Módulo Web Service
 * </p>
 * 
 * <p>
 * Sistema para potencializar o processo de doação.
 * </p>
 * 
 * <a href="https://github.com/AjudeMais/AjudeMais">Ajude Mais</a>
 * <a href="https://franckaj.github.io">Franck Aragão"></a>
 * 
 * AJUDE MAIS - 2017®
 * 
 */
package br.edu.ifpb.ajudeMais.api.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.ajudeMais.domain.entity.Doador;
import br.edu.ifpb.ajudeMais.service.exceptions.AjudeMaisException;
import br.edu.ifpb.ajudeMais.service.negocio.DoadorService;

/**
 * 
 * <p>
 * <b> {@link DoadorRestService} </b>
 * </p>
 *
 * <p>
 * Classe define serviços disponibilizados de um doador.
 * </p>
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 */
@RestController
@RequestMapping(value = "/doador")
public class DoadorRestService {

	/**
	 * 
	 */
	@Autowired
	private DoadorService doadorService;

	/**
	 * 
	 * <p>
	 * POST /doador/ : Método disponibiliza recurso para salvar um doador. ROLE:
	 * PUBLIC
	 * </p>
	 * 
	 * @param doador
	 * @return Htpp 201, caso cadastro tenha occorido com sucesso
	 * @throws AjudeMaisException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save(@Valid @RequestBody Doador doador) throws AjudeMaisException {

		Doador doadorCriado = doadorService.save(doador);
		return new ResponseEntity<>(doadorCriado, HttpStatus.CREATED);
	}

	/**
	 * 
	 * <p>
	 * PUT /doador/ : Método disponibiliza recurso para atualizar um doador.
	 * ROLE: DOADOR
	 * </p>
	 * 
	 * @param doador
	 *            doador a ser atualizado.
	 * @return 201 caso sucesso.
	 * @throws AjudeMaisException
	 */
	@PreAuthorize("hasRole('DOADOR')")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Doador> update(@Valid @RequestBody Doador doador) throws AjudeMaisException {

		Doador pacienteAtualizado = doadorService.update(doador);

		return new ResponseEntity<Doador>(pacienteAtualizado, HttpStatus.CREATED);
	}

	/**
	 * 
	 * <p>
	 * GET /doador/ : Método disponibiliza recurso obter doadores cadastrados.
	 * ROLE: DOADOR
	 * </p>
	 * 
	 * @return
	 */
	@PreAuthorize("hasRole('DOADOR')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Doador>> findAll() {

		List<Doador> doador = doadorService.findAll();

		return new ResponseEntity<List<Doador>>(doador, HttpStatus.OK);
	}
}