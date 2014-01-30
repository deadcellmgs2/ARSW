package edu.eci.arsw.calculator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.acme.utils.CalculatorScriptDataLoader;
import org.acme.utils.Operation;

import Command.AddCommand;
import Command.Command;
import Command.DivCommand;
import Command.MinusCommand;
import Command.SquareCommand;
import Command.TimesCommand;
import reader.WrongFormatException;

public class CalculatorMainFrame extends JFrame{

	private JButton b1;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	private JButton b7;
	private JButton b8;
	private JButton b9;
	private JButton b2;
	private JButton b0;
	private JButton botPunto;
	private JLabel resultado;
	private JButton botMas;
	private JButton botLimpiar;
	private JButton botCargarScript;
	private JButton botDeshacer;
	private JButton botRehacer;
	private JButton botMenos;
	private JButton botdiv;
	private JButton botPor;
	private JButton botSQR;
	
	private NumberInputActionListener numInputActionListener;
	private CalculatorModel model;
	private boolean readyForNewNumber;
	
	private Stack<Command> undo;
	
	private Stack<Command> redo;
	
	private String input="";
	
	public CalculatorMainFrame(){
		this.setLayout(null);
		numInputActionListener=new NumberInputActionListener();
		model=new CalculatorModel();
		readyForNewNumber=true;
		initComponents();
		undo= new Stack<Command>();
		redo= new Stack<Command>();
		
	}
	
	
	class NumberInputActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b=(JButton)e.getSource();
			if (readyForNewNumber){
				input="";
				readyForNewNumber=false;
			}
			if (!(b.getText().contains(".") && input.contains("."))){
				input+=b.getText();				
				resultado.setText(input);				
			}
		}
		
	}

	class NumberInputButton extends JButton{		
		private static final long serialVersionUID = -2756950546151216499L;

		public NumberInputButton() {
			this.addActionListener(numInputActionListener);
		}
	}
	
	private JButton getB1() {
		if (b1 == null) {
			b1 = new NumberInputButton();
			b1.setText("1");
			b1.setBounds(10, 89, 81, 26);						
		}
		return b1;
	}

	private void initComponents() {
		setLayout(null);
		add(getB1());
		add(getB2());
		add(getB3());
		add(getB4());
		add(getB5());
		add(getB6());
		add(getB7());
		add(getB8());
		add(getB9());
		add(getB0());
		add(getBotPunto());
		add(getResultado());
		add(getBotMas());
		add(getBotLimpiar());
		add(getBotCargarScript());
		add(getBotDeshacer());
		add(getBotRehacer());
		
		add(getBotMenos());
		add(getBotdiv());
		add(getBotPor());
		add(getBotSQR());
		
		setSize(500, 300);
	}

	private JButton getBotMenos() {
		if (botMenos == null) {
			botMenos = new JButton();
			botMenos.setText("-");
			botMenos.setBounds(400, 90, 81, 26);
			
			botMenos.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					Command restar= new MinusCommand(model, input);
					restar.execute();
					undo.push(restar);
					resultado.setText(model.getCurrentResult()+"");
					readyForNewNumber=true;
				}
			});
		}
		return botMenos;
	}

	private JButton getBotdiv() {
		if (botdiv == null) {
			botdiv = new JButton();
			botdiv.setText("/");
			botdiv.setBounds(304, 60, 81, 26);;	
			botdiv.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					Command com= new DivCommand(model, input);
					com.execute();
					undo.push(com);
					resultado.setText(model.getCurrentResult()+"");
					readyForNewNumber=true;
				}
			});
		}
		return botdiv;
	}

	private JButton getBotPor() {
		if (botPor == null) {
			botPor = new JButton();
			botPor.setText("*");
			botPor.setBounds(400, 60, 81, 26);
			botPor.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					Command com= new TimesCommand(model, input);
					com.execute();
					undo.push(com);
					resultado.setText(model.getCurrentResult()+"");
					readyForNewNumber=true;
				}
			});
		}
		return botPor;
	}

	private JButton getBotSQR() {
		if (botSQR == null) {
			botSQR = new JButton();
			botSQR.setText("SQR");
			botSQR.setBounds(350, 30, 81, 26);
			botSQR.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					Command com= new SquareCommand(model);
					com.execute();
					undo.push(com);
					resultado.setText(model.getCurrentResult()+"");
					readyForNewNumber=true;
				}
			});
		}
		return botSQR;
	}

	private JButton getBotLimpiar() {
		if (botLimpiar == null) {
			botLimpiar = new JButton();
			botLimpiar.setText("C");
			botLimpiar.setBounds(350, 196, 81, 26);
			botLimpiar.setBackground(Color.RED);
			
			botLimpiar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					model.reset();
					resultado.setText("0");
					readyForNewNumber=true;
					input="0";
				}
			});
		}
		return botLimpiar;
	}

	private JButton getBotCargarScript(){
		if (botCargarScript == null) {
			botCargarScript = new JButton();
			botCargarScript.setText("Script");
			botCargarScript.setBounds(350, 170, 81, 26);
			
			botCargarScript.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
						Iterator<Operation> op=new ExcelDataLoader("").load();
					
					String ruta=JOptionPane.showInputDialog(null, "Ruta del archivo: (Ejemplo: C:/Temp/ejemplo.xls)");
					model.applyDataScript(new CalculatorExcelScriptDataLoaderAdapter(new ExcelDataLoader(ruta)));
					resultado.setText(model.getCurrentResult()+"");
					readyForNewNumber=true;
				}
			});
		}
		return botCargarScript;

	}
	
	private JButton getBotDeshacer(){			
		
		if (botDeshacer == null) {
			botDeshacer = new JButton();
			botDeshacer.setText("Undo");
			botDeshacer.setBounds(304, 130, 81, 26);
			
			botDeshacer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					if(!undo.isEmpty()){
						Command comando= undo.pop();
						comando.undo();
						resultado.setText(model.getCurrentResult()+"");
						redo.push(comando);
					}
				}
			});
		}
		return botDeshacer;
	}

	private JButton getBotRehacer(){
		if (botRehacer == null) {
			botRehacer = new JButton();
			botRehacer.setText("Redo");
			botRehacer.setBounds(400, 130, 81, 26);
			
			botRehacer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					if(!redo.isEmpty()){
						Command comando= redo.pop();
						comando.execute();
						resultado.setText(model.getCurrentResult()+"");
						undo.push(comando);
						
					}
				}
			});
		}
		return botRehacer;
	}

	
	private JButton getBotMas() {
		if (botMas == null) {
			botMas = new JButton();
			botMas.setText("+");
			botMas.setBounds(304, 90, 81, 26);
			
			botMas.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					Command sumar= new AddCommand(model, input);
					sumar.execute();
					undo.push(sumar);
					resultado.setText(model.getCurrentResult()+"");
					readyForNewNumber=true;
				}
			});
		}
		return botMas;
	}

	private JLabel getResultado() {
		if (resultado == null) {
			resultado = new JLabel();
			resultado.setText("0");
			resultado.setBounds(13, 25, 381, 43);
		}
		return resultado;
	}


	private JButton getBotPunto() {
		if (botPunto == null) {
			botPunto = new NumberInputButton();
			botPunto.setText(".");
			botPunto.setBounds(8, 193, 83, 26);
		}
		return botPunto;
	}

	private JButton getB0() {
		if (b0 == null) {
			b0 = new NumberInputButton();
			b0.setText("0");
			b0.setBounds(99, 193, 81, 26);
		}
		return b0;
	}

	private JButton getB9() {
		if (b9 == null) {
			b9 = new NumberInputButton();
			b9.setText("9");
			b9.setBounds(186, 157, 81, 26);
		}
		return b9;
	}

	private JButton getB8() {
		if (b8 == null) {
			b8 = new NumberInputButton();
			b8.setText("8");
			b8.setBounds(99, 157, 81, 26);
		}
		return b8;
	}

	private JButton getB7() {
		if (b7 == null) {
			b7 = new NumberInputButton();
			b7.setText("7");
			b7.setBounds(10, 157, 81, 26);
		}
		return b7;
	}

	private JButton getB6() {
		if (b6 == null) {
			b6 = new NumberInputButton();
			b6.setText("6");
			b6.setBounds(184, 122, 81, 26);
		}
		return b6;
	}

	private JButton getB5() {
		if (b5 == null) {
			b5 = new NumberInputButton();
			b5.setText("5");
			b5.setBounds(98, 123, 81, 26);
		}
		return b5;
	}

	private JButton getB4() {
		if (b4 == null) {
			b4 = new NumberInputButton();
			b4.setText("4");
			b4.setBounds(10, 124, 81, 26);
		}
		return b4;
	}

	private JButton getB3() {
		if (b3 == null) {
			b3 = new NumberInputButton();
			b3.setText("3");
			b3.setBounds(183, 89, 81, 26);
		}
		return b3;
	}

	private JButton getB2() {
		if (b2 == null) {
			b2 = new NumberInputButton();
			b2.setText("2");
			b2.setBounds(98, 89, 81, 26);
		}
		return b2;
	}
	
	public static void main(String[] args) {
		CalculatorMainFrame mf=new CalculatorMainFrame();
		mf.setVisible(true);
	}
}


