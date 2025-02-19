import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class NavegadorSite {
	private final DefaultHttpClient client = new DefaultHttpClient();

	/**
	 * * Efetua login no site * @param url - URL de Login do site * @param user
	 * - usuario * @param password - senha * @return true - login ok | false -
	 * login fail * @throws UnsupportedEncodingException * @throws IOException
	 */
	public boolean login(final String url, final String user,
			final String password) throws UnsupportedEncodingException,
			IOException { /* M�todo POST */
		final HttpPost post = new HttpPost(url);
		boolean result = false;
		/* Configura os par�metros do POST */
		final List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("login", user));
		nameValuePairs.add(new BasicNameValuePair("password", password));
		nameValuePairs.add(new BasicNameValuePair("remember_me", "0"));
		/*
		 * * Codifica os parametros.
		 * 
		 * Antes do encoder: fulano@email.com Depois do enconder:
		 * fulano%40email.com
		 */

		post.setEntity(new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8));
		/* Define navegador */

		post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1;rv:18.0) Gecko/20100101 Firefox/18.0");
		/* Efetua o POST */HttpResponse response = client.execute(post);

		/*
		 * Resposta HTTP: Sempre imprimir� �HTTP/1.1 302 Object moved� (no caso
		 * da devmedia)
		 */System.out.println("Login form get: " + response.getStatusLine());
		/*
		 * * Consome o conte�do retornado pelo servidor * Necess�rio esvaziar o
		 * response antes de usar o httpClient novamente
		 */EntityUtils.consume(response.getEntity());
		/*
		 * * Testar se o login funcionou. * * Estrat�gia: acessar uma p�gina que
		 * s� est� dispon�vel quando se est� logado * Em caso de erro, o
		 * servidor ir� redirecionar para a p�gina de login * A pagina de login
		 * contem uma string: "Login DevMedia" * Se esta String estiver
		 * presente, significa que o login n�o foi efetuado com sucesso *
		 */final HttpGet get = new HttpGet(
				"http://fumec.bv3.digitalpages.com.br/users/sign_in");
		 		
		 
		
		response = client.execute(get);
			//saveHTLM(response);
		/* * Verifica se a String: "Login DevMedia" est� presente */if (checkSuccess(response)) {
			System.out.println("Conexao Estabelecida!");

			result = true;

		} else {
			System.out.println("Login n�o-efetuado!");

		}
		return result;

	}

	/** * Abre p�gina * @param url - P�gina a acessar * @throws IOException */
	public void openPage(final String url) throws IOException {
		final HttpGet get = new HttpGet(url);

		final HttpResponse response = client.execute(get);
		System.out.println("  {{{{{{{ " + response.getStatusLine());
		saveHTLM(response);

	}

	/** * Encerra conex�o */
	public void close() {
		client.getConnectionManager().shutdown();

	}

	/**
	 * * Busca por String que indica se o usu�rio est� logado ou n�o * @param
	 * response * @return true - N�o achou String | false - Achou String * @throws
	 * IOException
	 */
	private boolean checkSuccess(final HttpResponse response)
			throws IOException {
		final BufferedReader rd = new BufferedReader(new InputStreamReader(
				response.getEntity().getContent()));

		String line;

		boolean found = false;

		/*
		 * Deixa correr todo o laco, mesmo achando a String, para consumir o
		 * content
		 */while ((line = rd.readLine()) != null) {
			if (line.contains("Login DevMedia")) {
				found = true;
			}
		}
		return !found;

	}

	/** * Salva a p�gina * @param response * @throws IOException */
	private void saveHTLM(final HttpResponse response) throws IOException {
		final BufferedReader rd = new BufferedReader(new InputStreamReader(
				response.getEntity().getContent()));
		String line;
		
		File arquivo = new File("c:\\arquivo.html");

		PrintWriter writer = new PrintWriter(arquivo);

		while ((line = rd.readLine()) != null) {
			System.out.println(line);

			writer.println(line);

		}
		writer.flush();

		writer.close();

	}

	/** * Roda aplica��o * @param args */
	public static void main(String[] args) {
		NavegadorSite navegador = new NavegadorSite();
		try {

			boolean ok = navegador.login(
					"http://fumec.bv3.digitalpages.com.br/users/sign_in", "224731971",
					"220718");
			if (ok) {
				// Acessa p�gina restrita
			navegador.openPage("http://fumec.bv3.digitalpages.com.br/users/publications/9788544300312/pages/5");
				navegador.close();
			}

			navegador.close();

		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
