package xqj_ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirFichero {
	public void escribir(String cadena, File fichero){
		FileWriter escritor = null;
		try{
			escritor = new FileWriter(fichero);
			escritor.write(cadena);
		}catch (FileNotFoundException e){
			System.out.println("El fichero "+fichero+"no existe");
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(escritor!=null){
				try{
					escritor.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
