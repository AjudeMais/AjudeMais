package br.edu.ifpb.ajudeMais.service.negocio.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.ajudeMais.data.repository.CampanhaRepository;
import br.edu.ifpb.ajudeMais.domain.entity.Campanha;
import br.edu.ifpb.ajudeMais.domain.entity.InstituicaoCaridade;
import br.edu.ifpb.ajudeMais.service.exceptions.AjudeMaisException;
import br.edu.ifpb.ajudeMais.service.negocio.CampanhaService;
/**
 * Classe utilizada para serviços de {@link Campanha}
 * @author elson
 *
 */
@Service
public class CampanhaServiceImpl implements CampanhaService{
	
	@Autowired
	private CampanhaRepository campanhaRepository;
	
	/**
	 * Salva uma campanha no BD
	 * @param campanha
	 * @return
	 * @throws AjudeMaisException
	 */
	@Override
	@Transactional
	public Campanha save(Campanha donativo) throws AjudeMaisException {
		return campanhaRepository.save(donativo);
	}
	
	/**
	 * Atualiza uma campanha previamente salva no BD
	 * @param campanha
	 * @return
	 * @throws AjudeMaisException
	 */
	@Override
	@Transactional
	public Campanha update(Campanha donativo) throws AjudeMaisException {
		
		return campanhaRepository.save(donativo);
	}

	/**
	 * Busca e retorna todas as campanhas salvas
	 */
	@Override
	public List<Campanha> findAll() {
		return campanhaRepository.findAll();
	}

	/**
	 * Busca e retorna uma campanha específica pelo identificador
	 */
	@Override
	public Campanha findById(Long id) {
		return campanhaRepository.findOne(id);
	}

	/**
	 * Remove uma campanha ja cadastrada no BD
	 * @param campanha
	 */
	@Override
	@Transactional
	public void remover(Campanha donativo) {
		campanhaRepository.delete(donativo);
		
	}

	@Override
	public List<Campanha> findByInstituicaoCaridade(InstituicaoCaridade instituicaoCaridade) {
		return campanhaRepository.findByInstituicaoCaridade(instituicaoCaridade);
	}

}
