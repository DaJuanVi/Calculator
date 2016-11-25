package classes;

import javax.swing.JTextArea;

public class Display extends JTextArea{
	
	private String displayText;
	
	public Display() {
		
		super(2,20);
		displayText = getText();
	}
	
	
	private boolean isNumeric(String text) {
	    
		try{
	        double textToNumber = Double.parseDouble( text );
	        return true;
	    }
		
		/*
		 * A la hora de atrapar cualquier excepción
		 * ¿debemos usar una excepción que describa 
		 * el problema que debemos agarrar en caso
		 * de que se presente? En este caso usé
		 * NumberFormatException por que ya estaba
		 * hecha. Pero si no estuviera hecha hubiera
		 * usado Exception, ya que no me importa el
		 * tipo de error porque se que si se presenta
		 * un error al transformar el texto en número
		 * es porque no era un número. Es posible que
		 * se presentara otro error al transformar el
		 * número pero es poco probable. El punto es
		 * ¿es mala practica usar Exception en vez de
		 * una excepción especializada o depende de la
		 * situación? ¿Es mejor evitar los try/catch?(he
		 * escuchado que vuelven el código poco legible,
		 * aunque me imagino que en exceso)
		 * ¿está mal poner tildes a los comentarios?
		 *
		 *Se que esto lo puedo averiguar en internet
		 *pero me gustaría saber su opinión, Gracias :)
		 */
		catch (NumberFormatException e ) {		    
	        return false;
	    }
	}
	
	private boolean isDecimalPoint(char character){
		
		/*
		 * Está mal hacer el condicional en una linea?
		 * reduce la calidad del código?
		 */
		if(character == '.') return true;
		else return false;
	}
	
	private boolean isOperator(char character){
		
		boolean isIt = false;		// Se entiende la inicialización de esta variable? estoy haciendo algo mal?
		char operators[] = {'+', '-', '*', '/'};
		
		for (char c:operators) {
			
			if (c == character) {
				
				isIt = true;
				break;
			}
		}
		
		return isIt;
	}
	
	private boolean isAnOperation(String text){
		
		boolean isIt = true;
		char textCharacters[] = text.toCharArray();
		
		for (char c: textCharacters){
			
			if( !(isNumeric(Character.toString(c) )
					|| isOperator(c) ) ){
				
				isIt = false;
				break;
			}
		}
		
		return isIt;
	}
	
	// es muy larga esta función ? qué me recomienda?
	private double[] getDataOfPreviousNumberOperator(String text, int operatorIndex){
		
		char characterInActualIndex;
		double number;
		int initialIndexNumber = operatorIndex - 1;
		int lastIndexNumber = operatorIndex - 1;
		
		/*El siguiente array tendrá la siguiente información [initialIndexNumber, lastIndexNumber, number] */
		double dataOfNumber [] = new double[3];
		
		for (int i=lastIndexNumber; i >= 0; i--) {
			
			characterInActualIndex = text.charAt(i);
			
			if (isNumeric(Character.toString(characterInActualIndex) )
					|| isDecimalPoint(characterInActualIndex) ) {
				
				initialIndexNumber = i;
				
			}else {
				
				if (isOperator(characterInActualIndex) ){
					
					if(characterInActualIndex == '+'
							|| characterInActualIndex == '-'){
						
						initialIndexNumber = i;
					}
				}
				
				break;
			}
		}
		
		number = Double.parseDouble(text.substring(initialIndexNumber, lastIndexNumber + 1) );		// +1 porque este parámetro indica donde dejar de tomar la cadena
		dataOfNumber [0] = initialIndexNumber;
		dataOfNumber [1] = lastIndexNumber;
		dataOfNumber [2] = number;
		
		return dataOfNumber;
	}
	
	// este método es similar al anterior, ¿así está bien o que me recomienda?
	private double[] getDataOftNextNumberOperator(String text, int operatorIndex){
			
			char characterInActualIndex;
			double number;
			int initialIndexNumber = operatorIndex +1;
			int lastIndexNumber = operatorIndex + 1;
			int lastIndexText = text.length() - 1;		// -1 porque los indices empiezan en 0
			
			/*El siguiente array tendrá la siguiente información [initialIndexNumber, lastIndexNumber, number] */
			double dataOfNumber [] = new double[3];
			
			for (int i=initialIndexNumber; i <= lastIndexText; i++) {
				
				characterInActualIndex = text.charAt(i);
				
				if ( (i == initialIndexNumber) && (characterInActualIndex == '+'
						|| characterInActualIndex == '-') ) {
					
					lastIndexNumber = i;
				}else {
				
					if (isNumeric(Character.toString(characterInActualIndex) )
							|| isDecimalPoint(characterInActualIndex) ) {
						
						lastIndexNumber = i;
						
					}else {
						
						if (isOperator(characterInActualIndex) ){
							
							if(characterInActualIndex == '+'
									|| characterInActualIndex == '-'){
								
								lastIndexNumber = i;
							}
						}
						
						break;
					}
				}
			}
	
			number = Double.parseDouble(text.substring(initialIndexNumber, lastIndexNumber + 1) );		// +1 porque este parámetro indica donde dejar de tomar la cadena
			dataOfNumber [0] = initialIndexNumber;
			dataOfNumber [1] = lastIndexNumber;
			dataOfNumber [2] = number;
			
			return dataOfNumber;
		}
	
	
}
