package br.com.dbc.conta.corrente;

public class Endereco {
    final int RESIDENCIAL = 1;
    final int COMERCIAL = 2;

    public int tipo;
    public String logradouro;
    public int numero;
    public String complemento;
    public String cep;
    public String cidade;
    public String estado;
    public String pais;

    public void imprimirEndereco() {
        String tipoDoEnderecoString = null;

        if (tipo == RESIDENCIAL) {
            tipoDoEnderecoString = "Residencial";
        }

        if (tipo == COMERCIAL) {
            tipoDoEnderecoString = "Comercial";
        }

        System.out.println("Endereço " + tipoDoEnderecoString);
        System.out.println("=".repeat(20));
        System.out.println(pais);
        System.out.println(tipoDoEnderecoString);
        System.out.println(logradouro + ", " + numero + " compl: " + complemento);
        System.out.println(cidade + " - " + estado + ", " + cep);
        System.out.println("=".repeat(20));
    }
}
