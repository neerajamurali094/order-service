package com.diviso.graeshoppe.order.client.customer.model;

import java.util.Objects;
import com.diviso.graeshoppe.order.client.customer.model.Address;
import com.diviso.graeshoppe.order.client.customer.model.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Country
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-09-25T16:27:29.477214+05:30[Asia/Kolkata]")

public class Country   {
  @JsonProperty("addresses")
  @Valid
  private List<Address> addresses = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("phonecode")
  private Integer phonecode = null;

  @JsonProperty("states")
  @Valid
  private List<State> states = null;

  public Country addresses(List<Address> addresses) {
    this.addresses = addresses;
    return this;
  }

  public Country addAddressesItem(Address addressesItem) {
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

  public Country code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  **/
  @ApiModelProperty(value = "")


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Country id(Long id) {
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

  public Country name(String name) {
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

  public Country phonecode(Integer phonecode) {
    this.phonecode = phonecode;
    return this;
  }

  /**
   * Get phonecode
   * @return phonecode
  **/
  @ApiModelProperty(value = "")


  public Integer getPhonecode() {
    return phonecode;
  }

  public void setPhonecode(Integer phonecode) {
    this.phonecode = phonecode;
  }

  public Country states(List<State> states) {
    this.states = states;
    return this;
  }

  public Country addStatesItem(State statesItem) {
    if (this.states == null) {
      this.states = new ArrayList<State>();
    }
    this.states.add(statesItem);
    return this;
  }

  /**
   * Get states
   * @return states
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<State> getStates() {
    return states;
  }

  public void setStates(List<State> states) {
    this.states = states;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Country country = (Country) o;
    return Objects.equals(this.addresses, country.addresses) &&
        Objects.equals(this.code, country.code) &&
        Objects.equals(this.id, country.id) &&
        Objects.equals(this.name, country.name) &&
        Objects.equals(this.phonecode, country.phonecode) &&
        Objects.equals(this.states, country.states);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addresses, code, id, name, phonecode, states);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Country {\n");
    
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    phonecode: ").append(toIndentedString(phonecode)).append("\n");
    sb.append("    states: ").append(toIndentedString(states)).append("\n");
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

