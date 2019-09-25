package com.diviso.graeshoppe.order.client.customer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Date;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NoteDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-09-25T16:27:29.477214+05:30[Asia/Kolkata]")

public class NoteDTO   {
  @JsonProperty("createdDate")
  private LocalDate createdDate = null;

  @JsonProperty("customerId")
  private Long customerId = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("matter")
  private String matter = null;

  public NoteDTO createdDate(LocalDate createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  /**
   * Get createdDate
   * @return createdDate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public NoteDTO customerId(Long customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   * @return customerId
  **/
  @ApiModelProperty(value = "")


  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public NoteDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public NoteDTO matter(String matter) {
    this.matter = matter;
    return this;
  }

  /**
   * Get matter
   * @return matter
  **/
  @ApiModelProperty(value = "")


  public String getMatter() {
    return matter;
  }

  public void setMatter(String matter) {
    this.matter = matter;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NoteDTO noteDTO = (NoteDTO) o;
    return Objects.equals(this.createdDate, noteDTO.createdDate) &&
        Objects.equals(this.customerId, noteDTO.customerId) &&
        Objects.equals(this.id, noteDTO.id) &&
        Objects.equals(this.matter, noteDTO.matter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdDate, customerId, id, matter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NoteDTO {\n");
    
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    matter: ").append(toIndentedString(matter)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

