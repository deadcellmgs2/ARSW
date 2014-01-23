import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.Map.Entry;


public class Perfilador {
	public static void main(String[] args) {
	
		
		try {
			BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
			Class prueba= Class.forName(args[0]);
			
			Method[] metodos=prueba.getDeclaredMethods();
			ArrayList<Method> metodosProbables= new ArrayList<Method>();
			ArrayList<Integer> count= new ArrayList<Integer>();
			for (int i = 0; i < metodos.length; i++) {
				Annotation[] annotations=metodos[i].getDeclaredAnnotations();
				boolean perfilable=false;
				int vez=0;
				for (int j = 0; j < annotations.length && !perfilable; j++) {
					perfilable=(annotations[j].annotationType().getName().contentEquals("annotations.Perfilable"));
					if(perfilable)vez=(int) annotations[j].annotationType().getMethod("pruebas").invoke(annotations[j]);
				}
				if(perfilable){
					metodosProbables.add(metodos[i]);
					count.add(vez);
				}
			}
			if(metodosProbables.size()==0)throw new Exception("\nThe class has not perfilable methods");
			Object obj= prueba.newInstance();
			metodos= new Method[metodosProbables.size()];
			metodosProbables.toArray(metodos);
			Integer[] countable = new Integer[metodosProbables.size()];
			count.toArray(countable);
			metodosProbables.clear();
			count.clear();
			Long sumt=0L;
			System.out.println("\nThe test has started \n\nPlease wait...\n");
			for (int i = 0; i < countable.length; i++) {
				long time=0l;
				time=System.currentTimeMillis();
				for (int j = 0; j < countable[i]; j++)
					metodos[i].invoke(obj);
				time=System.currentTimeMillis()-time;
				sumt+=time;
				System.out.println("RUNNED: "+metodos[i].getName()+"\nTimes: "+countable[i]+"\nTime elapdsed: "+time+" milisegs\n");
			}
			System.out.println("Total test time:"+ sumt +" milisegs");
			
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("The class has not been found");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
}
