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
package br.edu.ifpb.ajudeMais.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.ajudeMais.domain.entity.Conta;
import br.edu.ifpb.ajudeMais.domain.entity.MensageiroAssociado;

/**
 * 
 * <p>
 * {@link MensageiroAssociadoRepository}
 * </p>
 * 
 * <p>
 * Interaface utilizada para operação de pesistência de
 * {@link MensageiroAssociado}
 * </p>
 *
 * <pre>
 * </pre
 *
 * @author <a href="https://franckaj.github.io">Franck Aragão</a>
 *
 */
public interface MensageiroAssociadoRepository extends JpaRepository<MensageiroAssociado, Long> {

	/**
	 * 
	 * <p>
	 * 
	 * Busca mensageiros associados pela insituição, filtrando por sua conta.
	 * </p>
	 * 
	 * @param conta
	 */
	List<MensageiroAssociado> findByInstituicaoCaridadeConta(Conta conta);

}
