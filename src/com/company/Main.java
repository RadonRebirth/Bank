package com.company;
class Account extends Thread{
    public Account(String name){
        this.name = name;
    }
    String name;
    int max = 1500;
    int rnd = (int) (Math.random()*250);
    int balance = -rnd;
    public void run(){
        for(int i = 0;i<max;i+=rnd) {
            balance+=rnd;
            System.out.println("Баланс "+name+" составляет: "+ balance);
        }
    }
    public void getBalance(){
        if(balance > 1000){
            balance-=500;
            System.out.println("Списание с счёта "+name+" в размере: 500");
            System.out.println("Остаток счёта "+name+" составляет: "+ balance);
        }
    }
}
public class Main{
    public static void main(String[] args) throws InterruptedException {
	Account account = new Account("RaDoN");
    account.start();
    account.join();
    account.getBalance();
    }
}
