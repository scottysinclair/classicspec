package scott.classicspec.dto;

import scott.barleydb.api.dto.BaseDto;

import java.math.BigDecimal;

/**
 * Generated from Entity Specification
 *
 * @author scott
 */
public class ServiceDto extends BaseDto {
  private static final long serialVersionUID = 1L;

  private Long id;
  private Long modifiedAt;
  private String name;
  private ApplicationDto application;
  private BigDecimal costPerCall;

  public ServiceDto() {
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

  public ApplicationDto getApplication() {
    return application;
  }

  public void setApplication(ApplicationDto application) {
    this.application = application;
  }

  public BigDecimal getCostPerCall() {
    return costPerCall;
  }

  public void setCostPerCall(BigDecimal costPerCall) {
    this.costPerCall = costPerCall;
  }
  public String toString() {
    return getClass().getSimpleName() + "[id = " + id + "]";
  }
}
