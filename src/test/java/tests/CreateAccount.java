package tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;

public class CreateAccount {

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
            if(teste.equals("_vss")){
                tokenSaraiva = c.getValue();
            }
        }
        ArrayList<String> tabs = new ArrayList<String>(navegador.getWindowHandles());
        navegador.switchTo().window(tabs.get(0));
        navegador.get("https://www.saraiva.com.br/api/vtexid/pub/authentication/oauth/redirect?authenticationToken=" + tokenSaraiva + "&providerName=Cliente%20Saraiva");

        navegador.findElement(By.linkText("CADASTRAR")).click();

        WebDriverWait wait = new WebDriverWait(navegador, 10);

        //Validar Nome
        navegador.findElement(By.id("InputNome1")).sendKeys("Gustavo");

        //Digitar no campo "Sobrenome" que está dentro do formulário de id "InputSobrenome1" o texto "Morais"
        navegador.findElement(By.id("InputSobrenome1")).sendKeys("Morais");

        // Digitar no campo no nome "E-mail" que está dentro do formulário de id "InputEmail1" o email existente  "gustavomorais21@mailinator.com"
       //OBS: sempre que for rodar este teste tem que alterar o e-mail
        navegador.findElement(By.id("InputEmail1")).sendKeys("gustavomorais21@mailinator.com");
        WebElement emailCadastrado = navegador.findElement(By.id("InputEmail1"));

        //Digitar no campo de criar "Senha" que está dentro do formulário de id "InputSenha1" a senha "abc123"
        navegador.findElement(By.id("InputSenha1")).sendKeys("abc123");

        //Digitar no campo de criar "Confirme a senha" que está dentro do formulário de id "InputConfirmeSenha1" a senha "abc123"
        navegador.findElement(By.id("InputConfirmeSenha1")).sendKeys("abc123");

        //Digitar no campo "CPF" que está dentro do formulário de id "InputCpf1" o cpf "794.727.965-91"
        //OBS: sempre que for rodar este teste tem que alterar o CPF
        navegador.findElement(By.id("InputCpf1")).sendKeys("655.403.000-09");

        //Clicar no campo "sexo" da pessoa que esta no formulario de id "RadioMasculino1" o sexo "Masculino"
        WebElement radio = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("RadioMasculino1")));
        ((JavascriptExecutor) navegador).executeScript("arguments[0].click()", radio);

        //Digitar no campo de "Data de nascimento" que esta no formulário id "InputDataNascimemto1" a data "30/07/2000"
        navegador.findElement(By.id("InputDataNascimento1")).sendKeys("30/07/2000");

        //Digitar no campo de "CEP" que esta no formulario id "InputCep1" a data "0665080"
        navegador.findElement(By.id("InputCep1")).sendKeys("06665080");
        Thread.sleep(Long.parseLong("1000"));

        //Digitar o no campo "Telefone para contato" que está no formulario id "InputTelefone1" o número "95654525254"
        navegador.findElement(By.id("InputCelular1")).sendKeys("95654525255");

        //Digitar o no campo "Número" que está no formulario id "InputNumero1" o número
        navegador.findElement(By.id("InputNumero1")).sendKeys("3021");

        //Digitar no campo "Telefone para contato" queesta no formulario id "InputTelefone1" o número "95654525254"
        navegador.findElement(By.id("InputTelefone1")).sendKeys("95654525255");

        //Digitar no campo "Complemento (Opcional)" queesta no formulario id "InputComplemento1" o número "52c"
        navegador.findElement(By.id("InputComplemento1")).sendKeys("52c");

        //Digitar no campo "Ponto de eferencia" queesta no formulario id "InputPontoReferencia1" o número "Próximo  do Mercado DIA"
        navegador.findElement(By.id("InputPontoReferencia1")).sendKeys("Próximo do Mercado DIA");

        //Clicar na caixa receber notificações
        WebElement boxSelecltTrue = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("InputOfertasPromocionais1")));
        ((JavascriptExecutor) navegador).executeScript("arguments[0].click()", boxSelecltTrue);
        Thread.sleep(Long.parseLong("1000"));

        //Clicar na caixa receber notificações
        WebElement boxSelectFalse = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("InputOfertasPromocionais1")));
        ((JavascriptExecutor) navegador).executeScript("arguments[0].click()", boxSelectFalse);

        Thread.sleep(Long.parseLong("1000"));

        ((JavascriptExecutor) navegador).executeScript("arguments[0].click()", boxSelecltTrue);

        //Clicar no Bortão "FINALIZAR CADASTRO"
        navegador.findElement(By.id("FinalizarCadastro1")).click();
    }
}