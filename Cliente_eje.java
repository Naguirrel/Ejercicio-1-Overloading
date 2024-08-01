import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente_eje {
    private String correlativo;
    private String nombre;
    private String email;
    private double presupuesto;
    private int boletosComprados;
    private int localidadId;

    public Cliente_eje(String nombre, String email, double presupuesto, int boletosComprados, int localidadId) {
        this.nombre = nombre;
        this.email = email;
        this.presupuesto = presupuesto;
        this.boletosComprados = boletosComprados;
        this.localidadId = localidadId;
        this.correlativo = generarCorrelativo();
    }

    private String generarCorrelativo() {
        String fecha = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int numeroRegistros = contarRegistros("clientes.csv");
        return fecha + (numeroRegistros + 1);
    }

    private int contarRegistros(String archivo) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while (br.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void registrarCliente(String archivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(correlativo + "," + nombre + "," + email + "," + presupuesto + "," + boletosComprados + "," + localidadId);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public int getBoletosComprados() {
        return boletosComprados;
    }

    public int getLocalidadId() {
        return localidadId;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "correlativo='" + correlativo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", presupuesto=" + presupuesto +
                ", boletosComprados=" + boletosComprados +
                ", localidadId=" + localidadId +
                '}';
    }
}
