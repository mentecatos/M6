package xqj_ejercicios;



public class MainXQJ_ejercicios {

	public static void main(String[] args){
		DAO_EjerciciosXQJ sentencias = DAO_Manager.getSentenciaDAO();
		sentencias.ejercicio1();
		sentencias.ejercicio2(10);
		sentencias.ejercicio3();

	}


}
