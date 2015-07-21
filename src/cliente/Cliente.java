package cliente;
import static Seleccion.Seleccion.Seleccion;
import java.io.*;
import java.net.*;

public class Cliente {
    
	public static void main(String[] args) throws IOException {
			
		File[] f = Seleccion();
		int count;               
		ServerSocket ss = new ServerSocket(5001);
                System.out.println("Servicio iniciado, esperando por el servidor..."+ InetAddress.getLocalHost());
                Socket cl = ss.accept();
                System.out.println("Cliente conectado desde:"+cl.getInetAddress()+":"+cl.getPort());
                ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
                oos.writeObject(f);
                oos.close();
                
		for(count = 0; count < f.length ; count++){
                        System.out.println("Servicio iniciado, esperando por el servidor...");
                        cl = ss.accept();
                        System.out.println("Cliente conectado desde:"+cl.getInetAddress()+":"+cl.getPort());
			BufferedOutputStream bos = new BufferedOutputStream(cl.getOutputStream());                   
			int leidos=0;
			int completados;
			System.out.println(f[count]);
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f[count]));
			byte[] buf = new byte[1024];
			int fin;
			int tam_bloque=(bis.available()>=1024)? 1024 :bis.available();
			int tam_arch = bis.available();
			System.out.println("tamaño archivo:"+bis.available()+ "bytes..");
			int b_leidos;
			while((b_leidos=bis.read(buf,0,buf.length))!= -1){
				bos.write(buf,0,b_leidos);
				bos.flush();
				leidos += tam_bloque;
				completados = (leidos * 100) / tam_arch;
				System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
				System.out.print("Completado:"+completados+" %");
				tam_bloque=(bis.available()>=1024)? 1024 :bis.available();
			}//while
			bis.close();
			bos.close();
		}//for
	}//main
}
