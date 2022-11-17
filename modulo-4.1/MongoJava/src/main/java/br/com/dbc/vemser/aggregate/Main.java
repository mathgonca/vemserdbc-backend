package br.com.dbc.vemser.aggregate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String uri = "mongodb://root:root@localhost:27017/?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&directConnection=true&ssl=false";
        MongoClient mongoClient = MongoClients.create(uri);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("vemserdbc");

        MongoCollection<Document> alunos = mongoDatabase.getCollection("alunos");

        System.out.println("-- Aggregate");
        System.out.println("-- Filtrado por status = A, Agrupado por idade");
        alunos.aggregate(Arrays.asList(
                Aggregates.match(Filters.eq("status", "A")),
                Aggregates.group("$idade", Accumulators.sum("qnt", 1))
        )).forEach(document -> System.out.println(document.toJson()));

        System.out.println("\n-- Agrupados por status");
        alunos.aggregate(Arrays.asList(
                Aggregates.match(Filters.empty()),
                Aggregates.group("$status", Accumulators.sum("qnt", 1))
        )).forEach(document -> System.out.println(document.toJson()));

        System.out.println("\n-- Agrupado por status, Somadas idades");
        alunos.aggregate(Arrays.asList(
                Aggregates.match(Filters.empty()),
                Aggregates.group("$status", Accumulators.sum("somaIdades", "$idade"))
        )).forEach(document -> System.out.println(document.toJson()));

        mongoClient.close();
    }
}
