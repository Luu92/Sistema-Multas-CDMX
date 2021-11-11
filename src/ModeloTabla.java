/**
 *
 * @author DevSolution
 */
public class ModeloTabla {
    String numCita,fecha,hora,verificentro;

    public ModeloTabla(String numCita, String fecha, String hora, String verificentro) {
        this.numCita = numCita;
        this.fecha = fecha;
        this.hora = hora;
        this.verificentro = verificentro;
    }

    public String getVerificentro() {
        return verificentro;
    }

    public void setVerificentro(String verificentro) {
        this.verificentro = verificentro;
    }

    public String getNumCita() {
        return numCita;
    }

    public void setNumCita(String numCita) {
        this.numCita = numCita;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }    
}
