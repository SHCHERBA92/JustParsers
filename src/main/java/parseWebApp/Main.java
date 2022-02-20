package parseWebApp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        final String HOME_URL = "https://faktodrom.com";
        ArrayList<String> urlString = new ArrayList<>();

        /**
         * http запрос будет большим
         * */
        String getUrl = "https://faktodrom.com/?ids=613,612,611,610,609,608,607,606,605,604&rFilterFromId=20&rfNotFirstQuery=1&action=4&ids=613,612,611,610,609,608,607,606,605,604,603,602,601,600,599,598,597,596,595,594";

        /**
         * Парсинг ВЕБ страницы
         * */
        try {
            urlString = (ArrayList<String>) getAllHref(HOME_URL);
            urlString.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<String> getAllHref(String homeUrl) throws IOException {
        Document document = Jsoup.connect(homeUrl).get();

        Elements h1Element = document.getElementsByAttributeValue("class","feed-record-title");
//        System.out.println(h1Element);
//        System.out.println("==================");

        ArrayList<String> urlString = new ArrayList<>();

        for (int i = 0; i < h1Element.size(); i++) {
            urlString.add(h1Element.get(i).getElementsByAttribute("href").attr("href"));
        }
       return urlString.stream().map(s -> {
                                            return homeUrl + s;
                                        }).collect(Collectors.toList());  //.forEach(System.out::println);

//        return urlString;
    }
}
