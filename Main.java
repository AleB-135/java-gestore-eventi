import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         Evento eventoUtente = null;
         boolean eventoCreato = false;

                                                         //CREAZIONE EVENTO DA PARTE DELL'UTENTE CON RELATIVE ECCEZIONI 

        while (!eventoCreato) {
            try {
                System.out.println("Inserisci un nuovo evento");
                String titolo = scanner.nextLine();

                System.out.println("Inserisci la data dell'evento");
                System.out.println("Anno (Es:2025): ");
                int anno = scanner.nextInt();
                System.out.println("Mese (1-12): ");
                int mese = scanner.nextInt();
                System.out.println("Giorno (1-31): ");
                int giorno = scanner.nextInt();

                LocalDate data = LocalDate.of(anno, mese, giorno);

                System.out.println("Inserisci il numero totale di posti disponibili");
                int postiTotali = scanner.nextInt();
                scanner.nextLine();
                
                eventoUtente = new Evento(titolo, data, postiTotali);
                eventoCreato = true;
                System.out.println("Evento creato: " + eventoUtente);

            } catch (InputMismatchException e) {
                System.err.println("Errore di input: Inserito un numero non valido per anno, mese, giorno o posti totali.");
                scanner.nextLine();

            } catch (DateTimeException e) {
                System.err.println("Errore: La data inserita non è valida.");

            }
            System.out.println();
        }

                                                                     // STEP 2 - PRENOTAZIONI

        if (eventoUtente != null){
            System.out.println("Gestione prenotazioni:");
            System.out.println("Posti prenotati: " + eventoUtente.getNumeroPostiPrenotati() + "\nPosti disponibili: " + (eventoUtente.getNumeroPostiTotali() - eventoUtente.getNumeroPostiPrenotati()));

            System.out.println("Quanti posti vuoi prenotare?");
            int numeroPrenotazioni = -1;
        try {
            numeroPrenotazioni = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Input non valido per il numero di prenotazioni.");
            scanner.nextLine();
        }

        if (numeroPrenotazioni > 0) {
            for (int i = 0; i < numeroPrenotazioni; i++) {
                System.out.println(eventoUtente.prenota());
            }
        } else if (numeroPrenotazioni == 0) {
            System.out.println("Nessuna prenotazione da effettuare.");
        } else {
            System.out.println("Il numero di prenotazioni deve essere positivo.");
        }

        System.out.println("\nStato attuale dell'evento:");
        System.out.println("Posti prenotati: " + eventoUtente.getNumeroPostiPrenotati() + ", Posti disponibili: " + (eventoUtente.getNumeroPostiTotali() - eventoUtente.getNumeroPostiPrenotati()));

                                                                            //DISDETTE
        
        System.out.println("Gestione Disdette:");
        System.out.print("Quante prenotazioni vuoi disdire? ");
        int numDisdette = 0;
        try {
            numDisdette = scanner.nextInt();
            scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.err.println("Input non valido per il numero di disdette.");
                scanner.nextLine();
            }

        if (numDisdette > 0) {
            for (int i = 0; i < numDisdette; i++) {
                System.out.println(eventoUtente.disdici());
            }
        } else if (numDisdette == 0) {
            System.out.println("Nessuna disdetta effettuata.");
        } else {
            System.out.println("Il numero di disdette deve essere positivo.");
        }

        System.out.println("\nPosti prenotati: " + eventoUtente.getNumeroPostiPrenotati() + ". Posti disponibili: " + (eventoUtente.getNumeroPostiTotali() - eventoUtente.getNumeroPostiPrenotati()));
        }
          
                                                                    // STEP 3 - CLASSE CONCERTO
            /* 
            Concerto concertoFineAnno = new Concerto("Concerto di Fine anno", LocalDate.of(2025, 12, 31), 200, LocalTime.of(20, 30), 30.00);
            System.out.println("\nConcerto creato: " + concertoFineAnno); 
            System.out.println("Data formattata: " + concertoFineAnno.getDataOraFormattata());
            System.out.println("Prezzo formattato: " + concertoFineAnno.getPrezzoFormattato());

            
            System.out.println(concertoFineAnno.prenota());
            System.out.println(concertoFineAnno.prenota());
            System.out.println("Posti prenotati per concerto: " + concertoFineAnno.getNumeroPostiPrenotati());
            System.out.println(concertoFineAnno.disdici());
            System.out.println("Posti prenotati per concerto: " + concertoFineAnno.getNumeroPostiPrenotati());
            */


        scanner.close();
        
        
    }
}
