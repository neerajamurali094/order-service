package com.diviso.graeshoppe.order.client.customer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CustomerDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-09-25T16:27:29.477214+05:30[Asia/Kolkata]")

public class CustomerDTO   {
  @JsonProperty("card")
  private String card = null;

  @JsonProperty("contactId")
  private Long contactId = null;

  @JsonProperty("curDebt")
  private Double curDebt = null;

  @JsonProperty("debtDate")
  private LocalDate debtDate = null;

  @JsonProperty("discount")
  private Double discount = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("maxDebt")
  private Double maxDebt = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("photo")
  private byte[] photo = null;

  @JsonProperty("photoContentType")
  private String photoContentType = null;

  @JsonProperty("reference")
  private String reference = null;

  @JsonProperty("searchKey")
  private String searchKey = null;

  @JsonProperty("visible")
  private Boolean visible = null;

  public CustomerDTO card(String card) {
    this.card = card;
    return this;
  }

  /**
   * Get card
   * @return card
  **/
  @ApiModelProperty(value = "")


  public String getCard() {
    return card;
  }

  public void setCard(String card) {
    this.card = card;
  }

  public CustomerDTO contactId(Long contactId) {
    this.contactId = contactId;
    return this;
  }

  /**
   * Get contactId
   * @return contactId
  **/
  @ApiModelProperty(value = "")


  public Long getContactId() {
    return contactId;
  }

  public void setContactId(Long contactId) {
    this.contactId = contactId;
  }

  public CustomerDTO curDebt(Double curDebt) {
    this.curDebt = curDebt;
    return this;
  }

  /**
   * Get curDebt
   * @return curDebt
  **/
  @ApiModelProperty(value = "")


  public Double getCurDebt() {
    return curDebt;
  }

  public void setCurDebt(Double curDebt) {
    this.curDebt = curDebt;
  }

  public CustomerDTO debtDate(LocalDate debtDate) {
    this.debtDate = debtDate;
    return this;
  }

  /**
   * Get debtDate
   * @return debtDate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getDebtDate() {
    return debtDate;
  }

  public void setDebtDate(LocalDate debtDate) {
    this.debtDate = debtDate;
  }

  public CustomerDTO discount(Double discount) {
    this.discount = discount;
    return this;
  }

  /**
   * Get discount
   * @return discount
  **/
  @ApiModelProperty(value = "")


  public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public CustomerDTO id(Long id) {
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

  public CustomerDTO maxDebt(Double maxDebt) {
    this.maxDebt = maxDebt;
    return this;
  }

  /**
   * Get maxDebt
   * @return maxDebt
  **/
  @ApiModelProperty(value = "")


  public Double getMaxDebt() {
    return maxDebt;
  }

  public void setMaxDebt(Double maxDebt) {
    this.maxDebt = maxDebt;
  }

  public CustomerDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CustomerDTO photo(byte[] photo) {
    this.photo = photo;
    return this;
  }

  /**
   * Get photo
   * @return photo
  **/
  @ApiModelProperty(value = "")

@Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$") 
  public byte[] getPhoto() {
    return photo;
  }

  public void setPhoto(byte[] photo) {
    this.photo = photo;
  }

  public CustomerDTO photoContentType(String photoContentType) {
    this.photoContentType = photoContentType;
    return this;
  }

  /**
   * Get photoContentType
   * @return photoContentType
  **/
  @ApiModelProperty(value = "")


  public String getPhotoContentType() {
    return photoContentType;
  }

  public void setPhotoContentType(String photoContentType) {
    this.photoContentType = photoContentType;
  }

  public CustomerDTO reference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Get reference
   * @return reference
  **/
  @ApiModelProperty(value = "")


  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public CustomerDTO searchKey(String searchKey) {
    this.searchKey = searchKey;
    return this;
  }

  /**
   * Get searchKey
   * @return searchKey
  **/
  @ApiModelProperty(value = "")


  public String getSearchKey() {
    return searchKey;
  }

  public void setSearchKey(String searchKey) {
    this.searchKey = searchKey;
  }

  public CustomerDTO visible(Boolean visible) {
    this.visible = visible;
    return this;
  }

  /**
   * Get visible
   * @return visible
  **/
  @ApiModelProperty(value = "")


  public Boolean isVisible() {
    return visible;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerDTO customerDTO = (CustomerDTO) o;
    return Objects.equals(this.card, customerDTO.card) &&
        Objects.equals(this.contactId, customerDTO.contactId) &&
        Objects.equals(this.curDebt, customerDTO.curDebt) &&
        Objects.equals(this.debtDate, customerDTO.debtDate) &&
        Objects.equals(this.discount, customerDTO.discount) &&
        Objects.equals(this.id, customerDTO.id) &&
        Objects.equals(this.maxDebt, customerDTO.maxDebt) &&
        Objects.equals(this.name, customerDTO.name) &&
        Objects.equals(this.photo, customerDTO.photo) &&
        Objects.equals(this.photoContentType, customerDTO.photoContentType) &&
        Objects.equals(this.reference, customerDTO.reference) &&
        Objects.equals(this.searchKey, customerDTO.searchKey) &&
        Objects.equals(this.visible, customerDTO.visible);
  }

  @Override
  public int hashCode() {
    return Objects.hash(card, contactId, curDebt, debtDate, discount, id, maxDebt, name, photo, photoContentType, reference, searchKey, visible);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerDTO {\n");
    
    sb.append("    card: ").append(toIndentedString(card)).append("\n");
    sb.append("    contactId: ").append(toIndentedString(contactId)).append("\n");
    sb.append("    curDebt: ").append(toIndentedString(curDebt)).append("\n");
    sb.append("    debtDate: ").append(toIndentedString(debtDate)).append("\n");
    sb.append("    discount: ").append(toIndentedString(discount)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    maxDebt: ").append(toIndentedString(maxDebt)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    photo: ").append(toIndentedString(photo)).append("\n");
    sb.append("    photoContentType: ").append(toIndentedString(photoContentType)).append("\n");
    sb.append("    reference: ").append(toIndentedString(reference)).append("\n");
    sb.append("    searchKey: ").append(toIndentedString(searchKey)).append("\n");
    sb.append("    visible: ").append(toIndentedString(visible)).append("\n");
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

