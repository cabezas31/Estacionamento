import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Configurações de valores
        double valorPrimeirasTresHoras = 10.00; // Valor até 3 horas
        double valorAdicionalHora = 5.00;      // Valor até 5 horas
        int toleranciaMinutos = 15;

        // Entrada de horário
        System.out.println("Digite a hora de entrada (HH:mm): ");
        String entradaStr = scanner.nextLine();
        System.out.println("Digite a hora de saída (HH:mm): ");
        String saidaStr = scanner.nextLine();

        try {
            // Converte as strings para LocalTime
            LocalTime horaEntrada = LocalTime.parse(entradaStr);
            LocalTime horaSaida = LocalTime.parse(saidaStr);

            // Calcula a duração da permanência
            Duration duracao = Duration.between(horaEntrada, horaSaida);
            long minutosTotais = duracao.toMinutes();

            // Verifica se a saída é no dia seguinte
            if (minutosTotais < 0) {
                minutosTotais += 24 * 60; // Adiciona 24 horas em minutos
            }

            // Calcula o tempo efetivo (após tolerância)
            long minutosCobrados = Math.max(0, minutosTotais - toleranciaMinutos);
            double valorTotal = 0.0;

            if (minutosCobrados > 0) {
                // Calcula o tempo em horas arredondado para cima
                double horasTotais = Math.ceil(minutosCobrados / 60.0);

                if (horasTotais <= 3) {
                    valorTotal = valorPrimeirasTresHoras;
                } else {
                    valorTotal = valorPrimeirasTresHoras + (horasTotais - 3) * valorAdicionalHora;
                }
            }

            // Exibe o ticket
            System.out.println("\n=== Ticket de Estacionamento ===");
            System.out.println("Hora de Entrada: " + horaEntrada);
            System.out.println("Hora de Saída: " + horaSaida);
            System.out.println("Tempo Total: " + minutosTotais + " minutos");
            System.out.printf("Valor a Pagar: R$ %.2f%n", valorTotal);
            System.out.println("===============================");
        } catch (Exception e) {
            System.out.println("Erro: Formato de hora inválido. Use HH:mm (ex: 14:30).");
        }

        scanner.close();
    }
}