package tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class accessAccountByPassword {
    private WebDriver navegador2;
    private WebDriver navegador;

    @Before
    public void setUp() {

        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\javaAutomação\\chromedriver_win32\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


        //navegando pelo navegador
        navegador.get("https://www.saraiva.com.br/");
    }

    @Test
    public void CreateAcconunt() throws InterruptedException {

        navegador.findElement(By.id("lgpd-accepted")).click();
        //Fazer autenticação Entrar / Cadastrar
        String tokenSaraiva = "";
        navegador.findElement(By.id("link-account")).click();
        for (Cookie c : navegador.manage().getCookies()) {
            String teste = c.getName();
            //_vss = Token de Acesso da loja para efetuar o cadastro/login pelo vtexid
            if (teste.equals("_vss")) {
                tokenSaraiva = c.getValue();
            }
        }
        ArrayList<String> tabs = new ArrayList<String>(navegador.getWindowHandles());
        navegador.switchTo().window(tabs.get(0));
        navegador.get("https://www.saraiva.com.br/api/vtexid/pub/authentication/oauth/redirect?authenticationToken=" + tokenSaraiva + "&providerName=Cliente%20Saraiva");

        //Entrar Conta Saraiva
        navegador.findElement(By.id("username")).sendKeys("gustavomorais20@mailinator.com");
        navegador.findElement(By.id("password")).sendKeys("abc123");
        navegador.findElement(By.id("submit-1")).click();
        Thread.sleep(Long.parseLong("5000"));
        navegador.close();
    }
}