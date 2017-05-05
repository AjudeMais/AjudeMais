package br.edu.ifpb.ajudemais.domain;

import java.io.Serializable;

/**
 * <p>
 * <b>InstituicaoCaridade</b>
 * </p>
 * </p>
 *
 * @author <a href="https://github.com/JoseRafael97">Rafael Feitosa</a>
 */
public class InstituicaoCaridade implements Serializable{

    private Long id;

    private String nome;

    private String descricao;

    private String telefone;

    private String documento;

    private Endereco endereco;

    private Conta conta;

    /**
     *
     * @param nome
     */
    public InstituicaoCaridade(String nome) {
        this.nome = nome;
    }

    /**
     *
     */
    public InstituicaoCaridade() {

    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }


    /**
     *
     * @return
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     *
     * @param telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     *
     * @return
     */
    public String getDocumento() {
        return documento;
    }

    /**
     *
     * @param documento
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     *
     * @return
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     *
     * @param endereco
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     *
     * @return
     */
    public Conta getConta() {
        return conta;
    }

    /**
     *
     * @param conta
     */
    public void setConta(Conta conta) {
        this.conta = conta;
    }

    /**
     *
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     *
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
