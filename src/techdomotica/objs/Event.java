package techdomotica.objs;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    
    private int eventID = 0;
    private LocalDate fecha = null;
    private LocalTime hora = null;
    
    private Perfil perfilEvento = null;

    public Event(int id, LocalDate fec, LocalTime hor, Perfil perf) {
        eventID = id;
        fecha = fec;
        hora = hor;
        perfilEvento = perf;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Perfil getPerfilEvento() {
        return perfilEvento;
    }

    public void setPerfilEvento(Perfil perfilEvento) {
        this.perfilEvento = perfilEvento;
    }
    
    
}