package br.com.dbc.vemser.update;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        String uri = "mongodb://root:root@localhost:27017/?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&directConnection=true&ssl=false";
        MongoClient mongoClient = MongoClients.create(uri);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("vemserdbc");

        MongoCollection<Document> alunos = mongoDatabase.getCollection("alunos");

        alunos.updateOne(Filters.eq("nome", "Ana"),
                new Document("$set", new Document("status", "x")));

        alunos.updateOne(Filters.eq("curso", new Document("nome", "Ciência da computação")),
                new Document("$set", new Document("nome", "Instrutor VemSer Rafa")));

        alunos.updateOne(Filters.eq("nome", "Jeffrey"),
                new Document("$set", new Document("nome", "Jeffrey, Jeffrey Bezos")));

        mongoClient.close();
    }
}
