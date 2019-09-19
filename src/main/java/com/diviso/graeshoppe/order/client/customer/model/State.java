package com.diviso.graeshoppe.order.client.customer.model;

import java.util.Objects;
import com.diviso.graeshoppe.order.client.customer.model.City;
import com.diviso.graeshoppe.order.client.customer.model.Country;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * State
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-09-19T14:05:40.905965+05:30[Asia/Kolkata]")

public class State   {
  @JsonProperty("cities")
  @Valid
  private List<City> cities = null;

  @JsonProperty("country")
  private Country country = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  public State cities(List<City> cities) {
    this.cities = cities;
    return this;
  }

  public State addCitiesItem(City citiesItem) {
    if (this.cities == null) {
      this.cities = new ArrayList<City>();
    }
    this.cities.add(citiesItem);
    return this;
  }

  /**
   * Get cities
   * @return cities
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<City> getCities() {
    return cities;
  }

  public void setCities(List<City> cities) {
    this.cities = cities;
  }

  public State country(Country country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public State id(Long id) {
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

  public State name(String name) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    State state = (State) o;
    return Objects.equals(this.cities, state.cities) &&
        Objects.equals(this.country, state.country) &&
        Objects.equals(this.id, state.id) &&
        Objects.equals(this.name, state.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cities, country, id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class State {\n");
    
    sb.append("    cities: ").append(toIndentedString(cities)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

