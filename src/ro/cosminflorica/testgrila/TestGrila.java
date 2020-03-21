package ro.cosminflorica.testgrila;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.util.Random;

public class TestGrila {
    private static class CR {
        private BufferedReader m_bufferedReader;

        public CR() {
            m_bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        }

        public String m_readString() {
            String s = "";
            try {
                s = m_bufferedReader.readLine();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return s;
        }

        public char m_readChar() {
            String s = this.m_readString();
            return s.charAt(0);
        }

        public int m_readInt() {
            String s = this.m_readString();
            return Integer.parseInt(s);
        }
    }


    public static void main(String[] args) {
        CR cr = new CR();
        int numberOfQuestions = 0, numberOfLines = 0, i;
        String fileName = "res/intrebari.txt";
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        String lines[] = new String[1000];
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            for (; ; ) {
                String linieCurenta = bufferedReader.readLine();
                if (linieCurenta == null)
                    break;
                lines[numberOfLines] = linieCurenta;
                numberOfLines++;
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        numberOfQuestions = numberOfLines / 3;
        int ordine[] = new int[numberOfQuestions];
        for (i = 0; i < numberOfQuestions; i++)
            ordine[i] = i;

        amesteca(ordine);

        Random r = new Random();
        char answerRight, answerUser;
        int corecte = 0;
        long iStart = System.currentTimeMillis();
        for (i = 0; i < numberOfQuestions; i++) {
            System.out.println(lines[3 * ordine[i]]);

            int answerRandom = r.nextInt(2);
            if (answerRandom == 0) {
                System.out.println("1. " + lines[3 * ordine[i] + 1]);
                System.out.println("2. " + lines[3 * ordine[i] + 2]);
                answerRight = '1';
            } else {
                System.out.println("1. " + lines[3 * ordine[i] + 2]);
                System.out.println("2. " + lines[3 * ordine[i] + 1]);
                answerRight = '2';
            }
            for (; ; ) {
                System.out.println("Raspuns = (1,2) : ");
                answerUser = cr.m_readChar();
                if (answerUser == answerRight) {
                    corecte++;
                }
                if (answerUser == '1' || answerUser == '2')
                    break;
            }
        }

        long iStop = System.currentTimeMillis();
        int time = (int) (iStop - iStart) / 1000;
        System.out.println("Time spend: " + time);
        System.out.println("Number of questions: " + numberOfQuestions);
        System.out.println("Number of right answers: " + corecte);

        double nota = 10.0 * corecte / numberOfQuestions;

        System.out.println("Nota: " + nota);

    }

    private static void amesteca(int[] ordine) {
        Random r = new Random();
        for (int i = 0; i < ordine.length; i++) {
            int index1 = r.nextInt(ordine.length);
            int index2 = r.nextInt(ordine.length);
            int aux = ordine[index1];
            ordine[index1] = ordine[index2];
            ordine[index2] = aux;
        }
    }
}
