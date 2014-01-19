

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import Interfaces.Perfilable;
/**
 * 
 * @author SEBASTIAN
 *
 */
public class Perfilador {
	public static void main(String[] args) {
	   try {
		   
		 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
		Class prueba=Class.forName(args[0]);
		Class[] interfaces=prueba.getInterfaces();
		
		Boolean isPerfilable=false;
		for (int i = 0; i < interfaces.length && !isPerfilable; i++){
			isPerfilable=(interfaces[i].getName()== "Interfaces.Perfilable");		
		}
		
		if(!isPerfilable){
			 throw new Exception("La clase: "+prueba.getName()+"\nNo es Perfilable");
		}
		
		
//		interface
		HashMap<String, Integer> hash=new HashMap<String,Integer>();
		Long sumt=0L;
		String confirm="";
		System.out.print("\nDesea iniciar la prueba Automatica(s/n):");
		
		while(!(confirm.toLowerCase().contentEquals("s")||confirm.toLowerCase().contentEquals("n"))){
			confirm=reader.readLine();
		}
		
		Object obj=prueba.newInstance();
		String[] metodos=((Perfilable)obj).getTesteableMethodName();
		int[] countable=((Perfilable)obj).getTestCount();
		
		if(confirm.contentEquals("s")){
			System.out.println("Espere por favor.....");
			sumt=automatic(prueba,hash,reader,obj,metodos,countable);
		}else{
			
			sumt=interfaz(prueba,hash,reader,obj,metodos,countable);
		}
		System.out.println("\n================================================================");
		System.out.println("RESULTADOS -CLASE: "+prueba.getName());
		System.out.println("================================================================");
		Set<Entry<String,Integer>>set=hash.entrySet();
		Entry[] arreglo= new Entry[hash.size()];
		set.toArray(arreglo);
		System.out.println("Metodos Probados:\n");
		for (int i = 0; i < arreglo.length; i++) {
			System.out.println(arreglo[i].getKey()+":"+arreglo[i].getValue() + (((Integer)arreglo[i].getValue()>1)?" Veces Probado":" Vez Probado"));
		}
		System.out.println("\nTiempo Total:"+((double)sumt/1000)+" Segundos");
	   } catch (Exception e) {
		// TODO Auto-generated catch block
		 System.out.println(e.getMessage());
	   }
	   
	}
/**
 * 
 * @param prueba
 * @param hash
 * @param reader
 * @param obj
 * @param metodos
 * @param countable
 * @return sumt
 * @throws IllegalAccessException
 * @throws IllegalArgumentException
 * @throws InvocationTargetException
 * @throws NoSuchMethodException
 * @throws SecurityException
 */
	public static Long automatic(Class prueba, HashMap<String, Integer> hash,
			BufferedReader reader, Object obj, String[] metodos, int[] countable) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Long sumt=0L;
		for (int i = 0; i < countable.length; i++) {
			for (int j = 0; j < countable[i]; j++) {
				Long x=System.currentTimeMillis();
				prueba.getMethod(metodos[i], null).invoke(obj, null);
				x=System.currentTimeMillis()-x;
				sumt+=x;
				
			}
			hash.put(metodos[i], countable[i]);
		}
		
		return sumt;
	}
	/**
	 * 
	 * @param prueba
	 * @param hash
	 * @param reader
	 * @param obj
	 * @param metodos
	 * @param countable
	 * @return sumt
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static long interfaz(Class prueba, HashMap<String, Integer> hash, BufferedReader reader, Object obj, String[] metodos, int[] countable) throws InstantiationException, IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		
		Long sumt=0l;
		String confirmacion="";
		
		while(!confirmacion.toLowerCase().contentEquals("no")){
			System.out.println("================================================================");
			System.out.println("CLASE A PROBAR:" + prueba.getName());
			System.out.println("================================================================");
			String metodo=view(metodos,reader);
			long current=System.currentTimeMillis();
			
			System.out.print("\nIngrese el numero de veces de la prueba:");
			confirmacion=reader.readLine();
			
			long n=Integer.parseInt(confirmacion);
		  
			
			Method metod=prueba.getMethod(metodo, null);
			System.out.println("Espere por favor.....");
			for (int i = 0; i < n; i++) {
				metod.invoke(obj, null);
			}
			current=System.currentTimeMillis()-current;
			sumt+=current;
		
			System.out.println("\nTiempo gastado:"+((double)current/1000)+" segundos en una prueba de "+n+" veces.");
			if(hash.containsKey(metodo)){
				Integer x = hash.get(metodo);
				hash.remove(metodo);
				hash.put(metodo, (int) (x+n));
			}else{
				hash.put(metodo, (int) n);
			}
			System.out.print("\nDesea continuar (si/no):");
			confirmacion=reader.readLine();
			
			
			while(!(confirmacion.toLowerCase().contentEquals("si") || confirmacion.toLowerCase().contentEquals("no")))confirmacion=reader.readLine();
		}
		return sumt; 	
	}
	/**
	 * 
	 * @param metodos
	 * @param reader
	 * @return metodo
	 * @throws IOException
	 */
	public static String view(String[] metodos, BufferedReader reader) throws IOException {
		System.out.println("\nMetodos de Probables para la clase:");
		for (int i = 0; i < metodos.length; i++) {
			System.out.println(metodos[i]);
		}
		System.out.print("\nCual metodo desea Probar?: ");
		String metodo=reader.readLine();
		boolean isMetod=false;
		while(!isMetod){
			for (int i = 0; i < metodos.length && !isMetod; i++) {
			  isMetod=(metodos[i].contentEquals(metodo));
			}
			if(!isMetod){
				System.out.print("\n\t-El metodo no se encontro\nReingrese el nombre del metodo:");
				metodo=reader.readLine();
			}
		}
		
		return metodo;
	}
}
