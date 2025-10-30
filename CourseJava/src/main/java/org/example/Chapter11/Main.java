package org.example.Chapter11;

import org.example.Chapter10.SealdTest;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ListFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

public class Main {

    static void func() throws IOException {
        System.out.println("Hi");
    }
    static void formatingNumbers() {
        double d = 1_23_4.5_2;
        NumberFormat nr = new DecimalFormat("####.000");
        System.out.println(nr.format(d));

        NumberFormat nr2 = new DecimalFormat("000000");
        System.out.println(nr2.format(d));

        NumberFormat nr3 = new DecimalFormat("What s up my man? Money: $###,000,000");
        System.out.println(nr3.format(d));
    }
    static void formatingDateTime(){
        LocalDate date = LocalDate.of(2025,11,23);
        System.out.println(date.getChronology());
        System.out.println(date.getEra());
        System.out.println(date.getDayOfMonth());
        System.out.println(date.getDayOfWeek());
        System.out.println(date.getDayOfYear());

        LocalDate date1 = LocalDate.of(2020,12,13);
        LocalTime time = LocalTime.of(3,30);
        LocalDateTime dateTime = LocalDateTime.of(date1,time);

        System.out.println(dateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(dateTime.format(DateTimeFormatter.ISO_DATE));

        // Error
        // System.out.println(date.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(time.format(DateTimeFormatter.ISO_TIME));
    }
    static void formatCustomizeDateTime(){
        LocalDateTime d = LocalDateTime.of(1999,12,3,15,45,30);
        var f = DateTimeFormatter.ofPattern("mm MM dd, yy, 'error?' ss:hh");
        System.out.println(d.format(f));
        var pat2 = DateTimeFormatter.ofPattern("'Year': yyyyyy, 'Month': MMMM, 'Day ''s': dd ");
        System.out.println(d.format(pat2));
    }
    static void localization(){
        Locale l1 = new Locale.Builder()
                .setLanguage("romana")
                .setRegion("RO")
                .build();
        System.out.println(l1);
        System.out.println(Locale.getDefault());
        Locale.setDefault(l1);
        System.out.println("New local date: " + l1);
    }
    static void localizationNumbers(){
        Locale[] loc = NumberFormat.getAvailableLocales();
//        Arrays.stream(loc).forEach(System.out::println);
        System.out.println("Localization Numbers ---\n"+NumberFormat.getInstance());
//        Locale.setDefault(Locale.US);
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US));

        System.out.println("Percent? " + NumberFormat.getPercentInstance());
        Locale france = Locale.of("franc","France");
        double price = 49.99;
        System.out.println(NumberFormat.getCurrencyInstance(france).format(price));
        System.out.println(NumberFormat.getCurrencyInstance().format(price));

        double failRate = 0.98029121;
        var rom = NumberFormat.getPercentInstance(Locale.of("ro","RO"));
        System.out.println(rom.format(failRate));
        var numbRom = NumberFormat.getNumberInstance(Locale.of("ro", "RO"));
        String success = "0.01%";

        var currJapan = NumberFormat.getCurrencyInstance(Locale.US);
        String dollars = "$4500.20";
        try {
            System.out.println("C? :: "+ numbRom.parse(success));
            System.out.println("Dollars parsed: " + currJapan.parse(dollars));
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());

            String s = "40.45";
            try {
                var en = NumberFormat.getInstance(Locale.US);
                System.out.println(en.parse(s));  // 40.45

                var fr = NumberFormat.getInstance(Locale.FRANCE);
                System.out.println(fr.parse(s));  // 40
            }catch (ParseException p){
                System.out.println(p.getMessage());
            }
        }
    }
    static void  formatCompactNumber(){
        var formatters = Stream.of(
                NumberFormat.getCompactNumberInstance(),
                NumberFormat.getCompactNumberInstance(Locale.of("ro","RO"), NumberFormat.Style.LONG),
                NumberFormat.getCompactNumberInstance(Locale.of("ro","RO"), NumberFormat.Style.SHORT),

                NumberFormat.getCompactNumberInstance(Locale.FRENCH, NumberFormat.Style.LONG),
                NumberFormat.getCompactNumberInstance(Locale.FRENCH, NumberFormat.Style.SHORT),

                NumberFormat.getNumberInstance());

        formatters.map(s -> s.format(7_123_456)).forEach(System.out::println);
    }
    static void formatLocalizingDate(){
        var italy = Locale.of("it","IT");
        var dt = LocalDateTime.of(2025, Month.OCTOBER,31,23, 59);
        var zone = dt.atZone(ZoneId.of("Europe/Rome"));

        //2025 Oct 31, Fri ---- venerdì 31 ottobre 2025
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(dt)
                + " ---- "
                + DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(italy).format(dt));
        //23:59 ---- 23:59
        System.out.println(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(dt)
                + " ---- "
                + DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(italy).format(dt));

        //2025-10-31 23:59 ---- 31/10/25, 23:59
        System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(dt)
                + " ---- "
                + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(italy).format(dt));


    }
    static void main() throws IOException{
        func();
        String str = null;
        try {
            str.toLowerCase();
        } catch (Exception e){
            System.out.println("Cached RuntimeException" + e.getMessage());
        }

        formatingNumbers();
        formatingDateTime();
        formatCustomizeDateTime();
        localization();
        localizationNumbers();
        formatCompactNumber();
        formatLocalizingDate();
    }
}
