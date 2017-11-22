package scott.classicspec.dto;

import scott.barleydb.api.dto.BaseDto;

import scott.barleydb.api.dto.DtoList;


/**
 * Generated from Entity Specification
 *
 * @author scott
 */
public class ApplicationDto extends BaseDto {
  private static final long serialVersionUID = 1L;

  private Long id;
  private Long modifiedAt;
  private String name;
  private DtoList<ServiceDto> services = new DtoList<>();

  public ApplicationDto() {
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

  public DtoList<ServiceDto> getServices() {
    return services;
  }
  public String toString() {
    return getClass().getSimpleName() + "[id = " + id + "]";
  }
}
