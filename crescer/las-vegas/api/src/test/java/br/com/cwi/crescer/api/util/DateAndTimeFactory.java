package br.com.cwi.crescer.api.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTimeFactory {
   public static LocalDateTime getPastDateTime(){
     return  LocalDateTime
              .parse("2022-05-01 12:00:00", DateTimeFormatter
                      .ofPattern("yyyy-MM-dd HH:mm:ss"));
   }
   public  static LocalDateTime getDateTime(){
      return  LocalDateTime
              .parse("2022-05-03 12:00:00", DateTimeFormatter
                      .ofPattern("yyyy-MM-dd HH:mm:ss"));
   }

   public static LocalDateTime getFutureDateTime(){
      return  LocalDateTime
              .parse("2022-05-09 12:00:00", DateTimeFormatter
                      .ofPattern("yyyy-MM-dd HH:mm:ss"));
   }

}
