package com.onus.exercise.entity.model;

import com.onus.exercise.entity.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

  public static final String DELETE_SUCCESS = "删除成功！";
  public static final String DELETE_FAILED = "删除失败！";
  public static final String OPERATION_SUCCESS = "操作成功！";
  public static final String OPERATION_FAILED = "操作失败！";
  public static final String SAVE_SUCCESS = "保存成功！";
  public static final String SAVE_FAILED = "保存失败！";
  public static final String UPDATE_SUCCESS = "更新成功！";
  public static final String UPDATE_FAILED = "更新失败！";

  private Integer code;

  private String msg;

  private T result;

  public static <T> Result<T> success() {
    return successWith(ResultCode.SUCCESS.getCode(), null, null);
  }

  public static <T> Result<T> success(String msg) {
    return successWith(ResultCode.SUCCESS.getCode(), msg, null);
  }

  public static <T> Result<T> success(T data) {
    return successWith(ResultCode.SUCCESS.getCode(), null, data);
  }

  public static <T> Result<T> success(String msg, T data) {
    return successWith(ResultCode.SUCCESS.getCode(), msg, data);
  }

  public static <T> Result<T> successWith(Integer code, String msg, T data) {
    return new Result<>(code, msg, data);
  }

  public static <T> Result<T> failed(String msg) {
    return failedWith(ResultCode.FAILED.getCode(), msg, null);
  }

  public static <T> Result<T> failedWith(Integer code, String msg, T data) {
    return new Result<>(code, msg, data);
  }

  public static <T> Result<T> error() {
    return successWith(ResultCode.FAILED.getCode(), null, null);
  }

  public static <T> Result<T> error(String msg) {
    return successWith(ResultCode.FAILED.getCode(), msg, null);
  }
}
