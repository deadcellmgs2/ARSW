package test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

import Interfaces.Perfilable;


public class ClaseAProbar implements Perfilable{

	
	public void metodoUno(){
		try {
			Thread.sleep(new Random().nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

	
	public void metodoDos(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}				
		
	}

	public void metodoTres(){
			int x=0;
			for (int i=0;i<99999999;i++){
				x=x*x;
			}
	}

	public void metodoCuatro(){
		int x=0;
		for (int i=0;i<99999999;i++){
			x=x+x;
		}		
	}


	@Override
	public String[] getTesteableMethodName() {
		// TODO Auto-generated method stub
		Class clase=this.getClass();
		
		Method[] metodos=clase.getDeclaredMethods();
		
		
		ArrayList<String> listado = new ArrayList<String>();
		for (int i = 0; i < metodos.length; i++) {
			if(metodos[i].getParameterTypes().length==0 && !(metodos[i].getName()=="getTestCount" 
					|| metodos[i].getName()=="getTesteableMethodName")){
				listado.add(metodos[i].getName());
			}
		}
		String[] retorno = new String[listado.size()];
		
		listado.toArray(retorno);
		return retorno;
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