package edu.eci.arsw.calculator;

import java.io.IOException;
import java.util.Iterator;

import org.acme.utils.CalculatorScriptDataLoader;
import org.acme.utils.Operation;

import reader.WrongFormatException;

public class CalculatorExcelScriptDataLoaderAdapter extends CalculatorScriptDataLoader{
	ExcelDataLoader loader;

	public CalculatorExcelScriptDataLoaderAdapter(ExcelDataLoader loader) {
		super();
		this.loader = loader;
	}
	
	public Iterator<Operation> loadOperationsSet(){
		Iterator<Operation> ops=null;
		
			ops= loader.load();
		
		
		return ops;
	}
	
}
