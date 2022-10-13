--- Selecionar todos os países ordenados por nome decrescente;
SELECT * FROM VEM_SER.PAIS ORDER BY NOME DESC;

--- Selecionar logradouro e cep dos endereços. Porém, somente os logradouros que comecem com a letra ‘a’ (maiúsculo ou minúsculo);
SELECT LOGRADOURO, CEP FROM VEM_SER.ENDERECO WHERE LOGRADOURO LIKE 'a%' OR LIKE 'A%';

--- Selecionar todos os endereços que tenham cep com final ‘0’;
SELECT * FROM VEM_SER.ENDERECO WHERE CEP LIKE '%0';

--- Selecionar todos os endereços que tenham números entre 1 e 100;
SELECT * FROM VEM_SER.ENDERECO WHERE NUMERO BETWEEN 1 AND 100;

--- Selecionar todos os endereços que comecem por “RUA” e ordenar pelo cep de forma decrescente;
SELECT * FROM VEM_SER.ENDERECO WHERE LOGRADOURO LIKE 'RUA%' ORDER BY CEP DESC;

--- Selecionar a quantidade de endereços cadastrados na tabela;
SELECT COUNT(*) FROM VEM_SER.ENDERECO;

--- Selecionar a quantidade de endereços cadastrados agrupados pelo id da cidade;
SELECT COUNT(*) FROM VEM_SER.ENDERECO GROUP BY ID_CIDADE;
