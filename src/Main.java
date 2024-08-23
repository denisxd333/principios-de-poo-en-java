import java.util.Date;

public class Main {
    public static void main(String[] args) {
        GestorAcademico gestor = new GestorAcademico();
        
        // Crear instancias de Estudiante y Curso
        Estudiante estudiante1 = new Estudiante(1, "Juan", "Pérez", new Date(), "matriculado");
        Curso curso1 = new Curso(101, "Matemáticas", "Curso de matemáticas avanzadas", 5, "1.0");

        // Crear y agregar curso
        gestor.agregarCurso(curso1);

        // Matricular estudiante
        gestor.matricularEstudiante(estudiante1);

        try {
            // Inscribir estudiante en curso
            gestor.inscribirEstudianteCurso(estudiante1, curso1.getId());

            // Desinscribir estudiante del curso
            gestor.desinscribirEstudianteCurso(estudiante1.getId(), curso1.getId());

        } catch (EstudianteYaInscritoException | EstudianteNoInscritoEnCursoException e) {
            e.printStackTrace();
        }
    }
}
