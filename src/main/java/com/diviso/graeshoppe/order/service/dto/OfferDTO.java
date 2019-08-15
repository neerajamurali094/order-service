package com.diviso.graeshoppe.order.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Offer entity.
 */
public class OfferDTO implements Serializable {

    private Long id;

    private String offerRef;


    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfferRef() {
        return offerRef;
    }

    public void setOfferRef(String offerRef) {
        this.offerRef = offerRef;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OfferDTO offerDTO = (OfferDTO) o;
        if (offerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), offerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OfferDTO{" +
            "id=" + getId() +
            ", offerRef='" + getOfferRef() + "'" +
            ", order=" + getOrderId() +
            "}";
    }
}
