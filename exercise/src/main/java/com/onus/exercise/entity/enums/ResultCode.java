package com.onus.exercise.entity.enums;


public enum ResultCode {

  SUCCESS(0), FAILED(-1);

  private final Integer code;

  ResultCode(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
