package org.example.Chapter2;

public class Main {
    static void main(String[] args){
        int b = 5;
        boolean a = true && ((b = 3) == 4);
        System.out.println("a: " + a + '\n' + " b: " + b);
        System.out.println(null instanceof Boolean);
        short s = 1;
        s += 100;
        System.out.println(s);
        byte x = 1, y = 2;
//        short z = x + y; // error (x+y is int)
        short z = (short)(x + y); // or use int
        System.out.println(z);
        long ear = 10;
        int hear = (int)(2 * ear);
        int aa = 1;
        int c = (short)++aa * (short)++aa;
        System.out.println(c+ " - " + aa);
        Object oo = (short)++aa * (short)++aa;
        System.out.println(oo.getClass());
        int pig = (short)4;
        pig = pig++;
        long goat = (int)2;
        goat -= 1.0;
        System.out.println(pig + " - " + goat);
//        short height = 1, weight = 3;
//        short zebra = (byte) weight * (byte) height;
        int ticketsTaken = 1;
        int ticketsSold = 3;
        ticketsSold += 1 + ticketsTaken++;
        ticketsTaken *= 2;
        ticketsSold += (long)1;
        System.out.println(ticketsSold);
        int start;
        start = (byte)(Byte.MAX_VALUE + 1);
        System.out.println(Integer.toBinaryString((Byte.MAX_VALUE+1)));
        System.out.println(Integer.toBinaryString((Byte.MIN_VALUE + 128)));
        int g = 2;
        System.out.println(((g > 2? 4 : 1) > ++g));

        int i;
        for ( i=0; i<0; i++){
            System.out.println(i + " \n");
        }
        System.out.println("Outside: + " + i);

        Object skips = 10;
        Double[][] p = new Double[0][0];
//        for(Double[] dc : p) {
//
//        }

        final  char abc = 'a';
        char bbb = 'a';
        final int adc = 3;
        switch (bbb){
            case abc:
            case adc:
        }
        boolean mama = false;
        System.out.println(true|((mama= true) == false));
        System.out.println(mama);
        String cccc = "012".indent(1).substring(1,3);
        System.out.println(cccc);
        int one = Math.min(5, 3);
         long two = Math.round(5.5);
         double three = Math.floor(6.6);
         var doubles = new double[] {one,two, three};

         L2 : for(int j =0; j <3; j++){
            int tmp = 0;
             for ( ; ;){
               tmp++;
               if (j % 2 == 0) {
                   continue L2;
               }
               if (tmp >= 5)
                   break;
               System.out.println("tmp: =" + tmp);
            }
             System.out.println("j: =" + j);
         }
        System.out.println("" + (5 + 6));

    }

}
