package com.diviso.graeshoppe.order.client.store.model;

import java.util.Objects;
import com.diviso.graeshoppe.order.client.store.model.Store;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Banner
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-09-18T12:35:56.219558+05:30[Asia/Kolkata]")

public class Banner   {
  @JsonProperty("file")
  private byte[] file = null;

  @JsonProperty("fileContentType")
  private String fileContentType = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("store")
  private Store store = null;

  public Banner file(byte[] file) {
    this.file = file;
    return this;
  }

  /**
   * Get file
   * @return file
  **/
  @ApiModelProperty(value = "")

@Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$") 
  public byte[] getFile() {
    return file;
  }

  public void setFile(byte[] file) {
    this.file = file;
  }

  public Banner fileContentType(String fileContentType) {
    this.fileContentType = fileContentType;
    return this;
  }

  /**
   * Get fileContentType
   * @return fileContentType
  **/
  @ApiModelProperty(value = "")


  public String getFileContentType() {
    return fileContentType;
  }

  public void setFileContentType(String fileContentType) {
    this.fileContentType = fileContentType;
  }

  public Banner id(Long id) {
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

  public Banner store(Store store) {
    this.store = store;
    return this;
  }

  /**
   * Get store
   * @return store
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Banner banner = (Banner) o;
    return Objects.equals(this.file, banner.file) &&
        Objects.equals(this.fileContentType, banner.fileContentType) &&
        Objects.equals(this.id, banner.id) &&
        Objects.equals(this.store, banner.store);
  }

  @Override
  public int hashCode() {
    return Objects.hash(file, fileContentType, id, store);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Banner {\n");
    
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    fileContentType: ").append(toIndentedString(fileContentType)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    store: ").append(toIndentedString(store)).append("\n");
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

