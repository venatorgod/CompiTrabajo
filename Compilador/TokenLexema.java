package Compilador;

public class TokenLexema {

    /**
     * identificador 257 
     * cte 258 
     * cadena 259 
     * if 260 
     * then 261
     * else 262
     * end_if 263
     * out 264
     * fun 265
     * return 266
     * break 267
     * ui8 268
     * f64 269
     * discard 270
     * for 271
     * continue 272
     * defer 273
     * =: 274
     * >= 275
     * <= 276
     * =! 277
     * << 278
     * >> 279
     */
	private int id;
	private StringBuilder lexema;

    /**
     * Para los identificadores si son funciones/variables/parametro
     */
	private String uso;
	
	/**
	 * Para las constantes o identificadores si es entera o double
	 */
	private String tipo;
	
	public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public TokenLexema(int id, String lexema) {
		this.id=id;
		this.lexema= new StringBuilder(lexema);
	}

    public TokenLexema(int id, String lexema, String tipo) {
        this.id=id;
        this.lexema= new StringBuilder(lexema);
        this.tipo=tipo;
    }

	public TokenLexema(String lexema) {
		this.id = 0;
		this.lexema = new StringBuilder(lexema);
	}
	
	public Object castearLexema() {
	    //Quedaria definirla mejor
	    return lexema;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public void setLexema(String lexema) {
	    if (lexema==null) {
	        this.lexema=null;
	        return;
	    }
		this.lexema = new StringBuilder(lexema);
	}

	public void appendLexema(Character caracter) {
		this.lexema.append(caracter);
	}
	
	public StringBuilder getLexema() {
		return lexema;
	}
	
	
	@Override
	public boolean equals(Object o) {
		try {
			TokenLexema x = (TokenLexema)o;
			if (x.id ==this.id && x.lexema.equals(this.lexema))
				return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	@Override
	public String toString() {
	    if (lexema!=null) {
	        if (tipo!=null) {
                return "Id: "+this.id+" | Lexema: \""+this.lexema.toString()+"\" | Tipo: "+this.tipo;
            }
	        return "Id: "+this.id+" | Lexema: \""+this.lexema.toString()+"\" | Tipo: nulo";
	    }else {
	        if (tipo!=null) {
	            return "Id: "+this.id+" | Lexema nulo | Tipo: "+this.tipo;
	        }
	        return "Id: "+this.id+" | Lexema nulo | Tipo: nulo";
	    }
	}

}
