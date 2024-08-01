import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class compra_eje {
    private List<Cliente_eje> clientes;
    private int[] localidadesDisponibles;
    private int[] boletosVendidosPorLocalidad;

    public compra_eje() {
        this.clientes = new ArrayList<>();
        this.localidadesDisponibles = new int[] {200, 200, 200};
        this.boletosVendidosPorLocalidad = new int[] {0, 0, 0};
    }

    public boolean verificarDisponibilidad(int localidadId, int cantidad) {
        if (localidadId < 1 || localidadId > 3) {
            return false;
        }
        return cantidad <= localidadesDisponibles[localidadId - 1];
    }

    public boolean procesarCompra(String nombre, String email, double presupuesto, int boletosComprados, int localidadId) {
        Localidad localidad = Localidad.getById(localidadId);
        if (localidad == null) {
            System.out.println("Localidad no válida.");
            return false;
        }

        double costoTotal = boletosComprados * localidad.getPrecio();

        if (verificarDisponibilidad(localidadId, boletosComprados) && (presupuesto >= costoTotal)) {
            localidadesDisponibles[localidadId - 1] -= boletosComprados;
            boletosVendidosPorLocalidad[localidadId - 1] += boletosComprados;
            Cliente_eje cliente = new Cliente_eje(nombre, email, presupuesto, boletosComprados, localidadId);
            cliente.registrarCliente("clientes.csv");
            clientes.add(cliente);
            System.out.println("Compra realizada exitosamente para: " + nombre);
            return true;
        } else {
            System.out.println("Compra fallida. Verifique disponibilidad y presupuesto.");
            return false;
        }
    }

    public void generarReporte() {
        int ticketsVendidos = 0;
        double totalGenerado = 0;
        for (Cliente_eje cliente : clientes) {
            Localidad localidad = Localidad.getById(cliente.getLocalidadId());
            ticketsVendidos += cliente.getBoletosComprados();
            totalGenerado += cliente.getBoletosComprados() * localidad.getPrecio();
        }

        System.out.println("Reporte de Ventas:");
        System.out.println("Tickets Vendidos: " + ticketsVendidos);
        System.out.println("Tickets Disponibles por Localidad:");
        for (Localidad loc : Localidad.values()) {
            System.out.println(loc.getNombre() + ": " + localidadesDisponibles[loc.getId() - 1]);
        }
        System.out.println("Total Generado: " + totalGenerado);
    }

    public void consultarDisponibilidadTotal() {
        System.out.println("Disponibilidad Total:");
        for (Localidad loc : Localidad.values()) {
            System.out.println(loc.getNombre() + ":");
            System.out.println("  Boletos Vendidos: " + boletosVendidosPorLocalidad[loc.getId() - 1]);
            System.out.println("  Boletos Disponibles: " + localidadesDisponibles[loc.getId() - 1]);
        }
    }

    public void consultarDisponibilidadIndividual(int localidadId) {
        Localidad localidad = Localidad.getById(localidadId);
        if (localidad != null) {
            System.out.println("Disponibilidad para " + localidad.getNombre() + ":");
            System.out.println("  Boletos Vendidos: " + boletosVendidosPorLocalidad[localidadId - 1]);
            System.out.println("  Boletos Disponibles: " + localidadesDisponibles[localidadId - 1]);
        } else {
            System.out.println("Localidad no válida.");
        }
    }

    public void escribirTicketsDisponiblesCSV(String archivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Localidad loc : Localidad.values()) {
                bw.write(loc.getNombre() + "," + localidadesDisponibles[loc.getId() - 1]);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
