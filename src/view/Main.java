package view;
import javax.swing.JOptionPane;
import controller.RedesController;

public class Main {

	public static void main(String[] args) { 

		RedesController cont = new RedesController();
		
		int opc = 0;
		
		
		 do{
			 
			 opc = Integer.parseInt(JOptionPane.showInputDialog("Qual Opção vc gostaria?\n 1 - Encontrar os Adaptadores com IPv4\n 2 - Descobrir o tempo medio do ping\n 9 - Finalizar Programa"));
			 
			switch(opc) {
			case 1: cont.ip();
				break;
			case 2: cont.ping();
				break;
			case 9: JOptionPane.showMessageDialog(null,"Programa finalizado");
				break;
			default: JOptionPane.showMessageDialog(null,"Opcao Invalida, tentar novamente");
				break;
			}
		} while(opc != 9);
	}

}
