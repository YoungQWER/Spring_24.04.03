package org.zerock.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpResponse;

public class YouTubeDataAPIClient {
    public static void main(String[] args) throws Exception {
        // API 키 및 비디오 ID 설정
        String apiKey = "AIzaSyC-iKxB-I4d5RynCsP6gHR9SOtnp0sIZO4";
        String videoId = "C4DuYovuPhM";

        // API 요청 URL 생성
        String apiUrl = "https://www.googleapis.com/youtube/v3/videos?id=" + videoId + "&key=" + apiKey + "&part=snippet";

        // HttpClient 객체 생성
        HttpClient httpClient = HttpClientBuilder.create().build();

        // HTTP GET 요청 생성
        HttpGet request = new HttpGet(apiUrl);

        // HTTP 요청 보내고 응답 받기
        HttpResponse response = httpClient.execute(request);

        // 응답 처리
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            // 요청이 성공한 경우 응답 내용을 읽어 처리합니다.
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder responseContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();

            // 응답 내용 출력
            System.out.println("API 응답 내용: " + responseContent.toString());
        } else {
            // 요청이 실패한 경우 오류 처리
            System.err.println("HTTP 요청 실패: " + response.getStatusLine().getReasonPhrase());
        }
    }
}