package br.edu.ifpb.ajudemais.testeaceitacao.seleniumPageObject.categoria;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.edu.ifpb.ajudemais.testeaceitacao.seleniumPageObject.AbstractPage;
import br.edu.ifpb.ajudemais.testeaceitacao.seleniumPageObject.instituicaoCaridade.CreateInstituicaoCaridadePage;
import br.edu.ifpb.ajudemais.testeaceitacao.seleniumPageObject.instituicaoCaridade.InstituicaoCaridadePage;
import br.edu.ifpb.ajudemais.testeaceitacao.seleniumPageObject.login.LoginPage;
/**
 * 
 * @author elson
 *
 */
public class CategoriaPage extends AbstractPage{
	
	private static final String USERNAME_INSTIUTICAO = "42199149196";
	private static final String PASSWORD_INSTIUTICAO = "42199149196";
	private LoginPage loginPage;
	private InstituicaoCaridadePage instituicaoCaridadePage;
	
	/**
	 * 
	 * @param driver
	 */
	public CategoriaPage(WebDriver driver) {
		super(driver);
		loginPage = new LoginPage(driver);
		instituicaoCaridadePage = new InstituicaoCaridadePage(driver);
	}
	
	/**
	 * 
	 * @param nome
	 * @return
	 */
	public boolean foiCadastradaComSucessoCategoria(String nome){
		$(By.xpath("//*[@id=\"dtCategorias\"]/tbody")).should(appears);
		boolean categoria = driver.getPageSource().contains(nome);
		return categoria;
	}
	/**
	 * 
	 * @param msg
	 * @return
	 */
	public boolean categoriaRemovidaComSucesso(String msg){
		$(By.xpath("//*[@id=\"dtCategorias\"]/tbody")).should(appears);
		boolean categoria = driver.getPageSource().contains(msg);
		return categoria;
	}
	/**
	 * 
	 */
	public void visita() {
		open(getUrlBase() + "/home/categoria");
		
		fazlogin(USERNAME_INSTIUTICAO, PASSWORD_INSTIUTICAO);
		
		try {
			Thread.sleep(1000);
			
			
			boolean houveLoginInvalido = loginPage.houveLoginInvalido("Nome de usuário ou senha inválido");
			
			if(houveLoginInvalido){
				addInstituicao();
				
				Thread.sleep(400);
				
				$(By.xpath("//*[@id=\"content-wrapper\"]/div/div[1]/div/div/div[1]/a")).click();
				$(By.xpath("//*[@id=\"content-wrapper\"]/div/div[1]/div/div/div[1]/ul/div/ul/a[2]")).click();
				
				fazlogin(USERNAME_INSTIUTICAO, PASSWORD_INSTIUTICAO);
			}
			

			$(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[4]/a")).click();


		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	private void addInstituicao(){
		instituicaoCaridadePage.visita();

		CreateInstituicaoCaridadePage createInstituicaoCaridadePage = instituicaoCaridadePage.novo();

		createInstituicaoCaridadePage.addOrEditInstituicaoCaridade(
				"INSTIUTIÇÂO P TESTE",
				"CRIADA EM CATEGORIA PAGE",
				USERNAME_INSTIUTICAO, 
				"(83) 99812-2196", 
				"testecat123@teste.com", 
				"58500-000",
				"Rua Teste",
				"123", 
				"Centro",
				"casa");
	}
	
	/**
	 * 
	 * @return
	 */
	public CriarCategoriaPage novo() {
		$(By.xpath("//*[@id=\"content-wrapper\"]/div/div[3]/div/div/div/div/div[1]/div/button")).click();

		return new CriarCategoriaPage(driver);
	}
	/**
	 * 
	 * @param nome
	 * @return
	 */
	public RemoverCategoriaPage remove(){
		$(By.xpath("//*[@id=\"dtCategorias\"]/tbody/tr[5]/td[1]/button[2]")).click();
		
		return new RemoverCategoriaPage(driver);
	}
	/**
	 * 
	 * @return
	 */
	public RemoverCategoriaPage tentaRemoverCategoriaEDesiste(){
		$(By.xpath("//*[@id=\"dtCategorias\"]/tbody/tr[4]/td[1]/button[2]")).click();
		return new RemoverCategoriaPage(driver);
	}
	/**
	 * Adiciona ou edita uma categoria
	 */
	public void adicionarOuEditarCategoria(String nome, String descricao, boolean ativo) {		

		$(By.xpath("//*[@id=\"modal-body\"]/div[1]/input")).setValue(nome);
		$(By.xpath("//*[@id=\"modal-body\"]/div[3]/textarea")).setValue(descricao);
		
		$(By.xpath("//*[@id=\"modal-body\"]/div[2]/div/div/span[3]")).click();
		
		$(By.xpath("/html/body/div[1]/div/div/form/div[2]/input")).click();
		

	}
	/**
	 * 
	 * @return
	 */
	public EditarCategoriaPage edit(String userName) {
		String xpath = String.format("//*[@id=\"dtCategorias\"]/tbody/tr[2]/td[1]/button[1]", userName);
		$(By.xpath(xpath)).click();
		return new EditarCategoriaPage(driver);
	}
	
	
}
