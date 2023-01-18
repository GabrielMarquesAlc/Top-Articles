package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



    /*
     * Complete the 'topArticles' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts INTEGER limit as parameter.
     * base url for copy/paste:
     * https://jsonmock.hackerrank.com/api/articles?page=<pageNumber>
     */

public class Solution {
    public static void main(String[] args) throws IOException {
        int limit = 10;
        List<String> result = topArticles(limit);
    }

    public static List<String> topArticles(int limit) {
          class Retorno {
            private Integer ordenacao;
            private String articles;

            public Retorno(Integer ordenacao, String articles) {
                this.ordenacao = ordenacao;
                this.articles = articles;
            }
            public String getArticles() {
                return articles;
            }
        }

        List<Articles>listData = new ArrayList<>();
        listData.add(new Articles());

        PaginationData paginationData = new PaginationData();
        String  url = "https://jsonmock.hackerrank.com/api/articles?page=<pageNumber>";

        HttpEntity<PaginationData> request = new HttpEntity<>(paginationData);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PaginationData> response = null;

        response = restTemplate.exchange(url
                        .replace("<pageNumber>", String.valueOf(1)),
                HttpMethod.GET, request, PaginationData.class );

        List<ResponseEntity<PaginationData>> listResponse = new ArrayList<>();

        int totalPages = response.getBody().getTotalPages();
        List<Retorno> listaArticlesTop = new ArrayList<>();

        for (int i =1; i<=totalPages;i++){
            response = restTemplate.exchange(url
                            .replace("<pageNumber>", String.valueOf(i)),
                    HttpMethod.GET, request, PaginationData.class );
            listResponse.add(response);
        }

        listResponse.forEach(respo->
        {
            respo.getBody().getData().forEach(data->{
                if (data.getTitle() != null || data.getStoryTitle()!= null ) {
                    String retornoString
                            = " title: " + (data.getTitle() != null ? data.getTitle() : data.getStoryTitle() )==null? "":data.getTitle() != null ? data.getTitle() : data.getStoryTitle() +
                            " story_tile: " + data.getStoryTitle() != null ? data.getStoryTitle() :""
                            + " num_comments" + data.getNumComments()==null?"0": data.getNumComments().toString();
                    if (data.getStoryTitle()!= null) {
                        Retorno ret = new Retorno(data.getNumComments() == null ? 0 : data.getNumComments(),retornoString);
                        listaArticlesTop.add(ret);
                    }
                }
            });
        });
        Collections.sort(listaArticlesTop,(o1, o2) -> o2.ordenacao - o1.ordenacao );
        limit = listaArticlesTop.size() >= limit ? limit : listaArticlesTop.size();
        List<String> responseFunction = new ArrayList<>();
        int i = 1;
        while (i <= limit ){
            responseFunction.add(listaArticlesTop.get(i-1).getArticles());
            i =i +1;
        }

        return responseFunction;

    }
}

