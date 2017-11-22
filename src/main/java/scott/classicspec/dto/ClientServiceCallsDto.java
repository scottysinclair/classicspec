package scott.classicspec.dto;

import scott.barleydb.api.dto.BaseDto;

import java.math.BigDecimal;

/**
 * Generated from Entity Specification
 *
 * @author scott
 */
public class ClientServiceCallsDto extends BaseDto {
  private static final long serialVersionUID = 1L;

  private Long id;
  private Long modifiedAt;
  private ClientDto client;
  private ServiceDto service;
  private BigDecimal cost;

  public ClientServiceCallsDto() {
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

  public ClientDto getClient() {
    return client;
  }

  public void setClient(ClientDto client) {
    this.client = client;
  }

  public ServiceDto getService() {
    return service;
  }

  public void setService(ServiceDto service) {
    this.service = service;
  }

  public BigDecimal getCost() {
    return cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }
  public String toString() {
    return getClass().getSimpleName() + "[id = " + id + "]";
  }
}
