package exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String UUID) {
        super("Record con id " + UUID + " non trovato");
    }
}
