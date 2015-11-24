package examenficheros1xoelvazquez;

class Ciclista  {
    private int dorsal;
    private String nombre ;
    private int puntos;
    private int equipo;
    private boolean baja;

    public Ciclista() {
    }
     public Ciclista(int dorsal, String nombre , int puntos, int equipo, boolean baja) {
        this.dorsal =dorsal;
        this.nombre = nombre ;
        this.puntos = puntos;
        this.equipo = equipo;
        this.baja = baja;
    }

     public int getEquipo() {
        return equipo;
    }

    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public boolean getBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }
    
    
      public long LongitudRegistro() {
        return 1;
      }

   

   }  

 
    
    
  
