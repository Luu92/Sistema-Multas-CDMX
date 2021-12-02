/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOModel;

/**
 *
 * @author DevSolutions
 */
public class Cita {
    private int id; 
    private String fecha;
    private String hora;
    private String verificentro; 
    private String placa;

    public Cita() {
    }

    public Cita(String fecha, String hora, String verificentro, String placa) {
        this.fecha = fecha;
        this.hora = hora;
        this.verificentro = verificentro;
        this.placa = placa;
    }
    
    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getVerificentro() {
        return verificentro;
    }

    public String getPlaca() {
        return placa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Fecha: " + fecha + 
               "\nHora=" + hora + 
               "\nVerificentro: " + verificentro + 
               "\nPlaca: " + placa;
    }
}
