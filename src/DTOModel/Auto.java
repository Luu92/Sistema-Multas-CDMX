
package DTOModel;

/**
 *
 * @author DevSolutions
 */
public class Auto {
    
    private String placa;
    private String marca;
    private int anio;
    private int id_propietario;

    
    public Auto(String placa, String marca, int anio, int id_propietario) {
        this.placa = placa;
        this.marca = marca;
        this.anio = anio;
        this.id_propietario = id_propietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(int id_propietario) {
        this.id_propietario = id_propietario;
    }

    @Override
    public String toString() {
        System.out.println("\t===== Detalles de Auto =====");
        return "Placa: " + placa + 
               "\nMarca: " + marca + 
               "\nAño: " + anio + 
               "\nId_Propietario=" + id_propietario;
    }
    
    
    
}
