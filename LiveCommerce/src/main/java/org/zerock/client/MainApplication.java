package org.zerock.client;
public class MainApplication {

    public static void main(String[] args) {
        try {
            YouTubeDataAPIClient.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}