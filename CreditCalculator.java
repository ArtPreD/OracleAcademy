import java.util.Scanner;

public class CreditCalculator {

    public void calculate(){
        System.out.println("Выберите способ расчета ежемесячного платежа. 1 - кслассический, 2 - аннуитетный");
        int var = userInput();
        int[] userData;
        double monthPay;
        switch (var){
            case 1:
                userData = userData();
                monthPay = klassicPayment(userData[0], userData[1], userData[2]);
                result(monthPay, overpayment(userData[0], userData[1], monthPay));
                break;
            case 2:
                userData = userData();
                monthPay = annuityPayment(userData[0], userData[1], userData[2]);
                result(monthPay, overpayment(userData[0], userData[1], monthPay));
                break;
            default:
                System.out.println("Ошибка ввода. Попробуйте еще раз");
                calculate();
        }
    }

    private double annuityPayment(int sumCredit, int month, double rate){
        double i = rate/12/100;
        double n = Math.pow(1 + i, month);
        double coefficient = i * n / (n - 1);
        return coefficient * sumCredit;
    }

    private double klassicPayment(int sumCredit, int month, int rate){
        return (sumCredit / month) + ((sumCredit / month) * rate/100);
    }

    private void result(double mPay, double overPay){
        System.out.println("Ежемесячный платеж по кредиту составит: " + mPay + ", переплата в месяц равна " + overPay);
    }

    private double overpayment(int sumCredit, int month, double mPay){
        return mPay - (sumCredit / month);
    }

    private int userInput(){
        Scanner scanner = new Scanner(System.in);
        int i;
        try{
             i = scanner.nextInt();
        }catch (Exception ex){
            System.out.print("Введено недопустимое значение. Попробуйте снова: ");
            i = userInput();
        }
        return i;
    }

    private int[] userData(){
        int[] data = new int[3];
        System.out.print("Введите сумму кредита: ");
        data[0] =userInput();
        System.out.print("Введите срок кредита (в месяцах): ");
        data[1] = userInput();
        System.out.print("Введите процентную ставку: ");
        data[2] = userInput();

        return data;
    }
}