package br.com.alura.leilao.login;
import br.com.alura.leilao.PageObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private LoginPage paginaDeLogin;

//    private static final String URL_LOGIN = "http://localhost:8080/login";
//    private WebDriver browser;

//    @BeforeAll
//    public static void beforeAll() {
//        WebDriverManager.chromedriver().setup();
//    }

    @BeforeEach
    public void beforeEach() {
//        this.browser = new ChromeDriver();
//        this.browser.navigate().to(URL_LOGIN);
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
//        WebDriverManager.chromedriver().setup();
//        this.browser.quit();
        this.paginaDeLogin.fechar();
    }

    @Test
    public void deveriaEfeturarLoginComDadosValidos() {
//        WebDriverManager.chromedriver().setup();
//        WebDriver browser = new ChromeDriver();
//        browser.navigate().to("http://localhost:8080/login");
//        browser.findElement(By.id("username")).sendKeys("fulano");
//        browser.findElement(By.id("password")).sendKeys("pass");
//        browser.findElement(By.id("login-form")).submit();
        paginaDeLogin.preeencheFormularioDeLogin("fulano", "pass");
        paginaDeLogin.efetuaLogin();

//        Assertions.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
//        Assertions.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
        Assertions.assertFalse(paginaDeLogin.isPaginaDeLogin());
        Assertions.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
//        browser.quit();
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
//        WebDriverManager.chromedriver().setup();
//        WebDriver browser = new ChromeDriver();
//        browser.navigate().to("http://localhost:8080/login");
//        browser.findElement(By.id("username")).sendKeys("invalido");
//        browser.findElement(By.id("password")).sendKeys("123123");
//        browser.findElement(By.id("login-form")).submit();
        paginaDeLogin.preeencheFormularioDeLogin("invalido", "123");
        paginaDeLogin.efetuaLogin();

//        Assertions.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
//        Assertions.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
//        Assertions.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
        Assertions.assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());
        Assertions.assertNull(paginaDeLogin.getNomeUsuarioLogado());
        Assertions.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
//        browser.quit();
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemLogin() {
//        this.browser.navigate().to("http://localhost:8080/leiloes/2");
        paginaDeLogin.navegarParaPaginaDeLances();

//        Assertions.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login"));
//        Assertions.assertFalse(browser.getPageSource().contains("Dados do Leilão"));
        Assertions.assertTrue(paginaDeLogin.isPaginaDeLogin());
        Assertions.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));

    }

}
