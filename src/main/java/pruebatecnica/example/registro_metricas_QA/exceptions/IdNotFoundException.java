package pruebatecnica.example.registro_metricas_QA.exceptions;

public class IdNotFoundException extends RuntimeException {
  private static final String ERROR_MESSAGE = "No hay registros en la entidad %s con el id sumunistrado";
    public IdNotFoundException(String message) {
        super(message);
    }
}
