package examenficheros1xoelvazquez;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Operaciones {

    String fichAl;
    String fichSec;
    String fichTexto;

    Operaciones(String fichero1, String fichero2, String fichero3) {

        this.fichSec = fichero1;
        this.fichAl = fichero2;
        this.fichTexto = fichero3;

    }

    public void AltasCiclistas() {
        Scanner sc = new Scanner(System.in);
        String nombre, apellidos, equipo;
        int puntos, dorsal;
        boolean existeEq;

        try {
            File equipos = new File(fichSec);
            RandomAccessFile cic = new RandomAccessFile(fichAl, "rw");
            //siturarse al final del archivo
            if (cic.length() == 0) {
                cic.seek(0L);
                dorsal = 1;
            } else {
                cic.seek(cic.length());
                dorsal = getUltimoDorsal(cic) + 1;
            }
            do {
                System.out.println("Introduzca su Nombre: ");
                nombre = sc.nextLine();
                if (!nombre.equals("*")) {
                    System.out.println("Introduzca sus Apellidos: ");
                    apellidos = sc.nextLine();
                    do {
                        System.out.println("Introduzca su Equipo: ");
                        equipo = sc.nextLine();
                        existeEq = buscarEquipo(equipos, equipo);
                        if (!existeEq) {
                            System.err.println("Equipo no existente, vuelva a introducir Equipo.");
                        }
                    } while (!existeEq);
                    System.out.println("Introduzca sus Puntos: ");
                    puntos = sc.nextInt();
                    sc.nextLine();
                    //escribimos
                    cic.writeBytes(nombre);
                    cic.seek(cic.length());
                    cic.writeBytes(apellidos);
                    cic.seek(cic.length());
                    cic.writeBytes(equipo);
                    cic.seek(cic.length());
                    cic.writeBytes(String.valueOf(puntos));
                    cic.seek(cic.length());
                    cic.writeBytes(String.valueOf(dorsal));
                    dorsal++;
                }
            } while (!nombre.equals("*"));

            //cic.writeBytes(s);
            cic.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.err.println("IO Exception alta Ciclista");
        }
    }

    //Accedemos al ultimo dorsal
    private int getUltimoDorsal(RandomAccessFile cic) {
        int dorsal = 1;
        String cad;
        try {
            //nos posicionamos en el ultimo dorsal recibido
            //esto nunca sera vacio ya que se comprueba en Alta
            cic.seek(cic.length() - 1);
            cad = cic.readLine();
            dorsal = Integer.parseInt(cad);
        } catch (IOException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dorsal;

    }

    public void LeerTodosCiclistas() {

        try {
            File equipos = new File(fichSec);
            RandomAccessFile cic = new RandomAccessFile(fichAl, "r");
            cic.seek(0L);
            String cad=cic.readLine();
            while (cad != null) {
                System.out.println(cad);
                cad = cic.readLine();
                
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void AltasEquipo() {
        Scanner sc = new Scanner(System.in);
        File equipos = new File(fichSec);
        String nombreEq = null;
        int idEq;
        ObjectOutputStream os;
        boolean existeEq, existeId = false;

        try {
            if (equipos.exists()) {
                os = new MiObjectOutputStream(new FileOutputStream(equipos, true));
            } else {
                os = new ObjectOutputStream(new FileOutputStream(equipos, false));
            }
            do {

                do {
                    System.out.println("Introduzca nombre del Equipo. ");
                    nombreEq = sc.nextLine();
                    existeEq = buscarEquipo(equipos, nombreEq);
                    if (existeEq) {
                        System.err.println("Equipo ya existente, vuelva a introducir nombre.");
                    }
                } while (existeEq);
                if (!nombreEq.equals("*")) {
                    do {
                        System.out.println("Introduzca Id del Equipo. ");
                        idEq = sc.nextInt();
                        sc.nextLine();
                        existeId = buscarIdEquipo(equipos, idEq);
                        if (existeId) {
                            System.err.println("IdEquipo ya existente, vuelva a introducir Id.");
                        }
                    } while (existeId);
                    os.writeObject(new Equipo(idEq, nombreEq));
                }
            } while (!nombreEq.equals("*"));
            os.close();
        } catch (IOException ex) {
            System.err.println("IO Exception alta equipo");
        }
    }

    private boolean buscarEquipo(File f, String equipo) {
        boolean existe = false;
        ObjectInputStream entrada = null;
        try {
            entrada = new ObjectInputStream(new FileInputStream(f));
            try {
                while (true) {
                    Equipo lectura = (Equipo) entrada.readObject();
                    if (lectura.getNomEquipo().equals(equipo)) {
                        existe = true;
                    }
                }
            } catch (IOException ex) {
                //System.err.println("llegado al final");
            } catch (ClassNotFoundException ex) {
                System.err.println("Class not found en Buscar Equipo");
            } finally {
                entrada.close();
            }
        } catch (IOException ex) {
            System.err.println("IO EXCEPTION");;
        }
        return existe;
    }

    private boolean buscarIdEquipo(File f, int idEq) {
        boolean existe = false;
        ObjectInputStream entrada = null;
        try {
            entrada = new ObjectInputStream(new FileInputStream(f));
            try {
                while (true) {
                    Equipo lectura = (Equipo) entrada.readObject();
                    if (lectura.getIDEquipo() == idEq) {
                        existe = true;
                    }
                }
            } catch (IOException ex) {
                //System.err.println("llegado al final");
            } catch (ClassNotFoundException ex) {
                System.err.println("Class not found en Buscar Equipo");
            } finally {
                entrada.close();
            }
        } catch (IOException ex) {
            System.err.println("IO EXCEPTION");;
        }
        return existe;

    }

    public void LeerTodosEquipos() {
        File f = new File(fichSec);
        ObjectInputStream entrada = null;
        ordenarEquipos(f);
        try {
            entrada = new ObjectInputStream(new FileInputStream(f));
            try {
                System.out.println("");
                System.out.println("Equipos registrados: ");
                while (true) {
                    Equipo lectura = (Equipo) entrada.readObject();
                    System.out.println(lectura.getIDEquipo() + ", " + lectura.getNomEquipo());
                }
            } catch (IOException ex) {
                //System.err.println("llegado al final");
            } catch (ClassNotFoundException ex) {
                System.err.println("Class not found en Buscar Equipo");
            } finally {
                entrada.close();
            }
        } catch (IOException ex) {
            System.err.println("IO EXCEPTION");;
        }
    }

    private void ordenarEquipos(File f) {
        File fAux = new File("auxiliar.data");
        ObjectOutputStream salida = null;
        Map<String, Integer> equipos = new TreeMap();
        ObjectInputStream entrada = null;
        try {
            entrada = new ObjectInputStream(new FileInputStream(f));
            salida = new ObjectOutputStream(new FileOutputStream(fAux));
            try {
                while (true) {
                    //cogemos lo que hay en el fichero y lo ordenamos en un Treemap
                    Equipo lectura = (Equipo) entrada.readObject();
                    equipos.put(lectura.getNomEquipo(), lectura.getIDEquipo());
                }
            } catch (IOException ex) {
                //System.err.println("llegado al final");
            } catch (ClassNotFoundException ex) {
                System.err.println("Class not found en Buscar Equipo");
            } finally {
                entrada.close();
            }
            //Ahora vamos a escribirlo en un auxiliar
            for (Map.Entry<String, Integer> equipo : equipos.entrySet()) {
                salida.writeObject(new Equipo(equipo.getValue(), equipo.getKey()));
            }
            salida.close();
            f.delete();
            fAux.renameTo(f);

        } catch (IOException ex) {
            System.err.println("IO EXCEPTION");;
        }
    }

    public void subirPuntosEquipo() {

    }

}
