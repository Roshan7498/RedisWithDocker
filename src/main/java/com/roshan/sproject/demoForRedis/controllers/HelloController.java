package com.roshan.sproject.demoForRedis.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloController {
  @GetMapping("/")
  public String home() { return "App is running!"; }
}
