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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.ajudeMais.domain.entity.Conta;
import br.edu.ifpb.ajudeMais.service.exceptions.AjudeMaisException;
import br.edu.ifpb.ajudeMais.service.negocio.ContaService;

/**
 * 
 * <p>
 * {@link ContaRestService}
 * </p>
 * 
 * <p>
 * Classe utilizada para serviços providos referentes a {@link Conta}
 * </p>
 *
 * <pre>
 * </pre
 *
 * @author <a href="https://franckaj.github.io">Franck Aragão</a>
 *
 */
@RestController
@RequestMapping("/conta")
public class ContaRestService {

	/**
	 * 
	 */
	@Autowired
	private ContaService contaService;

	/**
	 * POST /conta : Salva usuário
	 * 
	 * @param conta
	 * @return response
	 * @throws AjudeMaisException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Conta> create(@Valid @RequestBody Conta conta) throws AjudeMaisException {

		Conta contaCriada = contaService.save(conta);

		return new ResponseEntity<Conta>(contaCriada, HttpStatus.CREATED);
	}

	/**
	 * POST /conta/changePassword : Altera senha do usuário
	 *
	 * @param nova senha
	 * @return ResponseEntity 200 (ok) ou 400 (Bad Request)
	 */
	@PostMapping(path = "/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody String password) {
		contaService.changePassword(password);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}