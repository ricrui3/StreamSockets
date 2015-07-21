package servidor;
import java.net.*;
import java.io.*;

public class Servidor {
	
	public static void main(String[] args) throws Exception{
		int count;
                File[] f;
               
                //Instauracion de el socket
		Socket cl = new Socket(InetAddress.getByName("127.0.0.1"),5001);
		System.out.println("Servidor conectado..\n recibiendo nombres..");
                
                //Recibimiento del objeto con los nombres de los archivos a recibir
                ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
                f = (File[])ois.readObject();
                ois.close();
                //-----------------------------------------------------------------
                
                System.out.println("No de archivos: "+ f.length);
                
		//
                for(count = 0; count < f.length; count++){
                    
                    cl = new Socket( InetAddress.getByName( "127.0.0.1" ) , 5001 );
                    System.out.println("Servidor conectado.. recibiendo archivo no: "+ count +"\nNombre del Archivo: "+f[count].getName());
                    
                    BufferedInputStream bis = new BufferedInputStream(cl.getInputStream());
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f[count].getName()));
                    
                    //cl.setSoTimeout(3000);
                    
                    byte[] buf = new byte[1024];
                    int leidos;
                    while((leidos = bis.read(buf,0,buf.length))!=-1){
                        
                            bos.write(buf,0,leidos);
                            bos.flush();
                            
                    }//while
                    
                    System.out.println("Archivo copiado....");
                    bis.close();
                    bos.close();
		}
	}//main
	
}
