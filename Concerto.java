import java.time.LocalDate;
import java.time.LocalTime; 
import java.time.format.DateTimeFormatter; 

public class Concerto extends Evento {
    
    private LocalTime ora;
    private double prezzo;

    
    public Concerto(String titolo, LocalDate data, int numeroPostiTotali, LocalTime ora, double prezzo) {
        super(titolo, data, numeroPostiTotali);
        this.ora = ora;
    
                                //AGGIUNTA IMPOSSIBILITA' DI CONSIDERARE UN PREZZO IN NEGATIVO
        if (prezzo < 0) {
            throw new IllegalArgumentException("Errore: Il prezzo del concerto non può essere negativo.");
        }
        this.prezzo = prezzo;
    }

                                                //GETTER & SETTER
    public LocalTime getOra() {
        return ora;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public void setPrezzo(double prezzo) {
        if (prezzo < 0) {
            throw new IllegalArgumentException("Errore: Il prezzo del concerto non può essere negativo.");
        }
        this.prezzo = prezzo;
    }

                                                    // DATA E ORA FORMATTATE

    public String getDataOraFormattata() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return getData().atTime(ora).format(formatter);
    }

                                                    //PREZZO FORMATTATO
    public String getPrezzoFormattato() {
        return String.format("%.2f€", prezzo);
    }

   
    @Override
    public String toString() {
        return getDataOraFormattata() + " - " + getTitolo() + " - " + getPrezzoFormattato();
    }
}
