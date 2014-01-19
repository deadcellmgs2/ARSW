package Interfaces;

public interface Perfilable {
	/**
	* @obj retornar los nombres de los métodos que se pueden perfilar en esta aplicación
	* @return Un arreglo, sin elementos nulos, con los nombres de los métodos que se deben probar
	*/
	public String[] getTesteableMethodName();
	/**
	* @obj retornar un arreglo con el mismo número de elementos de la propiedad 'testeableMethods',
	*      donde cada posición corresponde al número de veces que se deben ejectuar los métodos indicados
	*      en dicha propiedad (la correspondencia número de veces/método la da el índice)
	* @
	*/
	public int[] getTestCount();
   
}
