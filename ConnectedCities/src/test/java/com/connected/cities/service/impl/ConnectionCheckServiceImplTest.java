package com.connected.cities.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ConnectionCheckServiceImplTest {

  private ConnectionCheckServiceImpl connectionCheckServiceImpl;

  private final static String YES = "yes";
  private final static String NO = "no";

  @Before
  public void setUp() throws Exception {
    connectionCheckServiceImpl = new ConnectionCheckServiceImpl();
  }

  @Test
  public void testBostonToNewark() {
    String result = connectionCheckServiceImpl.checkConnection("Boston", "Newark");
    assertEquals("Route should exists between Boston - Newark", YES, result);
  }

  @Test
  public void testBostonToPhiladelphia() {
    String result = connectionCheckServiceImpl.checkConnection("Boston", "Philadelphia");
    assertEquals("Route should exists between Boston - Philadelphia", YES, result);
  }

  @Test
  public void testPhiladelphiaToAlbany() {
    String result = connectionCheckServiceImpl.checkConnection("Philadelphia", "Albany");
    assertEquals("Route should not exists between Philadelphia - Albany", NO, result);
  }

  @Test
  public void testPhiladelphiaToNewYork() {
    String result = connectionCheckServiceImpl.checkConnection("Philadelphia", "New York");
    assertEquals("Route should exists between Philadelphia - New York", YES, result);
  }

}
