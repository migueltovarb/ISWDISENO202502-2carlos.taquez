import java.util.*;

class Estudiante {
    private String nombre;
    private int ID;
    private String programaAcademico;

    public Estudiante(String nombre, int ID, String programaAcademico) {
        this.nombre = nombre;
        this.ID = ID;
        this.programaAcademico = programaAcademico;
    }

    public String getNombre() { return nombre; }
    public int getID() { return ID; }
    public String getProgramaAcademico() { return programaAcademico; }

    @Override
    public String toString() {
        return nombre + " (" + programaAcademico + ")";
    }
}

class SalaDeEstudio {
    private int numeroDeSala;
    private int capacidadMaxima;
    private boolean disponibilidad;

    public SalaDeEstudio(int numeroDeSala, int capacidadMaxima, boolean disponibilidad) {
        this.numeroDeSala = numeroDeSala;
        this.capacidadMaxima = capacidadMaxima;
        this.disponibilidad = disponibilidad;
    }

    public int getNumeroDeSala() { return numeroDeSala; }
    public boolean isDisponible() { return disponibilidad; }
    public void setDisponibilidad(boolean disponibilidad) { this.disponibilidad = disponibilidad; }

    @Override
    public String toString() {
        return "Sala #" + numeroDeSala + " (Capacidad: " + capacidadMaxima + ", Disponible: " + disponibilidad + ")";
    }
}

class Reserva {
    private Date fechaYHora;
    private Estudiante estudiante;
    private SalaDeEstudio sala;

    public Reserva(Date fechaYHora, Estudiante estudiante, SalaDeEstudio sala) {
        this.fechaYHora = fechaYHora;
        this.estudiante = estudiante;
        this.sala = sala;
    }

    public Date getFechaYHora() { return fechaYHora; }
    public Estudiante getEstudiante() { return estudiante; }
    public SalaDeEstudio getSala() { return sala; }

    public boolean validarDisponibilidad(boolean disponibilidad) {
        return disponibilidad;
    }

    public boolean reservarSala(Estudiante estudiante, SalaDeEstudio salaDeEstudio) {
        if (validarDisponibilidad(salaDeEstudio.isDisponible())) {
            salaDeEstudio.setDisponibilidad(false);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Reserva de " + estudiante.getNombre() + " en sala " + sala.getNumeroDeSala() + " el " + fechaYHora;
    }
}

class Sistema {
    private List<SalaDeEstudio> listaSalas = new ArrayList<>();
    private List<Reserva> listaReservas = new ArrayList<>();

    public void registrarSala(SalaDeEstudio sala) {
        listaSalas.add(sala);
    }

    public void mostrarSalasDisponibles() {
        System.out.println(" Salas disponibles:");
        for (SalaDeEstudio s : listaSalas) {
            if (s.isDisponible()) {
                System.out.println(s);
            }
        }
    }

    public boolean validarReservaDuplicada(Reserva nuevaReserva) {
        for (Reserva r : listaReservas) {
            if (r.getEstudiante().getID() == nuevaReserva.getEstudiante().getID() &&
                    r.getSala().getNumeroDeSala() == nuevaReserva.getSala().getNumeroDeSala() &&
                    r.getFechaYHora().equals(nuevaReserva.getFechaYHora())) {
                return true;
            }
        }
        return false;
    }

    public void agregarReserva(Reserva reserva) {
        if (!validarReservaDuplicada(reserva)) {
            if (reserva.reservarSala(reserva.getEstudiante(), reserva.getSala())) {
                listaReservas.add(reserva);
                System.out.println(" Reserva creada exitosamente: " + reserva);
            } else {
                System.out.println(" No se pudo crear la reserva. Sala no disponible.");
            }
        } else {
            System.out.println(" Ya existe una reserva con la misma fecha, estudiante y sala.");
        }
    }

    public void historialReservasEstudiante(Estudiante e) {
        System.out.println("\n Historial de reservas de " + e.getNombre() + ":");
        for (Reserva r : listaReservas) {
            if (r.getEstudiante().getID() == e.getID()) {
                System.out.println(r);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        // Crear salas
        SalaDeEstudio sala1 = new SalaDeEstudio(101, 4, true);
        SalaDeEstudio sala2 = new SalaDeEstudio(102, 6, true);
        sistema.registrarSala(sala1);
        sistema.registrarSala(sala2);

        // Crear estudiantes
        Estudiante est1 = new Estudiante("Carlos", 1, "Ingeniería de Sistemas");
        Estudiante est2 = new Estudiante("Laura", 2, "Arquitectura");

        // Mostrar salas disponibles
        sistema.mostrarSalasDisponibles();

        // Crear fechas de reserva
        Date fecha1 = new Date(2025 - 1900, 9, 10, 10, 0); // 10 octubre 2025, 10:00
        Date fecha2 = new Date(2025 - 1900, 9, 10, 12, 0); // 10 octubre 2025, 12:00

        // Crear reservas
        Reserva reserva1 = new Reserva(fecha1, est1, sala1);
        sistema.agregarReserva(reserva1);

        // Intentar duplicado (misma fecha, estudiante, sala)
        Reserva reservaDuplicada = new Reserva(fecha1, est1, sala1);
        sistema.agregarReserva(reservaDuplicada);

        // Otra reserva válida
        Reserva reserva2 = new Reserva(fecha2, est2, sala2);
        sistema.agregarReserva(reserva2);

        // Mostrar historial
        sistema.historialReservasEstudiante(est1);
        sistema.historialReservasEstudiante(est2);
    }
}