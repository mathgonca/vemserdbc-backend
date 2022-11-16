db.alunos.insert({
  nome: "Márcio Araújo",
  data_nascimento: new Date(2000, 03, 01),
})

db.alunos.insert({
  nome: "Milena Almeida",
  data_nascimento: new Date(1988, 10, 05),
})

db.alunos.insert({
  nome: "Bruna Campos",
  data_nascimento: new Date(1979, 10, 17),
})

db.alunos.find({}).sort( { nome: 1, _id: -1 } )
db.alunos.find({}).sort( { data_nascimento: -1 } )

db.alunos.find({}).sort( { data_nascimento: 1 } ).limit(3)

db.alunos.find({}, 
{
  _id: 0,
  data_nascimento: { $dateToString: { date: "$data_nascimento", format: "%d/%m/%Y" }}, 
  nome: 1
})
