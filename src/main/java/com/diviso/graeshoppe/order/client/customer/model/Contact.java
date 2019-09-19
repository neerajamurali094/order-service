package com.diviso.graeshoppe.order.client.customer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * Contact
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-09-19T14:05:40.905965+05:30[Asia/Kolkata]")

public class Contact   {
  @JsonProperty("email")
  private String email = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("mobileNumber")
  private String mobileNumber = null;

  @JsonProperty("phoneCode")
  private String phoneCode = null;

  @JsonProperty("telephone")
  private String telephone = null;

  public Contact email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Contact id(Long id) {
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

  public Contact mobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  /**
   * Get mobileNumber
   * @return mobileNumber
  **/
  @ApiModelProperty(value = "")


  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public Contact phoneCode(String phoneCode) {
    this.phoneCode = phoneCode;
    return this;
  }

  /**
   * Get phoneCode
   * @return phoneCode
  **/
  @ApiModelProperty(value = "")


  public String getPhoneCode() {
    return phoneCode;
  }

  public void setPhoneCode(String phoneCode) {
    this.phoneCode = phoneCode;
  }

  public Contact telephone(String telephone) {
    this.telephone = telephone;
    return this;
  }

  /**
   * Get telephone
   * @return telephone
  **/
  @ApiModelProperty(value = "")


  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contact contact = (Contact) o;
    return Objects.equals(this.email, contact.email) &&
        Objects.equals(this.id, contact.id) &&
        Objects.equals(this.mobileNumber, contact.mobileNumber) &&
        Objects.equals(this.phoneCode, contact.phoneCode) &&
        Objects.equals(this.telephone, contact.telephone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, id, mobileNumber, phoneCode, telephone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Contact {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    mobileNumber: ").append(toIndentedString(mobileNumber)).append("\n");
    sb.append("    phoneCode: ").append(toIndentedString(phoneCode)).append("\n");
    sb.append("    telephone: ").append(toIndentedString(telephone)).append("\n");
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

