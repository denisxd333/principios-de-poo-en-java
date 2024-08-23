import java.util.Date;

public class Estudiante extends Persona {
    private Date fechaDeNacimiento;
    private String estado; // matriculado, inactivo, graduado

    public Estudiante(int id, String nombre, String apellido, Date fechaDeNacimiento, String estado) {
        super(id, nombre, apellido);
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.estado = estado;
    }

}
