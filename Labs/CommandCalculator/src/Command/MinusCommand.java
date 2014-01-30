package Command;

import edu.eci.arsw.calculator.CalculatorModel;

public class MinusCommand implements Command{
	Double value;
	CalculatorModel model;
	String input;
	
	public MinusCommand(CalculatorModel model, String input) {
		super();
		this.model = model;
		this.input = input;
	}

	@Override
	public void execute() {
		double capturedValue=Double.parseDouble(input);
		double currentResult=model.getCurrentResult();
		model.setCurrentResult(currentResult-capturedValue);
		value= Double.parseDouble(input);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		model.setCurrentResult(model.getCurrentResult()+value);
	}

}
