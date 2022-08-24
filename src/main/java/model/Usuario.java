package model;
//esta es una clase tipo POJO: Plain Old Java Object
//solo tiene atributos, sets y gets, constructores
//no tiene mas logica (metodos) que eso... una clase basica es una clase POJO
public class Usuario {
    //se mapea lo que nos interesa obtener de la tabla de la DB "usuario" en este caso. Se crean las variables
    private int id;
    private String idUsuario;
    private String pwUsuario;
    private String nombreUsuario;
    private String correoUsuario;
    private boolean activo;
    private String idRol;
//oonstructor default (Sin parametros)
    public Usuario() {
    }
//otro constructor con los atributos que nos interesa realmente sacar del DB
    public Usuario(String idUsuario, String nombreUsuario, String correoUsuario, String idRol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
        this.idRol = idRol;
    }  
    //getter y setter - encapsulate para private variables
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPwUsuario() {
        return pwUsuario;
    }
    public void setPwUsuario(String pwUsuario) {
        this.pwUsuario = pwUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }
    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getIdRol() {
        return idRol;
    }
    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }
    
    
}
