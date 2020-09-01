package com.rs.notedown.controllers;

import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ConditionalOnProperty(prefix = "swagger", value = "enabled",
                       havingValue = "false", matchIfMissing = true)
@CrossOrigin
@RestController
@RequestMapping("swagger-ui.html")
@SuppressWarnings({"UnusedDeclaration"})
public class DisabledSwaggerController {

  @RequestMapping(method = RequestMethod.GET)
  public void getSwagger(HttpServletResponse response) {
    response.setStatus(HttpStatus.NOT_FOUND.value());
  }
}
