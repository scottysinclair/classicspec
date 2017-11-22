package scott.classicspec.dto;

import scott.barleydb.api.dto.BaseDto;


import scott.classicspec.model.ClientTyp;

/**
 * Generated from Entity Specification
 *
 * @author scott
 */
public class ClientDto extends BaseDto {
  private static final long serialVersionUID = 1L;

  private Long id;
  private Long modifiedAt;
  private String name;
  private ClientTyp type;
  private BillingAddressDto billingAddress;

  public ClientDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(Long modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public scott.classicspec.model.ClientTyp getType() {
    return type;
  }

  public void setType(scott.classicspec.model.ClientTyp type) {
    this.type = type;
  }

  public BillingAddressDto getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(BillingAddressDto billingAddress) {
    this.billingAddress = billingAddress;
  }
  public String toString() {
    return getClass().getSimpleName() + "[id = " + id + "]";
  }
}
