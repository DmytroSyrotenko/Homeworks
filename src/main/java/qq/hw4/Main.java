package qq.hw4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {



        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose start point.Use numbers only");
        int start = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Choose finish point.Use numbers only");
        int finish = Integer.parseInt(bufferedReader.readLine());

        steps(start, finish);
    }


    public static void steps(int start, int finish) {

        int[][] relations = {
                {11, 18},
                {14, 3},
                {4, 2},
                {2, 11},
                {1, 8},
                {66, 5},
                {18, 32},
                {15, 1},
                {27, 4},
                {1, 4},
                {4, 18},
                {8, 27},
                {32, 64},
                {5, 17},
                {64, 88},
                {18, 5},
                {64, 88},
                {0, 0},
                {88, 7},
                {7, 122},

        };


        int[] arrFrom = new int[relations.length];
        int[] arrTo = new int[relations.length];


        for (int i = 0; i < relations.length; i++) {
            for (int j = 0; j < relations[i].length; j++) {
                for (int k = 0; k < j; k++) {
                    arrFrom[i] = relations[i][k];
                    break;
                }
                arrTo[i] = relations[i][j];
            }
        }

        System.out.println("arrayFrom " + Arrays.toString(arrFrom));
        System.out.println("arrayTo   " + Arrays.toString(arrTo));


        System.out.println("From: " + start);
        System.out.println("To  : " + finish);
        ////////////////////////////////////////////////////////////////////////

        int steps = 0;
        int maxSteps = 5;
        int currentNumber = start;
        int lastIndex = -1;


        int[] pathArray = new int[arrFrom.length];
        Arrays.fill(pathArray, -2);


        for (int i = 0; i < arrFrom.length; i++) {
            if (arrFrom[i] == currentNumber) {

                if (arrTo[i] == finish) {
                    System.out.println("----Congratulations,you can send a message using route below:");
                    lastIndex++;
                    pathArray[lastIndex] = i;

                    for (int j = 0; j <= lastIndex; j++) {
                        System.out.println(arrFrom[pathArray[j]] + "--" + arrTo[pathArray[j]]);
                    }

                    break;

                } else {
                    if (steps < maxSteps) {
                        currentNumber = arrTo[i];
                        lastIndex++;
                        pathArray[lastIndex] = i;
                        steps++;
                        i = -1;

                    } else if (steps == maxSteps) {
                        i++;

                        if (i >= arrTo.length - 1) {
                            currentNumber = arrTo[pathArray[lastIndex - 2]];
                            i = pathArray[lastIndex - 1] + 1;
                            pathArray[lastIndex] = -2;
                            lastIndex--;
                            pathArray[lastIndex] = -2;
                            lastIndex--;
                            steps = steps - 2;
                        }

                    } else {
                        i++;
                    }

                }


            } else {
                if (i == arrTo.length - 1) {

                    if (lastIndex > 0) {
                        currentNumber = arrTo[pathArray[lastIndex - 1]];
                        i = pathArray[lastIndex] + 1;
                        pathArray[lastIndex] = -2;
                        lastIndex--;
                        steps--;
                        if (i >= arrTo.length - 1) {
                            currentNumber = start;
                            pathArray[lastIndex] = -2;
                            lastIndex--;
                            i = pathArray[lastIndex] + 1;
                            pathArray[lastIndex] = -2;
                            lastIndex--;
                            steps--;
                        }

                    } else if (lastIndex == 0) {
                        currentNumber = start;
                        i = pathArray[lastIndex] + 1;
                        pathArray[lastIndex] = -2;
                        lastIndex--;
                        if (i >= arrTo.length - 1) {
                            System.out.println("--Message cant be sent.Try another route--");
                            break;
                        }


                    } else {
                        System.out.println("--Message cant be sent.Try another route--");
                        break;
                    }
                }

            }

        }
        /* знаю что лютій хардкод с затічками всего что только можно.
        после того как вімучал его, понял что следовало бі делать по другому,
        но и так уже слишком много времени потрачено,-поетому такая версия.
         */

    }

}






