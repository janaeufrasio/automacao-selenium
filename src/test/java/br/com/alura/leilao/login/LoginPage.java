package br.com.alura.leilao.login;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// PAGE OBJECT --- TODAS AS BIBLIOTECAS DO SELENIUM (NÃO ENTRA AS LIBS DO JUNIT)
public class LoginPage extends PageObject {

    private static final String URL_LOGIN = "http://localhost:8080/login";
//    private WebDriver browser; ------ PAGE OBJECT


    public LoginPage() { // construtor subistituindo os métodos
        super(null);
//        WebDriverManager.chromedriver().setup(); // @BeForAll ------ PAGE OBJECT
//        this.browser = new ChromeDriver();       //@BeForEach ------ PAGE OBJECT
        this.browser.navigate().to(URL_LOGIN);   //@BeForEach

    }
//    public void fechar() { ------ PAGE OBJECT
//        this.browser.quit(); //@AfterEach
//    }

    public void preeencheFormularioDeLogin(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public LeiloesPage efetuaLogin() {
        browser.findElement(By.id("login-form")).submit();
        return new LeiloesPage(browser);
    }

    public boolean isPaginaDeLogin() {
        return browser.getCurrentUrl().equals(URL_LOGIN);
    }

    public String getNomeUsuarioLogado() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }

    }

    public void navegarParaPaginaDeLances() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean contemTexto(String texto) {
        return browser.getPageSource().contains(texto);
    }

    public boolean isPaginaDeLoginComDadosInvalidos() {
        return browser.getCurrentUrl().equals(URL_LOGIN + "?error");
    }
}
