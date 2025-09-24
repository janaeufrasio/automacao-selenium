package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// PAGE OBJECT --- TODAS AS BIBLIOTECAS DO SELENIUM (NÃO ENTRA AS LIBS DO JUNIT)
public class LeiloesPage extends PageObject {

    private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";
    private static final String URL_LEILOES = "http://localhost:8080/leiloes";
//    private WebDriver browser; ------ PAGE OBJECT


    public LeiloesPage(WebDriver browser) { // construtor subistituindo os métodos
        super(browser);
//        this.browser = browser;       //@BeForEach
    }
//    public void fechar() { ------ PAGE OBJECT
//        this.browser.quit(); //@AfterEach
//    }


    public CadastroLeilaoPage carregarFormulario() {
        this.browser.navigate().to(URL_CADASTRO_LEILAO);
        return new CadastroLeilaoPage(browser);
    }

    public boolean isLeilaoCadastrado(String nome, String valor, String data) {
        WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child")); //css
        WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial= linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome) && colunaDataAbertura.getText().equals(data) && colunaValorInicial.getText().equals(valor);

    }

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().contentEquals(URL_LEILOES);
    }
}
