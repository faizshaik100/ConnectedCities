package com.connected.cities.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.connected.cities.model.ConnectedRoutes;
import com.connected.cities.service.IConnectionCheckService;

@Service
public class ConnectionCheckServiceImpl implements IConnectionCheckService {

  private List<ConnectedRoutes> connectedRoutes = getFileContents("city.txt");

  private static final Logger log = LoggerFactory.getLogger(ConnectionCheckServiceImpl.class);

  @Override
  public String checkConnection(String origin, String destination) {
    log.debug("origin: {}, destination: {}", origin, destination);
    boolean forwardPathFound = getRouteByRecursion(connectedRoutes, origin, destination);
    boolean reversePathFound = getRouteByRecursion(connectedRoutes, destination, origin);
    log.debug("forward Path found: {}", forwardPathFound);
    log.debug("reverse Path found: {}", reversePathFound);
    if (forwardPathFound || reversePathFound) {
      return "yes";
    } else {
      return "no";
    }
  }

  private List<ConnectedRoutes> getRoutesByOrigin(Collection<ConnectedRoutes> parentList,
      String origin) {
    log.debug("getRoutesByOrigin called for:{}", origin);
    List<ConnectedRoutes> matchedRoutes = new ArrayList<>();
    for (ConnectedRoutes route : parentList) {
      log.debug("conditin: {}, orig: {}, text:{}", route.getOrigin().equalsIgnoreCase(origin),
          route.getOrigin(), origin);
      if (route.getOrigin().equalsIgnoreCase(origin)) {
        matchedRoutes.add(route);
      }
    }
    log.debug("getRoutesByOrigin called for:{}, results are:{}", origin, matchedRoutes.size());
    return matchedRoutes;

  }

  private boolean getRouteByRecursion(List<ConnectedRoutes> parentList, String origin,
      String destination) {
    log.debug("start of rec method :{}, {} - {}", parentList, origin, destination);

    List<ConnectedRoutes> originMatch = getRoutesByOrigin(parentList, origin);
    log.debug("Routes with origin:{} are: {}", origin, originMatch);
    if (originMatch.isEmpty()) {
      log.debug("originMatch.isEmpty ending recursion");
      return false;
    } else if (originMatch.get(0).getOrigin().equals(origin)
        && originMatch.get(0).getDestination().equals(destination)) {
      log.debug("path: {} -> {}", origin, originMatch.get(0).getDestination());
      log.debug("Match found for source to destination");
      return true;

    }
    log.debug("path: {} -> {}", origin, originMatch.get(0).getDestination());
    log.debug("calling rec in rec: {} ,{}, {} ", parentList, originMatch.get(0).getDestination(),
        destination);
    return getRouteByRecursion(parentList, originMatch.get(0).getDestination(), destination);

  }

  public List<ConnectedRoutes> getFileContents(String path) {

    Set<ConnectedRoutes> connectedRoutesList = new HashSet<>();
    try (FileInputStream fis = new FileInputStream(new ClassPathResource(path).getFile())) {
      byte[] buffer = new byte[512];
      StringBuilder stringBuilder = new StringBuilder();
      while ((fis.read(buffer)) != -1) {
        stringBuilder.append(new String(buffer));
      }

      log.debug("file content:{}", stringBuilder.toString());
      StringTokenizer stringTokenizer = new StringTokenizer(stringBuilder.toString(),
          System.lineSeparator());

      String[] tokenArr = null;
      while (stringTokenizer.hasMoreTokens()) {
        tokenArr = stringTokenizer.nextToken().split(",");
        connectedRoutesList.add(new ConnectedRoutes(tokenArr[0], tokenArr[1]));
      }

    } catch (FileNotFoundException fileNotFoundException) {
      log.error("FileNotFoundException : ", fileNotFoundException);
    } catch (IOException ioException) {
      log.error("IOException : ", ioException);
    }
    return new ArrayList<ConnectedRoutes>(connectedRoutesList);
  }

}
