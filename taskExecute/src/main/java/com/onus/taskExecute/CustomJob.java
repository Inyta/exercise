package com.onus.taskExecute;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class CustomJob {

  @XxlJob("checkGoodsShelfHandler")
  public void checkGoodsShelfHandler() throws Exception {
    XxlJobHelper.log("check goods shelf ...");
  }

}
