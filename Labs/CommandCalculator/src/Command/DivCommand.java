package Command;

import edu.eci.arsw.calculator.CalculatorModel;

public class DivCommand implements Command{
	
	Double value;
	public DivCommand( CalculatorModel model, String input) {
		super();
		this.model = model;
		this.input = input;
	}

	CalculatorModel model;
	String input;
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		double capturedValue=Double.parseDouble(input);
		double currentResult=model.getCurrentResult();
		model.setCurrentResult(currentResult/capturedValue);
		value= Double.parseDouble(input);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		model.setCurrentResult(model.getCurrentResult()*value);
	}

}
