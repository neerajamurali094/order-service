package com.diviso.graeshoppe.order.client.customer.model;

import java.util.Objects;
import com.diviso.graeshoppe.order.client.customer.model.Address;
import com.diviso.graeshoppe.order.client.customer.model.Contact;
import com.diviso.graeshoppe.order.client.customer.model.FavouriteProduct;
import com.diviso.graeshoppe.order.client.customer.model.FavouriteStore;
import com.diviso.graeshoppe.order.client.customer.model.Note;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Customer
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-09-25T16:27:29.477214+05:30[Asia/Kolkata]")

public class Customer   {
  @JsonProperty("addresses")
  @Valid
  private List<Address> addresses = null;

  @JsonProperty("card")
  private String card = null;

  @JsonProperty("contact")
  private Contact contact = null;

  @JsonProperty("curDebt")
  private Double curDebt = null;

  @JsonProperty("debtDate")
  private LocalDate debtDate = null;

  @JsonProperty("discount")
  private Double discount = null;

  @JsonProperty("favouriteproducts")
  @Valid
  private List<FavouriteProduct> favouriteproducts = null;

  @JsonProperty("favouritestores")
  @Valid
  private List<FavouriteStore> favouritestores = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("maxDebt")
  private Double maxDebt = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("notes")
  @Valid
  private List<Note> notes = null;

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

  public Customer addresses(List<Address> addresses) {
    this.addresses = addresses;
    return this;
  }

  public Customer addAddressesItem(Address addressesItem) {
    if (this.addresses == null) {
      this.addresses = new ArrayList<Address>();
    }
    this.addresses.add(addressesItem);
    return this;
  }

  /**
   * Get addresses
   * @return addresses
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public Customer card(String card) {
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

  public Customer contact(Contact contact) {
    this.contact = contact;
    return this;
  }

  /**
   * Get contact
   * @return contact
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public Customer curDebt(Double curDebt) {
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

  public Customer debtDate(LocalDate debtDate) {
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

  public Customer discount(Double discount) {
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

  public Customer favouriteproducts(List<FavouriteProduct> favouriteproducts) {
    this.favouriteproducts = favouriteproducts;
    return this;
  }

  public Customer addFavouriteproductsItem(FavouriteProduct favouriteproductsItem) {
    if (this.favouriteproducts == null) {
      this.favouriteproducts = new ArrayList<FavouriteProduct>();
    }
    this.favouriteproducts.add(favouriteproductsItem);
    return this;
  }

  /**
   * Get favouriteproducts
   * @return favouriteproducts
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<FavouriteProduct> getFavouriteproducts() {
    return favouriteproducts;
  }

  public void setFavouriteproducts(List<FavouriteProduct> favouriteproducts) {
    this.favouriteproducts = favouriteproducts;
  }

  public Customer favouritestores(List<FavouriteStore> favouritestores) {
    this.favouritestores = favouritestores;
    return this;
  }

  public Customer addFavouritestoresItem(FavouriteStore favouritestoresItem) {
    if (this.favouritestores == null) {
      this.favouritestores = new ArrayList<FavouriteStore>();
    }
    this.favouritestores.add(favouritestoresItem);
    return this;
  }

  /**
   * Get favouritestores
   * @return favouritestores
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<FavouriteStore> getFavouritestores() {
    return favouritestores;
  }

  public void setFavouritestores(List<FavouriteStore> favouritestores) {
    this.favouritestores = favouritestores;
  }

  public Customer id(Long id) {
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

  public Customer maxDebt(Double maxDebt) {
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

  public Customer name(String name) {
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

  public Customer notes(List<Note> notes) {
    this.notes = notes;
    return this;
  }

  public Customer addNotesItem(Note notesItem) {
    if (this.notes == null) {
      this.notes = new ArrayList<Note>();
    }
    this.notes.add(notesItem);
    return this;
  }

  /**
   * Get notes
   * @return notes
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Note> getNotes() {
    return notes;
  }

  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  public Customer photo(byte[] photo) {
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

  public Customer photoContentType(String photoContentType) {
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

  public Customer reference(String reference) {
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

  public Customer searchKey(String searchKey) {
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

  public Customer visible(Boolean visible) {
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
    Customer customer = (Customer) o;
    return Objects.equals(this.addresses, customer.addresses) &&
        Objects.equals(this.card, customer.card) &&
        Objects.equals(this.contact, customer.contact) &&
        Objects.equals(this.curDebt, customer.curDebt) &&
        Objects.equals(this.debtDate, customer.debtDate) &&
        Objects.equals(this.discount, customer.discount) &&
        Objects.equals(this.favouriteproducts, customer.favouriteproducts) &&
        Objects.equals(this.favouritestores, customer.favouritestores) &&
        Objects.equals(this.id, customer.id) &&
        Objects.equals(this.maxDebt, customer.maxDebt) &&
        Objects.equals(this.name, customer.name) &&
        Objects.equals(this.notes, customer.notes) &&
        Objects.equals(this.photo, customer.photo) &&
        Objects.equals(this.photoContentType, customer.photoContentType) &&
        Objects.equals(this.reference, customer.reference) &&
        Objects.equals(this.searchKey, customer.searchKey) &&
        Objects.equals(this.visible, customer.visible);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addresses, card, contact, curDebt, debtDate, discount, favouriteproducts, favouritestores, id, maxDebt, name, notes, photo, photoContentType, reference, searchKey, visible);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Customer {\n");
    
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
    sb.append("    card: ").append(toIndentedString(card)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
    sb.append("    curDebt: ").append(toIndentedString(curDebt)).append("\n");
    sb.append("    debtDate: ").append(toIndentedString(debtDate)).append("\n");
    sb.append("    discount: ").append(toIndentedString(discount)).append("\n");
    sb.append("    favouriteproducts: ").append(toIndentedString(favouriteproducts)).append("\n");
    sb.append("    favouritestores: ").append(toIndentedString(favouritestores)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    maxDebt: ").append(toIndentedString(maxDebt)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
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

