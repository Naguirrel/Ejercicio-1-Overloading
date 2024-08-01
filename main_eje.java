import java.util.Scanner;

public class main_eje {
    public static void main(String[] args) {
        compra_eje compra = new compra_eje();
        Scanner scanner = new Scanner(System.in);
        int t = 0;

        while (t == 0) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("\nMenú:\n1. Realizar Compra\n2. Consultar Disponibilidad Total\n3. Consultar Disponibilidad Individual\n4. Generar Reporte\n5. Salir\nSelecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Presupuesto: ");
                    double presupuesto = scanner.nextDouble();
                    System.out.print("Número de Boletos: ");
                    int boletosComprados = scanner.nextInt();
                    System.out.print("ID de Localidad (1: Balcón 2, 2: Platea, 3: VIP): ");
                    int localidadId = scanner.nextInt();
                    scanner.nextLine();
                    compra.procesarCompra(nombre, email, presupuesto, boletosComprados, localidadId);
                    break;
                case 2:
                    compra.consultarDisponibilidadTotal();
                    break;
                case 3:
                    System.out.print("ID de Localidad (1: Balcón 2, 2: Platea, 3: VIP): ");
                    int idLocalidad = scanner.nextInt();
                    scanner.nextLine();
                    compra.consultarDisponibilidadIndividual(idLocalidad);
                    break;
                case 4:
                    compra.generarReporte();
                    break;
                case 5:
                    System.out.println("Gracias :)");
                    scanner.close();
                    t = 1;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        }
    }
}
