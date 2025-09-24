package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// PAGE OBJECT --- TODAS AS BIBLIOTECAS DO SELENIUM (NÃO ENTRA AS LIBS DO JUNIT)
public class CadastroLeilaoPage extends PageObject {

    private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";


    //    private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";
//    private WebDriver browser; ---- ------ PAGE OBJECT


    public CadastroLeilaoPage(WebDriver browser) { // construtor subistituindo os métodos
        super(browser);
//        this.browser = browser;       //@BeForEach
    }
//    public void fechar() { ------ PAGE OBJECT
//        this.browser.quit(); //@AfterEach
//    }


    public LeiloesPage cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {
        this.browser.findElement(By.id("nome")).sendKeys(nome);
        this.browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
        this.browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
        this.browser.findElement(By.id("button-submit")).submit();

        return new LeiloesPage(browser);

    }

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().equals(URL_CADASTRO_LEILAO);
    }

    public boolean isMensagensDeValicadaoVisiveis() {
        String pageSource = browser.getPageSource();
        return pageSource.contains("minimo 3 caracteres")
                && pageSource.contains("não deve estar em branco")
                && pageSource.contains("deve ser um valor maior de 0.1")
                && pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
    }
}
