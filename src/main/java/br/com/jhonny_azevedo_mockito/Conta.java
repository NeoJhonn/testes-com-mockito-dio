package br.com.jhonny_azevedo_mockito;

public class Conta {

    private double saldo;

    public Conta(double saldo) {
        this.saldo = saldo;
    }

    public void pagaBoleto(double valorAPagar) {
        validaSaldo(valorAPagar);
        debita(valorAPagar);
        enviaCreditoParaEmisso(valorAPagar);
    }

    public void validaSaldo(double valorAPagar) {
        if (valorAPagar > this.saldo) {
            throw  new IllegalArgumentException("Saldo Insuficiente");
        }
    }

    public void debita (double valorAPagar) {
        if (valorAPagar > this.saldo) {
            throw  new IllegalArgumentException("Saldo Insuficiente");
        }
        this.saldo -= valorAPagar;
    }

    public void enviaCreditoParaEmisso(double valorAPagar) {
        // envia o valor para o emissor do boleto
    }
}
