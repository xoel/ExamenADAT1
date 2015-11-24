package examenficheros1xoelvazquez;

import java.io.Serializable;


public class Equipo implements Serializable {
    private int IDEquipo;
    private String  nomEquipo ;
   

    public Equipo() {
    }

    public Equipo(int IDEquipo, String nomEquipo) {
        this.IDEquipo = IDEquipo;
        this.nomEquipo = nomEquipo;
    }

    

    public int getIDEquipo() {
        return IDEquipo;
    }

    public void setIDEquipo(int IDEquipo) {
        this.IDEquipo = IDEquipo;
    }

   
    
    public String getNomEquipo() {
        return nomEquipo;
    }

    public void setNomEquipo(String nomEquipo) {
        this.nomEquipo = nomEquipo;
    }
}
