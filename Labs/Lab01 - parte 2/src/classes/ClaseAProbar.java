package classes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

import annotations.Perfilable;



public class ClaseAProbar {

	@Perfilable(pruebas=5)
	public void metodoUno(){
		try {
			Thread.sleep(new Random().nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

	@Perfilable(pruebas=3)
	public void metodoDos(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}				
		
	}

	@Perfilable(pruebas=3)
	public void metodoTres(){
			int x=0;
			for (int i=0;i<99999999;i++){
				x=x*x;
			}
	}
	@Perfilable(pruebas=8)
	public void metodoCuatro(){
		int x=0;
		for (int i=0;i<99999999;i++){
			x=x+x;
		}		
	}


}
