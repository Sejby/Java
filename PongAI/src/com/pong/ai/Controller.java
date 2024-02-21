package com.pong.ai;

import com.pong.logic.Logic;
import com.pong.view.View;

import java.awt.*;
import java.util.Random;

public class Controller {
    private static Controller instance;
    public static Controller GetInstance() {
        if (instance == null) instance = new Controller();
        return instance;
    }

    private static Controller instance1;
    public static Controller GetInstance1() {
        if (instance1 == null) instance1 = new Controller();
        return instance1;
    }

    private final BP network;
    private final Random rand;

    public int amountOfRuns;

    private Controller() {
        network = new BP();
        rand = new Random();
        amountOfRuns = 100;
    }
    public void Learn() {
        Logic logic = Logic.GetInstance();
        View view = View.GetInstance();
        logic.canRun = false;
        int progress = 1;
        double[][] input = new double[amountOfRuns][4];
        double[][] output = new double[amountOfRuns][2];
        for (int i = 0; i < amountOfRuns; i++) {
            int playerPos = rand.nextInt(view.canvas.getHeight());
            Point target = new Point(rand.nextInt(view.canvas.getWidth()), rand.nextInt(view.canvas.getHeight()));

            input[i][0] = (double) playerPos / (double) view.canvas.getHeight();
            input[i][1] = (double) target.y  / (double) view.canvas.getHeight();

            double calculatedPosVector;

            if (playerPos > target.y) calculatedPosVector = 0;
            else calculatedPosVector = 1;


            /*double calculatedPosVector = 0;
            int distanceX = (view.canvas.getWidth() - target.x) / logic.ball.speed.x;

            if (dirY) { //Going down
                int distanceY = target.y + distanceX / logic.ball.speed.y;
                if (distanceY <= view.canvas.getHeight())
                    calculatedPosVector = (double) distanceY / (double) view.canvas.getHeight();
                else {
                    distanceY = view.canvas.getHeight() + (view.canvas.getHeight() - distanceY);
                    calculatedPosVector = (double) Math.abs(distanceY) / (double) view.canvas.getHeight();
                }
            } else {
                int distanceY = target.y - distanceX / logic.ball.speed.y;
                calculatedPosVector = (double) Math.abs(distanceY) / (double) view.canvas.getHeight();
            }*/


            /*if (distance <= (view.canvas.getHeight() - target.y) * logic.ball.speed.y)
                calculatedPosVector = (double)(view.canvas.getHeight() - target.y - logic.player2.size.height / 2) / (double) view.canvas.getHeight();
            else
                calculatedPosVector = (double)Math.abs(view.canvas.getHeight() - (view.canvas.getHeight() - target.y - logic.player2.size.height / 2)) / (double) view.canvas.getHeight();
*/
            output[i][0] = calculatedPosVector;
        }
        network.setVstupnitrenovaciEpocha(input);
        network.setVystupnitrenovaciEpocha(output);
        try {
            network.aktualizujMaticiVahEpocha();
        } catch (IndexOutOfBoundsException ignored) {
            network.setSit("2-4-1");
            network.vytvorSit();
        } finally {
            for (int i = 0; i < 500; i++) {
                PrintProgress((double)progress/500*100.0);
                network.aktualizujMaticiVahEpocha();
                progress++;
            }
        }
    }
    public void LearnFromPlayer() {
        Logic logic = Logic.GetInstance();
        View view = View.GetInstance();
        double[][] input = new double[amountOfRuns][4];
        double[][] output = new double[amountOfRuns][2];

        int playerPos = logic.player1.position.y;
        Point target = logic.ball.position;


        input[0][0] = (double) playerPos / (double) view.canvas.getHeight();
        input[0][1] = (double) target.y  / (double) view.canvas.getHeight();

        double calculatedPosVector;

        if (playerPos > target.y) calculatedPosVector = 0;
        else calculatedPosVector = 1;

        output[0][0] = calculatedPosVector;

        network.setVstupnitrenovaciEpocha(input);
        network.setVystupnitrenovaciEpocha(output);
        try {
            network.aktualizujMaticiVahEpocha();
        } catch (IndexOutOfBoundsException ignored) {
            network.setSit("4-15-5-1");
            network.vytvorSit();
        } finally {
            for (int i = 0; i < 500; i++) {
                network.aktualizujMaticiVahEpocha();
            }
        }
    }
    public void PrintProgress(double progress) {
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
        double Progress = progress;
        int full = (int) (progress / 5);
        progress = progress % 5;
        int threeQuarters = (int) (progress / 3.75);
        progress = progress % 3.75;
        int half = (int) (progress / 2.5);
        progress = progress % 2.5;
        int quarter = (int) (progress / 1.25);
        System.out.println();
        for (int i = 0; i < full ; i++)
            System.out.print("▉");
        for (int i = 0; i < threeQuarters ; i++)
            System.out.print("▊");
        for (int i = 0; i < half ; i++)
            System.out.print("▋");
        for (int i = 0; i < quarter ; i++)
            System.out.print("▌");
        for (int i = 0; i < 20-(full+threeQuarters+half+quarter); i++) {
            System.out.print(" ");
        }
        System.out.print(" " + ((int)Progress) + "% done");
        System.out.println();
    }

    public void SetNetwork(String network) {
        this.network.setSit(network);
        this.network.vytvorSit();
    }
    public void SetInput(double[][] in) {
        network.setVstupniVektorEpocha(in);
        network.vybavSitEpocha();
    }
    public double[][] GetOut() {
        return network.getVystupniVektorEpocha();
    }
    public String GetNet() {
        return network.getSit();
    }
}
