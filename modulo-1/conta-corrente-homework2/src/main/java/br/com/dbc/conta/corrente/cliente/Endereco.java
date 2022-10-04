package br.com.dbc.conta.corrente.cliente;

public class Endereco {
    private final int RESIDENCIAL = 1;
    private final int COMERCIAL = 2;

    private int tipo;
    private String logradouro;
    private int numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;

    public Endereco() {
    }

    public Endereco(int tipo, String logradouro, int numero, String complemento, String cep, String cidade,
                    String estado, String pais) {
        this.tipo = tipo;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

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
