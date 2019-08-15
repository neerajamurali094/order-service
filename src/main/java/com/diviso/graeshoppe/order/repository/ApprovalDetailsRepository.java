package com.diviso.graeshoppe.order.repository;

import com.diviso.graeshoppe.order.domain.ApprovalDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApprovalDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApprovalDetailsRepository extends JpaRepository<ApprovalDetails, Long> {

}
