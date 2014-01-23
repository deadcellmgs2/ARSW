

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
			 throw new Exception("The Class: "+prueba.getName()+"\nis not Perfilable");
		}
		
		Object obj = prueba.newInstance();
		String[] metodos=((Perfilable)obj).getTesteableMethodName();
		int[]countable= ((Perfilable)obj).getTestCount();
		long sumt=0l;
		System.out.println("\nThe test has started \n\nPlease wait...\n");
		for (int i = 0; i < countable.length; i++) {
			long time=0l;
			time=System.currentTimeMillis();
			for (int j = 0; j < countable[i]; j++)
				prueba.getMethod(metodos[i]).invoke(obj);
			time=System.currentTimeMillis()-time;
			sumt+=time;
			System.out.println("RUNNED: "+metodos[i]+"\nTimes: "+countable[i]+"\nTime elapdsed: "+time+" milisegs\n");
		}
		System.out.println("Total test time:"+ sumt +" milisegs");
		
		
	   }catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("The class has not been found");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
//		interface
	   
}
