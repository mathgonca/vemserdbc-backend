package exercicios;

import java.time.*;

public class Exercicio04 {
    public static void main(String[] args) {
        final LocalDate data = LocalDate.of(2024, 9, 14);
        final LocalTime horario = LocalTime.of(18, 30);
        ZonedDateTime dataHoraShow = ZonedDateTime.of(data, horario, ZoneId.of("Europe/London"));
        ZonedDateTime agoraBrasil = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("America/Sao_Paulo"));

        int fusoLondres = dataHoraShow.getOffset().getTotalSeconds();
        int fusoBrasil = agoraBrasil.getOffset().getTotalSeconds();
        int diferencaFusos = (fusoLondres - fusoBrasil);

        Period period = Period.between(agoraBrasil.toLocalDate(), dataHoraShow.toLocalDate());
        int anos = period.getYears();
        int meses = period.getMonths();
        int dias = period.getDays();

        Duration duration;
        LocalTime horaLondresParaBrasil = dataHoraShow.toLocalTime().plusSeconds(diferencaFusos);
        if (agoraBrasil.toLocalTime().isAfter(horaLondresParaBrasil)) {
            Duration duration1 = Duration.between(agoraBrasil.toLocalTime(), LocalTime.of(23, 59));
            duration = Duration.between(LocalTime.of(0, 0), horaLondresParaBrasil);
            duration = duration.plusSeconds(duration1.getSeconds());
        } else {
            duration = Duration.between(agoraBrasil.toLocalTime(), horaLondresParaBrasil);
        }

        final int HORA_EM_SEGUNDOS = 3600;
        final int MINUTOS_EM_SEGUNDOS = 60;

        long segundos = duration.getSeconds();
        long horas = segundos / HORA_EM_SEGUNDOS;
        long minutos = (segundos % HORA_EM_SEGUNDOS) / MINUTOS_EM_SEGUNDOS;
        segundos = (segundos % HORA_EM_SEGUNDOS) % MINUTOS_EM_SEGUNDOS;

        System.out.println("Wesley Safadão & Black Sabbath - ULTIMATE TOUR");
        System.out.println("Faltam: " +
                anos + " anos, " +
                meses + " meses, " +
                dias + " dias, " +
                horas + " horas, " +
                minutos + " minutos, " +
                segundos + " segundos.");
    }
}
