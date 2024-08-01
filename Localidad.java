public enum Localidad {
    BALCON_2(1, "Balcón 2", 300, 200),
    PLATEA(2, "Platea", 600, 200),
    VIP(3, "Balcón 1 o VIP", 1800, 200);

    private int id;
    private String nombre;
    private double precio;
    private int capacidad;

    Localidad(int id, String nombre, double precio, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public static Localidad getById(int id) {
        for (Localidad loc : values()) {
            if (loc.getId() == id) {
                return loc;
            }
        }
        return null;
    }
}

