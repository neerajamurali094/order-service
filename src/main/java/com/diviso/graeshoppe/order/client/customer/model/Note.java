package com.diviso.graeshoppe.order.client.customer.model;

import java.util.Objects;
import com.diviso.graeshoppe.order.client.customer.model.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Note
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-09-25T16:27:29.477214+05:30[Asia/Kolkata]")

public class Note   {
  @JsonProperty("createdDate")
  private LocalDate createdDate = null;

  @JsonProperty("customer")
  private Customer customer = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("matter")
  private String matter = null;

  public Note createdDate(LocalDate createdDate) {
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

  public Note customer(Customer customer) {
    this.customer = customer;
    return this;
  }

  /**
   * Get customer
   * @return customer
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Note id(Long id) {
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

  public Note matter(String matter) {
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
    Note note = (Note) o;
    return Objects.equals(this.createdDate, note.createdDate) &&
        Objects.equals(this.customer, note.customer) &&
        Objects.equals(this.id, note.id) &&
        Objects.equals(this.matter, note.matter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdDate, customer, id, matter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Note {\n");
    
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
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

