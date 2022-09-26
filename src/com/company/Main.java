package com.company;

import java.util.Scanner;

class Account extends Thread{
    private int money = 0;
    private int maxMoney = 1500;
    int minus = 500;
    public synchronized void setBalance(){
        while(money >= maxMoney){
            try{
                wait();
            }
            catch (Exception ex){
                ex.getMessage();
            }
        }
        money += 200;
        System.out.println("Пополнение баланса на 200 \n Остаток баланcа: " + money);
        notify();
    }
    public synchronized void getMoney(){
        while(money < maxMoney){
            try {
                wait();
            }
            catch (Exception ex){
                ex.getMessage();
            }
        }
        System.out.println("Введите сумму вывода: ");
        Scanner in = new Scanner(System.in);
        minus = in.nextInt();
        money -= minus;
        if(money < 0){
            System.out.println("На балансе нет такой суммы");
            System.exit(0);
        }
        System.out.println("Произошло списание в размере " + minus + "\n Остаток баланса: " + money);
        notify();
    }
}

class SetBalance extends Thread{
    Account account;
    SetBalance(Account account){
        this.account=account;
    }
    public void run(){
        for (int i = 1; i < 50; i++) {
            account.setBalance();
        }
    }
}
class GetMoney extends Thread{

    Account account;
    GetMoney(Account account){
        this.account=account;
    }
    public void run(){
        for (int i = 1; i < 50; i++) {
            account.getMoney();
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        GetMoney setBal = new GetMoney(account);
        SetBalance getMon = new SetBalance(account);
        new Thread(setBal).start();
        new Thread(getMon).start();
    }
}
