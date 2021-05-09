package vn.isofh.may.tho.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.isofh.may.tho.service.DangKyTaiKhoanService;

@Component
public class GuiEmailYeuCauTaoTk {

  @Autowired
  private DangKyTaiKhoanService dangKyTaiKhoanService;

   @Scheduled(cron = " 0 0 0/12 * * *")
   public void guiEmailYeuCauTaoTk() {
      dangKyTaiKhoanService.guiMailYeuCauDangKy();
   }

}
