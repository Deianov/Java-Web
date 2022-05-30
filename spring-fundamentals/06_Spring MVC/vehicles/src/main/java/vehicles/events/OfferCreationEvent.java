package vehicles.events;

import vehicles.model.Offer;

public class OfferCreationEvent {
    private final Offer offer;

    public OfferCreationEvent(Offer offer) {
        this.offer = offer;
    }

    public Offer getOffer() {
        return offer;
    }
}
