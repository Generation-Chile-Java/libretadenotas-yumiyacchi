import java.util.*;

public class LibretaDeNotas {
    private HashMap<String, ArrayList<Double>> notasEstudiantes;

    public LibretaDeNotas() {
        this.notasEstudiantes = new HashMap<>();
    }

    public void agregarNotas(String nombre, ArrayList<Double> notas) {
        notasEstudiantes.put(nombre, notas);
    }

    public void agregarNotaIndividual(String nombre, double nota) {
        notasEstudiantes.getOrDefault(nombre, new ArrayList<>()).add(nota);
    }

    public void mostrarNotas() {
        for (Map.Entry<String, ArrayList<Double>> entry : notasEstudiantes.entrySet()) {
            double promedio = entry.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            System.out.println("Estudiante: " + entry.getKey() + " - Notas: " + entry.getValue() + " - Promedio: " + promedio);
        }
    }

    public double calcularPromedio(String nombre) {
        return notasEstudiantes.getOrDefault(nombre, new ArrayList<>())
                .stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public boolean cumpleNotaAprobacion(String nombre) {
        return calcularPromedio(nombre) >= 4.5;
    }

    public double obtenerNotaMasAlta(String nombre) {
        return notasEstudiantes.getOrDefault(nombre, new ArrayList<>())
                .stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
    }

    public double obtenerNotaMasBaja(String nombre) {
        return notasEstudiantes.getOrDefault(nombre, new ArrayList<>())
                .stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibretaDeNotas libreta = new LibretaDeNotas();
        int opcion;

        do {
            System.out.println("\nMenu de opciones:");
            System.out.println("1. Agregar alumnos y notas");
            System.out.println("2. Agregar nota a un alumno");
            System.out.println("3. Calcular promedio de un alumno");
            System.out.println("4. Verificar si un alumno aprueba");
            System.out.println("5. Obtener nota más alta de un alumno");
            System.out.println("6. Obtener nota más baja de un alumno");
            System.out.println("7. Mostrar todas las notas");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la cantidad de alumnos: ");
                    int cantidadAlumnos = scanner.nextInt();
                    System.out.print("Ingrese la cantidad de notas por alumno: ");
                    int cantidadNotas = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < cantidadAlumnos; i++) {
                        System.out.print("Ingrese el nombre del estudiante: ");
                        String nombre = scanner.nextLine();
                        ArrayList<Double> notas = new ArrayList<>();

                        for (int j = 0; j < cantidadNotas; j++) {
                            System.out.print("Ingrese la nota " + (j + 1) + ": ");
                            notas.add(scanner.nextDouble());
                        }
                        scanner.nextLine();
                        libreta.agregarNotas(nombre, notas);
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el nombre del estudiante: ");
                    String nombreNota = scanner.nextLine();
                    System.out.print("Ingrese la nueva nota: ");
                    double nuevaNota = scanner.nextDouble();
                    scanner.nextLine();
                    libreta.agregarNotaIndividual(nombreNota, nuevaNota);
                    break;

                case 3:
                    System.out.print("Ingrese el nombre del estudiante: ");
                    String nombrePromedio = scanner.nextLine();
                    System.out.println("Promedio: " + libreta.calcularPromedio(nombrePromedio));
                    break;

                case 4:
                    System.out.print("Ingrese el nombre del estudiante: ");
                    String nombreAprobacion = scanner.nextLine();
                    if (libreta.cumpleNotaAprobacion(nombreAprobacion)) {
                        System.out.println("El estudiante aprueba.");
                    } else {
                        System.out.println("El estudiante no aprueba.");
                    }
                    break;

                case 5:
                    System.out.print("Ingrese el nombre del estudiante: ");
                    String nombreAlta = scanner.nextLine();
                    System.out.println("Nota más alta: " + libreta.obtenerNotaMasAlta(nombreAlta));
                    break;

                case 6:
                    System.out.print("Ingrese el nombre del estudiante: ");
                    String nombreBaja = scanner.nextLine();
                    System.out.println("Nota más baja: " + libreta.obtenerNotaMasBaja(nombreBaja));
                    break;

                case 7:
                    libreta.mostrarNotas();
                    break;

                case 8:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida, intente de nuevo.");
                    break;
            }
        } while (opcion != 8);

        scanner.close();
    }
}
