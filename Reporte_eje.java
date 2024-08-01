import java.util.List;

public class Reporte_eje {
    private List<Cliente_eje> clientes;
    private int ticketsVendidos;
    private int ticketsDisponibles;
    private double totalGenerado;

    public Reporte_eje(List<Cliente_eje> clientes, int ticketsDisponibles) {
        this.clientes = clientes;
        this.ticketsDisponibles = ticketsDisponibles;
        calcularTotales();
    }

    private void calcularTotales() {
        ticketsVendidos = clientes.size();
        totalGenerado = 0;
        for (Cliente_eje cliente : clientes) {
            totalGenerado += cliente.getBoletosComprados() * 100;
        }
    }

    public void generarReporte() {
        System.out.println("Reporte de Ventas:");
        System.out.println("Tickets Vendidos: " + ticketsVendidos);
        System.out.println("Tickets Disponibles: " + ticketsDisponibles);
        System.out.println("Total Generado: " + totalGenerado);
    }
}
