/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author DevLuu
 */
public class Ticket {
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YYYY");
    String fecha = formato.format(new Date());
    
    int hora = LocalTime.now().getHour();
    int minute = LocalTime.now().getMinute();
    int numeroAleatorio = (int) (Math.random()*10000+1);
    
    public void creaTicket(){
        File f;
        FileWriter w;
        BufferedWriter bw;
        PrintWriter wr;
        
        try {
            f = new File("ticket.txt");
            w = new FileWriter(f);
            bw = new BufferedWriter(w);
            wr = new PrintWriter(bw);
            
            wr.write("*****************************************");
            wr.write("\n           COMPROBANTE DE PAGO         ");
            wr.append("\n           CONSULTA DE MULTAS");
            wr.append("\n*****************************************\n");
            wr.append("\nFECHA:"+fecha);
            wr.append("\nHORA: "+hora+":"+minute);
            wr.append("\nFOLIO: 00"+numeroAleatorio);
            wr.append("\n-----------------------------------------\n");
            wr.append("\nPAGO REALIZADO EN LA APP:");
            wr.append("\nSistema de consulta de multas");
            wr.append("\n");
            wr.append("\nIMPORTE: $1200");
            wr.append("\n-----------------------------------------");
            wr.append("\n              DevSolutions");
            wr.append("\n          devsolution@gmail.com");
            wr.append("\n           Tel: 55-33-22-11-00");
            wr.append("\n-----------------------------------------");
            wr.close();
            bw.close();  
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }   
}
