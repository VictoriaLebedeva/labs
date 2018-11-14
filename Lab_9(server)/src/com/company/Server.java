package com.company;

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.util.*;

public class Server {

    private static final int SERVER_PORT = 4000;
    private static final int MAX_CLIENTS = 10;
    private ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;


    private static Socket fromClient;

    public static void main(String [] args) throws IOException, ParseException {

        ArrayList<Info> news = new ArrayList<>();
        news.add(new Info("03-11-2018","Вика Лебедева отметила свой День Рождения"));
        news.add(new Info("04-11-2018","Был выходной, ничего не произошло"));
        news.add(new Info("05-11-2018","10 группа списывала КР по социологии"));
        news.add(new Info("06-11-2018","Капустник ФПМИ + афтерпати"));
        news.add(new Info("07-11-2018","Был выходной, День Октябрьской революции"));

        ArrayList<Info> currentNews = new ArrayList<>();
        currentNews.add(new Info("","10 группа пишет КР по проге"));
        currentNews.add(new Info("","В среду мы пишем коллоквиум по ВКИАД"));
        currentNews.add(new Info("","Может ребята из 10 группы пойдут в кино"));


        System.out.println("Welcome to the servers side");
        Server server = new Server();
        server.Connection(currentNews, news);
    }


    public Server() throws IOException {
        server = new ServerSocket(SERVER_PORT, MAX_CLIENTS);
    }

    public void Connection(ArrayList<Info> currentNews, ArrayList<Info> news) throws IOException {
        Socket clientSocket = null;

        while (true) {
            clientSocket = server.accept();
            System.out.println("Мы подключились");
            ServerForClients newServerForClients = new ServerForClients(clientSocket,currentNews,news);
            Thread newThread = new Thread(newServerForClients);
            newThread.start();
        }
    }

    class ServerForClients implements Runnable {

        private Socket clientSocket = null;
        ArrayList<Info> currentNews;
        ArrayList<Info> news;

        public ServerForClients(Socket clientSocket, ArrayList<Info> currentNews, ArrayList<Info> news) {
            this.clientSocket = clientSocket;
            this.currentNews = currentNews;
            this.news = news;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                for (Info i : currentNews) {
                    out.write(i.information);
                }

                String date = in.readLine();
                System.out.println("Clients request: " + date);

                /*for (Info i : news) {
                    if(i.date == date)
                       out.write(i.information);
                }*/

                out.write("Hello : " + date  );
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
