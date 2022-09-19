package Compilador;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner; 

public class MatrizTransicion {

    /*
    * En el archivo de la matriz de transicion utilizamos la siguiente convencion:
    * Estado final, F = -1
    * Estado error, e = -2
    */
    private final String MATRIZ_TRANS = "./testFiles/TablaTransicion.txt";
    private final int FILAS_TRANS = 14;
    private final int COLUMNAS_TRANS = 28;
    private static final String ARCHIVO_SIMBOLO = "./testFiles/TablaDistintosSimbolos.txt";

    //Posicion actual en la tabla
    private int indexFila;
    private int indexCol;

    //Matriz de transicion de estados
    private int matrizEstado [][];

    private MatrizAccionSemantica matrizAccionSemantica;

    //Indice de simbolos
    private HashMap<String, Integer> indexSimbolo;

    public MatrizTransicion() {
        this.cargarMatrizTrans();
        this.cargarSimbolos();
        this.indexCol = 0;
        this.indexFila = 0;
    }

    public void cargarMatrizTrans() {
        this.matrizEstado = new int[FILAS_TRANS][COLUMNAS_TRANS];
        try {
            Scanner s = new Scanner(new File(MATRIZ_TRANS));
            
            for (int i=0; i<FILAS_TRANS; i++) {
                for (int j=0; j<COLUMNAS_TRANS; j++) {
                    this.matrizEstado[i][j] = Integer.parseInt(s.nextLine());
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha podido leer el archivo localizado en: " + MATRIZ_TRANS);
            e.printStackTrace();
        }
    }

    public void cargarSimbolos(){
        indexSimbolo = new HashMap<String, Integer>();
        try {
            String simbolo;
            int index;
            Scanner scanner = new Scanner(new File(ARCHIVO_SIMBOLO));
            while (scanner.hasNext()){
                simbolo = scanner.nextLine();
                index = Integer.parseInt(scanner.nextLine());
                indexSimbolo.put(simbolo, index);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha podido leer el archivo localizado en: " + ARCHIVO_SIMBOLO);
            e.printStackTrace();
        }
    }

    public Integer leerCaracterArchivo(char caracterArchivo, Boolean unico){
        System.out.println("el caracter es: "+caracterArchivo);
        indexCol = identificarCaracter(caracterArchivo);
        matrizAccionSemantica.ejecutar(indexFila, indexCol, caracterArchivo);
        indexFila = matrizEstado[indexFila][indexCol];
        if (indexFila == -1 ){
            System.out.println("Estado final");
            indexFila = 0;
            indexCol = 0;
        }
        return 1;
    }

    public int identificarCaracter(Character character){
        //Si no es ninguno de los anteriores entonces, buscar en la tabla
        String aux = String.valueOf(character);
        if (indexSimbolo.containsKey(aux))
            return indexSimbolo.get(aux);
        //Numero
        boolean isNumeric =  aux.matches("[+-]?\\d*(\\.\\d+)?");
        if (isNumeric)
            return 6;
        
        //Cadena vacia blanco
        if (aux.isEmpty())
            return 0;

        //Mayuscula
        if (aux.equals(aux.toLowerCase()))
            return 4;
        //Minuscula
        if (aux.equals(aux.toUpperCase()))
            return 5;
        return -1;
    }
}
