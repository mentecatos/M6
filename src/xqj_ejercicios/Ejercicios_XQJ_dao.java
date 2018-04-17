package xqj_ejercicios;

import java.io.File;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

public class Ejercicios_XQJ_dao implements DAO_EjerciciosXQJ{

	@Override
	public void ejercicio1() {
		XQConnection conexio;
		try{
			conexio = new Connexio().getConnection();
			//Preparem la consulta
			String squery = "for $nombre in /zonas/zona"+
					"\nfor $deno  in /productos/produc[cod_zona=$nombre/codi_zona]/denominacion"+
					"\nfor $price in /productos/produc[cod_zona=$nombre/codi_zona]/precio"+
					"\norder by $nombre/nombre"+
					"\nreturn ($deno,$price,$nombre/nombre)";
			XQPreparedExpression consulta = conexio.prepareExpression(squery);
			//Executem
			XQResultSequence resultat = consulta.executeQuery();
			//Recorrem els elements del resultat
			System.out.println("EJERCICIO 1 :\n");

			while(resultat.next()){

				System.out.println(resultat.getItemAsString(null));
			}
			resultat.close();
		}catch (XQException e){
			e.printStackTrace();
		}


	}

	@Override
	public void ejercicio2(int stockminimo) {
		XQConnection connexio;
		try{
			connexio = new Connexio().getConnection();
			String squery = "for $prod in /productos/produc[stock_minimo >"+String.valueOf(stockminimo)+"]"
					+ "\nreturn ($prod)";
			XQPreparedExpression consulta = connexio.prepareExpression(squery);
			//Executem
			XQResultSequence resultat = consulta.executeQuery();
			//Recorrem els elements del resultat
			System.out.println("EJERCICIO 2 :\n");
			while(resultat.next()){

				System.out.println(resultat.getItemAsString(null));
			}
			resultat.close();
		}catch (XQException e){
			e.printStackTrace();
		}
		}



	@Override
	public void ejercicio3() {
		XQConnection connexio;
		try{
			connexio = new Connexio().getConnection();
			String squery = "for $zona in /zonas/zona"
					+ "\nreturn data($zona/codi_zona)";
			XQPreparedExpression consulta = connexio.prepareExpression(squery);
			//Executem
			XQResultSequence resultat = consulta.executeQuery();
			//Recorrem els elements del resultat
			System.out.println("EJERCICIO 3 :\n");
			String nombreZonaConsulta = "for $zona in /zonas/zona"
					+ "\nreturn data($zona/nombre)";
			XQPreparedExpression consultaZona = connexio.prepareExpression(nombreZonaConsulta);
			XQResultSequence resultadoZona = consultaZona.executeQuery();
			while(resultat.next() && resultadoZona.next()){
				String codigoZona = resultat.getItemAsString(null);
				String productos = "<prodzona"+codigoZona+">\n";
				String nombreZona = resultadoZona.getItemAsString(null);
				productos += "<nom_zona>"+nombreZona+"</nom_zona>\n";
				File fichero = new File("ProdZona"+codigoZona+".xml");
				String prod = "for $codiProd in /productos/produc[cod_zona="+codigoZona+"]/cod_prod"
						+ "\nfor $deno in /productos/produc[cod_zona="+codigoZona+"]/denominacion"
								+ "\nreturn <produc>{$codiProd,$deno}</produc>";
				XQPreparedExpression consultarProductos = connexio.prepareExpression(prod);
				XQResultSequence resultadoProductos = consultarProductos.executeQuery();
				while (resultadoProductos.next()){
					productos += "\t"+resultadoProductos.getItemAsString(null)+"\n";
				}
				productos += "</prodzona"+codigoZona+">";
				EscribirFichero escritor = new EscribirFichero();
				escritor.escribir(productos, fichero);
			}
			resultat.close();
		}catch (XQException e){
			e.printStackTrace();
		}

	}

}
