import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
    public String titolo;
    public LocalDate data;
    private int numeroPostiTotali;
    private int numeroPostiPrenotati;


    public Evento(String titolo, LocalDate data, int numeroPostiTotali) {

        dataNonPassata(data);
        postiTotaliSoloPositivo(numeroPostiTotali);
        
        this.titolo = titolo;
        this.data = data;
        this.numeroPostiTotali = numeroPostiTotali;
        this.numeroPostiPrenotati = 0;
    }

     //ECCEZIONE PER DATA GIA' PASSATA
     private void dataNonPassata(LocalDate data) {
        if(data.isBefore(LocalDate.now())) {
        throw new IllegalArgumentException("Errore: La data dell'evento (" + data + ") risulta già essere passata.");
        }
    }

     // ECCEZIONE PER NUMERO POSTI ESCLUSIVAMENTE POSITIVO
    private void postiTotaliSoloPositivo(int numeroPostiTotali) {
        if (numeroPostiTotali <= 0){
        throw new IllegalArgumentException("Errore: Il numero dei posti totali (" + numeroPostiTotali + ") deve essere positivo.");
        }
    }

    //GETTER & SETTER

    public String getTitolo() {
        return this.titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getNumeroPostiTotali() {
        return this.numeroPostiTotali;
    }

    public int getNumeroPostiPrenotati() {
        return this.numeroPostiPrenotati;
    }


    //PRENOTAZIONI E DISDETTE CON CONTROLLI
    public String prenota() {
        if (this.data.isBefore(LocalDate.now())) {
            return "Avviso: Impossibile prenotare. L'evento '" + this.titolo + "' è già passato.";
        }

        if (numeroPostiPrenotati > numeroPostiTotali) {
            return "Avviso: Impossibile prenotare. L'evento '" + this.titolo + "' è al completo.";
        }
        numeroPostiPrenotati++;
        return "Prenotazione effettuata con successo per l'evento: " + this.titolo +  ". Posti prenotati: " + numeroPostiPrenotati;
    }

    public String disdici() {
        if (this.data.isBefore(LocalDate.now())){
            return "Avviso: Impossibile disdire. L'evento '" + this.titolo + "' è già passato.";
        }

        if (numeroPostiPrenotati <= 0){
            return "Avviso: Impossibile disdire. Non ci sono prenotazioni attive per l'evento '" + this.titolo + "'.";
        }

        numeroPostiPrenotati--;
        return "Disdetta effettuata con successo per l'evento '" + this.titolo + "'. Posti prenotati: " + numeroPostiPrenotati;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter) + " - " + titolo;
    }
}
