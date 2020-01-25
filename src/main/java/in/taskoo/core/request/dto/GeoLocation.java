package in.taskoo.core.request.dto;

import lombok.Data;

@Data
public class GeoLocation {
  private Double longitude;
  private Double latitude;
  private String formattedAddress;
}
