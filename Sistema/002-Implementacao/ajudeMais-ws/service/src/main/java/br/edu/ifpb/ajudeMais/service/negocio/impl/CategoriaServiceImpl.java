/**
 * Ajude Mais - Módulo Web Service
 * 
 * Sistema para potencializar o processo de doação.
 * 
 * <a href="https://github.com/AjudeMais/AjudeMais">Ajude Mais</a>
 * <a href="https://franckaj.github.io">Franck Aragão"></a>
 * <a href="https://github.com/JoseRafael97">Rafael Feitosa</a>
 * 
 * AJUDE MAIS - 2017®
 * 
 */
package br.edu.ifpb.ajudeMais.service.negocio.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.ajudeMais.data.repository.CategoriaRepository;
import br.edu.ifpb.ajudeMais.domain.entity.Categoria;
import br.edu.ifpb.ajudeMais.domain.entity.InstituicaoCaridade;
import br.edu.ifpb.ajudeMais.service.exceptions.AjudeMaisException;
import br.edu.ifpb.ajudeMais.service.negocio.CategoriaService;

/**
 * 
 * <p>{@link CategoriaServiceImpl} </p>
 * 
 * <p>
 * Classe utilizada para serviços de {@link Categoria}
 * </p>
 *
 * <pre>
 * </pre
 *
 * @author <a href="https://franckaj.github.io">Franck Aragão</a>
 *
 */
@Service
public class CategoriaServiceImpl implements CategoriaService{

	/**
	 * 
	 */
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/**
	 * salva uma categoria no BD
	 * 
	 * @throws AjudeMaisException
	 */
	@Override
	@Transactional
	public Categoria save(Categoria categoria) throws AjudeMaisException {
		
		return categoriaRepository.save(categoria);
	}

	/**
	 * atualiza uma categoria previamente salva
	 * 
	 * 
	 * @throws AjudeMaisException
	 */
	@Override
	@Transactional
	public Categoria update(Categoria categoria) throws AjudeMaisException {
		
		return categoriaRepository.save(categoria);
	}
	/**
	 * 
	 * busca e retorna todas as categorias salvas
	 * 
	 */
	@Override
	public List<Categoria> findAll() {
		
		return categoriaRepository.findAll();
	}
	/**
	 * 
	 * busca uma categoria especifica pelo ID
	 * 
	 */
	@Override
	public Categoria findById(Long id) {
		return categoriaRepository.findOne(id);
	}
	/**
	 * 
	 * remove uma categoria previamente cadastrada
	 * 
	 */
	@Override
	@Transactional
	public void remover(Categoria categoria) {
		categoriaRepository.delete(categoria);
	}

	/**
	 * 
	 * busca e retorna as categorias cadastradas por uma
	 * instituicao de caridade em especifico
	 * 
	 */
	@Override
	public List<Categoria> findByInstituicaoCaridade(InstituicaoCaridade instituicaoCaridade) {
		return categoriaRepository.findByInstituicaoCaridade(instituicaoCaridade);
	}
}
