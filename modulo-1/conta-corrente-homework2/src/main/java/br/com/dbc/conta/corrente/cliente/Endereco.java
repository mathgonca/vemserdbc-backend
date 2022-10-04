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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void imprimirEndereco() {
        String tipoDoEnderecoString = null;

        if (tipo == RESIDENCIAL) {
            tipoDoEnderecoString = "Residencial";
        } else if (tipo == COMERCIAL) {
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
