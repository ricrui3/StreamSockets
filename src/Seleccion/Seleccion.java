package Seleccion;

import java.io.File;
import javax.swing.JFileChooser;

public class Seleccion{
    
    public static File[] Seleccion(){
        int count  = 0;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        int resultado = fileChooser.showOpenDialog(null);
        if(resultado==JFileChooser.APPROVE_OPTION){
            File[] f = fileChooser.getSelectedFiles();
            System.out.println("No. archivos seleccionados: "+ f.length);
            for(count = 0;count < f.length;count++){
                if(f[count].length() < 1024)//bytes
                    System.out.println("\n" + (count+1) +".- Archivo seleccionado: "+ f[count] + " " + f[count].length()+ " bytessss");
                else
                    if(f[count].length()/1024 < 1024)//kbytes
                        System.out.println("\n" + (count+1) +".- Archivo seleccionado: "+ f[count] + " " + f[count].length()/1024 + " kbytessss");
                    else//Mbytes
                        System.out.println("\n" + (count+1) +".- Archivo seleccionado: "+ f[count] + " " + (f[count].length()/1024)/1024 + " Mbytesss");
            }
            return f;
        }
        return null;
    }//constructor

}