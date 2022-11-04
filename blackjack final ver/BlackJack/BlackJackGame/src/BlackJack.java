import java.util.Random;
import java.util.Scanner;

public class BlackJack {
    private static Scanner sc = new Scanner(System.in);

    public static void basicInfo() {
        System.out.println("Vítejte v Black Jacku u Karase");
        System.out.println("Teď si ujasníme princip hry:");
        System.out.println("Základní princip hry je, že hráč chce mít hodnotu karet blíže 21 než bankéř, " + "ale přitom 21 nepřekročit. Vyhrává ten, kdo má po ukončení hry v ruce nejvyšší součet, aniž by překročil 21.");
        System.out.println("Hráč, který má v ruce součet karet větší než 21, je takzvaně  „přes“.");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Ze začátku dostanete jako bonus 100KČ jako bonus ˘-˘");
        System.out.println();
        System.out.println("Chcete začít hru pokud chcete napište 1, jestli ne napište 2");
    }

    public static void checkInput(int play) {


        if (play == 2) {
            System.out.println("Hra ukončena");
            System.exit(0);
        }
        while (play != 1) {

            System.out.println("Jestli chcete hrát napište prosím 1, jestli ne napište 2");
            play = sc.nextInt();
            if (play == 2) {
                System.out.println("Hra ukončena");
                System.exit(0);
            }
        }
    }

    public static int bet(int account) {

        int value;

        do {

            System.out.println("Napište prosím svojí sázku");
            value = sc.nextInt();

            if (value > account) {
                System.out.println("Nemáš dostatek peněz");
            }

        } while (value > account);
        account -= value;
        System.out.println("Zbývá vám na účtě zatím " + account + " KČ");

        return value;

    }

    public static int carts(int bet) {


        Random rand = new Random();

        int cart;
        int totalUser = 0;
        int totalBank = 0;
        int bankCart;
        int array[] = new int[2];

        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(10) + 1;
            System.out.println(i + 1 + "." + " vaše karta je " + array[i]);
            totalUser = totalUser + array[i];

        }

        if (((array[0] == 1) && (array[1] == 10)) || ((array[0] == 10) && (array[1] == 1))) {
            System.out.println("Black jack");
            System.out.println("Vyhrál jste " + bet + " KČ");
            System.out.println("-----------------------------------------------------------------------");
        } else {
            System.out.println("Dohromady máte teď součet " + totalUser);
            System.out.println();
            int x;
            int count = 2;

            //user
            System.out.println("Pokud chcete další kartu napište 1, pokud ne napište 2");


            while (totalUser < 21) {

                x = sc.nextInt();
                if (x != 1 && x != 2) {
                    System.out.println("Nenapsal jste správné číslo");
                    System.out.println("Pokud chcete další kartu napište 1, pokud ne napište 2");
                } else if (x == 1) {

                    cart = rand.nextInt(10) + 1;
                    count++;
                    System.out.println("Vaše " + count + "." + " karta je " + cart);
                    totalUser = totalUser + cart;
                    System.out.println("Dohromady máte teď součet " + totalUser);
                    System.out.println("Pokud chcete další kartu napište 1, pokud ne napište 2");
                    System.out.println();

                    if (totalUser > 21) {
                        System.out.println("--------------------------------------------------------------");
                        System.out.println("Přesáhl jste hodnoty 21");
                        System.out.println("Prohrál jste " + bet + " KČ");

                        return -bet;
                    }

                } else if (x == 2) {
                    System.out.println("Dohromady máte součet " + totalUser);
                    break;
                }

            }
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            // dealer
            int bankArray[] = new int[2];
            int count2 = 2;

            for (int i = 0; i < bankArray.length; i++) {
                bankArray[i] = rand.nextInt(10) + 1;
                System.out.println("Bankéře " + (i + 1) + "." + " karta je " + bankArray[i]);
                totalBank = totalBank + bankArray[i];
            }
            System.out.println("Bankéř má dohromady  " + totalBank);
            System.out.println();
            if (((bankArray[0] == 1) && (bankArray[1] == 10)) || ((bankArray[0] == 10) && (bankArray[1] == 1))) {
                System.out.println("Black jack");
                System.out.println("Prohrál jste " + -bet + " KČ");
                System.out.println("-----------------------------------------------------------------------");
            } else {
                while (totalBank < 17) {

                    bankCart = rand.nextInt(10) + 1;
                    count2++;
                    System.out.println("Bankéře " + count2 + "." + " karta je " + bankCart);
                    totalBank = totalBank + bankCart;
                    System.out.println("Bankéř má dohromady " + totalBank);
                    System.out.println("---------------------------------");
                }
            }
        }

        return winner(totalUser, totalBank, bet);

    }

    public static int winner(int totalUser, int totalBank, int bet) {


        if (totalUser > totalBank) {
            System.out.println("Výhrál jste " + bet + " KČ");
            return bet;
        } else if (totalUser == totalBank) {
            System.out.println("Remíza");
            return 0;
        } else if (totalBank > 21) {
            System.out.println("Vyhrál jste " + bet + " KČ");
            return bet;

        } else {

            System.out.println("Prohrál jste " + bet + " KČ");
            return -bet;
        }
    }


    public static void main(String[] args) {
        int account = 100;
        basicInfo();

        while (account > 0) {

            checkInput(sc.nextInt());
            int bet = bet(account);

            account += carts(bet);


            if (account > 0) {
                System.out.println("Na účtě máte " + account + " KČ");
                System.out.println("Jestli chcete pokračovat dále ve hře napište znovu  1, jestli ne napište 2");
            } else {
                System.out.println("Nemáte peníze, PROHRÁL JSTE");
                break;
            }
        }
    }

}