package vip.fairy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class City {

  @Id
  private Long Id;

  private String Name;
  private String CountryCode;
  private String District;
  private Long Population;
}
