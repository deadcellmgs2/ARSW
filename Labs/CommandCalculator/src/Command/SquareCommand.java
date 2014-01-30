package Command;

import edu.eci.arsw.calculator.CalculatorModel;

public class SquareCommand implements Command{

	Double value;
	CalculatorModel model;
	
	public SquareCommand(CalculatorModel model) {
		super();
		this.model = model;
		}

	@Override
	public void execute() {
		double currentResult=model.getCurrentResult();
		model.setCurrentResult(Math.sqrt(currentResult));
		value= currentResult;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		model.setCurrentResult(value);
	}
}
