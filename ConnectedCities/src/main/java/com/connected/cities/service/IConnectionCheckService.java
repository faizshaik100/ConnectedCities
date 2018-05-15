package com.connected.cities.service;

public interface IConnectionCheckService {

  /**
   * Checks connection between the two cities.
   * 
   * @param origin
   *          origin city name
   * @param destination
   *          destination city name
   * @return yes, if connection exists. <br>
   *         no, if connection doesn't exists.
   */
  String checkConnection(String origin, String destination);

}
