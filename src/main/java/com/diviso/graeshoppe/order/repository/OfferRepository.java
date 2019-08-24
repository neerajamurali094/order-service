package com.diviso.graeshoppe.order.repository;

import com.diviso.graeshoppe.order.domain.Offer;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Offer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

	List<Offer> findByOrder_Id(Long id);
}
