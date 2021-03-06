package com.rs.notedown.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotExistException extends RuntimeException {
  public DataNotExistException(String dataType, long dataId) {
    super(dataType + " for id " + dataId + " does not exist.");
  }

  public DataNotExistException(String dataType, String dataTitle) {
    super(dataType + " " + dataTitle + " does not exist.");
  }
}
