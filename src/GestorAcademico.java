import java.util.ArrayList;
import java.util.HashMap;

public class GestorAcademico implements ServiciosAcademicosI {
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Curso> cursos;
    private HashMap<Integer, ArrayList<Estudiante>> inscripciones;

    public GestorAcademico() {
        estudiantes = new ArrayList<>();
        cursos = new ArrayList<>();
        inscripciones = new HashMap<>();
    }

    @Override
    public void matricularEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        }
    }

    @Override
    public void agregarCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
        }
    }

    @Override
    public void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException {
        if (!estudiantes.contains(estudiante)) {
            throw new IllegalArgumentException("Estudiante no registrado.");
        }
        Curso curso = buscarCursoPorId(idCurso);
        if (curso == null) {
            throw new IllegalArgumentException("Curso no encontrado.");
        }
        ArrayList<Estudiante> inscritos = inscripciones.getOrDefault(idCurso, new ArrayList<>());
        if (inscritos.contains(estudiante)) {
            throw new EstudianteYaInscritoException("El estudiante ya está inscrito en este curso.");
        }
        inscritos.add(estudiante);
        inscripciones.put(idCurso, inscritos);
    }

    @Override
    public void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException {
        Curso curso = buscarCursoPorId(idCurso);
        if (curso == null) {
            throw new IllegalArgumentException("Curso no encontrado.");
        }
        ArrayList<Estudiante> inscritos = inscripciones.get(idCurso);
        if (inscritos == null || inscritos.stream().noneMatch(e -> e.getId() == idEstudiante)) {
            throw new EstudianteNoInscritoEnCursoException("El estudiante no está inscrito en este curso.");
        }
        inscritos.removeIf(e -> e.getId() == idEstudiante);
        inscripciones.put(idCurso, inscritos);
    }

    private Curso buscarCursoPorId(int idCurso) {
        for (Curso curso : cursos) {
            if (curso.getId() == idCurso) {
                return curso;
            }
        }
        return null;
    }
}
