package tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class validateErrorMessage {
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
    public void MenssergeTheError() throws InterruptedException {
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

        navegador.findElement(By.linkText("CADASTRAR")).click();
        Thread.sleep(Long.parseLong("3000"));

        //Validar se a mensagem de erro está "Informe o nome" quando o campo "Nome" está sem preencher
        navegador.findElement(By.id("FinalizarCadastro1")).click();

        WebElement messagemErroNome = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Informe o nome')]"));
        String textoElementNome = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Informe o nome')]")).getText();
        assertEquals("Informe o nome", textoElementNome);

        //Validar se a mensagem de erro está "Informe o sobrenome" quando o campo "Sobrenome" esta sem preencher
        WebElement messagemErroSobrenome = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Informe o sobrenome')]"));
        String textoElementSobreNome = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Informe o sobrenome')]")).getText();
        assertEquals("Informe o sobrenome", textoElementSobreNome);

        //Validar se a mensagem de erro está "Informe um e-mail válido" quando o campo "E-mail" esta sem preencher
        WebElement messagemErroEmail = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Informe o sobrenome')]"));
        String textoElementEmail = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Informe um e-mail válido')]")).getText();
        assertEquals("Informe um e-mail válido", textoElementEmail);

        //Validar se a mensagem de erro está "Informe uma senha" quando o campo "Senha" esta sem preencher
        WebElement messagemErroSenha = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Informe uma senha')]"));
        String textoElementSenha = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Informe uma senha')]")).getText();
        assertEquals("Informe uma senha", textoElementSenha);

        //Validar se a mensagem de erro está "Confirme a senha" quando o campo "Confirme a senha" esta sem preencher
        WebElement messagemErroConfirmeSenha = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Confirme a senha')]"));
        String textoElementConfirmeSenha = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Confirme a senha')]")).getText();
        assertEquals("Confirme a senha", textoElementConfirmeSenha);

        //Validar se a mensagem de erro está "Informe um CPF válido" quando o campo "CPF" esta sem preencher
        //OBS a mensagem "Informe um CPF" A mensagem tem um erro de português. A mensagem está da seguinte forma "Inform um CPF válido
        WebElement messagemErroCpf = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Inform um CPF válido')]"));
        String textoElementCpf = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Inform um CPF válido')]")).getText();
        assertEquals("Inform um CPF válido", textoElementCpf);

        //Validar se a mensagem de erro está "Escolha uma opção" quando o campo "Sexo" esta sem preencher
        WebElement messagemErroSexo = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Escolha uma opção')]"));
        String textoElementSexo = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Escolha uma opção')]")).getText();
        assertEquals("Escolha uma opção", textoElementSexo);

        //calidar se a mensagem de erro está "Informe uma data válida" quando o campo "Data de nascimento" esta sem preencher
        WebElement messagemErroDataNascimento = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Informe uma data válida')]"));
        String textoElementDataNascimento = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Informe uma data válida')]")).getText();
        assertEquals("Informe uma data válida", textoElementDataNascimento);

        //calidar se a mensagem de erro está "Informe um número de telefone válido" quando o campo "Telefone" esta sem preencher
        WebElement messagemErroTelefone = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Informe um número de telefone válido')]"));
        String textoElementTelefone = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Informe um número de telefone válido')]")).getText();
        assertEquals("Informe um número de telefone válido", textoElementTelefone);

        //calidar se a mensagem de erro está "Informe um CEP válido" quando o campo "CEP" esta sem preencher
        WebElement messagemErroCep = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Informe um CEP válido')]"));
        String textoElementCep = navegador.findElement(By.xpath("//small[@class='error' and contains(text(),'Informe um CEP válido')]")).getText();
        assertEquals("Informe um CEP válido", textoElementCep);
    }
}