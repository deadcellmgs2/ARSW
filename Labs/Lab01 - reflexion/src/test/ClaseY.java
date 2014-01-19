package test;

import java.lang.reflect.Method;
import java.util.ArrayList;

import Interfaces.Perfilable;

public class ClaseY implements Perfilable{

	
	public void op2(){
		
	}	
	@Override
	public String[] getTesteableMethodName() {
		// TODO Auto-generated method stub
		Class clase=this.getClass();
		
		Method[] metodos=clase.getMethods();
		
		
		ArrayList<String> listado = new ArrayList<String>();
		for (int i = 0; i < metodos.length; i++) {
			if(metodos[i].getParameterTypes().length==0){
				listado.add(metodos[i].getName());
			}
		}
		
		
		return (String[])listado.toArray();
	}

	@Override
	public int[] getTestCount() {
		// TODO Auto-generated method stub
		int[] ret= new int[this.getTesteableMethodName().length];
		for (int i = 0; i < ret.length; i++) {
			ret[i]=i+1;
		}
		return ret;
	}

}
