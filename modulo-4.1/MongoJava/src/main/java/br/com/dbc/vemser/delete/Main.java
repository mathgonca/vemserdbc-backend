package br.com.dbc.vemser.delete;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        String uri = "mongodb://root:root@localhost:27017/?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&directConnection=true&ssl=false";
        MongoClient mongoClient = MongoClients.create(uri);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("vemserdbc");

        MongoCollection<Document> alunos = mongoDatabase.getCollection("alunos");

        DeleteResult result1 = alunos.deleteOne(Filters.eq("nome", "Gabriel"));
        System.out.println(result1);

        DeleteResult result2 = alunos.deleteOne(Filters.eq("status", "x"));
        System.out.println(result2);

        DeleteResult result3 = alunos.deleteOne(Filters.eq("idade", 58));
        System.out.println(result3);

        mongoClient.close();
    }
}
