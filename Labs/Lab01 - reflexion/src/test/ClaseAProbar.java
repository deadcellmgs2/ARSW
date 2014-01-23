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
		String[] retorno={"metodoUno","metodoDos","metodoCuatro"};
		return  retorno;
	}

	@Override
	public int[] getTestCount() {
		// TODO Auto-generated method stub
		int[] retorno={10,2,15};
		return retorno;
	}


	
}