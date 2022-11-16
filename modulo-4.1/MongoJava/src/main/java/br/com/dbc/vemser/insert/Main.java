package br.com.dbc.vemser.insert;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String uri = "mongodb://root:root@localhost:27017/?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&directConnection=true&ssl=false";
        MongoClient mongoClient = MongoClients.create(uri);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("vemserdbc");

        MongoCollection<Document> alunos = mongoDatabase.getCollection("alunos");

        Document novoAluno1 = new Document("nome", "Matheus")
                .append("data_nascimento", new Date(1991, 02, 10))
                .append("idade", 31)
                .append("curso", new Document("nome", "Culinária"))
                .append("notas", Arrays.asList(10, 10, 10))
                .append("Habilidades", Arrays.asList(new Document()
                        .append("nome", "Cortar cebola")
                        .append("nivel", "Choro avançado")))
                .append("status", "A");

        Document novoAluno2 = new Document("nome", "Giulia")
                .append("data_nascimento", new Date(1989, 03, 23))
                .append("idade", 33)
                .append("curso", new Document("nome", "Publicidade"))
                .append("notas", Arrays.asList(10, 10, 10))
                .append("Habilidades", Arrays.asList(new Document()
                        .append("nome", "Inglês")
                        .append("nivel", "Intermediário")))
                .append("status", "C");

        Document novoAluno3 = new Document("nome", "Jeffrey")
                .append("data_nascimento", new Date(1964, 01, 12))
                .append("idade", 58)
                .append("curso", new Document("nome", "Comércio"))
                .append("notas", Arrays.asList(6, 8, 8))
                .append("Habilidades", Arrays.asList(new Document()
                        .append("nome", "Bilionário")
                        .append("nivel", "Avançado")))
                .append("status", "E");

        List<Document> documentList = Arrays.asList(novoAluno2, novoAluno3);

        alunos.insertOne(novoAluno1);
        alunos.insertMany(documentList);

        mongoClient.close();
    }
}
