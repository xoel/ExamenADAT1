/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenficheros1xoelvazquez;

/**
 *
 * @author usuario
 */
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Redefinición de la clase ObjectOuputStream para que no escriba una cabecera
 * al inicio del Stream.
*
 */
public class MiObjectOutputStream extends ObjectOutputStream {

    /**
     * Constructor que recibe OutputStream
     * @param out
     * @throws java.io.IOException
     */
    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    /**
     * Constructor sin parámetros
     * @throws java.io.IOException
     */
    protected MiObjectOutputStream() throws IOException, SecurityException {
        super();
    }

    /**
     * Redefinición del método de escribir la cabecera para que no haga nada.
     * @throws java.io.IOException
     */
    @Override
    protected void writeStreamHeader() throws IOException {
    }
}
