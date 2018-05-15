package com.connected.cities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.connected.cities.service.IConnectionCheckService;

@RestController
public class ConnectionController {

  @Autowired
  private IConnectionCheckService connectionCheckService;

  @GetMapping(value = "/connected")
  public String connectionCheck(@RequestParam(required = true) String origin,
      @RequestParam(required = true) String destination) {

    return connectionCheckService.checkConnection(origin, destination);

  }

}
