package com.connected.cities.model;

import java.io.Serializable;

public class ConnectedRoutes implements Serializable {

  private static final long serialVersionUID = -3402755788859691656L;

  private String origin;
  private String destination;

  public ConnectedRoutes() {

  }

  public ConnectedRoutes(String origin, String destination) {
    super();
    setOrigin(origin);
    setDestination(destination);
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin.trim();
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination.trim();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((destination == null) ? 0 : destination.hashCode());
    result = prime * result + ((origin == null) ? 0 : origin.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ConnectedRoutes other = (ConnectedRoutes) obj;
    if (destination == null) {
      if (other.destination != null)
        return false;
    } else if (!destination.equals(other.destination))
      return false;
    if (origin == null) {
      if (other.origin != null)
        return false;
    } else if (!origin.equals(other.origin))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "[origin=" + origin + ", destination=" + destination + "]";
  }

}
