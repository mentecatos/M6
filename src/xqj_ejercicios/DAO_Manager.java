package xqj_ejercicios;



public class DAO_Manager {
	private static DAO_EjerciciosXQJ xqjDAO;


	public static DAO_EjerciciosXQJ getSentenciaDAO() {
		if(xqjDAO == null){
			xqjDAO = new Ejercicios_XQJ_dao();
		}
		return xqjDAO;
	}
}

