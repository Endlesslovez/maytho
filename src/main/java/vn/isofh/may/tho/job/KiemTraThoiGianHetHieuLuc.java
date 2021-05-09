package vn.isofh.may.tho.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import vn.isofh.may.tho.dao.repository.ThietBiRepository;

@Component
public class KiemTraThoiGianHetHieuLuc {

   @Autowired
   private ThietBiRepository repository;

   @Scheduled(cron = " 0 0 0 * * *")
   public void kiemTraThoiGianHetHieuLuc() {

      LocalDate dateNow = LocalDate.now();

      repository.getByNgayHieuLuc(dateNow).forEach(e -> {
         
         e.setActive(false);
         repository.save(e);

      });
   }
}
