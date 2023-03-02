package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}
	
	//Identificando o sistema operacional
	public String os() {
		
		String os = System.getProperty("os.name");
		String[] vtSO = os.split(" ");
		
		if(os.contains("Windows")) {
			os = vtSO[0];
		} else {
			if(os.contains("Linux")) {
				os = vtSO[0];
			}
		}
		
		return os;
	}
	
	//Faz a chamada do IP e printa se o adaptador tiver IPv4
	public void ip() {
		
		String codip = os();
		String adaptador = "";
		String IPv4 = "";
		
		if(codip.contains("Windows")) {
			codip = "ipconfig";
		} else {
			if(codip.contains("Linux")) {
				codip = "ifconfig";
			}
		}
		
		
		try {
			Process p = Runtime.getRuntime().exec(codip);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo); 
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			while (linha != null) {
				if(linha.contains("Adaptador")){
					adaptador = linha;
				} else {
					if(linha.contains("IPv4")){
						System.out.println(adaptador + "\n" + linha);
					}
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Descobre  o tempo médio do ping
	public void ping () {
		String sistemas = os();
		String codping = "";
		
		
		if(sistemas.contains("Windows")) {
			codping = "PING -4 -n 10 www.google.com.br";
		} else {
			if(sistemas.contains("Linux")) {
				codping = "PING -4 -c 10 www.google.com.br";
			}
		}
		
		try {
			Process p = Runtime.getRuntime().exec(codping);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo); 
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			while(linha != null) {
				if(sistemas.contains("Windows")) {
					if(linha.contains("dia")) {
						String vt[] = linha.split(" = ");
						System.out.println("Media eh " + vt[3]);
					}
				}
				if(sistemas.contains("Linux")) {
					if(linha.contains("rtt")) {
						String vt[] = linha.split("/");
						System.out.println("Media eh " + vt[4]);
					}
				}
				linha = buffer.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
