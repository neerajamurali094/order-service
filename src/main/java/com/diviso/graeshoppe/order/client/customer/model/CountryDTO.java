package com.diviso.graeshoppe.order.client.customer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CountryDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-09-19T14:05:40.905965+05:30[Asia/Kolkata]")

public class CountryDTO   {
  @JsonProperty("code")
  private String code = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("phonecode")
  private Integer phonecode = null;

  public CountryDTO code(String code) {
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

  public CountryDTO id(Long id) {
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

  public CountryDTO name(String name) {
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

  public CountryDTO phonecode(Integer phonecode) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CountryDTO countryDTO = (CountryDTO) o;
    return Objects.equals(this.code, countryDTO.code) &&
        Objects.equals(this.id, countryDTO.id) &&
        Objects.equals(this.name, countryDTO.name) &&
        Objects.equals(this.phonecode, countryDTO.phonecode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, id, name, phonecode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CountryDTO {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    phonecode: ").append(toIndentedString(phonecode)).append("\n");
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

