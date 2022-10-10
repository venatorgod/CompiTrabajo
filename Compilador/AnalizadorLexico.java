package Compilador;
import java.util.HashMap;
import java.util.List;

import Compilador.AccionesSemanticas.AccionSemantica;




public class AnalizadorLexico {
    
	private static MatrizTransicion matrizTransicion=new MatrizTransicion();
	private static List<List<Character>> archivoCodigoFuente;
	private static int iteradorLineaCaracteres = 0;
	private static int iteradorListaCaracteres = 0;
    private static Character simboloProcesar;
    private static List<Character> lineaProcesar = null;
    private static int tokenEntregar = -1;
    public static TokenLexema anteriorToken;
    public static Parser paruser;
	
	public AnalizadorLexico() {
	}
	
	public static void inic(String ruta) {
	    archivoCodigoFuente = GestorArchivo.readCode(ruta);
        anteriorToken = AccionSemantica.getNewToken();
        lineaProcesar = archivoCodigoFuente.get(iteradorListaCaracteres);
        TablaSimbolos.inic();
	}
	
	public static HashMap<String, Integer> getTablasSimbolos(){
        return matrizTransicion.getTablasSimbolos();
	}
        
	public static int getLinea() {
	    return iteradorListaCaracteres+1;
	}
	
	public static int getCaracter() {
        return iteradorLineaCaracteres+1;
    }
	
	public static void entregarToken(int idToken) {
	    tokenEntregar=idToken;
	    anteriorToken=AccionSemantica.getToken();
	}
	
	public static void avanzarLectura() {
		iteradorLineaCaracteres++;
	}
	
	public static int siguienteToken() {
	    tokenEntregar=-1;
	    while (iteradorListaCaracteres < archivoCodigoFuente.size()) {
	        while (iteradorLineaCaracteres < lineaProcesar.size()) {
                simboloProcesar = lineaProcesar.get(iteradorLineaCaracteres);
                //System.out.print(simboloProcesar);
                matrizTransicion.transicionCaracter(simboloProcesar);
                if (tokenEntregar!=-1){
                    return tokenEntregar;
                }
            }
	        iteradorListaCaracteres++;
	        if (iteradorListaCaracteres < archivoCodigoFuente.size()) {
	            lineaProcesar = archivoCodigoFuente.get(iteradorListaCaracteres);
	        }
	        iteradorLineaCaracteres = 0;
	    }
	    anteriorToken.setLexema(null);
	    return tokenEntregar;
	}

}