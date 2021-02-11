package tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;

public class AccessAccountByMailinator {
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
    public void AccessAccountByMailinator() throws InterruptedException {
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

// Fazer Login direto pelo E-mail (mailinator)
        navegador.findElement(By.id("loginWithAccessKeyBtn")).click();

        //Adicionando o e-mail de acesso de login no campo "E-mail"
        navegador.findElement(By.id("appendedInputButton")).sendKeys("gustavomorais19@mailinator.com");
        navegador.findElement(By.id("sendAccessKeyBtn")).click();

        //Clicando no botão "Entrar"
        navegador2 = new ChromeDriver();
        navegador2.get("https://www.mailinator.com/v4/public/inboxes.jsp?to=gustavomorais19");
//                navegador.findElement(By.linkText("moments ogo")).click();
//                WebElement  codeAccess = navegador.findElement(By.className("cor-numero p-t-10"));
        Thread.sleep(Long.parseLong("5000"));
        JavascriptExecutor js = (JavascriptExecutor) navegador2;
        String sText = js.executeScript("return document.getElementsByTagName('tr')[1].innerText;").toString();
        String String[] = sText.split("=");
        String actual = String[3].substring(2, 9);
        navegador2.close();

        navegador.findElement(By.id("access-code")).sendKeys(actual);
        navegador.findElement(By.id("confirmLoginAccessKeyBtn")).click();
        Thread.sleep(Long.parseLong("7000"));
        navegador.close();
    }
}