use vemserdbc
db.createCollection("alunos")

db.alunos.insert(
  {
    "nome" : "Matheus Goncalves",
    "data_nascimento" : new Date (1991,02,10)
  }
)

db.alunos.insert(
  {
    "nome" : "Giulia Ramires",
    "data_nascimento" : new Date (1989,03,23)
  }
)

db.alunos.insert(
  {
    "nome" : "Jeff Bezos",
    "data_nascimento" : new Date (1964,01,12)
  }
)

db.alunos.find()
db.alunos.find( { data_nascimento: { $gt: new Date('1970-01-01') } } )
db.alunos.find( { nome: "Giulia Ramires" } )
db.alunos.find( { data_nascimento: { $eq: new Date (1991,02,10) } })
