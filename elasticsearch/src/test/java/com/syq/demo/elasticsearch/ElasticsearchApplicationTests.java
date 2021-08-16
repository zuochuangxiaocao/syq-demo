package com.syq.demo.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.syq.demo.elasticsearch.pojo.User;
import lombok.val;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ApiApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    //创建索引
    @Test
    void createIndex() throws IOException {

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("sxx_index");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().
                create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    //删除索引
    @Test
    void deleteIndex() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("sxx_index");

        AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices()
                .delete(deleteIndexRequest, RequestOptions.DEFAULT);

        System.out.println(acknowledgedResponse.isAcknowledged());
    }

    //创建文档
    @Test
    void createDocument() throws IOException {

        //创建对象
        User user = new User("sxx", 1);
        //创建请求
        IndexRequest indexRequest = new IndexRequest("sxx_index");
        //创建规则
        indexRequest.id("1");
        indexRequest.timeout("1s");
        //放入数据
        IndexRequest source = indexRequest.source(JSON.toJSONString(user), XContentType.JSON);

        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        System.out.println(index.toString());

    }

    //获取文档
    @Test
    void getDoument() throws IOException {

        GetRequest request = new GetRequest("sxx_index", "1");
        GetResponse getResponse = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSource());
        System.out.println(getResponse.toString());
    }

    //更新文档
    @Test
    void updateDoument() throws IOException {
        UpdateRequest request = new UpdateRequest("sxx_index", "1");

        User user = new User("xiaoming", 100);

        request.doc(JSON.toJSONString(user), XContentType.JSON);

        UpdateResponse update = restHighLevelClient.update(request, RequestOptions.DEFAULT);


        System.out.println(update.status());
    }

    //删除文档
    @Test
    void deleteDoument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("sxx_index", "3");
        deleteRequest.timeout("1s");
        DeleteResponse delete = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);

        System.out.println(delete.status());
    }

    //批量操作
    @Test
    void BulkDoument() throws IOException {

        BulkRequest bulkRequest = new BulkRequest();

        bulkRequest.timeout("10s");

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("xiaoming1", 11));
        users.add(new User("xiaoming2", 12));
        users.add(new User("xiaoming3", 13));
        users.add(new User("xiaoming4", 14));
        users.add(new User("xiaoming5", 15));
        users.add(new User("xiaoming6", 16));
        users.add(new User("xiaoming7", 17));
        users.add(new User("xiaoming8", 18));
        users.add(new User("xiaoming9", 19));


        for (int i = 0; i < users.size(); i++) {
            bulkRequest.add(new IndexRequest("sxx_index")
                    .id("" + (i + 1))
                    .source(JSON.toJSONString(users.get(i)), XContentType.JSON)
            );
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.hasFailures());
    }


    //搜索
    @Test
    void search() throws IOException {

        SearchRequest searchResult = new SearchRequest("sxx_index");//搜索的库名
        //构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //查询条件
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("name", "xiaoming1");//精确匹配
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //分页
        //  searchSourceBuilder.from(0);
        // searchSourceBuilder.size(2);

        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);

        //执行搜索
        searchResult.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchResult, RequestOptions.DEFAULT);

        System.out.println(JSON.toJSONString(search.getHits()));

        System.out.println("-----------------------------------");
        ArrayList<Map<String, Object>> objects = new ArrayList<>();
        for (SearchHit hit : search.getHits().getHits()) {
            //System.out.println(hit.getSourceAsMap());
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField age = highlightFields.get("name");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            if (age != null) {
                Text[] fragments = age.fragments();
                String n_title = "";
                for (Text text : fragments) {
                    n_title += text;
                }
                sourceAsMap.put("age", n_title);
            }
            objects.add(sourceAsMap);
        }

        //objects.forEach(stringObjectMap -> System.out.println());
        for (Map<String, Object> map : objects) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
        }

    }

}
