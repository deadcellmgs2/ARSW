package edu.eci.arsw.calculator;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.acme.utils.Operation;

import reader.ExcelReader;
import reader.WrongFormatException;

public class ExcelDataLoader {
	
	String ruta;
	
	public ExcelDataLoader(String ruta) {
		this.ruta = ruta;
			
	}

	public Iterator<Operation> load() {
		List<Operation> it= new ArrayList<Operation>();
		try{
		ExcelReader reader = new ExcelReader();
		List<String> data=reader.read(ruta);
		for (String datai:data){
			String[] vec=datai.split(",");
			String operator=vec[0];
			double value=Double.parseDouble(vec[1]);
			it.add(new Operation(operator, value));
		}
	   }catch(Exception e){}
		return it.iterator();
	}
}
