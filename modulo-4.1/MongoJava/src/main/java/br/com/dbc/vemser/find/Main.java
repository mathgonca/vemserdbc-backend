package br.com.dbc.vemser.find;

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

        System.out.println("-- Aluno");
        System.out.println("-".repeat(75));
        System.out.println("-- Find Queries");
        System.out.println("-".repeat(75));
        System.out.println("-- Find Todos");
        alunos.find().forEach(document -> System.out.println(document.toJson()));

        Document aluno = alunos.find(new Document("nome", "Gabriel"))
                .first();
        System.out.println("-".repeat(75));
        System.out.println("-- Find Gabriel");
        System.out.println(aluno.toJson());

        System.out.println("-".repeat(75));
        System.out.println("-- Find alunos com Idade menor ou igual a 25");
        alunos.find(Filters.lte("idade", 25))
                .forEach(document -> System.out.println(document.toJson()));

        mongoClient.close();
    }
}