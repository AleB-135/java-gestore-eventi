import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
    private String titolo;
    private LocalDate data;
    private final int numeroPostiTotali; 
    private int numeroPostiPrenotati;

    public Evento(String titolo, LocalDate data, int numeroPostiTotali) {
        
        dataNonPassata(data);
        numeroPostiPositivo(numeroPostiTotali);

        this.titolo = titolo;
        this.data = data;
        this.numeroPostiTotali = numeroPostiTotali;
        this.numeroPostiPrenotati = 0;
    }

                                                            //GETTER & SETTER
    public String getTitolo() {
        return titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public int getNumeroPostiTotali() {
        return numeroPostiTotali;
    }

    public int getNumeroPostiPrenotati() {
        return numeroPostiPrenotati;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setData(LocalDate data) {
        dataNonPassata(data);
        this.data = data;
    }

                                             //PRENOTAZIONI E DISDETTE CON ECCEZIONI
    public String prenota() {
        if (this.data.isBefore(LocalDate.now())) {
            return "Avviso: Impossibile prenotare. L'evento '" + this.titolo + "' è già passato.";
        }

        
        if (numeroPostiPrenotati >= numeroPostiTotali) {
            return "Avviso: Impossibile prenotare. L'evento '" + this.titolo + "' è al completo.";
        }

        numeroPostiPrenotati++;
        return "Prenotazione effettuata con successo per l'evento '" + this.titolo + "'. Posti prenotati: " + numeroPostiPrenotati + "/" + numeroPostiTotali + ".";
    }

   
    public String disdici() {
        
        if (this.data.isBefore(LocalDate.now())) {
            return "Avviso: Impossibile disdire. L'evento '" + this.titolo + "' è già passato.";
        }

        if (numeroPostiPrenotati <= 0) {
            return "Avviso: Impossibile disdire. Non ci sono prenotazioni attive per l'evento '" + this.titolo + "'.";
        }

        numeroPostiPrenotati--;
        return "Disdetta effettuata con successo per l'evento '" + this.titolo + "'. Posti prenotati: " + numeroPostiPrenotati + "/" + numeroPostiTotali + ".";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter) + " - " + titolo;
    }
   

                                                //CONTROLLO PER DATA GIA' PASSATA
    private void dataNonPassata(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Errore: La data dell'evento (" + data + ") risulta già essere passata.");
        }
    }

                                         // CONTROLLO PER NUMERO POSTI ESCLUSIVAMENTE POSITIVO
    private void numeroPostiPositivo(int postiTotali) {
        if (postiTotali <= 0) {
            throw new IllegalArgumentException("Errore: Il numero di posti totali (" + postiTotali + ") deve essere un numero positivo.");
        }
    }
}

