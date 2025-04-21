package vip.fairy.service;

import java.util.List;
import vip.fairy.domain.Reservation;

public interface ReservationService {

  public List<Reservation> query(String courtName);


}
