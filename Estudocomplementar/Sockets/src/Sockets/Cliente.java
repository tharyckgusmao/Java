package Sockets;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Iniciando cliente.");
		System.out.println("iniciando conexao com o serv.");
		
		
		Socket socket = new Socket("localhost",2525);
		
		System.out.println("Conexao ok.");
		
		InputStream input = socket.getInputStream();
		OutputStream output = socket.getOutputStream();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		PrintStream out = new PrintStream(output);
		
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.println("Digite uma mensagem: ");
			String mensagem = scanner.nextLine();
			
			out.println(mensagem);
			
			if("FIM".equals(mensagem)){
				break;
				
			}
			
			
			mensagem = in.readLine();
			
			System.out.println("Mensagem recebida do servidor"+mensagem);
			
			
			
		}
		
		System.out.println("Encerrando conexao");
		
		
		in.close();
		out.close();
		socket.close();
	}
	
}
