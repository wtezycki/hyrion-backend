package pl.hyrion.hyrionbackend.offer.command.domain.exception;

public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException(String message) {
        super(message);
    }
}
