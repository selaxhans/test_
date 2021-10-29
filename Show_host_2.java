package gruppovning_1_metodDisplay;
/*
 * forsta forsok till dellösning tre i rad

 * en metod att visa upp spelplan etc
 * 
 * 
 * 
 * 
 * 
 * 
 */
import java.util.*;

import javax.swing.JOptionPane;


public class Show_host {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] spel = new int[10];    //index anger ruta pss som spelplan index =0 används för att räkna totalt antal upptagna rutor;
		String[] lirare = {"tom","kryss","ringar"};
		int spelarval = 1;			//spelare lirare 1 eller 2;
		String sv = "";
		boolean istreirad= false;
		
		/* Fötsta ansats;
		 * spelplanen innehåller nio rutor; dessa kan vara i tre olika tillstånd; O,X eller tomma;
		 * arrayens positioner tolkas så här;
		 * tom ruta =0;
		 * "X"      =1;
		 * "O"      =2;
		 * När metoden anropas, raderar den tidigare och ritar den nya i dess ställe;
		 * Index till arrayen motsvarar positioner enligt den uppritade definitionen;
		 * metoden anropas med arrayen spel som parameter.
		 * För arr kunna göra ett drag genom angivande av Ett nummer måste spelplanen med koder visas.
		 * 
		 *  1. Gör det Visa koder och tom spelplan;
		 *  
		 */
		
		for (int i =0; i !=10; i++)
		{spel[i] =0;}
		//Lirare X börjar...
		
		ritaplan(spel);
		 spelarval = 1;
		
		
		
		/*
		 * spelarna kryss och ringar, kan nu placera ut markeringar i tomma rutor;
		 * För varje inmatning skall först spelare väljas och efter ingivet val kontroll ske av
		 *  att indata är giltigt.att en ledig ruta valts; därefter ritas planen om med nya val och kontroll skal ske
		 * att inge spelare har fått tre i rad.
		 * Därefter växlas spelare och proceduren upprepas.
		 * Om någon får tre i rad meddelas det och spelet avbryt.
		 * 
		 */
		spelarval = 1;
		int target;	
		while(!istreirad)
		{
		sv = "";
		
		target=0;			//tavelrutanummer
		
		while (sv.length()==0)
		{			
		/*
		 * Snurra runt med samma spelare tills giltigt drag gjorts 	
		 */
		sv =JOptionPane.showInputDialog("spelare: "+ lirare[spelarval] + "  Ange med ett nummer 1..9 var du vill sätta ditt drag   'S' avslutar");
		if (sv.equals("S"))
		System.exit(0);
		
		
		
		try
		{
		 target = Integer.parseInt(sv);
		}
		catch (Exception ex)
		{
			sv="";
			target =0;
		}
		if((target==0)|(target>9)|(sv.length()>1))            
		{
			sv="";
			target =0;
		}
		// kolla tom ruta
		
		if(spel[target] != 0)
		{
			sv="";
			target =0;
			JOptionPane.showInternalMessageDialog(null,"Rutan upptagen försök igen..");
		}
		else
		{
		spel[target]= spelarval;
		spel[0]++;
		ritaplan(spel);
		
		istreirad = kollatreirad(spel,spelarval);
		if (istreirad)
		{
		JOptionPane.showInternalMessageDialog(null,"Spelare:  "+ lirare[spelarval] + "  vann denna rond");
		 System.exit(0);
		};
		
		if (spelarval ==1)
			spelarval=2;
		else
			spelarval =1;
		
		if (spel[0] == 9)
		{
			JOptionPane.showInternalMessageDialog(null,"Oavgjort, inga tomma rutor kvar men inte tre i rad");
			 System.exit(0);
			
		}
		
		}
		}
		}
	}
		
		
		
				

	

		private static boolean kollatreirad(int[] marks,int spelare) {
			boolean israd3=false; // är tre i rsd
			boolean [] ismarked = new boolean[10]; //ruta markerad för aktuell spelare
			int wrk =0; //slaskvariabel, temporär
			
		/*
		 * minsta antal markeringar för möjligt tre i rad är fem;
		 * Om tre i rad finns, är det den aktuella liraren som vinner;
		 * tre i rad kan uppstå på åtta olika sätt; undersök alla;
		 * 	
		 */
			if (marks[0] >=5)
			{
			int [] check = new int[10];				// kopiera 	för att inte förlora data;
			for (int i =1; i< 10;i++ )
			{
				wrk = marks[i];						// här sorteras fram de rutor som kan leda till vinst nu!
				if (wrk == spelare)					// sedan  bortses från motspelares och tomma rutor;
					ismarked[i] = true;				//nu kan logiska villkor lätt formuleras !!
													//för alla åtta fallen av tre i rad
			}
			
			if (((ismarked[1]&ismarked[2])&(ismarked[3]))|		// horisontell rad1
				((ismarked[4]&ismarked[5])&(ismarked[6]))|		// horisontell rad2
				((ismarked[7]&ismarked[8])&(ismarked[9]))|		// horisontell rad3
				((ismarked[1]&ismarked[4])&(ismarked[7]))|		// vertikal column1
				((ismarked[2]&ismarked[5])&(ismarked[8]))|		// vertikal column2
				((ismarked[3]&ismarked[6])&(ismarked[9]))|		// vertikal column3
				((ismarked[1]&ismarked[5])&(ismarked[9]))|		// diagonal 1
				((ismarked[3]&ismarked[5])&(ismarked[7])))		// diagonal 2
				israd3 = true;
				
			
			}
		 
			
		return israd3;
	}


public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
}



		public static void ritaplan(int[] spelad)
		{
			
		String[] mark = new String[10];
			
		String[] horis = {"--------------------------",   //0
						  "    1   |   2   |   3     ", //1
						  "    4   |   5   |   6     ",  //2
						  "    7   |   8   |   9     ", //3
						  "--------|-------|---------",//4
						  "        |       | 	  " };//5

		int wrk =0;
		for (int i =1; i< 10; i++ )
		{
		wrk = spelad[i];
		if (wrk ==1)
			mark[i] = "X";
		if (wrk ==2)
			mark[i] = "O";
		if (wrk ==0)
			mark[i]= " ";
		}
		
		
		
		String sp5 ="     ";
		String spx ="    ";
		String spy ="   |   ";
		
		clearScreen();
		
		System.out.println(" ");
		System.out.println(horis[5]+ sp5 + horis[5]);
		System.out.println(horis[1]+sp5+spx+mark[1]+spy+mark[2]+spy + mark[3]);
		System.out.println(horis[5]+ sp5 + horis[5]);
		System.out.println(horis[4]	+ sp5 + horis[4]);
		System.out.println(horis[5]+ sp5 + horis[5]);
		System.out.println(horis[2]+sp5+spx+mark[4]+spy+mark[5]+spy + mark[6]);   
		System.out.println(horis[5]	+ sp5 + horis[5]);
		System.out.println(horis[4]	+ sp5 + horis[4]);
		System.out.println(horis[5]+ sp5 + horis[5]);
		System.out.println(horis[3]+sp5+spx+mark[7]+spy+mark[8]+spy + mark[9]);
		System.out.println(horis[5]	+ sp5 + horis[5]);
		System.out.println(horis[4]+ sp5 + horis[4]);
		System.out.println(horis[5]+ sp5 + horis[5]);
		System.out.println("       DEFINITIONER                     SPELPLAN");
		
		}	
	
	
}
		
		
	
		
		
		
		
		

