package ecomerce;


public class Usuario {

    private int id;
    private String nombre;
    private String correo;
    private String contrasena;

    // Constructor
    public Usuario(int id, String nombre, String correo, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Finalize method
    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println("Usuario con ID " + id + " está siendo eliminado");
        } finally {
            super.finalize();
        }
    }
}