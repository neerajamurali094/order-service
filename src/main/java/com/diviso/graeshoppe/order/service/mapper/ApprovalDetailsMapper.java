package com.diviso.graeshoppe.order.service.mapper;

import com.diviso.graeshoppe.order.domain.*;
import com.diviso.graeshoppe.order.service.dto.ApprovalDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ApprovalDetails and its DTO ApprovalDetailsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ApprovalDetailsMapper extends EntityMapper<ApprovalDetailsDTO, ApprovalDetails> {



    default ApprovalDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApprovalDetails approvalDetails = new ApprovalDetails();
        approvalDetails.setId(id);
        return approvalDetails;
    }
}
