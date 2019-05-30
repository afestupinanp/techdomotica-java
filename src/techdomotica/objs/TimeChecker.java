package techdomotica.objs;

import java.util.ArrayList;
import java.time.LocalTime;

public class TimeChecker extends Thread {
    
    private Ambiente ambiente;
    private ArrayList<Event> eventList = new ArrayList();
    
    private boolean continueThread = true,
            eventConfirmed = false;
    
    private long spentSeconds = 0;
    
    private int seconds, minutes, hours;
    private int nextMinutes = -1, nextHours = -1;
    
    public TimeChecker(Ambiente amb) {
        ambiente = amb;
    }
    
    public void getAmbienteEventos() {
        eventList = ambiente.getEventList();
    }
    
    public void setNextEvent() {
        if(!eventList.isEmpty() && eventConfirmed) {
            System.out.println("Indexes: " + eventList.size());
            eventList.remove(0);
            if(!eventList.isEmpty()) {
                nextHours = eventList.get(0).getHora().getHour();
                nextMinutes = eventList.get(0).getHora().getMinute();
                eventConfirmed = true;
                onEventChange();
            }
            else {
                eventConfirmed = false;
                nextHours = -1;
                nextMinutes = -1;
            }
        }
        else {
            eventConfirmed = false;
            nextHours = -1;
            nextMinutes = -1;
        }
    }
    
    public void checkEvents() {
        if(eventList.isEmpty()) {
            System.out.println("Not empty, updating!");
            nextHours = eventList.get(0).getHora().getHour();
            nextMinutes = eventList.get(0).getHora().getMinute();
            eventConfirmed = true;
        }
        else System.out.println("Empty!");
    }
    
    public void disableTimeThread() {
        continueThread = false;
    }
    
    public int getSeconds() {
        return seconds;
    }
    
    public int getMinutes() {
        return minutes;
    }
    
    public int getHours() {
        return hours;
    }
    
    public long getSpentSeconds() {
        return spentSeconds;
    }
    
    public void onEventChange() {}
    
    public void run() {
        getAmbienteEventos();
        seconds = LocalTime.now().getSecond();
        minutes = LocalTime.now().getMinute();
        hours = LocalTime.now().getHour();
        
        if(!eventList.isEmpty()) {
            nextHours = eventList.get(0).getHora().getHour();
            nextMinutes = eventList.get(0).getHora().getMinute();
            eventConfirmed = true;
        }
        while(continueThread) {
            try {
                Thread.sleep(1000);
                seconds++;
                spentSeconds++;
                if(seconds == 60) {
                    seconds = 0;
                    minutes++;
                    if(minutes == 60) {
                        minutes = 0;
                        hours++;
                        if(hours == 24) {
                            hours = 0;
                        }
                    }
                }
                System.out.println(String.format("%02d:%02d - Next event: %02d:%02d", hours, minutes, nextHours, nextMinutes));
                if(hours == nextHours && minutes == nextMinutes && seconds == 0) {
                    setNextEvent();
                }
                
                switch(hours) {
                    case 0:
                        break;
                    case 1:
                        
                        break;
                }
                
            }
            catch(InterruptedException e) {
                System.out.println(e);
            }
        }
    }
    
}

//package techdomotica.objs;
//
//import java.awt.TrayIcon;
//import java.util.ArrayList;
//
//public class TimeChecker extends Thread {
//    
//    private Ambiente ambiente;
//    private boolean continueThread = true;
//    private int seconds, minutes, hours;
//    private int nextMinutes, nextHours;
//    
//    private ArrayList<Event> eventos;
//    
//    public TimeChecker(Ambiente amb) {
//        ambiente = amb;
//        setTodayEvents();
//        setNextEvent();
//    }
//    
//    @Override
//    public void run() {
//        seconds = java.time.LocalDateTime.now().getSecond();
//        minutes = java.time.LocalDateTime.now().getMinute();
//        hours = java.time.LocalDateTime.now().getHour();
//        
//        /*seconds = 50;
//        minutes = 59;
//        hours = 6;*/
//        while(continueThread) {
//            try {
//                Thread.sleep(1000);
//                seconds++;
//                if(seconds == 60) {
//                    seconds = 0;
//                    minutes++;
//                    if(minutes == 60) {
//                        minutes = 0;
//                        hours++;
//                        switch(hours) {
//                            case 1:
//                                ambiente.loadTodayEvents();
//                                setTodayEvents();
//                                break;
//                            case 24:
//                                hours = 0;
//                                break;
//                        }
//                    }
//                }
//                System.out.println(String.format("%02d:%02d - Next evento: %02d:%02d", hours, minutes, nextHours, nextMinutes));
//                if(hours == nextHours && minutes == nextMinutes && seconds == 0) {
//                    System.out.println("John Cena the time is now!!!!!!!!!!1");
//                    setNextEvent();
//                }
//                
//                //currentDateFormatted = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern());
//            } 
//            catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//    
//    public void disableTimeThread() {
//        continueThread = false;
//    }
//    
//    public int getSeconds() {
//        return seconds;
//    }
//    
//    public int getMinutes() {
//        return minutes;
//    }
//    
//    public int getHours() {
//        return hours;
//    }
//    
//    public void setNextEvent() {
//        if(eventos != null) {
//            if(!eventos.isEmpty()) {
//                System.out.println("JHOOOON NCENASODNAODSAND");
//                eventos.remove(0);
//                splitTimeNextEvent();
//                ambiente.loadPerfilFromEvent(0);
//                onEventChange();
//            }
//        }
//    }
//    
//    public void setTodayEvents() {
//        eventos = ambiente.getEventList();
//        if(eventos != null) {
//            System.out.println("Eventos null");
//        }
//    }
//    
//    private void splitTimeNextEvent() {
//        if(!eventos.isEmpty()) {
//            nextHours = eventos.get(0).getHora().getHour();
//            nextMinutes = eventos.get(0).getHora().getMinute();
//        }
//    }
//    
//    public void onEventChange() {
//        ambiente.getTrayIcon().displayMessage("Cambio de evento", "Un nuevo evento ha cambiado las configuraciones de los dispositivos.", TrayIcon.MessageType.INFO);
//    }
//    
//}