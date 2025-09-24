package br.com.alura.leilao;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PageObject {

    protected WebDriver browser;

    public PageObject(WebDriver browser) {
        WebDriverManager.chromedriver().setup(); // @BeForAll

        if (browser == null) {
            this.browser = new ChromeDriver();
        } else {
            this.browser = browser;       //@BeForEach
        }

        this.browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS). pageLoadTimeout(10, TimeUnit.SECONDS); // tempo para carregar a aplicação

    }

    public void fechar() {
        this.browser.quit(); //@AfterEach
    }



}
