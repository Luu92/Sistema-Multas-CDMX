/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOModel;

/**
 *
 * @author Luu_PC
 */
public class Multa {
    
    private int folio;
    private String motivo;
    private float precio;
    private String placa;
    private String estado;
    
    public Multa(){
        
    }
    public Multa(int folio, String motivo, float precio, String placa,String estado){
        this.folio  = folio;
        this.motivo = motivo;
        this.precio = precio;
        this.placa  = placa;
        this.estado = estado;
    }
    
    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        System.out.println("\t===== Detalles de Multa =====");
        return    "Folio: "    + folio 
                + "\nMotivo: " + motivo 
                + "\nPrecio: " + precio
                + "\nPlaca: "  + placa
                + "\nEstado: " + estado;
    }
    
}
